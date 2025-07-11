package org.zoo.simulation.animals.types;

import org.zoo.simulation.animals.species.Feline;

public class Lion extends Feline {
    public Lion(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " roars! 🦁");
    }
}