package BankManagementSystem.src;

public class Constants {
    public static String repeat = ("=").repeat(15);
    public static String errorRepeat = ("=".repeat(10))+" Error Message Ignore It "+("=".repeat(10));
    public static String askNewUser = 
                                    """
                                        Follow These Steps To Use The App!
                                        1. Sign-In
                                        2. SignUp
                                        3. Exit
                                        Please Enter a Key!
                                    """;
    public static String inValidKeyError = "Invalid Key Please Enter Valid Key!";
    public static String passwordError = 
                                        """
                                            Password Sould be 6-12 Characters Long
                                            Must Contain One Or More (Capital Letter, 
                                            Small Letter, Special Character And Integer)
                                        """;
    public static String askCurrentUser = 
                                        """
                                            Welcome To This App! How Can I Help You
                                            1. Personal Banking
                                            2. Register Bank.
                                            3. Exit
                                            Please Enter a Key!
                                        """;
    public static String askCustomer = 
                                        """
                                            Hello Please Follow These Options:
                                            1. Add Account
                                            2. Delete Account
                                            3. Update Account
                                            4. Banking
                                            5. Exit
                                            Please Enter a Key!
                                        """;

    public static String askBanking = 
                                        """
                                            Hello Please Follow These Options:
                                            1. Withdraw Amount
                                            2. Deposite Account
                                            3. Check Account Balance
                                            4. Print Passbook
                                            5. Take Loan
                                            6. Exit
                                            Please Enter a Key!
                                        """;
}
