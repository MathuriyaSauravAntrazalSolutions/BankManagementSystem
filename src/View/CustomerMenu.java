package BankManagementSystem.src.View;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.MIddlewares.CustomerMenuMiddlware;

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
public class CustomerMenu extends App {
    
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
    public static void viewCustmerMenu(){
        System.out.println(Constants.REPEAT+"\n");
        System.out.println(Constants.CUSTOMER_MENU);
        System.out.println(Constants.REPEAT);
        try{
            CustomerMenuMiddlware.keyMiddleware(1, 7);
        }
        catch(Exception e){
            if(e.getMessage().equalsIgnoreCase("Logging Out Current User :)")){
                System.out.println(Constants.REPEAT);
                System.out.println(e.getMessage());
                MainMenu.viewMainMenu();
            }
            else if(e.getMessage().equalsIgnoreCase("Going Back To Home Page :)")){
                System.out.println(Constants.REPEAT);
                System.out.println(e.getMessage());
                HomeMenu.viewHomeMenu();
            }
            System.out.println(Constants.REPEAT);
            System.out.println(e.getMessage());
            e.printStackTrace();
            viewCustmerMenu();
        }
        BankingMenu.viewBankingMenu();
    }
}
