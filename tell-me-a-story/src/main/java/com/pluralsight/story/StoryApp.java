package com.pluralsight.story;

import java.io.FileInputStream;
import java.util.Scanner;

public class StoryApp {

    FileInputStream fis = new FileInputStream("/src/main/resources/goldilocks.txt");
    Scanner scanner = new Scanner(fis);

    while (FileInputStream.hasNextLine()) {
        String input = fileScanner.nextLine();
        System.out.println(input);
    }
}
