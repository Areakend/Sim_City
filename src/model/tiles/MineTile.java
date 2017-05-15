package model.tiles;

import model.CityResources;

public class MineTile extends Tile implements Destroyable{
	protected boolean isDestroyed;
	
    public final static int EXTRA_PRODUCTION = 5;
	
    public final static int DEFAULT_PRODUCTION_CAPACITY = 70;
    
    public final static int DEFAULT_MINER_CAPACITY = 5;
    
    protected final int productionCapacity;
    
    protected final int minerCapacity;
	
	public MineTile(int productionCapacity, int minerCapacity){
        super();
        this.productionCapacity = productionCapacity;
        this.isDestroyed = false;
        this.minerCapacity = minerCapacity;   
		
	}
	
    public MineTile() {
        this(MineTile.DEFAULT_PRODUCTION_CAPACITY, MineTile.DEFAULT_MINER_CAPACITY);
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
	        return this == o || o.productionCapacity == this.productionCapacity && o.isDestroyed == this.isDestroyed
	        		&& o.minerCapacity == this.minerCapacity;
	    }	    
	    

	    // Change
	    @Override
	    public void disassemble(CityResources res) {
	        if (!this.isDestroyed) {
	            this.isDestroyed = true;
	            res.increaseSteelCapacity(-this.productionCapacity);
	            res.increaseRockCapacity(-this.productionCapacity);
	            res.increaseMinerCapacity(-this.minerCapacity);
	            res.fireMiner(res.getMiner()-res.getMinerCapacity());
	        }
	    }

	    @Override
	    public void update(CityResources res) {
	        if (!this.isDestroyed) {
	            
	            /**
	             * Le x sert � continuer d'augmenter le nombre de travailleurs m�me si celui ci est en 
	             * dessous de 4. On augmente au maximum d'un quart du nombre de ch�meur le nombre de mineurs parce qu'il y a 4 m�tiers.
	             */
	            int x;						
	            if (res.getUnworkingPopulation() < 4 && res.getUnworkingPopulation() != 0){
	            	x = 1;            	
	            }
	            else{
	            	x = Math.round(res.getUnworkingPopulation()/4);
	            }     
	            
	            final int extraWorkingPopulation = Math.min(x, res.getMinerCapacity()-res.getMiner());
	            res.hireMiner(extraWorkingPopulation);
	        }
	    }
  
  

}
