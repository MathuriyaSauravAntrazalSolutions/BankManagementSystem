package BankManagementSystem.src.Controllers;


import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Modals.User;
import BankManagementSystem.src.Services.UserServices.SignInService;
import BankManagementSystem.src.Services.UserServices.SignUpService;

public class MainMenuController extends App {

    public static User signIn() throws Exception{
        return SignInService.signIn();
    }

    public static User signUp() throws Exception{
        return SignUpService.signUp();
    }
}
