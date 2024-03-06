package BankManagementSystem.src.DataBases;



import java.sql.*;
import java.util.ArrayList;

import BankManagementSystem.src.Bank;
import BankManagementSystem.src.Constants;
import BankManagementSystem.src.Accounts.Account;
import BankManagementSystem.src.Users.Customer;
import BankManagementSystem.src.Users.User;

public class Database {

    public static int getId(String column, String tableName){
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
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            // System.out.println(e.getLocalizedMessage());
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


    public static boolean createDatabase() {
        Connection conn = null;
        Statement stmt = null;
        boolean fl = false;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            // Step 3: Execute a query to create a database
            stmt = conn.createStatement();
            stmt.executeUpdate(Queries.CREATE_APP_DB_QUERY);
            // System.out.println("AppDatabase created successfully");
            fl = true;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            // System.out.println(se.getLocalizedMessage());
            fl = false;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            fl = false;
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


    public static boolean createUserTable() {
        Connection conn = null;
        Statement stmt = null;
        boolean fl = false;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            // Step 3: Execute a query to create a usertable
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            stmt.executeUpdate(Queries.CREATE_USERS_TABLE_QUERY);
            fl = true;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            fl = false;
            System.out.println(e.getLocalizedMessage());
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


    public static boolean createBanksTable() {
        Connection conn = null;
        Statement stmt = null;
        boolean fl = false;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            // Step 3: Execute a query to create a usertable
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            stmt.executeUpdate(Queries.CREATE_BANKS_TABLE_QUERY);
            fl = true;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            fl = false;
            System.out.println(e.getLocalizedMessage());
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


    public static boolean createUsersBanksInfoTable() {
        Connection conn = null;
        Statement stmt = null;
        boolean fl = false;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            // Step 3: Execute a query to create a usertable
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            stmt.executeUpdate(Queries.CREATE_USERS_BANKS_INFO_TABLE_QUERY);
            fl = true;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            fl = false;
            System.out.println(e.getLocalizedMessage());
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


    public static User InsertUserInTable(User currentUser) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            stmt.executeUpdate("Insert into users(userId, username, password, emailId, firstName, lastName, phone) values("
            +currentUser.userId+", '"+currentUser.username+"', '"+currentUser.password+"', '"+currentUser.emailId+"','"
            +currentUser.firstName+"', '"+currentUser.lastName+"', "+currentUser.phone
            +")");
            return currentUser;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            currentUser = null;
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            currentUser = null;
            System.out.println(e.getLocalizedMessage());
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


    public static Bank InsertBankInTable(User currentUser, Bank currentBank) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            stmt.executeUpdate("Insert into Banks(bankId, name, ownerId, address) values("
            +currentBank.bankId+", '"+currentBank.name+"', "+currentUser.userId+", '"+currentBank.address
            +"')");
            return currentBank;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            currentBank = null;
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            currentBank = null;
            System.out.println(e.getLocalizedMessage());
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return currentBank;
    }


    public static boolean addUserInUserInfoTable(Customer currenCustomer, String bankName, long accountNumber, int branch_code){
        Connection conn = null;
        Statement stmt = null;
        boolean fl = false;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select bankId from banks where name = '"+bankName+"'");
            int bankId = -1;
            if(rs.next()) bankId = rs.getInt("bankId");
            int affected_rows = stmt.executeUpdate("Insert into usersBanksInfo(userId, bankId, custId, accountNumber, branch_Code) values("
            +currenCustomer.userId+", "+bankId+", "+currenCustomer.custId+", "+accountNumber+", "
            + branch_code
            +")");
            if(affected_rows>0) fl = true;
            return fl;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            fl = false;
            System.out.println(e.getLocalizedMessage());
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


    public static User checkForUserExist(String user, String pass) {
        Connection conn = null;
        Statement stmt = null;
        User currentUser = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select * from Users where username = '"+ user +"' and "+ " password = '"+pass+"';");
            if(rs.next()){
                currentUser = new User(rs.getInt("userId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"), rs.getString("password"), rs.getString("emailId"), rs.getLong("phone"));
            }
            return currentUser;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            currentUser = null;
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            currentUser = null;
            System.out.println(e.getLocalizedMessage());
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


    public static boolean checkForUserExist(String user) {
        Connection conn = null;
        Statement stmt = null;
        boolean fl = false;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select * from Users where username = '"+ user +"';");
            if(rs.next()){
                fl = true;
            }
            return fl;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            fl = false;
            System.out.println(e.getLocalizedMessage());
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



    public static int checkForBankExist(String bankName) {
        Connection conn = null;
        Statement stmt = null;
        int bankId = 10;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select * from Banks where name = '"+bankName+"'");
            if(rs.next()){
                bankId = rs.getInt("bankId");
            }
            return bankId;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            bankId = 0;
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            bankId = 0;
            System.out.println(e.getLocalizedMessage());
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return bankId;
    }



    

    public static ArrayList<Long> findCustomerCards(User currentUser){
        Connection conn = null;
        Statement stmt = null;
        ArrayList<Long> arr = new ArrayList<>();
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select * from usersBanksInfo where userId = "+currentUser.userId);
            if(rs.next()){
                arr = Bank.findCustomerCards(rs.getInt("bankId"), currentUser.userId);
            }
            return arr;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            arr = null;
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            arr = null;
            System.out.println(e.getLocalizedMessage());
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return arr;
    }


    public static String getBankName(int bankId){
        String bankName = null;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            
            ResultSet rs = stmt.executeQuery("select name from banks where bankId = "+bankId);
            if(rs.next()){
                bankName = rs.getString("name");
            }
            return bankName;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            bankName = null;
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            bankName = null;
            System.out.println(e.getLocalizedMessage());
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return bankName;
    }


    public static ArrayList<Account> findAccounts(Customer currentCustomer){
        ArrayList<Account> accounts = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            
            ResultSet rs = stmt.executeQuery("select * from usersBanksInfo where userId = "+currentCustomer.userId+ " && isActive = 'ACTIVE'");
            while(rs.next()){
                long accountNumber = rs.getLong("accountNumber");
                int custId = rs.getInt("custId");
                // this next query closing above resultset dont know why
                // ResultSet rs2 = stmt.executeQuery("select name from banks where bankId = "+rs.getInt("bankId"));
                int bankId = rs.getInt("bankId");
                String bankName = Database.getBankName(rs.getInt("bankId"));
                // System.out.println("bank name: "+bankName);
                // System.out.println("account number: "+ accountNumber);
                int branch_code = Bank.getBranchCode(bankId, accountNumber);
                long balance = Bank.getBalance(bankId, accountNumber);
                accounts.add(new Account(accountNumber, currentCustomer.userId, custId, bankName, branch_code, balance, bankId));
            }
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            accounts = null;
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            accounts = null;
            System.out.println(e.getLocalizedMessage());
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


    public static boolean removeAccountFromBankInfo(Account account){
        Connection conn = null;
        Statement stmt = null;
        boolean fl = false;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            stmt.executeUpdate("UPDATE usersBanksInfo SET isActive = 'InActive' WHERE accountNumber = "+account.accountNumber
            +" && custId = "+account.custId+" && bankId = "+account.bankId);
            // find if there is any other active account
            ResultSet rs = stmt.executeQuery("select * from usersBanksInfo where custId = "+account.custId+" && bankId = "+account.bankId+" && isActive = 'ACTIVE'");
            if(rs.next()){
                fl = true;
            }
            return fl;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            fl = false;
            System.out.println(e.getLocalizedMessage());
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

    public static ArrayList<String> getBanksList(){
        ArrayList<String> banks = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            
            ResultSet rs = stmt.executeQuery("select name from Banks");
            while(rs.next()){
                banks.add(rs.getString("name"));
            }
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            banks = null;
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            banks = null;
            System.out.println(e.getLocalizedMessage());
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


    public static ArrayList<String> getBankBranchList(int bankId){
        ArrayList<String> branches = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            
            ResultSet rs = stmt.executeQuery("select name from Branches where bankId = "+bankId);
            while(rs.next()){
                branches.add(rs.getString("name"));
            }
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            branches = null;
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            branches = null;
            System.out.println(e.getLocalizedMessage());
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

    public static int getBranchCode(int bankId, String branchName){
        int branchCode = -1;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            
            ResultSet rs = stmt.executeQuery("select branch_code from Branches where bankId = "+bankId + " && name = '"+branchName+"'");
            while(rs.next()){
                branchCode = rs.getInt("branch_code");
            }
            return  branchCode;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            branchCode = -1;
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            branchCode = -1;
            System.out.println(e.getLocalizedMessage());
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return branchCode;
    }

    public static String getBranchName(int bankId, int branchCode){
        String branchName = "";
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            
            ResultSet rs = stmt.executeQuery("select name from Branches where bankId = "+bankId + " && branch_code = "+branchCode);
            while(rs.next()){
                branchName = rs.getString("name");
            }
            return  branchName;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            branchName = "";
            // System.out.println(se.getLocalizedMessage());
        } catch (Exception e) {
            branchName = "";
            System.out.println(e.getLocalizedMessage());
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return branchName;
    }
}
