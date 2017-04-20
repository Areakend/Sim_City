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

package model;

/**
 * Represents the resources and the parameters of the city. An ephemeral
 * resource is reset at each step thanks to {@link CityResources#getVat()}.
 */
public class CityResources {
	
	private int Wood;
	private int Steel;
	private int Rock;

	
	private int unconsumedFood;
	private int foodProduction;

    // Constant
    /**
     * Default value for {@link CityResources#getVat()}.
     */
    public final static int DEFAULT_VAT = 20;

    // Implementation (Currency)
    /**
     * {@link #getCurrency()}
     */
    private int currency;

    /**
     * {@link #getVat()}
     */
    private int vat;

    // Implementation (Energy)
    /**
     * {@link #getUnconsumedEnergy()}
     */
    private int unconsumedEnergy;

    /**
     * {@link #getEnergyProduction()}
     */
    private int energyProduction;

    // Implementation (Population)
    /**
     * {@link #getUnworkingPopulation()}
     */
    private int unworkingPopulation;

    /**
     * {@link #getPopulation()}
     */
    private int population;

    /**
     * {@link #getPopulationCapacity()}
     */
    private int populationCapacity;

    // Implementation (Product)
    /**
     * {@link #getProductsCount()}
     */
    private int productsCount;

    /**
     * {@link #getProductsCapacity()}
     */
    private int productsCapacity;

    // Creation
    /**
     *
     * @param aCurrency
     *            - {@link #getCurrency()}
     */
    public CityResources(int aCurrency) {
        assert aCurrency >= 0;
        this.Wood = 250;
        this.Steel = 250;
        this.Rock = 250;
        this.currency = aCurrency;
        this.vat = CityResources.DEFAULT_VAT;
    }

    /**
     *
     * @param aCurrency
     *            - {@link #getCurrency()}
     * @param aPopulation
     *            - {@link #getPopulation()} and
     *            {@link #getPopulationCapacity()}
     */
    public CityResources(int aCurrency, int aPopulation) {
        this(aCurrency);
        assert aPopulation >= 0;

        this.population = aPopulation;
        this.populationCapacity = aPopulation;

        this.resetEphemerals();
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return o instanceof CityResources && this.equals((CityResources) o);
    }

    /**
     * @param o
     * @return Is {@value o} equals to this?
     */
    public boolean equals(CityResources o) {
        return this == o || super.equals(o) && o.currency == this.currency && o.vat == this.vat && o.unconsumedEnergy == this.unconsumedEnergy && o.energyProduction == this.energyProduction
                && o.unworkingPopulation == this.unworkingPopulation && o.population == this.population && o.populationCapacity == this.populationCapacity && o.productsCount == this.productsCount
                && o.productsCapacity == this.productsCapacity;
    }

    // Access
    @Override
    public int hashCode() {
        int result = 1;
        result = result * 17 + this.currency;
        result = result * 17 + this.vat;
        result = result * 17 + this.unconsumedEnergy;
        result = result * 17 + this.energyProduction;
        result = result * 17 + this.unworkingPopulation;
        result = result * 17 + this.population;
        result = result * 17 + this.populationCapacity;
        result = result * 17 + this.productsCount;
        result = result * 17 + this.productsCapacity;
        return result;
    }

    // Access (Currency)
    /**
     *
     * @return Accumulated currency.
     */
    public int getCurrency() {
        return this.currency;
    }

    /**
     * @return Value-Added-Tax in percentage.
     */
    public int getVat() {
        return this.vat;
    }

    // Access (Energy)
    /**
     * @return Number of consumed energy units.
     */
    public int getConsumedEnergy() {
        return this.energyProduction - this.unconsumedEnergy;
    }
    /**
     * @return Number of available energy units.
     */
 
    public int getWood() {
        return this.Wood;
    }
    
    public int getRock() {
        return this.Rock;
    }
    
    public int getConsumedFood() {
        return this.foodProduction - this.unconsumedFood;
    }
    
    public int getSteel() {
        return this.Steel;
    }
 
    public int getUnconsumedEnergy() {
        return this.unconsumedEnergy;
    }
    
 
    public int getUnconsumedFood() {
        return this.unconsumedFood;
    }
 
    /**
    *
    * @return Monthly production of energy units.
    */
    
    public int getEnergyProduction() {
        return this.energyProduction;
    }
    
    public int getFoodProduction() {
        return this.Foodproduction;
    }

    // Access (Population)
    /**
     * @return Number of job-less citizens.
     */
    public int getUnworkingPopulation() {
        return this.unworkingPopulation;
    }

    /**
     * @return Number of citizens with a job.
     */
    public int getWorkingPopulation() {
        return this.population - this.unworkingPopulation;
    }

    /**
     * @return Total number of citizens.
     */
    public int getPopulation() {
        return this.population;
    }

    /**
     *
     * @return Capacity of the city.
     */
    public int getPopulationCapacity() {
        return this.populationCapacity;
    }

    // Access (Product)
    /**
     * @return Accumulated number of products.
     */
    public int getProductsCount() {
        return this.productsCount;
    }

    /**
     * @return Maximum number of products that can be stored.
     */
    public int getProductsCapacity() {
        return this.productsCapacity;
    }

    // Change (Currency)
    /**
     * Decrease {@link #getCurrency()} by {@value amount}.
     *
     * @param amount
     */
    public void credit(int amount) {
        assert amount >= 0;

        this.currency = this.currency + amount;
    }
    public void creditW(int amount) {
        assert amount >= 0;

        this.Wood = this.Wood + amount;
    }
    public void creditR(int amount) {
        assert amount >= 0;

        this.Rock = this.Rock + amount;
    }
    public void creditS(int amount) {
        assert amount >= 0;

        this.Steel = this.Steel + amount;
    }

