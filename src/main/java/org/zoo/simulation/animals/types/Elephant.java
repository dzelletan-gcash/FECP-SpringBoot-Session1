package org.zoo.simulation.animals.types;
import org.zoo.simulation.animals.species.Pachyderm;

public class Elephant extends Pachyderm {
    public Elephant(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " trumpets powerfully!");
    }
}