package BankManagementSystem.src.Users;

import java.util.ArrayList;
import java.util.Scanner;

import BankManagementSystem.src.Bank;
import BankManagementSystem.src.Accounts.Account;
import BankManagementSystem.src.DataBases.Database;
import BankManagementSystem.src.People.Manager;

public class Customer extends User {

    public Customer(int userId, String firstName, String lastName, String username, String password, String emailId,
            long phone) {
        super(userId, firstName, lastName, username, password, emailId, phone);
        //TODO Auto-generated constructor stub
    }

    public long adharNumber;
    public long panNumber;
    public int custId;
    public ArrayList<Account> accounts = new ArrayList<>();
    // static Scanner sc = new Scanner(System.in); inheriting from User

    public Customer(User currentUser, long adharNumber, long panNumber){
        super(currentUser.userId, currentUser.firstName, currentUser.lastName, currentUser.username, currentUser.password, currentUser.emailId, currentUser.phone);
        this.panNumber = panNumber;
        this.adharNumber = adharNumber;
        this.custId = -1;
    }

    public static Customer askCustomerDetails(User currentUser){
        System.out.println(("=".repeat(15)));
        long panNumber, adharNumber;
        while(true){
            try{
                System.out.print("enter your PanCard Number:");
                panNumber = Long.parseLong(sc.nextLine());
                // System.out.println(panNumber);
                break;
            }
            catch(Exception e){
                System.out.println("Please Specify A Number!");
            }
        }
        while(true){
            try{
                System.out.print("enter your Adhar Number:");
                adharNumber = Long.parseLong(sc.nextLine());
                // System.out.println(adharNumber);
                break;
            }
            catch(Exception e){
                System.out.println("Please Specify A Number!");
            }
        }
        return new Customer(currentUser, adharNumber, panNumber);
    }



    public int askCustomer(){
        System.out.println(("=".repeat(15)));
        System.out.println("""
                Hello Please Follow These Options:
                1. Add Account
                2. Delete Account
                3. Update Account
                4. Banking
                5. Exit
                Please Enter a Key!""");
                int key;
        try{
            key = sc.nextInt(); sc.nextLine();
            if(key<=0 || key >5){
            System.out.println("Invalid Key Please Enter Valid Key!");
            key = User.AskCurrentUser();
        }
        }catch(Exception e){
            System.out.println("Invalid Key Please Enter Valid Key!");
            sc.nextLine();
            key = User.AskCurrentUser();
        }
        return key;
    }


    public boolean addAccount(){
        System.out.println(("=".repeat(15)));
        boolean fl = true;
        System.out.println("Choose Bank To Open An Account");
        String bankName = sc.nextLine();
        fl = Database.checkForBankExist(bankName);
        if(!fl){
            System.out.println("Bank Does Not Exists");
            return fl;
        }
        // depends on bank which customer id he has bcoz for defferent banks user would have different customer id's
        // getting if user is customer of bank
        this.custId = Bank.getCustomerId(this, bankName);
        System.out.println("Choose Bank Branch To Open An Account");
        String branchName = sc.nextLine();
        fl = Bank.checkForBranchExist(bankName, branchName);
        if(!fl){
            System.out.println("Bank Branch Does Not Exists");
            return fl;
        }
        System.out.println("Choose Account Type To Open An Account\n1. Personal Account\n2. Joint Account");
        int num;
        while(true){
            try{
                System.out.print("Enter Account Type As Key:");
                num = Integer.parseInt(sc.nextLine());
                // System.out.println(num);
                break;
            }
            catch(Exception e){
                System.out.println("Please Specify A Number!");
            }
        }
        String type = (num==1)?"Personal":"Joint";
        // checking if user has an account in banks branch already
        fl = Bank.checkForCustomerExist(this, bankName, branchName, type); // if account already exist
        if(fl){
            System.out.println("Customers Account Already Exists");
            return false;
        }
        long balance;
        while(true){
            try{
                System.out.print("Enter Initial Account Balance: ");
                balance = Long.parseLong(sc.nextLine());
                // System.out.println(balance);
                break;
            }
            catch(Exception e){
                System.out.println("Please Specify A Number!");
            }
        }
        String anotherCustomer=null;
        if(num==2) {
            System.out.println("Give Another Authorised Customer's Name For Joint Account!");
            anotherCustomer = sc.nextLine();
        }
        fl = Manager.addAccount(this, bankName, branchName, type, balance, anotherCustomer);
        return fl;
    }


    public boolean deleteAccount(){
        this.accounts = Database.findAccounts(this);
        if(accounts==null || accounts.size() < 1){
            System.out.println("No Accounts Exists! Please Add One");
            return false;
        }
        System.out.println("Your Accounts are Listed Below");
        int i = 1;
        for(Account account:accounts){
            System.out.println("=".repeat(15));
            System.out.println(i+". "+account.toString());
            System.out.println("=".repeat(15));
            i++;
        }
        int key;
        while(true){
            try{
                System.out.println("Choose Key To remove account");
                key = Integer.parseInt(sc.nextLine());
                // System.out.println(key);
                break;
            }
            catch(Exception e){
                System.out.println("Please Specify A Number!");
            }
        }
        boolean fl = Manager.removeAccount(accounts.get(key-1));
        if(fl){
            this.accounts = Database.findAccounts(this);
        }
        return fl;
    }

}
