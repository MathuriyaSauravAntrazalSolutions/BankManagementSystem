package BankManagementSystem.src.Services.UserServices;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Modals.User;
import BankManagementSystem.src.Operations.GetUserInput;
import BankManagementSystem.src.Repository.UserRepo;

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
public class SignInService extends App {

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
    public static final User signIn() throws CustomException{
        System.out.println(Constants.REPEAT);
        String username = GetUserInput.getUserName();
        boolean fl = UserRepo.getUser(username)==null?false:true;
        if(!fl){
            throw new CustomException("This username is not available :(");
        }
        String password = GetUserInput.getPassword();
        currentUser = UserRepo.getUser(username, password); // cheking if user exists
        if(currentUser==null && fl == true){
            throw new CustomException("Wrong Password Sign-In Again!");   
        }
        else if(currentUser==null && fl == false){
            throw new CustomException("User dont exists! Please Signup");
        }
        return currentUser;
    }
}
