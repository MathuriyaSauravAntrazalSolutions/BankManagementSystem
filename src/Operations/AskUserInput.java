package BankManagementSystem.src.Operations;

import java.util.Scanner;

import BankManagementSystem.src.Utils.Constants;

public class AskUserInput {
    static Scanner sc = new Scanner(System.in);

    public static final int askUser(){
        System.out.println(Constants.REPEAT+"\n");
        System.out.println(Constants.ASK_NEW_USER);
        System.out.println(Constants.REPEAT);
        int key;
        try{
            System.out.print("Key: ");
            key = sc.nextInt(); sc.nextLine();
            if(key<=0 || key >3){
                System.out.println(Constants.REPEAT);
                System.out.println(Constants.IN_VALID_KEY_ERROR);
                key = AskUserInput.askUser();
            }
        }catch(Exception e){
            System.out.println(Constants.REPEAT);
            System.out.println(Constants.IN_VALID_KEY_ERROR);
            sc.nextLine();
            key = AskUserInput.askUser();
        }
        return key;
    }


    public static final int askUserCustomer(){
        System.out.println(Constants.REPEAT+"\n");
        System.out.println(Constants.ASK_CURRENT_USER);
        System.out.println(Constants.REPEAT);
        int key;
        try{
            System.out.print("Key: ");
            key = sc.nextInt(); sc.nextLine();
            if(key<=0 || key >4){
                System.out.println(Constants.REPEAT);
                System.out.println(Constants.IN_VALID_KEY_ERROR);
                key = AskUserInput.askUserCustomer();
        }
        }catch(Exception e){
            System.out.println(Constants.REPEAT);
            System.out.println(Constants.IN_VALID_KEY_ERROR);
            sc.nextLine();
            key = AskUserInput.askUserCustomer();
        }
        return key;
    }



    public static String getUserInput(){
        return sc.nextLine();
    }
}
