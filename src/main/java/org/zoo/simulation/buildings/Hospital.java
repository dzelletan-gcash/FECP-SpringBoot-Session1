package org.zoo.simulation.buildings;
/**
 * Represents the veterinary hospital within the zoo simulation.
 * The hospital is a specialized medical facility where sick or injured
 * animals receive treatment and care from veterinarians.
 *
 * <p>The hospital serves as a critical component of the zoo's animal
 * welfare system, providing medical services, health monitoring, and
 * educational opportunities through veterinary lectures. Animals are
 * transferred here when they require medical attention and returned
 * to their enclosures once healed.</p>
 *
 * <p>Visitors can visit the hospital to observe medical procedures
 * and attend educational lectures about animal health and veterinary
 * science.</p>
 *
 * @author Group 14 - FECP5 1006 - Kayne Rodrigo
 * @version 1.0
 * @since 2025-07-11
 * @see Building
* * @see org.zoo.simulation.people.Veterinarian
 * * @see org.zoo.simulation.animals.Animal
 */

public class Hospital implements Building {
    /**
     * The name of this hospital.
     * This field is immutable once set during construction.
     */
    private final String name;


    /**
     * Constructs a new hospital with the specified name.
     *
     * <p>Typically, there is one main hospital per zoo, but the name
     * allows for potential expansion or specialized medical facilities.</p>
     *
     * @param name the name of the hospital, must not be null
     * @throws IllegalArgumentException if name is null or empty
     */
    public Hospital(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Hospital name cannot be null or empty");
        }
        this.name = name;
    }

    /**
     * Returns the name of this hospital.
     *
     * <p>The name is used for identification and navigation purposes
     * within the zoo simulation, helping staff and visitors locate
     * the medical facility.</p>
     *
     * @return the name of the hospital, never null
     */
    @Override
    public String getName() {
        return name;
    }
}
