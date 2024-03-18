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
public class Branch{
    public int branchCode;
    public String branchName;
    public String branchAddress;
    protected int totalBalance;

    /*
    *********************************************************
     *  @Method Name    :   Branch
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Branch Class Constructor
     *  @param          :   (int branchCode, String branchName, String branchAddress)
     *  @return         :   Branch Object            
    *********************************************************
    */
    public Branch(int branchCode, String branchName, String branchAddress){
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.totalBalance = 0;
    }

    /*
    *********************************************************
     *  @Method Name    :   getBranchInstance
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Returns New Branch Instance
     *  @param          :   (int branchCode, String branchName, String branchAddress)
     *  @return         :   Branch Object            
    *********************************************************
    */
    public static Branch getBranchInstance(int branchCode, String branchName, String branchAddress){
        return new Branch(branchCode, branchName, branchAddress);
    }
}
