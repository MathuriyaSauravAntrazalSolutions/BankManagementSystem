package BankManagementSystem.src.MIddlewares;


import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Errors;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Operations.GetUserInput;
import BankManagementSystem.src.Routes.HomeMenuRouter;

public class HomeMenuMiddlware extends App  {

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
