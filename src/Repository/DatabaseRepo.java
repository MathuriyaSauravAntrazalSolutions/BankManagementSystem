package BankManagementSystem.src.Repository;
import java.sql.*;

import BankManagementSystem.src.Constatnts.Errors;
import BankManagementSystem.src.Constatnts.DatabaseQueries;

/*
*********************************************************************************************************
 *  @Java Class Name :   BankingMenuController
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This controller class contains all the controller methods which shold handle banking menu actions
 * 
 ********************************************************************************************************
*/
public class DatabaseRepo {

    /*
    *********************************************************
     *  @Method Name    :   initialiseApplication
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
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

        return uniqueNumber;
    }


    /*
    *********************************************************
     *  @Method Name    :   initialiseApplication
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
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

        return uniqueNumber;
    }


    /*
    *********************************************************
     *  @Method Name    :   initialiseApplication
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static boolean createDatabaseAndTables() {
        Connection conn = null;
        Statement stmt = null;
        boolean fl = false;
        try {
            // Step 0: Starting the Connection Instance
            DatabaseConnection.getInstance().getConnection();
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
            // System.out.println(Errors.ERROR_REPEAT);
            // e.printStackTrace();
            // System.out.println(Errors.ERROR_REPEAT);
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
}
