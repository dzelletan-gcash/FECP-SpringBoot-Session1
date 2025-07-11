package org.zoo.simulation.people;
import org.zoo.simulation.animals.Animal;

public class Veterinarian extends People {
    public Veterinarian(String name) { super(name); }

    public void heal(Animal animal) {
        System.out.println(getName() + " is healing " + animal.getName() + ".");
    }

    public void lecture() {
        System.out.println(getName() + " gives a science lecture on animal health and conservation.");
    }
}