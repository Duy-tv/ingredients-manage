/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 * This interface defines a contract for classes that can provide a key.
 * 
 * @author Duy.Tran
 */
public interface KeyProvider {
  /**
   * This method returns the key provided by the implementing class.
   * 
   * @return the key as a String
   */
  String getKey();
}
