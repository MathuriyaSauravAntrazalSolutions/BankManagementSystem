package BankManagementSystem.src.Operations;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Constatnts.Errors;
import BankManagementSystem.src.Utils.Validator;

/*
*********************************************************************************************************
 *  @Java Class Name :   GetUserInput
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This class contains all the user related input output things.
 * 
 ********************************************************************************************************
*/
public class GetUserInput extends App {

    /*
    *********************************************************
     *  @Method Name    :   getUserInput
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This method takes user input from console.
     *  @param          :   --------
     *  @return         :   String            
    *********************************************************
    */
    public static String getUserInput(){
        return sc.nextLine();
    }

    /*
    *********************************************************
     *  @Method Name    :   getFirstName
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function takes and user's firsr
     *  @param          :   --------
     *  @return         :   String            
    *********************************************************
    */
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

    /*
    *********************************************************
     *  @Method Name    :   getLastName
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function takes user last name as input
     *  @param          :   --------
     *  @return         :   --------            
    *********************************************************
    */
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

    /*
    *********************************************************
    *  @Method Name    :   getEmail
    *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
    *  @Company        :   Antrazal
    *  @Date           :   18-03-2024
    *  @description    :   This function takes user email as input.
    *  @param          :   --------
    *  @return         :   String
    *********************************************************
    */
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

    /*
    *********************************************************
    *  @Method Name    :   getPhone
    *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
    *  @Company        :   Antrazal
    *  @Date           :   18-03-2024
    *  @description    :   This method takes user's mobile number as input and returns it.
    *  @param          :   --------
    *  @return         :   long phoneNumber
    *********************************************************
    */
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


    /*
    *********************************************************
    *  @Method Name    :   getUserName
    *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
    *  @Company        :   Antrazal
    *  @Date           :   18-03-2024
    *  @description    :   This function takes username as input from user.
    *  @param          :   --------
    *  @return         :   String
    *********************************************************
    */
    public static String getUserName(){
        String username;
        System.out.print("Enter Your Username: ");
        username = GetUserInput.getUserInput();
        return username;
    }


    /*
    *********************************************************
     *  @Method Name    :   getPassword
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   this function takes user's password as input from user.
     *  @param          :   --------
     *  @return         :   String            
    *********************************************************
    */
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


    /*
    *********************************************************
     *  @Method Name    :   getBankName
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function takes users bank name as input form user.
     *  @param          :   --------
     *  @return         :   String            
    *********************************************************
    */
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


    /*
    *********************************************************
     *  @Method Name    :   getBankAddress
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function takes users bank address as input form user.
     *  @param          :   --------
     *  @return         :   String            
    *********************************************************
    */
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


    /*
    *********************************************************
     *  @Method Name    :   getNumberOfBranches
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function takes number of branches as input form user.
     *  @param          :   --------
     *  @return         :   int           
    *********************************************************
    */
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
