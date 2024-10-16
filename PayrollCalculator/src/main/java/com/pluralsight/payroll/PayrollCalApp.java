package com.pluralsight.payroll;

import java.io.*;

public class PayrollCalApp {
    public static void main(String[] args) {

        //File path data
        String filePath = "src/main/resources/employee.csv";
        Employee e;
        try {
            //Call readEmployeeFile method to retrieve a new employee from file
           readEmployeeFile(filePath);

        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    private static void readEmployeeFile(String fileName) throws FileNotFoundException {
        //Storing employee information
        Employee e;

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

                //Printing each employee in new string format
                printEmployee(e);
            }

        }  catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    private static BufferedReader openFileReader(String filename) throws FileNotFoundException {
        //Creating a new BufferedReader object to read "employeePayroll.csv" and initializing it to read contents from FileReader
        BufferedReader bufReader = new BufferedReader(new FileReader(filename));
        return bufReader;
    }

    public static void printEmployee(Employee e) {
        System.out.println("Employee ID: " + e.getEmployeeId());
        System.out.printf("Employee Name: %s\n", e.getName());
        System.out.printf("Gross Pay: $%.2f", e.getHoursWorked() * e.getPayRate());
        System.out.println("\n");
    }
}
