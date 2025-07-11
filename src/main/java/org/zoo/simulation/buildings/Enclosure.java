package org.zoo.simulation.buildings;
/**
 * Represents an animal enclosure within the zoo simulation.
 * Enclosures are specialized buildings that house animals and provide
 * spaces for visitors to observe and interact with wildlife.
 *
 * <p>Each enclosure is designed to accommodate specific animal species
 * and provides a controlled environment for animal care and visitor
 * experiences. Animals can be relocated to enclosures from other
 * buildings such as hospitals after treatment.</p>
 *
 * <p>Enclosures serve as primary locations where handlers perform
 * animal care activities including feeding, exercise, and health
 * examinations.</p>
 *
 * @author Group 14 - FECP5 1006 - Kayne Rodrigo
 * @version 1.0
 * @since 2025-07-11
 */

public class Enclosure implements Building {

    /**
     * The name of this enclosure.
     * This field is immutable once set during construction.
     */
    private final String name;

    /**
     * Constructs a new enclosure with the specified name.
     *
     * <p>The name should be descriptive and indicate the type of
     * animals housed or the specific area designation within the zoo.</p>
     *
     * @param name the name of the enclosure, must not be null
     * @throws IllegalArgumentException if name is null or empty
     */
    public Enclosure(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Enclosure name cannot be null or empty");
        }
        this.name = name;
    }


    /**
     * Returns the name of this enclosure.
     *
     * <p>The name is used for identification purposes and display
     * in the zoo simulation interface. It helps visitors and staff
     * locate specific animal habitats.</p>
     *
     * @return the name of the enclosure, never null
     */
    @Override
    public String getName() {
        return name;
    }
}
