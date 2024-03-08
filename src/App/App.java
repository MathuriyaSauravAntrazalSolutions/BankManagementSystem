package BankManagementSystem.src.App;

import java.sql.SQLException;
import java.util.Scanner;

import BankManagementSystem.src.Constatnts.Messages;
import BankManagementSystem.src.Modals.Bank;
import BankManagementSystem.src.Modals.Customer;
import BankManagementSystem.src.Modals.User;
import BankManagementSystem.src.Repository.DatabaseConnection;
import BankManagementSystem.src.Repository.DatabaseRepo;
import BankManagementSystem.src.Services.BankServices.RegisterBranchesServices;
import BankManagementSystem.src.View.MainMenu;



public class App {
    public static User currentUser = null;
    public static Bank currentBank = null;
    public static Customer currentCustomer = null;
    public static Scanner sc = new Scanner(System.in);

    public static void InitialiseApplication() throws SQLException{
        System.out.println(Messages.WELCOME_MESSAGE);
        // this is just for testing 
        DatabaseRepo.createDatabaseAndTables(); // making whole apps database and tables
        // Enter In Main Menu
        MainMenu.viewMainMenu();
        // Just For Testing Purpose
        DatabaseConnection.getInstance().closeConnection();
    }
}
