package BankManagementSystem.src.Services.CustomerServices;

import java.util.ArrayList;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Modals.Bank;
import BankManagementSystem.src.Modals.Branch;
import BankManagementSystem.src.Operations.GetCustomerInput;
import BankManagementSystem.src.Repository.BanksRepo;
import BankManagementSystem.src.Repository.BranchRepo;
import BankManagementSystem.src.Repository.CustomerRepo;
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
public class AddAccountService extends App {
    
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
    public static boolean addAccount() throws CustomException{
        System.out.println(Constants.REPEAT);
        boolean fl = true;
        System.out.println("Choose Bank To Open An Account");
        ArrayList<Bank> banks = BanksRepo.getBanks();
        int i = 1;
        for(Bank bank: banks){
            System.out.println(i+". "+bank.name);
            i++;
        }
        System.out.println(Constants.REPEAT);
        System.out.println("Bank Name As Key");
        int key = Helper.getKey(1, banks.size());
        Bank bank = banks.get(key-1);
        // depends on bank which customer id he has bcoz for defferent banks user would have different customer id's
        // getting if user is customer of bank than custId else -1
        currentCustomer.custId = CustomerRepo.getExistingCustomerId(currentCustomer, bank.name, bank.bankId);
        System.out.println("Choose Bank Branch To Open An Account");
        ArrayList<Branch> bankBranches = BranchRepo.getBankBranches(bank.bankId);
        i = 1;
        for(Branch branch: bankBranches){
            System.out.println(i+". "+branch.branchName);
            i++;
        }
        System.out.println(Constants.REPEAT);
        System.out.println("Bank Branch Name As Key ");
        key = Helper.getKey(1, bankBranches.size());
        Branch branch = bankBranches.get(key-1);
        System.out.println(Constants.REPEAT);
        fl = BranchRepo.checkForBranchExist(bank.name, branch.branchName, bank.bankId);
        if(!fl){
            throw new CustomException("Bank Branch Does Not Exists");
        }
        System.out.println("\nChoose Account Type To Open An Account\n1. Personal Account\n2. Joint Account\n");
        System.out.println(Constants.REPEAT);
        int num = Helper.getKey(1, 2);
        String type = (num==1)?"Personal":"Joint";
        // checking if user has an account in banks branch already
        fl = CustomerRepo.checkForActiveCustomerAcountExistOfSameType(currentCustomer, bank.name, branch.branchName, type, bank.bankId); // if account already exist
        if(fl){
            throw new CustomException("Customers Account Already Exists");
        }
        System.out.print("Enter Initial Account Balance: ");
        long balance = GetCustomerInput.getAmount();
        String anotherCustomer=null; int securityKey2 = 0;
        int securityKey1 = GetCustomerInput.setPin(currentCustomer.firstName+" "+currentCustomer.lastName);
        if(type.equalsIgnoreCase("Joint")) {
            System.out.println("Give Another Authorised Customer's Name For Joint Account!");
            anotherCustomer = GetCustomerInput.getName();
            securityKey2 = GetCustomerInput.setPin(anotherCustomer);
        }
        fl = ManagerService.addAccount(currentCustomer, bank.name, branch.branchName, type, balance, anotherCustomer, bank.bankId, securityKey1, securityKey2);
        if(fl){
            throw new CustomException("Account Added :)");
        }
        return fl;
    }

}
