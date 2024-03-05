package BankManagementSystem.src.Users;

import java.util.Scanner;

import BankManagementSystem.src.Constants;
import BankManagementSystem.src.DataBases.Database;
import BankManagementSystem.src.Validation.Validator;

public class User{
    public final int userId;
    public final String firstName;
    public final String lastName;
    public final String username;
    public final String password;
    public final String emailId;
    public final long phone;
    static Scanner sc = new Scanner(System.in);

    public User(int userId, String firstName, String lastName, String username, String password, String emailId, long phone){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;        
        this.username = username;
        this.password = password;
        this.emailId = emailId;
        this.phone = phone;
    }

    // using recursion here
    public static final int AskUser(){
        System.out.println(Constants.repeat+"\n");
        System.out.println(Constants.askNewUser);
        System.out.println(Constants.repeat);
        int key;
        try{
            System.out.print("Key: ");
            key = sc.nextInt(); sc.nextLine();
            if(key<=0 || key >3){
                System.out.println(Constants.repeat);
                System.out.println(Constants.inValidKeyError);
                key = User.AskUser();
            }
        }catch(Exception e){
            System.out.println(Constants.repeat);
            System.out.println(Constants.inValidKeyError);
            sc.nextLine();
            key = User.AskUser();
        }
        return key;
    }

    public static final User signIn(){
            System.out.println(Constants.repeat);
            System.out.print("enter your username: ");
            String username = sc.nextLine();
            boolean fl = Database.checkForUserExist(username);
            String password;
            while(true){
                System.out.print("Enter Your Password: ");
                password = sc.nextLine();
                boolean tl = Validator.isValidPassword(password);
                if(!tl){
                    System.out.println(Constants.repeat+"\n");
                    System.out.println(Constants.passwordError);
                    System.out.println(Constants.repeat);
                    continue;
                }
                break;
            }
            User currentUser = Database.checkForUserExist(username, password); // cheking if user exists
            if(currentUser==null && fl == true){
                System.out.println("Wrong Password Sign-In Again!");   
            }
            else if(currentUser==null && fl == false){
                System.out.println("User dont exists! Please Signup");
            }
            return currentUser;
    }

    public static final User signUp(){
        boolean fl = false;
        System.out.println(Constants.repeat);
        String firstName;
        while(true){
            System.out.print("Enter Your FirstName: ");
            firstName = sc.nextLine();
            // System.out.println(firstName);
            fl = Validator.isValidName(firstName);
            if(!fl){
                System.out.println("Name Should Not Contain Any Number Or Special Characters");
                continue;
            }
            break;
        }
        String lastName;
        while(true){
            System.out.print("Enter Your LastName: ");
            lastName = sc.nextLine();
            // System.out.println(lastName);
            fl = Validator.isValidName(lastName);
            if(!fl){
                System.out.println("Name Should Not Contain Any Number Or Special Characters");
                continue;
            }
            break;
        }
        String emailId;
        while(true){
            System.out.print("Enter Your EmailId: ");
            emailId = sc.nextLine();
            // System.out.println(emailId);
            fl = Validator.isValidEmail(emailId);
            if(!fl){
                System.out.println("Please Enter a Valid Email Address");
                continue;
            }
            break;
        }
        long phone;
        while(true){
            try{
                System.out.print("Enter Your 10 Degit Phone Number: ");
                phone = Long.parseLong(sc.nextLine());
                // System.out.println(phone);
                if(String.valueOf(phone).length() != 10){
                    System.out.println("Must be 10 Degit Number!");
                    continue;
                }
                break;
            }
            catch(Exception e){
                System.out.println("Please Specify A Number!");
            }
        }
        // System.out.println(emailId);
        String username;
        while(true){
            System.out.print("Enter Your Username: ");
            username = sc.nextLine();
            // System.out.println(username);
            fl = Database.checkForUserExist(username);
            if(fl){
                System.out.println("This username is not available :(");
                continue;
            }
            break;
        }
        String password;
        while(true){
            System.out.print("Enter Your Password: ");
            password = sc.nextLine();
            fl = Validator.isValidPassword(password);
            if(!fl){
                System.out.println(Constants.repeat+"\n");
                System.out.println(Constants.passwordError);
                System.out.println(Constants.repeat);
                continue;
            }
            break;
        }
        // System.out.println(password);
        User currentUser = Database.checkForUserExist(username, password);
        if(currentUser!=null){
            return null;
        }
        int userId = Database.getId("userId", "users"); // will work fine
        currentUser = new User(userId, firstName, lastName, username, password, emailId, phone); // creating new user
        return currentUser;
    }


    public static final int AskCurrentUser(){
        System.out.println(Constants.repeat+"\n");
        System.out.println(Constants.askCurrentUser);
        System.out.println(Constants.repeat);
        int key;
        try{
            System.out.print("Key: ");
            key = sc.nextInt(); sc.nextLine();
            if(key<=0 || key >3){
                System.out.println(Constants.repeat);
                System.out.println(Constants.inValidKeyError);
                key = User.AskCurrentUser();
        }
        }catch(Exception e){
            System.out.println(Constants.repeat);
            System.out.println(Constants.inValidKeyError);
            sc.nextLine();
            key = User.AskCurrentUser();
        }
        return key;
    }
    
}