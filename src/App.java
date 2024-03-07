package BankManagementSystem.src;

import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.crypto.Data;

import BankManagementSystem.src.DataBases.Database;
import BankManagementSystem.src.DataBases.DatabaseConnection;
import BankManagementSystem.src.Users.Customer;
import BankManagementSystem.src.Users.User;


public class App {
    public static User currentUser = null;
    public static Bank currentBank = null;
    public static Customer currentCustomer = null;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.println(Constants.repeat);
        System.out.println("Welcome To My App!");
        boolean check = Database.createDatabase(); // making whole apps database;
        // creating users table in app database;
        check = Database.createUserTable();
        check = Database.createBanksTable();
        check = Database.createUsersBanksInfoTable();
        int key = User.AskUser();
        while(true){
            if(key==3){
                System.out.println(Constants.repeat);
                System.out.println("Bye! Visit Us Again :)");
                System.exit(0);
            }
            else if(key == 1){
                currentUser = User.signIn();
                if(currentUser==null) key = User.AskUser();
                else break;
            }
            else if(key==2){
                currentUser = User.signUp();
                if(currentUser==null){
                    System.out.println(Constants.repeat);
                    System.out.println("This User Already Exists Please Sign In!");
                    key = User.AskUser();
                    continue;
                }
                currentUser = Database.InsertUserInTable(currentUser);
                if(currentUser!=null){
                    System.out.println(Constants.repeat);
                    System.out.println("User: "+currentUser.firstName+" "+currentUser.lastName+" has Registered");
                    // System.out.println(Constants.repeat);
                    break;
                }
                else{
                    System.out.println(Constants.repeat);
                    System.out.println("Something went wrong!");
                    key = User.AskUser();
                    continue;
                }
            }
        }

        // System.out.println("currentUser "+currentUser.firstName+" "+currentUser.lastName);
        key = User.AskCurrentUser();
        while(true){
            // Exit From App
            if(key==3){
                System.out.println(Constants.repeat);
                System.out.println("Bye! Visit Us Again :)");
                System.exit(0);
            }
            else if(key == 1){
                if(Database.getBanksList().size()>0) break;
                System.out.println("No Banks Are Available");
                key = User.AskCurrentUser();
            }
            // Add Bank
            else if(key==2){
                currentBank = Bank.registerBank();
                if(currentBank == null){
                    System.out.println(Constants.repeat);
                    System.out.println("This Bank Already Exists");
                    key = User.AskCurrentUser();
                    continue;
                }
                currentBank = Database.InsertBankInTable(currentUser, currentBank);
                if(currentBank!=null){
                    boolean fl = currentBank.createBanksTables();
                    currentBank.registerBranches();
                    System.out.println(Constants.repeat);
                    System.out.println("Thanks For Registering Your Bank With Us :)");
                    key = User.AskCurrentUser();
                    continue;
                }
                else{
                    System.out.println(Constants.repeat);
                    System.out.println("Something went wrong!");
                    key = User.AskCurrentUser();
                    continue;
                }
            }
        }


        // now we will impliment normal use
        // if this customer is new or already a user
        ArrayList<Long> idProof = Database.findCustomerCards(currentUser);
        // check       ---------------------------------------------------------------------------
        if(idProof.size()<=0) currentCustomer = Customer.askCustomerDetails(currentUser);
        else{
            currentCustomer = new Customer(currentUser, idProof.get(0), idProof.get(1));
        } 
        key = currentCustomer.askCustomer();
        while(true){
            if(key==5){
                System.out.println(Constants.repeat);
                System.out.println("Bye! Visit Us Again :)");
                System.exit(0);
            }
            else if(key == 3){
                boolean fl = currentCustomer.updateAccount();
                if(fl){
                    key = currentCustomer.askCustomer();
                    continue;
                }
                else {
                    System.out.println("Something Went Wrong");
                    key = currentCustomer.askCustomer();
                    continue;
                }
            }
            else if(key==4){
                currentCustomer.accounts = Database.findAccounts(currentCustomer);
                if(currentCustomer.accounts.size()>0) break;
                System.out.println(Constants.repeat);
                System.out.println("No Accounts Exists Please Add One!");
                key = currentCustomer.askCustomer();
                continue;
            }
            else if(key==2){
                // we dont have customerId as such bcoz for different banks there might be different ids for currentUser
                // but userId must be same
                // retrieve accounts which will have customerIds
                boolean fl = currentCustomer.deleteAccount();
                if(fl){
                    key = currentCustomer.askCustomer();
                    continue;
                }
                else {
                    System.out.println("Something Went Wrong");
                    key = currentCustomer.askCustomer();
                    continue;
                }
            }else{
                boolean fl = currentCustomer.addAccount();
                if(fl){
                    System.out.println("Account Added :)");
                    key = currentCustomer.askCustomer();
                    continue;
                }
                else {
                    System.out.println("Something Went Wrong");
                    key = currentCustomer.askCustomer();
                    continue;
                }
            }
        }
    
        key = currentCustomer.askBanking();
        while(true){
            if(key==6){
                System.out.println(Constants.repeat);
                System.out.println("Bye! Visit Us Again :)");
                System.exit(0);
            }
            else if(key == 5){
                currentCustomer.getLoan();
                System.out.println("Yet To Imppliment!");
                // continue;
                break;
            }
            else if(key==4){
                currentCustomer.printPassbook();
                key = currentCustomer.askBanking();
                continue;
            }
            else if(key==3){
                currentCustomer.checkBalance();
                key = currentCustomer.askBanking();
                continue;
            }
            else if(key==2){
                // we dont have customerId as such bcoz for different banks there might be different ids for currentUser
                // but userId must be same
                // retrieve accounts which will have customerIds
                boolean fl = currentCustomer.deposite();
                if(fl){
                    key = currentCustomer.askBanking();
                    continue;
                }
                else {
                    System.out.println("Something Went Wrong");
                    key = currentCustomer.askBanking();
                    continue;
                }
            }else{
                boolean fl = currentCustomer.withdraw();
                if(fl){
                    key = currentCustomer.askBanking();
                    continue;
                }
                else {
                    System.out.println("Something Went Wrong");
                    key = currentCustomer.askBanking();
                    continue;
                }
            }
        }
        DatabaseConnection.getInstance().closeConnection();
    }
}
