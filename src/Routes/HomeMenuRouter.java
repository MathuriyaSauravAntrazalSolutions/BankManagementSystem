package BankManagementSystem.src.Routes;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Controllers.HomeMenuController;
import BankManagementSystem.src.Exceptions.CustomException;

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
public class HomeMenuRouter extends App {

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
    public static void router(int key) throws CustomException{
        if(key==4){
            System.out.println(Constants.REPEAT);
            System.out.println("Bye! Visit Us Again :)");
            System.exit(0);
        }
        // LogOut From App
        else if(key==3){
            throw new CustomException("Logging Out Current User :)");
        }
        else if(key == 1){
            if(HomeMenuController.getBanks().size()>0){
                return;
            }
            throw new CustomException("No Banks Are Available");
        }
        // Add Bank
        else if(key==2){
            currentBank = HomeMenuController.registerBank();
        }
    }
}
