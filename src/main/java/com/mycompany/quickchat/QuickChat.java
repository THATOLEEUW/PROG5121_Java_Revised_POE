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


        //calling the returnLoginStatus
        System.out.println(user.returnLoginStatus(userName, password));
        
            //closing the scanner object
            userInput.close();
    }
}
