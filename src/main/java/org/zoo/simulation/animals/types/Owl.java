package org.zoo.simulation.animals.types;
import org.zoo.simulation.animals.species.Bird;

public class Owl extends Bird {
    public Owl(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " hoots softly in the distance.");
    }
}