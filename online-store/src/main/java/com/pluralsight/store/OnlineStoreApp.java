package com.pluralsight.store;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimeZone;

public class OnlineStoreApp {

    public static Scanner inputSc;

    public static String userInput;
    public static boolean exitMenu;

    public static ArrayList<Product> productsList;
    public static ArrayList<Product> shoppingCart;

    public static String escapeKey = "\033";
    public static String italicText = escapeKey + "[3m";
    public static String resetText = escapeKey + "[23m";
    public static String productSkuColor = escapeKey + "[38;5;12m";
    public static String productNameColor = escapeKey + "[38;5;50m";
    public static String productPriceColor = escapeKey + "[38;5;220m";
    public static String productDeptColor = escapeKey + "[38;5;170m";
    public static String successActionColor = escapeKey + "[38;5;46m";
    public static String allScreensColor = escapeKey + "[38;5;153m";
    public static String promptTextColor = escapeKey + "[38;5;231m";

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
            System.out.print(allScreensColor + homeScreen + "Selection 1 or 2? : ");
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
            //Showing full list of products that the store sells
            for (Product p : productsList) {
                System.out.println(productSkuColor + "SKU: " + p.getSku() + productNameColor + " Name: " + p.getProductName() + productPriceColor + " Price: " + p.getPrice() + productDeptColor + " Department: " + p.getProductDepartment());
            }

            System.out.print(allScreensColor + productsScreen + "Selection 1 or 2? : ");
            userInput = inputSc.nextLine().trim();

