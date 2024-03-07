package BankManagementSystem.src.People;

import java.sql.*;

import BankManagementSystem.src.Bank;
import BankManagementSystem.src.Accounts.Account;
import BankManagementSystem.src.Branches.Branch;
import BankManagementSystem.src.DataBases.Database;
import BankManagementSystem.src.DataBases.DatabaseConnection;
import BankManagementSystem.src.DataBases.Queries;
import BankManagementSystem.src.Users.Customer;

public class Manager {


    
    public static int getUniqueId(String bankName, String column, String tableName, int bankId){
        int uniqueNumber = 1001;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            // Step 3: Execute a query to create a database
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            // Query to find the maximum existing number in the table
            String query = "SELECT MAX("+column+") FROM "+tableName + " where bankId = "+bankId;

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
        }

        return uniqueNumber;
    }


    public static long getAccountNumber(String bankName, String column, String tableName, int bankId){
        long uniqueNumber = 1111111111;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            // Step 3: Execute a query to create a database
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            // Query to find the maximum existing number in the table
            String query = "SELECT MAX("+column+") FROM "+tableName+" where bankId = "+bankId;

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
        }

        return uniqueNumber;
    }



    public static boolean addAccount(Customer currenCustomer, String bankName, String branchName, String type, long balance, String anotherCustomer, int bankId, int securityKey, int securityKey2){
        boolean fl = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
                        
            stmt.execute(Queries.USE_APP_DB_QUERY);

            ResultSet rs = stmt.executeQuery("select branch_code from branches where name = '"+branchName+"' && bankId = "+bankId);
            
            int branch_code = -1;
            if(rs.next()) branch_code = rs.getInt("branch_code");
            if(currenCustomer.custId==-1){
                // customer is new to bank
                fl = true;
                currenCustomer.custId = Manager.getUniqueId(bankName, "custId", "customers", bankId); 
            }

            int affected_rows;
            if(fl) affected_rows = stmt.executeUpdate("Insert into customers(appUserId, custId, firstName, lastName, panNumber, adharNumber, phone, gmail, bankId) values("
                +currenCustomer.userId+", "+currenCustomer.custId+", '"+currenCustomer.firstName+"', '"+currenCustomer.lastName+"', "
                +currenCustomer.panNumber+", "+currenCustomer.adharNumber+", "+currenCustomer.phone+", '"+currenCustomer.emailId
                +"', "+bankId+")");
            
            else{
                stmt.executeUpdate("UPDATE customers SET isActive = 'Active' WHERE custId = "+currenCustomer.custId+" && bankId = "+bankId);
            }
            fl = false;
            long accountNumber = Manager.getAccountNumber(bankName, "accountNumber", "accounts", bankId);    
            
            affected_rows = stmt.executeUpdate("Insert into accounts(custId, accountNumber, branch_code, balance, type, bankId, securityPin) values("
                +currenCustomer.custId+","+accountNumber+", "+branch_code+", "+balance+", '"+type+"', "+bankId+", "+securityKey
                +")");

            if(anotherCustomer!=null){
                // lets suppose this another customer is new and unique we will not take him as main customer
                int custId_two = Manager.getUniqueId(bankName, "custId", "customers", bankId);
                affected_rows = stmt.executeUpdate("Insert into jointAccounts(custId_one, custId_two, accountNumber, balance, bankId, Cust2SecurityPin, custId_two_name) values("
                +currenCustomer.custId+", "+custId_two+","+accountNumber+", "+balance+", "+bankId+","+securityKey2+", '"+anotherCustomer
                +"')");
            }
            Database.addUserInUserInfoTable(currenCustomer, bankName, accountNumber, branch_code);
            currenCustomer.accounts.add(new Account(accountNumber, currenCustomer.userId,currenCustomer.custId, bankName, branch_code, balance, bankId));
            if(affected_rows>0){
                fl = true;
            }

            // updating branch balance
            rs = stmt.executeQuery("select balance from branches where branch_code = "+branch_code+" && bankId = "+bankId);
            if(rs.next()) balance += rs.getLong("balance");
            stmt.executeUpdate("UPDATE branches SET balance = "+balance+" Where branch_code = "+branch_code+" && bankId = "+bankId);
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
        }
        return fl;
    }


    public static boolean removeAccount(Account account){
        boolean fl = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
                        
            stmt.execute(Queries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select type from accounts WHERE accountNumber = "+account.accountNumber+" && bankId = "+account.bankId);
            String type = null;
            if(rs.next()){
                type = rs.getString("type");
            }
            // remove from account
            int rowAffected = stmt.executeUpdate("UPDATE accounts SET isActive = 'InActive' WHERE accountNumber = "+account.accountNumber+" && bankId = "+account.bankId);
            // remove from join if joint account
            if(type.equalsIgnoreCase("joint")){
                rowAffected = stmt.executeUpdate("UPDATE jointAccounts SET isActive = 'InActive' WHERE accountNumber = "+account.accountNumber+" && bankId = "+account.bankId);
            }
            // returns whether there are any other accounts left in same bank
            // if not soft remove from customers also
            boolean tl = Database.removeAccountFromBankInfo(account);
            // System.out.println(fl);
            if(!tl){
                rowAffected = stmt.executeUpdate("UPDATE customers SET isActive = 'InActive' WHERE custId = "+account.custId+" && bankId = "+account.bankId);
            }
            fl = true;
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
        }
        return fl;
    }

    public static boolean updateAccount(Account account, int branchCode){
        boolean fl = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
                        
            stmt.execute(Queries.USE_APP_DB_QUERY);
            // update account
            int rowAffected = stmt.executeUpdate("UPDATE accounts SET branch_code = "+branchCode+" WHERE accountNumber = "+account.accountNumber+" && bankId = "+account.bankId);
            if(rowAffected>0) fl = true;
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
        }
        return fl;
    }
    
}
