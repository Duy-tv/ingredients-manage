/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bussiness.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
* This class provides methods for checking and managing item statuses.
*
* @author Duy.Tran
*/
public class Status {

   
   public static final String EXPIRED = "Expired";
   public static final String OUT_OF_STOCK = "Out of stock";
   public static final String IN_STOCK = "In stock";
   public static final String IN_DATE = "In date";

   /**
    * Determines whether a given expiration date has passed.
    *
    * @param expirationDateStr the expiration date as a string in "dd/MM/yyyy" format
    * @return true if the expiration date has passed, false otherwise
    */
   private static boolean isExpired(String expirationDateStr) {
    if (expirationDateStr.isEmpty()) {
        // If the expiration date string is empty, consider it as not expired
        return false;
    }
    try {
        LocalDate expirationDate = LocalDate.parse(expirationDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate currentDate = LocalDate.now();
        return expirationDate.isBefore(currentDate);
    } catch (DateTimeParseException e) {
        // Handle parsing exception (e.g., invalid date format)
        System.out.println("Error parsing expiration date: " + e.getMessage());
        return false;
    }
}

   /**
    * Checks the status of an item based on its quantity and expiration date.
    *
    * @param quantity the quantity of the item
    * @param expirationDateStr the expiration date as a string in "dd/MM/yyyy" format
    * @return the status of the item as a {@link String}
    */
   public String checkStatus(int quantity, String expirationDateStr) {
       if (quantity <= 0) {
           return OUT_OF_STOCK;
       } else if (isExpired(expirationDateStr)) {
           return EXPIRED;
       } else {
           return IN_DATE;
       }
   }

   /**
    * Returns an array of all possible status values.
    *
    * @return a {@link String} array containing all status values
    */
   public String[] getStatusList() {
       return new String[]{EXPIRED, OUT_OF_STOCK, IN_STOCK, IN_DATE};
   }

   /**
    * Formats a status string for display.
    *
    * @param checkStatus the status to format
    * @return the formatted status string
    */
   public String toString(String checkStatus) {
       return String.format("%17s|", checkStatus);
   }
}
