package BankManagementSystem.src.Services;

import java.util.ArrayList;
import java.util.Scanner;


import BankManagementSystem.src.IRepo.Database;
import BankManagementSystem.src.Operations.AskCustomerInput;
import BankManagementSystem.src.Operations.AskUserInput;
import BankManagementSystem.src.Utils.Constants;

public class MenuController {
    public static User currentUser = null;
    public static Bank currentBank = null;
    public static Customer currentCustomer = null;
    static Scanner sc = new Scanner(System.in);


    public static void createAppDB(){
        System.out.println(Constants.REPEAT);
        System.out.println("Welcome To My App!");
        Database.createDatabaseAndTables(); // making whole apps database and tables
    }

    public static void MainMenu(){
        int key = AskUserInput.askUser();
        while(true){
            if(key==3){
                System.out.println(Constants.REPEAT);
                System.out.println("Bye! Visit Us Again :)");
                System.exit(0);
            }
            else if(key == 1){
                currentUser = User.signIn();
                if(currentUser==null) key = AskUserInput.askUser();
                else MenuController.HomeMenu(); // Now USer Has Signed In
            }
            else if(key==2){
                currentUser = User.signUp();
                if(currentUser==null){
                    System.out.println(Constants.REPEAT);
                    System.out.println("This User Already Exists Please Sign In!");
                    key = AskUserInput.askUser();
                    continue;
                }
                boolean fl = Database.insertUserInTable(currentUser);
                if(fl){
                    System.out.println(Constants.REPEAT);
                    System.out.println("User: "+currentUser.firstName+" "+currentUser.lastName+" has Registered");
                    // System.out.println(Constants.REPEAT);
                    key = AskUserInput.askUser();
                    continue;
                }
                else{
                    System.out.println(Constants.REPEAT);
                    System.out.println("Something went wrong!");
                    key = AskUserInput.askUser();
                    continue;
                }
            }
        }
    }

    public static void HomeMenu(){
        int key = AskUserInput.askUserCustomer();
        while(true){
            if(key==4){
                System.out.println(Constants.REPEAT);
                System.out.println("Bye! Visit Us Again :)");
                System.exit(0);
            }
            // LogOut From App
            else if(key==3){
                System.out.println(Constants.REPEAT);
                System.out.println("Logging Out Current User :)");
                MenuController.MainMenu();
                break;
            }
            else if(key == 1){
                if(Database.getBanks().size()>0){
                    MenuController.CustomerMenu(); // User Want To Banking
                    break;
                }
                System.out.println("No Banks Are Available");
                key = AskUserInput.askUserCustomer();
            }
            // Add Bank
            else if(key==2){
                currentBank = Bank.registerBank();
                if(currentBank==null){
                    System.out.println(Constants.REPEAT);
                    System.out.println("This Bank Already Exists");
                    key = AskUserInput.askUserCustomer();
                    continue;
                }
                boolean fl = Database.insertBankInTable(currentUser, currentBank);
                if(fl){
                    System.out.println("Thanks For Registering Your Bank With Us :)");
                    key = AskUserInput.askUserCustomer();
                    continue;
                }
                else{
                    System.out.println(Constants.REPEAT);
                    System.out.println("Something went wrong!");
                    key = AskUserInput.askUserCustomer();
                    continue;
                }
            }
        }
    }

    public static void CustomerMenu(){
        ArrayList<Long> idProof = Database.findCustomerIdentityCards(currentUser);
        // check       ---------------------------------------------------------------------------
        if(idProof.size()<=0) currentCustomer = Customer.askCustomerDetails(currentUser);
        else{
            currentCustomer = new Customer(currentUser, idProof.get(0), idProof.get(1));
        } 
        int key = AskCustomerInput.askCustomer();
        while(true){
            if(key==7){
                System.out.println(Constants.REPEAT);
                System.out.println("Bye! Visit Us Again :)");
                System.exit(0);
            }
            else if(key==6){
                System.out.println(Constants.REPEAT);
                System.out.println("Logging Out Current User :)");
                MenuController.MainMenu();
                break;
            }
            else if(key==5){
                System.out.println(Constants.REPEAT);
                System.out.println("Going Back To Home Page :)");
                MenuController.HomeMenu();
                break;
            }
            else if(key == 3){
                boolean fl = currentCustomer.updateAccount();
                if(fl){
                    key = AskCustomerInput.askCustomer();
                    continue;
                }
                else {
                    System.out.println("Something Went Wrong");
                    key = AskCustomerInput.askCustomer();
                    continue;
                }
            }
            else if(key==4){
                currentCustomer.accounts = Database.findAccounts(currentCustomer);
                if(currentCustomer.accounts.size()>0){
                    MenuController.CustomerBankingMenu(); // customer wants To use his bank accounts
                    break;
                }
                System.out.println(Constants.REPEAT);
                System.out.println("No Accounts Exists Please Add One!");
                key = AskCustomerInput.askCustomer();
                continue;
            }
            else if(key==2){
                // we dont have customerId as such bcoz for different banks there might be different ids for currentUser
                // but userId must be same
                // retrieve accounts which will have customerIds
                boolean fl = currentCustomer.deleteAccount();
                if(fl){
                    key = AskCustomerInput.askCustomer();
                    continue;
                }
                else {
                    System.out.println("Something Went Wrong");
                    key = AskCustomerInput.askCustomer();
                    continue;
                }
            }else{
                boolean fl = currentCustomer.addAccount();
                if(fl){
                    System.out.println("Account Added :)");
                    key = AskCustomerInput.askCustomer();
                    continue;
                }
                else {
                    System.out.println("Something Went Wrong");
                    key = AskCustomerInput.askCustomer();
                    continue;
                }
            }
        }
    }

    
    public static void CustomerBankingMenu(){
        int key = currentCustomer.askBanking();
        while(true){
            if(key==9){
                System.out.println(Constants.REPEAT);
                System.out.println("Bye! Visit Us Again :)");
                System.exit(0);
            }
            else if(key==8){
                System.out.println(Constants.REPEAT);
                System.out.println("Logging Out Current User :)");
                MenuController.MainMenu();
            }
            else if(key==7){
                System.out.println(Constants.REPEAT);
                System.out.println("Going Back To Home Menu :)");
                MenuController.HomeMenu();
            }
            else if(key==6){
                System.out.println(Constants.REPEAT);
                System.out.println("Going Back To Banking Menu :)");
                MenuController.CustomerMenu();
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
    }
}
