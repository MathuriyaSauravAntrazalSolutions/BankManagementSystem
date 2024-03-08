package BankManagementSystem.src.Services.CustomerServices;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Modals.Account;
import BankManagementSystem.src.Operations.GetCustomerInput;
import BankManagementSystem.src.Repository.AccountRepo;
import BankManagementSystem.src.Utils.Helper;

public class WithdrawService extends App  {

    public static void withdraw() throws CustomException{
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

        System.out.println("Choose Account As Key To Withdraw Amount");
        int key = Helper.getKey(1, currentCustomer.accounts.size());
        System.out.println("Enter Amount To Deposit");
        long amount = GetCustomerInput.getAmount();

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
        // checking if account is joint to get authorised by second account holder
        fl = CashierService.withdrawAmount(currentCustomer.accounts.get(key-1), amount);
        if(fl){
           throw new CustomException(amount+ " Withdraw From The Account "+currentCustomer.accounts.get(key-1).accountNumber+" :)");
        }
        else {
           throw new CustomException( "Bank Account Balance Is Insufficient :(");
        }
    }
}
