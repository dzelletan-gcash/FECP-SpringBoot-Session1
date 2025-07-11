package org.zoo.simulation.animals.species;
import org.zoo.simulation.animals.Animal;


/**
 * Constructs a specie under feline
 *
 * <p>The name should include the name of the feline/p>
 *
 */

public abstract class Feline extends Animal {

    public Feline(String name) {
        super(name);
    }

    @Override
    public void roam() {
        System.out.println(getName() + " is prowling.");
    }

}