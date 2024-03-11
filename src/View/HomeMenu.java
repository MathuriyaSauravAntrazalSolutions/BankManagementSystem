package BankManagementSystem.src.View;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.MIddlewares.HomeMenuMiddlware;

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
public class HomeMenu extends App {

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
    public static final void viewHomeMenu(){
        System.out.println(Constants.REPEAT+"\n");
        System.out.println(Constants.HOME_MENU);
        System.out.println(Constants.REPEAT);
        // this middleware will be different
        try{
            HomeMenuMiddlware.keyMiddleware(1, 4);
        }
        catch(Exception e){
            if(e.getMessage().equalsIgnoreCase("Logging Out Current User :)")){
                System.out.println(Constants.REPEAT);
                System.out.println(e.getMessage());
                MainMenu.viewMainMenu();
            }
            System.out.println(Constants.REPEAT);
            System.out.println(e.getMessage());
            viewHomeMenu();
        }
        CustomerMenu.viewCustmerMenu();
    }
}
