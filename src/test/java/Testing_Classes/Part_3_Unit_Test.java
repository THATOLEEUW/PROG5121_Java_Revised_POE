package Testing_Classes;


import com.mycompany.quickchat.QuickChat; 
import static com.mycompany.quickchat.QuickChat.messages;
import static com.mycompany.quickchat.QuickChat.recipients;
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
        QuickChat.msgIDs[0]= "001";
        QuickChat.msgHashes[0] = "HSH1";
        QuickChat.msgCounter++;

        // Populates dummy Message 2
        QuickChat.recipients[1] = "+27838884567"; 
        QuickChat.messages[1] = "Where are you? You are late! I have asked you to be on time.";
        QuickChat.msgStatus[1] = "Stored";
        QuickChat.msgIDs[1]= "002";
        QuickChat.msgHashes[1] = "HSH2";
        QuickChat.msgCounter++;
        
        // Populates dummy Message 3
        QuickChat.recipients[2] = "+27834484567";
        QuickChat.messages[2] = "Yohoooo, I am at your gate.";
        QuickChat.msgStatus[2] = "Discard";
        QuickChat.msgIDs[2]= "003";
        QuickChat.msgHashes[2] = "HSH3";
        QuickChat.msgCounter++;
        
        // Populates dummy Message 4
        QuickChat.recipients[3] = "0838884567";
        QuickChat.messages[3] = "It is dinner time !";
        QuickChat.msgStatus[3] = "Sent";
        QuickChat.msgIDs[3]= "004";
        QuickChat.msgHashes[3] = "HSH4";
        QuickChat.msgCounter++;
        
        // Populates dummy Message 5
        QuickChat.recipients[4] = "+27838884567";
        QuickChat.messages[4] = "Ok, I am leaving without you.";       
        QuickChat.msgStatus[4] = "Sent";
        QuickChat.msgIDs[4]= "005";
        QuickChat.msgHashes[4] = "HSH5";
        QuickChat.msgCounter++;
    }
    
    @Test
    public void testGetLongestMessage() {
        String expected = QuickChat.messages[1];
        String actual = QuickChat.getLongestMessage();
        assertEquals(expected,actual);
    }

    @Test
    public void testSearchMessageByID() {
        String searchID = "004";
        String expected = "Message Found!\nRecipient: " + recipients[3] + "\nMessage: " + messages[3];
        String actual = QuickChat.searchMessageByID(searchID);
        //String actual2 = "Message Found!\nRecipient: " + recipients[3] + "\nMessage: " + messages[3];
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchMessagesByRecipient() {
        String recipientCell = "+27838884567";
        String expected = "- " + QuickChat.messages[1] +"\n" + "- " + QuickChat.messages[4];
        String actual = QuickChat.searchMessagesByRecipient(recipientCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testDeleteMessageByHash() { 
        String expected = "Message: \"" + QuickChat.messages[1] + "\" successfully deleted.";
        String actual = QuickChat.deleteMessageByHash("HSH2");
        assertEquals(expected, actual);
    }
        
}
