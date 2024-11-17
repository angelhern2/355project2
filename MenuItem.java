package it355project2;

public class MenuItem {
    private String id;
    private String name;
    private double price;

    /**
     * Constructs a new MenuItem with the specified id, name, and price.
     *
     * @param id    The unique identifier for the menu item.
     * @param name  The name of the menu item.
     * @param price The price of the menu item.
     */
    public MenuItem(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // toString method for display purposes
    @Override
    public String toString() {
        return String.format("%s. %s - $%.2f", id, name, price);
    }
}