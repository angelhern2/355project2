package it355project2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class Menu {
    private Map<String, MenuItem> items;

    /**
     * Initializes an empty menu.
     */
    public Menu() {
        items = new HashMap<>();
    }

    /**
     * Adds a new item to the menu.
     *
     * @param item The MenuItem to add.
     */
    public void addItem(MenuItem item) {
        items.put(item.getId(), item);
    }

    /**
     * Removes an item from the menu based on its id.
     *
     * @param id The id of the MenuItem to remove.
     * @return true if the item was removed, false otherwise.
     */
    public boolean removeItem(String id) {
        if (items.containsKey(id)) {
            items.remove(id);
            return true;
        }
        return false;
    }

    /**
     * Retrieves all menu items.
     *
     * @return A collection of all MenuItem objects.
     */
    public Collection<MenuItem> getAllItems() {
        return items.values();
    }

    /**
     * Retrieves a menu item by its id.
     *
     * @param id The id of the MenuItem.
     * @return The MenuItem if found, null otherwise.
     */
    public MenuItem getItemById(String id) {
        return items.get(id);
    }

    /**
     * Displays the entire menu to the console.
     */
    public void displayMenu() {
        if (items.isEmpty()) {
            System.out.println("\n--- Menu is currently empty ---\n");
            return;
        }

        System.out.println("\n--- Restaurant Menu ---");
        for (MenuItem item : items.values()) {
            System.out.println(item);
        }
        System.out.println("-----------------------\n");
    }

    /**
     * Loads menu items from a specified .txt file.
     *
     * @param filePath The path to the menu file.
     * @throws IOException If an I/O error occurs.
     */
    public void loadFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                // Skip empty lines
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.out.println("Invalid format in line " + lineNumber + ": " + line);
                    continue; // Skip invalid lines
                }

                String id = parts[0].trim();
                String name = parts[1].trim();
                double price;

                try {
                    price = Double.parseDouble(parts[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price in line " + lineNumber + ": " + parts[2]);
                    continue; // Skip lines with invalid price
                }

                // Check for duplicate IDs
                if (items.containsKey(id)) {
                    System.out.println("Duplicate ID in line " + lineNumber + ": " + id);
                    continue; // Skip duplicate IDs
                }

                MenuItem item = new MenuItem(id, name, price);
                addItem(item);
            }
        }
    }

    /**
     * Saves the current menu to a specified .txt file.
     *
     * @param filePath The path to save the menu file.
     * @throws IOException If an I/O error occurs.
     */
    public void saveToFile(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (MenuItem item : items.values()) {
                String line = String.format("%s,%s,%.2f%n", item.getId(), item.getName(), item.getPrice());
                writer.write(line);
            }
        }
    }
}
