package com.pluralsight.store;

import java.util.Scanner;

public class OnlineStoreApp {

    public static Scanner inputSc;

    public static String userInput;
    public static boolean exitMenu;

    public static void main(String[] args) {
        inputSc = new Scanner(System.in);
        //Cart is an array of product objects (only)

        //Calling showHomeScreen to display Home Screen
        showHomeScreen();
    }

    //Methods for displaying screens
    private static void showHomeScreen() {
        exitMenu = false;
        String homeScreen = """
                    ======================================================================================
                    |                       * * * WELCOME TO THE E-STORE * * *                           |
                    |                                                                                    |
                    |                      -  What would you like to do today?                           |
                    |                                                                                    |
                    |                               1. Show Products                                     |
                    |                               2. Show Carts                                        |
                    |                                                                                    |
                    |                               Hit Q to exit menu                                   |
                    ======================================================================================        
                    """;

        do {
            System.out.print("Selection 1 or 2? : ");
            userInput = inputSc.nextLine().trim();

            switch (userInput) {
                case "1":
                    showDisplayProductsScreen();
                    break;
                case "2":
                    showDisplayCartScreen();
                    break;
                case "Q", "q":
                    exitMenu = true;
                    break;
                default:
                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
            }

        } while (!exitMenu);
    }
    private static void showDisplayProductsScreen(){}
    private static void showDisplayCartScreen(){}


    private static void removeProductFromCart(){}
    private static void addProductFromCart(){}
}
