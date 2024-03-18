package BankManagementSystem.src.Repository;

import java.sql.*;
import java.util.ArrayList;

import BankManagementSystem.src.Constatnts.Errors;
import BankManagementSystem.src.Constatnts.DatabaseQueries;
import BankManagementSystem.src.Modals.Bank;
import BankManagementSystem.src.Modals.Branch;

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
public class BranchRepo {
    
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
        return branches;
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
        return branch;
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
            System.out.println(Errors.ERROR_REPEAT);
            e.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);} finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                // System.out.println(se.getLocalizedMessage());
            }
        }
        return branch;
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
            System.out.println(Errors.ERROR_REPEAT);
            se.printStackTrace();
            System.out.println(Errors.ERROR_REPEAT);
        } catch (Exception e) {
            branch_code = -1;
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
        return branch_code;
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
