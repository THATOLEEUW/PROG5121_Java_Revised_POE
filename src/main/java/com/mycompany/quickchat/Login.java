/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;
import java.util.regex.Pattern;
/**
 *
 * @author Student
 */
public class Login {
    //data fields to hold registered password and username
    private String registeredUsername;
    private String registerdPassword;
    
      public boolean checkUserName(String userName){
            if (!(userName.contains("_") && userName.length()<=5)){
                return false;
            } else{
                return true;
            }        
        }
        
       public boolean checkPasswordComplexity(String password){
             //complexity rules
            boolean hasUpperCase = false;
            boolean hasNumber = false;
            boolean hasSpecialChar = false;
            boolean minLength = false;
            //validity checker variable
            boolean isValid = false;
            //nested if
            if (!(password == null || password.isEmpty())){
                //conditional statement to check password length
                if (!(password.length()>= 8)){
                    minLength = false;
                } else{
                    minLength = true;
                     // for loop to check each element of the entered input (password)
                       for (char x: password.toCharArray()){
                            if (Character.isUpperCase(x)){
                                hasUpperCase = true;
                               // isValid = true;
                            }
                            if (Character.isDigit(x)){
                                hasNumber = true;
                               // isValid = true;
                            }
                            if (!Character.isLetterOrDigit(x)){
                                hasSpecialChar = true;
                               // isValid = true;
                            }                       
                       }
                       isValid = hasUpperCase && hasNumber && hasSpecialChar && minLength;
                    }   
            }
            return isValid;
        }
        
/* A validation method was suggested by Microsoft Copilot (2026) to validate the
   the South African cell phone numbers that start with a +27 and with exact 9
   digits in length.*/
        // Regex: ^\+27\d{1,9}$
        private static final Pattern SA_PHONE_PATTERN = Pattern.compile("^\\+27\\d{1,9}$");
        //method prints messages and returns boolean
      public boolean checkCellPhoneNumber(String phoneNumber){
            if (SA_PHONE_PATTERN.matcher(phoneNumber).matches()) {
            System.out.println("Cell phone number successfully added.");
            return true;
        } else{
                System.out.println("Cell phone number incorrectly formated or does not contain international code(+27)");
                return false;
            }
        }
        
        public String registerUser(String userName, String password){
            StringBuilder feedback = new StringBuilder();
            //checks password
           if (checkPasswordComplexity(password)){
            registerdPassword = password;
            feedback.append("Password successfully captured.\n");
        } else{
            feedback.append("Password is not correctly formatted; please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.\n");
        }
           //checks username
           if (checkUserName(userName)){
            registeredUsername = userName;
              feedback.append("Username is successfully captured.\n");
           }else{
               feedback.append("Username is not correctly formatted; please ensure that the username contains an underscore and is no more than five characters in length.\n");
           }
        
           return feedback.toString();
        }

      public boolean loginUser(String userName, String password){
            if (registerdPassword == null && registeredUsername == null){
            return false;
            } else{
                return password.equals(registerdPassword) && userName.equals(registeredUsername);
            }
        }

      public String returnLoginStatus(String userName, String password){
            boolean isSuccessLogin = loginUser(userName, password);

            if (isSuccessLogin){
                return "Successful login!";
            }else{
            return "Unsuccessful login";
            }
        }

}
