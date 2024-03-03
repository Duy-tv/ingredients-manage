/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness.service;

import bussiness.entity.IngredientsInformation;
import data.DataFile;
import gui.utilities.Inputer;
import java.util.Map;
import java.util.TreeMap;

/**
 * IngredientsService class provides methods for managing ingredients.
 * It implements the ServiceManagement interface.
 * 
 * @author Duy.Tran
 */
public class IngredientsService implements ServiceManagement {

    Inputer inputer = new Inputer();
    private TreeMap<String, IngredientsInformation> ingredientsMap = new TreeMap<>();
    private DataFile<IngredientsInformation> data = new DataFile<>();
    private String pathName;
    Status status = new Status();

    /**
     * Constructor for IngredientsService.
     * 
     * @param fileName The name of the file containing ingredient data.
     */
    public IngredientsService(String fileName) {

        this.pathName = fileName;

        try {
            data.loadData(ingredientsMap, pathName);
        } catch (Exception e) {
            System.out.println("Empty list");
        }
        if (ingredientsMap.isEmpty()) {
            System.out.println("Empty list, add new: ");
            add();
        }
    }

    @Override
    public void add() {
        boolean check = true;
        while (check) {
            String code = inputer.inputCode(ingredientsMap.keySet());
            String name = inputer.inputString("Enter ingredient name:");
            int quantity = inputer.inputInt("Enter quantity: ");
            String unit = inputer.unitCheck("Enter unit (g, ml): ");
            String expirationDateStr = inputer.formatDate("Enter expiration date (dd/mm/yyyy): ");
            IngredientsInformation newIngredient = new IngredientsInformation(code, name, quantity, unit, expirationDateStr);
            ingredientsMap.put(newIngredient.getCode(), newIngredient);
            data.saveData(ingredientsMap, pathName, "save successful.");
            check = inputer.inputY("Do you want to continue(Y or other): ");
        }
    }

    @Override
    public void update() {
        String codeToUpdate = inputer.inputString("Enter the code of the ingredient to update:");
        if (ingredientsMap.containsKey(codeToUpdate)) {
            String newName = inputer.inputString("Enter new name for the ingredient:");
            Integer newQuantity = null;
            newQuantity = inputer.inputIntNull("Enter new quantity: ");
            String newUnit = inputer.unitCheckNull("Enter new unit (g, ml): ");
            String newExpirationDate = inputer.formatDateNull("Enter new expiration date (dd/mm/yyyy):");
            IngredientsInformation updatedIngredient = ingredientsMap.get(codeToUpdate);
            if (!newName.isEmpty()) {
                updatedIngredient.setName(newName);
            }
            if (newQuantity != null) {
                updatedIngredient.setQuantity(newQuantity);
            }
            if (newUnit != null && !newUnit.isEmpty()) {
                updatedIngredient.setUnit(newUnit);
            }
            if (newExpirationDate != null && !newExpirationDate.isEmpty()) {
                updatedIngredient.setExpirationDate(newExpirationDate);
            }
            data.saveData(ingredientsMap, pathName, "save successful.");
            System.out.println("Ingredient information updated successfully!");
        } else {
            System.out.println("The ingredient does not exist.");
        }
    }

    @Override
    public void delete() {
        String codeToDelete = inputer.inputString("Enter the code of the ingredient to delete:");
        if (ingredientsMap.containsKey(codeToDelete)) {
            System.out.println("Do you really want to delete this ingredient?");
            if (inputer.inputY("(Y or other)")) {
                ingredientsMap.remove(codeToDelete);
                data.saveData(ingredientsMap, pathName, "save successful.");
                System.out.println("Ingredient deleted successfully!");
            } else {
                System.out.println("Fail.");
            }
        } else {
            System.out.println("The ingredient does not exist.");
        }
    }

    @Override
    public void show() {
        display();
        for (Map.Entry<String, IngredientsInformation> entry : ingredientsMap.entrySet()) {
            String statusCheck = status.checkStatus(entry.getValue().getQuantity(), entry.getValue().getExpirationDate());
            System.out.println(entry.getValue() + status.toString(statusCheck));
        }
    }

    /**
     * Displays the header for the ingredient information.
     */
    public void display() {
        System.out.println("|   Code   |       Name        | Quantity |   Unit   | Expiration Date |      Status     |");
        System.out.println("+----------+-------------------+----------+----------+-----------------+-----------------+");
    }

}
