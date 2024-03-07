package BankManagementSystem.src.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {
    
    public static String getCurrentDateAndTime() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define a custom date time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time
        String formattedDateTime = currentDateTime.format(formatter);

        // Print the formatted date and time
        return formattedDateTime;
    }
}
