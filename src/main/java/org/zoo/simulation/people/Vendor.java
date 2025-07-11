package org.zoo.simulation.people;

import org.zoo.simulation.buildings.Building;

public class Vendor extends People{
    /**
     * Constructs a new person with the specified name.
     *
     * <p>The person is created without an assigned location. The location
     * must be set separately using the {@link #goTo(Building)} method when
     * the person moves to a specific building within the zoo.</p>
     *
     * @param name the name of the person, must not be null or empty
     * @throws IllegalArgumentException if name is null or empty
     */
    public Vendor(String name) {
        super(name);
    }

    public void sell() {
        System.out.println(getName() + " is ready to sell items.");
    }
}
