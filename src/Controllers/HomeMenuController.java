package BankManagementSystem.src.Controllers;

import java.util.ArrayList;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Modals.Bank;
import BankManagementSystem.src.Services.BankServices.GetBanksService;
import BankManagementSystem.src.Services.BankServices.RegisterBankService;

public class HomeMenuController extends App {
    
    public static ArrayList<Bank> getBanks() throws CustomException{
        return GetBanksService.getBanks();
    }

    public static Bank registerBank() throws CustomException{
        return RegisterBankService.registerBank();
    }
}
