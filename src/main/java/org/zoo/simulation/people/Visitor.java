package org.zoo.simulation.people;

import org.zoo.simulation.buildings.Building;
import java.util.concurrent.atomic.AtomicInteger;

public class Visitor extends People {
    private final String ticketCode;
    private static final AtomicInteger ticketCounter = new AtomicInteger(6742);

    /**
     * Constructs a new person with the specified name.
     *
     * <p>The person is created without an assigned location. The location
     * must be set separately using the {@link #goTo(Building)} method when
     * the person moves to a specific building within the zoo.</p>
     *
     * @param name the name of the person, must not be null or empty
     * @throws IllegalArgumentException if name is null or empty
     */
    public Visitor(String name) {
        super(name);
        // This is a simplified ticket generation for demonstration
        this.ticketCode = "ZOO-" + ticketCounter.incrementAndGet();
    }
    public String getTicketCode() { return ticketCode; }


}
