package org.zoo.simulation.animals.types;
import org.zoo.simulation.animals.species.Pachyderm;

public class Hippo extends Pachyderm {
    public Hippo(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " grunts and groans!");
    }
}