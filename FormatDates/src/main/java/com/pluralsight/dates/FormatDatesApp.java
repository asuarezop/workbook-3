package com.pluralsight.dates;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class FormatDatesApp {

    public static void main(String[] args) {

        //Date and time variables with format Strings to hold final output
        TimeZone timeZone = TimeZone.getTimeZone("US/Eastern");
        LocalDateTime todaysDate = LocalDateTime.now();

        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formatTodaysDateInAmerica = todaysDate.format(formattedDate);

        DateTimeFormatter traditionalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatTraditionalDate = todaysDate.format(traditionalDate);

        DateTimeFormatter expandedDate = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        String formatExpandedDate = todaysDate.format(expandedDate);

        DateTimeFormatter utcDate = DateTimeFormatter.ofPattern("E, MMM dd, yyyy HH:mm");
        String formatUTCDate = todaysDate.format(utcDate);

        DateTimeFormatter localTimeZoneDate = DateTimeFormatter.ofPattern("HH:mm  dd-MMM-yyyy");
        String formatLocalTimeDate = todaysDate.format(localTimeZoneDate);

        //Printed out date in US format - MM/dd/YYYY
        System.out.println("Today's date: " + formatTodaysDateInAmerica);
        //Printed out date in standard format - YYYY-MM-dd
        System.out.println("Today's date: " + formatTraditionalDate);
        //Printed out date in expanded format - MMMM dd, yyyy
        System.out.println("Today's date: " + formatExpandedDate);
        //Printed out date in GMT format - E, MMM dd, yyyy HH:mm
        System.out.println("Today's date: " + formatUTCDate);
        //Printed out date in local time zone
        System.out.println("Today's date: " + formatLocalTimeDate);
    }
}
