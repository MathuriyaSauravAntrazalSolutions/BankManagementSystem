package BankManagementSystem.src.Modals;

import java.util.Scanner;

public class User {
    static Scanner sc = new Scanner(System.in);
    public final int userId;
    public final String firstName;
    public final String lastName;
    public final String username;
    public final String password;
    public final String emailId;
    public final long phone;

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
}
