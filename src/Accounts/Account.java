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
}
