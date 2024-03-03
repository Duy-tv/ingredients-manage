package data;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.TreeMap;

/**
 * DataFile class provides methods for loading and saving data to files.
 * It supports loading and saving data for TreeMap and List collections.
 * 
 * @param <T> The type of objects to be stored.
 */
public class DataFile<T extends KeyProvider> {
    /**
     * Loads data from a file into a TreeMap.
     * 
     * @param map The TreeMap to load the data into.
     * @param fileName The name of the file to load data from.
     * @return true if the data is successfully loaded, otherwise false.
     */
    public boolean loadData(TreeMap<String, T> map, String fileName)  {
        map.clear();
        File f = new File(fileName);
        if (!f.exists()) {
            return false;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            int size = ois.readInt(); 
            for (int i = 0; i < size; i++) {
                T item = (T) ois.readObject();
                String key = item.getKey();
                map.put(key, item);
            }
            ois.close();
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from file: " + fileName);
        }
        return false;
    }

    /**
     * Saves data from a TreeMap to a file.
     * 
     * @param map The TreeMap containing data to be saved.
     * @param fileName The name of the file to save data to.
     * @param msg The message to display after saving data.
     * @return true if the data is successfully saved, otherwise false.
     */
    public boolean saveData(TreeMap<String, T> map, String fileName, String msg) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeInt(map.size()); 
            for (T item : map.values()) {
                oos.writeObject(item);
            }
            System.out.println(msg);
            oos.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + fileName);
        }
        return false;
    }
    
    /**
     * Loads data from a file into a List.
     * 
     * @param list The List to load the data into.
     * @param fileName The name of the file to load data from.
     * @return true if the data is successfully loaded, otherwise false.
     */
    public boolean loadOrderData(List<T> list, String fileName) {
        list.clear();
        File file = new File(fileName);
        if (!file.exists()) {
            return false;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            int size = ois.readInt();
            for (int i = 0; i < size; i++) {
                T item = (T) ois.readObject();
                list.add(item);
            }
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data from file: " + fileName);
            return false;
        }
    }
    
    /**
     * Saves data from a List to a file.
     * 
     * @param list The List containing data to be saved.
     * @param fileName The name of the file to save data to.
     * @param msg The message to display after saving data.
     * @return true if the data is successfully saved, otherwise false.
     */
    public boolean saveOrderData(List<T> list, String fileName, String msg) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeInt(list.size());
            for (T item : list) {
                oos.writeObject(item);
            }
            System.out.println(msg);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + fileName);
            return false;
        }
    }
}
