package BankManagementSystem.src.Routes;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Messages;
import BankManagementSystem.src.Controllers.MainMenuController;

public class MainMenuRouter extends App {
    
    public static void router(int key) throws Exception{
        if(key==3){
            System.out.println(Messages.EXIT_MESSAGE);
            System.exit(0);
        }
        else if(key == 1){
            // sign in user else throw Exception
            currentUser = MainMenuController.signIn();
        }
        else if(key==2){
            currentUser = MainMenuController.signUp();
        }
    }
}
