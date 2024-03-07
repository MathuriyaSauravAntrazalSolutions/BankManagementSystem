package BankManagementSystem.src.Operations;

import java.util.Scanner;

import BankManagementSystem.src.Utils.Constants;

public class AskCustomerInput {
    static Scanner sc = new Scanner(System.in);

    // just getting key only
    public static int askCustomer(){
        System.out.println(Constants.REPEAT+"\n");
        System.out.println(Constants.ASK_CUSTOMER);
        System.out.println(Constants.REPEAT);
        int key;
        try{
            System.out.print("Key: ");
            key = sc.nextInt(); sc.nextLine();
            if(key<=0 || key >7){
                System.out.println(Constants.REPEAT);
                System.out.println("Invalid Key Please Enter Valid Key!");
                key = askCustomer();
            }
        }catch(Exception e){
            System.out.println(Constants.REPEAT);
            System.out.println("Invalid Key Please Enter Valid Key!");
            sc.nextLine();
            key = askCustomer();
        }
        return key;
    }
}
