package org.zoo.simulation.animals.types;

import org.zoo.simulation.animals.species.Feline;

public class Tiger extends Feline {
    public Tiger() {
        super("Tiger");
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says: Roar!");
    }
}
