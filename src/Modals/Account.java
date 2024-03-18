package BankManagementSystem.src.Modals;

/*
*********************************************************************************************************
 *  @Java Class Name :   Account
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This Class Contains Account Model/POJO
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
     *  @Method Name    :   Account
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Constructor of Account class
     *  @param          :   long accountNumber, int userId, int custId, String bankName, int branch_code, long balance, int bankId
     *  @return         :   Account Object            
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
     *  @Method Name    :   toString()
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function returns the Account Object as An String
     *  @param          :   --------
     *  @return         :   String            
    *********************************************************
    */
    public String toString(){
        return "Account Number: "+this.accountNumber+"\n"
        + "Bank Name: "+this.bankName+"\n"
        + "Bank Id: "+this.bankId+"\n"
        + "Branch Code: "+this.branch_code;
    }
}
