package BankManagementSystem.src.MIddlewares;

import java.util.Scanner;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Printer;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Operations.GetUserInput;
import BankManagementSystem.src.Routes.BankingMenuRouter;

/*
*********************************************************************************************************
 *  @Java Class Name :   BankingMenuMiddlware
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This Class used to impliment validations for user input from banking menu
 * 
 ********************************************************************************************************
*/
public class BankingMenuMiddlware extends App {
    public static Scanner sc = new Scanner(System.in);

    /*
    *********************************************************
     *  @Method Name    :   keyMiddleware
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function vaidates the key entered by the user and calls the router of corresponding Menu.
     *  @param          :   (int start, int end)
     *  @return         :   --------            
    *********************************************************
    */
    public static void keyMiddleware(int start, int end) throws CustomException{
        int key = -1;
        try{
            Printer.printKey();
            key = Integer.parseInt(GetUserInput.getUserInput());
            if(key < start || key > end){
                Printer.printInvalidKeyError();
                keyMiddleware(start, end);
            }
        }catch(Exception e){
            Printer.printInvalidKeyError();
            keyMiddleware(start, end);
        }
        BankingMenuRouter.router(key);
    }
}
