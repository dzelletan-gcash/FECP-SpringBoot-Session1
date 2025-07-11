package org.zoo.simulation;

import org.zoo.simulation.people.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static boolean isZooOpen = false;
    private static final Map<String, Visitor> visitors = new HashMap<>();
    private static final Manager manager = new Manager("Mr. Hammond");
    private static final Veterinarian vet = new Veterinarian("Dr. Ellie");
    // Simplified setup for demonstration
    private static final Handler felineHandler = new Handler("Claire");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Welcome to the Zoo Simulation ===");
            System.out.println("1. Administrator Console");
            System.out.println("2. Visitor Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": runAdminModule(scanner); break;
                case "2": runVisitorModule(scanner); break;
                case "3": System.out.println("Exiting simulation. Goodbye!"); return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private static void runAdminModule(Scanner scanner) {
        System.out.println("\n=== Welcome to the Zoo Admin Console ===");
        System.out.print("Enter username: ");
        String user = scanner.nextLine();
        System.out.print("Enter password: ");
        String pass = scanner.nextLine();

        if ("admin".equals(user) && "adminadmin".equals(pass)) {
            System.out.println("Login successful. Welcome!\n");
            adminMenu(scanner);
        } else {
            System.out.println("Login failed.");
        }
    }

    private static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("========== 🦁 ZOO ADMIN MAIN MENU ==========");
            System.out.println("1. Setup Zoo Staff");
            System.out.println("2. Open Zoo to Visitors");
            System.out.println("3. Close Zoo to Visitors");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": System.out.println("Staff has been pre-configured for this simulation."); break;
                case "2": manager.openZoo(); isZooOpen = true; break;
                case "3": manager.closeZoo(); isZooOpen = false; break;
                case "4": return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void runVisitorModule(Scanner scanner) {
        System.out.println("\n=== Welcome, Visitor! ===");
        System.out.println("1. Buy a Ticket");
        System.out.println("2. Enter the Zoo");
        System.out.print("Choose an option: ");
        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            buyTicket(scanner);
        } else if ("2".equals(choice)) {
            enterZoo(scanner);
        }
    }

    private static void buyTicket(Scanner scanner) {
        System.out.println("\n=== 🎟️ WELCOME TO THE ZOO TICKET SHOP ===");
        System.out.print("Would you like to buy a ticket? (yes/no): ");
        if (!"yes".equalsIgnoreCase(scanner.nextLine())) return;

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your age: ");
        int age = Integer.parseInt(scanner.nextLine());

        String ticketType;
        double price;

        if (age >= 0 && age <= 5) { ticketType = "Child"; price = 0.00; }
        else if (age >= 6 && age <= 17) { ticketType = "Student"; price = 75.00; }
        else if (age >= 18 && age <= 59) { ticketType = "Adult"; price = 150.00; }
        else { ticketType = "Senior"; price = 50.00; }

        System.out.println("You qualify for a " + ticketType.toUpperCase() + " ticket.");
        System.out.printf("Ticket Price: P%.2f\n", price);
        System.out.print("Proceed with purchase? (yes/no) ");
        if ("yes".equalsIgnoreCase(scanner.nextLine())) {
            Visitor visitor = new Visitor(name);
            visitors.put(visitor.getTicketCode(), visitor);
            System.out.println("Ticket purchased!");
            System.out.println("Your ticket code is: " + visitor.getTicketCode());
            System.out.println("[Ticket added to system]\n");
        }
    }

    private static void enterZoo(Scanner scanner) {
        if (!isZooOpen) {
            System.out.println("Sorry, the Zoo is currently closed.");
            return;
        }
        System.out.print("Enter your ticket code: ");
        String code = scanner.nextLine();
        Visitor visitor = visitors.get(code);

        if (visitor != null) {
            System.out.println("Welcome, " + visitor.getName() + "!");
            zooVisitorMenu(scanner);
        } else {
            System.out.println("Invalid ticket code.");
        }
    }

    private static void zooVisitorMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Visit Enclosure");
            System.out.println("2. Visit Shop");
            System.out.println("3. Visit Hospital");
            System.out.println("4. Leave Zoo");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": visitEnclosure(scanner); break;
                case "2": visitShop(scanner); break;
                case "3": visitHospital(scanner); break;
                case "4": System.out.println("You have left the zoo. Goodbye!"); return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private static void visitEnclosure(Scanner scanner) {
        System.out.println("\n===Zoo Enclosure===");
        System.out.println("1. Pachyderm (Elephant)");
        System.out.println("2. Feline (Lion)");
        System.out.println("3. Bird (Owl)");
        System.out.print("Choose an option: ");
        String choice = scanner.nextLine();
        if ("2".equals(choice)) { // Simulating Feline enclosure
            System.out.print("Would you like to feed Lion? (yes/no) ");
            if ("yes".equalsIgnoreCase(scanner.nextLine())) {
                System.out.println("Lion is eating.");
                System.out.println("Lion roars! 🦁");
            }
        } else {
            System.out.println("You observe the animals in their enclosure.");
        }
    }

    private static void visitShop(Scanner scanner) {
        System.out.println("\n=== 🛒 Zoo Shop ===");
        System.out.println("Available Products:");
        System.out.println("1. Soft Drink - P30");
        System.out.println("2. Popcorn - P50");
        System.out.print("Enter the numbers of the items you want to buy: ");
        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            System.out.println("Total: P30");
            System.out.print("Proceed to checkout? (yes/no) ");
            if ("yes".equalsIgnoreCase(scanner.nextLine())) {
                System.out.println("Payment successful!");
                System.out.println("Receipt: - Soft Drink: P30");
            }
        }
    }

    private static void visitHospital(Scanner scanner) {
        System.out.println("\n=== 🏥 Zoo Visitor Hospital Monitor ===");
        System.out.println("1. View Sick Animals");
        System.out.println("2. Attend Science Lecture");
        System.out.print("Choose an option: ");
        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            System.out.println("Sick Animals Currently in Hospital:");
            System.out.println("- Dumbo");
        } else if ("2".equals(choice)) {
            vet.lecture();
        }
    }
}