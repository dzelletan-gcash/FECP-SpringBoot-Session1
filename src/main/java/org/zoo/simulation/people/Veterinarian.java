package org.zoo.simulation.people;

import org.zoo.simulation.animals.Animal;

public class Veterinarian extends People {
    /**
     * Constructs a new person with the specified name.
     *
     * <p>The person is created without an assigned location. The location
     * must be set separately using the  method when
     * the person moves to a specific building within the zoo.</p>
     *
     * @param name the name of the person, must not be null or empty
     * @throws IllegalArgumentException if name is null or empty
     */
    public Veterinarian(String name) {
        super(name);
    }

    public String heal(Animal animal) {
        return String.format("%s the veterinarian is healing %s...", getName(), animal.getName());
    }

    public String lecture() {
        return String.format(getName() + " gives a science lecture on animal health and conservation.");
    }
}
