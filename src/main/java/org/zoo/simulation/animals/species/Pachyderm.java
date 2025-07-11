package org.zoo.simulation.animals.species;
import org.zoo.simulation.animals.Animal;

/**
 * Constructs a specie under pachyderm
 *
 * <p>The name should include the name of the pachyderm</p>
 *
 */

public abstract class Pachyderm extends Animal {

    public Pachyderm(String name) {
        super(name);
    }

    @Override
    public void roam() {
        System.out.println(getName() + " is slowly wandering.");
    }

}