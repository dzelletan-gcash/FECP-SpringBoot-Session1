package org.zoo.simulation.animals;
import org.zoo.simulation.buildings.Building;

/**
 * Abstract base class representing an animal in the zoo simulation.
 * This class defines the common attributes and behaviors shared by all
 * animals in the zoo, regardless of their species or type.
 *
 * <p>All animals have fundamental characteristics including a name,
 * health status, and location within the zoo. They share common
 * behaviors like eating and sleeping, while species-specific behaviors
 * like roaming and making sounds are defined by concrete subclasses.</p>
 *
 * <p>Animals can be moved between different buildings in the zoo,
 * such as from enclosures to the hospital for medical treatment,
 * or back to enclosures after recovery. Their location is tracked
 * to facilitate zoo management and visitor interactions.</p>
 *
 * <p>The class follows the Template Method pattern, where common
 * behaviors are implemented in the base class while species-specific
 * behaviors are left to concrete implementations.</p>
 *
 * @author Group 14 - FECP5 1006
 * @version 1.0
 * @since 2025-07-11
 * @see org.zoo.simulation.animals.species.Pachyderm
 * @see org.zoo.simulation.animals.species.Feline
 * @see org.zoo.simulation.animals.species.Bird
 * @see org.zoo.simulation.buildings.Building
 */
public abstract class Animal {

    /**
     * The name of this animal.
     * Used for identification and display purposes throughout the simulation.
     */
    private String name;

    /**
     * The health status of this animal.
     * {@code true} indicates the animal is healthy, {@code false} indicates
     * the animal is sick and may require medical attention.
     */
    private boolean isHealthy = true;

    /**
     * The current location of this animal within the zoo.
     * Can be an enclosure, hospital, or other building type.
     * May be {@code null} if the animal's location is not yet assigned.
     */
    private Building location;

    /**
     * Constructs a new animal with the specified name.
     *
     * <p>The animal is created in a healthy state by default and
     * without an assigned location. The location must be set separately
     * using the {@link #setLocation(Building)} method.</p>
     *
     * @param name the name of the animal, must not be null or empty
     * @throws IllegalArgumentException if name is null or empty
     */
    public Animal(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Animal name cannot be null or empty");
        }
        this.name = name;
    }

    /**
     * Simulates the animal eating behavior.
     *
     * <p>This method represents the basic feeding activity common to all
     * animals. It displays a message indicating that the animal is eating.
     * This behavior is typically triggered by handlers during feeding time
     * or as part of daily animal care routines.</p>
     *
     * * @see org.zoo.simulation.people.Handler#feed()
     */
    public void eat() {
        System.out.println(name + " is eating.");
    }

    /**
     * Simulates the animal sleeping behavior.
     *
     * <p>This method represents the basic rest activity common to all
     * animals. It displays a message indicating that the animal is sleeping.
     * This behavior reflects the natural rest patterns of zoo animals
     * and can be observed by visitors during quiet periods.</p>
     */
    public void sleep() {
        System.out.println(name + " is sleeping.");
    }

    /**
     * Defines the roaming behavior specific to this animal's species.
     *
     * <p>This abstract method must be implemented by concrete subclasses
     * to provide species-specific movement patterns. For example, felines
     * might prowl, birds might fly around their enclosure, and pachyderms
     * might lumber around their habitat.</p>
     *
     * <p>The roaming behavior is typically displayed when visitors observe
     * animals in their enclosures or when handlers exercise the animals.</p>
     *
     * * @see org.zoo.simulation.people.Handler#exercise()
     */
    public abstract void roam();

    /**
     * Defines the sound-making behavior specific to this animal type.
     *
     * <p>This abstract method must be implemented by concrete subclasses
     * to provide animal-specific vocalizations. Each animal type has its
     * own distinctive sound (e.g., lions roar, elephants trumpet, owls hoot).</p>
     *
     * <p>This behavior adds realism to the simulation and enhances the
     * visitor experience by providing audio cues characteristic of each
     * animal species.</p>
     */
    public abstract void makeSound();

    /**
     * Returns the name of this animal.
     *
     * <p>The name is used for identification purposes throughout the
     * simulation, including display in user interfaces, location tracking,
     * and interaction logging.</p>
     *
     * @return the name of the animal, never null
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the current location of this animal.
     *
     * <p>This method updates the animal's location within the zoo facility.
     * Animals may be moved between different buildings such as enclosures,
     * hospitals, or other facilities as part of their care routine.</p>
     *
     * <p>Location tracking is essential for zoo management, allowing staff
     * to know where each animal is located for feeding, medical care, and
     * visitor interactions.</p>
     *
     * @param location the new location for this animal, can be null to
     *                 indicate the animal is not currently assigned to
     *                 any specific building
     * @see org.zoo.simulation.buildings.Enclosure
     * @see org.zoo.simulation.buildings.Hospital
     */
    public void setLocation(Building location) {
        this.location = location;
    }

    /**
     * Returns the current location of this animal.
     *
     * <p>This method provides access to the animal's current location
     * within the zoo facility. The location information is used by
     * zoo staff for animal management and by visitors for locating
     * specific animals.</p>
     *
     * @return the current location of the animal, or null if no
     *         location has been assigned
     */
    public Building getLocation() {
        return location;
    }

    /**
     * Returns the health status of this animal.
     *
     * <p>The health status indicates whether the animal is in good
     * health or requires medical attention. Sick animals may need
     * to be transferred to the hospital for treatment.</p>
     *
     * @return true if the animal is healthy, false if the animal is sick
     * * @see org.zoo.simulation.people.Handler#examine()
     * * @see org.zoo.simulation.people.Veterinarian#heal()
     */
    public boolean isHealthy() {
        return isHealthy;
    }

    /**
     * Sets the health status of this animal.
     *
     * <p>This method is typically called by handlers during health
     * examinations or by veterinarians during treatment. Health status
     * changes may trigger location transfers (e.g., sick animals moved
     * to hospital, healed animals returned to enclosures).</p>
     *
     * @param healthy true if the animal is healthy, false if sick
     * * @see org.zoo.simulation.people.Handler#examine()
     * * @see org.zoo.simulation.people.Veterinarian#heal()
     */
    public void setHealthy(boolean healthy) {
        this.isHealthy = healthy;
    }
}