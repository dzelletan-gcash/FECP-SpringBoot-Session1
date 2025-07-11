package org.zoo.simulation.animals.types;
import org.zoo.simulation.animals.species.Bird;

public class Falcon extends Bird {
    public Falcon(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " gives a sharp 'kak-kak-kak' cry!");
    }
}