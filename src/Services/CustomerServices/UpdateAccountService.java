package BankManagementSystem.src.Services.CustomerServices;

import java.util.ArrayList;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Modals.Account;
import BankManagementSystem.src.Modals.Branch;
import BankManagementSystem.src.Operations.GetCustomerInput;
import BankManagementSystem.src.Repository.AccountRepo;
import BankManagementSystem.src.Repository.BranchRepo;
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
public class UpdateAccountService extends App {

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
    public static boolean updateAccount() throws CustomException{
        System.out.println(Constants.REPEAT);
        System.out.println("You Can Change The Branch Of Your Account Only\n1. Change Branch\n2. Go Back");
        int key = Helper.getKey(1, 2);
        if(key==2) return true;
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
        System.out.println("Choose Key To Update Account");
        key = Helper.getKey(1, currentCustomer.accounts.size());
        int bankId = currentCustomer.accounts.get(key-1).bankId;
        String bankName = currentCustomer.accounts.get(key-1).bankName;
        int branchCode = currentCustomer.accounts.get(key-1).branch_code;
        ArrayList<Branch> bankBranches = BranchRepo.getBankBranches(bankId);
        if(bankBranches.size()==1){
            throw new CustomException("No Bank Branches Available To Switch");
        }
        String brName = BranchRepo.getBranch(bankId, branchCode).branchName; 
        System.out.println("Choose Bank Branch To Update With As A Key");
        i = 1; int index = 0;
        for(Branch branch: bankBranches){
            if(branch.branchName.equalsIgnoreCase(brName)){
                index = i;
                continue;
            }
            System.out.println(i+". "+branch.branchName);
            i++;
        }
        System.out.println(Constants.REPEAT);
        key = Helper.getKey(0, bankBranches.size());
        System.out.println(Constants.REPEAT);
        boolean fl = BranchRepo.checkForBranchExist(bankName, bankBranches.get(key-1).branchName, bankId);
        if(!fl){
            throw new CustomException("Bank Branch Does Not Exists");
        }
        branchCode = BranchRepo.getBranch(bankId, ((key<index)?bankBranches.get(key-1).branchName:bankBranches.get(key).branchName)).branchCode;
        System.out.println("index "+index+" branch code: "+branchCode);
        int securityPin = GetCustomerInput.getPin(currentCustomer.firstName+" "+currentCustomer.lastName);
        fl = CashierService.verifyPin(currentCustomer.accounts.get(key-1), securityPin);
        if(!fl){
            throw new CustomException("Wrong Security Pin Entered!");
        }
        String type = AccountRepo.getAccountType(currentCustomer.accounts.get(key-1));
        if(type.equalsIgnoreCase("joint")){
            String anotherCustomer = AccountRepo.getAnothersName(currentCustomer.accounts.get(key-1));
            int securityPin2 = GetCustomerInput.getPin(anotherCustomer);
            fl = CashierService.verifySecondPin(currentCustomer.accounts.get(key-1), securityPin2);
            if(!fl){
                throw new CustomException("Wrong Security Pin Entered!");
            }
        }
        fl = ManagerService.updateAccount(currentCustomer.accounts.get(key-1), branchCode);
        currentCustomer.accounts = AccountRepo.findAccounts(currentCustomer);
        if(fl) throw new CustomException("Account Updated :)");
        return fl;
    }
}
