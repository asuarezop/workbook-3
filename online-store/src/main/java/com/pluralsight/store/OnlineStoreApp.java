package com.pluralsight.store;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlineStoreApp {

    public static Scanner inputSc;

    public static String userInput;
    public static boolean exitMenu;

    public static ArrayList<Product> productsList;
    public static ArrayList<String> shoppingCart;

    public static void main(String[] args) {
        inputSc = new Scanner(System.in);

        String productsFilePath = "src/main/resources/products.csv";

        //ArrayList to hold all our products from csv file
        productsList = new ArrayList<>();

        //ArrayList to hold user's current products in their cart
        shoppingCart = new ArrayList<>();

        try {
            //Have to read products.csv file and load it into products ArrayList
            productsList = readProducts(productsFilePath);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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
                |                               2. Show Cart                                         |
                |                                                                                    |
                |                               Hit Q to exit menu                                   |
                ======================================================================================        
                """;

        do {
            System.out.print(homeScreen + "Selection 1 or 2? : ");
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

    private static void showDisplayProductsScreen() {
        exitMenu = false;
        String productsScreen = """
                 ======================================================================================
                 |                       * * * WELCOME TO THE E-STORE * * *                           |
                 |                                                                                    |
                 |                                - Product Menu -                                    |
                 |                                                                                    |
                 |                              1. Search Products                                    |
                 |                              2. Add a Product To Cart                              |
                 |                                                                                    |
                 |             Hit Q to exit menu        |        Hit X to go back to Home Screen     |
                 ======================================================================================
                """;

        do {
            //Show all list of products that store is selling
            for (Product p: productsList) {
                System.out.println("SKU: " + p.getSku() + " Name: " + p.getProductName() + " Price: " + p.getPrice() + " Department: " + p.getProductDepartment());
            }

            System.out.print(productsScreen + "Selection 1 or 2? : ");
            userInput = inputSc.nextLine().trim();

            switch (userInput) {
                case "1":
                    searchProductFromCart(productsList);
                    break;
                case "2":
                    addProductToCart();
                    break;
                case "X", "x":
                    showHomeScreen();
                    break;
                case "Q", "q":
                    exitMenu = true;
                    break;
                default:
                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
            }
        } while (!exitMenu);
    }

    private static void showDisplayCartScreen() {
        exitMenu = false;
        String cartScreen = """
                 ======================================================================================
                 |                       * * * WELCOME TO THE E-STORE * * *                           |
                 |                                                                                    |
                 |                                - Cart Menu -                                       |
                 |                                                                                    |
                 |                              1. Check Out                                          |
                 |                              2. Remove a Product To Cart                           |
                 |                                                                                    |
                 |             Hit Q to exit menu        |        Hit X to go back to Home Screen     |
                 ======================================================================================
                """;

        do {
            System.out.print(cartScreen + "Selection 1 or 2? :");
            userInput = inputSc.nextLine().trim();

            switch (userInput) {
                case "1":
                    checkOut();
                    break;
                case "2":
                    removeProductFromCart();
                    break;
                case "X", "x":
                    showHomeScreen();
                    break;
                case "Q", "q":
                    exitMenu = true;
                    break;
                default:
                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
            }
        } while(!exitMenu);
    }

    //Should be methods on Product class ?
    private static void searchProductFromCart(ArrayList<Product> products) {
        String searchTerm;
        System.out.println("Enter the product name you'd like to search: ");
        searchTerm = inputSc.nextLine().trim().toLowerCase();

        //Search with subString doesn't work
//        for (int i = 0; i < products.size(); i++) {
//            if (searchTerm.substring(0, searchTerm.length() - 1).equals(products.get(i))) {
//                System.out.println(products.get(i));
//            }
//        }

        //If the searchTerm string matches any of the product names, return only that product
        if (!searchTerm.isEmpty()) {
            for (Product p: products) {
                if (searchTerm.equalsIgnoreCase(p.getProductName())) {
                    System.out.println("Search product result: " + p.getSku() + "|" + p.getProductName() + "|" + p.getPrice() + "|" + p.getProductDepartment());
                    break;
                }
            }
        }
    }

    private static void addProductToCart() {

    }

    private static void removeProductFromCart() {
    }

    private static void checkOut() {
    }

    //Methods to read products.csv file
    private static ArrayList<Product> readProducts(String filename) throws FileNotFoundException {
        Product p;

        ArrayList<Product> products = new ArrayList<>();

        try {
            //Calling openFileReader method to initialize BufferedReader
            BufferedReader bufReader = openFileReader(filename);

            //Reading each line of input from fileContents
            String fileContents;

            //Skip the first line of the file
            bufReader.readLine();

            while ((fileContents = bufReader.readLine()) != null) {
                //Storing file contents as an array of objects, splitting contents at |
                String[] productsData = fileContents.split("\\|");

                //To store values from fileContents and assigning their values to product variables
                String productSku = productsData[0];
                String productName = productsData[1];
                double productPrice = Double.parseDouble(productsData[2]);
                String productDept = productsData[3];

                //Creating a new product object and passing product variables to constructor
                p = new Product(productSku, productName, productPrice, productDept);

                //Adding each product to the products ArrayList
                products.add(p);
            }

            //Printing success message when file is done being read
            System.out.println("File was successfully read!");

            //Closing bufReader
            bufReader.close();

            //Return ArrayList of products
            return products;

        } catch (IOException err) {
            throw new RuntimeException(err);
        }
    }

    private static BufferedReader openFileReader(String filename) throws FileNotFoundException {
        //Creating a new BufferedReader object to read "products.csv" and initializing it to read contents from FileReader
        BufferedReader bufReader = new BufferedReader(new FileReader(filename));
        return bufReader;
    }
}

