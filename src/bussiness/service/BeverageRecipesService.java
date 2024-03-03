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
 * BeverageRecipesService class provides methods for managing beverage recipes,
 * including adding, updating, deleting, and displaying recipes.
 * It interacts with MenuInformation, IngredientsInformation, and DispensingDrink entities.
 * 
 * @author Duy.Tran
 */
public class BeverageRecipesService implements ServiceManagement {

    Inputer inputer = new Inputer();
    private TreeMap<String, MenuInformation> menuMap;
    private List<DispensingDrink> dispensingList = new ArrayList<>();
    private TreeMap<String, IngredientsInformation> ingredientsMap = new TreeMap<>();
    private DataFile<MenuInformation> data = new DataFile<>();
    private DataFile<DispensingDrink> dispensingDataFile = new DataFile<>();
    private String pathName;

    /**
     * Constructor for BeverageRecipesService.
     * Loads existing menu data and initializes the list of drinks being dispensed.
     * 
     * @param fileName The name of the file containing menu data.
     */
    public BeverageRecipesService(String fileName) {
        loadOrderData();
        this.pathName = fileName;
        this.menuMap = new TreeMap<>();
        try {
            data.loadData(menuMap, pathName);
        } catch (Exception e) {
            System.out.println("Empty list");
        }
        if (menuMap.isEmpty()) {
            System.out.println("Empty list, add new: ");
            add();
        }
    }

    private void loadOrderData() {
        try {
            dispensingDataFile.loadOrderData(dispensingList, "Order.dat");
        } catch (Exception e) {
            System.out.println("Error loading menu data: " + e.getMessage());
        }
    }

    /**
     * Adds a new beverage recipe to the menu.
     */
    @Override
    public void add() {
        String code = inputer.inputCode(menuMap.keySet());
        String name = inputer.inputString("Enter drink name:");
        List<IngredientsInformation> ingredientsList = new ArrayList<>();
        boolean check = true;
        while (check) {
            String ingredientCode = inputer.inputCode(ingredientsMap.keySet());
            checkIngredient(ingredientCode, ingredientsList);
            check = inputer.inputY("Do you want to continue(Y or other): ");
        }

        MenuInformation newDrink = new MenuInformation(code, name, 0, "", "", 0);
        newDrink.setIngredientsList(ingredientsList);

        menuMap.put(code, newDrink);
        data.saveData(menuMap, pathName, "Drink added to menu successfully.");
    }

    /**
     * Displays all beverage recipes in the menu.
     */
    @Override
    public void show() {
        display();

        for (Map.Entry<String, MenuInformation> entry : menuMap.entrySet()) {
            MenuInformation drink = entry.getValue();
            boolean firstRow = true;
            for (IngredientsInformation ingredient : drink.getIngredientsList()) {
                if (firstRow) {
                    System.out.printf("|%12s|%19s|", drink.getCode(), drink.getName());
                    firstRow = false;
                } else {
                    System.out.printf("|%12s|%19s|", "", "");
                }
                System.out.printf("%18s|%10d|%6s|\n", ingredient.getCode(), ingredient.getQuantity(), ingredient.getUnit());
            }
            System.out.println("+------------+-------------------+------------------+----------+------+");
        }
    }

    /**
     * Displays a header for the beverage recipes display.
     */
    public void display() {
        System.out.println("| Drink Code | Drink Name        | Ingredients Code | Quantity | Unit |");
        System.out.println("+------------+-------------------+------------------+----------+------+");
    }

    /**
     * Updates an existing beverage recipe in the menu.
     */
    @Override
    public void update() {
        String drinkCodeToUpdate = inputer.inputString("Enter the code of the drink to update:");
        if (menuMap.containsKey(drinkCodeToUpdate)) {
            MenuInformation drinkToUpdate = menuMap.get(drinkCodeToUpdate);
            System.out.println("| Drink Code | Drink Name        |");
            System.out.printf("|%12s|%19s|\n", drinkToUpdate.getCode(), drinkToUpdate.getName());
            String newName = inputer.inputString("Enter the new name for the drink:");
            if (!newName.isEmpty()) {
                drinkToUpdate.setName(newName);
            }
            for (IngredientsInformation ingredient : drinkToUpdate.getIngredientsList()) {
                Integer newQuantity = null;
                newQuantity = inputer.inputIntNull("Enter new quantity for ingredient " + ingredient.getCode() + ":");
                if (newQuantity != null) {
                    ingredient.setQuantity(newQuantity);
                }
                String newUnit = inputer.unitCheckNull("Enter new unit for ingredient " + ingredient.getCode() + " (g, ml):");
                if (newUnit != null && !newUnit.isEmpty()) {
                    ingredient.setUnit(newUnit);
                }

            }
            menuMap.put(drinkCodeToUpdate, drinkToUpdate);
            data.saveData(menuMap, pathName, "Drink information updated successfully.");
            System.out.println("Drink information updated successfully.");
        } else {
            System.out.println("No drink found with the provided code.");
        }
    }

    /**
     * Deletes a beverage recipe from the menu.
     */
    @Override
    public void delete() {
        String drinkCodeToDelete = inputer.inputString("Enter the code of the drink to delete:");
        boolean check = false;
        for (DispensingDrink dispensedDrink : dispensingList) {
            if (dispensedDrink.getCode().equals(drinkCodeToDelete)) {
                check = true;
            }
        }
        if (menuMap.containsKey(drinkCodeToDelete) && check) {
            System.out.println("cannot delete drink existed in the Order");
        } else if (menuMap.containsKey(drinkCodeToDelete)) {
            System.out.println("Do you really want to delete this ingredient?");
            if (inputer.inputY("(Y or other)") == true) {
                menuMap.remove(drinkCodeToDelete);
                data.saveData(menuMap, pathName, "Drink deleted from menu successfully.");
                System.out.println("Drink deleted from menu successfully.");
            } else {
                System.out.println("FailS");
            }
        } else {
            System.out.println("No drink found with the provided code.");
        }
    }

    /**
     * Checks if an ingredient exists and adds it to the list of ingredients for a drink.
     * 
     * @param ingredientCode The code of the ingredient to check.
     * @param ingredientsList The list of ingredients for the drink.
     */
    public void checkIngredient(String ingredientCode, List<IngredientsInformation> ingredientsList) {
        TreeMap<String, IngredientsInformation> loadedIngredients = new TreeMap<>();
        DataFile<IngredientsInformation> ingredientsData = new DataFile<>();
        ingredientsData.loadData(loadedIngredients, "Ingredients.dat");
        if (loadedIngredients.containsKey(ingredientCode)) {
            int quantity = inputer.inputInt("Enter quantity:");
            String unit = inputer.unitCheck("Enter unit (g, ml): ");
            IngredientsInformation ingredient = new IngredientsInformation(ingredientCode, "", quantity, unit, "");
            ingredientsList.add(ingredient);
            System.out.println("Ingredient added: " + ingredient.getCode());
        } else {
            System.out.println("Ingredient with code " + ingredientCode + " does not exist.");
        }
    }

}
