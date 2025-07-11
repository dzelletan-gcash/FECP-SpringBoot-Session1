package org.zoo.simulation.animals.types;

import org.zoo.simulation.animals.species.Feline;

public class Lion extends Feline {
    public Lion() {
        super("Lion");
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says: Grrr!");
    }
}
