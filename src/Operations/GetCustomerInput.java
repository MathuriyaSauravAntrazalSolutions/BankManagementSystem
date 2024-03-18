package BankManagementSystem.src.Operations;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Modals.User;
import BankManagementSystem.src.Utils.Validator;

/*
*********************************************************************************************************
 *  @Java Class Name :   GetCustomerInput
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This class contains all the functionalities to take input from user.
 * 
 ********************************************************************************************************
*/
public class GetCustomerInput extends App {

    /*
    *********************************************************
     *  @Method Name    :   getCustomerPan
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This method is used to get pan card details of customer.
     *  @param          :   User currentUser
     *  @return         :   long panNumber            
    *********************************************************
    */
    public static long getCustomerPan(User currentUser){
        long panNumber;
        while(true){
            try{
                System.out.println(Constants.REPEAT);
                System.out.print("Enter your PanCard Number:");
                panNumber = Long.parseLong(sc.nextLine());
                if(String.valueOf(panNumber).length()!=12){
                    System.out.println("Number Length Shold Be 12"); 
                    continue;
                }
                // System.out.println(panNumber);
                break;
            }
            catch(Exception e){
                System.out.println(Constants.REPEAT);
                System.out.println("Please Specify A Number!");
            }
        }
        return panNumber;
    }


    /*
    *********************************************************
     *  @Method Name    :   getCustomerAdhar
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function is used to get users adhar number
     *  @param          :   User currentUser
     *  @return         :   long adharNumber            
    *********************************************************
    */
    public static long getCustomerAdhar(User currentUser){
        long adharNumber;
        while(true){
            try{
                System.out.println(Constants.REPEAT);
                System.out.print("Enter your Adhar Number:");
                adharNumber = Long.parseLong(sc.nextLine());
                // System.out.println(adharNumber);
                if(String.valueOf(adharNumber).length()!=12){
                    System.out.println("Number Length Shold Be 12"); 
                    continue;
                }
                break;
            }
            catch(Exception e){
                System.out.println(Constants.REPEAT);
                System.out.println("Please Specify A Number!");
            }
        }
        return adharNumber;
    }


    /*
    *********************************************************
     *  @Method Name    :   getAmount
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This method takes amount input from user
     *  @param          :   --------
     *  @return         :   long amount            
    *********************************************************
    */
    public static long getAmount(){
        long amount;
        while(true){
            try{        
                System.out.println(Constants.REPEAT);
                System.out.print("Amount: ");
                amount = Long.parseLong(sc.nextLine());
                if(amount<0){
                    System.out.println(Constants.REPEAT);
                    System.out.println("Please Specify A Positive Amount!");
                    continue;
                }
                break;
            }
            catch(Exception e){
                System.out.println(Constants.REPEAT);
                System.out.println("Please Specify A Number!");
            }
        }
        return amount;
     }


    /*
    *********************************************************
     *  @Method Name    :   getName
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function is used to take Name input of customer.
     *  @param          :   --------
     *  @return         :   String name            
    *********************************************************
    */
    public static String getName(){
        String name;
        while(true){
            System.out.print("Enter Your FirstName: ");
            name = GetUserInput.getUserInput();
            // System.out.println(lastName);
            boolean fl = Validator.isValidName(name);
            if(!fl){
                System.out.println("Name Should Not Contain Any Number Or Special Characters");
                continue;
            }
            break;
        }
        return name;
    }


    /*
    *********************************************************
     *  @Method Name    :   setPin
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function is used to set input pin of user account
     *  @param          :   --------
     *  @return         :   int pin            
    *********************************************************
    */
    public static int setPin(String name){
        int pin;
        while(true){
            try{
                System.out.println(Constants.REPEAT);
                System.out.println(name+" Please Set Up Your 4 Degit Account's Security Pin ");
                System.out.print("Enter The Pin: ");
                pin = Integer.parseInt(sc.nextLine());
                if(String.valueOf(pin).length() != 4){
                    System.out.println(Constants.REPEAT);
                    System.out.println("Please Specify 4 Degit Number");
                }
                // System.out.println(balance);
                break;
            }
            catch(Exception e){
                System.out.println(Constants.REPEAT);
                System.out.println("Please Specify A Number!");
            }
        }
        return pin;
    }

    /*
    *********************************************************
     *  @Method Name    :   getPin
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function is used to get pin as input of user account
     *  @param          :   --------
     *  @return         :   int pin            
    *********************************************************
    */
    public static int getPin(String name){
        int pin;
        while(true){
            try{
                System.out.println(Constants.REPEAT);
                System.out.println(name+" Please Enter Your 4 Degit Account's Security Pin ");
                System.out.print("Enter The Pin: ");
                pin = Integer.parseInt(sc.nextLine());
                if(String.valueOf(pin).length() != 4){
                    System.out.println(Constants.REPEAT);
                    System.out.println("Please Specify 4 Degit Number");
                }
                // System.out.println(balance);
                break;
            }
            catch(Exception e){
                System.out.println(Constants.REPEAT);
                System.out.println("Please Specify A Number!");
            }
        }
        return pin;
    }

}
