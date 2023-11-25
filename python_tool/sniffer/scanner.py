import ipaddress
import os
import socket
import sys
import threading
import time
from ip_header import IP
from Icmp_decoder import ICMP


SUBNET = '192.168.0.0/24'
MESSAGE = 'PYTHONRULES!'

def udp_sender():
   
    with socket.socket(socket.AF_INET,socket.SOCK_DGRAM) as sender:
        for ip in ipaddress.ip_network(SUBNET).hosts():
            sender.sendto(bytes(MESSAGE,'utf8'),(str(ip),65212))
    

class Scanner:
    def __init__ (self,host):
        self.host = host
        if os.name == 'nt':
            socket_protocol = socket.IPPROTO_IP
        else:
            socket_protocol = socket.IPPROTO_ICMP
        
        self.sniffer = socket.socket(socket.AF_INET,socket.SOCK_RAW,socket_protocol)
        self.sniffer.bind((host,0))
        self.sniffer.setsockopt(socket.IPPROTO_IP,socket.IP_HDRINCL,1)
        if os.name=='nt':
            self.sniffer.ioctl(socket.SIO_RCVALL,socket.RCVALL_ON)
        
    def sniff(self):
        host_up = set([f'{str(self.host)}*'])     

        try:
            while True:
                raw_buffer = self.sniffer.recvfrom(65535)[0]
                IP_header = IP(raw_buffer[0:20])
                print(IP_header.protocol,IP_header.src_address)
                if IP_header.protocol=='ICMP':
                    offset = IP_header.ihl*4 
                    buf = raw_buffer[offset:offset+8]

                    icmp_header = ICMP(buf)
                    print(icmp_header.code ,icmp_header.type)
                    if icmp_header.type==3 and icmp_header.code==3:
                        if ipaddress.ip_address(IP_header.src_address) in ipaddress.IPv4Network(SUBNET):

                            if raw_buffer[len(raw_buffer)-len(MESSAGE):] == bytes(MESSAGE,'utf8'):
                                tgt = str(IP_header.src_address)

                                if tgt != self.host and tgt not in host_up:
                                    host_up.add(str(IP_header.src_address)) 
                                    print(f'Host Up: {tgt}')
        except KeyboardInterrupt:
            if os.name == 'nt':
                self.sniffer.ioctl(socket.SIO_RCVALL,socket.RCVALL_OFF)

            print('\nUser Terminated')

            if host_up:
                print(f'\n\n Summary:Hosts up on {SUBNET}')
            
            for host in sorted(host_up):
                print(f'{host}')
            print('')
            sys.exit()

if __name__=="__main__":
    if len(sys.argv)==2:
        host = sys.argv[1]
    else:
        host = '192.168.0.119'
    
    s = Scanner(host)
    time.sleep(5)
    t = threading.Thread(target=udp_sender)
    t.start()
    s.sniff()





