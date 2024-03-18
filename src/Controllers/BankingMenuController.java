package BankManagementSystem.src.Controllers;

import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Services.CustomerServices.CheckBalanceService;
import BankManagementSystem.src.Services.CustomerServices.DepositeService;
import BankManagementSystem.src.Services.CustomerServices.PrintPassBookService;
import BankManagementSystem.src.Services.CustomerServices.WithdrawService;


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
public class BankingMenuController extends MainMenuController {


    /*
    *********************************************************
     *  @Method Name    :   getLoan
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function used to call the loan service
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static void getLoan() throws CustomException {
        throw new CustomException("Unimplemented method 'getLoan'"); 
    }

    /*
    *********************************************************
     *  @Method Name    :   printPassbook
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function used to call the printPassbook service.
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static void printPassbook()  throws CustomException {
        PrintPassBookService.printPassbook();
    }

    /*
    *********************************************************
     *  @Method Name    :   checkBalance
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function used to call the checkBalance service.
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static void checkBalance() throws CustomException  {
        CheckBalanceService.checkBalance();
    }

    /*
    *********************************************************
     *  @Method Name    :   deposite
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function used to call the deposite service.
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static void deposite() throws CustomException  {
        DepositeService.deposite();
    }

    /*
    *********************************************************
     *  @Method Name    :   withdraw
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function used to call the withdraw service.
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static void withdraw() throws CustomException  {
        WithdrawService.withdraw();
    }
}
