package BankManagementSystem.src.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean isValidPassword(String input) {
        String regex = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};:\"'<>,.?\\/])(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d!@#$%^&*()_+\\-=\\[\\]{};:\"'<>,.?\\/]{6,12}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    public static boolean isValidEmail(String email) {
        // Regular expression for email validation
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(email);

        // Perform matching
        return matcher.matches();
    }

    public static boolean isValidName(String name) {
        // Regular expression for name validation
        String regex = "^[A-Za-z\\s'-]+$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(name);

        // Perform matching
        return matcher.matches();
    }
}
