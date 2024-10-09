package com.pluralsight.payroll;

public class Employee {
    private int employeeId;
    private String name;
    private double hoursWorked;
    private double payRate;

    public Employee (int employeeId, String name, double hoursWorked, double payRate) {
        this.employeeId = employeeId;
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.payRate = payRate;
    }

    //Getters for Employee info
    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getPayRate() {
        return payRate;
    }

    public static double getGrossPay(double hoursWorked, double payRate) {
        return hoursWorked * payRate;
    }

    public String toString(Employee e) {

        final StringBuilder sb = new StringBuilder();

        sb.append("Employee ID: ").append(employeeId).append(" Employee Name: ")
                .append(name).append(" Gross Pay: ").append(hoursWorked * payRate);
        return sb.toString();
    }
}

