package BankManagementSystem.src.Modals;

import BankManagementSystem.src.IRepo.Database;

public class Cashier {

    public static long checkBalance(Account account){
        return Database.getBalance(account.bankId, account.accountNumber);
    }

    public static boolean verifyPin(Account account, int securityPin){
        return Database.verifyPin(account, securityPin);
    }

    public static boolean verifySecondPin(Account account, int securityPin){
        return Database.verifySecondPin(account,securityPin);
    }


    public static boolean depositAmount(Account account, long amount){
        return Database.depositAmount(account, amount);
    }

    public static boolean withdrawAmount(Account account, long amount){
        return Database.withdrawAmount(account, amount);
    }

    public static void printPassbook(String customerName, Account account) {
        Database.printPassbook(customerName, account);
    }
}
