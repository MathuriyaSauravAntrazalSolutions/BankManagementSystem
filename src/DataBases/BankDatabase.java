package BankManagementSystem.src.DataBases;

import java.sql.*;


public abstract class BankDatabase {
    public abstract int getId(String column, String tableName, int bankId);
    public abstract boolean createBanksTables();
}
