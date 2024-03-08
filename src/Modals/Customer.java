package BankManagementSystem.src.Modals;

import java.util.ArrayList;

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

    public String toString(){
        System.err.println("Name: "+this.firstName+" "+this.lastName);
        return "";
    }

}
