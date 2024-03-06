package bussiness.entity;

import data.KeyProvider;
import java.io.Serializable;

/**
 * DispensingDrink class represents information about a drink being dispensed.
 * It implements the Serializable interface and extends the KeyProvider interface.
 * It provides methods to access and modify the properties of a dispensing drink.
 * 
 * @author Duy.Tran
 */
public class DispensingDrink implements Serializable, KeyProvider {

    private String code;
    private String name;
    private int quantity;
    private int orderNum;

    /**
     * Constructs a DispensingDrink object with the specified code, name, and quantity.
     * 
     * @param code The code of the drink being dispensed.
     * @param name The name of the drink being dispensed.
     * @param quantity The quantity of the drink being dispensed.
     */
    public DispensingDrink( String code, String name, int quantity) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.orderNum = 0;
    }

    /**
     * Retrieves the code of the drink being dispensed.
     * 
     * @return The code of the drink being dispensed.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code of the drink being dispensed.
     * 
     * @param code The code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Retrieves the name of the drink being dispensed.
     * 
     * @return The name of the drink being dispensed.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the drink being dispensed.
     * 
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the quantity of the drink being dispensed.
     * 
     * @return The quantity of the drink being dispensed.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the drink being dispensed.
     * 
     * @param quantity The quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * Retrieves the key of the dispensing drink, which is its code.
     * 
     * @return The code of the drink being dispensed.
     */
    @Override
    public String getKey() {
        return getCode();
    }

    /**
     * Generates a string representation of the dispensing drink.
     * 
     * @return A string containing the code, name, and quantity of the drink being dispensed.
     */
    @Override
    public String toString() {
        return String.format("|%7d|%10s|%19s|%10d|",orderNum, code, name, quantity);
    }

}
