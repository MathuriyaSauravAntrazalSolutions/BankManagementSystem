package BankManagementSystem.src;

import java.util.Scanner;

import BankManagementSystem.src.DataBases.BankDatabase;
import BankManagementSystem.src.DataBases.Database;

public class Bank extends BankDatabase{
    public final String name;
    public final String address;
    public final int bankId;
    static Scanner sc = new Scanner(System.in);


    public Bank(String name, String address, int bankId){
        this.name = name;
        this.address = address;
        this.bankId = bankId;
    }

    public static Bank registerBank(){
        System.out.print("enter your Bank Name:");
        String bankName = sc.nextLine();
        // System.out.println(bankName);
        boolean fl = Database.checkForBankExist(bankName);
        if(fl){
            return null;
        }
        System.out.print("enter your Bank Address:");
        String address = sc.nextLine();
        // System.out.println(address);
        int bankId = Database.getId("bankId", "banks");
        Bank currentBank = new Bank(bankName, address, bankId); // creating new user
        return currentBank;
    }
}
