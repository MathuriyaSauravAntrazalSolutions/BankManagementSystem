package BankManagementSystem.src.Users;

import java.util.ArrayList;

import BankManagementSystem.src.Bank;
import BankManagementSystem.src.Constants;
import BankManagementSystem.src.Accounts.Account;
import BankManagementSystem.src.DataBases.Database;
import BankManagementSystem.src.People.Cashier;
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
        long panNumber, adharNumber;
        while(true){
            try{
                System.out.println(Constants.repeat);
                System.out.print("Enter your PanCard Number:");
                panNumber = Long.parseLong(sc.nextLine());
                if(String.valueOf(panNumber).length()!=12){
                    System.out.println("Number Length Shold Be 12"); 
                    continue;
                }
                // System.out.println(panNumber);
                break;
            }
            catch(Exception e){
                System.out.println(Constants.repeat);
                System.out.println("Please Specify A Number!");
            }
        }
        while(true){
            try{
                System.out.println(Constants.repeat);
                System.out.print("Enter your Adhar Number:");
                adharNumber = Long.parseLong(sc.nextLine());
                // System.out.println(adharNumber);
                if(String.valueOf(adharNumber).length()!=12){
                    System.out.println("Number Length Shold Be 12"); 
                    continue;
                }
                break;
            }
            catch(Exception e){
                System.out.println(Constants.repeat);
                System.out.println("Please Specify A Number!");
            }
        }
        return new Customer(currentUser, adharNumber, panNumber);
    }



    public int askCustomer(){
        System.out.println(Constants.repeat+"\n");
        System.out.println(Constants.askCustomer);
        System.out.println(Constants.repeat);
        int key;
        try{
            System.out.print("Key: ");
            key = sc.nextInt(); sc.nextLine();
            if(key<=0 || key >5){
                System.out.println(Constants.repeat);
                System.out.println("Invalid Key Please Enter Valid Key!");
                key = this.askCustomer();
            }
        }catch(Exception e){
            System.out.println(Constants.repeat);
            System.out.println("Invalid Key Please Enter Valid Key!");
            sc.nextLine();
            key = this.askCustomer();
        }
        return key;
    }



    public int askBanking(){
        System.out.println(Constants.repeat+"\n");
        System.out.println(Constants.askBanking);
        System.out.println(Constants.repeat);
        int key;
        try{
            System.out.print("Key: ");
            key = sc.nextInt(); sc.nextLine();
            if(key<=0 || key >6){
                System.out.println(Constants.repeat);
                System.out.println("Invalid Key Please Enter Valid Key!");
                key = this.askBanking();
            }
        }catch(Exception e){
            System.out.println(Constants.repeat);
            System.out.println("Invalid Key Please Enter Valid Key!");
            sc.nextLine();
            key = this.askBanking();
        }
        return key;
    }


    public boolean addAccount(){
        System.out.println(Constants.repeat);
        boolean fl = true;
        System.out.println("Choose Bank To Open An Account");
        ArrayList<String> banks = Database.getBanksList();
        int i = 1;
        for(String bankName: banks){
            System.out.println(i+". "+bankName);
            i++;
        }
        System.out.println(Constants.repeat);
        System.out.print("Bank Name: ");
        String bankName = sc.nextLine();
        System.out.println(Constants.repeat);
        int bankId = Database.checkForBankExist(bankName);
        if(bankId<1001){
            System.out.println("Bank Does Not Exists");
            return fl;
        }
        // depends on bank which customer id he has bcoz for defferent banks user would have different customer id's
        // getting if user is customer of bank than custId else -1
        this.custId = Bank.getExistingCustomerId(this, bankName, bankId);
        System.out.println("Choose Bank Branch To Open An Account");
        ArrayList<String> bankBranches = Database.getBankBranchList(bankId);
        i = 1;
        for(String branchName: bankBranches){
            System.out.println(i+". "+branchName);
            i++;
        }
        System.out.println(Constants.repeat);
        System.out.print("Bank Branch Name: ");
        String branchName = sc.nextLine();
        System.out.println(Constants.repeat);
        fl = Bank.checkForBranchExist(bankName, branchName, bankId);
        if(!fl){
            System.out.println("Bank Branch Does Not Exists");
            return fl;
        }
        System.out.println("\nChoose Account Type To Open An Account\n1. Personal Account\n2. Joint Account\n");
        System.out.println(Constants.repeat);
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
        fl = Bank.checkForActiveCustomerAcountExistOfSameType(this, bankName, branchName, type, bankId); // if account already exist
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
            System.out.print("Enter The Name: ");
            anotherCustomer = sc.nextLine();
        }
        fl = Manager.addAccount(this, bankName, branchName, type, balance, anotherCustomer, bankId);
        return fl;
    }


    public boolean deleteAccount(){
        System.out.println(Constants.repeat);
        this.accounts = Database.findAccounts(this);
        if(accounts==null || accounts.size() < 1){
            System.out.println("No Accounts Exists! Please Add One");
            return true;
        }
        System.out.println("Your Accounts are Listed Below");
        int i = 1;
        for(Account account:accounts){
            System.out.println(Constants.repeat);
            System.out.println(i+". "+account.toString());
            i++;
        }
        int key;
        while(true){
            try{
                System.out.println(Constants.repeat);
                System.out.println("Choose Key To remove account");
                System.out.print("Key: ");
                key = Integer.parseInt(sc.nextLine());
                // System.out.println(key);
                if(key<0 || key>accounts.size()){
                    System.out.println(Constants.repeat);
                    System.out.println("Invalid Key");
                    continue;
                }
                break;
            }
            catch(Exception e){
                System.out.println(Constants.repeat);
                System.out.println("Please Specify A Number!");
            }
        }
        boolean fl = Manager.removeAccount(accounts.get(key-1));
        if(fl){
            this.accounts = Database.findAccounts(this);
            System.out.println("Account Deleted :)");
        }
        return fl;
    }


    public boolean updateAccount(){
        System.out.println(Constants.repeat);
        System.out.println("You Can Change The Branch Of Your Account Only\n1. Change Branch\n2. Go Back");
        int key;
        while(true){
            System.out.print("Key: ");
            try{
                key = Integer.parseInt(sc.nextLine());
                if(key!=1 && key!=2){
                    System.out.println(Constants.repeat);
                    System.out.println("Please Specify A Valid Key");
                    System.out.println(Constants.repeat);
                    continue;
                }
                break;
            }
            catch(Exception e){
                System.out.println(Constants.repeat);
                System.out.println("Please Specify A Valid Key");
                System.out.println(Constants.repeat);
            }
        }
        if(key==2) return true;
        this.accounts = Database.findAccounts(this);
        if(accounts==null || accounts.size() < 1){
            System.out.println("No Accounts Exists! Please Add One");
            return true;
        }
        System.out.println("Your Accounts are Listed Below");
        int i = 1;
        for(Account account:accounts){
            System.out.println(Constants.repeat);
            System.out.println(i+". "+account.toString());
            i++;
        }
        key = 0;
        while(true){
            try{
                System.out.println(Constants.repeat);
                System.out.println("Choose Key To Update Account");
                System.out.print("Key: ");
                key = Integer.parseInt(sc.nextLine());
                // System.out.println(key);
                if(key<0 || key>accounts.size()){
                    System.out.println(Constants.repeat);
                    System.out.println("Invalid Key");
                    continue;
                }
                break;
            }
            catch(Exception e){
                System.out.println(Constants.repeat);
                System.out.println("Please Specify A Number!");
            }
        }
        int bankId = accounts.get(key-1).bankId;
        String bankName = accounts.get(key-1).bankName;
        int branchCode = accounts.get(key-1).branch_code;
        ArrayList<String> bankBranches = Database.getBankBranchList(bankId);
        if(bankBranches.size()==1){
            System.out.println("No Bank Branches Available To Switch");
            return false;
        }
        String brName = Database.getBranchName(bankId, branchCode); 
        System.out.println("Choose Bank Branch To Update With");
        i = 1;
        for(String branchName: bankBranches){
            if(branchName.equalsIgnoreCase(brName)) continue;
            System.out.println(i+". "+branchName);
            i++;
        }
        System.out.println(Constants.repeat);
        System.out.print("Bank Branch Name: ");
        String branchName = sc.nextLine();
        System.out.println(Constants.repeat);
        boolean fl = Bank.checkForBranchExist(bankName, branchName, bankId);
        if(!fl){
            System.out.println("Bank Branch Does Not Exists");
            return fl;
        }
        branchCode = Database.getBranchCode(bankId, branchName);
        fl = Manager.updateAccount(accounts.get(key-1), branchCode);
        this.accounts = Database.findAccounts(this);
        System.out.println("Account Updated :)");
        return fl;
    }



    public void checkBalance(){
        System.out.println(Constants.repeat);
        this.accounts = Database.findAccounts(this);
        if(accounts==null || accounts.size() < 1){
            System.out.println("No Accounts Exists! Please Add One");
            return;
        }
        System.out.println("Your Accounts are Listed Below");
        int i = 1;
        for(Account account:accounts){
            System.out.println(Constants.repeat);
            System.out.println(i+". "+account.toString());
            i++;
        }
        int key;
        while(true){
            try{
                System.out.println(Constants.repeat);
                System.out.println("Enter Key To Account To Know Account Balance");
                System.out.print("Key: ");
                key = Integer.parseInt(sc.nextLine());
                // System.out.println(key);
                if(key<0 || key>accounts.size()){
                    System.out.println(Constants.repeat);
                    System.out.println("Invalid Key");
                    continue;
                }
                break;
            }
            catch(Exception e){
                System.out.println(Constants.repeat);
                System.out.println("Please Specify A Number!");
            }
        }
        long balance = Cashier.checkBalance(accounts.get(key-1));
        System.out.println("Available Account Balance: "+ balance);
    }

    public boolean deposite(){
        System.out.println(Constants.repeat);
        this.accounts = Database.findAccounts(this);
        if(accounts==null || accounts.size() < 1){
            System.out.println("No Accounts Exists! Please Add One");
            return true;
        }
        System.out.println("Your Accounts are Listed Below");
        int i = 1;
        for(Account account:accounts){
            System.out.println(Constants.repeat);
            System.out.println(i+". "+account.toString());
            i++;
        }
        int key;
        while(true){
            try{
                System.out.println(Constants.repeat);
                System.out.println("Enter Key To Account To Deposite Ammount");
                System.out.print("Key: ");
                key = Integer.parseInt(sc.nextLine());
                // System.out.println(key);
                if(key<0 || key>accounts.size()){
                    System.out.println(Constants.repeat);
                    System.out.println("Invalid Key");
                    continue;
                }
                break;
            }
            catch(Exception e){
                System.out.println(Constants.repeat);
                System.out.println("Please Specify A Number!");
            }
        }
        long amount;
        while(true){
            try{
                System.out.println(Constants.repeat);
                System.out.println("Enter Amount To Deposit");
                System.out.print("Amount: ");
                amount = Long.parseLong(sc.nextLine());
                // System.out.println(key);
                break;
            }
            catch(Exception e){
                System.out.println("Please Specify A Number!");
            }
        }
        boolean fl = Cashier.depositAmount(accounts.get(key-1), amount);
        if(fl){
            System.out.println(amount+ " Added To The Account "+accounts.get(key-1).accountNumber+" :)");
        }
        return fl;
    }

    public boolean withdraw(){
        System.out.println(Constants.repeat);
        this.accounts = Database.findAccounts(this);
        if(accounts==null || accounts.size() < 1){
            System.out.println("No Accounts Exists! Please Add One");
            return true;
        }
        System.out.println("Your Accounts are Listed Below");
        int i = 1;
        for(Account account:accounts){
            System.out.println(Constants.repeat);
            System.out.println(i+". "+account.toString());
            i++;
        }
        int key;
        while(true){
            try{
                System.out.println(Constants.repeat);
                System.out.println("Enter Key To Account To Withdraw Ammount");
                System.out.print("Key: ");
                key = Integer.parseInt(sc.nextLine());
                // System.out.println(key);
                if(key<0 || key>accounts.size()){
                    System.out.println(Constants.repeat);
                    System.out.println("Invalid Key");
                    continue;
                }
                break;
            }
            catch(Exception e){
                System.out.println(Constants.repeat);
                System.out.println("Please Specify A Number!");
            }
        }
        long amount;
        while(true){
            try{
                System.out.println(Constants.repeat);
                System.out.println("Enter Amount To Withdraw");
                System.out.print("Amount: ");
                amount = Long.parseLong(sc.nextLine());
                // System.out.println(key);
                break;
            }
            catch(Exception e){
                System.out.println(Constants.repeat);
                System.out.println("Please Specify A Number!");
            }
        }
        boolean fl = Cashier.withdrawAmount(accounts.get(key-1), amount);
        if(fl){
            System.out.println(amount+ " Withdraw From The Account "+accounts.get(key-1).accountNumber+" :)");
        }
        else {
            System.out.println( "Bank Account Balance Is Insufficient :(");
        }
        return fl;
    }

    public void printPassbook(){
        System.out.println(Constants.repeat);
        this.accounts = Database.findAccounts(this);
        if(accounts==null || accounts.size() < 1){
            System.out.println("No Accounts Exists! Please Add One");
            return;
        }
        System.out.println("Your Accounts are Listed Below");
        int i = 1;
        for(Account account:accounts){
            System.out.println(Constants.repeat);
            System.out.println(i+". "+account.toString());
            i++;
        }
        int key;
        while(true){
            try{
                System.out.println(Constants.repeat);
                System.out.println("Enter Key To Select Account");
                System.out.print("Key: ");
                key = Integer.parseInt(sc.nextLine());
                // System.out.println(key);
                if(key<0 || key>accounts.size()){
                    System.out.println(Constants.repeat);
                    System.out.println("Invalid Key");
                    continue;
                }
                break;
            }
            catch(Exception e){
                System.out.println(Constants.repeat);
                System.out.println("Please Specify A Number!");
            }
        }
        Cashier.printPassbook(accounts.get(key-1));
    }


    public void getLoan(){}

}
