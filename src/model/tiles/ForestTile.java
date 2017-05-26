package model.tiles;

import model.CityResources;

public class ForestTile extends Tile implements Destroyable{
	protected boolean isDestroyed;
	
    public final static int EXTRA_WOOD_PRODUCTION = 5;
	
    public final static int DEFAULT_PRODUCTION_CAPACITY = 100;
    
    public final static int DEFAULT_LUMBERJACK_CAPACITY = 5;
    
    
    protected final int productionCapacity;
    
    protected int lumberjack;
    protected final int lumberjackCapacity;
	
	public ForestTile(int productionCapacity, int lumberjackCapacity){
        super();
        this.productionCapacity = productionCapacity;
        this.isDestroyed = false;
		this.lumberjack = 0;
		this.lumberjackCapacity = lumberjackCapacity;
	}
	
    public ForestTile() {
        this(ForestTile.DEFAULT_PRODUCTION_CAPACITY, ForestTile.DEFAULT_LUMBERJACK_CAPACITY);
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
        return this == o || o.productionCapacity == this.productionCapacity && o.isDestroyed == this.isDestroyed
        		&& o.lumberjackCapacity == this.lumberjackCapacity;
    }	    
    

    // Change
    @Override
    public void disassemble(CityResources res) {
        if (!this.isDestroyed) {
            this.isDestroyed = true;
            res.increaseLumberjackCapacity(-this.lumberjackCapacity);
            res.increaseWoodCapacity(-this.productionCapacity);
            int n = res.wood - Math.min(res.woodCapacity,res.wood);
            res.spendW(n);
            res.fireLumberjack(res.getLumberjack()-Math.min(res.getLumberjackCapacity(),res.getLumberjack()));
        }
        
    }

    @Override
    public void update(CityResources res) {
        if (!this.isDestroyed) {
            final int extraProduction = Math.min(ForestTile.EXTRA_WOOD_PRODUCTION*this.lumberjack, res.woodCapacity - res.wood);

            res.creditW(extraProduction);
            
            /**
             * Le x sert à continuer d'augmenter le nombre de travailleurs même si celui ci est en 
             * dessous de 4. On augmente au maximum d'un quart du nombre de chômeur le nombre de bûcherons parce qu'il y a 4 métiers.
             */
            
            int x;
            if (res.getUnworkingPopulation() < 4 && res.getUnworkingPopulation() != 0){
            	x = 1;            	
            }
            else{
            	x = Math.round(res.getUnworkingPopulation()/4);
            }           
            

            final int extraWorkingPopulation = Math.min(x, res.getLumberjackCapacity()-res.getLumberjack());
            res.hireLumberjack(extraWorkingPopulation);
        }
    }



}