            switch (userInput) {
                case "1":
                    searchProductFromCart(productsList);
                    break;
                case "2":
                    addProductToCart(productsList);
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
            //Displaying all products from user's cart
            for (Product p : shoppingCart) {
                System.out.println(productSkuColor + "SKU: " + p.getSku() + productNameColor + " Name: " + p.getProductName() + productPriceColor + " Price: " + p.getPrice() + productDeptColor + " Department: " + p.getProductDepartment());
            }

            System.out.print(allScreensColor + cartScreen + "Selection 1 or 2? : ");
            userInput = inputSc.nextLine().trim();

            switch (userInput) {
                case "1":
                    checkOut(shoppingCart);
                    break;
                case "2":
                    removeProductFromCart(productsList);
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

    //Action methods
    private static void searchProductFromCart(ArrayList<Product> products) {
        String searchTerm;
        System.out.println(promptTextColor + "Enter the product name you'd like to search: ");
        searchTerm = inputSc.nextLine().trim().toLowerCase();

        //If the searchTerm string matches any of the product names, return only that product
        if (!searchTerm.isEmpty()) {
            for (Product p : products) {
                if (searchTerm.equalsIgnoreCase(p.getProductName())) {
                    System.out.println(successActionColor + italicText + "Search product result: " + resetText + p.getSku() + "|" + p.getProductName() + "|" + p.getPrice() + "|" + p.getProductDepartment());
                }
            }
        }
    }

    private static void addProductToCart(ArrayList<Product> products) {
        //Get user input to ask for product
        System.out.println(promptTextColor + "What product do you want to add to cart? (product name only): ");
        userInput = inputSc.nextLine().trim().toLowerCase();

        //Add product to cart
        for (Product product : products) {
            if (userInput.equalsIgnoreCase(product.getProductName()) && !userInput.isEmpty()) {
                shoppingCart.add(product);

                //Final output should be confirmation message to user about product being added to cart
                System.out.println(successActionColor + italicText + "Product added to cart: " + resetText + product.getProductName());
            }
        }
    }

    private static void removeProductFromCart(ArrayList<Product> products) {
        //Get user input to ask for product
        System.out.println(promptTextColor + "What product do you want to add to cart? (product name only): ");
        userInput = inputSc.nextLine().trim().toLowerCase();

        //Removing a product from cart
        for (Product product : products) {
            if (userInput.equalsIgnoreCase(product.getProductName()) && !userInput.isEmpty()) {
                shoppingCart.remove(product);

                //Final output should be confirmation message to user about product being removed from cart
                System.out.println(successActionColor + "Product removed to cart: " + product.getProductName());
            }
        }
    }

    private static void checkOut(ArrayList<Product> cart) {
        exitMenu = false;
        //To hold user payment for checkOut()
        double parsedPay;
        String payment;

        //To hold sales total and change due
        double totalCart;
        double changeFromPurchase;

        //To hold order timestamp info
        LocalDateTime saleDate = LocalDateTime.now();
        String[] orderDateTimeFormats;

        String checkOutScreen = """
                ======================================================================================
                |                           * * * E-STORE CHECKOUT * * *                             |
                |                                                                                    |
                |                                    1. Cash                                         |
                |                                    2. Card                                         |
                |                                                                                    |
                |       Hit Q to cancel transaction      |       Hit X to go back to Home Screen     |
                ======================================================================================
                """;

        do {
            //Get user input to ask for payment
            System.out.print(allScreensColor + checkOutScreen + resetText + promptTextColor + "Would you be paying with cash or card?: ");
            userInput = inputSc.nextLine().trim();

            switch (userInput) {
                case "1":
                    //Get receipt date and time for this checkout transaction and store it inside new String[] array
                    orderDateTimeFormats = getReceiptDateTime(saleDate).split("\\|");

                    //Printing order date to the screen
                    System.out.println("\n***************** E-STORE *****************");
                    System.out.println("Date: " + orderDateTimeFormats[0] + "     " + orderDateTimeFormats[1]);
                    System.out.println("-------------------------------------------");

                    //Printing line items to the screen
                    for (Product p : cart) {
                        //To get padding between line item and price
                        String leftOutput = p.getSku() + " " + p.getProductName() + " ";
                        int lineItemPadding = 36 - leftOutput.length();

                        System.out.print(p.getSku() + " " + p.getProductName() + " " + String.format("%" + lineItemPadding + "s", " "));
                        System.out.printf("$%.2f \n", p.getPrice());
                    }

                    System.out.println("-------------------------------------------");

                    //Calculate total for shoppingCart
                    totalCart = getSalesTotal(cart);

                    //Printing sales total from this purchase to the user
                    System.out.printf("Sales Total: $%.2f \n", totalCart);

                    //Prompt user for cash
                    System.out.println("Enter amount of cash on hand to complete your purchase: ");
                    payment = inputSc.nextLine().trim();
                    parsedPay = Double.parseDouble(payment);

                    //Calculate change owed to user
                    changeFromPurchase = getChangeOwed(parsedPay, totalCart);

                    System.out.println("-------------------------------------------");

                    //Printing amount paid and change owed to user
                    System.out.printf("Amount Paid: $%.2f \n", parsedPay);
                    System.out.printf("Change Due:  $%.2f \n", changeFromPurchase);

                    //Print sales receipt to user
                    printSalesReceipt(saleDate, orderDateTimeFormats, totalCart, parsedPay, changeFromPurchase, cart);

                    //Final message to user for completing transaction
                    System.out.println("Thank you for shopping with us at the E-STORE! Have a nice day :)");

                    //Return to homeScreen
                    showHomeScreen();
                    break;
                case "2":
                    System.out.println("Debit or credit?: ");
                case "Q", "q":
                    exitMenu = true;
                    break;
                case "X", "x":
                    showHomeScreen();
                    break;
                default:
                    throw new Error("Sorry, that's not a valid option. Please make your selection.");
            }

        } while (!exitMenu);
    }

    //Method to print final receipt to screen and writing to file
    private static void printSalesReceipt(LocalDateTime saleDate, String[] orderDate, double salesTotal, double userPay, double changeOwed, ArrayList<Product> cart) {
        String fileTimestamp = getFileTimestamp(saleDate);

        String receiptsFilePath = "src/main/resources/receipts/" + fileTimestamp + ".txt";

        try {
            FileWriter fileWriter = new FileWriter(receiptsFilePath);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            //Writing to order details to file
            bufWriter.write("***************** E-STORE *****************\n");
            bufWriter.write("Date: " + orderDate[0] + "     " + orderDate[1] + "\n");
            bufWriter.write("-------------------------------------------\n");

            //Writing all line items
            for (Product p : cart) {
                //To get padding between line item and price
                String leftOutput = p.getSku() + " " + p.getProductName() + " ";
                int lineItemPadding = 36 - leftOutput.length();

                bufWriter.write(p.getSku() + " " + p.getProductName() + " " + String.format("%" + lineItemPadding + "s", " "));
                bufWriter.write(String.format("$%.2f \n", p.getPrice()));
            }

            bufWriter.write("-------------------------------------------\n");

            //Order payment info
            bufWriter.write("Sales Total: $" + String.format("%.2f", salesTotal) + "\n");
            bufWriter.write("Amount Paid: $" + String.format("%.2f", userPay) + "\n");
            bufWriter.write("Change Due:  $" + String.format("%.2f", changeOwed) + "\n");

            bufWriter.write("-------------------------------------------\n");

            //Final message to user for completing transaction
            bufWriter.write("Thank you for shopping with us at E-STORE! \nHave a nice day :)");

            //Clear the cart
            cart.clear();

            //To close bufWriter stream
            bufWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Methods for order/receipt processing and formatting
    private static String getFileTimestamp(LocalDateTime saleDate) {
        //Date to print to file
        DateTimeFormatter traditionalDate = DateTimeFormatter.ofPattern("yyyyMMdd");
        String orderDate = saleDate.format(traditionalDate);

        //Time to print onto file
        DateTimeFormatter traditionalTime = DateTimeFormatter.ofPattern("HHmm");
        String orderTime = saleDate.format(traditionalTime);

        return orderDate + orderTime;
    }

    private static String getReceiptDateTime(LocalDateTime saleDate) {
        //Receipt date in a readable format
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String receiptDate = saleDate.format(formattedDate);

        //Receipt time in a readable format
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm a");
        String receiptTime = saleDate.format(formattedTime);

        return receiptDate + "|" + receiptTime;
    }

    private static double getSalesTotal(ArrayList<Product> cart) {
        double cartTotal = 0.0;

        for (Product p : cart) {
            //Add up all pricing for each product inside cart
            cartTotal += p.getPrice();
        }
        return cartTotal;
    }

    private static double getChangeOwed(double userPay, double totalCart) {
        double changeFromTotal = 0.0;

        if (userPay >= totalCart) {
            changeFromTotal = userPay - totalCart;

        } else {
            System.out.println("Pay provided is insufficient to cover full purchase.");
        }
        return changeFromTotal;
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
            System.out.println(successActionColor + "File was successfully read!");

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

