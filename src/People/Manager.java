package BankManagementSystem.src.People;

import java.sql.*;

import BankManagementSystem.src.Bank;
import BankManagementSystem.src.Accounts.Account;
import BankManagementSystem.src.Branches.Branch;
import BankManagementSystem.src.DataBases.Database;
import BankManagementSystem.src.Users.Customer;

public class Manager {
    public static int getUniqueId(String bankName, String column, String tableName){
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
            stmt.execute("Use "+bankName);
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
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
        } catch (Exception e) {
            System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            e.printStackTrace();
            System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            }
        }

        return uniqueNumber;
    }


    public static long getAccountNumber(String bankName, String column, String tableName){
        long uniqueNumber = 1111111111;
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
            stmt.execute("Use "+bankName);
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
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
        } catch (Exception e) {
            System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            e.printStackTrace();
            System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            }
        }

        return uniqueNumber;
    }



    public static boolean addAccount(Customer currenCustomer, String bankName, String branchName, String type, long balance, String anotherCustomer){
        boolean fl = false;
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
            
            stmt = conn.createStatement();
                        
            stmt.execute("Use "+bankName);

            ResultSet rs = stmt.executeQuery("select branch_code from branches where name = '"+branchName+"'");
            
            int branch_code = -1;
            if(rs.next()) branch_code = rs.getInt("branch_code");
            if(currenCustomer.custId==-1){
                fl = true;
                currenCustomer.custId = Manager.getUniqueId(bankName, "custId", "customers"); 
            }

            int affected_rows;
            if(fl) affected_rows = stmt.executeUpdate("Insert into customers(appUserId, custId, firstName, lastName, panNumber, adharNumber, phone, gmail) values("
                +currenCustomer.userId+", "+currenCustomer.custId+", '"+currenCustomer.firstName+"', '"+currenCustomer.lastName+"', "
                +currenCustomer.panNumber+", "+currenCustomer.adharNumber+", "+currenCustomer.phone+", '"+currenCustomer.emailId
                +"')");
            fl = false;
            long accountNumber = Manager.getAccountNumber(bankName, "accountNumber", "accounts");    
            
            affected_rows = stmt.executeUpdate("Insert into accounts(custId, accountNumber, branch_code, balance, type) values("
                +currenCustomer.custId+","+accountNumber+", "+branch_code+", "+balance+", '"+type
                +"')");

            if(anotherCustomer!=null){
                // lets suppose this another customer is new and unique we will not take him as main customer
                int custId_two = Manager.getUniqueId(bankName, "custId", "customers");
                affected_rows = stmt.executeUpdate("Insert into jointAccounts(custId_one, custId_two, accountNumber, balance) values("
                +currenCustomer.custId+", "+custId_two+","+accountNumber+", "+balance
                +")");
            }
            Database.addUserInUserInfoTable(currenCustomer, bankName, accountNumber, branch_code);
            currenCustomer.accounts.add(new Account(accountNumber, currenCustomer.userId,currenCustomer.custId, bankName, branch_code, balance));
            if(affected_rows>0){
                fl = true;
            }
            return fl;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
        } catch (Exception e) {
            fl = false;
            System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            e.printStackTrace();
            System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            }
            try { 
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            }
        }
        return fl;
    }


    public static boolean removeAccount(Account account){
        boolean fl = false;
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
            
            stmt = conn.createStatement();
                        
            stmt.execute("Use "+account.bankName);
            ResultSet rs = stmt.executeQuery("select type from accounts WHERE accountNumber = "+account.accountNumber);
            String type = null;
            if(rs.next()){
                type = rs.getString("type");
            }
            // remove from account
            int rowAffected = stmt.executeUpdate("DELETE FROM accounts WHERE accountNumber = "+account.accountNumber);
            // remove from join if joint account
            if(type.equalsIgnoreCase("joint")){
                rowAffected = stmt.executeUpdate("DELETE FROM jointAccounts WHERE accountNumber = "+account.accountNumber);
            }
            // returns whether there are any other accounts left in same bank
            // if not remove from customers also
            fl = Database.removeAccountFromBankInfo(account);
            if(!fl){
                rowAffected = stmt.executeUpdate("DELETE FROM customers WHERE custId = "+account.custId);
                if(rowAffected>0){
                    fl = true;
                }
                else fl = false;
            }
            return fl;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
        } catch (Exception e) {
            fl = false;
            System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            e.printStackTrace();
            System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            }
            try { 
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            }
        }
        return fl;
    }
    
}
