package org.zoo.simulation.people;
import java.util.concurrent.atomic.AtomicInteger;

public class Visitor extends People {
    private String ticketCode;
    private static final AtomicInteger ticketCounter = new AtomicInteger(6742);

    public Visitor(String name) {
        super(name);
        // This is a simplified ticket generation for demonstration
        this.ticketCode = "ZOO-" + ticketCounter.incrementAndGet();
    }
    public String getTicketCode() { return ticketCode; }
}