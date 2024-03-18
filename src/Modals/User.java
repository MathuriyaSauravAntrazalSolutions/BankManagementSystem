package BankManagementSystem.src.Modals;

import java.util.Scanner;

/*
*********************************************************************************************************
 *  @Java Class Name :   User
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This class Contains User Model Or POJO
 * 
 ********************************************************************************************************
*/
public class User {
    static Scanner sc = new Scanner(System.in);
    public final int userId;
    public final String firstName;
    public final String lastName;
    public final String username;
    public final String password;
    public final String emailId;
    public final long phone;

    /*
    *********************************************************
     *  @Method Name    :   User
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   User Class Constructor
     *  @param          :   (int userId, String firstName, String lastName, String username, String password, String emailId, long phone)
     *  @return         :   returnns The User Object            
    *********************************************************
    */
    public User(int userId, String firstName, String lastName, String username, String password, String emailId, long phone){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;        
        this.username = username;
        this.password = password;
        this.emailId = emailId;
        this.phone = phone;
    }



    /*
    *********************************************************
     *  @Method Name    :   getUserInstance
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   returns new instance of the User class.
     *  @param          :   (int userId, String firstName, String lastName, String username, String password, String emailId, long phone)
     *  @return         :   Returns New User Instance            
    *********************************************************
    */
    public static User getUserInstance(int userId, String firstName, String lastName, String username, String password, String emailId, long phone){
        return new User(userId, firstName, lastName, username, password, emailId, phone);
    }
}
