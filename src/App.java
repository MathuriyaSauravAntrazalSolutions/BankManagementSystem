package BankManagementSystem.src;

import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.crypto.Data;

import BankManagementSystem.src.DataBases.Database;
import BankManagementSystem.src.Users.Customer;
import BankManagementSystem.src.Users.User;


public class App {
    static enum app_info{
        APPDATABASE,
        USERS,
        BANKS,
        USERSBANKINFO
    }
    public static User currentUser = null;
    public static Bank currentBank = null;
    public static Customer currentCustomer = null;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.println(("=".repeat(15)));
        System.out.println("Welcome To My App!");
        boolean check = Database.createDatabase(app_info.APPDATABASE.name()); // making whole apps database;
        if(check){
            System.out.println("Database For App Created Successfully!");
        }
        else{
            System.out.println("Database for app already exists!");
        }
        // creating users table in app database;
        check = Database.createUserTable(app_info.USERS.name());
        if(check){
            System.out.println("Users Table For App Created Successfully!");
        }
        else{
            System.out.println("Users Table for app already exists!");
        }
        check = Database.createBankTable(app_info.BANKS.name());
        if(check){
            System.out.println("Banks Table For App Created Successfully!");
        }
        else{
            System.out.println("Banks Table for app already exists!");
        }
        check = Database.createUserInfoTable(app_info.USERSBANKINFO.name());
        if(check){
            System.out.println("UsersBankInfo Table For App Created Successfully!");
        }
        else{
            System.out.println("UsersBankInfo Table for app already exists!");
        }
        int key = User.AskUser();
        while(true){
            if(key==3){
                System.out.println(("=".repeat(15)));
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
                    System.out.println(("=".repeat(15)));
                    System.out.println("This User Already Exists Please Sign In!");
                    key = User.AskUser();
                    continue;
                }
                currentUser = Database.InsertUserInTable(currentUser);
                if(currentUser!=null){
                    System.out.println(("=".repeat(15)));
                    System.out.println("User: "+currentUser.firstName+" "+currentUser.lastName+" has Registered");
                    break;
                }
                else{
                    System.out.println(("=".repeat(15)));
                    System.out.println("Something wnt wrong!");
                    key = User.AskUser();
                    continue;
                }
            }
        }
        // System.out.println("currentUser "+currentUser.firstName+" "+currentUser.lastName);
        key = User.AskCurrentUser();
        while(true){
            if(key==3){
                System.out.println(("=".repeat(15)));
                System.out.println("Bye! Visit Us Again :)");
                System.exit(0);
            }
            else if(key == 1){
                break;
            }
            else if(key==2){
                currentBank = Bank.registerBank();
                if(currentBank == null){
                    System.out.println(("=".repeat(15)));
                    System.out.println("This Bank Already Exists");
                    key = User.AskCurrentUser();
                    continue;
                }
                currentBank = Database.InsertBankInTable(currentUser, currentBank);
                if(currentBank!=null){
                    System.out.println(("=".repeat(15)));
                    System.out.println("Bank: "+currentBank.name+" has Registered");
                    boolean fl = currentBank.createBankDatabaseAndTables();
                    if(fl){
                        System.out.println(currentBank.name+" Bank Database Created");   
                    }
                    else {
                        System.out.println("Something Went Wrong");
                    }
                    currentBank.registerBranches();
                    System.out.println(("=".repeat(15)));
                    System.out.println("Thanks For Registering Your Bank With Us :)");
                    key = User.AskCurrentUser();
                    continue;
                }
                else{
                    System.out.println(("=".repeat(15)));
                    System.out.println("Something went wrong!");
                    key = User.AskCurrentUser();
                    continue;
                }
            }
        }


        // now we will impliment normal use
        // if this customer is new
        ArrayList<Long> idProof = Database.findCustomer(currentUser);
        System.out.println(idProof);
        // check       ---------------------------------------------------------------------------
        if(idProof==null) currentCustomer = Customer.askCustomerDetails(currentUser);
        else{
            currentCustomer = new Customer(currentUser, idProof.get(0), idProof.get(1));
        } 
        key = currentCustomer.askCustomer();
        while(true){
            if(key==5){
                System.out.println(("=".repeat(15)));
                System.out.println("Bye! Visit Us Again :)");
                System.exit(0);
            }
            else if(key == 4){
                break;
            }
            else if(key==3){
                break;
            }
            else if(key==2){
                // we dont have customerId as such bcoz for different banks there might be different ids for currentUser
                // but userId must be same
                // retrieve accounts which will have customerIds
                System.out.println(("=".repeat(15)));
                boolean fl = currentCustomer.deleteAccount();
                if(fl){
                    System.out.println("Account Deleted :)");
                    key = currentCustomer.askCustomer();
                    continue;
                }
                else {
                    System.out.println("Something Went Wrong");
                    key = currentCustomer.askCustomer();
                    continue;
                }
            }else{
                System.out.println(("=".repeat(15)));
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
    }
}