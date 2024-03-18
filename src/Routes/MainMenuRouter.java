package BankManagementSystem.src.Routes;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Printer;
import BankManagementSystem.src.Controllers.MainMenuController;

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
public class MainMenuRouter extends App {
    
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
    public static void router(int key) throws Exception{
        if(key==3){
            System.out.println(Printer.EXIT_MESSAGE);
            System.exit(0);
        }
        else if(key == 1){
            // sign in user else throw Exception
            currentUser = MainMenuController.signIn();
        }
        else if(key==2){
            currentUser = MainMenuController.signUp();
        }
    }
}
