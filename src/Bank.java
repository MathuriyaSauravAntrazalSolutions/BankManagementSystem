package BankManagementSystem.src;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

import BankManagementSystem.src.Accounts.Account;
import BankManagementSystem.src.Branches.Branch;
import BankManagementSystem.src.DataBases.BankDatabase;
import BankManagementSystem.src.DataBases.Database;
import BankManagementSystem.src.DataBases.DatabaseConnection;
import BankManagementSystem.src.DataBases.Queries;
import BankManagementSystem.src.Users.Customer;
import BankManagementSystem.src.Validation.Validator;

public class Bank extends BankDatabase{
    public final String name;
    public final String address;
    public final int bankId;
    public ArrayList<Branch> branches; 
    static Scanner sc = new Scanner(System.in);


    public Bank(String name, String address, int bankId){
        this.name = name;
        this.address = address;
        this.bankId = bankId;
        this.branches = new ArrayList<>();
    }

    public static Bank registerBank(){
        System.out.println((Constants.repeat));
        String bankName; boolean fl = false;
        // System.out.println(bankName);
        while(true){
            System.out.print("enter your Bank Name:");
            bankName = sc.nextLine();
            // System.out.println(lastName);
            fl = Validator.isValidName(bankName);
            if(!fl){
                System.out.println((Constants.repeat));
                System.out.println("Name Should Not Contain Any Number Or Special Characters");
                continue;
            }
            break;
        }
        int bankId = Database.checkForBankExist(bankName);
        // System.out.println(bankId);
        if(bankId >= 1001){
            return null;
        }
        String address;
        // System.out.println(address);
        while(true){
            System.out.print("enter your Bank Address:");
            address = sc.nextLine();
            // System.out.println(lastName);
            fl = Validator.isValidName(address);
            if(!fl){
                System.out.println((Constants.repeat));
                System.out.println("Address Should Not Contain Any Number Or Special Characters");
                continue;
            }
            break;
        }
        bankId = Database.getId("bankId", "banks");
        Bank currentBank = new Bank(bankName, address, bankId); // creating new Bank
        return currentBank;
    }


