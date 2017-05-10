package model.tiles;

import model.CityResources;

public class FarmTile extends Tile implements Destroyable{
	protected boolean isDestroyed;
	
    public final static int EXTRA_ENERGY_PRODUCTION = 15;
	
    public final static int DEFAULT_PRODUCTION_CAPACITY = 70;
    
    protected int production;
    
    protected final int productionCapacity;
	
	public FarmTile(int productionCapacity){
        super();
        this.productionCapacity = productionCapacity;
        this.production = 0;
        this.isDestroyed = false;
		
	}
	
    public FarmTile() {
        this(FarmTile.DEFAULT_PRODUCTION_CAPACITY);
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
	        return o instanceof FarmTile && this.equals((FarmTile) o);
	    }

	    /**
	     * @param o
	     * @return Is {@value o} equals to this?
	     */
	    public boolean equals(FarmTile o) {
	        return this == o || o.production == this.production && o.productionCapacity == this.productionCapacity && o.isDestroyed == this.isDestroyed;
	    }	    
	    

	    // Change
	    @Override
	    public void disassemble(CityResources res) {
	        if (!this.isDestroyed) {
	            res.decreaseFoodProduction(this.productionCapacity);
	            this.isDestroyed = true;
	        }
	    }

	    @Override
	    public void update(CityResources res) {
	        if (!this.isDestroyed) {
	            // Double production
	            final int extraProduction = Math.min(FarmTile.EXTRA_ENERGY_PRODUCTION, this.productionCapacity - this.production);

	            this.production = this.production + extraProduction;
	            res.increaseFoodProduction(extraProduction);
	        }
	    }
    
    



}
