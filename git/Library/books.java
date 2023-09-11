package Library;

import java.util.Scanner;

public class books {
    book theBooks[] = new book[50];
    public static int count;
    Scanner sc = new Scanner(System.in);

    public static int compareBookObjects(book b1,book b2){
        if(b1.bookName.equalsIgnoreCase(b2.bookName)){
            System.out.println("Book of this Name Already Exist.");
        return 0;
        }
        if(b1.Sno==b2.Sno){
            System.out.println("Book of this SerialNo is Already Exist");
        return 0;
        }
        return 1;

    }

//     method add book
    public void addBook(book b){
        for(int i =0;i<count;i++) {
            if ((this.compareBookObjects(b, this.theBooks[i])) == 0) {
                return;
            }
        }

            if(count<50){
                theBooks[count] = b;
                count++;

            }else{
                System.out.println("NO SPACE AVAILABLE");
            }

    }
    public void searchBySno()
    {
        System.out.println("\t\t\tSEARCH BY S-NO\n");
        int sNO;
        System.out.println("enter Serial No of Books");
        sNO = sc.nextInt();
        int flag = 0;
        System.out.println("SNO\tName\t\tAuthor Name \tAvailable-Qty\t\tTotal Qty");
        for(int i =0;i<count;i++){
            if(sNO==theBooks[i].Sno){
                System.out.println(
                        theBooks[i].Sno+"\t"
                        +theBooks[i].bookName+"\t\t"+
                                theBooks[i].authorName+"\t"
                        +theBooks[i].bookQtyCopy+"\t\t"+
                                theBooks[i].bookQty
                );
                flag++;
                return;
            }
        }
        if(flag==0){
            System.out.println("No Book for Serial No "+ sNO + "Found");
        }
    }

//    method 4 to search by author name
    public void searchByAuthorName(){
        System.out.println("\t\t\tSEARCH BY AUTHORNAME");
                sc.nextLine();
        System.out.println("Enter a Author's Name");
        String authorName = sc.nextLine();
        int flag=0;
        System.out.println("S.No\t\tName\t\tAuthor\t\tAvailableQty\t\tTotal Qty");
        for(int i =0;i<count;i++){
            if(authorName.equalsIgnoreCase(theBooks[i].authorName)){
                System.out.println(
                        theBooks[i].Sno+"\t\t"+
                                theBooks[i].bookName+"\t\t"+
                                theBooks[i].authorName+"\t\t"+
                                theBooks[i].bookQtyCopy+"\t\t"+
                                theBooks[i].bookQty
                );
                flag++;
            }
        }
        if(flag==0){
            System.out.println("No book of "+authorName+" Found");
        }
    }
//    method 5
    // to display the books
    public void showAllBooks(){
        System.out.println("\t\t\tSHOWING ALL BOOKS\n");
        System.out.println("S.no\t\tName\t\tAuthor Name\t\tAvailable Qty\t\tTotal Qty");
        for(int i =0;i<count;i++){
            System.out.println(
                    theBooks[i].Sno+"\t\t"+
                            theBooks[i].bookName+"\t\t"+
                            theBooks[i].authorName+"\t\t"+
                            theBooks[i].bookQtyCopy+"\t\t"+
                            theBooks[i].bookQty
            );
        }
    }

//    method 6
    // to edit the books
    public void upgradeBooksQty(){
        System.out.println("\t\t\tUPGRADE THE QUANTITY OF BOOKS");
        System.out.println("Enter Serial No Of Books");
        int sNo = sc.nextInt();
        for(int i =0;i<count ;i++){
            if(sNo==theBooks[i].Sno){
                System.out.println("Enter No of Book To Be Added");
                int addingQty = sc.nextInt();
                theBooks[i].bookQty+=addingQty;
                theBooks[i].bookQtyCopy+=addingQty;
                return;
            }

        }
    }
    // methods 7
    //to create menu


    public void DisplayMenu(){
        // Displaying menu
        System.out.println(
                "-----------------------------------------------------------------------------------------------"
        );
        System.out.println("press 1 for Adding The books");
        System.out.println("Press 0 for Exit Application");
        System.out.println("Press 2 to upgrade the Quantity of Books");
        System.out.println("press 3 to Search Books");
        System.out.println("Press 4 to Show All books");
        System.out.println("Press 5 to Register Students");
        System.out.println("Press 6 to Show All Registered Students.");
        System.out.println("Press 7 to Checkout Book");
        System.out.println("press 8 to Check in Books");
        System.out.println(
                "-------------------------------------------------------------------------------------------------"
        );
    }
 // Method 8
 // to search the library
 public int isAvailable(int sNo){
        for(int i =0;i<count;i++){
            if(sNo==theBooks[i].Sno){
                if(theBooks[i].bookQtyCopy>0){
                    System.out.println("Book is Available.");
                    return i;
                }
                System.out.println("Book is unAvailable");
                return -1;
            }
        }
     System.out.println("No Book of Serial Number "+ " Available in Library");
        return -1;
   }

//   Method 9
//    to remove the book from the library
    public book checkOutBook(){
        System.out.println("Enter a Serial No of Book to be CheckOut .");
        int sNo = sc.nextInt();
        int bookIndex = isAvailable(sNo);
        if(bookIndex!=-1){
            theBooks[bookIndex].bookQtyCopy--;
            return  theBooks[bookIndex];
        }
        return null;
    }

    // Method 10
    // To add the boo to the Library

    public void checkInBook(book b){
        for(int i =0;i<count ;i++){
            if(b.equals(theBooks[i]))
                theBooks[i].bookQtyCopy++;
            return;
        }
    }

}
