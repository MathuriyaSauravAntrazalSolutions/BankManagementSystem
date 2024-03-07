package BankManagementSystem.src.Services;

import java.util.ArrayList;
import java.util.Scanner;

import BankManagementSystem.src.IRepo.Database;
import BankManagementSystem.src.Modals.Branch;
import BankManagementSystem.src.Utils.Constants;

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

    public static Bank registerBank(){
        System.out.println((Constants.REPEAT));
        String bankName; boolean fl = false;
        // System.out.println(bankName);
        while(true){
            System.out.print("enter your Bank Name:");
            bankName = sc.nextLine();
            // System.out.println(lastName);
            fl = Validator.isValidName(bankName);
            if(!fl){
                System.out.println((Constants.REPEAT));
                System.out.println("Name Should Not Contain Any Number Or Special Characters");
                continue;
            }
            break;
        }
        Bank currentBank = Database.getBank(bankName); // null or bank
        // System.out.println(bankId);
        if(currentBank != null){
            return null; // mean this bank already exists
        }
        String address;
        // System.out.println(address);
        while(true){
            System.out.print("enter your Bank Address:");
            address = sc.nextLine();
            // System.out.println(lastName);
            fl = Validator.isValidName(address);
            if(!fl){
                System.out.println((Constants.REPEAT));
                System.out.println("Address Should Not Contain Any Number Or Special Characters");
                continue;
            }
            break;
        }
        int bankId = Database.GET_UNIQUE_ID("bankId", "banks");
        currentBank = new Bank(bankName, address, bankId); // creating new Bank which has no Branhes
        currentBank.registerBranches();
        // currentBank.toString();
        return currentBank;
    }

    public void registerBranches(){
        System.out.println((Constants.REPEAT));
        System.out.println("Register Branches!");
        int numberOfBranches;
        while(true){
            try{
                System.out.print("Number Of Branches: ");
                numberOfBranches = Integer.parseInt(sc.nextLine());
                break;
            }
            catch(Exception e){
                System.out.println("Please Specify A Number!");
            }
        }
        System.out.println((Constants.REPEAT));
        System.out.println("Please Specify "+ numberOfBranches +" Bank Branches In This Formate:\nBranch Name:Address");
        int branchCode = Database.GET_UNIQUE_ID("branch_Code", "branches", this.bankId);
        System.out.println((Constants.REPEAT));
        while(numberOfBranches>0){
            String branchString = sc.nextLine();
            if(!branchString.contains(":")){
                System.out.println("Please Specify Branch Details In Specify Formate");
                continue;
            }
            String[] arg = branchString.split(":");
            Branch branch = new Branch(branchCode, arg[0], arg[1]);
            this.branches.add(branch);
            branchCode++;
            numberOfBranches--;
        }
        boolean fl = Database.insertBranchesToTable(this);
        if(fl){
            System.out.println((Constants.REPEAT)); 
            System.out.println("Branches Added Successfully!");
        }
        else{
            System.out.println("Something Went Wrong:)");
        }
    }
}
