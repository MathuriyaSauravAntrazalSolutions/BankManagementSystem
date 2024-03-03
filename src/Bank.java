package BankManagementSystem.src;

import java.util.Scanner;
import java.sql.*;
import BankManagementSystem.src.DataBases.BankDatabase;
import BankManagementSystem.src.DataBases.Database;

public class Bank extends BankDatabase{
    public final String name;
    public final String address;
    public final int bankId;
    static Scanner sc = new Scanner(System.in);


    public Bank(String name, String address, int bankId){
        this.name = name;
        this.address = address;
        this.bankId = bankId;
    }

    public static Bank registerBank(){
        System.out.print("enter your Bank Name:");
        String bankName = sc.nextLine();
        // System.out.println(bankName);
        boolean fl = Database.checkForBankExist(bankName);
        if(fl){
            return null;
        }
        System.out.print("enter your Bank Address:");
        String address = sc.nextLine();
        // System.out.println(address);
        int bankId = Database.getId("bankId", "banks");
        Bank currentBank = new Bank(bankName, address, bankId); // creating new user
        return currentBank;
    }



    public int getId(String column, String tableName){
        int uniqueNumber = 1001;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Step 2: Open a connection
            String jdbcUrl = "jdbc:mysql://localhost:3306/";
            String username = "root";
            String password = "Saurav@2942";
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            
            // Step 3: Execute a query to create a database
            stmt = conn.createStatement();
            stmt.execute("Use "+this.name);
            // Query to find the maximum existing number in the table
            String query = "SELECT MAX("+column+") FROM "+tableName;

            // Execute the query
            ResultSet resultSet = stmt.executeQuery(query);

            // Get the maximum existing number
            if (resultSet.next()) {
                int maxNumber = resultSet.getInt(1);
                uniqueNumber = (maxNumber<uniqueNumber)?uniqueNumber:maxNumber + 1;
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            System.out.println(se.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println("3. "+se.getMessage());
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println("4. "+se.getMessage());
            }
        }

        return uniqueNumber;
    }

    public boolean createBankDatabaseAndTables() {
        Connection conn = null;
        Statement stmt = null;
        boolean fl = false;
        try {
            // Step 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Step 2: Open a connection
            String jdbcUrl = "jdbc:mysql://localhost:3306/";
            String username = "root";
            String password = "Saurav@2942";
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            
            // Step 3: Execute a query to create a database
            stmt = conn.createStatement();
            String sqlCreateDB = "CREATE DATABASE "+this.name;
            stmt.executeUpdate(sqlCreateDB);
            stmt.execute("Use "+this.name);
            stmt.executeUpdate("CREATE TABLE IF NOT Exists "+this.name+"."+"customer("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "appUserId INT NOT NULL,"
            + "custId INT NOT NULL,"
            + "firstName VARCHAR(255) NOT NULL,"
            + "lastName VARCHAR(255),"
            + "panNumber BIGINT NOT NULL,"
            + "adharNumber BIGINT NOT NULL,"
            + "phone BIGINT NOT NULL,"
            + "gmail VARCHAR(255)"
            +")");
            stmt.executeUpdate("CREATE TABLE IF NOT Exists "+this.name+"."+"accounts("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "custId INT NOT NULL,"
            + "accountNumber BIGINT NOT NULL,"
            + "branch_code INT NOT NULL,"
            + "balance BIGINT NOT NULL,"
            + "type VARCHAR(255)"
            +")");
            stmt.executeUpdate("CREATE TABLE IF NOT Exists "+this.name+"."+"branches("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "branchCode INT NOT NULL,"
            + "name VARCHAR(255) NOT NULL,"
            + "address VARCHAR(255)"
            +")");
            stmt.executeUpdate("CREATE TABLE IF NOT Exists "+this.name+"."+"transactions("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "accountNumber BIGINT NOT NULL,"
            + "amount INT NOT NULL,"
            + "type VARCHAR(255)"
            +")");
            stmt.executeUpdate("CREATE TABLE IF NOT Exists "+this.name+"."+"loans("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "custId INT NOT NULL,"
            + "accountNumber BIGINT NOT NULL,"
            + "loanAmount INT NOT NULL,"
            + "remainingAmount INT NOT NULL,"
            + "creditScore INT NOT NULL"
            +")");
            stmt.executeUpdate("CREATE TABLE IF NOT Exists "+this.name+"."+"jointAccounts("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "custId_one INT NOT NULL,"
            + "custId_two INT NOT NULL,"
            + "accountNumber BIGINT NOT NULL,"
            + "balance BIGINT NOT NULL"
            +")");
            fl = true;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
            fl = false;
        } catch (Exception e) {
            e.printStackTrace();
            fl = false;
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println("3. "+se.getMessage());
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println("4. "+se.getMessage());
            }
        }
        return fl;
    }
}
