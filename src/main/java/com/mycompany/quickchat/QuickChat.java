/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class QuickChat {

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
                System.out.println("---Message Menu---: choose option 1-3 \n 1: Send Messages \n 2: Show recent messages \n 3: quit");
                
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
                        default: 
                            System.out.println("Invalid Option. Please choose a digit between 1 and 3");
                    }
                }else {
                    System.out.println("Invalid input!. PLease enter only a digit e.g 1,2,etc.");
                    scan.nextLine(); // clears the invalid input from the scanner buffer
                }
            
            }
            scan.close();
        }
            //closing the scanner object
            userInput.close();
    }
}
