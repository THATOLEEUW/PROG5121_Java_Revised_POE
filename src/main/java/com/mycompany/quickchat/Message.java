/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

/**
 *
 * @author Student
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Message {
    //Data fields for tracking each messages
    public  String messageID;
    public String messageText;
    public String messageHash;
    public String recipient;
    public int messageNumber;
    
    // static variables to keep track of messages across the app
    private static int totalMessagesSent = 0;
    private static List<String> sentMessagesHistory = new ArrayList<>();
    
    // constructor
    public Message(String msgID, int msgNumber, String Recipient, String msgText){
        messageID = msgID;
        messageNumber = msgNumber;
        recipient = Recipient;
        messageText = msgText;
    }
    
    // default constructor for utility method access
    public Message(){}
    // This method ensures that the message ID is not > 10 characters.
    public boolean checkMessageID(String messageID){
        if (messageID.length()>10){
            return false;
        } else {
            return true;
        }
    }
    
    /* This message ensures that the recipient cell number is not > 10
     characters long and starts with a code. */
    public String checkRecipientCell(){
        if (recipient.length() > 10 && !recipient.startsWith("+27")){
            return "Invalid recipient cell: exceeds 10 characters or doesn't starts with +27 ";
        } else {
            return "Valid";
        }
    }
    // This method creates and returns the message Hash
    public String createMessageHash(){
        String firstTwoDigits = messageID.substring(0,2);
        String cleanedMessage = messageText.replaceAll("[^a-zA-Z0-9\\s]","");
        String [] letters = cleanedMessage.trim().split("\\s+");
        String firstLetter = letters[0].toUpperCase();
        String lastLetter = letters[letters.length-1].toUpperCase();
        
        // Format: 1st 2 numbers of ID + ":" + message Number + ":" + 1st letter + last letter
         messageHash = firstTwoDigits + ":" + messageNumber + ":" + firstLetter + lastLetter;  
        return messageHash; 
    }
    
    /* This method allows the user to choose if they want to send, store, or
    disregard the message */
    public String SentMessage(){
        Scanner scan = new Scanner(System.in);
        System.out.println("~~Message Action Menu~~ \n 1: Send Message \n 2: Discard Message "
                + "\n 3: Store message to send later \n Enter an option(1-3) below!");
        String choice = scan.nextLine();
        
        switch (choice) {
            case "1":
                totalMessagesSent ++;
                createMessageHash();
                String details = "Message ID: " + messageID + ", Message Hash: " + messageHash +
                        ", Recipient: " + recipient + ", Message: " + messageText;
                sentMessagesHistory.add(details);
                return "Message successfully sent";
            case "2":
                return "Press 0 to delete the message";
            case "3":
                storeMessage();
                return "Message successfully stored";
            default:
                return "Invalid selection";
        }
    }
    // This method returns all messages sent while the program is running.
    public String printMessage(){
        if (sentMessagesHistory.isEmpty()){
            return "No messages have been successfully sent";
        }else {
            StringBuilder sb = new StringBuilder();
            sb.append("\n ~~~ SENT Messages History~~~ \n");
            
            for (String msg: sentMessagesHistory){
                sb.append(msg).append("\n");
            }
            return sb.toString();
        }
    }
    
    // This method returns the total number of messages sent.
    public int returnTotalMessages(){
    return totalMessagesSent;
    }
    
    //This method should store the messages in JSON.
    public void storeMessage(){
        createMessageHash();
        String jsonStructure = "{\n" +
                " \"MessageID\":\""+ messageID + "\", \n" +
                " \"Message Hash\":\""+ messageHash + "\",\n" +
                " \"Recipient\":\""+ recipient + "\",\n" +
                " \"MessageText\":\""+ messageText + "\"\n"+
                "}";
        try (FileWriter file = new FileWriter("Messages.json", true)){
            file.write(jsonStructure + "\n");
        }catch (IOException e){
            System.out.println("An error occurred while saving to JSON:"+ e.getMessage());
        }     
    }
}
