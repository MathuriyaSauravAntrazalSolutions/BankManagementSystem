package BankManagementSystem.src.Repository;


import java.sql.*;
import java.util.ArrayList;

import BankManagementSystem.src.Constatnts.Errors;
import BankManagementSystem.src.Constatnts.DatabaseQueries;
import BankManagementSystem.src.Modals.Customer;
import BankManagementSystem.src.Modals.User;

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
public class CustomerRepo {
    
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
            System.out.println(Errors.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } catch (Exception e) {
            cards = null;
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
        return cards;
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
            System.out.println(Errors.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } catch (Exception e) {
            custId = -1;
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
        return custId;
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
    public static boolean checkForActiveCustomerAcountExistOfSameType(Customer currentCustomer,String bankName, String branchName, String type, int bankId){
        boolean fl = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            // System.out.println("Branch code is "+branch_code);
            ResultSet rs = stmt.executeQuery("select * from accounts where type = '"+type+"' && bankId = "+bankId+ " && custId = "+currentCustomer.custId+" && isActive = 'ACTIVE'");
            if(rs.next()){
                fl = true;
            }
            return fl;
            
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
}
