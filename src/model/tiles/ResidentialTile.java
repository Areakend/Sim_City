/**
 * TNCity
 * Copyright (c) 2017
 *  Jean-Philippe Eisenbarth,
 *  Victorien Elvinger
 *  Martine Gautier,
 *  Quentin Laporte-Chabasse
 *
 *  This file is part of TNCity.
 *
 *  TNCity is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  TNCity is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with TNCity.  If not, see <http://www.gnu.org/licenses/>.
 */

package model.tiles;

import model.CityResources;

/**
 * Enable to welcome new inhabitants and consume water units according to the
 * number of inhabitants.
 */
public class ResidentialTile extends BuildableTile {

    // Constants
    /**
     * Default value of {@link ResidentialTile#getEvolutionWaterConsumption()}
     */
    public final static int DEFAULT_EVOLUTION_ENERGY_CONSUMPTION = 5;

    /**
     * Default value of {@link ResidentialTile#maxJoiningInhabitants}
     */
    public final static int DEFAULT_MAX_JOINING_INHABITANTS = 2;

    /**
     * Default value of {@link ResidentialTile#maxLeavingInhabitants}
     */
    private final static int DEFAULT_MAX_LEAVING_INHABITANTS = 2;

    /**
     * Default value of {@link ResidentialTile#getMaxNeededWater()}
     */
    public final static int DEFAULT_MAX_NEEDED_ENERGY = 30;

    /**
     * Default value of {@link ResidentialTile#getInhabitantsCapacity()}
     */
    public final static int DEFAULT_INHABITANTS_CAPACITY = 10;

    // Implementation
    /**
     * {@link #getInhabitantsCapacity()}
     */
    public final int inhabitantsCapacity;

    /**
     * Maximum number of newcomers for each update.
     */
    private final int maxJoiningInhabitants;

    /**
     * Maximum number of leaving inhabitants for each update.
     */
    private final int maxLeavingInhabitants;

    /**
     * {@link #getMaxNeededWater()}
     */
    private final int maxNeededWater;

    // Creation
    /**
     * @param capacity
     *            - {@link #getInhabitantsCapacity()}
     */
    public ResidentialTile(int capacity) {
        super(ResidentialTile.DEFAULT_EVOLUTION_ENERGY_CONSUMPTION);

        this.inhabitantsCapacity = capacity;
        this.maxNeededWater = ResidentialTile.DEFAULT_MAX_NEEDED_ENERGY;
        this.maxJoiningInhabitants = ResidentialTile.DEFAULT_MAX_JOINING_INHABITANTS;
        this.maxLeavingInhabitants = ResidentialTile.DEFAULT_MAX_LEAVING_INHABITANTS;
    }

    /**
     * Create with default settings.
     */
    public ResidentialTile() {
        this(ResidentialTile.DEFAULT_INHABITANTS_CAPACITY);
    }

    // Access
    /**
     * @return Maximum number of inhabitants.
     */
    public final int getInhabitantsCapacity() {
        return this.inhabitantsCapacity;
    }

    /**
     * @return Maximum number of water units to consume. This maximum is
     *         consumed if the residence is full.
     */
    public final int getMaxNeededWater() {
        return this.maxNeededWater;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = result * 17 + this.inhabitantsCapacity;
        result = result * 17 + this.maxJoiningInhabitants;
        result = result * 17 + this.maxLeavingInhabitants;
        result = result * 17 + this.maxNeededWater;
        return result;
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return o instanceof ResidentialTile && this.equals((ResidentialTile) o);
    }

    /**
     * @param o
     * @return Is {@value o} equals to this?
     */
    public boolean equals(ResidentialTile o) {
        return this == o || super.equals(o) && o.inhabitantsCapacity == this.inhabitantsCapacity && o.maxJoiningInhabitants == this.maxJoiningInhabitants
                && o.maxLeavingInhabitants == this.maxLeavingInhabitants && o.maxNeededWater == this.maxNeededWater;
    }

    @Override
    public boolean isDestroyed() {
        return this.state == ConstructionState.DESTROYED;
    }

    // Change
    @Override
    public void disassemble(CityResources res) {
        if (this.state == ConstructionState.BUILT) {
        	res.decreasePopulation(this.getInhabitants(res));
            res.decreasePopulationCapacity(this.inhabitantsCapacity);
            super.disassemble(res);
        }
    }

    @Override
    public void evolve(CityResources res) {
        super.evolve(res);

        if (this.state == ConstructionState.BUILT) {
            res.increasePopulationCapacity(this.inhabitantsCapacity);

            this.update(res);
        }
    }

    @Override
    public void update(CityResources res) {
        if (this.state == ConstructionState.BUILT) {
            final int inhabitants = this.getInhabitants(res);

            final int busyPercentage = inhabitants * 100 / this.inhabitantsCapacity; // Integer
                                                                                     // division
            final int neededWater = Math.max(1, busyPercentage * this.maxNeededWater / 100); // Integer
                                                                                               // division

            if (res.getUnconsumedWater() >= neededWater) {
                res.consumeWater(neededWater);
                this.isWaterMissing = false;

                // Less space is available, less newcomers join
                final int vacantPercentage = 100 - busyPercentage;
                final int newcomers = vacantPercentage * this.maxJoiningInhabitants / 100;

                res.increasePopulation(newcomers);
            } else {
                final int consumedWater = res.getUnconsumedWater();
                res.consumeWater(consumedWater);
                this.isWaterMissing = true;

                // More water units are missing, more inhabitants leave
                final int missingWaterPercentage = 100 - consumedWater * 100 / neededWater; // Integer
                                                                                               // division
                final int leavingInhabitants = Math.min(this.maxLeavingInhabitants, missingWaterPercentage * inhabitants / 100); // Integer
                                                                                                                                  // division

                res.decreasePopulation(leavingInhabitants);
            }
        }
    }

    // Implementation
    /**
     * @param res
     * @return Approximation of the number of inhabitants in the current
     *         residence if the population is uniformly distributed.
     *
     *         e.g. if the residence capacity is X = 50, the city capacity is Y
     *         = 100 (including X) and the population is Z = 20, then the
     *         residence has (X / Y) * Z = 10 inhabitants.
     */
    public int getInhabitants(CityResources res) {
        assert res.getPopulationCapacity() != 0;

        final int capacityPercentage = this.inhabitantsCapacity * 100 / res.getPopulationCapacity(); // Integer
                                                                                                     // division
        return res.getPopulation() * capacityPercentage / 100; // Integer
                                                               // division
    }

}
