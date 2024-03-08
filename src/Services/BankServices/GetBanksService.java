package BankManagementSystem.src.Services.BankServices;

import java.util.ArrayList;

import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Modals.Bank;
import BankManagementSystem.src.Repository.BanksRepo;

public class GetBanksService {
    public static ArrayList<Bank> getBanks() throws CustomException{
        return BanksRepo.getBanks();
    }
}