    /**
     * Get VAT on {@value currencyAmount} and {@link #credit(int)} with the
     * obtained result.
     *
     * @param currencyAmount
     */
    public void creditWithTaxes(int currencyAmount) {
        assert currencyAmount >= 0;

        this.credit(currencyAmount * this.vat / 100); // Integer division
    }

    /**
     * Increase {@link #getCurrency()} by {@value amount}.
     *
     * @param amount
     */
    public void spend(int amount) {
        assert amount >= 0;

        this.currency = this.currency - amount;
    }
    public void spendW(int amount) {
        assert amount >= 0;

        this.Wood = this.Wood - amount;
    }
    public void spendR(int amount) {
        assert amount >= 0;

        this.Rock = this.Rock - amount;
    }
    public void spendS(int amount) {
        assert amount >= 0;

        this.Steel = this.Steel - amount;
    }

    // Change (Energy)
    /***
     * Increase {@link #getConsumedEnergy()} by {@value amount}.
     *
     * @param amount
     */
    public void consumeEnergy(int amount) {
        assert 0 <= amount && amount <= this.getUnconsumedEnergy();

        this.unconsumedEnergy = this.unconsumedEnergy - amount;
    }

    public void consumeFood(int amount) {
        assert 0 <= amount && amount <= this.getUnconsumedFood();

        this.unconsumedFood = this.unconsumedFood - amount;
    }

    /**
     * Decrease {@link #getEnergyProduction()} by {@value amount}.
     *
     * @param amount
     */
    public void decreaseEnergyProduction(int amount) {
        assert amount >= 0;

        this.energyProduction = Math.max(0, this.energyProduction - amount);
        this.unconsumedEnergy = Math.min(this.unconsumedEnergy, this.energyProduction);
    }

    public void decreaseFoodProduction(int amount) {
        assert amount >= 0;

        this.foodProduction = Math.max(0, this.foodProduction - amount);
        this.unconsumedFood = Math.min(this.unconsumedFood, this.foodProduction);
    }

    /**
     * Increase {@link #getEnergyProduction()} by {@value amount}.
     *
     * @param amount
     */
    public void increaseEnergyProduction(int amount) {
        assert amount >= 0;

        this.energyProduction = this.energyProduction + amount;
        this.unconsumedEnergy = this.unconsumedEnergy + amount;
    }

    public void increaseFoodProduction(int amount) {
        assert amount >= 0;

        this.foodProduction = this.energyProduction + amount;
        this.unconsumedEnergy = this.unconsumedEnergy + amount;
    }

    // Change (Population)
    /**
     * Increase {@link #getWorkingPopulation()} by {@value amount}.
     *
     * @param amount
     */
    public void hireWorkers(int amount) {
        assert 0 <= amount && amount <= this.getUnworkingPopulation();

        this.unworkingPopulation = this.unworkingPopulation - amount;
    }

    /**
     * Increase {@link #getPopulation()} by {@value amount}.
     *
     * @param amount
     */
    public void increasePopulation(int amount) {
        assert amount >= 0;

        final int joiningPopulation = Math.min(this.populationCapacity - this.population, amount);
        this.population = this.population + joiningPopulation;
        this.unworkingPopulation = this.unworkingPopulation + joiningPopulation;
    }

    /**
     * Decrease {@link #getPopulation()} by {@value amount}.
     *
     * @param amount
     */
    public void decreasePopulation(int amount) {
        assert amount >= 0;

        this.population = Math.max(0, this.population - amount);
        this.unworkingPopulation = Math.min(this.unworkingPopulation, this.population);
    }

    /**
     * Increase {@link #getPopulationCapacity()} by {@value amount}.
     *
     * @param amount
     */
    public void increasePopulationCapacity(int amount) {
        assert amount >= 0;

        this.populationCapacity = this.populationCapacity + amount;
    }

    /**
     * Decrease {@link #getPopulationCapacity()} by {@value amount}.
     *
     * @param amount
     */
    public void decreasePopulationCapacity(int amount) {
        assert 0 <= amount && amount <= this.getPopulationCapacity();

        this.populationCapacity = this.populationCapacity - amount;
        this.population = Math.min(this.population, this.populationCapacity);
    }

    // Change (Product)
    /**
     * Decrease {@link #getProductsCount()} by {@value amount}.
     *
     * @param amount
     */
    public void consumeProducts(int amount) {
        assert amount >= 0;

        this.productsCount = Math.max(0, this.productsCount - amount);
    }

    /**
     * Increase {@link #getProductsCount()} by {@value amount}.
     *
     * @param amount
     */
    public void storeProducts(int amount) {
        assert amount >= 0;

        this.productsCount = Math.min(this.productsCapacity, this.productsCount + amount);
    }

    /**
     * Decrease {@link #getProductsCapacity()} by {@value amount}.
     *
     * @param amount
     */
    public void decreaseProductsCapacity(int amount) {
        assert 0 <= amount && amount <= this.getProductsCapacity();

        this.productsCapacity = this.productsCapacity - amount;
        this.productsCount = Math.min(this.productsCount, this.productsCapacity);
    }

    /**
     * Increase {@link #getProductsCapacity()} by {@value amount}.
     *
     * @param amount
     */
    public void increaseProductsCapacity(int amount) {
        assert amount >= 0;

        this.productsCapacity = this.productsCapacity + amount;
    }

    // Reset
    /**
     * Reset ephemeral resources.
     */
    public void resetEphemerals() {
        this.unworkingPopulation = this.population;
        this.unconsumedEnergy = this.energyProduction;
    }
}
