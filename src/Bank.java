package BankManagementSystem.src;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

import BankManagementSystem.src.Branches.Branch;
import BankManagementSystem.src.DataBases.BankDatabase;
import BankManagementSystem.src.DataBases.Database;
import BankManagementSystem.src.Users.Customer;

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
        System.out.println(("=".repeat(15)));
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
        Bank currentBank = new Bank(bankName, address, bankId); // creating new Bank
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
            stmt.executeUpdate("CREATE TABLE IF NOT Exists "+this.name+"."+"customers("
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
            + "branch_code INT NOT NULL,"
            + "name VARCHAR(255) NOT NULL,"
            + "balance BIGINT NOT NULL,"
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



    public boolean addBranchToTable(){
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
            stmt.execute("Use "+this.name);
            for(Branch br: branches){
                int affected_rows = stmt.executeUpdate("Insert into branches(branch_Code, name, address, balance) values("
                +br.branchCode+", '"+br.branchName+"', '"+br.branchAddress
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


    public void registerBranches(){
        System.out.println(("=".repeat(15)));
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
        System.out.println(("=".repeat(15)));
        System.out.println("Please Specify "+ numberOfBranches +" Bank Branches In This Formate:\nBranch Name:Address");
        int branchCode = this.getId("branch_Code", "branches");
        System.out.println(("=".repeat(15)));
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


    public static boolean checkForBranchExist(String bankName, String branchName){
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
            ResultSet rs = stmt.executeQuery("select * from branches where name = '"+branchName+"'");
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


    public static boolean checkForCustomerExist(Customer currentCustomer,String bankName, String branchName, String type){
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
            // System.out.println("Branch code is "+branch_code);
            rs = stmt.executeQuery("select c.custId as custId from customers c Inner Join "
            +"accounts a on a.custId = c.custId where a.branch_code = "+branch_code+" && c.appUserId = "
            +currentCustomer.userId+" && a.type = '"+type+"'");
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


    public static int getCustomerId(Customer currentCustomer,String bankName){
        int custId = -1;
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
            ResultSet rs = stmt.executeQuery("select custId from customers where appUserId = "+currentCustomer.userId);
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
            try { 
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            }
        }
        return custId;
    }


    public static int getBranchCode(String bankName, long accountNumber){
        int branch_code = -1;
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
            ResultSet rs = stmt.executeQuery("select branch_code from accounts where accountNumber = "+accountNumber);
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
            try { 
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            }
        }
        return branch_code;
    }

    public static long getBalance(String bankName, long accountNumber){
        int balance = -1;
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
            ResultSet rs = stmt.executeQuery("select balance from accounts where accountNumber = "+accountNumber);
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
            try { 
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            }
        }
        return balance;
    }

    public static ArrayList<Long> findCustomer(String bankName, int userId){
        ArrayList<Long> arr = new ArrayList<>();
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
            ResultSet rs = stmt.executeQuery("select * from customers where appUserId = "+userId);
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
            try { 
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
                se.printStackTrace();
                System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            }
        }
        return arr;
    }
}
