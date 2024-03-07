package BankManagementSystem.src.Services;

import java.util.Scanner;

import BankManagementSystem.src.IRepo.Database;
import BankManagementSystem.src.Operations.AskUserInput;
import BankManagementSystem.src.Utils.Constants;

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



    public static User getUserInstance(int userId, String firstName, String lastName, String username, String password, String emailId, long phone){
        return new User(userId, firstName, lastName, username, password, emailId, phone);
    }


    public static final User signIn(){
            System.out.println(Constants.REPEAT);
            System.out.print("Enter your username: ");
            String username = AskUserInput.getUserInput();
            boolean fl = Database.getUser(username)==null?false:true;
            String password;
            while(true){
                System.out.print("Enter Your Password: ");
                password = AskUserInput.getUserInput();
                boolean tl = Validator.isValidPassword(password);
                if(!tl){
                    System.out.println(Constants.REPEAT+"\n");
                    System.out.println(Constants.WRONG_PASSWORD_ERROR);
                    System.out.println(Constants.REPEAT);
                    continue;
                }
                break;
            }
            User currentUser = Database.getUser(username, password); // cheking if user exists
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
        System.out.println(Constants.REPEAT);
        String firstName;
        while(true){
            System.out.print("Enter Your FirstName: ");
            firstName = AskUserInput.getUserInput();
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
            lastName = AskUserInput.getUserInput();
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
            emailId = AskUserInput.getUserInput();
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
            username = AskUserInput.getUserInput();
            // System.out.println(username);
            fl = Database.getUser(username)==null?false:true;
            if(fl){
                System.out.println("This username is not available :(");
                continue;
            }
            break;
        }
        String password;
        while(true){
            System.out.print("Enter Your Password: ");
            password = AskUserInput.getUserInput();
            fl = Validator.isValidPassword(password);
            if(!fl){
                System.out.println(Constants.REPEAT+"\n");
                System.out.println(Constants.WRONG_PASSWORD_ERROR);
                System.out.println(Constants.REPEAT);
                continue;
            }
            break;
        }
        // System.out.println(password);
        User currentUser = Database.getUser(username, password);
        if(currentUser!=null){
            return null;
        }
        int userId = Database.GET_UNIQUE_ID("userId", "users"); // will work fine
        currentUser = new User(userId, firstName, lastName, username, password, emailId, phone); // creating new user
        return currentUser;
    }
    
}