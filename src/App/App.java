package BankManagementSystem.src.App;

import java.sql.SQLException;
import java.util.Scanner;

import BankManagementSystem.src.Constatnts.Printer;
import BankManagementSystem.src.Modals.Bank;
import BankManagementSystem.src.Modals.Customer;
import BankManagementSystem.src.Modals.User;
import BankManagementSystem.src.Repository.DatabaseConnection;
import BankManagementSystem.src.Repository.DatabaseRepo;
import BankManagementSystem.src.View.MainMenu;


/*
*********************************************************************************************************
 *  @Java Class Name :   APP
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This Is The Driver Class Which Starts The Program In Console For All The Actions And Functionalities Implimented
 * 
 ********************************************************************************************************
*/
public class App {
    public static User currentUser = null;
    public static Bank currentBank = null;
    public static Customer currentCustomer = null;
    public static Scanner sc = new Scanner(System.in);

    /*
    *********************************************************
     *  @Method Name    :   initialiseApplication
     *  @Author         :  <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static void initialiseApplication() throws SQLException{
        System.out.println(Printer.WELCOME_MESSAGE);
        // this is just for testing 
        DatabaseRepo.createDatabaseAndTables(); // making whole apps database and tables
        // Enter In Main Menu
        MainMenu.viewMainMenu();
        // Just For Testing Purpose
        DatabaseConnection.getInstance().closeConnection();
    }
}
