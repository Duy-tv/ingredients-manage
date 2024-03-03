/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Inputer class provides methods for user input handling.
 * It includes methods for inputting strings, integers, dates, and units, among others.
 * 
 * @author Duy.Tran
 */
public class Inputer {

    Scanner sc = new Scanner(System.in);
    private final String dateFormat = "dd/MM/yyyy";
    private final Pattern unitPattern = Pattern.compile("^(g|ml)$");
    private final String UNIT_VALID = "Invalid unit. Please enter one of the following units: g, ml";

    /**
     * Prompts the user to input a string.
     * 
     * @param msg The message to display to the user.
     * @return The string input by the user.
     */
    public String inputString(String msg) {
        String input;
        System.out.println(msg);
        input = sc.nextLine();
        return input;
    }

    /**
     * Prompts the user to input a non-blank string.
     * 
     * @param msg The message to display to the user.
     * @return The non-blank string input by the user.
     */
    public String inputStringNotBlank(String msg) {
        String input;
        do {
            input = inputString(msg);
            if (input.trim().isEmpty()) {
                System.out.println("Input cannot be blank. Please enter a non-empty value.");
            }
        } while (input.trim().isEmpty());
        return input;
    }

    /**
     * Prompts the user to input an integer.
     * 
     * @param msg The message to display to the user.
     * @return The integer input by the user.
     */
    public int inputInt(String msg) {
        int result = 0;
        boolean check = true;

        while (check) {
            try {
                String tmp = inputString(msg);
                result = Integer.parseInt(tmp);
                return result;
            } catch (NumberFormatException e) {
                System.out.println("This must be number!");
                check = true;
            }
        }
        return result;
    }

    /**
     * Prompts the user to input an integer or null.
     * 
     * @param msg The message to display to the user.
     * @return The integer input by the user, or null if no input is provided.
     */
    public Integer inputIntNull(String msg) {
        boolean check = false;
        Integer result = null;
        while (!check) {
            try {
                String tmp = inputString(msg);
                if (tmp == null || tmp.trim().isEmpty()) {
                    check = true;
                } else {
                    result = Integer.parseInt(tmp);
                    check = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("This must be a number!");
            }
        }
        return result;
    }

    /**
     * Prompts the user to input a code that does not already exist in the provided set.
     * 
     * @param codeSet The set of existing codes.
     * @return The unique code input by the user.
     */
    public String inputCode(Set<String> codeSet) {
        String code = "";
        boolean check = true;
        do {
            code = inputStringNotBlank("Enter code: ");
            if (codeSet.contains(code)) {
                System.out.println("Code already exists.");
            } else {
                check = false;
            }
        } while (check);
        return code;
    }

    /**
     * Checks if a given date string is in a valid format.
     * 
     * @param dateStr The date string to validate.
     * @param format The expected date format.
     * @return true if the date string is in a valid format, otherwise false.
     */
    public static boolean isValidDate(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Prompts the user to input a date in the specified format.
     * 
     * @param msg The message to display to the user.
     * @return The date input by the user.
     */
    public String formatDate(String msg) {
        String dateStr = null;
        boolean check = true;
        do {
            try {
                dateStr = formatDateNull(msg);
                if (dateStr != null || !dateStr.trim().isEmpty()) {
                    check = false;
                }
            } catch (NullPointerException e) {
                System.out.println("not a valid date!");
            }
           
        } while (check);

        return dateStr;
    }

    /**
     * Prompts the user to input a date in the specified format, allowing null input.
     * 
     * @param msg The message to display to the user.
     * @return The date input by the user, or null if no input is provided.
     */
    public String formatDateNull(String msg) {
        String dateStr;
        boolean check = true;
        do {
            dateStr = inputString(msg);
            if (dateStr == null || dateStr.trim().isEmpty()) {
                return null;
            } else if (isValidDate(dateStr, dateFormat)) {
                check = false;
            } else {
                System.out.println("not a valid date!");
            }
        } while (check);

        return dateStr;
    }

    /**
     * Prompts the user to input a choice (Y/N).
     * 
     * @param msg The message to display to the user.
     * @return true if the user chooses 'Y', otherwise false.
     */
    public boolean inputY(String msg) {
        String choice;
        boolean check = true;
        while (check) {
            choice = inputString(msg);
            if (choice.equalsIgnoreCase("Y")) {
                return check = true;
            } else {
                check = false;
            }
        }

        return check;
    }

    /**
     * Prompts the user to input a unit (g/ml).
     * 
     * @param msg The message to display to the user.
     * @return The unit input by the user.
     */
    public String unitCheck(String msg) {
        String unit = null;
        boolean validUnit = false;
        do {
            try {
                unit = unitCheckNull(msg);
                if (unit != null || !unit.trim().isEmpty()) {
                    validUnit = true;
                }
            } catch (NullPointerException e) {
                System.out.println(UNIT_VALID);
            }

        } while (!validUnit);
        return unit;
    }

    /**
     * Prompts the user to input a unit (g/ml), allowing null input.
     * 
     * @param msg The message to display to the user.
     * @return The unit input by the user, or null if no input is provided.
     */
    public String unitCheckNull(String msg) {
        String unit;
        boolean validUnit = false;
        do {
            unit = inputString(msg);
            if (unit == null || unit.trim().isEmpty()) {
                return null;
            } else if (unitPattern.matcher(unit).matches()) {
                validUnit = true;
            } else {
                System.out.println(UNIT_VALID);
            }
        } while (!validUnit);
        return unit;
    }
}
