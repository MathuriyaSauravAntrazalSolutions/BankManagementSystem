package BankManagementSystem.src.Operations;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Constatnts.Errors;
import BankManagementSystem.src.Modals.User;
import BankManagementSystem.src.Repository.UserRepo;
import BankManagementSystem.src.Utils.Validator;

public class GetUserInput extends App {
    public static String getUserInput(){
        return sc.nextLine();
    }

    public static String getFirstName(){
        String firstName;
        while(true){
            System.out.print("Enter Your FirstName: ");
            firstName = GetUserInput.getUserInput();
            // System.out.println(lastName);
            boolean fl = Validator.isValidName(firstName);
            if(!fl){
                System.out.println("Name Should Not Contain Any Number Or Special Characters");
                continue;
            }
            break;
        }
        return firstName;
    }

    public static String getLastName(){
        String lastName;
        while(true){
            System.out.print("Enter Your LastName: ");
            lastName = GetUserInput.getUserInput();
            // System.out.println(lastName);
            boolean fl = Validator.isValidName(lastName);
            if(!fl){
                System.out.println("Name Should Not Contain Any Number Or Special Characters");
                continue;
            }
            break;
        }
        return lastName;
    }

    public static String getEmail(){
        String emailId;
        while(true){
            System.out.print("Enter Your EmailId: ");
            emailId = GetUserInput.getUserInput();
            // System.out.println(emailId);
            boolean fl = Validator.isValidEmail(emailId);
            if(!fl){
                System.out.println("Please Enter a Valid Email Address");
                continue;
            }
            break;
        }
        return emailId;
    }

    public static long getPhone(){
        long phone;
        while(true){
            try{
                System.out.print("Enter Your 10 Degit Phone Number: ");
                phone = Long.parseLong(sc.nextLine());
                // System.out.println(phone);
                if(String.valueOf(phone).length() != 10){
                    System.out.println("Must be 10 Degit Number!");
                    continue;
                }
                else if(phone<0){
                    System.out.println("Please Specify A Number!");
                    continue;
                }
                break;
            }
            catch(Exception e){
                System.out.println("Please Specify A Number!");
            }
        }
        return phone;
    }


    public static String getUserName(){
        String username;
        System.out.print("Enter Your Username: ");
        username = GetUserInput.getUserInput();
        return username;
    }

    public static String getPassword(){
        String password;
        while(true){
            System.out.print("Enter Your Password: ");
            password = GetUserInput.getUserInput();
            boolean fl = Validator.isValidPassword(password);
            if(!fl){
                System.out.println(Constants.REPEAT+"\n");
                System.out.println(Errors.WRONG_PASSWORD_ERROR);
                System.out.println(Constants.REPEAT);
                continue;
            }
            break;
        }
        return password;
    }

    public static String getBankName(){
        String bankName;
        while(true){
            System.out.print("enter your Bank Name:");
            bankName = sc.nextLine();
            // System.out.println(lastName);
            boolean fl = Validator.isValidName(bankName);
            if(!fl){
                System.out.println((Constants.REPEAT));
                System.out.println("Name Should Not Contain Any Number Or Special Characters");
                continue;
            }
            break;
        }
        return bankName;
    }

    public static String getBankAddress(){
        String address;
        // System.out.println(address);
        while(true){
            System.out.print("enter your Bank Address:");
            address = sc.nextLine();
            // System.out.println(lastName);
            boolean fl = Validator.isValidName(address);
            if(!fl){
                System.out.println((Constants.REPEAT));
                System.out.println("Address Should Not Contain Any Number Or Special Characters");
                continue;
            }
            break;
        }
        return address;
    }


    public static int getNumberOfBranches(){
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
        return numberOfBranches;
    }
}
