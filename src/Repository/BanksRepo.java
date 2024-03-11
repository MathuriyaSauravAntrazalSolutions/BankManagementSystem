package BankManagementSystem.src.Repository;

import java.sql.*;
import java.util.ArrayList;

import BankManagementSystem.src.Constatnts.Errors;
import BankManagementSystem.src.Constatnts.DatabaseQueries;
import BankManagementSystem.src.Modals.Bank;
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
public class BanksRepo {
    

    /*
    *********************************************************
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static boolean insertBankInTable(User currentUser, Bank currentBank) {
        Connection conn = null;
        Statement stmt = null;
        boolean fl = false;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            stmt.executeUpdate("Insert into Banks(bankId, name, ownerId, address) values("
            +currentBank.bankId+", '"+currentBank.name+"', "+currentUser.userId+", '"+currentBank.address
            +"')");
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


    /*
    *********************************************************
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static Bank getBank(int bankId){
        Bank bank = null;
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            
            ResultSet rs = stmt.executeQuery("select name from banks where bankId = "+bankId);
            if(rs.next()){
                bank = BanksRepo.getBank(rs.getString("name"));
            }
            return bank;
            
        } catch (Exception e) {
            bank = null;
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
        return bank;
    }


    /*
    *********************************************************
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static Bank getBank(String bankName) {
        Connection conn = null;
        Statement stmt = null;
        Bank bank = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            ResultSet rs = stmt.executeQuery("select * from Banks where name = '"+bankName+"'");
            if(rs.next()){
                return Bank.getBankInstance(rs.getString("name"), rs.getString("address"), rs.getInt("bankId"), BranchRepo.getBankBranches(rs.getInt("bankId")));
            }
            return bank;
            
        }catch (Exception e) {
            bank = null;
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
        return bank;
    }


    /*
    *********************************************************
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
    public static ArrayList<Bank> getBanks(){
        ArrayList<Bank> banks = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Get Connection
            conn = DatabaseConnection.getInstance().getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(DatabaseQueries.USE_APP_DB_QUERY);
            
            ResultSet rs = stmt.executeQuery("select name from Banks");
            while(rs.next()){
                banks.add(BanksRepo.getBank(rs.getString("name")));
            }
            
        } catch (Exception e) {
            banks = null;
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
        return banks;
    }

}
