package org.zoo.simulation.animals.types;

import org.zoo.simulation.animals.species.Pachyderm;

public class Rhino extends Pachyderm {
    public Rhino(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " snorts loudly!");
    }
}