package BankManagementSystem.src.View;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.MIddlewares.BankingMenuMiddlware;

public class BankingMenu extends App {

    public static void viewBankingMenu(){
        System.out.println(Constants.REPEAT+"\n");
        System.out.println(Constants.BANKING_MENU);
        System.out.println(Constants.REPEAT);
        try{
            BankingMenuMiddlware.keyMiddleware(1, 9);
        }
        catch(Exception e){
            if(e.getMessage().equalsIgnoreCase("Logging Out Current User :)")){
                System.out.println(Constants.REPEAT);
                System.out.println(e.getMessage());
                MainMenu.viewMainMenu();
            }
            else if(e.getMessage().equalsIgnoreCase("Going Back To Banking Menu :)")){
                System.out.println(Constants.REPEAT);
                System.out.println(e.getMessage());
                CustomerMenu.viewCustmerMenu();
            }
            else if(e.getMessage().equalsIgnoreCase("Going Back To Home Menu :)")){
                System.out.println(Constants.REPEAT);
                System.out.println(e.getMessage());
                HomeMenu.viewHomeMenu();
            }
            System.out.println(Constants.REPEAT);
            System.out.println(e.getMessage());
            viewBankingMenu();
        }
        viewBankingMenu();
    }
}
