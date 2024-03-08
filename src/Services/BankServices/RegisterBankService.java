package BankManagementSystem.src.Services.BankServices;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Modals.Bank;
import BankManagementSystem.src.Operations.GetUserInput;
import BankManagementSystem.src.Repository.BanksRepo;
import BankManagementSystem.src.Repository.DatabaseRepo;


public class RegisterBankService extends App {

     public static Bank registerBank() throws CustomException{
        System.out.println((Constants.REPEAT));
        String bankName = GetUserInput.getBankName(); 
        currentBank = BanksRepo.getBank(bankName); // null or bank
        if(currentBank != null){
            throw new CustomException("This Bank Already Exists");
        }
        String address = GetUserInput.getBankAddress();
        int bankId = DatabaseRepo.GET_UNIQUE_ID("bankId", "banks");
        currentBank = new Bank(bankName, address, bankId); // creating new Bank which has no Branches
        RegisterBranchesServices.registerBranches(); // registering the branches of this table
        return currentBank;
    }
}
