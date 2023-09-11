package Library;

import java.util.Scanner;

public class Library {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("***********************************************************************************");
        System.out.println("***************************WELCOME TO ALPU LIBRARY*********************************");
        System.out.println(
                     "            Select from the following options:                             "
        );
        System.out.println("***********************************************************************************");
       books ob = new books();
       Students obStudent = new Students();
       int choice ;
       int searchChoice;

       do{
           ob.DisplayMenu();
           choice = sc.nextInt();
           switch (choice){
               case 1:
                   book b = new book();
                   ob.addBook(b);
                   break;
               case 2:
                   ob.upgradeBooksQty();
                   break;
               case 3:
                   System.out.println("press 1 to search with book serial No.");
                   System.out.println("press 2 to search with book Author name");
                   searchChoice = sc.nextInt();
                   switch (searchChoice){
                       case 1:
                           ob.searchBySno();
                           break;
                       case 2:
                           ob.searchByAuthorName();
                   }
                   break;
               case 4:
                   ob.showAllBooks();
                   break;
               case 5:
                   Student s = new Student();
                   obStudent.addStudent(s);
                   break;
               case 6:
                   obStudent.showAllStudents();
                   break;
               case 7:
                   obStudent.checkOutBook(ob);
                   break;
               case 8:
                   obStudent.checkInBook(ob);
                   break;
               default:
                   System.out.println("Enter between 0 to 8");
           }
       }
       while(choice!=0);
    }
}
