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
import gui.utilities.Inputer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * DispensingService class provides methods for dispensing drinks, checking ingredient availability,
 * and managing the list of drinks being dispensed.
 * It interacts with IngredientsInformation, MenuInformation, and DispensingDrink entities.
 * 
 * @author Duy.Tran
 */
public class DispensingService {

    private Inputer inputer = new Inputer();
    private TreeMap<String, IngredientsInformation> ingredientsMap = new TreeMap<>();
    private TreeMap<String, MenuInformation> menuMap = new TreeMap<>();
    private List<DispensingDrink> dispensingList = new ArrayList<>();
    private DataFile<IngredientsInformation> ingredientDataFile = new DataFile<>();
    private DataFile<MenuInformation> menuDataFile = new DataFile<>();
    private DataFile<DispensingDrink> dispensingDataFile = new DataFile<>();
    private String menuFilePath = "Menu.dat";
    private String ingredientFilePath = "Ingredients.dat";
    private String pathName;

    /**
     * Constructor for DispensingService.
     * Loads menu data and initializes the list of drinks being dispensed.
     * 
     * @param fileName The name of the file containing dispensing data.
     */
    public DispensingService(String fileName) {
        loadMenuData();
        this.pathName = fileName;
        try {
            dispensingDataFile.loadOrderData(dispensingList, pathName);
        } catch (Exception e) {
            System.out.println("Empty list");
        }
        if (dispensingList.isEmpty()) {
            showDrinkList();
            System.out.println("Order: ");
            dispenseDrink();
        }
    }

    private void loadMenuData() {
        try {
            menuDataFile.loadData(menuMap, menuFilePath);
            ingredientDataFile.loadData(ingredientsMap, ingredientFilePath);
        } catch (Exception e) {
            System.out.println("Error loading menu data: " + e.getMessage());
        }
    }

    /**
     * Displays the list of drinks available in the menu.
     */
    public void showDrinkList() {
        if (menuMap.isEmpty()) {
            System.out.println("Menu is empty.");
        } else {
            System.out.println("| Drink Code |     Drink Name    |");
            for (Map.Entry<String, MenuInformation> entry : menuMap.entrySet()) {
                MenuInformation drink = entry.getValue();
                System.out.println(drink.toString());
            }
        }
    }

    /**
     * Dispenses a drink based on user input of drink code and quantity.
     * Checks ingredient availability before dispensing.
     */
    public void dispenseDrink() {
        String code = inputer.inputString("Enter Code: ");
        MenuInformation menuInfo = menuMap.get(code);
        if (menuInfo != null) {
            int quantity = inputer.inputInt("Enter quantity: ");
            boolean check = checkDrink(code, quantity);
            if (check) {
                String drinkName = menuInfo.getName();
                DispensingDrink dispensingDrink = new DispensingDrink(code, drinkName, quantity);
                dispensingList.add(dispensingDrink);
                dispensingDataFile.saveOrderData(dispensingList, "Order.dat", "Drink ordered successfully.");
                show();
            }
        } else {
            System.out.println("The drink does not exist.");
        }
    }

    /**
     * Checks the availability of ingredients for dispensing a drink.
     * 
     * @param drinkCode The code of the drink.
     * @param quantity The quantity of the drink.
     * @return True if ingredients are available, false otherwise.
     */
    public boolean checkDrink(String drinkCode, int quantity) {
        MenuInformation menuDrink = menuMap.get(drinkCode);
        List<IngredientsInformation> ingredientsList = menuDrink.getIngredientsList();
        boolean check = true;
        for (IngredientsInformation ingredient : ingredientsList) {
            String ingredientCode = ingredient.getCode();
            if (ingredientsMap.containsKey(ingredientCode)) {
                IngredientsInformation existingIngredient = ingredientsMap.get(ingredientCode);
                int requiredQuantity = ingredient.getQuantity() * quantity;
                if (existingIngredient.getQuantity() >= requiredQuantity) {
                    existingIngredient.setQuantity(existingIngredient.getQuantity() - requiredQuantity);
                } else {
                    check = false;
                    System.out.println("Insufficient quantity for ingredient with code " + ingredientCode);
                }
            } else {
                check = false;
                System.out.println("No matching ingredient found for ingredient with code " + ingredientCode);
            }
        }
        if (check) {
            ingredientDataFile.saveData(ingredientsMap, "Ingredients.dat", "Updated ingredient quantities.");
            System.out.println("Drink dispensed successfully.");
        } else {
            System.out.println("Drink dispensing failed due to insufficient ingredients.");
        }
        return check;
    }

    /**
     * Updates the quantity of a dispensed drink.
     */
    public void updateDispensingDrink() {
        String codeToUpdate = inputer.inputString("Enter the code of the drink to update: ");
        int oldQuantity = inputer.inputInt("Enter the old quantity: ");
        boolean found = false;
        for (DispensingDrink drink : dispensingList) {
            if (drink.getCode().equals(codeToUpdate) && drink.getQuantity() == oldQuantity) {
                found = true;
                int newQuantity = inputer.inputInt("Enter the new quantity for the drink: ");
                boolean check = checkDrink(codeToUpdate, newQuantity);
                if (check) {
                    drink.setQuantity(newQuantity);
                    dispensingDataFile.saveOrderData(dispensingList, "Order.dat", "Drink quantity updated successfully.");
                    System.out.println("Drink quantity updated successfully.");
                } else {
                    System.out.println("Drink dispensing failed due to insufficient ingredients.");
                }
            }
        }
        if (!found) {
            System.out.println("No drink found with the provided code and old quantity.");
        }
    }

    /**
     * Displays the list of drinks being dispensed.
     */
    public void show() {
        System.out.println("|   Code   |       Name        | Quantity |");
        for (DispensingDrink dispendingDrink : dispensingList) {
            System.out.println(dispendingDrink.toString());
        }
    }
}
