package BankManagementSystem.src.Constatnts;

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
