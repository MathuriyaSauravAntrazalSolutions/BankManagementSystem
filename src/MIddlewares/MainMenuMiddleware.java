package BankManagementSystem.src.MIddlewares;


import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Errors;
import BankManagementSystem.src.Operations.GetUserInput;
import BankManagementSystem.src.Routes.MainMenuRouter;

public class MainMenuMiddleware extends App {

    public static void keyMiddleware(int start, int end) throws Exception{
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

        MainMenuRouter.router(key);
    }
}
