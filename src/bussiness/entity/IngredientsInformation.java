package bussiness.entity;

import data.KeyProvider;
import java.io.Serializable;

/**
 * IngredientsInformation class represents information about an ingredient.
 * It implements the Serializable interface and extends the KeyProvider interface.
 * It provides methods to access and modify the properties of an ingredient.
 * 
 * @author Duy.Tran
 */
public class IngredientsInformation implements Serializable, KeyProvider {
   
    private String code;
    private String name;
    private String expirationDate;
    private String unit;
    private int quantity;

    /**
     * Default constructor for IngredientsInformation.
     */
    public IngredientsInformation() {
    }

    /**
     * Parameterized constructor for IngredientsInformation.
     * 
     * @param code The code of the ingredient.
     * @param name The name of the ingredient.
     * @param quantity The quantity of the ingredient.
     * @param unit The unit of measurement for the ingredient.
     * @param expirationDate The expiration date of the ingredient.
     */
    public IngredientsInformation(String code, String name, int quantity, String unit, String expirationDate) {
        this.code = code;
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    /**
     * Retrieves the code of the ingredient.
     * 
     * @return The code of the ingredient.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code of the ingredient.
     * 
     * @param code The code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Retrieves the name of the ingredient.
     * 
     * @return The name of the ingredient.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the ingredient.
     * 
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the unit of measurement for the ingredient.
     * 
     * @return The unit of measurement.
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the unit of measurement for the ingredient.
     * 
     * @param unit The unit of measurement to set.
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Retrieves the quantity of the ingredient.
     * 
     * @return The quantity of the ingredient.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the ingredient.
     * 
     * @param quantity The quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Retrieves the expiration date of the ingredient.
     * 
     * @return The expiration date of the ingredient.
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the ingredient.
     * 
     * @param expirationDate The expiration date to set.
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Retrieves the key of the ingredient, which is its code.
     * 
     * @return The code of the ingredient.
     */
    @Override
    public String getKey() {
        return getCode();
    }

    /**
     * Generates a string representation of the ingredient.
     * 
     * @return A string containing the code, name, quantity, unit, and expiration date of the ingredient.
     */
    @Override
    public String toString() {
        return String.format("|%10s|%19s|%10d|%10s|%17s|", code, name, quantity, unit, expirationDate);
    }
}
