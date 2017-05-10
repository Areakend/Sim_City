package model.tiles;

import model.CityResources;

public class MineTile extends Tile implements Destroyable{
	protected boolean isDestroyed;
	
    public final static int EXTRA_ENERGY_PRODUCTION = 15;
	
    public final static int DEFAULT_PRODUCTION_CAPACITY = 70;
    
    protected int productionR; //Rock production
    protected int productionS; //Steel production
    
    protected final int productionCapacity;
	
	public MineTile(int productionCapacity){
        super();
        this.productionCapacity = productionCapacity;
        this.productionR = 0;
        this.productionS = 0;
        this.isDestroyed = false;
		
	}
	
    public MineTile() {
        this(MineTile.DEFAULT_PRODUCTION_CAPACITY);
    }

	public boolean isDestroyed() {
		return this.isDestroyed=false;
	}

    
	  @Override
	    public boolean equals(Object o) {
	        return o instanceof MineTile && this.equals((MineTile) o);
	    }

	    /**
	     * @param o
	     * @return Is {@value o} equals to this?
	     */
	    public boolean equals(MineTile o) {
	        return this == o || o.productionS == this.productionS && o.productionR == this.productionR && o.productionCapacity == this.productionCapacity && o.isDestroyed == this.isDestroyed;
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
	            final int extraProductionS = Math.min(FarmTile.EXTRA_ENERGY_PRODUCTION, this.productionCapacity - this.productionS);

	            this.productionS = this.productionS + extraProductionS;
	            
	            final int extraProductionR = Math.min(FarmTile.EXTRA_ENERGY_PRODUCTION, this.productionCapacity - this.productionR);

	            this.productionR = this.productionR + extraProductionR;
	            
	            res.creditR(extraProductionR);
	            res.creditS(extraProductionS);
	        }
	    }
  
  

}
