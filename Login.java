import java.util.*;
import java.io.*;

public class Login{
    public Scanner x;
    private String user_name, password;
    public void openFile()
    {
       try
       {
        x = new Scanner(new File("file.txt"));
       }
       catch(Exception e){
       System.out.println("Couldn't find file"); 
       System.exit(0);
       }
    }

    public boolean checklog(String username, String password)
    {
        String temp;
        String[] info;

        while(x.hasNext())
        {
           temp = x.nextLine();
           info = temp.split(" ");

           
           
           if(info[0].equals(username) && info[1].equals(password))
           {
            System.out.println("Login Successful");
            return true;
           }
        }
        System.out.println("Login failed wrong id or password");
        return false;
   }
}
