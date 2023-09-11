package Library;

import java.util.Scanner;

public class Students {
    Scanner sc = new Scanner(System.in);
    Student theStudent[] = new Student[50];
    public static int count =0;

    // method 1
    // to add books

    public void addStudent(Student s){
        for(int i =0;i<count;i++){
            if(s.regNo.equalsIgnoreCase(theStudent[i].regNo)){
                System.out.println("Student of Reg Num "+s.regNo+" is Already Registered.");
                return;
            }
        }
        if(count<=50){
            theStudent[count]=s;
            count++;
        }
    }

    public void showAllStudents(){
        System.out.println("Student Name\t\t RegisterName");
        for(int i =0;i<count;i++){
            System.out.println(theStudent[i].studentName+"\t\t"+theStudent[i].regNo);
        }
    }

    //method 3
    // To check the Student

    public int isStudent(){
        System.out.println("Enter Reg Number");
        String regNum = sc.nextLine();
        for(int i =0;i<count;i++){
            if(theStudent[i].regNo.equalsIgnoreCase(regNum)){
                return i;
            }
        }
        return -1;
    }

    // method 4
    // To Remove the Books

    public void checkOutBook(books book){
        int studentIndex = this.isStudent();
        if(studentIndex!=-1){
            System.out.println("checking out");
            book.showAllBooks();
            book b = book.checkOutBook();
            System.out.println("checking out");
            if(b!=null){
                if(theStudent[studentIndex].booksCount<=3){
                    System.out.println("adding book");
                    theStudent[studentIndex].borrowedBooks[theStudent[studentIndex].booksCount]=b;
                    theStudent[studentIndex].booksCount++;
                    return;
                }else{
                    System.out.println("Student Can not Borrow more than 3 Books. ");
                    return;
                }
            }
            System.out.println("Book is not Available.");
        }
    }

    //method 5
    // to add the book


    public void checkInBook(books book){
        int studentIndex = this.isStudent();
        if(studentIndex!=-1){
            System.out.println("S.No\t\tBook Name\t\t\tAuthor Name");
            Student s = theStudent[studentIndex];
            for(int i =0;i<s.booksCount;i++){
                System.out.println(
                        s.borrowedBooks[i].Sno+"\t\t"+
                                s.borrowedBooks[i].bookName+"\t\t"+
                                s.borrowedBooks[i].authorName
                );
            }
            System.out.println("Enter a serial Number of to be checked in");
            int sNo = sc.nextInt();
            for(int i =0;i<s.booksCount;i++){
                if(sNo==s.borrowedBooks[i].Sno){
                    book.checkInBook(s.borrowedBooks[i]);
                    s.borrowedBooks[i] =null;
                    return;
                }
            }
            System.out.println("Book of Serial No "+ sNo +" not Found");
        }
    }
}
