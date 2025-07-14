package org.zoo.simulation;

import org.zoo.simulation.animals.Animal;
import org.zoo.simulation.people.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This class holds the entire state of the Zoo
public class Zoo {
    private boolean isOpen = false;

    // These fields will be populated by the setupStaff method
    private Manager manager;
    private Veterinarian veterinarian;
    private final Map<String, Handler> handlers = new HashMap<>();
    private final Map<String, Vendor> vendors = new HashMap<>();

    // State variables for animals and visitors
    private final List<Animal> animals = new ArrayList<>();
    private final Map<String, Visitor> visitors = new HashMap<>();
    private final List<Animal> sickAnimals = new ArrayList<>();
    private final List<String> healedAnimalLog = new ArrayList<>();

    public Zoo() {
        // The constructor is empty; setup is fully interactive
    }

    // --- Methods to SET staff ---
    public void setManager(Manager manager) { this.manager = manager; }
    public void setVeterinarian(Veterinarian veterinarian) { this.veterinarian = veterinarian; }
    public void addHandler(String role, Handler handler) { handlers.put(role, handler); }
    public void addVendor(String role, Vendor vendor) { vendors.put(role, vendor); }
    public void addAnimal(Animal animal) { this.animals.add(animal); }


    // --- Methods to GET staff and state ---

    // NEW METHOD: Returns the entire map of handlers and their roles.
    public Map<String, Handler> getHandlers() {
        return this.handlers;
    }

    public Handler getHandler(String name) {
        // Find handler by name instead of role for the handler module
        for(Handler handler : handlers.values()) {
            if (handler.getName().equalsIgnoreCase(name)) {
                return handler;
            }
        }
        return null;
    }
    public Veterinarian getVeterinarian() { return veterinarian; }
    public List<Animal> getAnimals() { return this.animals; }
    public Visitor getVisitor(String ticketCode) { return visitors.get(ticketCode); }
    public List<Animal> getSickAnimals() { return sickAnimals; }
    public List<String> getHealedAnimalLog() { return healedAnimalLog; }


    // --- Methods to manage Zoo state ---
    public void openZoo() {
        if (manager == null) {
            System.out.println("Cannot open the zoo. Please set up the Manager first.");
            return;
        }
        this.isOpen = true;
        manager.openZoo();
    }
    public void closeZoo() {
        if (manager == null) {
            System.out.println("Manager not set up.");
            return;
        }
        this.isOpen = false;
        manager.closeZoo();
    }
    public boolean isSetupAndOpen() {
        return manager != null && isOpen;
    }
    public void addVisitor(Visitor visitor) { visitors.put(visitor.getTicketCode(), visitor); }

    public void admitToHospital(Animal animal) {
        if (!sickAnimals.contains(animal)) {
            sickAnimals.add(animal);
        }
    }

    public void healAllSickAnimals() {
        if (sickAnimals.isEmpty()) {
            System.out.println("There are no sick animals to heal.");
            return;
        }
        if(this.veterinarian == null) {
            System.out.println("Veterinarian is not set up.");
            return;
        }
        System.out.println(veterinarian.getName() + " begins healing sick animals...");
        for (Animal animal : new ArrayList<>(sickAnimals)) {
            veterinarian.heal(animal);
            healedAnimalLog.add("✔ Healed: " + animal.getName() + " (" + java.time.LocalDate.now() + ")");
            System.out.println(animal.getName() + " has been discharged and returned to enclosure.");
        }
        sickAnimals.clear();
    }
}