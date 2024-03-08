package BankManagementSystem.src.View;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.MIddlewares.CustomerMenuMiddlware;

public class CustomerMenu extends App {
    // just getting key only
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
