package org.zoo.simulation.people;

import org.zoo.simulation.animals.Animal;
import org.zoo.simulation.buildings.Hospital;

public class Handler extends People {
    public Handler(String name) {
        super(name);
    }

    public void feed(Animal animal) {
        System.out.println(getName() + " feeds " + animal.getName() + ".");
        animal.eat();
    }

    public void exercise(Animal animal) {
        System.out.println(getName() + " exercises " + animal.getName() + ".");
        animal.roam();
    }

    public void examine(Animal animal, Hospital hospital) {
        System.out.println(getName() + " examines " + animal.getName() + "...");
        // Simple logic to decide if an animal is sick
        if (Math.random() > 0.8) { // 20% chance of being sick
            System.out.println("Oh no! " + animal.getName() + " is sick and needs to go to the hospital.");
            animal.setLocation(hospital);
        } else {
            System.out.println(animal.getName() + " is healthy.");
        }
    }
}