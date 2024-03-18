package BankManagementSystem.src.Controllers;


import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Modals.User;
import BankManagementSystem.src.Services.UserServices.SignInService;
import BankManagementSystem.src.Services.UserServices.SignUpService;

/*
*********************************************************************************************************
 *  @Java Class Name :   MainMenuController
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This controller class contains all the controller methods which shold handle Main menu actions
 * 
 ********************************************************************************************************
*/
public class MainMenuController extends App {

    /*
    *********************************************************
     *  @Method Name    :   signIn
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function is used to call signIn service.
     *  @param          :   --------
     *  @return         :   User Object            
    *********************************************************
    */
    public static User signIn() throws Exception{
        return SignInService.signIn();
    }

    /*
    *********************************************************
     *  @Method Name    :   signUp
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function is used to call signUp service.
     *  @param          :   --------
     *  @return         :   User Object          
    *********************************************************
    */
    public static User signUp() throws Exception{
        return SignUpService.signUp();
    }
}
