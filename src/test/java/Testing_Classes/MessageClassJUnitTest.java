/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Testing_Classes;
import com.mycompany.quickchat.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageClassJUnitTest {
    Message objMsg = new Message();
    
    @Test
    public void testCheckMessageLength_Success(){
        String shortMsg = "Hi Keegan, did you recieve the payment";
        String expectedOutcome = "Message ready to send";
        
    }
    
    @Test
    public void testMessageHash_TestCase1(){
        String testCaseOneData = "TestCase1Data";
        String expected = "00:0:HITONIGHT";
       // String actual = objMsg.createMessageHash(testCaseOneData);
    }
    
    
}
