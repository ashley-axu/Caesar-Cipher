/* 
A brief summary: A Caesar cipher is a simple approach to encoding messages by shifting each letter in a message along the alphabet by a constant amount k. 
                This project is a program that asks user to input some random constant as well as a string to be encoded. 
                Then it returns the encoded message according to the constant user gives and decode
                that encoded message. 
                At the end of the program, user can enter 'y' to play again, otherwise, 'n' to quit. 
Authors: 1. Paul Sining Lu; sininglu@sandiego.edu 
         2. Ashley; axu@sandiego.edu 

Last Date Modified: 09/24/2021 
*/ 

import java.util.Arrays; 
import java.util.Scanner; 

public class proj1 { 
    public static void main(String[] args) { 

    outerLoop: 
    while (true){ 
        String keyValues, encodedMsg, decision; 
        // make 3 vars: keyV: to store user's keys, 
        // encodedMsg: to store user's string 
        // decision: to store user's decision, i.e. y for yes, n for no 

        Scanner scanner = new Scanner(System.in); 

        // Asking the user to input a list of key values 
        System.out.println("\n"); 
        System.out.println("Enter the individual key values (positive or negative integers," +
        " one after another in the same line with a blank between two values):"); 

        keyValues = scanner.nextLine(); 
        String[] keyInput = keyValues.split(" ");// split the string into a string[] by space 
        int[] keys = new int[keyInput.length]; // make an int[] with the same length of string[] 

        // try to convert String[] from int[], if fails, ask user retype. 
        try { 
            for (int i=0; i<keys.length; i++){ 
                keys[i] = Integer.parseInt(keyInput[i]); // convert each key from string to int 
            } 
        } 
        catch (NumberFormatException e){ 
            System.out.println("Oops! Invalid number detected! Please try again."); 
            continue; // user needs to retype again 
        } 

        // asking the user to input a sentence needs to be encoded. 
        System.out.println("\n");
        System.out.println("Enter a string to be encoded:"); 
        encodedMsg = scanner.nextLine(); 

        final int asciiRange = 95; // (=126-32+1) range from 32 to 126; 

        char[] msg = new char[encodedMsg.length()]; // convert string into char[] 
        for (int i=0; i<encodedMsg.length(); i++){ 
            msg[i] = encodedMsg.charAt(i); // keys = [1,2,3] encodedMsg = ['h' 'e' 'l' 'l' 'o'] 

            int curReversedKey = keys[i%keys.length]; // get the corresponding key position 
            int curAsc = msg[i]; // convert the current char into ascii value 
            int totalAsc = curAsc + curReversedKey; // add up the converted ascii value 

            // we use if and while loop to prevent ascii value over the range (either too small or too large) 
            if (totalAsc > 126){ 
                while (totalAsc > 126){ 
                    totalAsc -= asciiRange; 
                } 
            } 

            if (totalAsc < 32){ 
                while (totalAsc<32) { 
                    totalAsc += asciiRange; 
                } 
            } 

            char newChar = (char) (totalAsc); // convert from new ascii to new char 
            msg[i] = newChar; 
        } 

        // display the encoded message 
        System.out.println("\n"); 
        System.out.println("The encoded message:"); 

        String eMsg = new String(msg); 
        System.out.println(eMsg); 

        // display the decoded message 
        System.out.println("\n"); 
        System.out.println("The decoded message:"); 
        for (int i=0; i<msg.length; i++){ 

            // doing the same step as we did in encoding except for the key value needed to be negative 
            int curReversedKey = -(keys[i%keys.length]); 
            int curAsc = msg[i]; 
            int totalAsc = curAsc + curReversedKey;
            if (totalAsc < 32){ 
                while (totalAsc<32) { 
                    totalAsc += asciiRange; 
                } 
            } 
            if (totalAsc > 126){ 
                while (totalAsc > 126){ 
                    totalAsc -= asciiRange; 
                } 
            } 

            char newChar = (char) (totalAsc); 

            msg[i] = newChar; 
        } 

        encodedMsg = new String(msg); // convert it back to original msg. 
        System.out.println(encodedMsg); 

        // making a nested while loop in case that user doesn't type 'y' or 'n', we continue ask the user. 
        while(true) { 
            // ask the user if he/she wants to play again 
            System.out.println("\n"); 
            System.out.println("Do you want to run the program again (y for yes and n for no)?:"); 
            decision = scanner.next(); 

            if (decision.equals("n")) { 
                break outerLoop; 
            } else if (decision.equals("y")) { 
                break; 
            } 
            else { 
                System.out.println("Invalid Command, please type: y for yes and n for no"); 
                } 
            } 
        } 
    } 
}
