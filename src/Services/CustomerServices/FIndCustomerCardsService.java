package BankManagementSystem.src.Services.CustomerServices;

import java.util.ArrayList;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Repository.CustomerRepo;

public class FIndCustomerCardsService extends App {

    public static ArrayList<Long> findIdentityCards(){
        return CustomerRepo.findCustomerIdentityCards(currentUser);
    }
}
