package BankManagementSystem.src.Constatnts;

/*
*********************************************************************************************************
 *  @Java Class Name :   Errors
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This class contains all the Errors which should bve thrown or print on console.
 * 
 ********************************************************************************************************
*/
public class Errors {
    public static final String SOMETHING_WENT_WRONG_ERROR =    Constants.REPEAT+"\nSomething went wrong!"; 
    public static final String USER_ALREADY_EXISTS_MESSAGE = Constants.REPEAT+"\nThis User Already Exists Please Sign In!";
    public final static String ERROR_REPEAT = ("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10));
    public final static String IN_VALID_KEY_ERROR = Constants.REPEAT+"\nInvalid Key Please Enter Valid Key!";
    public final static String WRONG_PASSWORD_ERROR = 
                                        """
                                            Password Sould be 6-12 Characters Long
                                            Must Contain One Or More (Capital Letter, 
                                            Small Letter, Special Character And Integer)
                                        """;
}
