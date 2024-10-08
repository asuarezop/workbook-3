package com.pluralsight.stories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class BedtimeStoriesApp {
    public static void main(String[] args) {
        //Get userInput to define what story they'd like to read
        Scanner inputSc = new Scanner(System.in);
        String storyPrompt = """
                Welcome to Bedtime Stories! Enter the story you'd like to read:
                A. Goldilocks
                B. Hansel and Gretel
                C. Mary Had a Little Lamb
                """;

        //Prompt user to enter a story
        System.out.println(storyPrompt);
        String userInput = inputSc.nextLine().trim();

        //Control flow for user story selection
        try {
            switch(userInput) {
                //Read Goldilocks
                case "A", "a":
                    readStory("goldilocks.txt");
                    break;
                //Read Hansel and Gretel
                case "B", "b":
                    readStory("hansel_and_gretel.txt");
                    break;
                //Read Mary Had A Little Lamb
                case "C", "c":
                    readStory("mary_had_a_little_lamb.txt");
                    break;
                default:
                    System.out.println("Sorry, that's not a valid story.");
            }
        } catch (IOException e) { //Display stack trace if there was an error
            e.printStackTrace();
        }
    }

    private static void readStory(String file) throws FileNotFoundException {
        //Declaring a new FileInputStream and reading it from the Scanner
        FileInputStream fis = new FileInputStream("src/main/resources/" + file);
        Scanner inputSc = new Scanner(fis);

        //Reading from the scanner input and outputting contents to the console
        while (inputSc.hasNextLine()) {
            String fileInput = inputSc.nextLine();
            System.out.println(fileInput);
        }
        //Close the current scanner from file we're reading
        inputSc.close();
    }
}
