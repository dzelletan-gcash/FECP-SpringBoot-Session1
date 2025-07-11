package org.zoo.simulation.animals.types;

import org.zoo.simulation.animals.species.Pachyderm;

public class Hippo extends Pachyderm {
    public Hippo() {
        super("Hippo");
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says: Honk! Honk!");
    }
}
