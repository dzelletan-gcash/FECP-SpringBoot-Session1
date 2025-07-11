package org.zoo.simulation;

import org.zoo.simulation.animals.Animal;
import org.zoo.simulation.people.Handler;
import org.zoo.simulation.people.Manager;
import org.zoo.simulation.people.Veterinarian;
import org.zoo.simulation.people.Visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This class holds the entire state of the Zoo
public class Zoo {
    private boolean isOpen = false;
    private final Manager manager;
    private final Veterinarian veterinarian;
    private final Map<String, Handler> handlers = new HashMap<>();
    private final List<Animal> animals = new ArrayList<>();
    private final Map<String, Visitor> visitors = new HashMap<>();
    private final List<Animal> sickAnimals = new ArrayList<>();
    private final List<String> healedAnimalLog = new ArrayList<>();

    public Zoo() {
        // Pre-configure the zoo for the simulation
        this.manager = new Manager("Mr. Hammond");
        this.veterinarian = new Veterinarian("Dr. Ellie");
        handlers.put("Claire", new Handler("Claire"));
        handlers.put("Robert", new Handler("Robert"));
        // ...add animals, etc.
    }

    // Methods to manage the zoo's state, perfectly matching the flowchart
    public void openZoo() { this.isOpen = true; manager.openZoo(); }
    public void closeZoo() { this.isOpen = false; manager.closeZoo(); }
    public boolean isSetupAndOpen() { return !handlers.isEmpty() && isOpen; }
    public void addVisitor(Visitor visitor) { visitors.put(visitor.getTicketCode(), visitor); }
    public Visitor getVisitor(String ticketCode) { return visitors.get(ticketCode); }
    public Handler getHandler(String name) { return handlers.get(name); }
    public Veterinarian getVeterinarian() { return veterinarian; }
    public List<Animal> getSickAnimals() { return sickAnimals; }
    public List<String> getHealedAnimalLog() { return healedAnimalLog; }

    public void admitToHospital(Animal animal) {
        if (!sickAnimals.contains(animal)) {
            sickAnimals.add(animal);
            System.out.println(animal.getName() + " has been admitted to the hospital.");
        }
    }

    public void healAllSickAnimals() {
        if (sickAnimals.isEmpty()) {
            System.out.println("There are no sick animals to heal.");
            return;
        }
        System.out.println(veterinarian.getName() + " begins healing sick animals...");
        for (Animal animal : new ArrayList<>(sickAnimals)) {
            veterinarian.heal(animal);
            healedAnimalLog.add("✔ Healed: " + animal.getName() + " (Timestamp: " + java.time.LocalDateTime.now() + ")");
            System.out.println(animal.getName() + " has been discharged and returned to enclosure.");
        }
        sickAnimals.clear();
    }
}