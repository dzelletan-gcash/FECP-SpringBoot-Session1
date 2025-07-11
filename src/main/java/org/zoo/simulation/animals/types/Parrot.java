package org.zoo.simulation.animals.types;
import org.zoo.simulation.animals.species.Bird;

public class Parrot extends Bird {
    public Parrot(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " squawks 'Polly wants a cracker!'");
    }
}