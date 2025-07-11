package org.zoo.simulation.animals.types;

import org.zoo.simulation.animals.species.Bird;

public class Parrot extends Bird {

    public Parrot() {
        super("Parrot");
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says: Squawk!");
    }
}
