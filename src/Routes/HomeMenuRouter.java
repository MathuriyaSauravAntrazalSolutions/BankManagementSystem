package BankManagementSystem.src.Routes;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Controllers.CustomerMenuControlller;
import BankManagementSystem.src.Controllers.HomeMenuController;
import BankManagementSystem.src.Controllers.MainMenuController;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Repository.BanksRepo;
import BankManagementSystem.src.Services.BankServices.RegisterBranchesServices;
import BankManagementSystem.src.Services.BankServices.GetBanksService;
import BankManagementSystem.src.Services.BankServices.RegisterBankService;
import BankManagementSystem.src.View.HomeMenu;

public class HomeMenuRouter extends App {
    public static void router(int key) throws CustomException{
        if(key==4){
            System.out.println(Constants.REPEAT);
            System.out.println("Bye! Visit Us Again :)");
            System.exit(0);
        }
        // LogOut From App
        else if(key==3){
            throw new CustomException("Logging Out Current User :)");
        }
        else if(key == 1){
            if(HomeMenuController.getBanks().size()>0){
                return;
            }
            throw new CustomException("No Banks Are Available");
        }
        // Add Bank
        else if(key==2){
            currentBank = HomeMenuController.registerBank();
        }
    }
}
