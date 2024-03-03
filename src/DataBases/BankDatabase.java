package BankManagementSystem.src.DataBases;

import java.sql.*;


public class BankDatabase {
    private String name;

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
            stmt.execute("Use "+this.name+"BankDatabase");
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

}
