package com.pluralsight.payroll;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PayrollListCalApp {
    public static void main(String[] args) {
        //File path variables
        String employeeFilePath;
        String grossPayFilePath;

        //Intializing a new ArrayList of employees to hold value from readEmployeeFile
        ArrayList<Employee> employeeList = new ArrayList<>();

        //Initializing a new Scanner object for user input
        Scanner inputSc = new Scanner(System.in);

        try {
            System.out.print("Enter the name of employee file you'd like to process: ");
            String readFileInput = inputSc.nextLine().trim().toLowerCase();

            if (!readFileInput.isEmpty()) {
                employeeFilePath = "src/main/resources/" + readFileInput;
                //Call readEmployeeFile method to retrieve a new employee from file
                employeeList = readEmployeeFile(employeeFilePath);
            }

            System.out.print("Enter the name of the payroll file you'd like to create: ");
            String writeFileInput = inputSc.nextLine().trim().toLowerCase();

            if (!writeFileInput.isEmpty()) {
                grossPayFilePath = "src/main/resources/" + writeFileInput;
                /* Call writeEmployeeFile method to write each employee from read file
                 *  -- Note: if user types same file, the contents will be overwritten
                 * */
                writeEmployeeFile(grossPayFilePath, employeeList);
            }

        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    private static ArrayList<Employee> readEmployeeFile(String fileName) throws FileNotFoundException {
        //Storing value for one employee
        Employee e;

        //A collection that holds employees
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            //Calling openFileReader method to initialize BufferedReader
            BufferedReader bufReader = openFileReader(fileName);

            //Reading each line of input from fileContents
            String fileContents;

            while ((fileContents = bufReader.readLine()) != null) {
                //Storing readFile contents as an array of objects
                String[] employeeData = fileContents.split("\\|");

                //To store values from fileContents and assigning their values to employee variables
                int employeeID = Integer.parseInt(employeeData[0]);
                String employeeName = employeeData[1];
                double hoursWorked = Double.parseDouble(employeeData[2]);
                double payRate = Double.parseDouble(employeeData[3]);

                //Creating a new employee object and passing it employee variables to constructor
                e = new Employee(employeeID, employeeName, hoursWorked, payRate);

                //Adding each new employee to employees ArrayList
                employees.add(e);
            }

            //Printing success message when file is done being read
            System.out.println("File was successfully read!");

            //Closing bufReader
            bufReader.close();

            //Return ArrayList of employees
            return employees;

            //Call method to print each employee in new string format
//            printEmployee(employees);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void writeEmployeeFile(String filename, ArrayList<Employee> employees) throws FileNotFoundException {
        try {
            //Create a fileWriter
            FileWriter fileWriter = new FileWriter(filename);

            //Create a bufferWriter
            BufferedWriter bufWriter = new BufferedWriter((fileWriter));

            for (Employee e : employees) {
                //Formatting employee data
                String employeeID = String.format("%d", e.getEmployeeId());
                String employeeName = String.format("|%s", e.getName());
                String employeeGrossPay = String.format("|$%.2f \n", e.getHoursWorked() * e.getPayRate());

                //Writing contents of each employee's ID, name, and gross pay to file
                bufWriter.write(employeeID);
                bufWriter.write(employeeName);
                bufWriter.write(employeeGrossPay);
            }

            //Print success message when file was done being written
            System.out.println("File was successfully written!");

            //Closing bufferedWriter
            bufWriter.close();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static BufferedReader openFileReader(String filename) throws FileNotFoundException {
        //Creating a new BufferedReader object to read "employeePayroll.csv" and initializing it to read contents from FileReader
        BufferedReader bufReader = new BufferedReader(new FileReader(filename));
        return bufReader;
    }

    //From Exercise 2
//    public static void printEmployee(ArrayList<Employee> employees) {
//        //Print out each employee inside the ArrayList employees
//        for (Employee e : employees) {
//            System.out.println("Employee ID: " + e.getEmployeeId());
//            System.out.printf("Employee Name: %s\n", e.getName());
//            System.out.printf("Gross Pay: $%.2f", e.getHoursWorked() * e.getPayRate());
//            System.out.println("\n");
//        }
//    }
}
