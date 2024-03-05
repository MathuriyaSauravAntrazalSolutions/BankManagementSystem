package BankManagementSystem.src.Accounts;

public class Account {
    public long accountNumber;
    public String bankName;
    public int branch_code;
    public int custId;
    public int userId;
    public long balance;

    public Account(long accountNumber, int userId, int custId, String bankName, int branch_code, long balance){
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.balance = balance;
        this.branch_code = branch_code;
        this.custId = custId;
        this.userId = userId;
    }


    public String toString(){
        return "Account Number: "+this.accountNumber+"\n"
        + "Bank Name: "+this.bankName+"\n"
        + "Branch Code: "+this.branch_code+"\n"
        + "Customer Id: "+this.custId+"\n"
        + "App User Id: "+this.userId+"\n"
        + "Account Balance: "+this.balance;
    }
}
