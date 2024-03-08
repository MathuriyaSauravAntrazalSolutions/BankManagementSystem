package BankManagementSystem.src.Routes;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Controllers.BankingMenuController;
import BankManagementSystem.src.Exceptions.CustomException;

public class BankingMenuRouter extends App {
    public static void router(int key) throws CustomException{
        if(key==9){
            System.out.println(Constants.REPEAT);
            System.out.println("Bye! Visit Us Again :)");
            System.exit(0);
        }
        else if(key==8){
            throw new CustomException("Logging Out Current User :)");
        }
        else if(key==7){
            throw new CustomException("Going Back To Home Menu :)");
        }
        else if(key==6){
            throw new CustomException("Going Back To Banking Menu :)");
        }
        else if(key == 5){
            // BankingMenuController.getLoan();
            throw new CustomException("Yet To Imppliment!");
        }
        else if(key==4){
            BankingMenuController.printPassbook();
        }
        else if(key==3){
            BankingMenuController.checkBalance();
        }
        else if(key==2){
            // we dont have customerId as such bcoz for different banks there might be different ids for currentUser
            // but userId must be same
            // retrieve accounts which will have customerIds
            BankingMenuController.deposite();
            throw new CustomException("Something Went Wrong");
        }else{
            BankingMenuController.withdraw();
            throw new CustomException("Something Went Wrong");
        }
    }
}
