package it355project2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private Map<MenuItem, Integer> orderItems;

    /**
     * Initializes a new Order.
     */
    public Order() {
        orderItems = new HashMap<>();
    }

    /**
     * Adds a MenuItem to the order.
     *
     * @param item     The MenuItem to add.
     * @param quantity The quantity of the item.
     */
    public void addItem(MenuItem item, int quantity) {
        if (orderItems.containsKey(item)) {
            orderItems.put(item, orderItems.get(item) + quantity);
        } else {
            orderItems.put(item, quantity);
        }
    }

    /**
     * Removes a MenuItem from the order.
     *
     * @param item The MenuItem to remove.
     * @return true if the item was removed, false otherwise.
     */
    public boolean removeItem(MenuItem item) {
        if (orderItems.containsKey(item)) {
            orderItems.remove(item);
            return true;
        }
        return false;
    }

    /**
     * Retrieves all items in the order.
     *
     * @return A map of MenuItem objects to their quantities.
     */
    public Map<MenuItem, Integer> getOrderItems() {
        return orderItems;
    }

    /**
     * Calculates the total price of the order.
     *
     * @return The total price.
     */
    public double calculateTotal() {
        double total = 0.0;
        for (Map.Entry<MenuItem, Integer> entry : orderItems.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    /**
     * Displays the current order details.
     */
    public void displayOrder() {
        if (orderItems.isEmpty()) {
            System.out.println("Your order is currently empty.");
            return;
        }

        System.out.println("\n--- Your Order ---");
        for (Map.Entry<MenuItem, Integer> entry : orderItems.entrySet()) {
            System.out.printf("%s x%d - $%.2f%n",
                    entry.getKey().getName(),
                    entry.getValue(),
                    entry.getKey().getPrice() * entry.getValue());
        }
        System.out.printf("Total: $%.2f%n", calculateTotal());
        System.out.println("------------------\n");
    }

    /**
     * Generates a receipt and writes it to the specified file.
     *
     * @param filePath The path or name of the file to save the receipt.
     * @throws IOException If an I/O error occurs.
     */
    public void generateReceipt(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("----- Receipt -----\n");
            for (Map.Entry<MenuItem, Integer> entry : orderItems.entrySet()) {
                writer.write(String.format("%s x%d - $%.2f%n",
                        entry.getKey().getName(),
                        entry.getValue(),
                        entry.getKey().getPrice() * entry.getValue()));
            }
            writer.write(String.format("Total: $%.2f%n", calculateTotal()));
            writer.write("-------------------\n");
            writer.write("Thank you for your purchase!\n");
        }
        // Clear the order after generating the receipt
        orderItems.clear();
    }
}
