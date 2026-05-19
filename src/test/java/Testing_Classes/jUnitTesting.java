/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Testing_Classes;
import com.mycompany.quickchat.Login;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class jUnitTesting {
    
    Login user1 = new Login();
        Pattern SA_PHONE_PATTERN = Pattern.compile("^\\+27\\d{1,9}$");
     
    @Test
    public void testRegisterUserMethod() {   //using valid data; expecting test to be passed when using valid test data
        String validPassword = "Ch&&sec@ke99!";
        String validUserName = "kyl_1";
        String isValid = "Password successfully captured.\nUsername is successfully captured.\n";
        String checkRegisterUserMethod = user1.registerUser(validUserName, validPassword);
        
        assertEquals(isValid, checkRegisterUserMethod, "Expected valid test data for this method");
    }
    
     @Test
    public void testRegisterUserMethod2() { //using invalid data; expecting test to be passed when using invalid test data
        String inValidPassword = "password";
        String inValidUserName = "kyle!!!!!!";
        String inValid = "Password is not correctly formatted; please ensure that the password "
                + "contains at least 8 characters, a capital letter, a number and a special character.\n"
                + "Username is not correctly formatted; please ensure that the username contains an "
                + "underscore and is no more than five characters in length.\n";
        String checkRegisterUserMethod =  user1.registerUser(inValidUserName, inValidPassword);
        
        assertEquals(inValid, checkRegisterUserMethod, "Expected invalid test data for this method");
    }
    
    @Test
    public void testCheckPhoneNumberMethod(){//using valid data; expecting test to be passed
        String validCellNum = "+27838968976";
        boolean isValid = true;
        boolean checkCellPhoneNum = user1.checkCellPhoneNumber(validCellNum);
        
        assertEquals(isValid,checkCellPhoneNum,"Expected valid test data");
        assertTrue((SA_PHONE_PATTERN.matcher(validCellNum).matches())); // Testing VALID cell phone number Logic
    }
    
    @Test
    public void testCheckPhoneNumberMethod2(){ //using INVALID data; expecting test to be passed when using invalid test data.    
        String inValidCellNum = "08966553";
        boolean isValid = false;
        boolean checkCellPhoneNum = user1.checkCellPhoneNumber(inValidCellNum);
        
        assertEquals(isValid,checkCellPhoneNum,"Expected valid test data "); 
        assertFalse((SA_PHONE_PATTERN.matcher(inValidCellNum).matches()));   // Testing invalid cell phone number Logic    
    }
   
    @Test
    public void testLoginLogic2(){ //for Login successfull Logic
        String validPassword = "Ch&&sec@ke99!";
        String validUserName = "kyl_1";
        boolean checkLoginUser = user1.loginUser(validUserName, validPassword);
       
        assertFalse(checkLoginUser);
    }
    
    @Test
    public void testLoginLogic(){ //for Login failed Logic
        String inValidPassword = "password";
        String inValidUserName = "kyle!!!!!!";
        boolean checkLoginUser = user1.loginUser(inValidUserName, inValidPassword);
       
        assertFalse(checkLoginUser);
    }
    
    @Test
    public void testUserNameLogic(){ // for username CORRECTLY formatted
        String validUserName = "kyl_1";
        boolean checkUserName = user1.checkUserName(validUserName);
        
        assertTrue(checkUserName);
    }
    
    @Test
    public void testUserNameLogic2(){ // for username UNCORRECTLY formatted 
        String inValidUserName = "kyle!!!!!!";
        boolean checkUserName = user1.checkUserName(inValidUserName);
        
        assertFalse(checkUserName);
    }
    
    @Test
    public void testPasswordComplexity(){ // for pasword MEETS complexity requirements
        String validPassword = "Ch&&sec@ke99!";
        boolean checkPassword = user1.checkPasswordComplexity(validPassword);
        
        assertTrue(checkPassword);
    }
    
    @Test
    public void testPasswordComplexity2(){ // for pasword DOES NOT meet complexity requirements
        String inValidPassword = "password";
        boolean checkPassword = user1.checkPasswordComplexity(inValidPassword);
        
        assertFalse(checkPassword);
    }

    
}
