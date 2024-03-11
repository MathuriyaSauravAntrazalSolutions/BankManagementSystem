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
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static User signIn() throws Exception{
        return SignInService.signIn();
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
    public static User signUp() throws Exception{
        return SignUpService.signUp();
    }
}
