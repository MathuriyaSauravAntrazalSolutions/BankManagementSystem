package BankManagementSystem.src.MIddlewares;

import java.util.Scanner;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Errors;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Operations.GetUserInput;
import BankManagementSystem.src.Routes.BankingMenuRouter;

public class BankingMenuMiddlware extends App {
    public static Scanner sc = new Scanner(System.in);

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
        BankingMenuRouter.router(key);
    }
}
