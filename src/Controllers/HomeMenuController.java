package BankManagementSystem.src.Controllers;

import java.util.ArrayList;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Modals.Bank;
import BankManagementSystem.src.Services.BankServices.GetBanksService;
import BankManagementSystem.src.Services.BankServices.RegisterBankService;

/*
*********************************************************************************************************
 *  @Java Class Name :   HomeMenuController
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This controller class contains all the controller methods which shold handle Home menu actions
 * 
 ********************************************************************************************************
*/
public class HomeMenuController extends App {
    
    /*
    *********************************************************
     *  @Method Name    :   getBanks
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function is used to call getBanks service.
     *  @param          :   --------
     *  @return         :   ArrayList<Bank>          
    *********************************************************
    */
    public static ArrayList<Bank> getBanks() throws CustomException{
        return GetBanksService.getBanks();
    }

    /*
    *********************************************************
     *  @Method Name    :   registerBank
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function is used to call registerBank service.
     *  @param          :   --------
     *  @return         :   Bank Object            
    *********************************************************
    */
    public static Bank registerBank() throws CustomException{
        return RegisterBankService.registerBank();
    }
}
