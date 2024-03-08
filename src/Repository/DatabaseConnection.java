package BankManagementSystem.src.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import BankManagementSystem.src.Constatnts.Errors;

// Database Connection Singelton Class

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/";
    private String username = "root";
    private String password = "Saurav@2942";

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException error) {
            System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
            System.out.println("Database Connection Creation Failed : " + error.getMessage());
            // error.printStackTrace();
            System.out.println(("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10)));
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public void closeConnection(){
        try {
            if (this.connection != null) this.connection.close();
        } catch (SQLException error) {
            System.out.println(Errors.ERROR_REPEAT);
            // System.out.println("error in closing the connection" + error.getMessage());
            error.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        }
    }
}