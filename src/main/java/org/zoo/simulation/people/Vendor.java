package org.zoo.simulation.people;

public class Vendor extends People {
    public Vendor(String name) {
        super(name);
    }

    public void sell() {
        // This method would contain logic for selling items from a shop.
        System.out.println(getName() + " is ready to sell items.");
    }
}