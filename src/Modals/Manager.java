package BankManagementSystem.src.Modals;


import BankManagementSystem.src.IRepo.Database;
import BankManagementSystem.src.Services.Customer;


public class Manager {
    
    public static boolean addAccount(Customer currenCustomer, String bankName, String branchName, String type, long balance, String anotherCustomer, int bankId, int securityKey, int securityKey2){
        return Database.addAccount(currenCustomer, bankName, branchName, type, balance, anotherCustomer, bankId, securityKey, securityKey2);
    }
    public static boolean removeAccount(Account account){
        return Database.removeAccount(account);
    }

    public static boolean updateAccount(Account account, int branchCode){
        return Database.updateAccount(account, branchCode);
    }
}
