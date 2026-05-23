/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;

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
            
            while (running){
                //Prompting the user to choose  menu numeric options
                System.out.println("---Message Menu---: choose option 1-3 \n 1: Send Messages \n 2: Show recent messages \n 3: quit");
                
                //validation to ensure user enteres an integer value
                if (scan.hasNextInt()){
                    int choice = scan.nextInt();
                    scan.nextLine(); // discard the leftover newline character
                    
                    switch (choice){
                        case 1:
                            System.out.println("#Option 1 Selected: Send Messages");
                            // insert method call here!
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
        }
            //closing the scanner object
            userInput.close();
    }
}
