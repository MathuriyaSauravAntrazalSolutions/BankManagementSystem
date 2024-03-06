package BankManagementSystem.src.People;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.constant.Constable;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import BankManagementSystem.src.Bank;
import BankManagementSystem.src.Constants;
import BankManagementSystem.src.Accounts.Account;
import BankManagementSystem.src.DataBases.DatabaseConnection;
import BankManagementSystem.src.DataBases.Queries;

public class Cashier {


    public static long checkBalance(Account account){
        return Bank.getBalance(account.bankId, account.accountNumber);
    }


    public static String getCurrentDateAndTime() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define a custom date time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time
        String formattedDateTime = currentDateTime.format(formatter);

        // Print the formatted date and time
        return formattedDateTime;
    }



    public static boolean verifyPin(Account account, int securityPin){
        boolean fl = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            
            // updating branch balance
            ResultSet rs = stmt.executeQuery("select securityPin from accounts where accountNumber = "+account.accountNumber+" && bankId = "+account.bankId);
            int pin = 0;
            if(rs.next()){
                pin = rs.getInt("securityPin");
            }
            if(pin==securityPin) fl = true;
            
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
        }
        return fl;
    }


    public static boolean verifySecondPin(Account account, int securityPin){
        boolean fl = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            
            // updating branch balance
            ResultSet rs = stmt.executeQuery("select Cust2SecurityPin from jointaccounts where accountNumber = "+account.accountNumber+" && bankId = "+account.bankId);
            int pin = 0;
            if(rs.next()){
                pin = rs.getInt("Cust2SecurityPin");
            }
            if(pin==securityPin) fl = true;
            
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
        }
        return fl;
    }



    public static boolean depositAmount(Account account, long amount){
        boolean fl = true;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            long balance = Bank.getBalance(account.bankId, account.accountNumber);
            balance+=amount;
            stmt.executeUpdate("UPDATE accounts SET balance = "+balance+" where accountNumber = "+account.accountNumber+" && custId = "+account.custId+" && bankId = "+account.bankId);
            
            //updating joint account balance
            String type = Bank.getAccountType(account);
            if(type.equalsIgnoreCase("joint")){
                stmt.executeUpdate("UPDATE jointAccounts SET balance = "+balance+" WHERE accountNumber = "+account.accountNumber+" && bankId = "+account.bankId);
            }

            // updating branch balance
            ResultSet rs = stmt.executeQuery("select balance from branches where branch_code = "+account.branch_code+" && bankId = "+account.bankId);
            long branchBalance = 0;
            if(rs.next()) branchBalance = rs.getLong("balance");
            branchBalance+=amount;
            stmt.executeUpdate("UPDATE branches SET balance = "+branchBalance+" Where branch_code = "+account.branch_code+" && bankId = "+account.bankId);
            
            String timeWithDate = getCurrentDateAndTime();
            // add in transactions
            stmt.executeUpdate("Insert into transactions(bankId, accountNumber, amount, type, date) values("
            +account.bankId+", "+account.accountNumber+", "+amount+", 'DEPOSIT', '"+timeWithDate+"' )");
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
        }
        return fl;
    }

    public static boolean withdrawAmount(Account account, long amount){
        boolean fl = true;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            long balance = Bank.getBalance(account.bankId, account.accountNumber);
            balance -= amount;
            if(balance < 0) return false;
            stmt.executeUpdate("UPDATE accounts SET balance = "+balance+" where accountNumber = "+account.accountNumber+" && custId = "+account.custId+" && bankId = "+account.bankId);
            
            String type = Bank.getAccountType(account);
            if(type.equalsIgnoreCase("joint")){
                stmt.executeUpdate("UPDATE jointAccounts SET balance = "+balance+" WHERE accountNumber = "+account.accountNumber+" && bankId = "+account.bankId);
            }

            // updating branch balance
            ResultSet rs = stmt.executeQuery("select balance from branches where branch_code = "+account.branch_code+" && bankId = "+account.bankId);
            long branchBalance = 0;
            if(rs.next()) branchBalance = rs.getLong("balance");
            branchBalance -= amount;
            stmt.executeUpdate("UPDATE branches SET balance = "+branchBalance+" Where branch_code = "+account.branch_code+" && bankId = "+account.bankId);
            
            String timeWithDate = getCurrentDateAndTime();
            // add in transactions
            stmt.executeUpdate("Insert into transactions(bankId, accountNumber, amount, type, Date) values("
            +account.bankId+", "+account.accountNumber+", "+amount+", 'WITHDRAW', '"+timeWithDate+"' )");
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
        }
        return fl;
    }


    public static void printPassbook(String customerName, Account account) {
        String fileName = "D:\\Projects\\BankManagementSystem\\src\\PassBooks\\"+account.bankName+"_"+account.branch_code+"_"+account.accountNumber+".md"; // Name of the output file
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM Transactions where accountnumber = "+account.accountNumber+" && bankId ="+account.bankId);
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(

            "<div style=\"text-align: center;\">\r\n" + //
                                "\r\n" + //
                                "#### Transaction Details\r\n" + //
                                "\r\n" + //
                                "</div>\n\n\n"
            +"**Account Number:** "+account.accountNumber+"\n"
            +"**Account Holder Name:** "+customerName+"\n"
            +"**Account Current Balance:** "+account.balance+"\n"
            +"**Bank Name:** "+account.bankName+"\n"
            +"**Branch Code:** "+account.branch_code+"\n"
            +"**Customer ID:** "+account.custId+"\n"
            +"**App User ID:** "+account.userId+"\n"
            +"**Today Date:** "+getCurrentDateAndTime()+"\n\n\n"
            );
            fileWriter.write("| Bank ID | Account Number | Amount | Type Of Transaction | Date Of Transaction |\n");
            fileWriter.write("|----|------|-----|-----|-----|\n");
            boolean fl = false;
            while (rs.next()) {
                fl = true;
                int BankId = rs.getInt("bankId");
                long AccountNumber = rs.getLong("accountNumber");
                long Amount = rs.getLong("amount");
                String TransactionType = rs.getString("type");
                String TimeWithDate = rs.getString("date");
                // Writing transactions to the file
                // fileWriter.write(BankId + "\t" + AccountNumber + "\t" + Amount + "\t" + TransactionType + "\n");
                fileWriter.write("| " + BankId + " | " + AccountNumber + " | " + Amount +" | " + TransactionType +" | " + TimeWithDate + " |\n");
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
        }
    }

}
