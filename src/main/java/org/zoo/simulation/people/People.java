package org.zoo.simulation.people;
import org.zoo.simulation.buildings.Building;

/**
 * Abstract base class representing a person in the zoo simulation.
 * This class defines the common attributes and behaviors shared by all
 * people in the zoo, including visitors, staff members, and management.
 *
 * <p>All people have fundamental characteristics including a name and
 * location within the zoo facilities. They share the common ability to
 * move between different buildings in the zoo, such as enclosures,
 * shops, hospitals, and other facilities.</p>
 *
 * <p>People can navigate throughout the zoo using the movement system,
 * which tracks their current location and provides feedback about their
 * movements. This location tracking is essential for zoo management,
 * security, and visitor services.</p>
 *
 * <p>Concrete subclasses implement specific roles and behaviors:
 * managers handle zoo operations, handlers care for animals, veterinarians
 * provide medical services, and visitors enjoy the zoo experience.</p>
 *
 * @author Group 14 - FECP5 1006 - Kayne Rodrigo
 * @version 1.0
 * @since 2025-07-11
 * @see org.zoo.simulation.people.Manager
 * @see org.zoo.simulation.people.Handler
 * @see org.zoo.simulation.people.Veterinarian
 * @see org.zoo.simulation.people.Visitor
 * @see org.zoo.simulation.buildings.Building
 */
public abstract class People {

    /**
     * The name of this person.
     * Used for identification and display purposes throughout the simulation.
     */
    private String name;

    /**
     * The current location of this person within the zoo.
     * Can be any building type including enclosures, shops, hospitals, etc.
     * May be {@code null} if the person's location is not yet assigned.
     */
    private Building location;

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
    public People(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Person name cannot be null or empty");
        }
        this.name = name;
    }

    /**
     * Moves this person to the specified destination building.
     *
     * <p>This method simulates the person's movement within the zoo facility.
     * It updates the person's current location and provides feedback about
     * the movement through console output. The movement is instantaneous
     * in the simulation context.</p>
     *
     * <p>This method is used by all types of people in the zoo:
     * <ul>
     * <li>Visitors moving between attractions, shops, and facilities</li>
     * <li>Staff members going to their assigned work locations</li>
     * <li>Managers traveling between different areas for oversight</li>
     * <li>Veterinarians moving between hospital and enclosures</li>
     * </ul></p>
     *
     * @param destination the building to move to, must not be null
     * @throws IllegalArgumentException if destination is null
     * @see org.zoo.simulation.buildings.Enclosure
     * @see org.zoo.simulation.buildings.Hospital
     * @see org.zoo.simulation.buildings.Shop
     */
    public void goTo(Building destination) {
        if (destination == null) {
            throw new IllegalArgumentException("Destination cannot be null");
        }
        this.location = destination;
        System.out.println(name + " is going to " + destination.getName());
    }

    /**
     * Returns the name of this person.
     *
     * <p>The name is used for identification purposes throughout the
     * simulation, including display in user interfaces, location tracking,
     * interaction logging, and communication with other people or systems.</p>
     *
     * @return the name of the person, never null
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current location of this person.
     *
     * <p>This method provides access to the person's current location
     * within the zoo facility. The location information is used for
     * tracking, security, and enabling location-based interactions
     * between people and animals or other people.</p>
     *
     * @return the current location of the person, or null if no
     *         location has been assigned
     */
    public Building getLocation() {
        return location;
    }

    /**
     * Sets the current location of this person directly.
     *
     * <p>This method updates the person's location without the movement
     * feedback provided by {@link #goTo(Building)}. It is primarily used
     * for internal state management or when silent location updates are
     * required.</p>
     *
     * <p>For normal movement with user feedback, use {@link #goTo(Building)}
     * instead.</p>
     *
     * @param location the new location for this person, can be null to
     *                 indicate the person is not currently in any specific
     *                 building
     * @see #goTo(Building)
     */
    public void setLocation(Building location) {
        this.location = location;
    }

    /**
     * Returns a string representation of this person.
     *
     * <p>The string includes the person's name and current location,
     * providing a quick overview of their status in the simulation.</p>
     *
     * @return a string representation in the format "Name at Location"
     *         or "Name (no location)" if location is null
     */
    @Override
    public String toString() {
        if (location != null) {
            return name + " at " + location.getName();
        } else {
            return name + " (no location)";
        }
    }
}