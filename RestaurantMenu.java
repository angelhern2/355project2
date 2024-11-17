package it355project2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        System.out.println("7. Save Menu to File");
        System.out.println("8. Exit");
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

        // Initialize a list to keep track of all receipt file paths
        List<String> receiptFiles = new ArrayList<>();

        // Initialize a variable to keep track of total sales
        double totalSales = 0.0;

        // Initialize an order counter for unique receipt naming
        int orderCounter = 1;

        // Define the directory path for receipts and summaries
        String outputDirectory = "it355project2";

        // Ensure the output directory exists
        File directory = new File(outputDirectory);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Created directory: " + outputDirectory);
            } else {
                System.out.println("Failed to create directory: " + outputDirectory);
                System.out.println("Receipts and summaries will be saved in the current directory.");
                outputDirectory = "."; // Fallback to current directory
            }
        }

        System.out.println("Welcome to the Restaurant!");

        // Prompt user to load menu from file
        System.out.print("Would you like to load the menu from a file? (yes/no): ");
        String loadChoice = scanner.nextLine().trim().toLowerCase();

        if (loadChoice.equals("yes") || loadChoice.equals("y")) {
            System.out.print("Enter the path to the menu file (e.g., menu.txt): ");
            String menuFilePath = scanner.nextLine().trim();

            if (menuFilePath.isEmpty()) {
                System.out.println("No file path provided. Starting with an empty menu.");
            } else {
                File menuFile = new File(menuFilePath);
                if (menuFile.exists() && menuFile.isFile()) {
                    try {
                        menu.loadFromFile(menuFilePath);
                        System.out.println("Menu loaded successfully from " + menuFilePath + "\n");
                    } catch (IOException e) {
                        System.out.println("An error occurred while loading the menu: " + e.getMessage());
                        System.out.println("Starting with an empty menu.\n");
                    }
                } else {
                    System.out.println("File does not exist or is not a valid file. Starting with an empty menu.\n");
                }
            }
        } else {
            System.out.println("Starting with an empty menu. You can add items manually.\n");
        }

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
                    // Calculate the order total before generating the receipt
                    double orderTotal = order.calculateTotal();

                    // Generate a unique receipt file name using the order counter
                    String defaultReceiptName = "receipt" + orderCounter + ".txt";
                    System.out.print("Enter the file name to save the receipt (e.g., " + defaultReceiptName + "): ");
                    String userFileName = scanner.nextLine().trim();

                    // If the user doesn't provide a file name, use the default
                    String filePath;
                    if (userFileName.isEmpty()) {
                        filePath = defaultReceiptName;
                    } else {
                        // Ensure the file name ends with .txt
                        if (!userFileName.endsWith(".txt")) {
                            userFileName += ".txt";
                        }
                        filePath = userFileName;
                    }

                    // Prepend the output directory to the file path
                    filePath = outputDirectory + File.separator + filePath;

                    try {
                        order.generateReceipt(filePath);
                        System.out.println("Receipt has been saved to " + filePath);
                        System.out.println("Thank you for your order!\n");

                        // Update the list of receipt files and total sales
                        receiptFiles.add(filePath);
                        totalSales += orderTotal;

                        // Increment the order counter for the next receipt
                        orderCounter++;

                    } catch (IOException e) {
                        System.out.println("An error occurred while writing the receipt: " + e.getMessage());
                    }
                    break;
                case "7":
                    // Save Menu to File
                    System.out.print("Enter the file name to save the current menu (e.g., current_menu.txt): ");
                    String saveMenuFileName = scanner.nextLine().trim();

                    if (saveMenuFileName.isEmpty()) {
                        System.out.println("File name cannot be empty. Menu not saved.");
                        break;
                    }

                    // Ensure the file name ends with .txt
                    if (!saveMenuFileName.endsWith(".txt")) {
                        saveMenuFileName += ".txt";
                    }

                    // Prepend the output directory to the file path
                    String saveMenuFilePath = outputDirectory + File.separator + saveMenuFileName;

                    try {
                        menu.saveToFile(saveMenuFilePath);
                        System.out.println("Menu has been saved to " + saveMenuFilePath);
                    } catch (IOException e) {
                        System.out.println("An error occurred while saving the menu: " + e.getMessage());
                    }
                    break;
                case "8":
                    // Prompt the user to generate a summary file
                    System.out.print("Would you like to generate a summary of all receipts? (yes/no): ");
                    String summaryChoice = scanner.nextLine().trim().toLowerCase();

                    if (summaryChoice.equals("yes") || summaryChoice.equals("y")) {
                        System.out.print("Enter the file name for the summary (e.g., summary.txt): ");
                        String summaryFileName = scanner.nextLine().trim();

                        if (summaryFileName.isEmpty()) {
                            System.out.println("File name cannot be empty. Summary will not be generated.");
                        } else {
                            // Ensure the file name ends with .txt
                            if (!summaryFileName.endsWith(".txt")) {
                                summaryFileName += ".txt";
                            }

                            // Prepend the output directory to the summary file path
                            String summaryFilePath = outputDirectory + File.separator + summaryFileName;

                            try (FileWriter summaryWriter = new FileWriter(summaryFilePath)) {
                                summaryWriter.write("----- Summary of All Receipts -----\n\n");

                                if (receiptFiles.isEmpty()) {
                                    summaryWriter.write("No receipts were generated during this session.\n");
                                } else {
                                    for (String receipt : receiptFiles) {
                                        summaryWriter.write("Receipt File: " + receipt + "\n");
                                    }
                                    summaryWriter.write("\nTotal Sales: $" + String.format("%.2f", totalSales) + "\n");
                                }

                                summaryWriter.write("\nThank you for using the Restaurant Management System!\n");
                                System.out.println("Summary has been saved to " + summaryFilePath);
                            } catch (IOException e) {
                                System.out.println("An error occurred while writing the summary: " + e.getMessage());
                            }
                        }
                    }

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
