package BankManagementSystem.src.DataBases;



import java.sql.*;

import BankManagementSystem.src.Bank;
import BankManagementSystem.src.Users.User;

public class Database {
    public static int getId(String column, String tableName){
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
            stmt.execute("Use AppDatabase");
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
            System.out.println(se.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println("3. "+se.getMessage());
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println("4. "+se.getMessage());
            }
        }

        return uniqueNumber;
    }

    public static boolean createDatabase(String databaseName) {
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
            String sqlCreateDB = "CREATE DATABASE "+databaseName;
            stmt.executeUpdate(sqlCreateDB);
            // System.out.println("AppDatabase created successfully");
            fl = true;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            // System.out.println("1. "+se.getMessage());
            fl = false;
        } catch (Exception e) {
            // System.out.println(e.getMessage());
            fl = false;
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println("3. "+se.getMessage());
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println("4. "+se.getMessage());
            }
        }
        return fl;
    }




    public static boolean createUserTable(String tablename) {
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
            
            // Step 3: Execute a query to create a usertable
            stmt = conn.createStatement();
            stmt.execute("Use AppDatabase");
            stmt.executeUpdate("CREATE TABLE appdatabase."+ tablename+"("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "userId INT NOT NULL,"
            + "username VARCHAR(255) NOT NULL,"
            + "password VARCHAR(255) NOT NULL,"
            + "emailId VARCHAR(255) NOT NULL,"
            + "phone BIGINT NOT NULL,"
            + "firstName VARCHAR(255) NOT NULL,"
            + "lastName VARCHAR(255) NOT NULL"
            +")");
            fl = true;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
        } catch (Exception e) {
            fl = false;
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println("3. "+se.getMessage());
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println("4. "+se.getMessage());
            }
        }
        return fl;
    }


    public static boolean createBankTable(String tablename) {
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
            
            // Step 3: Execute a query to create a usertable
            stmt = conn.createStatement();
            stmt.execute("Use AppDatabase");
            stmt.executeUpdate("CREATE TABLE appdatabase."+ tablename+"("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "bankId Int NOT NULL,"
            + "name VARCHAR(50) NOT NULL,"
            + "ownerId int NOT NULL,"
            + "address VARCHAR(50) NOT NULL"
            +")");
            fl = true;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
        } catch (Exception e) {
            fl = false;
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println("3. "+se.getMessage());
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println("4. "+se.getMessage());
            }
        }
        return fl;
    }


    public static boolean createUserInfoTable(String tablename) {
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
            
            // Step 3: Execute a query to create a usertable
            stmt = conn.createStatement();
            stmt.execute("Use AppDatabase");
            stmt.executeUpdate("CREATE TABLE appdatabase."+ tablename+"("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "userId Int NOT NULL,"
            + "bankId Int NOT NULL,"
            + "accountNumber BIGINT NOT NULL"
            +")");
            fl = true;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
        } catch (Exception e) {
            fl = false;
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println("3. "+se.getMessage());
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println("4. "+se.getMessage());
            }
        }
        return fl;
    }


    public static User checkForUserExist(String user, String pass) {
        Connection conn = null;
        Statement stmt = null;
        User currentUser = null;
        try {
            // Step 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Step 2: Open a connection
            String jdbcUrl = "jdbc:mysql://localhost:3306/";
            String username = "root";
            String password = "Saurav@2942";
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            
            stmt = conn.createStatement();
            stmt.execute("Use AppDatabase");
            ResultSet rs = stmt.executeQuery("select * from Users where username = '"+ user +"' and "+ " password = '"+pass+"';");
            if(rs.next()){
                currentUser = new User(rs.getInt("userId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"), rs.getString("password"), rs.getString("emailId"), rs.getLong("phone"));
            }
            return currentUser;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            currentUser = null;
        } catch (Exception e) {
            currentUser = null;
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println("3. "+se.getMessage());
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println("4. "+se.getMessage());
            }
        }
        return currentUser;
    }


    public static User InsertUserInTable(User currentUser) {
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
            stmt.execute("Use AppDatabase");
            int affected_rows = stmt.executeUpdate("Insert into users(userId, username, password, emailId, firstName, lastName, phone) values("
            +currentUser.userId+", '"+currentUser.username+"', '"+currentUser.password+"', '"+currentUser.emailId+"','"
            +currentUser.firstName+"', '"+currentUser.lastName+"', "+currentUser.phone
            +")");
            return currentUser;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            currentUser = null;
            System.out.println(se.getMessage());
        } catch (Exception e) {
            currentUser = null;
            System.out.println(e.getMessage());
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println("3. "+se.getMessage());
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println("4. "+se.getMessage());
            }
        }
        return currentUser;
    }


    public static boolean checkForBankExist(String bankName) {
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
            
            stmt = conn.createStatement();
            stmt.execute("Use AppDatabase");
            ResultSet rs = stmt.executeQuery("select * from Banks where name = '"+bankName+"'");
            if(rs.next()){
                fl = true;
            }
            return fl;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            fl = false;
        } catch (Exception e) {
            fl = false;
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println("3. "+se.getMessage());
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println("4. "+se.getMessage());
            }
        }
        return fl;
    }


    
    public static Bank InsertBankInTable(User currentUser, Bank currentBank) {
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
            stmt.execute("Use AppDatabase");
            int affected_rows = stmt.executeUpdate("Insert into Banks(bankId, name, ownerId, address) values("
            +currentBank.bankId+", '"+currentBank.name+"', '"+currentUser.userId+"', '"+currentBank.address
            +"')");
            return currentBank;
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            currentBank = null;
            System.out.println(se.getMessage());
        } catch (Exception e) {
            currentBank = null;
            System.out.println(e.getMessage());
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println("3. "+se.getMessage());
            }
            try { 
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println("4. "+se.getMessage());
            }
        }
        return currentBank;
    }


}
