package org.zoo.simulation.buildings;


/**
 * Represents a retail shop within the zoo simulation.
 * Shops are commercial buildings where visitors can purchase various
 * items including tickets, food, drinks, and souvenirs.
 *
 * <p>The zoo features multiple shop types to serve different visitor
 * needs: ticket shops for entry passes, food and drink shops for
 * refreshments, and gift shops for memorabilia. Each shop is managed
 * by vendors who handle sales transactions.</p>
 *
 * <p>Shops provide essential services that enhance the visitor experience
 * and generate revenue for zoo operations. They serve as interaction
 * points where visitors can make purchases and receive customer service.</p>
 *
 * @author Group 14 - FECP5 1006
 * @version 1.0
 * @since 2025-07-11
 * @see Building
 * * @see org.zoo.simulation.people.Vendor
 * * @see org.zoo.simulation.people.Visitor
 */

public class Shop implements Building {

    /**
     * The name of this shop.
     * This field is immutable once set during construction.
     */
    private final String name;

    /**
     * Constructs a new shop with the specified name.
     *
     * <p>The name should indicate the type of shop and the products
     * or services it offers. Examples include "Ticket Shop",
     * "Food Court", "Gift Shop", or "Souvenir Stand".</p>
     *
     * @param name the name of the shop, must not be null
     * @throws IllegalArgumentException if name is null or empty
     */
    public Shop(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Shop name cannot be null or empty");
        }
        this.name = name;
    }

    /**
     * Returns the name of this shop.
     *
     * <p>The name helps visitors identify the type of goods or services
     * available and assists in navigation throughout the zoo facility.</p>
     *
     * @return the name of the shop, never null
     */
    @Override
    public String getName() {
        return name;
    }
}

