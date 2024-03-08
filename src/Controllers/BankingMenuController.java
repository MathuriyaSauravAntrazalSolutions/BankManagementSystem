package BankManagementSystem.src.Controllers;

import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Services.CustomerServices.CheckBalanceService;
import BankManagementSystem.src.Services.CustomerServices.DepositeService;
import BankManagementSystem.src.Services.CustomerServices.PrintPassBookService;
import BankManagementSystem.src.Services.CustomerServices.WithdrawService;

public class BankingMenuController extends MainMenuController {

    public static void getLoan() throws CustomException {
        // TODO Auto-generated method stub
        throw new CustomException("Unimplemented method 'getLoan'");
    }

    public static void printPassbook()  throws CustomException {
        // TODO Auto-generated method stub
        PrintPassBookService.printPassbook();
    }

    public static void checkBalance() throws CustomException  {
        // TODO Auto-generated method stub
        CheckBalanceService.checkBalance();
    }

    public static void deposite() throws CustomException  {
        // TODO Auto-generated method stub
        DepositeService.deposite();
    }

    public static void withdraw() throws CustomException  {
        // TODO Auto-generated method stub
        WithdrawService.withdraw();
    }
}
