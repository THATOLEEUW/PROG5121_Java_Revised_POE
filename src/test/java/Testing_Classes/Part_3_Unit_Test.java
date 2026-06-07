package Testing_Classes;

import com.mycompany.quickchat.QuickChat; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Student
 */
public class Part_3_Unit_Test {

    // This method runs automatically before EVERY test to reset and populate the arrays
    @BeforeEach
    public void setUp() {
        // Resets the global counter
        QuickChat.msgCounter = 0;
        
        // Clears out any old data to prevent test overlap
        for(int i = 0; i < QuickChat.MAX_MSGS; i++) {
            QuickChat.msgIDs[i] = null;
            QuickChat.msgHashes[i] = null;
            QuickChat.recipients[i] = null;
            QuickChat.messages[i] = null;
            QuickChat.msgStatus[i] = null;
        }

        // Populates dummy Message 1
        QuickChat.recipients[0] = "+27834557896";
        QuickChat.messages[0] = "Did you get the cake?";
        QuickChat.msgStatus[0] = "Sent";
        QuickChat.msgCounter++;

        // Populates dummy Message 2
        QuickChat.recipients[1] = "+27838884567"; 
        QuickChat.messages[1] = "Where are you? You are late! I have asked you to be on time.";
        QuickChat.msgStatus[1] = "Stored";
        QuickChat.msgCounter++;
        
        // Populates dummy Message 3
        QuickChat.recipients[2] = "+27834484567";
        QuickChat.messages[2] = "Yohoooo, I am at your gate.";
        QuickChat.msgStatus[2] = "Discard";
        QuickChat.msgCounter++;
        
        // Populates dummy Message 4
        QuickChat.recipients[3] = "0838884567";
        QuickChat.messages[3] = "It is dinner time !";
        QuickChat.msgStatus[3] = "Sent";
        QuickChat.msgCounter++;
        
        // Populates dummy Message 5
        QuickChat.recipients[4] = "+27838884567";
        QuickChat.messages[4] = "Ok, I am leaving without you.";       
        QuickChat.msgStatus[4] = "Sent";
        QuickChat.msgCounter++;
    }

    
    
    @Test
    public void testGetLongestMessage() {
        String expected = QuickChat.messages[1];
        String actual = QuickChat.getLongestMessage();
        assertEquals(actual,expected);
    }

    @Test
    public void testSearchMessageByID() {
        setUp();
        String searchID = "0838884567";
        String expected = QuickChat.recipients[3];
        String actual = QuickChat.searchMessageByID(searchID);
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchMessagesByRecipient() {
        
        String expected = QuickChat.messages[1];
        String actual = QuickChat.searchMessagesByRecipient("+27838884567");
        assertEquals(expected, actual);
    }
    
    @Test
    public void testDeleteMessageByHash() {
        String expectedResponse = QuickChat.messages[1];
        String actualResponse = QuickChat.deleteMessageByHash("");
        assertEquals(expectedResponse, actualResponse);
    }
    
    
}