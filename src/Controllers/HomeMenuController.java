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
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static ArrayList<Bank> getBanks() throws CustomException{
        return GetBanksService.getBanks();
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
    public static Bank registerBank() throws CustomException{
        return RegisterBankService.registerBank();
    }
}
