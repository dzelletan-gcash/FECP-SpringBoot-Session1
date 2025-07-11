package org.zoo.simulation.animals.types;

import org.zoo.simulation.animals.species.Pachyderm;

public class Rhino extends Pachyderm {
    public Rhino() {
        super("Rhino");
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says: Grunt!");
    }
}
