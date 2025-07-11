package org.zoo.simulation.animals.types;
import org.zoo.simulation.animals.species.Feline;

public class Cheetah extends Feline {
    public Cheetah(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " chirps and purrs!");
    }
}