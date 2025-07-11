package org.zoo.simulation.animals.species;
import org.zoo.simulation.animals.Animal;

/**
 * Constructs a specie under bird
 *
 * <p>The name should include the name of the bird</p>
 *
 */

public abstract class Bird extends Animal {

    public Bird(String name) {
        super(name);
    }

    @Override
    public void roam() {
        System.out.println(getName() + " is flying around");
    }

}