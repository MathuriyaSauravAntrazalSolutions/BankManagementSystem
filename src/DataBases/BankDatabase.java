package BankManagementSystem.src.DataBases;


public abstract class BankDatabase {
    public abstract int getId(String column, String tableName, int bankId);
    public abstract boolean createBanksTables();
}
