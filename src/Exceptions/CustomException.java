package BankManagementSystem.src.Exceptions;

/*
*********************************************************************************************************
 *  @Java Class Name :   CustomException
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This class is used to handle custom exceptions from user
 * 
 ********************************************************************************************************
*/
public class CustomException extends Exception {
    
    /*
    *********************************************************
     *  @Method Name    :   CustomException
     *  @Author         :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   This function is the constructor of the CustomException class.
     *  @param          :   (String message)
     *  @return         :   CustomException Object            
    *********************************************************
    */
    public CustomException(String message) {
        super(message);
    }
}
