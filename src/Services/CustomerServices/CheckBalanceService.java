package BankManagementSystem.src.Services.CustomerServices;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Modals.Account;
import BankManagementSystem.src.Operations.GetCustomerInput;
import BankManagementSystem.src.Repository.AccountRepo;
import BankManagementSystem.src.Utils.Helper;

/*
*********************************************************************************************************
 *  @Java Class Name :   BankingMenuController
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This controller class contains all the controller methods which shold handle banking menu actions
 * 
 ********************************************************************************************************
*/
public class CheckBalanceService extends App {

    /*
    *********************************************************
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static void checkBalance() throws CustomException{
        System.out.println(Constants.REPEAT);
        currentCustomer.accounts = AccountRepo.findAccounts(currentCustomer);
        if(currentCustomer.accounts==null || currentCustomer.accounts.size() < 1){
            throw new CustomException("No Accounts Exists! Please Add One");
        }
        System.out.println("Your Accounts are Listed Below");
        int i = 1;
        for(Account account:currentCustomer.accounts){
            System.out.println(Constants.REPEAT);
            System.out.println(i+". "+account.toString());
            i++;
        }
        System.out.println("Choose Key To Account To Know Account Balance");
        int key = Helper.getKey(1, currentCustomer.accounts.size());
        int securityPin = GetCustomerInput.getPin(currentCustomer.firstName+" "+currentCustomer.lastName);
        int securityPin2 = 0; 
        boolean fl = CashierService.verifyPin(currentCustomer.accounts.get(key-1), securityPin);
        if(!fl){
            throw new CustomException("Wrong Security Pin Entered!");
        }
        String type = AccountRepo.getAccountType(currentCustomer.accounts.get(key-1));
        if(type.equalsIgnoreCase("joint")){
            String anotherCustomer = AccountRepo.getAnothersName(currentCustomer.accounts.get(key-1));
            securityPin2 = GetCustomerInput.getPin(anotherCustomer);
            fl = CashierService.verifySecondPin(currentCustomer.accounts.get(key-1), securityPin2);
            if(!fl){
                throw new CustomException("Wrong Security Pin Entered!");
            }
        }
        long balance = CashierService.checkBalance(currentCustomer.accounts.get(key-1));
        throw new CustomException("Available Account Balance: "+ balance);
    }
}