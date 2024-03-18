package BankManagementSystem.src.Constatnts;

/*
*********************************************************************************************************
 *  @Java Class Name :   Printer
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This class contains all the messages which should be shown to user on console for different purposes.
 * 
 ********************************************************************************************************
*/
public class Printer {
    public static final String WELCOME_MESSAGE = Constants.REPEAT+"\nWelcome To My App!";
    public static final String EXIT_MESSAGE = Constants.REPEAT+"\nBye! Visit Us Again :)";

    public static void printInvalidKeyError(){
        System.out.println(Errors.IN_VALID_KEY_ERROR);
    }

    public static void printKey(){
        System.out.print("Key: ");
    }
}
