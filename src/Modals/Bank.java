package BankManagementSystem.src.Modals;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    public final String name;
    public final String address;
    public final int bankId;
    public ArrayList<Branch> branches; 
    static Scanner sc = new Scanner(System.in);


    public Bank(String name, String address, int bankId, ArrayList<Branch> branches){
        this.name = name;
        this.address = address;
        this.bankId = bankId;
        this.branches = branches;
    }

    public Bank(String name, String address, int bankId){
        this.name = name;
        this.address = address;
        this.bankId = bankId;
        this.branches = new ArrayList<>();
    }


    public static Bank getBankInstance(String name, String address, int bankId, ArrayList<Branch> branches){
        return new Bank(name, address, bankId, branches);
    }


    public String toString(){
        System.out.println("Bank Name: "+this.name+"\nBank Id: "+this.bankId);
        return "";
    }
}
