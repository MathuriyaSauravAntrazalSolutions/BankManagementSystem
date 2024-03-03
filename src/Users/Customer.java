package BankManagementSystem.src.Users;

import java.util.ArrayList;

import BankManagementSystem.src.Accounts.Account;

public class Customer {
    private String firstName;
    private String lastName;
    private int age;
    private int salary;
    private long adharNumber;
    private String panNumber;
    private String address;
    private long phoneNumber;
    private String password;
    private ArrayList<Account> accounts = new ArrayList<>();

    public Customer(String firstName,String lastName, int age, int salary, long adharNumber, String panNumber, String address, long phoneNumber, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
        this.adharNumber = adharNumber;
        this.panNumber = panNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public long getAdharNumber() {
        return adharNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public String getAddress() {
        return address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public boolean addInJointAccount(Account account, Customer customer){
        if(accounts.contains(account)){
            System.out.println("Joint account added successfully");
            // do some stuff
            return true;
        }
        System.out.println("No Account Found");
        return false;
    }


    public boolean addAccount(Account account){
        System.out.println(("=".repeat(15)));
        if(accounts.contains(account)){
            System.out.println("Sorry user already has this account");
            return false;
        }
        accounts.add(account);
        return true;
    }

    public boolean deleteAccount(Account account){
        System.out.println(("=".repeat(15)));
        if(accounts.contains(account)){
            System.out.println("Account is deleted Successfully!");
            // delete account here
            return true;
        }
        System.out.println("No Account Found With This Account");
        return false;
    }


    public boolean findAccount(Account account){
        System.out.println(("=".repeat(15)));
        if(accounts.contains(account)){
            System.out.println("Account is Found Successfully!");
            // do stuff here
            return true;
        }
        System.out.println("No Account Found With This Account");
        return false;
    }


    public void printAccountsDetails(){
        System.out.println(("=".repeat(15)));
        if(accounts.size() > 0){
            System.out.println("Total Bank Accounts Associate with user:");
            for(Account account: accounts){
                // do stuff here
                System.out.println(account);
            }
        }
        System.out.println("This user has 0 Bank Accounts");
    }

    public void printCustomerDetails(){
        System.out.println(("=".repeat(15)));
        System.out.println("Customer Details:");
        System.out.println("Cusomer Name: "+ this.firstName+" "+this.lastName);
        System.out.println("Cusomer Age: "+ this.age);
        System.out.println("Cusomer Salary: "+ this.salary);
        System.out.println("Cusomer Adhar Number: "+ this.adharNumber);
        System.out.println("Cusomer Pan Number: "+ this.panNumber);
        System.out.println("Cusomer Address: "+ this.address);
        System.out.println("Cusomer Phone Number: "+ this.phoneNumber);
    }
}
