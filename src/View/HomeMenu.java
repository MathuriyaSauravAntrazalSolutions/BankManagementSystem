package BankManagementSystem.src.View;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.MIddlewares.HomeMenuMiddlware;

public class HomeMenu extends App {
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
