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

import model.tiles.CastleaTile;
import model.tiles.FarmTile;
import model.tiles.ForestTile;
import model.tiles.MineTile;

/**
 * Represents the resources and the parameters of the city. An ephemeral
 * resource is reset at each step thanks to {@link CityResources#getVat()}.
 */
public class CityResources {
	
	public int wood;
	public int steel;
	public int rock;	
	public int food;
	
	public int woodCapacity;
	public int steelCapacity;
	public int rockCapacity;
	public int foodCapacity;
	

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

    // Implementation (Water)
    /**
     * {@link #getUnconsumedWater()}
     */
    int unconsumedWater;

    /**
     * {@link #getWaterProduction()}
     */
    int waterProduction;

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
    
    
    private int farmer;
    private int farmerCapacity;
    
    private int lumberjack;
    private int lumberjackCapacity;
    
    private int miner;
    private int minerCapacity;
    
    private int knight;
    private int knightCapacity;

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
        this.wood = 70;
        this.steel = 70;
        this.rock = 70;
        this.currency = aCurrency;
        this.vat = CityResources.DEFAULT_VAT;
        this.food = 70;
        this.foodCapacity = FarmTile.DEFAULT_PRODUCTION_CAPACITY;
        this.woodCapacity = ForestTile.DEFAULT_PRODUCTION_CAPACITY;
        this.steelCapacity = MineTile.DEFAULT_PRODUCTION_CAPACITY;
        this.rockCapacity = MineTile.DEFAULT_PRODUCTION_CAPACITY;
        this.miner = 0;
        this.lumberjack = 0;
        this.farmer = 0;
        this.knight = 0;
        this.farmerCapacity = 0;
        this.minerCapacity = 0;
        this.lumberjackCapacity = 0;
        this.knightCapacity = CastleaTile.DEFAULT_KNIGHT_CAPACITY;
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

