package org.zoo.simulation.animals.types;
import org.zoo.simulation.animals.species.Feline;

public class Tiger extends Feline {
    public Tiger(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " lets out a deep chuff!");
    }
}