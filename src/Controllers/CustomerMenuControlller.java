package BankManagementSystem.src.Controllers;

import java.util.ArrayList;

import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Services.CustomerServices.AddAccountService;
import BankManagementSystem.src.Services.CustomerServices.DeleteAccountService;
import BankManagementSystem.src.Services.CustomerServices.FIndCustomerCardsService;
import BankManagementSystem.src.Services.CustomerServices.UpdateAccountService;

/*
*********************************************************************************************************
 *  @Java Class Name :   CustomerMenuController
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This controller class contains all the controller methods which shold handle customer menu actions
 * 
 ********************************************************************************************************
*/
public class CustomerMenuControlller extends MainMenuController{

    /*
    *********************************************************
     *  @Method Name    :   findCustomerCards
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   this function is used to call findCustomerIdentityCards service.
     *  @param          :   --------
     *  @return         :   ArrayList<Long>           
    *********************************************************
    */
    public static ArrayList<Long> findCustomerCards(){
        return FIndCustomerCardsService.findIdentityCards();
    }

    /*
    *********************************************************
     *  @Method Name    :   updateAccount
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   this function is used to call updateAccount service.
     *  @param          :   --------
     *  @return         :   boolean            
    *********************************************************
    */
    public static boolean updateAccount() throws CustomException{
        return UpdateAccountService.updateAccount();
    }

    /*
    *********************************************************
     *  @Method Name    :   deleteAccount
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   this function is used to call deleteAccount service.
     *  @param          :   --------
     *  @return         :   boolean            
    *********************************************************
    */
    public static boolean deleteAccount() throws CustomException{
        return DeleteAccountService.deleteAccount();
    }

    /*
    *********************************************************
     *  @Method Name    :   addAccount
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   this function is used to call addAccount service.
     *  @param          :   --------
     *  @return         :   boolean            
    *********************************************************
    */
    public static boolean addAccount() throws CustomException{
        return AddAccountService.addAccount();
    }
}
