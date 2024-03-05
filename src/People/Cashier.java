package BankManagementSystem.src.People;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.constant.Constable;
import java.sql.*;

import BankManagementSystem.src.Bank;
import BankManagementSystem.src.Constants;
import BankManagementSystem.src.Accounts.Account;
import BankManagementSystem.src.DataBases.Queries;

public class Cashier {
    public static long checkBalance(Account account){
        return Bank.getBalance(account.bankId, account.accountNumber);
    }


    public static boolean depositAmount(Account account, long amount){
        boolean fl = true;
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
            stmt.execute(Queries.USE_APP_DB_QUERY);
            long balance = Bank.getBalance(account.bankId, account.accountNumber);
            balance+=amount;
            stmt.executeUpdate("UPDATE accounts SET balance = "+balance+" where accountNumber = "+account.accountNumber+" && custId = "+account.custId+" && bankId = "+account.bankId);
            
            // updating branch balance
            ResultSet rs = stmt.executeQuery("select balance from branches where branch_code = "+account.branch_code+" && bankId = "+account.bankId);
            long branchBalance = 0;
            if(rs.next()) branchBalance = rs.getLong("balance");
            branchBalance+=amount;
            stmt.executeUpdate("UPDATE branches SET balance = "+branchBalance+" Where branch_code = "+account.branch_code+" && bankId = "+account.bankId);
            
            
            // add in transactions
            stmt.executeUpdate("Insert into transactions(bankId, accountNumber, amount, type) values("
            +account.bankId+", "+account.accountNumber+", "+amount+", 'DEPOSIT' )");
            fl = true;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
                System.out.println(Constants.errorRepeat);
                se.printStackTrace();
                System.out.println(Constants.errorRepeat);
        } catch (Exception e) {
            fl = false;
            System.out.println(Constants.errorRepeat);
            e.printStackTrace();
            System.out.println(Constants.errorRepeat);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println(Constants.errorRepeat);
                se.printStackTrace();
                System.out.println(Constants.errorRepeat);
            }
            try { 
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println(Constants.errorRepeat);
                se.printStackTrace();
                System.out.println(Constants.errorRepeat);
            }
        }
        return fl;
    }

    public static boolean withdrawAmount(Account account, long amount){
        boolean fl = true;
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
            stmt.execute(Queries.USE_APP_DB_QUERY);
            long balance = Bank.getBalance(account.bankId, account.accountNumber);
            balance -= amount;
            if(balance < 0) return false;
            stmt.executeUpdate("UPDATE accounts SET balance = "+balance+" where accountNumber = "+account.accountNumber+" && custId = "+account.custId+" && bankId = "+account.bankId);
            
            // updating branch balance
            ResultSet rs = stmt.executeQuery("select balance from branches where branch_code = "+account.branch_code+" && bankId = "+account.bankId);
            long branchBalance = 0;
            if(rs.next()) branchBalance = rs.getLong("balance");
            branchBalance -= amount;
            stmt.executeUpdate("UPDATE branches SET balance = "+branchBalance+" Where branch_code = "+account.branch_code+" && bankId = "+account.bankId);
            
            // add in transactions
            stmt.executeUpdate("Insert into transactions(bankId, accountNumber, amount, type) values("
            +account.bankId+", "+account.accountNumber+", "+amount+", 'WITHDRAW' )");
            fl = true;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
                System.out.println(Constants.errorRepeat);
                se.printStackTrace();
                System.out.println(Constants.errorRepeat);
        } catch (Exception e) {
            fl = false;
            System.out.println(Constants.errorRepeat);
            e.printStackTrace();
            System.out.println(Constants.errorRepeat);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println(Constants.errorRepeat);
                se.printStackTrace();
                System.out.println(Constants.errorRepeat);
            }
            try { 
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println(Constants.errorRepeat);
                se.printStackTrace();
                System.out.println(Constants.errorRepeat);
            }
        }
        return fl;
    }


    public static void printPassbook(Account account) {
        String fileName = "D:\\Projects\\BankManagementSystem\\src\\PassBooks\\"+account.bankName+"_"+account.branch_code+"_"+account.accountNumber+".md"; // Name of the output file
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
            stmt.execute(Queries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM Transactions where accountnumber = "+account.accountNumber+" && bankId ="+account.bankId);
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("| Bank ID | Account Number | Amount | Type Of Transaction |\n");
            fileWriter.write("|----|------|-----|-----|\n");
            boolean fl = false;
            while (rs.next()) {
                fl = true;
                int BankId = rs.getInt("bankId");
                long AccountNumber = rs.getLong("accountNumber");
                long Amount = rs.getLong("amount");
                String TransactionType = rs.getString("type");

                // Writing transactions to the file
                // fileWriter.write(BankId + "\t" + AccountNumber + "\t" + Amount + "\t" + TransactionType + "\n");
                fileWriter.write("| " + BankId + " | " + AccountNumber + " | " + Amount +" | " + TransactionType + " |\n");
            }
            if(fl){
                System.out.println(Constants.repeat);
                System.out.println("Your Bank Passbook Is Printed For Account Number: "+account.accountNumber);
            }
            else{
                System.out.println(Constants.repeat);
                System.out.println("No Transactions Available For Account Number:"+account.accountNumber);
            }
            // Close the FileWriter
            fileWriter.close();
        } 
        catch (IOException e) {
            System.out.println(Constants.errorRepeat);
            e.printStackTrace();
            System.out.println(Constants.errorRepeat);
        }
        catch (SQLException se) {
            // Handle errors for JDBC
            System.out.println(Constants.errorRepeat);
            se.printStackTrace();
            System.out.println(Constants.errorRepeat);
        } catch (Exception e) {
            System.out.println(Constants.errorRepeat);
            e.printStackTrace();
            System.out.println(Constants.errorRepeat);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println(Constants.errorRepeat);
                se.printStackTrace();
                System.out.println(Constants.errorRepeat);
            }
            try { 
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println(Constants.errorRepeat);
                se.printStackTrace();
                System.out.println(Constants.errorRepeat);
            }
        }
    }

}
