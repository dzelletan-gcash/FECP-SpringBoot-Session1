package org.zoo.simulation.animals.types;

import org.zoo.simulation.animals.species.Pachyderm;

public class Elephant extends Pachyderm {
    public Elephant() {
        super("Elephant");
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says: Trumpet!");
    }
}
