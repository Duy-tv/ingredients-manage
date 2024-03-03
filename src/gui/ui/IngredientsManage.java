/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ui;

import gui.utilities.Menu;

/**
 * IngredientsManage class provides a user interface for managing ingredients, beverage recipes, dispensing beverages, and generating reports.
 * It utilizes the Menu class to display menu options and handle user input.
 * 
 * @author Duy.Tran
 */
public class IngredientsManage {

    static Menu menu = new Menu();

    /**
     * Main method to run the Ingredients Management system.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        int choice = 0;
        do {
            menu.display();
            choice = menu.choose();
            switch (choice) {
                case 1:
                    menu.manageIngredients("-----Manage ingredients-----");
                    break;
                case 2:
                    menu.manageBeverageRecipes("------Manage beverage recipes-----");
                    break;
                case 3:
                    menu.dispensingBeverages("-----Dispensing beverages-----");
                    break;
                case 4:
                    menu.report("-----Report-----");
                    break;
            }
        } while (0 < choice && choice <= 4);
    }
}
