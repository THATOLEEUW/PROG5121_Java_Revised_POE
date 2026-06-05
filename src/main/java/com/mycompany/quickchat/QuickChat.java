/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;
import java.util.Random;
import java.util.Scanner;
import java.io.File;                  
import java.io.FileNotFoundException; 

/**
 *
 * @author Student
 */
public class QuickChat {
   // max number of messages 
  public static final int MAX_MSGS = 5;
   // tracks the number of loaded messages
  public static int msgCounter = 0;
    
   // parallel arrays for displaying messages
  public static String [] msgIDs = new String[MAX_MSGS];
  public static String [] msgHashes = new String[MAX_MSGS];
  public static String [] recipients = new String[MAX_MSGS];
  public static String [] messages = new String[MAX_MSGS];
  public static String [] msgStatus = new String[MAX_MSGS];
    

    public static void main(String[] args) {
        Login user = new Login();
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("Please enter username!");
        String userName= userInput.nextLine(); 
        user.checkUserName(userName);
        
        System.out.println("Please enter password!");
        String password = userInput.nextLine();
        user.checkPasswordComplexity(password);
        
        System.out.println("Enter a south african phone number");
        String phoneNumber = userInput.nextLine();
        user.checkCellPhoneNumber(phoneNumber);
        
        System.out.println(user.registerUser(userName, password));
        user.loginUser(userName, password);
        
        String loginStatus = user.returnLoginStatus(userName, password,phoneNumber);
        
        // lets a user send a message only when they've succcessfully login
        if (!"Successful login!".equals(loginStatus)){    
            System.out.println(loginStatus);
        } else {            
            System.out.println(loginStatus);
            
            boolean running = true;
            Scanner scan = new Scanner(System.in);
            int currentMessageCounter = 0;
            
            while (running){
                System.out.println("Welcome to Quickchat.");
                System.out.println("---Message Menu---: choose option 1-4 \n 1: Send Messages \n 2: Show recent messages \n 3: quit \n 4: Stored Messages");
                
                if (scan.hasNextInt()){
                    int choice = scan.nextInt();
                    scan.nextLine(); 
                    
                    switch (choice){
                        case 1:
                            System.out.println("#Option 1 Selected: Send Messages");
                            if (msgCounter >= MAX_MSGS) {
                                System.out.println("Cannot send messages. System storage is full (" + MAX_MSGS + " max messages reached).");
                                break;
                            }

                            System.out.println("Please enter the total number of messages you wish to send");
                            int numOfMessages = scan.nextInt();
                            scan.nextLine();
                            
                            if (numOfMessages <= 0) {
                                System.out.println("Please enter a number greater than 0.");
                                break;
                            }

                            System.out.println("Enter recipient cell number:");
                            String recipientCellNum = scan.nextLine();
                            
                            for (int i = 1; i <= numOfMessages ;i++){
                                if (msgCounter >= MAX_MSGS) {
                                    System.out.println("Stopping batch early! Array limit of " + MAX_MSGS + " reached.");
                                    break;
                                }

                                System.out.println("Send messages: " + i + " of " + numOfMessages);
                                String  rawText = scan.nextLine();
                            
                                if (rawText.length()> 250){
                                    System.out.println("Please ensure that your message is not more than 250 character long");
                                    // decrements 
                                    i--;
                                    // Skips the rest of the loop and goes back to the top of the FOR loop
                                    continue;
                                } else {
                                    System.out.println("Message sent");
                                }
                              
                                StringBuilder idBuilder = new StringBuilder();
                                Random rand = new Random();
                                for (int x = 0; x < 10; x++){
                                    idBuilder.append(rand.nextInt(10));
                                }
                            
                                String generatedID = idBuilder.toString();
                                //instantiating a Message object with a parameter list
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
                                
                                    msgIDs[msgCounter] = newMessage.messageID;
                                    msgHashes[msgCounter] = newMessage.messageHash;
                                    recipients[msgCounter] = newMessage.recipient;
                                    messages[msgCounter] = newMessage.messageText;
                                    msgStatus[msgCounter] = "Sent";
                                
                                    msgCounter++;
                                }
                                currentMessageCounter++;
                            } 
                            
                            // instantaited another instance of the Message class
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
                            // loading the JSON file into the parallel arrays before the user reads the menu to manipulate messages
                            loadMessagesFromJSON();

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
                                            System.out.println("-- Sender and Recipient List --");
                                            if (msgCounter == 0) {
                                                System.out.println("No messages are currently stored.");
                                            } else {
                                                for (int i = 0; i < msgCounter; i++) {
                                                    System.out.println("Message " + (i + 1) + " | Recipient: " + recipients[i] + " | Status: " + msgStatus[i]);
                                                }
                                            }
                                            break;
                                        case 2:
                                            System.out.println("-- Longest Stored Message --");
                                            String longest = getLongestMessage();
                                            
                                            if (longest.equals("No messages are currently stored.")) {
                                                System.out.println(longest);
                                            } else {
                                                System.out.println("The longest message is:");
                                                System.out.println("\"" + longest + "\"");
                                            }
                                            break;
                                        case 3:
                                            System.out.println("-- Search by Message ID --");
                                            System.out.print("Enter the Message ID to search for: ");
                                            String searchID = scan.nextLine();
                                            System.out.println(searchMessageByID(searchID));
                                            break;
                                        case 4:
                                            System.out.println("-- Search by Recipient --");
                                            System.out.print("Enter recipient number: ");
                                            String searchNum = scan.nextLine();
                                            System.out.println(searchMessagesByRecipient(searchNum));
                                            break;
                                        case 5:
                                            System.out.println("-- Delete Message --");
                                            System.out.print("Enter the Message Hash to delete: ");
                                            String targetHash = scan.nextLine();
                                            System.out.println(deleteMessageByHash(targetHash));
                                            break;
                                        case 6:
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
                                            System.out.println("Invalid Option. Please choose 1-7!");
                                    }
                                } else {
                                    System.out.println("Invalid input! Please enter a digit.");
                                    scan.nextLine(); 
                                }
                            }
                        break; 
                        default: 
                            System.out.println("Invalid Option. Please choose a digit between from 1 and 4");
                    }
                } else {
                    System.out.println("Invalid input!. PLease enter only a digit e.g 1,2,etc.");
                    scan.nextLine(); 
                }
            }
            scan.close();
        }
        userInput.close();
    }

    //  SEPARATED PART 3 METHODS TO BE FRIENDLY WITH JUNIT TESTING 
    //-----------------------------------

    // Gets longest message
    public static String getLongestMessage() {
        if (msgCounter == 0) return "No messages are currently stored.";
        
        String longestMsg = "";
        for (int i = 0; i < msgCounter; i++) {
            if (messages[i] != null && messages[i].length() > longestMsg.length()) {
                longestMsg = messages[i];
            }
        }
        return longestMsg;
    }
    // Seaches message by ID
    public static String searchMessageByID(String searchID) {
        for (int i = 0; i < msgCounter; i++) {
            if (msgIDs[i] != null && msgIDs[i].equals(searchID)) {
                return "Message Found!\nRecipient: " + recipients[i] + "\nMessage: " + messages[i];
            }
        }
        return "No message found with ID: " + searchID;
    }
    // Searches Messages by recipient
    public static String searchMessagesByRecipient(String searchNum) {
        StringBuilder foundMessages = new StringBuilder();
        boolean found = false;

        for (int i = 0; i < msgCounter; i++) {
            if (recipients[i] != null && recipients[i].equals(searchNum)) {
                foundMessages.append("- ").append(messages[i]).append("\n");
                found = true;
            }
        }

        if (!found) {
            return "No messages found for that recipient.";
        }
        // .trim() removes the trailing invisible newline character 
        return foundMessages.toString().trim(); 
    }
    // Deletes message by hash
    public static String deleteMessageByHash(String targetHash) {
        int targetIndex = -1;
        
        //  Finding the index of the hash
        for (int i = 0; i < msgCounter; i++) {
            if (msgHashes[i] != null && msgHashes[i].equalsIgnoreCase(targetHash)) {
                targetIndex = i;
                break;
            }
        }

        if (targetIndex == -1) {
            return "Message hash not found.";
        } 
        
        String deletedText = messages[targetIndex];

        //  Shifting everything to the left
        for (int i = targetIndex; i < msgCounter - 1; i++) {
            msgIDs[i] = msgIDs[i + 1];
            msgHashes[i] = msgHashes[i + 1];
            recipients[i] = recipients[i + 1];
            messages[i] = messages[i + 1];
            msgStatus[i] = msgStatus[i + 1];
        }

        //  Clearing the duplicate at the end
        msgIDs[msgCounter - 1] = null;
        msgHashes[msgCounter - 1] = null;
        recipients[msgCounter - 1] = null;
        messages[msgCounter - 1] = null;
        msgStatus[msgCounter - 1] = null;

        // Shrinking the active tracking size
        msgCounter--;

        return "Message: \"" + deletedText + "\" successfully deleted.";
    }

    // Reads from the JSON file
    public static void loadMessagesFromJSON() {
        try {
            File myFile = new File("Messages.json");
            Scanner fileReader = new Scanner(myFile);
            
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
                    
                    msgStatus[msgCounter] = "Stored";
                    msgHashes[msgCounter] = "LOADED_MSG_" + (msgCounter + 1); 
                    msgCounter++; 
                }
            }
            fileReader.close();
            System.out.println("Successfully loaded " + msgCounter + " message(s) into memory.");
            
        } catch (FileNotFoundException e) {
            System.out.println("No existing 'Messages.json' file found. Starting with empty arrays.");
        }
    }
    // Helping method for JSON
    private static String extractJSONValue(String jsonLine) {
        String[] parts = jsonLine.split(":");
        if (parts.length > 1) {
            return parts[1].replaceAll("[\",]", "").trim();
        }
        return "";
    }
}