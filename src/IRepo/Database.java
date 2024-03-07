package BankManagementSystem.src.IRepo;



import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import BankManagementSystem.src.Modals.Account;
import BankManagementSystem.src.Modals.Branch;
import BankManagementSystem.src.Services.Bank;
import BankManagementSystem.src.Services.Customer;
import BankManagementSystem.src.Services.User;
import BankManagementSystem.src.Utils.Constants;
import BankManagementSystem.src.Utils.DatabaseQueries;
import BankManagementSystem.src.Utils.Helper;

public class Database {

    public static int GET_UNIQUE_ID(String column, String tableName){
        int uniqueNumber = 1001;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            // Step 3: Execute a query to create a database
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            // Query to find the maximum existing number in the table
            String query = "SELECT MAX("+column+") FROM "+tableName;

            // Execute the query
            ResultSet resultSet = stmt.executeQuery(query);

            // Get the maximum existing number
            if (resultSet.next()) {
                int maxNumber = resultSet.getInt(1);
                uniqueNumber = (maxNumber<uniqueNumber)?uniqueNumber:maxNumber + 1;
            }
        } catch (Exception e) {
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }

        return uniqueNumber;
    }


    public static int GET_UNIQUE_ID(String column, String tableName, int conditional){
        int uniqueNumber = 1001;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            // Step 3: Execute a query to create a database
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            // Query to find the maximum existing number in the table
            String query = "SELECT MAX("+column+") FROM "+tableName+ " where bankId = "+conditional;

            // Execute the query
            ResultSet resultSet = stmt.executeQuery(query);

            // Get the maximum existing number
            if (resultSet.next()) {
                int maxNumber = resultSet.getInt(1);
                uniqueNumber = (maxNumber<uniqueNumber)?uniqueNumber:maxNumber + 1;
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } catch (Exception e) {
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                    System.out.println(Constants.ERROR_REPEAT);
                se.printStackTrace();
                    System.out.println(Constants.ERROR_REPEAT);
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


    public static boolean createDatabaseAndTables() {
        Connection conn = null;
        Statement stmt = null;
        boolean fl = false;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            // Step 3: Execute a query to create a database
            stmt = conn.createStatement();
            stmt.executeUpdate(DatabaseQueries.CREATE_APP_DB_QUERY);
            // System.out.println("AppDatabase created successfully");
            stmt.executeUpdate(DatabaseQueries.CREATE_USERS_TABLE_QUERY);
            stmt.executeUpdate(DatabaseQueries.CREATE_BANKS_TABLE_QUERY);
            stmt.executeUpdate(DatabaseQueries.CREATE_USERS_BANKS_INFO_TABLE_QUERY);
            stmt.executeUpdate(DatabaseQueries.CREATE_CUSTOMERS_TABLE_QUERY);
            stmt.executeUpdate(DatabaseQueries.CREATE_ACCOUNTS_TABLE_QUERY);
            stmt.executeUpdate(DatabaseQueries.CREATE_BRANCHES_TABLE_QUERY);
            stmt.executeUpdate(DatabaseQueries.CREATE_TRANSACTIONS_TABLE_QUERY);
            stmt.executeUpdate(DatabaseQueries.CREATE_LOANS_TABLE_QUERY);
            stmt.executeUpdate(DatabaseQueries.CREATE_JOINT_ACCOUNTS_TABLE_QUERY);
            fl = true;
            
        } catch (Exception e) {
            // System.out.println(e.getLocalizedMessage());
            fl = false;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return fl;
    }


    public static boolean insertUserInTable(User currentUser) {
        Connection conn = null;
        Statement stmt = null;
        boolean fl = false;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            stmt.executeUpdate("Insert into users(userId, username, password, emailId, firstName, lastName, phone) values("
            +currentUser.userId+", '"+currentUser.username+"', '"+currentUser.password+"', '"+currentUser.emailId+"','"
            +currentUser.firstName+"', '"+currentUser.lastName+"', "+currentUser.phone
            +")");
            return true;
            
        } catch (Exception e) {
            // Handle errors for JDBC
            fl = false;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return fl;
    }


    public static boolean insertBankInTable(User currentUser, Bank currentBank) {
        Connection conn = null;
        Statement stmt = null;
        boolean fl = false;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            stmt.executeUpdate("Insert into Banks(bankId, name, ownerId, address) values("
            +currentBank.bankId+", '"+currentBank.name+"', "+currentUser.userId+", '"+currentBank.address
            +"')");
            return true;
            
        } catch (Exception e) {
            // Handle errors for JDBC
            fl = false;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return fl;
    }


    public static boolean insertUserInUserInfoTable(Customer currentCustomer, String bankName, long accountNumber, int branch_code){
        Connection conn = null;
        Statement stmt = null;
        boolean fl = false;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select bankId from banks where name = '"+bankName+"'");
            int bankId = -1;
            if(rs.next()) bankId = rs.getInt("bankId");
            stmt.executeUpdate("Insert into usersBanksInfo(userId, bankId, custId, accountNumber, branch_Code) values("
            +currentCustomer.userId+", "+bankId+", "+currentCustomer.custId+", "+accountNumber+", "
            + branch_code
            +")");
            return true;
            
        } catch (Exception e) {
            fl = false;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return fl;
    }


    public static User getUser(String user, String pass) {
        Connection conn = null;
        Statement stmt = null;
        User currentUser = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select * from Users where username = '"+ user +"' and "+ " password = '"+pass+"';");
            if(rs.next()){
                currentUser = new User(rs.getInt("userId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"), rs.getString("password"), rs.getString("emailId"), rs.getLong("phone"));
            }
            return currentUser;
            
        } catch (Exception e) {
            // Handle errors for JDBC
            currentUser = null;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return currentUser;
    }


    public static User getUser(String user) {
        Connection conn = null;
        Statement stmt = null;
        User currentUser = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select * from Users where username = '"+ user +"';");
            if(rs.next()){
                currentUser = User.getUserInstance(rs.getInt("userId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"), rs.getString("password"), rs.getString("emailId"), rs.getLong("phone"));
            }
            return currentUser;
            
        } catch (Exception e) {
            // Handle errors for JDBC
            currentUser = null;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return currentUser;
    }


    public static Bank getBank(int bankId){
        Bank bank = null;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            
            ResultSet rs = stmt.executeQuery("select name from banks where bankId = "+bankId);
            if(rs.next()){
                bank = Database.getBank(rs.getString("name"));
            }
            return bank;
            
        } catch (Exception e) {
            bank = null;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return bank;
    }


    public static Bank getBank(String bankName) {
        Connection conn = null;
        Statement stmt = null;
        Bank bank = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select * from Banks where name = '"+bankName+"'");
            if(rs.next()){
                return Bank.getBankInstance(rs.getString("name"), rs.getString("address"), rs.getInt("bankId"), Database.getBankBranches(rs.getInt("bankId")));
            }
            return bank;
            
        }catch (Exception e) {
            bank = null;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return bank;
    }


    public static ArrayList<Bank> getBanks(){
        ArrayList<Bank> banks = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            
            ResultSet rs = stmt.executeQuery("select name from Banks");
            while(rs.next()){
                banks.add(Database.getBank(rs.getString("name")));
            }
            
        } catch (Exception e) {
            banks = null;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return banks;
    }


    public static ArrayList<Branch> getBankBranches(int bankId){
        ArrayList<Branch> branches = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            
            ResultSet rs = stmt.executeQuery("select * from Branches where bankId = "+bankId);
            while(rs.next()){
                branches.add(Branch.getBranchInstance(rs.getInt("branch_code"), rs.getString("name"), rs.getString("address")));
            }
            
        } catch (Exception e) {
            branches = null;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return branches;
    }


    public static boolean insertBranchesToTable(Bank bank){
        boolean fl = true;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            for(Branch br: bank.branches){
                stmt.executeUpdate("Insert into branches(bankId, branch_Code, name, address, balance) values("+
                bank.bankId+", "+br.branchCode+", '"+br.branchName+"', '"+br.branchAddress
                +"', "+0+")");
            }
            fl = true;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } catch (Exception e) {
            fl = false;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                    System.out.println(Constants.ERROR_REPEAT);
                se.printStackTrace();
                    System.out.println(Constants.ERROR_REPEAT);
            }
        }
        return fl;
    }


    public static Branch getBranch(int bankId, String branchName){
        Branch branch = null;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            
            ResultSet rs = stmt.executeQuery("select * from Branches where bankId = "+bankId + " && name = '"+branchName+"'");
            while(rs.next()){
                branch = Branch.getBranchInstance(rs.getInt("branch_code"), rs.getString("name"), rs.getString("address"));
            }
            return  branch;
            
        } catch (Exception e) {
            branch = null;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return branch;
    }


    public static Branch getBranch(int bankId, int branchCode){
        Branch branch = null;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            
            ResultSet rs = stmt.executeQuery("select * from Branches where bankId = "+bankId + " && branch_code = "+branchCode);
            while(rs.next()){
                branch = Branch.getBranchInstance(rs.getInt("branch_code"), rs.getString("name"), rs.getString("address"));
            }
            return  branch;
            
        }catch (Exception e) {
            branch = null;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);} finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return branch;
    }
    

    public static ArrayList<Long> findCustomerIdentityCards(User currentUser){
        ArrayList<Long> cards = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select * from customers where appUserId = "+ currentUser.userId);
            if(rs.next()){
                cards.add(rs.getLong("adharNumber"));
                cards.add(rs.getLong("panNumber"));
            }
            return cards;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            cards = null;
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } catch (Exception e) {
            cards = null;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                    System.out.println(Constants.ERROR_REPEAT);
                se.printStackTrace();
                    System.out.println(Constants.ERROR_REPEAT);
            }
        }
        return cards;
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
                Bank bank = Database.getBank(rs.getInt("bankId"));
                // System.out.println("bank name: "+bankName);
                // System.out.println("account number: "+ accountNumber);
                int branch_code = Database.getBranchCode(bankId, accountNumber);
                long balance = Database.getBalance(bankId, accountNumber);
                accounts.add(new Account(accountNumber, currentCustomer.userId, custId, bank.name, branch_code, balance, bankId));
            }
            
        } catch (Exception e) {
            accounts = null;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
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
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
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
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } catch (Exception e) {
            anotherName = "";
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                    System.out.println(Constants.ERROR_REPEAT);
                se.printStackTrace();
                    System.out.println(Constants.ERROR_REPEAT);
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
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } catch (Exception e) {
            type = "";
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                    System.out.println(Constants.ERROR_REPEAT);
                se.printStackTrace();
                    System.out.println(Constants.ERROR_REPEAT);
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
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } catch (Exception e) {
            balance = -1;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                    System.out.println(Constants.ERROR_REPEAT);
                se.printStackTrace();
                    System.out.println(Constants.ERROR_REPEAT);
            }
        }
        return balance;
    }



    public static int getBranchCode(int bankId, long accountNumber){
        int branch_code = -1;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select branch_code from accounts where accountNumber = "+accountNumber+" && bankId = "+bankId);
            if(rs.next()){
                branch_code = rs.getInt("branch_code");
            }
            return branch_code;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            branch_code = -1;
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } catch (Exception e) {
            branch_code = -1;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                    System.out.println(Constants.ERROR_REPEAT);
                se.printStackTrace();
                    System.out.println(Constants.ERROR_REPEAT);
            }
        }
        return branch_code;
    }    
    


    public static int getExistingCustomerId(Customer currentCustomer,String bankName, int bankId){
        int custId = -1;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select custId from customers where appUserId = "+currentCustomer.userId + " && bankId = "+bankId);
            if(rs.next()){
                custId = rs.getInt("custId");
            }
            return custId;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            custId = -1;
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } catch (Exception e) {
            custId = -1;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                    System.out.println(Constants.ERROR_REPEAT);
                se.printStackTrace();
                    System.out.println(Constants.ERROR_REPEAT);
            }
        }
        return custId;
    }

    public static boolean checkForActiveCustomerAcountExistOfSameType(Customer currentCustomer,String bankName, String branchName, String type, int bankId){
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
            // System.out.println("Branch code is "+branch_code);
            rs = stmt.executeQuery("select * from accounts where type = '"+type+"' && bankId = "+bankId+ " && custId = "+currentCustomer.custId+" && isActive = 'ACTIVE'");
            if(rs.next()){
                fl = true;
            }
            return fl;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } catch (Exception e) {
            fl = false;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                    System.out.println(Constants.ERROR_REPEAT);
                se.printStackTrace();
                    System.out.println(Constants.ERROR_REPEAT);
            }
        }
        return fl;
    }


    public static boolean checkForBranchExist(String bankName, String branchName, int bankId){
        boolean fl = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select * from branches where name = '"+branchName+"' && bankId = "+bankId);
            if(rs.next()){
                fl = true;
            }
            return fl;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } catch (Exception e) {
            fl = false;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                    System.out.println(Constants.ERROR_REPEAT);
                se.printStackTrace();
                    System.out.println(Constants.ERROR_REPEAT);
            }
        }
        return fl;
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
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } catch (Exception e) {
            fl = false;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
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
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } catch (Exception e) {
            fl = false;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
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
            long balance = Database.getBalance(account.bankId, account.accountNumber);
            balance+=amount;
            stmt.executeUpdate("UPDATE accounts SET balance = "+balance+" where accountNumber = "+account.accountNumber+" && custId = "+account.custId+" && bankId = "+account.bankId);
            
            //updating joint account balance
            String type = Database.getAccountType(account);
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
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } catch (Exception e) {
            fl = false;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
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
            long balance = Database.getBalance(account.bankId, account.accountNumber);
            balance -= amount;
            if(balance < 0) return false;
            stmt.executeUpdate("UPDATE accounts SET balance = "+balance+" where accountNumber = "+account.accountNumber+" && custId = "+account.custId+" && bankId = "+account.bankId);
            
            String type = Database.getAccountType(account);
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
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } catch (Exception e) {
            fl = false;
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
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
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        }
        catch (SQLException se) {
            // Handle errors for JDBC
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } catch (Exception e) {
            System.out.println(Constants.ERROR_REPEAT);
            e.printStackTrace();
            e.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
            System.out.println(Constants.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Constants.ERROR_REPEAT);
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
                currenCustomer.custId = Database.GET_UNIQUE_ID("custId", "customers", bankId); 
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
            long accountNumber = Database.getAccountNumber(bankName, "accountNumber", "accounts", bankId);    
            
            affected_rows = stmt.executeUpdate("Insert into accounts(custId, accountNumber, branch_code, balance, type, bankId, securityPin) values("
                +currenCustomer.custId+","+accountNumber+", "+branch_code+", "+balance+", '"+type+"', "+bankId+", "+securityKey
                +")");

            if(anotherCustomer!=null){
                // lets suppose this another customer is new and unique we will not take him as main customer
                int custId_two = Database.GET_UNIQUE_ID("custId", "customers", bankId);
                affected_rows = stmt.executeUpdate("Insert into jointAccounts(custId_one, custId_two, accountNumber, balance, bankId, Cust2SecurityPin, custId_two_name) values("
                +currenCustomer.custId+", "+custId_two+","+accountNumber+", "+balance+", "+bankId+","+securityKey2+", '"+anotherCustomer
                +"')");
            }
            Database.insertUserInUserInfoTable(currenCustomer, bankName, accountNumber, branch_code);
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
            boolean tl = Database.removeAccountFromUsersBanksInfo(account);
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
