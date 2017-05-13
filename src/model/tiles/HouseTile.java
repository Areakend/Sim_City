package model.tiles;

import model.CityResources;

public class HouseTile extends Tile implements Destroyable{
	protected boolean isDestroyed;
	
    public final static int EXTRA_ENERGY_PRODUCTION = 2;
	
    public final static int DEFAULT_POPULATION_CAPACITY = 10;
    
    protected int population;
    
    protected final int populationCapacity;
	
	public HouseTile(int populationCapacity){
        super();
        this.populationCapacity = populationCapacity;
        this.population = 0;
        this.isDestroyed = false;
		
	}
	
    public HouseTile() {
        this(HouseTile.DEFAULT_POPULATION_CAPACITY);
    }

	public boolean isDestroyed() {
		return this.isDestroyed=false;
	}
	
	// public int hashCode() {
	  //      int result = 1;
	    //    result = result * 17 + this.production;
	      //  result = result * 17 + this.productionCapacity;
	        //result = result * 17 + Boolean.hashCode(this.isDestroyed);
	        //return result;
	    //}

	    // Status
	    @Override
	    public boolean equals(Object o) {
	        return o instanceof HouseTile && this.equals((HouseTile) o);
	    }

	    /**
	     * @param o
	     * @return Is {@value o} equals to this?
	     */
	    public boolean equals(HouseTile o) {
	        return this == o || o.population == this.population && o.populationCapacity == this.populationCapacity && o.isDestroyed == this.isDestroyed;
	    }	    
	    

	    // Change
	    @Override
	    public void disassemble(CityResources res) {
	        if (!this.isDestroyed) {
	            res.decreasePopulation(this.populationCapacity);
	            this.isDestroyed = true;
	        }
	    }

	    @Override
	    public void update(CityResources res) {
	        if (!this.isDestroyed) {
	            // Double production
	            final int extraProduction = Math.min(HouseTile.EXTRA_ENERGY_PRODUCTION, this.populationCapacity - this.population);

	            this.population = this.population + extraProduction;
	            res.increaseFoodProduction(extraProduction);
	        }
	    }
    
    



}
