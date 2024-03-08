package BankManagementSystem.src.Controllers;

import java.util.ArrayList;

import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Services.CustomerServices.AddAccountService;
import BankManagementSystem.src.Services.CustomerServices.DeleteAccountService;
import BankManagementSystem.src.Services.CustomerServices.FIndCustomerCardsService;
import BankManagementSystem.src.Services.CustomerServices.UpdateAccountService;

public class CustomerMenuControlller extends MainMenuController{

    public static ArrayList<Long> findCustomerCards(){
        return FIndCustomerCardsService.findIdentityCards();
    }

    public static boolean updateAccount() throws CustomException{
        return UpdateAccountService.updateAccount();
    }

    public static boolean deleteAccount() throws CustomException{
        return DeleteAccountService.deleteAccount();
    }

    public static boolean addAccount() throws CustomException{
        return AddAccountService.addAccount();
    }
}
