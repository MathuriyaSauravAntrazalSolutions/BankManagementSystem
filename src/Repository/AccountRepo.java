package BankManagementSystem.src.Repository;



import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Constatnts.DatabaseQueries;
import BankManagementSystem.src.Constatnts.Errors;
import BankManagementSystem.src.Modals.Account;
import BankManagementSystem.src.Modals.Bank;
import BankManagementSystem.src.Modals.Branch;
import BankManagementSystem.src.Modals.Customer;
import BankManagementSystem.src.Utils.Helper;

public class AccountRepo {
    
    public static long getAccountNumber(String bankName, String column, String tableName, int bankId){
        long uniqueNumber = 1111111111;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            // Step 3: Execute a query to create a database
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
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

    public static ArrayList<Account> findAccounts(Customer currentCustomer){
        ArrayList<Account> accounts = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            
            ResultSet rs = stmt.executeQuery("select * from usersBanksInfo where userId = "+currentCustomer.userId+ " && isActive = 'ACTIVE'");
            while(rs.next()){
                long accountNumber = rs.getLong("accountNumber");
                int custId = rs.getInt("custId");
                // this next query closing above resultset dont know why
                // ResultSet rs2 = stmt.executeQuery("select name from banks where bankId = "+rs.getInt("bankId"));
                int bankId = rs.getInt("bankId");
                Bank bank = BanksRepo.getBank(rs.getInt("bankId"));
                // System.out.println("bank name: "+bankName);
                // System.out.println("account number: "+ accountNumber);
                int branch_code = BranchRepo.getBranchCode(bankId, accountNumber);
                long balance = AccountRepo.getBalance(bankId, accountNumber);
                accounts.add(new Account(accountNumber, currentCustomer.userId, custId, bank.name, branch_code, balance, bankId));
            }
            
        } catch (Exception e) {
            accounts = null;
            System.out.println(Errors.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return accounts;
    }

    public static boolean removeAccountFromUsersBanksInfo(Account account){
        Connection conn = null;
        Statement stmt = null;
        boolean fl = false;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            stmt.executeUpdate("UPDATE usersBanksInfo SET isActive = 'InActive' WHERE accountNumber = "+account.accountNumber
            +" && custId = "+account.custId+" && bankId = "+account.bankId);
            // find if there is any other active account
            ResultSet rs = stmt.executeQuery("select * from usersBanksInfo where custId = "+account.custId+" && bankId = "+account.bankId+" && isActive = 'ACTIVE'");
            if(rs.next()) return true;
            return false;
            
        } catch (Exception e) {
            fl = false;
            System.out.println(Errors.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println(se.getLocalizedMessage());
            }
        }
        return fl;
    }

    public static String getAnothersName(Account account){
        String anotherName = "";
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select custId_two_name from jointaccounts WHERE accountNumber = "+account.accountNumber+" && bankId = "+account.bankId);
            // if accounttype is Joint
            if(rs.next()){
                anotherName = rs.getString("custId_two_name");
            }
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            anotherName = "";
            System.out.println(Errors.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } catch (Exception e) {
            anotherName = "";
            System.out.println(Errors.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                    System.out.println(Errors.ERROR_REPEAT);
                se.printStackTrace();
                    System.out.println(Errors.ERROR_REPEAT);
            }
        }
        return anotherName;
    }

    public static String getAccountType(Account account){
        String type = "";
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select type from accounts WHERE accountNumber = "+account.accountNumber+" && bankId = "+account.bankId);
            // if accounttype is Joint
            if(rs.next()){
                type = rs.getString("type");
            }
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            type = "";
            System.out.println(Errors.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } catch (Exception e) {
            type = "";
            System.out.println(Errors.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                    System.out.println(Errors.ERROR_REPEAT);
                se.printStackTrace();
                    System.out.println(Errors.ERROR_REPEAT);
            }
        }
        return type;
    }

    public static long getBalance(int bankId, long accountNumber){
        int balance = -1;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select balance from accounts where accountNumber = "+accountNumber+" && bankId = "+bankId);
            if(rs.next()){
                balance = rs.getInt("balance");
            }
            return balance;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            balance = -1;
            System.out.println(Errors.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } catch (Exception e) {
            balance = -1;
            System.out.println(Errors.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                    System.out.println(Errors.ERROR_REPEAT);
                se.printStackTrace();
                    System.out.println(Errors.ERROR_REPEAT);
            }
        }
        return balance;
    }

    public static boolean verifyPin(Account account, int securityPin){
        boolean fl = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            
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
            System.out.println(Errors.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } catch (Exception e) {
            fl = false;
            System.out.println(Errors.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
            System.out.println(Errors.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
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
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            
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
            System.out.println(Errors.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } catch (Exception e) {
            fl = false;
            System.out.println(Errors.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
            System.out.println(Errors.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
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
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            long balance = AccountRepo.getBalance(account.bankId, account.accountNumber);
            balance+=amount;
            stmt.executeUpdate("UPDATE accounts SET balance = "+balance+" where accountNumber = "+account.accountNumber+" && custId = "+account.custId+" && bankId = "+account.bankId);
            
            //updating joint account balance
            String type = AccountRepo.getAccountType(account);
            if(type.equalsIgnoreCase("joint")){
                stmt.executeUpdate("UPDATE jointAccounts SET balance = "+balance+" WHERE accountNumber = "+account.accountNumber+" && bankId = "+account.bankId);
            }

            // updating branch balance
            ResultSet rs = stmt.executeQuery("select balance from branches where branch_code = "+account.branch_code+" && bankId = "+account.bankId);
            long branchBalance = 0;
            if(rs.next()) branchBalance = rs.getLong("balance");
            branchBalance+=amount;
            stmt.executeUpdate("UPDATE branches SET balance = "+branchBalance+" Where branch_code = "+account.branch_code+" && bankId = "+account.bankId);
            
            String timeWithDate = Helper.getCurrentDateAndTime();
            // add in transactions
            stmt.executeUpdate("Insert into transactions(bankId, accountNumber, amount, type, date) values("
            +account.bankId+", "+account.accountNumber+", "+amount+", 'DEPOSIT', '"+timeWithDate+"' )");
            fl = true;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
            System.out.println(Errors.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } catch (Exception e) {
            fl = false;
            System.out.println(Errors.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
            System.out.println(Errors.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
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
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            long balance = AccountRepo.getBalance(account.bankId, account.accountNumber);
            balance -= amount;
            if(balance < 0) return false;
            stmt.executeUpdate("UPDATE accounts SET balance = "+balance+" where accountNumber = "+account.accountNumber+" && custId = "+account.custId+" && bankId = "+account.bankId);
            
            String type = AccountRepo.getAccountType(account);
            if(type.equalsIgnoreCase("joint")){
                stmt.executeUpdate("UPDATE jointAccounts SET balance = "+balance+" WHERE accountNumber = "+account.accountNumber+" && bankId = "+account.bankId);
            }

            // updating branch balance
            ResultSet rs = stmt.executeQuery("select balance from branches where branch_code = "+account.branch_code+" && bankId = "+account.bankId);
            long branchBalance = 0;
            if(rs.next()) branchBalance = rs.getLong("balance");
            branchBalance -= amount;
            stmt.executeUpdate("UPDATE branches SET balance = "+branchBalance+" Where branch_code = "+account.branch_code+" && bankId = "+account.bankId);
            
            String timeWithDate = Helper.getCurrentDateAndTime();
            // add in transactions
            stmt.executeUpdate("Insert into transactions(bankId, accountNumber, amount, type, Date) values("
            +account.bankId+", "+account.accountNumber+", "+amount+", 'WITHDRAW', '"+timeWithDate+"' )");
            fl = true;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
            System.out.println(Errors.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } catch (Exception e) {
            fl = false;
            System.out.println(Errors.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
            System.out.println(Errors.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
            }
        }
        return fl;
    }

    public static void printPassbook(String customerName, Account account) {
        String fileName = Constants.PASSBOOK_URL+account.bankName+"_"+customerName+"_"+account.accountNumber+".md"; // Name of the output file
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
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
            +"**Today Date:** "+Helper.getCurrentDateAndTime()+"\n\n\n"
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
                System.out.println(Constants.REPEAT);
                System.out.println("Your Bank Passbook Is Printed For Account Number: "+account.accountNumber);
            }
            else{
                System.out.println(Constants.REPEAT);
                System.out.println("No Transactions Available For Account Number:"+account.accountNumber);
            }
            // Close the FileWriter
            fileWriter.close();
        } 
        catch (IOException e) {
            System.out.println(Errors.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        }
        catch (SQLException se) {
            // Handle errors for JDBC
            System.out.println(Errors.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } catch (Exception e) {
            System.out.println(Errors.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
            System.out.println(Errors.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
            }
        }
    }

    public static boolean addAccount(Customer currenCustomer, String bankName, String branchName, String type, long balance, String anotherCustomer, int bankId, int securityKey, int securityKey2){
        boolean fl = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
                        
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);

            ResultSet rs = stmt.executeQuery("select branch_code from branches where name = '"+branchName+"' && bankId = "+bankId);
            
            int branch_code = -1;
            if(rs.next()) branch_code = rs.getInt("branch_code");
            if(currenCustomer.custId==-1){
                // customer is new to bank
                fl = true;
                currenCustomer.custId = DatabaseRepo.GET_UNIQUE_ID("custId", "customers", bankId); 
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
            long accountNumber = AccountRepo.getAccountNumber(bankName, "accountNumber", "accounts", bankId);    
            
            affected_rows = stmt.executeUpdate("Insert into accounts(custId, accountNumber, branch_code, balance, type, bankId, securityPin) values("
                +currenCustomer.custId+","+accountNumber+", "+branch_code+", "+balance+", '"+type+"', "+bankId+", "+securityKey
                +")");

            if(anotherCustomer!=null){
                // lets suppose this another customer is new and unique we will not take him as main customer
                int custId_two = DatabaseRepo.GET_UNIQUE_ID("custId", "customers", bankId);
                affected_rows = stmt.executeUpdate("Insert into jointAccounts(custId_one, custId_two, accountNumber, balance, bankId, Cust2SecurityPin, custId_two_name) values("
                +currenCustomer.custId+", "+custId_two+","+accountNumber+", "+balance+", "+bankId+","+securityKey2+", '"+anotherCustomer
                +"')");
            }
            UserRepo.insertUserInUserInfoTable(currenCustomer, bankName, accountNumber, branch_code);
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
     
     e.printStackTrace();       e.printStackTrace();
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
                        
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
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
            boolean tl = AccountRepo.removeAccountFromUsersBanksInfo(account);
            System.out.println(tl);
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
     
     e.printStackTrace();       e.printStackTrace();
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
                        
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
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
     
     e.printStackTrace();       e.printStackTrace();
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
