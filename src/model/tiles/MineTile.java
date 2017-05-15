package model.tiles;

import model.CityResources;

public class MineTile extends Tile implements Destroyable{
	protected boolean isDestroyed;
	
    public final static int EXTRA_PRODUCTION = 5;
	
    public final static int DEFAULT_PRODUCTION_CAPACITY = 70;
    
    public final static int DEFAULT_MINER_CAPACITY = 5;
    
    protected final int productionCapacity;
    
    protected int miner;
    protected final int minerCapacity;
	
	public MineTile(int productionCapacity, int minerCapacity){
        super();
        this.productionCapacity = productionCapacity;
        this.isDestroyed = false;
        this.minerCapacity = minerCapacity;
        this.miner = 0;
   
		
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
	        return this == o || o.productionCapacity == this.productionCapacity && o.isDestroyed == this.isDestroyed;
	    }	    
	    

	    // Change
	    @Override
	    public void disassemble(CityResources res) {
	        if (!this.isDestroyed) {
	            this.isDestroyed = true;
	            res.increaseSteelCapacity(-this.productionCapacity);
	            res.increaseRockCapacity(-this.productionCapacity);
	            res.fireWorkers(this.miner);
	        }
	    }

	    @Override
	    public void update(CityResources res) {
	        if (!this.isDestroyed) {
	            // Double production
	            final int extraProductionS = Math.min(MineTile.EXTRA_PRODUCTION*this.miner, res.steelCapacity - res.steel);
	            
	            final int extraProductionR = Math.min(MineTile.EXTRA_PRODUCTION*this.miner, res.rockCapacity - res.rock);

	            
	            res.creditR(extraProductionR);
	            res.creditS(extraProductionS);
	            /**
	             * Le x sert à continuer d'augmenter le nombre de travailleurs même si celui ci est en 
	             * dessous de 4. On augmente au maximum d'un quart du nombre de chômeur le nombre de mineurs parce qu'il y a 4 métiers.
	             */
	            int x;						
	            if (res.getUnworkingPopulation() < 4 && res.getUnworkingPopulation() != 0){
	            	x = 1;            	
	            }
	            else{
	            	x = Math.round(res.getUnworkingPopulation()/4);
	            }     
	            
	            final int extraWorkingPopulation = Math.min(x, this.minerCapacity-this.miner);
	            this.miner += extraWorkingPopulation;
	            res.hireWorkers(extraWorkingPopulation);
	        }
	    }
  
  

}
