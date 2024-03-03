package bussiness.entity;

import java.util.List;

/**
 * MenuInformation class represents information about a drink menu item.
 * It extends IngredientsInformation class and contains a list of ingredients.
 * It provides methods to access and modify the ingredients list.
 * 
 * @author Duy.Tran
 */
public class MenuInformation extends IngredientsInformation {

    private List<IngredientsInformation> ingredientsList;

    /**
     * Default constructor for MenuInformation.
     */
    public MenuInformation() {
    }

    /**
     * Parameterized constructor for MenuInformation.
     * 
     * @param code The code of the menu item.
     * @param name The name of the menu item.
     * @param quantity The quantity of the menu item.
     * @param unit The unit of measurement for the menu item.
     * @param expirationDate The expiration date of the menu item.
     * @param quantities The quantities of the menu item.
     */
    public MenuInformation(String code, String name, int quantity, String unit, String expirationDate, int quantities) {
        super(code, name, quantity, unit, expirationDate);
    }

    /**
     * Retrieves the list of ingredients for the menu item.
     * 
     * @return The list of ingredients.
     */
    public List<IngredientsInformation> getIngredientsList() {
        return ingredientsList;
    }

    /**
     * Sets the list of ingredients for the menu item.
     * 
     * @param ingredientsList The list of ingredients to set.
     */
    public void setIngredientsList(List<IngredientsInformation> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    /**
     * Retrieves the key of the menu item, which is its code.
     * 
     * @return The code of the menu item.
     */
    @Override
    public String getKey() {
        return super.getCode();
    }

    /**
     * Generates a string representation of the menu item.
     * 
     * @return A string containing the code and name of the menu item.
     */
    @Override
    public String toString() {
        return String.format("|%12s|%19s|", getCode(), getName());
    }
}
