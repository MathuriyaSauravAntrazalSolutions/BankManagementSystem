package BankManagementSystem.src.Services.UserServices;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Constatnts.Errors;
import BankManagementSystem.src.Exceptions.CustomException;
import BankManagementSystem.src.Modals.User;
import BankManagementSystem.src.Operations.GetUserInput;
import BankManagementSystem.src.Repository.DatabaseRepo;
import BankManagementSystem.src.Repository.UserRepo;

public class SignUpService extends App {
     public static final User signUp() throws CustomException{
        // Taking All User Input-------
        System.out.println(Constants.REPEAT);
        String firstName = GetUserInput.getFirstName();
        String lastName = GetUserInput.getLastName();
        String emailId =  GetUserInput.getEmail();
        long phone = GetUserInput.getPhone();
        String username = GetUserInput.getUserName();
        String password = GetUserInput.getPassword();

        // check If User Exists---------
        User currentUser = UserRepo.getUser(username, password);
        if(currentUser!=null){
            throw new CustomException(Errors.USER_ALREADY_EXISTS_MESSAGE);
        }
        int userId = DatabaseRepo.GET_UNIQUE_ID("userId", "users"); // will work fine
        currentUser = new User(userId, firstName, lastName, username, password, emailId, phone); // creating new user
        boolean fl = UserRepo.insertUserInTable(currentUser); // inserting the user
        if(fl){
           throw new CustomException("User: "+currentUser.firstName+" "+currentUser.lastName+" has Registered");
        }
        else{
           throw new CustomException("Something went wrong!");
        }
    }
    
}
