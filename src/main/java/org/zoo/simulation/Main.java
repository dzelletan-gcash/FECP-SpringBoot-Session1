package org.zoo.simulation;

import org.zoo.simulation.animals.Animal;
import org.zoo.simulation.animals.types.Lion;
import org.zoo.simulation.animals.types.Elephant;
import org.zoo.simulation.animals.types.Owl;
import org.zoo.simulation.people.*;

import java.util.Scanner;

/**
 * Main class for the Zoo Simulation application.
 * This class serves as the entry point and primary controller for the simulation,
 * managing user interactions through a console-based menu system. It directs
 * users to different modules such as Administrator, Handler, and Visitor.
 */
public class Main {

    /**
     * The main entry point for the Zoo Simulation.
     * Initializes the simulation, creates a single Zoo instance, and presents the
     * main menu to switch between different user roles.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Zoo zoo = new Zoo(); // Create one instance of the Zoo

        System.out.println("--- Zoo Simulation Initializing: Administrator Access Required ---");
        System.out.println("  __________             ");
        System.out.println("  \\____    /____   ____  ");
        System.out.println("    /     //  _ \\ /  _ \\ ");
        System.out.println("   /     /(  <_> |  <_> )");
        System.out.println("  /_______ \\____/ \\____/ ");
        System.out.println("          \\/              ");
        runAdminModule(scanner, zoo);

        System.out.println("\n--- Administrator session ended. Entering public access mode. ---");
        // Main simulation loop for public access (Handlers and Visitors).
        while (true) {
            System.out.println("\n=== Zoo Main Entrance ===");
            System.out.println("1. Handler Module");
            System.out.println("2. Visitor Module");
            System.out.println("3. Exit Simulation");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": runHandlerModule(scanner, zoo); break;
                case "2": runVisitorModule(scanner, zoo); break;
                case "3": System.out.println("Exiting simulation. Goodbye!"); return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    /**
     * Runs the administrator module.
     * This method handles the login process for the administrator. If authentication
     * is successful, it proceeds to the main admin menu.
     *
     * @param scanner The Scanner instance for user input.
     * @param zoo The main Zoo instance.
     */
    private static void runAdminModule(Scanner scanner, Zoo zoo) {
        System.out.println("\n=== Welcome to the Zoo Admin Console ===");
        System.out.print("Enter username: ");
        String user = scanner.nextLine();
        System.out.print("Enter password: ");
        String pass = scanner.nextLine();

        if ("admin".equals(user) && "admin123".equals(pass)) {
            System.out.println("Login successful. Welcome!\n");
            adminMenu(scanner, zoo);
        } else {
            System.out.println("Login failed. Exiting admin setup.");
        }
    }

