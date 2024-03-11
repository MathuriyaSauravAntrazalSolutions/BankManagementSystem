package BankManagementSystem.src.View;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.MIddlewares.MainMenuMiddleware;

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
public class MainMenu extends App {
    
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
    public static final void viewMainMenu(){
        System.out.println(Constants.REPEAT+"\n");
        System.out.println(Constants.MAIN_MENU);
        System.out.println(Constants.REPEAT);
        try{
            MainMenuMiddleware.keyMiddleware(1, 3);
    
        }catch(Exception e){
            System.out.println(Constants.REPEAT);
            System.out.println(e.getMessage());
            MainMenu.viewMainMenu();
        }
        HomeMenu.viewHomeMenu();
    }
}
