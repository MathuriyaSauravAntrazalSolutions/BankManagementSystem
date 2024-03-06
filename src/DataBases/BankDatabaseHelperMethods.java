package BankManagementSystem.src.DataBases;


public abstract class BankDatabaseHelperMethods {
    public abstract int getId(String column, String tableName, int bankId);
    public abstract boolean createBanksTables();
}
