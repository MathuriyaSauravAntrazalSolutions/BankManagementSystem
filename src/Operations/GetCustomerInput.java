package BankManagementSystem.src.Operations;

import BankManagementSystem.src.App.App;
import BankManagementSystem.src.Constatnts.Constants;
import BankManagementSystem.src.Modals.User;
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
public class GetCustomerInput extends App {

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
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
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
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
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
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
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
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
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
     *  @Method Name    :   initialiseApplication
     *  @author         :   <Himanshu Vaswani>(Himanshu.Vaswani@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This Function Initialise The Code To Take Care Of The Console As It Shows The User The Main Menu Of The Overall Application
     *  @param          :   --------
     *  @return         :   --------            
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