    public int getId(String column, String tableName, int bankId){
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
            String query = "SELECT MAX("+column+") FROM "+tableName+ " where bankId = "+bankId;

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

    public boolean createBanksTables() {
        Connection conn = null;
        Statement stmt = null;
        boolean fl = false;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            // Step 3: Execute a query to create a database
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            stmt.executeUpdate(Queries.CREATE_CUSTOMERS_TABLE_QUERY);
            stmt.executeUpdate(Queries.CREATE_ACCOUNTS_TABLE_QUERY);
            stmt.executeUpdate(Queries.CREATE_BRANCHES_TABLE_QUERY);
            stmt.executeUpdate(Queries.CREATE_TRANSACTIONS_TABLE_QUERY);
            stmt.executeUpdate(Queries.CREATE_LOANS_TABLE_QUERY);
            stmt.executeUpdate(Queries.CREATE_JOINT_ACCOUNTS_TABLE_QUERY);
            fl = true;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            fl = false;
        } catch (Exception e) {
            System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            e.printStackTrace();
            System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            fl = false;
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

    public void registerBranches(){
        System.out.println((Constants.repeat));
        System.out.println("Register Branches!");
        int numberOfBranches;
        while(true){
            try{
                System.out.print("Number Of Branches: ");
                numberOfBranches = Integer.parseInt(sc.nextLine());
                break;
            }
            catch(Exception e){
                System.out.println("Please Specify A Number!");
            }
        }
        System.out.println((Constants.repeat));
        System.out.println("Please Specify "+ numberOfBranches +" Bank Branches In This Formate:\nBranch Name:Address");
        int branchCode = this.getId("branch_Code", "branches", this.bankId);
        System.out.println((Constants.repeat));
        while(numberOfBranches>0){
            String branchString = sc.nextLine();
            if(!branchString.contains(":")){
                System.out.println("Please Specify Branch Details In Specify Formate");
                continue;
            }
            String[] arg = branchString.split(":");
            Branch branch = new Branch(branchCode, arg[0], arg[1]);
            this.branches.add(branch);
            branchCode++;
            numberOfBranches--;
        }
        boolean fl = this.addBranchToTable();
        if(fl){
            System.out.println("Branches Added Successfully!");
        }
        else{
            System.out.println("Something Went Wrong:)");
        }
    }

    public boolean addBranchToTable(){
        boolean fl = true;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            for(Branch br: branches){
                stmt.executeUpdate("Insert into branches(bankId, branch_Code, name, address, balance) values("+
                this.bankId+", "+br.branchCode+", '"+br.branchName+"', '"+br.branchAddress
                +"', "+0+")");
            }
            fl = true;
            
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

    public static boolean checkForBranchExist(String bankName, String branchName, int bankId){
        boolean fl = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select * from branches where name = '"+branchName+"' && bankId = "+bankId);
            if(rs.next()){
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
        }
        return fl;
    }


    public static boolean checkForActiveCustomerAcountExistOfSameType(Customer currentCustomer,String bankName, String branchName, String type, int bankId){
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
            // System.out.println("Branch code is "+branch_code);
            rs = stmt.executeQuery("select * from accounts where type = '"+type+"' && bankId = "+bankId+ " && custId = "+currentCustomer.custId+" && isActive = 'ACTIVE'");
            if(rs.next()){
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
        }
        return fl;
    }


    public static int getExistingCustomerId(Customer currentCustomer,String bankName, int bankId){
        int custId = -1;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select custId from customers where appUserId = "+currentCustomer.userId + " && bankId = "+bankId);
            if(rs.next()){
                custId = rs.getInt("custId");
            }
            return custId;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            custId = -1;
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
        } catch (Exception e) {
            custId = -1;
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
        return custId;
    }


    public static int getBranchCode(int bankId, long accountNumber){
        int branch_code = -1;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select branch_code from accounts where accountNumber = "+accountNumber+" && bankId = "+bankId);
            if(rs.next()){
                branch_code = rs.getInt("branch_code");
            }
            return branch_code;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            branch_code = -1;
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
        } catch (Exception e) {
            branch_code = -1;
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
        return branch_code;
    }

    public static long getBalance(int bankId, long accountNumber){
        int balance = -1;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select balance from accounts where accountNumber = "+accountNumber+" && bankId = "+bankId);
            if(rs.next()){
                balance = rs.getInt("balance");
            }
            return balance;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            balance = -1;
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
        } catch (Exception e) {
            balance = -1;
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
        return balance;
    }

    public static String getAccountType(Account account){
        String type = "";
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select type from accounts WHERE accountNumber = "+account.accountNumber+" && bankId = "+account.bankId);
            // if accounttype is Joint
            if(rs.next()){
                type = rs.getString("type");
            }
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            type = "";
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
        } catch (Exception e) {
            type = "";
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
        return type;
    }

    public static ArrayList<Long> findCustomerCards(int bankId, int userId){
        ArrayList<Long> arr = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select * from customers where appUserId = "+userId +" && bankId = "+bankId);
            if(rs.next()){
                arr.add(rs.getLong("adharNumber"));
                arr.add(rs.getLong("panNumber"));
            }
            return arr;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            arr = null;
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
        } catch (Exception e) {
            arr = null;
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
        return arr;
    }

    public static String getAnothersName(Account account){
        String anotherName = "";
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(Queries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select custId_two_name from jointaccounts WHERE accountNumber = "+account.accountNumber+" && bankId = "+account.bankId);
            // if accounttype is Joint
            if(rs.next()){
                anotherName = rs.getString("custId_two_name");
            }
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            anotherName = "";
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
        } catch (Exception e) {
            anotherName = "";
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
        return anotherName;
    }

    
}
