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

    // Getters

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

    // Override equals and hashCode to ensure MenuItem can be compared based on id

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MenuItem other = (MenuItem) obj;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
