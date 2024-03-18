package BankManagementSystem.src.Modals;

import java.util.ArrayList;

/*
*********************************************************************************************************
 *  @Java Class Name :   BankingMenuController
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This controller class contains all the controller methods which shold handle banking menu actions
 * 
 ********************************************************************************************************
*/
public class Customer extends User {

    /*
    *********************************************************
     *  @Method Name    :   Customer
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Customer Class Constructor
     *  @param          :   (int userId, String firstName, String lastName, String username, String password, String emailId, long phone)
     *  @return         :   Customer Object            
    *********************************************************
    */
    public Customer(int userId, String firstName, String lastName, String username, String password, String emailId,
            long phone) {
        super(userId, firstName, lastName, username, password, emailId, phone);
        //TODO Auto-generated constructor stub
    }

    public long adharNumber;
    public long panNumber;
    public int custId;
    public ArrayList<Account> accounts = new ArrayList<>();
    // static Scanner sc = new Scanner(System.in); inheriting from User

    /*
    *********************************************************
     *  @Method Name    :   Customer
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Customer Class Constructor
     *  @param          :   (User currentUser, long adharNumber, long panNumber)
     *  @return         :   --------            
    *********************************************************
    */
    public Customer(User currentUser, long adharNumber, long panNumber){
        super(currentUser.userId, currentUser.firstName, currentUser.lastName, currentUser.username, currentUser.password, currentUser.emailId, currentUser.phone);
        this.panNumber = panNumber;
        this.adharNumber = adharNumber;
        this.custId = -1;
    }

    /*
    *********************************************************
     *  @Method Name    :   getFullName
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Returns The Customer's Name By Concatenating The FirstName and LastName
     *  @param          :   --------
     *  @return         :   String             
    *********************************************************
    */
    public String getFullName(){
        System.err.println("Name: "+this.firstName+" "+this.lastName);
        return "";
    }

}
