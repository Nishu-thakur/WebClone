import struct

import socket
from ip_header import IP
import sys
import os


class ICMP:
    def __init__(self,buff=None):
        header = struct.unpack('<BBHHH',buff)
        self.type = header[0]
        self.code = header[1]
        self.sum = header[2]
        self.id = header[3]
        self.seq = header[4]

    
    def sniff(host):

        if os.name=='nt':
            socket_protocol = socket.IPPROTO_IP
        else:
            socket_protocol = socket.IPPROTO_ICMP
        
        sniffer = socket.socket(socket.AF_INET,socket.SOCK_RAW,socket_protocol)
        sniffer.bind((host,0))
        sniffer.setsockopt(socket.IPPROTO_IP,socket.IP_HDRINCL,1)


        if os.name=='nt':
            sniffer.ioctl(socket.SIO_RCVALL,socket.RCVALL_ON)
        
        try:
            while True:
               raw_buffer = sniffer.recvfrom(65535)[0]
               ip_header = IP(raw_buffer[0:20])
               offset = ip_header.ihl*4
               icmp_header = ICMP(raw_buffer[offset:offset+8])
               print('PROTOCOL: %s %s-->%s' %(ip_header.protocol,ip_header.src_address,ip_header.dst_address))
               print('ICMP_type -> Type:%s and code:%s' %(icmp_header.type,icmp_header.code))
        except KeyboardInterrupt:
            if os.name =='nt':
                sniffer.ioctl(socket.SIO_RCVALL,socket.RCVALL_OFF)
            sys.exit()

if __name__=="__main__":
    if len(sys.argv)==2:
        host = sys.argv[1]
    else:
        host = '192.168.0.119'
    ICMP.sniff(host)