/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness.service;

import bussiness.entity.DispensingDrink;
import bussiness.entity.IngredientsInformation;
import bussiness.entity.MenuInformation;
import data.DataFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * ReportService class provides methods for generating reports related to ingredients and drinks.
 * It includes methods to display available ingredients, drinks out of ingredients, and all dispensing drinks.
 * 
 * @author Duy.Tran
 */
public class ReportService {

    private DataFile<IngredientsInformation> ingredientsDataFile = new DataFile<>();
    private DataFile<MenuInformation> menuDataFile = new DataFile<>();
    private DataFile<DispensingDrink> dispensingDataFile = new DataFile<>();
    private TreeMap<String, IngredientsInformation> ingredientsMap = new TreeMap<>();
    private TreeMap<String, MenuInformation> menuMap = new TreeMap<>();
    private List<DispensingDrink> dispensingList = new ArrayList<>();
    private String ingredientsFilePath = "Ingredients.dat";
    private String menuFilePath = "Menu.dat";
    private String dispensingFilePath = "Order.dat";

    /**
     * Displays the available ingredients along with their quantities.
     */
    public void displayAvailableIngredients() throws IOException {
        ingredientsDataFile.loadData(ingredientsMap, ingredientsFilePath);
        System.out.println("Available Ingredients:");
        display();
        boolean found = false;
        for (Map.Entry<String, IngredientsInformation> entry : ingredientsMap.entrySet()) {
            IngredientsInformation ingredient = entry.getValue();
            if (ingredient.getQuantity() > 0) {
                System.out.printf("|%10s|%19s|%12s|\n",
                        ingredient.getCode(), ingredient.getName(), ingredient.getQuantity());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available ingredients.");
        }
    }

    /**
     * Displays the drinks that are out of ingredients.
     */
    public void displayDrinksOutOfIngredients() throws IOException {
        menuDataFile.loadData(menuMap, menuFilePath);
        ingredientsDataFile.loadData(ingredientsMap, ingredientsFilePath);
        System.out.println("| Code     | Name              |");
        System.out.println("+----------+-------------------+");
        for (Map.Entry<String, MenuInformation> entry : menuMap.entrySet()) {
            MenuInformation drink = entry.getValue();
            for (Map.Entry<String, IngredientsInformation> ingredientEntry : ingredientsMap.entrySet()) {
                IngredientsInformation ingredient = ingredientEntry.getValue();
                for (IngredientsInformation ingredientList : drink.getIngredientsList()) {
                    if (ingredient.getQuantity() == 0 && ingredientList.getCode().equals(ingredient.getCode())) {
                        System.out.printf("|%10s|%19s|", drink.getCode(), drink.getName());
                    }
                }
            }

        }
    }

    /**
     * Displays all dispensing drinks.
     */
    public void showAllDispensingDrinks() {

        dispensingDataFile.loadOrderData(dispensingList, dispensingFilePath);

        display();

        for (DispensingDrink dispensingDrink : dispensingList) {
            System.out.println(dispensingDrink.toString());
        }

        System.out.println("--------------------------------------------");
    }

    /**
     * Displays the header for the report.
     */
    public void display() {
        System.out.println("| Code     | Name              | Quantities |");
        System.out.println("+----------+-------------------+------------+");
    }
}
