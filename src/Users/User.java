package BankManagementSystem.src.Users;

import java.util.Scanner;

import BankManagementSystem.src.DataBases.Database;

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
        System.out.println("""
                Follow These Steps To Use Me!
                1. Sign-In
                2. SignUp
                3. Exit
                Please Enter a Key!""");
        int key = sc.nextInt(); sc.nextLine();
        if(key<=0 || key >3){
            System.out.println("Invalid Key Please Enter Valid Key!");
            key = User.AskUser();
        }
        return key;
    }

    public static final User signIn(){
            System.out.print("enter your username:");
            String username = sc.nextLine();
            // System.out.println(username);
            System.out.print("enter your password:");
            String password = sc.nextLine();
            // System.out.println(password);
            User currentUser = Database.checkForUserExist(username, password); // cheking if user exists
            if(currentUser==null){
                System.out.println("user dont exists! Please Signup");
            }
            return currentUser;
    }

    public static final User signUp(){
        System.out.print("enter your FirstName:");
        String firstName = sc.nextLine();
        // System.out.println(FirstName);
        System.out.print("enter your LastName:");
        String lastName = sc.nextLine();
        // System.out.println(lastName);
        System.out.print("enter your EmailId:");
        String emailId = sc.nextLine();
        // System.out.println(emailId);
        System.out.print("enter your Phone:");
        long phone = Long.parseLong(sc.nextLine());
        // System.out.println(emailId);
        System.out.print("enter your username:");
        String username = sc.nextLine();
        // System.out.println(username);
        System.out.print("enter your password:");
        String password = sc.nextLine();
        // System.out.println(password);
        User currentUser = Database.checkForUserExist(username, password);
        if(currentUser!=null){
            return null;
        }
        int userId = Database.getId("userId", "users");
        currentUser = new User(userId, firstName, lastName, username, password, emailId, phone); // creating new user
        return currentUser;
    }


    public static final int AskCurrentUser(){
        System.out.println("""
                Welcome To This App! How Can I Help You
                1. Personal Banking
                2. Register Bank.
                3. Exit
                Please Enter a Key!""");
        int key = sc.nextInt(); sc.nextLine();
        if(key<=0 || key >3){
            System.out.println("Invalid Key Please Enter Valid Key!");
            key = User.AskUser();
        }
        return key;
    }

}