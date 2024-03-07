package BankManagementSystem.src.Utils;

public class Constants {
    public static String REPEAT = ("=").repeat(15);
    
    public static String ERROR_REPEAT = ("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10));

    public static String ASK_NEW_USER = 
                                    """
                                        Follow These Steps To Use The App!
                                        1. Sign-In
                                        2. SignUp
                                        3. Exit
                                        Please Enter a Key!
                                    """;
    public static String IN_VALID_KEY_ERROR = "Invalid Key Please Enter Valid Key!";

    public static String WRONG_PASSWORD_ERROR = 
                                        """
                                            Password Sould be 6-12 Characters Long
                                            Must Contain One Or More (Capital Letter, 
                                            Small Letter, Special Character And Integer)
                                        """;
    public static String ASK_CURRENT_USER = 
                                        """
                                            Welcome To This App! This Is The Home Page Of This App
                                            1. Personal Banking
                                            2. Register Bank.
                                            3. LogOut
                                            4. Exit
                                            Please Enter a Key!
                                        """;
    public static String ASK_CUSTOMER = 
                                        """
                                            Hello Please Follow These Options:
                                            1. Add Account
                                            2. Delete Account
                                            3. Update Account
                                            4. Banking
                                            5. Go Back
                                            6. LogOut
                                            7. Exit
                                            Please Enter a Key!
                                        """;

    public static String ASK_BANKING = 
                                        """
                                            Hello Please Follow These Options:
                                            1. Withdraw Amount
                                            2. Deposite Account
                                            3. Check Account Balance
                                            4. Print Passbook
                                            5. Take Loan
                                            6. Go Back
                                            7. Go To Home Page
                                            8. LogOut
                                            9. Exit
                                            Please Enter a Key!
                                        """;

    public static String PASSBOOK_URL = "D:\\Projects\\BankManagementSystem\\PassBooks\\";
}