    /**
     * Displays the main menu for administrators.
     * Allows the admin to set up staff, access handler functions, open or close the zoo,
     * or exit the admin module.
     *
     * @param scanner The Scanner instance for user input.
     * @param zoo The main Zoo instance.
     */
    private static void adminMenu(Scanner scanner, Zoo zoo) {
        while (true) {
            System.out.println("========== 🦁 ZOO ADMIN MAIN MENU ==========");
            System.out.println("1. Setup Zoo Staff");
            System.out.println("2. Access Handler Module");
            System.out.println("3. Open Zoo to Visitors");
            System.out.println("4. Close Zoo to Visitors");
            System.out.println("5. Exit Admin Module");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    setupStaff(scanner, zoo);
                    break;
                case "2":
                    runHandlerModule(scanner, zoo);
                    break;
                case "3":
                    zoo.openZoo();
                    break;
                case "4":
                    zoo.closeZoo();
                    break;
                case "5":
                    return; // Exits the admin menu and proceeds to the public-facing loop
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * Guides the administrator through the process of setting up zoo personnel and animals.
     * This includes creating a Manager, Veterinarian, various Handlers, and Vendors,
     * and adding initial animals to the zoo.
     *
     * @param scanner The Scanner instance for user input.
     * @param zoo The main Zoo instance.
     */
    private static void setupStaff(Scanner scanner, Zoo zoo) {
        System.out.println("\n--- Zoo Setup ---");

        System.out.print("Enter your name, Manager: ");
        zoo.setManager(new Manager(scanner.nextLine()));

        System.out.print("Enter Veterinarian's name: ");
        zoo.setVeterinarian(new Veterinarian(scanner.nextLine()));

        System.out.print("Enter Handler for Pachyderm Enclosure: ");
        zoo.addHandler("Pachyderm Handler", new Handler(scanner.nextLine()));

        System.out.print("Enter Handler for Feline Enclosure: ");
        zoo.addHandler("Feline Handler", new Handler(scanner.nextLine()));

        System.out.print("Enter Handler for Bird Enclosure: ");
        zoo.addHandler("Bird Handler", new Handler(scanner.nextLine()));

        System.out.print("Enter Vendor for Ticket Shop: ");
        zoo.addVendor("Ticket Vendor", new Vendor(scanner.nextLine()));

        System.out.print("Enter Vendor for Shop: ");
        zoo.addVendor("Shop Vendor", new Vendor(scanner.nextLine()));

        // Also set up some animals for the simulation
        zoo.addAnimal(new Lion("Mufasa"));
        zoo.addAnimal(new Lion("Simba"));
        zoo.addAnimal(new Elephant("Dumbo"));
        zoo.addAnimal(new Owl("Hedwig"));

        System.out.println("Zoo staff and animals setup complete.\n");
    }

    /**
     * Runs the animal handler module.
     * Authenticates a handler by name and allows them to perform duties such as
     * feeding, exercising, or admitting an animal to the hospital.
     *
     * @param scanner The Scanner instance for user input.
     * @param zoo The main Zoo instance.
     */
    private static void runHandlerModule(Scanner scanner, Zoo zoo) {
        System.out.print("\nEnter your name (Handler): ");
        String name = scanner.nextLine();

        // Find the handler and their specific role (e.g., "Feline Handler")
        String handlerRole = null;
        Handler handler = null;
        for (java.util.Map.Entry<String, Handler> entry : zoo.getHandlers().entrySet()) {
            if (entry.getValue().getName().equalsIgnoreCase(name)) {
                handler = entry.getValue();
                handlerRole = entry.getKey();
                break;
            }
        }

        if (handler == null) {
            System.out.println("No handler found with that name.");
            return;
        }

        System.out.println("Welcome, Handler " + name + "!");

        // Create a new list containing only the animals assigned to this handler
        java.util.List<Animal> assignedAnimals = new java.util.ArrayList<>();
        if (handlerRole != null) {
            switch (handlerRole) {
                case "Feline Handler":
                    for (Animal animal : zoo.getAnimals()) {
                        if (animal instanceof org.zoo.simulation.animals.species.Feline) {
                            assignedAnimals.add(animal);
                        }
                    }
                    break;
                case "Pachyderm Handler":
                    for (Animal animal : zoo.getAnimals()) {
                        if (animal instanceof org.zoo.simulation.animals.species.Pachyderm) {
                            assignedAnimals.add(animal);
                        }
                    }
                    break;
                case "Bird Handler":
                    for (Animal animal : zoo.getAnimals()) {
                        if (animal instanceof org.zoo.simulation.animals.species.Bird) {
                            assignedAnimals.add(animal);
                        }
                    }
                    break;
            }
        }

        // The rest of the module now uses the filtered 'assignedAnimals' list
        while (true) {
            System.out.println("\n--- Animal Duty Menu ---");
            System.out.println("Animals assigned to you:");
            if (assignedAnimals.isEmpty()) {
                System.out.println("There are no animals of your type set up in the zoo.");
                break;
            }
            for (int i = 0; i < assignedAnimals.size(); i++) {
                System.out.println((i + 1) + ". " + assignedAnimals.get(i).getName());
            }
            System.out.print("Choose animal number to interact with (0 to exit): ");
            int animalChoice_idx = Integer.parseInt(scanner.nextLine());

            if (animalChoice_idx == 0) break;

            if (animalChoice_idx < 1 || animalChoice_idx > assignedAnimals.size()) {
                System.out.println("Invalid animal number.");
                continue;
            }

            Animal selectedAnimal = assignedAnimals.get(animalChoice_idx - 1);

            System.out.println("\nChoose action:");
            System.out.println("1. Feed " + selectedAnimal.getName());
            System.out.println("2. Exercise " + selectedAnimal.getName());
            System.out.println("3. Examine " + selectedAnimal.getName() + " to Vet");
            System.out.print("Choose an option: ");
            String actionChoice = scanner.nextLine();

            switch (actionChoice) {
                case "1":
                    handler.feed(selectedAnimal);
                    break;
                case "2":
                    handler.exercise(selectedAnimal);
                    break;
                case "3":
                    System.out.println("Sending to Hospital...");
                    zoo.admitToHospital(selectedAnimal);
                    System.out.println(selectedAnimal.getName() + " admitted at " + java.time.LocalDateTime.now());
                    break;
            }
        }
        System.out.println("Finished duties for the day.");
    }

    /**
     * Runs the visitor module.
     * Checks if the zoo is open. If so, it prompts the visitor for a ticket.
     * If they don't have one, it offers the option to buy one. Validated visitors
     * are then taken to the main visitor menu.
     *
     * @param scanner The Scanner instance for user input.
     * @param zoo The main Zoo instance.
     */
    private static void runVisitorModule(Scanner scanner, Zoo zoo) {
        if (!zoo.isSetupAndOpen()) {
            System.out.println("Sorry, the zoo is not open to visitors yet. Please check back later.");
            return;
        }

        System.out.println("\n=== Visitor Validation ===");
        System.out.print("Do you have a ticket? (yes/no): ");
        String choice = scanner.nextLine();

        if ("no".equalsIgnoreCase(choice)) {
            if (!buyTicket(scanner, zoo)) {
                System.out.println("Ticket purchase cancelled. Returning to main entrance.");
                return;
            }
        }

        System.out.println("\n=== Visitor Entry ===");
        System.out.print("Enter your ticket code: ");
        String code = scanner.nextLine();
        Visitor visitor = zoo.getVisitor(code);

        if (visitor != null) {
            System.out.println("Welcome, " + visitor.getName() + "!");
            zooVisitorMenu(scanner, zoo);
        } else {
            System.out.println("Invalid ticket code.");
        }
    }

    /**
     * Manages the ticket purchasing process for a visitor.
     * Calculates the ticket price based on age, confirms the purchase,
     * and if successful, creates a new Visitor and adds them to the zoo's system.
     *
     * @param scanner The Scanner instance for user input.
     * @param zoo The main Zoo instance.
     * @return {@code true} if the ticket was successfully purchased, {@code false} otherwise.
     */
    private static boolean buyTicket(Scanner scanner, Zoo zoo) {
        System.out.println("\n=== 🎟️ WELCOME TO THE ZOO TICKET SHOP ===");
        System.out.println("Here's what you can experience in the zoo:\n" +
                "Visit Animal Enclosures (Elephant, Lion, Owl)\n" +
                "Buy snacks and drinks from our Shops\n" +
                "Listen to science lectures at the Hospital\n" +
                "Buy fun gifts at our Gift Shop");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your age: ");
        int age = Integer.parseInt(scanner.nextLine());

        String ticketType;
        double price;

        if (age <= 5) { ticketType = "Child"; price = 0.00; }
        else if (age <= 17) { ticketType = "Student"; price = 75.00; }
        else if (age <= 59) { ticketType = "Adult"; price = 150.00; }
        else { ticketType = "Senior"; price = 50.00; }

        System.out.println("You qualify for a " + ticketType.toUpperCase() + " ticket.");
        System.out.printf("Ticket Price: P%.2f\n", price);
        System.out.print("Proceed with purchase? (yes/no) ");
        if ("yes".equalsIgnoreCase(scanner.nextLine())) {
            Visitor newVisitor = new Visitor(name);
            zoo.addVisitor(newVisitor);
            System.out.println("Ticket purchased!");
            System.out.println("Your ticket code is: " + newVisitor.getTicketCode());
            System.out.println("[Ticket added to system]");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Displays the main menu for a visitor who is inside the zoo.
     * Allows the visitor to navigate to different areas like enclosures, the shop,
     * or the hospital, or to leave the zoo.
     *
     * @param scanner The Scanner instance for user input.
     * @param zoo The main Zoo instance.
     */
    private static void zooVisitorMenu(Scanner scanner, Zoo zoo) {
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Visit Enclosure");
            System.out.println("2. Visit Shop");
            System.out.println("3. Visit Hospital");
            System.out.println("4. Leave Zoo");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    visitEnclosure(scanner, zoo);
                    break;
                case "2":
                    visitShop(scanner, zoo);
                    break;
                case "3":
                    visitHospital(scanner, zoo);
                    break;
                case "4":
                    System.out.println("You have left the zoo. Goodbye!");
                    return;
            }
        }
    }

    /**
     * Manages a visitor's interaction with an animal enclosure.
     * The visitor chooses an enclosure type, and the system finds an animal of that
     * type for interaction (e.g., feeding).
     *
     * @param scanner The Scanner instance for user input.
     * @param zoo The main Zoo instance.
     */
    private static void visitEnclosure(Scanner scanner, Zoo zoo) {
        System.out.println("\n===Zoo Enclosure===");
        System.out.println("1. Pachyderm (Elephant)");
        System.out.println("2. Feline (Lion)");
        System.out.println("3. Bird (Owl)");
        System.out.print("Choose an option: ");
        String choice = scanner.nextLine();

        Animal animalToInteract = null;
        Class<? extends Animal> animalType = null;

        // Determine which type of animal to look for based on the user's choice
        switch (choice) {
            case "1":
                animalType = Elephant.class;
                break;
            case "2":
                animalType = Lion.class;
                break;
            case "3":
                animalType = Owl.class;
                break;
            default:
                System.out.println("Invalid enclosure choice.");
                return;
        }

        // Find the first animal of the chosen type in the zoo
        for (Animal animal : zoo.getAnimals()) {
            if (animalType.isInstance(animal)) {
                animalToInteract = animal;
                break;
            }
        }

        // If an animal was found, start the interaction
        if (animalToInteract != null) {
            System.out.print("Would you like to feed " + animalToInteract.getName() + "? (yes/no) ");
            if ("yes".equalsIgnoreCase(scanner.nextLine())) {
                animalToInteract.eat();       // Animal performs its eat action
                animalToInteract.makeSound(); // Animal performs its unique sound
            }
        } else {
            // If no animal of that type was found in the zoo setup
            System.out.println("There are no animals of that type in the enclosure at the moment.");
        }
    }

    /**
     * Simulates a visitor visiting the zoo shop.
     * Displays a list of products and allows the visitor to select an item,
     * view the price, and complete a mock purchase.
     *
     * @param scanner The Scanner instance for user input.
     * @param zoo The main Zoo instance.
     */
    private static void visitShop(Scanner scanner, Zoo zoo) {
        System.out.println("\n=== 🛒 Zoo Shop ===");
        System.out.println("Available Products:");

        java.util.Map<String, Double> products = new java.util.LinkedHashMap<>();
        products.put("Soft Drink", 30.00);
        products.put("Popcorn", 50.00);
        products.put("Plush Toy", 120.00);
        products.put("Keychain", 45.00);

        java.util.List<String> productNames = new java.util.ArrayList<>(products.keySet());

        // Display the products to the user
        for (int i = 0; i < productNames.size(); i++) {
            String name = productNames.get(i);
            double price = products.get(name); // Get the price from the map using the name as the key
            System.out.printf("%d. %s - P%.2f\n", i + 1, name, price);
        }

        System.out.print("Enter the number of the item you want to buy: ");
        String choiceStr = scanner.nextLine();

        try {
            int choice = Integer.parseInt(choiceStr);
            if (choice >= 1 && choice <= productNames.size()) {
                // Get the name from our ordered list
                String selectedName = productNames.get(choice - 1);
                // Get the price from the map using that name
                double selectedPrice = products.get(selectedName);

                System.out.println("Selected:");
                System.out.printf("%s (P%.2f)\n", selectedName, selectedPrice);
                System.out.printf("Total: P%.2f\n", selectedPrice);
                System.out.print("Proceed to checkout? (yes/no) ");

                if ("yes".equalsIgnoreCase(scanner.nextLine())) {
                    System.out.println("Payment successful!");
                    System.out.println("Receipt:");
                    System.out.printf("- %s: P%.2f\n", selectedName, selectedPrice);
                    System.out.printf("Total Paid: P%.2f\n", selectedPrice);
                }
            } else {
                System.out.println("Invalid selection. Please choose a number from the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    /**
     * Manages a visitor's interaction with the zoo hospital.
     * Allows visitors to view lists of sick and healed animals, attend a science lecture
     * by the veterinarian, or exit. Also includes a veterinarian-specific function
     * to heal animals.
     *
     * @param scanner The Scanner instance for user input.
     * @param zoo The main Zoo instance.
     */
    private static void visitHospital(Scanner scanner, Zoo zoo) {
        while (true) {
            System.out.println("\n=== 🏥 Zoo Visitor Hospital Monitor ===");
            System.out.println("1. View Sick Animals");
            System.out.println("2. View Healed Animals");
            System.out.println("3. Attend Science Lecture");
            System.out.println("4. Heal Animals (Veterinarian)");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Sick Animals Currently in Hospital:");
                    if (zoo.getSickAnimals().isEmpty()) {
                        System.out.println("None at the moment.");
                    } else {
                        for (Animal animal : zoo.getSickAnimals()) {
                            System.out.println("- " + animal.getName());
                        }
                    }
                    break;
                case "2":
                    System.out.println("Healed Animals with Timestamps:");
                    if (zoo.getHealedAnimalLog().isEmpty()) {
                        System.out.println("None yet today.");
                    } else {
                        for (String log : zoo.getHealedAnimalLog()) {
                            System.out.println(log);
                        }
                    }
                    break;
                case "3":
                    if (zoo.getVeterinarian() != null) {
                        zoo.getVeterinarian().lecture();
                    } else {
                        System.out.println("The Veterinarian has not been set up yet.");
                    }
                    break;
                case "4":
                    zoo.healAllSickAnimals();
                    break;
                case "5":
                    System.out.println("Exiting Zoo Vet Hospital. Goodbye!");
                    return; // This exits the method and returns to the zooVisitorMenu
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}