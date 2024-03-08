package BankManagementSystem.src.Services.CustomerServices;


import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Modals.Account;
import BankManagementSystem.src.Modals.Customer;
import BankManagementSystem.src.Repository.AccountRepo;


public class ManagerService extends App  {
    
    public static boolean addAccount(Customer currenCustomer, String bankName, String branchName, String type, long balance, String anotherCustomer, int bankId, int securityKey, int securityKey2){
        return AccountRepo.addAccount(currenCustomer, bankName, branchName, type, balance, anotherCustomer, bankId, securityKey, securityKey2);
    }
    public static boolean removeAccount(Account account){
        return AccountRepo.removeAccount(account);
    }

    public static boolean updateAccount(Account account, int branchCode){
        return AccountRepo.updateAccount(account, branchCode);
    }
}
