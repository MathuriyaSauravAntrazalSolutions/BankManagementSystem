package BankManagementSystem.src.Services.CustomerServices;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Modals.Account;
import BankManagementSystem.src.Operations.GetCustomerInput;
import BankManagementSystem.src.Utils.Helper;

public class PrintPassBookService extends App  {

    public static void printPassbook() throws CustomException{
        System.out.println(Constants.REPEAT);
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
        System.out.println("Choose Account As Key To Print Passbook");
        int key = Helper.getKey(1, currentCustomer.accounts.size());
        int securityPin = GetCustomerInput.getPin(currentCustomer.firstName+" "+currentCustomer.lastName);
        boolean fl = CashierService.verifyPin(currentCustomer.accounts.get(key-1), securityPin);
        if(!fl){
            throw new CustomException("Wrong Security Pin Entered!");
        }
        CashierService.printPassbook(currentCustomer.firstName+" "+currentCustomer.lastName, currentCustomer.accounts.get(key-1));
    }

}
