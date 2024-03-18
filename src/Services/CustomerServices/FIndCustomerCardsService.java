package BankManagementSystem.src.Services.CustomerServices;

import java.util.ArrayList;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Repository.CustomerRepo;

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
public class FIndCustomerCardsService extends App {

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
    public static ArrayList<Long> findIdentityCards(){
        return CustomerRepo.findCustomerIdentityCards(currentUser);
    }
}
