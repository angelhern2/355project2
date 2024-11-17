package it355project2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RestaurantMenu {

    /**
     * Displays the main menu options to the user.
     */
    private static void displayMainOptions() {
        System.out.println("Please select an option:");
        System.out.println("1. View Menu");
        System.out.println("2. Add Menu Item");
        System.out.println("3. Remove Menu Item");
        System.out.println("4. Place Order");
        System.out.println("5. View Current Order");
        System.out.println("6. Finalize Order");
        System.out.println("7. Exit");
    }

    /**
     * The entry point of the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        Order order = new Order();
        boolean running = true;

        System.out.println("Welcome to the Restaurant!");

        while (running) {
            displayMainOptions();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    menu.displayMenu();
                    break;
                case "2":
                    System.out.print("Enter new item ID: ");
                    String newId = scanner.nextLine().trim();
                    if (menu.getItemById(newId) != null) {
                        System.out.println("Item ID already exists. Please try again.");
                        break;
                    }
                    System.out.print("Enter new item name: ");
                    String newName = scanner.nextLine().trim();
                    System.out.print("Enter new item price: ");
                    String priceInput = scanner.nextLine().trim();
                    double newPrice;
                    try {
                        newPrice = Double.parseDouble(priceInput);
                        if (newPrice < 0) {
                            System.out.println("Price cannot be negative. Please try again.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price format. Please enter a numeric value.");
                        break;
                    }
                    MenuItem newItem = new MenuItem(newId, newName, newPrice);
                    menu.addItem(newItem);
                    System.out.println("Item added successfully!");
                    break;
                case "3":
                    System.out.print("Enter the ID of the item to remove: ");
                    String removeId = scanner.nextLine().trim();
                    if (menu.removeItem(removeId)) {
                        System.out.println("Item removed successfully!");
                    } else {
                        System.out.println("Item not found. Please try again.");
                    }
                    break;
                case "4":
                    menu.displayMenu();
                    System.out.print("Enter the ID of the item to order: ");
                    String orderId = scanner.nextLine().trim();
                    MenuItem orderItem = menu.getItemById(orderId);
                    if (orderItem != null) {
                        System.out.print("Enter quantity: ");
                        String qtyInput = scanner.nextLine().trim();
                        int quantity;
                        try {
                            quantity = Integer.parseInt(qtyInput);
                            if (quantity <= 0) {
                                System.out.println("Quantity must be at least 1.");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid quantity. Please enter a numeric value.");
                            break;
                        }
                        order.addItem(orderItem, quantity);
                        System.out.println("Item added to your order!");
                    } else {
                        System.out.println("Item not found. Please try again.");
                    }
                    break;
                case "5":
                    order.displayOrder();
                    break;
                case "6":
                    if (order.getOrderItems().isEmpty()) {
                        System.out.println("Your order is empty. Please add items before finalizing.");
                        break;
                    }
                    System.out.print("Enter the file path or name to save the receipt (e.g., receipt.txt): ");
                    String filePath = scanner.nextLine().trim();
                    if (filePath.isEmpty()) {
                        System.out.println("File path cannot be empty.");
                        break;
                    }
                    try {
                        order.generateReceipt(filePath);
                        System.out.println("Receipt has been saved to " + filePath);
                        System.out.println("Thank you for your order!\n");
                    } catch (IOException e) {
                        System.out.println("An error occurred while writing the receipt: " + e.getMessage());
                    }
                    break;
                case "7":
                    System.out.println("Exiting out!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }
}
