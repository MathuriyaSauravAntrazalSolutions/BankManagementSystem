package BankManagementSystem.src.MIddlewares;

import java.util.ArrayList;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Printer;
import BankManagementSystem.src.Controllers.CustomerMenuControlller;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Modals.Customer;
import BankManagementSystem.src.Operations.GetCustomerInput;
import BankManagementSystem.src.Operations.GetUserInput;
import BankManagementSystem.src.Routes.CustomerMenuRouter;

/*
*********************************************************************************************************
 *  @Java Class Name :   CustomerMenuMiddlware
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This Class used to impliment validations for user input from Customer menu
 * 
 ********************************************************************************************************
*/
public class CustomerMenuMiddlware extends App {

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
        ArrayList<Long> idProof = CustomerMenuControlller.findCustomerCards();
        // check       ---------------------------------------------------------------------------
        if(idProof.size()<=0) currentCustomer = new Customer(currentUser, GetCustomerInput.getCustomerAdhar(currentUser), GetCustomerInput.getCustomerPan(currentUser));
        else{
            currentCustomer = new Customer(currentUser, idProof.get(0), idProof.get(1));
        } 
        CustomerMenuRouter.router(key);
    }
}
