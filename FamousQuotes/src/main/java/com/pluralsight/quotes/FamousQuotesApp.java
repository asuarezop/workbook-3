package com.pluralsight.quotes;
import java.util.Scanner;

public class FamousQuotesApp {
    public static Scanner inputSc;
    public static String[] quotes;


    public static void main(String[] args) {
        inputSc = new Scanner(System.in);

        //Have to initialize an array with new String[]
        quotes = new String[] {
                "The happiness of your life depends upon the quality of your thoughts. -Marcus Aurelius", "In the middle of difficulty lies opportunity. -Albert Einstein", "Fear is not real. It is a product of thoughts you create. -Will Smith", "The first and best victory is to conquer self. -Plato", "We are what we repeatedly do. Excellence, then, is not an act, but a habit. -Aristotle", "Knowing yourself is the beginning of all wisdom. -Aristotle, The only true wisdom is in knowing you know nothing. -Socrates", "He who has a why to live can bear almost any how. -Frederich Nietzsche", "Do not dwell in the past, do not dream of the future, concentrate the mind on the present moment. -Buddha", "Life is what happens while you are busy making other plans. -John Lennon"
        };

        String userSelection;
        int parsedSelection;
        boolean exitLoop = false;

        do {
            //Prompt user to enter a number for quotes
            System.out.println("Welcome to the famous quotes app! Select a number between 1 and 10 to display a quote (digits only): ");
            userSelection = inputSc.nextLine().trim();
            parsedSelection = Integer.parseInt(userSelection);

            //If the userSelection is not empty
            if(!userSelection.isEmpty()) {
                //Call the getQuote() method to retrieve user's selected quote
               String userQuote = getQuote(parsedSelection);
               System.out.println(userQuote);
               exitLoop = true;
            }
        } while(!exitLoop);

    }

    public static String getQuote(int quoteSelection) {
        //How to get quote user selected
        //1 - Loop over array
        //2 - Have a condition to check userSelection against current index of array
        //3 - If both indexes match, return the selected Quote from the array

        for (int i = 0; i < quotes.length; i++) {
            if (i == quoteSelection) {
                return quotes[quoteSelection];
            }
        }
        return "Array index out of bounds. Cannot retrieve quote.";
    }
}
