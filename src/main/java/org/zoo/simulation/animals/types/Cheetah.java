package org.zoo.simulation.animals.types;

import org.zoo.simulation.animals.species.Feline;

public class Cheetah extends Feline {
    public Cheetah() {
        super("Cheetah");
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says: hss!");
    }
}
