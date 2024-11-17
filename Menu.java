package it355project2;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class Menu {
    private Map<String, MenuItem> items;

    /**
     * Initializes the menu with default items.
     */
    public Menu() {
        items = new HashMap<>();
        // Adding default menu items
        addItem(new MenuItem("1", "Spaghetti", 12.99));
        addItem(new MenuItem("2", "Pizza", 15.99));
        addItem(new MenuItem("3", "Salad", 9.99));
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
        System.out.println("\n--- Restaurant Menu ---");
        for (MenuItem item : items.values()) {
            System.out.println(item);
        }
        System.out.println("-----------------------\n");
    }
}