        this.unworkingPopulation = this.population;
        this.unconsumedWater = this.waterProduction;
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
        return this == o || super.equals(o) && o.currency == this.currency && o.vat == this.vat && o.unconsumedWater == this.unconsumedWater && o.waterProduction == this.waterProduction
                && o.unworkingPopulation == this.unworkingPopulation && o.population == this.population && o.populationCapacity == this.populationCapacity && o.productsCount == this.productsCount
                && o.productsCapacity == this.productsCapacity && o.wood == this.wood && o.steel == this.steel && this.rock == o.rock && this.food == o.food &&
                this.woodCapacity == o.woodCapacity && this.steelCapacity == o.steelCapacity && o.rockCapacity == this.rockCapacity && this.foodCapacity == o.foodCapacity;
    }

    // Access
    @Override
    public int hashCode() {
        int result = 1;
        result = result * 17 + this.currency;
        result = result * 17 + this.vat;
        result = result * 17 + this.unconsumedWater;
        result = result * 17 + this.waterProduction;
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

    // Access (Water)
    /**
     * @return Number of consumed water units.
     */
    public int getConsumedWater() {
        return this.waterProduction - this.unconsumedWater;
    }
    /**
<<<<<<< HEAD
     * @return Number of available water units.
=======
     * @return Number of Wood units.
>>>>>>> 34029e0b7d7c634be6ab246c3db68b26cecf25a4
     */
 
    public int getWood() {
        return this.wood;
    }
    /**
     * @return Number of Rock units.
     */
 
    public int getRock() {
        return this.rock;
    }
    /**
     * @return Number of Food units.
     */
 
    public int getFood() {
        return this.food;
    }
    /**
     * @return Number of Steel units.
     */
  
    public int getSteel() {
        return this.steel;
    }
 

    public int getUnconsumedWater() {
        return this.unconsumedWater;
    /**
     * @return Unconsumed Water units.
     */

    }
    

    /**
    *
    * @return Monthly production of water units.
    */
    
    public int getWaterProduction() {
        return this.waterProduction;
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
     * @return Number of Farmer.
     */
    public int getFarmer() {
		return farmer;
	}
    
    /**
     * @return Number of Lumberjack.
     */
	public int getLumberjack() {
		return lumberjack;
	}
	
    /**
     * @return Number of Miner.
     */
	public int getMiner() {
		return miner;
	}
	
    /**
     * @return Number of Knight.
     */
	public int getKnight() {
		return knight;
	}
	
    /**
     * @return Farmer capacity.
     */
	public int getFarmerCapacity() {
		return farmerCapacity;
	}
	
    /**
     * @return Lumberjack capacity.
     */
	public int getLumberjackCapacity() {
		return lumberjackCapacity;
	}
	
    /**
     * @return Miner capacity.
     */
	public int getMinerCapacity() {
		return minerCapacity;
	}
	
    /**
     * @return Knight capacity.
     */
	public int getKnightCapacity() {
		return knightCapacity;
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
    
    /**
     * @return Maximum number of Wood that can be stored.
     */
    public int getWoodCapacity() {
		return woodCapacity;
	}
	
    /**
     * Increase Wood capacity.
     * @param woodCapacity
     */
	public void increaseWoodCapacity(int woodCapacity) {
		this.woodCapacity += woodCapacity;
	}
    
    /**
     * @return Maximum number of Steel that can be stored.
     */
	public int getSteelCapacity() {
		return steelCapacity;
	}
	
    /**
     * Increase Steel capacity.
     * @param steelCapacity
     */
	public void increaseSteelCapacity(int steelCapacity) {
		this.steelCapacity += steelCapacity;
	}
    
    /**
     * @return Maximum number of Rock that can be stored.
     */
	public int getRockCapacity() {
		return rockCapacity;
	}
	
    /**
     * Increase Rock capacity.
     * @param rockCapacity
     */
	public void increaseRockCapacity(int rockCapacity) {
		this.rockCapacity += rockCapacity;
	}

    
    /**
     * @return Maximum number of Food that can be stored.
     */
	public int getFoodCapacity() {
		return foodCapacity;
	}
	
    /**
     * Increase Food capacity.
     * @param foodCapacity
     */
	public void increaseFoodCapacity(int foodCapacity) {
		this.foodCapacity += foodCapacity;
	}
    
    /**
     * Increase maximum number of Farmer.
     * @param farmerCapacity
     */
	public void increaseFarmerCapacity(int farmerCapacity) {
		this.farmerCapacity += farmerCapacity;
	}
    
    /**
     * Increase maximum number of Lumberjack.
     * @param lumberjackCapacity
     */
	public void increaseLumberjackCapacity(int lumberjackCapacity) {
		this.lumberjackCapacity += lumberjackCapacity;
	}

    
    /**
     * Increase maximum number of Miner.
     * @param minerCapacity
     */
	public void increaseMinerCapacity(int minerCapacity) {
		this.minerCapacity += minerCapacity;
	}
    
    /**
     * Increase maximum number of Knight.
     * @param knightCapacity
     */
	public void increaseKnightCapacity(int knightCapacity) {
		this.knightCapacity += knightCapacity;
	}

	// Change (Currency)
    /**
     * Decrease {@link #getCurrency()} by {@value amount}.
     *
     * @param amount
     */
    public void credit(int amount) {
        assert amount >= 0;

        this.currency+= amount;
    }
    
    /**
     * Decrease {@link #getWood()} by {@value amount}.
     *
     * @param amount
     */
    public void creditW(int amount) {
        assert amount >= 0;

        this.wood += amount;
    }
    /**
     * Decrease {@link #getRock()} by {@value amount}.
     *
     * @param amount
     */
    public void creditR(int amount) {
        assert amount >= 0;

        this.rock += amount;
    }
    /**
     * Decrease {@link #getSteel()} by {@value amount}.
     *
     * @param amount
     */
    public void creditS(int amount) {
        assert amount >= 0;

        this.steel += amount;
    }
    /**
     * Decrease {@link #getFood()} by {@value amount}.
     *
     * @param amount
     */
    public void creditF(int amount){
    	assert amount >= 0;
    	
    	this.food += amount;
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
     * Decrease {@link #getCurrency()} by {@value amount}.
     *
     * @param amount
     */
    public void spend(int amount) {
        assert amount >= 0;

        this.currency -= amount;
    }
    
    /**
     * Decrease {@link #getWood()} by {@value amount}.
     *
     * @param amount
     */
    public void spendW(int amount) {
        assert amount >= 0 && amount <= this.wood;

        this.wood -= amount;
    }

    /**
     * Decrease {@link #getRock()} by {@value amount}.
     *
     * @param amount
     */
    public void spendR(int amount) {
        assert amount >= 0 && amount <= this.rock;

        this.rock -= amount;
    }

    /**
     * Decrease {@link #getSteel()} by {@value amount}.
     *
     * @param amount
     */
    public void spendS(int amount) {
        assert amount >= 0 && amount <= this.steel;

        this.steel -= amount;
    }

    /**
     * Decrease {@link #getFood()} by {@value amount}.
     *
     * @param amount
     */
    public void spendF(int amount){
    	assert amount >= 0 && amount <= this.food;
    	
    	this.food -= amount;
    }

    // Change (Water)
    /***
     * Increase {@link #getConsumedWater()} by {@value amount}.
     *
     * @param amount
     */
    public void consumeWater(int amount) {
        assert 0 <= amount && amount <= this.getUnconsumedWater();

        this.unconsumedWater = this.unconsumedWater - amount;
    }


    /**
     * Decrease {@link #getWaterProduction()} by {@value amount}.
     *
     * @param amount
     */
    public void decreaseWaterProduction(int amount) {
        assert amount >= 0;

        this.waterProduction = Math.max(0, this.waterProduction - amount);
        this.unconsumedWater = Math.min(this.unconsumedWater, this.waterProduction);
    }


    /**
     * Increase {@link #getWaterProduction()} by {@value amount}.
     *
     * @param amount
     */
    public void increaseWaterProduction(int amount) {
        assert amount >= 0;

        this.waterProduction = this.waterProduction + amount;
        this.unconsumedWater = this.unconsumedWater + amount;
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
     * Decrease {@link #getWorkingPopulation()} by {@value amount}.
     *
     * @param amount
     */
    public void fireWorkers(int amount) {
    	assert 0 <= amount && amount <= this.getWorkingPopulation();
    	
    	this.unworkingPopulation += amount;
    }
    
    /**
     * Increase {@link #getFarmer()} by {@value amount}.
     *
     * @param amount
     */
    public void hireFarmer(int amount) {
        assert 0 <= amount && amount <= this.getUnworkingPopulation();
        this.unworkingPopulation -= amount;
        this.farmer += amount;
    }
    
    /**
     * Decrease {@link #getFarmer()} by {@value amount}.
     *
     * @param amount
     */
    public void fireFarmer(int amount) {
    	assert 0 <= amount && amount <= this.getFarmer();
    	this.farmer -= amount;    	
    	this.unworkingPopulation += amount;
    }
    
    /**
     * Increase {@link #getMiner()} by {@value amount}.
     *
     * @param amount
     */
    public void hireMiner(int amount) {
        assert 0 <= amount && amount <= this.getUnworkingPopulation();
        this.unworkingPopulation -= amount;
        this.miner += amount;
    }
    
    /**
     * Decrease {@link #getMiner()} by {@value amount}.
     *
     * @param amount
     */
    public void fireMiner(int amount) {
    	assert 0 <= amount && amount <= this.getMiner();
    	this.miner -= amount;    	
    	this.unworkingPopulation += amount;
    }
    
    /**
     * Increase {@link #getKnight()} by {@value amount}.
     *
     * @param amount
     */
    public void hireKnight(int amount) {
        assert 0 <= amount && amount <= this.getUnworkingPopulation();
        this.unworkingPopulation -= amount;
        this.knight += amount;
    }
    
    /**
     * Decrease {@link #getKnight()} by {@value amount}.
     *
     * @param amount
     */
    public void fireKnight(int amount) {
    	assert 0 <= amount && amount <= this.getKnight();
    	this.knight -= amount;    	
    	this.unworkingPopulation += amount;
    }
    
    /**
     * Increase {@link #getLumberack()} by {@value amount}.
     *
     * @param amount
     */
    public void hireLumberjack(int amount) {
        assert 0 <= amount && amount <= this.getUnworkingPopulation();
        this.unworkingPopulation -= amount;
        this.lumberjack += amount;
    }
    
    /**
     * Decrease {@link #getLumberack()} by {@value amount}.
     *
     * @param amount
     */
    public void fireLumberjack(int amount) {
    	assert 0 <= amount && amount <= this.getLumberjack();
    	this.lumberjack -= amount;    	
    	this.unworkingPopulation += amount;
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
        for (int i=1; i<=amount; i++ ){
        	double x = Math.random();
        	if (x<0.2 && this.unworkingPopulation > 0){
        		this.unworkingPopulation--;
        	}
        	if (x<0.4 && this.getKnight()>0){
        		this.knight--;
        	}
        	else if (x<0.6 && this.getLumberjack()>0){
        		this.lumberjack--;
        	}
        	else if (x<0.8 && this.getMiner()>0){
        		this.miner--;
        	}
        	else {
        		if (this.getFarmer()>0){
        			this.farmer--;
        		}
        		else if (this.getMiner()>0){
        			this.miner--;
        		}
        		else if (this.getLumberjack()>0){
        			this.lumberjack--;
        		}
        		else if (this.getKnight()>0){
        			this.knight--;
        		}
        		else if (this.unworkingPopulation>0){
        			this.unworkingPopulation--;
        		}
        	}

        }
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


}
