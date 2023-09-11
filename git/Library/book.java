package Library;

import java.util.Scanner;

public class book {


    public int Sno;
    public String bookName;
    public String authorName;
    public int bookQty;
    public int bookQtyCopy;
    Scanner sc= new Scanner(System.in);
    public book(){
        System.out.println("Enter the serial no of Book");
        this.Sno = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Book Name");
        this.bookName =sc.nextLine();
        System.out.println("Enter authorName");
        this.authorName =sc.nextLine();
        System.out.println("Enter quantity of books");
        this.bookQty = sc.nextInt();
        bookQtyCopy = this.bookQty;
    }
}
