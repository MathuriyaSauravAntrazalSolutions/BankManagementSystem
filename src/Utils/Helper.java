package BankManagementSystem.src.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import BankManagementSystem.src.Constatnts.Errors;
import BankManagementSystem.src.Operations.GetUserInput;

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


    public static int getKey(int start, int end){
        int key = -1;
        try{
            System.out.print("Key: ");
            key = Integer.parseInt(GetUserInput.getUserInput());
            if(key < start || key > end){
                System.out.println(Errors.IN_VALID_KEY_ERROR);
                getKey(start, end);
            }
        }catch(Exception e){
            System.out.println(Errors.IN_VALID_KEY_ERROR);
            getKey(start, end);
        }

        return key;
    }
}
