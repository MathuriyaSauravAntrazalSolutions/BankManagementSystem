package BankManagementSystem.src.Services.CustomerServices;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Modals.Account;
import BankManagementSystem.src.Repository.AccountRepo;

public class CashierService extends App  {

    public static long checkBalance(Account account){
        return AccountRepo.getBalance(account.bankId, account.accountNumber);
    }

    public static boolean verifyPin(Account account, int securityPin){
        return AccountRepo.verifyPin(account, securityPin);
    }

    public static boolean verifySecondPin(Account account, int securityPin){
        return AccountRepo.verifySecondPin(account,securityPin);
    }


    public static boolean depositAmount(Account account, long amount){
        return AccountRepo.depositAmount(account, amount);
    }

    public static boolean withdrawAmount(Account account, long amount){
        return AccountRepo.withdrawAmount(account, amount);
    }

    public static void printPassbook(String customerName, Account account) {
        AccountRepo.printPassbook(customerName, account);
    }
}
