/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness.service;

/**
 * ServiceManagement interface defines methods for managing entities.
 * It includes methods for adding, updating, deleting, and showing entities.
 * 
 * @author Duy.Tran
 */
public interface ServiceManagement {
    /**
     * Adds a new entity.
     */
    public void add();
    
    /**
     * Updates an existing entity.
     */
    public void update();
    
    /**
     * Deletes an existing entity.
     */
    public void delete();
    
    /**
     * Displays all entities.
     */
    public void show();
}
