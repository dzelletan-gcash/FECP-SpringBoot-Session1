package org.zoo.simulation.animals.types;

import org.zoo.simulation.animals.species.Bird;

public class Owl extends Bird {
    public Owl() {
        super("Owl");
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says: HOOT!");
    }
}
