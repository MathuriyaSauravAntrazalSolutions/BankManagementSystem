package BankManagementSystem.src.Services.CustomerServices;


import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Modals.Account;
import BankManagementSystem.src.Modals.Customer;
import BankManagementSystem.src.Repository.AccountRepo;

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
public class ManagerService extends App  {
    
    /*
    *********************************************************
     *  @Method Name    :   initialiseApplication
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static boolean addAccount(Customer currenCustomer, String bankName, String branchName, String type, long balance, String anotherCustomer, int bankId, int securityKey, int securityKey2){
        return AccountRepo.addAccount(currenCustomer, bankName, branchName, type, balance, anotherCustomer, bankId, securityKey, securityKey2);
    }

    /*
    *********************************************************
     *  @Method Name    :   initialiseApplication
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static boolean removeAccount(Account account){
        return AccountRepo.removeAccount(account);
    }

    /*
    *********************************************************
     *  @Method Name    :   initialiseApplication
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static boolean updateAccount(Account account, int branchCode){
        return AccountRepo.updateAccount(account, branchCode);
    }
}
