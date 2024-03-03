/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.utilities;

import bussiness.service.BeverageRecipesService;
import bussiness.service.DispensingService;
import bussiness.service.IngredientsService;
import bussiness.service.ReportService;

/**
 * Menu class provides methods to display and manage different aspects of the beverage system.
 * It includes functionalities to manage ingredients, beverage recipes, dispensing beverages, and generating reports.
 * 
 * @author Duy.Tran
 */
public class Menu extends Inputer {

    IngredientsService ingredientsService = new IngredientsService("Ingredients.dat");
    BeverageRecipesService beverageRecipesService = new BeverageRecipesService("Menu.dat");
    DispensingService dispensingService = new DispensingService("Order.dat");
    ReportService reportService = new ReportService();

    /**
     * Displays the main menu options.
     */
    public void display() {
        System.out.println("\n1. Manage ingredients\n"
                + "2. Manage beverage recipes\n"
                + "3. Dispensing beverages\n"
                + "4. Report\n");

    }

    /**
     * Displays the options for managing ingredients.
     */
    public void displayIngredients() {
        System.out.println("\n1. Add an ingredient\n"
                + "2. Update ingredient information\n"
                + "3. Delete ingredient\n"
                + "4. Show all ingredients\n");
    }

    /**
     * Displays the options for managing beverage recipes.
     */
    public void displayBeverageRecipes() {
        System.out.println("\n1. Add the drink to menu\n"
                + "2. Update the drink information\n"
                + "3. Delete the drink from menu\n"
                + "4. Show menu\n");
    }

    /**
     * Displays the options for dispensing beverages.
     */
    public void displayBeverages() {
        dispensingService.showDrinkList();
        System.out.println("\n1. Dispensing the drink.\n"
                + "2. Update the dispensing drink.\n");
    }

    /**
     * Displays the options for generating reports.
     */
    public void displayReport() {
        System.out.println("\n1. Display available ingredients\n"
                + "2. Display drinks out of ingredients\n"
                + "3. Show all dispensing drinks");
    }

    /**
     * Allows the user to choose an option from the menu.
     * 
     * @return The chosen option.
     */
    public int choose() {
        return inputInt("Choose your option: ");
    }

    /**
     * Manages ingredients based on the user's choice.
     * 
     * @param msg The message to display.
     */
    public void manageIngredients(String msg) {
        System.out.println(msg);
        displayIngredients();
        switch (choose()) {
            case 1:
                ingredientsService.add();
                break;
            case 2:
                ingredientsService.update();
                break;
            case 3:
                ingredientsService.delete();
                break;
            case 4:
                ingredientsService.show();
                break;

        }

    }

    /**
     * Manages beverage recipes based on the user's choice.
     * 
     * @param msg The message to display.
     */
    public void manageBeverageRecipes(String msg) {
        System.out.println(msg);
        displayBeverageRecipes();
        switch (choose()) {
            case 1:
                beverageRecipesService.add();
                break;
            case 2:
                beverageRecipesService.update();
                break;
            case 3:
                beverageRecipesService.delete();
                break;
            case 4:
                beverageRecipesService.show();
                break;

        }
    }

    /**
     * Dispenses beverages based on the user's choice.
     * 
     * @param msg The message to display.
     */
    public void dispensingBeverages(String msg) {
        System.out.println(msg);
        displayBeverages();
        switch (choose()) {
            case 1:
                dispensingService.dispenseDrink();
                break;
            case 2:
                dispensingService.updateDispensingDrink();
                break;

        }
    }

    /**
     * Generates reports based on the user's choice.
     * 
     * @param msg The message to display.
     */
    public void report(String msg) {
        System.out.println(msg);
        displayReport();
        switch (choose()) {
            case 1:
                reportService.displayAvailableIngredients();
                break;
            case 2:
                reportService.displayDrinksOutOfIngredients();
                break;
            case 3:
                reportService.showAllDispensingDrinks();
                break;
        }
    }
}
