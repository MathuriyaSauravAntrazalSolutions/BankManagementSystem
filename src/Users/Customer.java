package BankManagementSystem.src.Users;

import java.util.ArrayList;

import BankManagementSystem.src.Accounts.Account;

public class Customer extends User {

    public Customer(int userId, String firstName, String lastName, String username, String password, String emailId,
            long phone) {
        super(userId, firstName, lastName, username, password, emailId, phone);
        //TODO Auto-generated constructor stub
    }

    public long adharNumber;
    public long panNumber;
    private ArrayList<Account> accounts = new ArrayList<>();

    public Customer(User currentUser, long adharNumber, long panNumber){
        super(currentUser.userId, currentUser.firstName, currentUser.lastName, currentUser.username, currentUser.password, currentUser.emailId, currentUser.phone);
        this.panNumber = panNumber;
        this.adharNumber = adharNumber;
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
                Please Enter a Key!""");
                int key;
        try{
            key = sc.nextInt(); sc.nextLine();
            if(key<=0 || key >3){
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

}
