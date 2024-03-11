package BankManagementSystem.src.Services.BankServices;


import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Modals.Branch;
import BankManagementSystem.src.Operations.GetUserInput;
import BankManagementSystem.src.Repository.BanksRepo;
import BankManagementSystem.src.Repository.BranchRepo;
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
public class RegisterBranchesServices extends App{

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
    public static void registerBranches() throws CustomException{
        int numberOfBranches = GetUserInput.getNumberOfBranches();
        System.out.println("Please Specify "+ numberOfBranches +" Bank Branches In This Formate:\nBranch Name:Address");
        int branchCode = DatabaseRepo.GET_UNIQUE_ID("branch_Code", "branches", currentBank.bankId);
        System.out.println((Constants.REPEAT));
        while(numberOfBranches>0){
            String branchString = sc.nextLine();
            if(!branchString.contains(":")){
                System.out.println("Please Specify Branch Details In Specify Formate");
                continue;
            }
            String[] arg = branchString.split(":");
            Branch branch = new Branch(branchCode, arg[0], arg[1]);
            currentBank.branches.add(branch);
            branchCode++;
            numberOfBranches--;
        }
        boolean fl = BranchRepo.insertBranchesToTable(currentBank);
        if(!fl) throw new CustomException("Something Went Wrong:) During Inserting Branches");
        fl = BanksRepo.insertBankInTable(currentUser, currentBank);
        if(fl){
            throw new CustomException("\nThanks For Registering Your Bank With Us :)\nBranches Added Successfully!");
        }
        else{
            throw new CustomException("Something Went Wrong:) During Inserting Bank");
        }
    }
}
