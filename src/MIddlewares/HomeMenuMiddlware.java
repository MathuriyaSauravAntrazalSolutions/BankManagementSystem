package BankManagementSystem.src.MIddlewares;


import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Errors;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Operations.GetUserInput;
import BankManagementSystem.src.Routes.HomeMenuRouter;

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
public class HomeMenuMiddlware extends App  {

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
    public static void keyMiddleware(int start, int end) throws CustomException{
        int key = -1;
        try{
            System.out.print("Key: ");
            key = Integer.parseInt(GetUserInput.getUserInput());
            if(key < start || key > end){
                System.out.println(Errors.IN_VALID_KEY_ERROR);
                keyMiddleware(start, end);
            }
        }catch(Exception e){
            System.out.println(Errors.IN_VALID_KEY_ERROR);
            keyMiddleware(start, end);
        }
        HomeMenuRouter.router(key);
    }
}
