package org.zoo.simulation;

import org.zoo.simulation.animals.Animal;
import org.zoo.simulation.animals.types.Lion;
import org.zoo.simulation.animals.types.Elephant;
import org.zoo.simulation.animals.types.Owl;
import org.zoo.simulation.people.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Zoo zoo = new Zoo(); // Create one instance of the Zoo

        // This main loop follows the flowchart
        while (true) {
            System.out.println("\n=== Welcome to the Zoo Simulation ===");
            System.out.println("1. Administrator Module");
            System.out.println("2. Handler Module");
            System.out.println("3. Visitor Module");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": runAdminModule(scanner, zoo); break;
                case "2": runHandlerModule(scanner, zoo); break;
                case "3": runVisitorModule(scanner, zoo); break;
                case "4": System.out.println("Exiting simulation. Goodbye!"); return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    // Corresponds to the "Admin Module" box in the flowchart
    private static void runAdminModule(Scanner scanner, Zoo zoo) {
        System.out.println("\n=== Welcome to the Zoo Admin Console ===");
        System.out.print("Enter username: ");
        String user = scanner.nextLine();
        System.out.print("Enter password: ");
        String pass = scanner.nextLine();

        if ("admin".equals(user) && "adminadmin".equals(pass)) {
            System.out.println("Login successful. Welcome!\n");
            adminMenu(scanner, zoo);
        } else {
            System.out.println("Login failed.");
        }
    }

    private static void adminMenu(Scanner scanner, Zoo zoo) {
        while (true) {
            System.out.println("========== 🦁 ZOO ADMIN MAIN MENU ==========");
            System.out.println("1. Setup Zoo Staff");
            System.out.println("2. Open Zoo to Visitors");
            System.out.println("3. Close Zoo to Visitors");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // This now calls the interactive setup method
                    setupStaff(scanner, zoo);
                    break;
                case "2":
                    zoo.openZoo();
                    break;
                case "3":
                    zoo.closeZoo();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // NEW: Fully interactive staff setup method
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

    // Implements the "Handler Module" from the screenshots and flowchart
    private static void runHandlerModule(Scanner scanner, Zoo zoo) {
        System.out.print("\nEnter your name (Handler): ");
        String name = scanner.nextLine();
        Handler handler = zoo.getHandler(name);

        if (handler == null) {
            System.out.println("No handler found with that name.");
            return;
        }

        System.out.println("Welcome, Handler " + name + "!");

        while (true) {
            System.out.println("\n--- Animal Duty Menu ---");
            System.out.println("Animals in the zoo:");
            // List animals from the Zoo object for interaction
            for (int i = 0; i < zoo.getAnimals().size(); i++) {
                System.out.println((i + 1) + ". " + zoo.getAnimals().get(i).getName());
            }
            System.out.print("Choose animal number to interact with (0 to exit): ");
            int animalChoice_idx = Integer.parseInt(scanner.nextLine());

            if (animalChoice_idx == 0) break;

            Animal selectedAnimal = zoo.getAnimals().get(animalChoice_idx - 1);

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

    // Corresponds to the "Ticketing Module" and "Zoo Module" in the flowchart
    private static void runVisitorModule(Scanner scanner, Zoo zoo) {
        if (!zoo.isSetupAndOpen()) {
            System.out.println("Sorry, the zoo is not open to visitors yet. Please check back later.");
            return;
        }

        System.out.println("\n=== Visitor Validation ===");
        System.out.print("Do you have a ticket? (yes/no): ");
        String choice = scanner.nextLine();

        if ("no".equalsIgnoreCase(choice)) {
            buyTicket(scanner, zoo);
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

    private static void buyTicket(Scanner scanner, Zoo zoo) {
        System.out.println("\n=== 🎟️ WELCOME TO THE ZOO TICKET SHOP ===");
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
        }
    }

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
                    // This logic would be expanded to show specific enclosures
                    System.out.println("You enjoy watching the animals in their enclosures.");
                    break;
                case "2":
                    // This logic would be expanded to allow purchasing items
                    System.out.println("You browse the gift shop.");
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

    private static void visitHospital(Scanner scanner, Zoo zoo) {
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
                if(zoo.getSickAnimals().isEmpty()) {
                    System.out.println("None at the moment.");
                } else {
                    for (Animal animal : zoo.getSickAnimals()) {
                        System.out.println("- " + animal.getName());
                    }
                }
                break;
            case "2":
                System.out.println("Healed Animals with Timestamps:");
                if(zoo.getHealedAnimalLog().isEmpty()) {
                    System.out.println("None yet today.");
                } else {
                    for (String log : zoo.getHealedAnimalLog()) {
                        System.out.println(log);
                    }
                }
                break;
            case "3":
                zoo.getVeterinarian().lecture();
                break;
            case "4":
                zoo.healAllSickAnimals();
                break;
            case "5":
                System.out.println("Exiting Zoo Vet Hospital. Goodbye!");
        }
    }
}