package BankManagementSystem.src.Modals;

import java.util.ArrayList;
import java.util.Scanner;

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
public class Bank {
    public final String name;
    public final String address;
    public final int bankId;
    public ArrayList<Branch> branches; 
    static Scanner sc = new Scanner(System.in);


    /*
    *********************************************************
     *  @Method Name    :   Bank
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Constructor Of Bank Class
     *  @param          :   String name, String address, int bankId, ArrayList<Branch> branches
     *  @return         :   Bank Object            
    *********************************************************
    */
    public Bank(String name, String address, int bankId, ArrayList<Branch> branches){
        this.name = name;
        this.address = address;
        this.bankId = bankId;
        this.branches = branches;
    }

    /*
    *********************************************************
     *  @Method Name    :   Bank
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Constructor Of Bank Class
     *  @param          :   String name, String address, int bankId
     *  @return         :   Bank Object            
    *********************************************************
    */
    public Bank(String name, String address, int bankId){
        this.name = name;
        this.address = address;
        this.bankId = bankId;
        this.branches = new ArrayList<>();
    }


    /*
    *********************************************************
     *  @Method Name    :   getBankInstance
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Used to get Instance
     *  @param          :   String name, String address, int bankId, ArrayList<Branch> branches
     *  @return         :   Bank Object            
    *********************************************************
    */
    public static Bank getBankInstance(String name, String address, int bankId, ArrayList<Branch> branches){
        return new Bank(name, address, bankId, branches);
    }


    /*
    *********************************************************
     *  @Method Name    :   toString
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function is used to Bank Details as String
     *  @param          :   --------
     *  @return         :   String            
    *********************************************************
    */
    public String toString(){
        System.out.println("Bank Name: "+this.name+"\nBank Id: "+this.bankId);
        return "";
    }
}
