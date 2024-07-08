/**Library management software provides all the necessary services to the enduser.
This application is designed using java language.
The packages used is java.io package and java.util package */
import java.util.*;
import java.io.*;
//serializable interface is implemented
//serialization is a mechanism of writing the state of an object into a byte-stream
class Books implements Serializable{
    //attributes
    int isbn;//ISBN number is unique for every number
    String book;
    String author;
    int edition;
    String publications;
    String category;
    //parameterized constructor
    Books(int isbn,String book,String author,int edition,String publications,String category){
        this.isbn=isbn;
        this.book=book;
        this.author=author;
        this.edition=edition;
        this.publications=publications;
        this.category=category;
    }
    //returns the value in string format
    public String toString(){
        return isbn+" "+book+" "+author+" "+edition+" "+publications+" "+category;
    }
}
//main method
class BooksDemo{
    //attributes
    String email;
    String password;
    int flag;
    String Recommend_book;
    String experience;
    String regno;
    public static void main(String[] args) throws Exception{
        Scanner s=new Scanner(System.in);
        Scanner s1=new Scanner(System.in);
        System.out.println("WELCOME TO LIBRARY");
        System.out.println("Press...");
        System.out.println("1.Login");
        System.out.println("Any other to go BACK");
        int flag=s.nextInt();
        if(flag==1)
        {
            System.out.println("Enter the emailId:");
            String email=s.nextLine();
            System.out.println("Enter your password:");
            String password=s.nextLine();
            //object is created for Login class
            Login verify=new Login();
            //this method opens the file
            verify.openFile();
            //invoking this method validates the entered details
            boolean check = verify.checklog(email,password);
            while(check==false){
                System.out.println("Enter valid details");
                System.out.println("Enter the emailId:");
                email=s.nextLine();
                System.out.println("Enter your password:");
                password=s.nextLine();
                verify.openFile();
                check = verify.checklog(email,password);
            
            }
            int choice=-1;
            while(check==true && choice!=0)
            {
                //file object is created
                File file=new File("inventory.txt");
                File file1=new File("Feedback.txt");
                //object gets created for the collection of ArrayList
                ArrayList<Books> al=new ArrayList<Books>();
                ObjectOutputStream oos=null;
                ObjectInputStream ois=null;
                ListIterator li=null;
                //checks whether the file is present or not
                //if presents the content of the file will get read into ArrayList
                if(file.isFile()){
                    ois=new ObjectInputStream(new FileInputStream(file));
                    al=(ArrayList<Books>)ois.readObject();
                    ois.close();
                }
                do{
                    //Menu or services provided by the software
                    System.out.println("1.Search by book name");
                    System.out.println("2.Search by ISBN number");
                    System.out.println("3.Display");
                    System.out.println("4.Update");
                    System.out.println("5.Delete");
                    System.out.println("6.Help");
                    System.out.println("7.Feedback");
                    System.out.println("8.Report on total number of books");
                    System.out.println("9.Enter 0 to EXIT");
                    System.out.println("Enter your choice:");
                    choice=s.nextInt();
                    switch(choice){
                        case 1:
                            if(file.isFile()){
                                ois=new ObjectInputStream(new FileInputStream(file));
                                al=(ArrayList<Books>)ois.readObject();
                                //ObjectOutputStream gets closed
                                ois.close();
                                boolean found=false;
                                System.out.println("Enter book name to search:");
                                String book=s1.nextLine();
                                System.out.println("----------------------------------------");
                                li=al.listIterator();
                                while(li.hasNext()){
                                    Books e=(Books)li.next();
                                    if(e.book.equals(book)){
                                        System.out.println(e);
                                        found=true;
                                    }

                                }
                                if(!found)
                                    System.out.println("Book not found");
                                System.out.println("---------------------------------------");

                            }else{
                                System.out.println("file not exists");
                            }
                        break;
                        case 2:
                            //book can be searched through ISBN number
                            if(file.isFile()){
                                ois=new ObjectInputStream(new FileInputStream(file));
                                al=(ArrayList<Books>)ois.readObject();
                                ois.close();
                                boolean found=false;
                                System.out.println("Enter book ISBN number to search:");
                                int isbn=s.nextInt();
                                System.out.println("----------------------------------------");
                                //invokes ListIterator method
                                li=al.listIterator();
                                while(li.hasNext()){
                                    Books e=(Books)li.next();
                                    if(e.isbn==isbn){
                                        System.out.println("Book found");
                                        System.out.println(e);
                                        found=true;
                                    }

                                }
                                if(!found)
                                    System.out.println("Book not found");
                                System.out.println("---------------------------------------");

                            }else{
                                System.out.println("file not exists");
                            }
                        break;
                        case 3:
                            if(file.isFile()){
                                ois=new ObjectInputStream(new FileInputStream(file));
                                al=(ArrayList<Books>)ois.readObject();
                                ois.close();
                                System.out.println("------------------------------------------");
                                li=al.listIterator();
                                //displays all content in the file
                                while(li.hasNext()){
                                    System.out.println(li.next());
                                }
                                System.out.println("--------------------------------------");
                            }else{
                                System.out.println("file not exits");
                            }
                        break;
                        case 4:
                            //allows librarian to enter the book details available in the library
                            System.out.println("Enter how many books you want to add:");
                            int n=s.nextInt();
                            for(int i=0;i<n;i++){
                                System.out.print("Enter ISBN number: ");
                                int isbn=s.nextInt();
                                System.out.print("Enter book name: ");
                                String book=s1.nextLine();
                                System.out.print("Enter author name: ");
                                String author=s1.nextLine();
                                System.out.print("Enter edition: ");
                                int edition=s.nextInt();
                                System.out.print("Enter publisher's name: ");
                                String publications=s1.nextLine();
                                System.out.print("Enter category: ");
                                String category=s1.nextLine();
                                //invokes constructor
                                //data gets stored into ArrayList
                                al.add(new Books(isbn,book,author,edition,publications,category));
                            }
                        //writes data to file
                        //the data stored in the file cannot be read
                        oos=new ObjectOutputStream(new FileOutputStream(file));
                        oos.writeObject(al);
                        oos.close();
                        break;
                        case 5:
                            if(file.isFile()){
                                ois=new ObjectInputStream(new FileInputStream(file));
                                al=(ArrayList<Books>)ois.readObject();
                                ois.close();
                                boolean found=false;
                                System.out.println("Enter ISBN number:");
                                int isbn=s.nextInt();
                                System.out.println("----------------------------------------");
                                li=al.listIterator();
                                while(li.hasNext()){
                                    Books e=(Books)li.next();
                                    //invoking remove() deletes the specified data from the file
                                    if(e.isbn==isbn){
                                        li.remove();
                                        found=true;
                                    }

                                }
                                if(found){
                                    oos=new ObjectOutputStream(new FileOutputStream(file));
                                    oos.writeObject(al);
                                    oos.close();
                                    System.out.println("Book deleted"); 
                                }else{
                                    System.out.println("Book not exists");
                                }
                                System.out.println("-------------------------------");
                            }else{
                                System.out.println("not exists");
                            }
                            break;
                            case 6:
                                try{
                                    //reads the data from the file  
                                    InputStream stream = new FileInputStream("Help.txt");  
                                    Reader reader = new InputStreamReader(stream);  
                                    int data = reader.read();  
                                    while (data != -1) {  
                                        System.out.print((char) data);  
                                        data = reader.read();  
                                    }
                                    System.out.println(" ");
                                    System.out.println("---------------END----------------");  
                                } catch (Exception e) {
                                        //exception gets raised if file not exists  
                                        e.printStackTrace();  
                                }
                            break;
                            case 7:
                                System.out.print("Enter registerd number: ");
                                String regno=s1.nextLine();
                                System.out.print("How is online library experience: ");
                                String experience=s1.nextLine();
                                System.out.print("Enter if any recommended books: ");
                                String Recommend_book=s1.nextLine();
                                oos=new ObjectOutputStream(new FileOutputStream(file1));
                                System.out.println("----------------------------------");
                                //ObjectOutputStream writes the object to the file
                                oos.writeObject(regno);
                                oos.writeObject(experience);
                                oos.writeObject(Recommend_book);
                                System.out.println("---------------------------------");
                                oos.close();
                            break;
                            case 8:
                                if(file.isFile()){
                                    ois=new ObjectInputStream(new FileInputStream(file));
                                    al=(ArrayList<Books>)ois.readObject();
                                    ois.close();
                                    //inititializing the starting count to zero
                                    int all=0;
                                    System.out.println("------------------------------------------");
                                    li=al.listIterator();
                                    while(li.hasNext()){
                                        System.out.println(li.next());
                                        all=all+1;
                                    }
                                    System.out.println("Total books="+all);
                                    System.out.println("--------------------------------------");
                                    }else{
                                        System.out.println("file not exits");
                                    }
                                    break;
                            
                     
                                

                    }
 
                }while(choice!=0);//end of do-while loop
            }//end of while loop
        }
        else{
            System.out.println("Thank you!...");
        } 
    } 
    //end of main method     
}  
