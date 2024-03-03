package BankManagementSystem.src.Branches;

public class Branch{
    public int branchCode;
    public String branchName;
    public String branchAddress;
    protected int totalBalance;

    public Branch(int branchCode, String branchName, String branchAddress){
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.totalBalance = 0;
    }
}
