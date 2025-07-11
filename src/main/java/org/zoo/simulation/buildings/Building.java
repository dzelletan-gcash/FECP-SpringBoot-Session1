package org.zoo.simulation.buildings;

/**
 * Represents a building within the zoo simulation system.
 * This interface defines the contract for all building types in the zoo,
 * including enclosures, shops, and hospitals.
 *
 * <p>All building implementations must provide a name that can be used
 * for identification and display purposes throughout the simulation.</p>
 *
 * @author Group 14 - FECP5 1006 - Kayne Rodrigo
 * @version 1.0
 * @since 2025-07-11
 */
public interface Building {
    /**
     * Returns the name of this building.
     *
     * <p>The name should be descriptive and unique enough to identify
     * the building within the zoo context. This name is used for
     * display purposes in the simulation interface and for location
     * tracking of people and animals.</p>
     *
     * @return the name of the building as a non-null String
     */
    String getName();
}
