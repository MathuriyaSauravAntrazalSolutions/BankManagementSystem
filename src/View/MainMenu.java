package BankManagementSystem.src.View;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.MIddlewares.MainMenuMiddleware;

public class MainMenu extends App {
    // will be void 
    public static final void viewMainMenu(){
        System.out.println(Constants.REPEAT+"\n");
        System.out.println(Constants.MAIN_MENU);
        System.out.println(Constants.REPEAT);
        try{
            MainMenuMiddleware.keyMiddleware(1, 3);
    
        }catch(Exception e){
            System.out.println(Constants.REPEAT);
            System.out.println(e.getMessage());
            MainMenu.viewMainMenu();
        }
        HomeMenu.viewHomeMenu();
    }
}
