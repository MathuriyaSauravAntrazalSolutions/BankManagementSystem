package BankManagementSystem.src.Repository;

import java.sql.*;

import BankManagementSystem.src.Constatnts.Errors;
import BankManagementSystem.src.Constatnts.DatabaseQueries;
import BankManagementSystem.src.Modals.Customer;
import BankManagementSystem.src.Modals.User;


public class UserRepo {
    
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
        return currentUser;
    }


















}
