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
        QuickChat.msgIDs[0] = "1000000001";
        QuickChat.recipients[0] = "+27834557896";
        QuickChat.messages[0] = "Did you get the cake?";
      //  QuickChat.msgHashes[0] = "HASH_1";
        QuickChat.msgStatus[0] = "Sent";
        QuickChat.msgCounter++;

        // Populates dummy Message 2 
        QuickChat.msgIDs[1] = "1000000002";
        QuickChat.recipients[1] = "+27838884567"; 
        QuickChat.messages[1] = "Where are you? You are late! I have asked you to be on time.";
        QuickChat.msgHashes[1] = "10:2:WHERETIME.";
        QuickChat.msgStatus[1] = "Stored";
        QuickChat.msgCounter++;
        
        // Populates dummy Message 3
        QuickChat.msgIDs[2] = "1000000003";
        QuickChat.recipients[2] = "+27834484567";
        QuickChat.messages[2] = "Yohoooo, I am at your gate.";
        QuickChat.msgHashes[2] = "HASH_3";
        QuickChat.msgStatus[2] = "Discard";
        QuickChat.msgCounter++;
        
        // Populates dummy Message 4
        QuickChat.msgIDs[2] = "1000000004";
        QuickChat.recipients[2] = "0838884567";
        QuickChat.messages[2] = "It is dinner time !";
        QuickChat.msgHashes[2] = "HASH_3";
        QuickChat.msgStatus[2] = "Sent";
        QuickChat.msgCounter++;
        
        // Populates dummy Message 5
        QuickChat.msgIDs[2] = "1000000005";
        QuickChat.recipients[2] = "+27838884567";
        QuickChat.messages[2] = "Ok, I am leaving without you.";
        QuickChat.msgHashes[2] = "HASH_3";
        QuickChat.msgStatus[2] = "Sent";
        QuickChat.msgCounter++;
    }

    @Test
    public void testGetLongestMessage() {
        String expected = "Where are you? You are late! I have asked you to be on time.";
        String actual = QuickChat.getLongestMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchMessageByID() {
        String expected = "Message Found!\nRecipient: +27838884567\n Ok, I am leaving without you.";
        String actual = QuickChat.searchMessageByID("1000000001");
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchMessagesByRecipient() {
        String expected = "- Hello, please call me back.\n Where are you? You are late! I have asked you to be on time.";
        String actual = QuickChat.searchMessagesByRecipient("+27838884567");
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteMessageByHash() {
        String expectedResponse = "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
        String actualResponse = QuickChat.deleteMessageByHash("HASH_3");
        assertEquals(expectedResponse, actualResponse);
        assertEquals(2, QuickChat.msgCounter);
    }
}