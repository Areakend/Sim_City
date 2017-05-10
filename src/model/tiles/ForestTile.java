package model.tiles;

import model.CityResources;

public class ForestTile extends Tile implements Destroyable{
	protected boolean isDestroyed;
	
    public final static int EXTRA_ENERGY_PRODUCTION = 15;
	
    public final static int DEFAULT_PRODUCTION_CAPACITY = 70;
    
    protected int production;
    
    protected final int productionCapacity;
	
	public ForestTile(int productionCapacity){
        super();
        this.productionCapacity = productionCapacity;
        this.production = 0;
        this.isDestroyed = false;
		
	}
	
    public ForestTile() {
        this(ForestTile.DEFAULT_PRODUCTION_CAPACITY);
    }

	public boolean isDestroyed() {
		return this.isDestroyed=false;
	}

    
    public boolean equals(Object o) {
        return o instanceof ForestTile && this.equals((ForestTile) o);
    }

    /**
     * @param o
     * @return Is {@value o} equals to this?
     */
    public boolean equals(ForestTile o) {
        return this == o || o.production == this.production && o.productionCapacity == this.productionCapacity && o.isDestroyed == this.isDestroyed;
    }	    
    

    // Change
    @Override
    public void disassemble(CityResources res) {
        if (!this.isDestroyed) {
            this.isDestroyed = true;
        }
    }

    @Override
    public void update(CityResources res) {
        if (!this.isDestroyed) {
            // Double production
            final int extraProduction = Math.min(ForestTile.EXTRA_ENERGY_PRODUCTION, this.productionCapacity - this.production);

            this.production = this.production + extraProduction;
            res.creditW(extraProduction);
        }
    }



}
