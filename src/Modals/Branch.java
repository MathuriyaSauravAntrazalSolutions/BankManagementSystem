package BankManagementSystem.src.Modals;

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

    public static Branch getBranchInstance(int branchCode, String branchName, String branchAddress){
        return new Branch(branchCode, branchName, branchAddress);
    }
}
