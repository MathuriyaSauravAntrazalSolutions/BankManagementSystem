package BankManagementSystem.src.Routes;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Controllers.CustomerMenuControlller;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Repository.AccountRepo;

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
public class CustomerMenuRouter extends App {

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
        if(key==7){
            System.out.println(Constants.REPEAT);
            System.out.println("Bye! Visit Us Again :)");
            System.exit(0);
        }
        else if(key==6){
            throw new CustomException("Logging Out Current User :)");
        }
        else if(key==5){
            throw new CustomException("Going Back To Home Page :)");
        }
        else if(key == 3){
            boolean fl = CustomerMenuControlller.updateAccount();
            if(!fl) {
                throw new CustomException("Something Went Wrong During Account Updation");
            }
        }
        else if(key==4){
            currentCustomer.accounts = AccountRepo.findAccounts(currentCustomer);
            if(currentCustomer.accounts.size()<=0) throw new CustomException("No Accounts Exists Please Add One!");
        }
        else if(key==2){
            // we dont have customerId as such bcoz for different banks there might be different ids for currentUser
            // but userId must be same
            // retrieve accounts which will have customerIds
            CustomerMenuControlller.deleteAccount();
            throw new CustomException("Something Went Wrong During Deletion Of Account");
        }else{
            CustomerMenuControlller.addAccount();
            throw new CustomException("Something Went Wrong During Adding Account");
        }
    }

}
