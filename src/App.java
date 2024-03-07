package BankManagementSystem.src;

import BankManagementSystem.src.IRepo.DatabaseConnection;
import BankManagementSystem.src.Services.MenuController;


public class App {

    public static void main(String[] args) throws Exception {
        // Starting the Connection Instance
        DatabaseConnection.getInstance().getConnection();
        // Creating AppDB For Whole App
        MenuController.createAppDB();
        // Start The App
        MenuController.MainMenu();
        // Closing The Connection
        DatabaseConnection.getInstance().closeConnection();
    }
}
