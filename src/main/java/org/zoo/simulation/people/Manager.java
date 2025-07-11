package org.zoo.simulation.people;

public class Manager extends People {
    public Manager(String name) { super(name); }
    public void openZoo() { System.out.println("Zoo is now OPEN to visitors."); }
    public void closeZoo() { System.out.println("Zoo is now CLOSED to visitors."); }
}