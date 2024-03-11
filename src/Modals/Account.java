package BankManagementSystem.src.Modals;

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
public class Account {
    public long accountNumber;
    public String bankName;
    public int bankId;
    public int branch_code;
    public int custId;
    public int userId;
    public long balance;

    /*
    *********************************************************
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public Account(long accountNumber, int userId, int custId, String bankName, int branch_code, long balance, int bankId){
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.balance = balance;
        this.branch_code = branch_code;
        this.custId = custId;
        this.userId = userId;
        this.bankId = bankId;
    }


    /*
    *********************************************************
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public String toString(){
        return "Account Number: "+this.accountNumber+"\n"
        + "Bank Name: "+this.bankName+"\n"
        + "Bank Id: "+this.bankId+"\n"
        + "Branch Code: "+this.branch_code;
    }
}
