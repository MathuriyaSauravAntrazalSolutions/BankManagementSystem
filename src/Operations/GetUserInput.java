package BankManagementSystem.src.Operations;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Constatnts.Errors;
import BankManagementSystem.src.Utils.Validator;

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
public class GetUserInput extends App {

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
    public static String getUserInput(){
        return sc.nextLine();
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
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
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
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
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
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
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
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
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
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
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
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
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
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
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
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
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
