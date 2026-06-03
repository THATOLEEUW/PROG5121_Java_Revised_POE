/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class QuickChat {
    // max number of messages 
   static final int MAX_MSGS = 5;
    // tracks the number of loaded messages
   static int msgCounter = 0;
    
    // parallel arrays for displaying messages
   static String [] msgIDs = new String[MAX_MSGS];
   static String [] msgHashes = new String[MAX_MSGS];
   static String [] recipients = new String[MAX_MSGS];
   static String [] messages = new String[MAX_MSGS];
   static String [] msgStatus = new String[MAX_MSGS];
    

    public static void main(String[] args) {
        Login user = new Login();
        //instantiating an instance of the Scanner class for user input 
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("Please enter username!");//prompting the user to type in their username
        String userName= userInput.nextLine(); // initializing userName to a Scanner object
        user.checkUserName(userName);
        
        System.out.println("Please enter password!");//prompting user to enter their password
        String password = userInput.nextLine();
        user.checkPasswordComplexity(password);
        
        System.out.println("Enter a south african phone number");
        String phoneNumber = userInput.nextLine();
        //calling the method, it handles both printing and returning
        user.checkCellPhoneNumber(phoneNumber);
        
        //calling the registerUser method
        System.out.println(user.registerUser(userName, password));

        //calling the loginUser method
        user.loginUser(userName, password);
        
        //calling the Login status method (returnLoginStatus) inside of a variable
          String loginStatus = user.returnLoginStatus(userName, password,phoneNumber);
          
       /* This conditional statement displays the login status of the user and
       also allows the user to send a message based on the login status. */
       
        if (!"Successful login!".equals(loginStatus)){    
            System.out.println(loginStatus);
        }else{            
            System.out.println(loginStatus);
            
            boolean running = true;
            Scanner scan = new Scanner(System.in);
             int currentMessageCounter = 0;
            
            while (running){
                System.out.println("Welcome to Quickchat.");
                //Prompting the user to choose  menu numeric options
                System.out.println("---Message Menu---: choose option 1-4 \n 1: Send Messages \n 2: Show recent messages \n 3: quit \n 4: Stored Messages");
                
                //validation to ensure user enteres an integer value
                if (scan.hasNextInt()){
                    int choice = scan.nextInt();
                    scan.nextLine(); // discard the leftover newline character
                    
                    switch (choice){
                        case 1:
                            System.out.println("#Option 1 Selected: Send Messages");
                            System.out.println("Please enter the total number of messages you wish to send");
                            int numOfMessages = scan.nextInt();
                            // Clears the leftover Enter space(key) from the buffer
                            scan.nextLine();
                            
                            if (currentMessageCounter >= numOfMessages){
                                System.out.println("Max message limit reached: " + numOfMessages + " is the specified limit.");
                            break;
                            }
                            //propmting user to enter recipient cell number
                            System.out.println("Enter recipient cell number:");
                            String recipientCellNum = scan.nextLine();
                            //System.out.println("Enter your message below.");
                           // String rawText = scan.nextLine();
                            // this for loop allows a user to enter the number of messages they'll send
                            for (int i = 1; i <= numOfMessages ;i++){
                                System.out.println("Send messages: " + i + " of " + numOfMessages);
                                String  rawText = scan.nextLine();
                            
                              if (rawText.length()> 250){
                                    System.out.println("Please ensure that your message is not more than 250 character long");
                                    break;
                                }else {
                                    
                                    System.out.println("Message sent");
                                }
                              
                            // 10 auto generated digits
                            StringBuilder idBuilder = new StringBuilder();
                            Random rand = new Random();
                            for (int x = 0; x < 10; x++){
                                idBuilder.append(rand.nextInt(10));
                            }
                            
                            //assigning the random 10 digits to a variable called ID
                            String generatedID = idBuilder.toString();
                            //Message class object
                            Message newMessage = new Message(generatedID, currentMessageCounter,recipientCellNum, rawText);
                            
                            if (!newMessage.checkMessageID(generatedID)){
                                System.out.println("Invalid message ID format");
                                break;
                            }
                            
                            String cellValidation = newMessage.checkRecipientCell();
                            if (!cellValidation.equals("Valid")){
                                System.out.println(cellValidation);
                                break;
                            }
                            
                            String actionResult = newMessage.SentMessage();
                            System.out.println(actionResult);
                            if (actionResult.equals("Message successfully sent")){
                                System.out.println("**Outbound Descriptors**"+ '\n'+ "Message ID: " + newMessage.messageID);
                                System.out.println("Message Hash: " + newMessage.messageHash);
                                System.out.println( "Recipient: " + newMessage.recipient);
                                System.out.println("Message: " + newMessage.messageText);
                                
                                // ### NEW: Saving the data to Arrays ###
                                msgIDs[msgCounter] = newMessage.messageID;
                                msgHashes[msgCounter] = newMessage.messageHash;
                                recipients[msgCounter] = newMessage.recipient;
                                messages[msgCounter] = newMessage.messageText;
                                msgStatus[msgCounter] = "Sent";
                                
                                //incrementing the global counter so the next message goes to the next slot
                                msgCounter = msgCounter +1;
                            }
                            //incrementing currentMessageCounter
                            currentMessageCounter++;
                            } // #################### Closing TAG of For loop
                            
                            // Message object for utility usage
                            Message msgUtilityObj = new Message();
                            System.out.println("______________________________");
                            System.out.println("Total messages sent during runtime: "+ msgUtilityObj.returnTotalMessages());
                            System.out.println(msgUtilityObj.printMessage());
                            break;
                        case 2:
                            System.out.println("Coming Soon!");
                            break;
                        case 3:
                            System.out.println("#Option 3 Selected: Application Exited");
                            running = false;
                            break;
                        case 4:
                            // ### newly added features for part 3
                            System.out.println("#Option 4 Selected: Stored Messages Menu");
                            boolean storedMenuRunning = true;

                            while (storedMenuRunning) {
                                System.out.println("---Stored Messages Menu---:");
                                System.out.println("1: Display sender and recipient of all stored messages");
                                System.out.println("2: Display the longest stored message");
                                System.out.println("3: Search for a message by ID");
                                System.out.println("4: Search for messages by recipient number");
                                System.out.println("5: Delete a message using its hash");
                                System.out.println("6: Display full message report");
                                System.out.println("7: Return to Main Menu");

                                System.out.print("Enter your choice: ");

                                if (scan.hasNextInt()) {
                                    int subChoice = scan.nextInt();
                                    scan.nextLine(); // Clearing buffer

                                    switch (subChoice) {
                                        case 1:
                                            // Logic for displaying sender/recipient
                                            System.out.println("-- Sender and Recipient List --");
                                            if (msgCounter == 0) {
                                                System.out.println("No messages are currently stored.");
                                            } else {
                                                for (int i = 0; i < msgCounter; i++) {
                                                    // Displaying the recipient for every logged message
                                                    System.out.println("Message " + (i + 1) + " | Recipient: " + recipients[i] + " | Status: " + msgStatus[i]);
                                                }
                                            }
                                            break;
                                        case 2:
                                            // Logic for longest message
                                            System.out.println("-- Longest Stored Message --");
                                            if (msgCounter == 0) {
                                                System.out.println("No messages are currently stored.");
                                            } else {
                                                String longestMsg = "";

                                                // Loop through all saved messages to compare lengths
                                                for (int i = 0; i < msgCounter; i++) {
                                                    if (messages[i] != null && messages[i].length() > longestMsg.length()) {
                                                        longestMsg = messages[i];
                                                    }
                                                }
                                                System.out.println("The longest message is:");
                                                System.out.println("\"" + longestMsg + "\"");
                                            }
                                            break;
                                        case 3:
                                            // Logic for searching by ID
                                            System.out.println("-- Search by Message ID --");
                                            System.out.println("Enter the Message ID to search for: ");
                                            String searchID = scan.nextLine();
                                            boolean foundID = false;

                                            for (int i = 0; i < msgCounter; i++) {
                                                if (msgIDs[i] != null && msgIDs[i].equals(searchID)) {
                                                    System.out.println("Message Found!");
                                                    System.out.println("Recipient: " + recipients[i]);
                                                    System.out.println("Message: " + messages[i]);
                                                    foundID = true;
                                                    break; // Stop searching once found
                                                }
                                            }
                                            if (!foundID) {
                                                System.out.println("No message found with ID: " + searchID);
                                            }
                                            break;
                                        case 4:
                                            // Logic for searching by recipient
                                            System.out.println("-- Search by Recipient --");
                                            System.out.println("Enter recipient number: ");
                                            String searchNum = scan.nextLine();
                                            boolean foundRecip = false;

                                            for (int i = 0; i < msgCounter; i++) {
                                                if (recipients[i] != null && recipients[i].equals(searchNum)) {
                                                    // Return the message text
                                                    System.out.println("- " + messages[i]); 
                                                    foundRecip = true;
                                                }
                                            }

                                            if (!foundRecip) {
                                                System.out.println("No messages found for that recipient.");
                                            }
                                            break;
                                        case 5:
                                            // Logic for deleting by hash
                                            System.out.println("-- Delete Message --");
                                            System.out.println("Enter the Message Hash to delete: ");
                                            
                                            String targetHash = scan.nextLine();
                                            int targetIndex = -1;

                                            // 1. Finding the index of the hash
                                            for (int i = 0; i < msgCounter; i++) {
                                                if (msgHashes[i] != null && msgHashes[i].equalsIgnoreCase(targetHash)) {
                                                    targetIndex = i;
                                                    break;
                                                }
                                            }

                                            if (targetIndex == -1) {
                                                System.out.println("Message hash not found.");
                                            } else {
                                                // Save text before overwriting so we can print the exact required confirmation
                                                String deletedText = messages[targetIndex];

                                                // 2. Shifting everything to the left
                                                for (int i = targetIndex; i < msgCounter - 1; i++) {
                                                    msgIDs[i] = msgIDs[i + 1];
                                                    msgHashes[i] = msgHashes[i + 1];
                                                    recipients[i] = recipients[i + 1];
                                                    messages[i] = messages[i + 1];
                                                    msgStatus[i] = msgStatus[i + 1];
                                                }

                                                // 3. Clearing the duplicate at the end
                                                msgIDs[msgCounter - 1] = null;
                                                msgHashes[msgCounter - 1] = null;
                                                recipients[msgCounter - 1] = null;
                                                messages[msgCounter - 1] = null;
                                                msgStatus[msgCounter - 1] = null;

                                                // 4. Shrinking the active tracking size: decrements
                                                msgCounter--;

                                                System.out.println("Message: \"" + deletedText + "\" successfully deleted.");
                                            }
                                            break;
                                        case 6:
                                            // Logic for full report
                                            System.out.println("-- Full Message Report --");
                                            if (msgCounter == 0) {
                                                System.out.println("No messages currently in the system.");
                                            } else {
                                                for (int i = 0; i < msgCounter; i++) {
                                                    System.out.println("Message " + (i + 1) + ":");
                                                    System.out.println("Message Hash: " + msgHashes[i]);
                                                    System.out.println("Recipient: " + recipients[i]);
                                                    System.out.println("Message: " + messages[i]);
                                                    System.out.println("-------------------------");
                                                }
                                            }
                                            break;
                                        case 7:
                                            System.out.println("Returning to Main Menu");
                                            storedMenuRunning = false;
                                            break;
                                        default:
                                            System.out.println("Invalid Option. Please choose 1-7.");
                                    }
                                } else {
                                    System.out.println("Invalid input! Please enter a digit.");
                                    scan.nextLine(); // Clear buffer
                                }
                            }
                        break; // End of case 4
                        default: 
                            System.out.println("Invalid Option. Please choose a digit between from 1 and 4");
                    }
                }else {
                    System.out.println("Invalid input!. PLease enter only a digit e.g 1,2,etc.");
                    scan.nextLine(); // clears the invalid input from the scanner buffer
                }
            }
            scan.close();
        }
            //closing the userInput scanner object
            userInput.close();
    }
    // reading JSON file void method
    public static void loadMessagesFromJSON() {
        try {
            
            File myFile = new File("Messages.json");
            Scanner fileReader = new Scanner(myFile);
            
            // Clear current memory counter before loading from file
            msgCounter = 0;
            System.out.println("\n--- Loading Stored Data from Messages.json ---");
            
            while (fileReader.hasNextLine() && msgCounter < MAX_MSGS) {
                String line = fileReader.nextLine();
                
                if (line.contains("\"MessageID\"")) {
                    msgIDs[msgCounter] = extractJSONValue(line);
                } else if (line.contains("\"Recipient\"")) {
                    recipients[msgCounter] = extractJSONValue(line);
                } else if (line.contains("\"MessageText\"")) {
                    messages[msgCounter] = extractJSONValue(line);
                    
                    // Since hash and status aren't saved in the JSON file, 
                    // it populates them automatically upon loading so the menus don't crash.
                    msgStatus[msgCounter] = "Stored";
                    msgHashes[msgCounter] = "LOADED_MSG_" + (msgCounter + 1); 
                    
                    // Move to the next index slot in your parallel arrays
                    msgCounter++; 
                }
            }
            fileReader.close();
            System.out.println("Successfully loaded " + msgCounter + " message(s) into memory.");
            
        } catch (FileNotFoundException e) {
            System.out.println("No existing 'Messages.json' file found. Starting with empty arrays.");
        }
    }

    // Helper method to cleanly extract the values between quotes
    private static String extractJSONValue(String jsonLine) {
        String[] parts = jsonLine.split(":");
        if (parts.length > 1) {
            return parts[1].replaceAll("[\",]", "").trim();
        }
        return "";
    }
}
