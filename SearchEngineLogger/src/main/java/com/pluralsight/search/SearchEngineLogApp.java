package com.pluralsight.search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SearchEngineLogApp {
    public static void main(String[] args) {
        Scanner inputSc = new Scanner(System.in);

        //Log file path
        String logFilePath = "src/main/resources/logs.txt";

        //Actions
        String launchAction = "launch";
        String exitAction = "exit";
        boolean stopProgram = false;

        //Create a fileWriter
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(logFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Create a bufferWriter
        BufferedWriter bufWriter = new BufferedWriter((fileWriter));

        //Launch application and log action when starting application
        writeToLogFile(bufWriter, launchAction);

        do {
            System.out.println("Enter a search term (X to exit): ");
            String userInput = inputSc.nextLine().toLowerCase();

            if (!userInput.isEmpty() && userInput.equals("exit")) {
                stopProgram = true;
            }

            //Continue writing to file if searching for a term
            writeToLogFile(bufWriter, "search : " + userInput);

        } while (!stopProgram);

        //The final write before exiting the program
        writeToLogFile(bufWriter, exitAction);

        try {
            bufWriter.close(); //Stop bufferWriter stream
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeToLogFile(BufferedWriter bufWriter, String action) {
        try {
            //Date and time formats to be written to logFile
            LocalDateTime todaysDate = LocalDateTime.now();
            DateTimeFormatter traditionalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formatDate = todaysDate.format(traditionalDate);
            DateTimeFormatter traditionalTime = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formatTime = todaysDate.format(traditionalTime);

            //Combined formatted Date and Time
            String timeStamp = formatDate + " " + formatTime;

            //Write to file (timestamp) & (action)
            bufWriter.write(timeStamp + " ");
            bufWriter.write(action + "\n");

            //To flush out final contents to file
            bufWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
