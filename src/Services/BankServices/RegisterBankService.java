package BankManagementSystem.src.Services.BankServices;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Modals.Bank;
import BankManagementSystem.src.Operations.GetUserInput;
import BankManagementSystem.src.Repository.BanksRepo;
import BankManagementSystem.src.Repository.DatabaseRepo;

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
public class RegisterBankService extends App {

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
