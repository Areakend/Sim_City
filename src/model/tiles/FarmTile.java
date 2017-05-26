package model.tiles;

import model.CityResources;

public class FarmTile extends Tile implements Destroyable{
	protected boolean isDestroyed;
	
    public final static int EXTRA_FOOD_PRODUCTION = 5;
	
    public final static int DEFAULT_PRODUCTION_CAPACITY = 100;
    
    public final static int DEFAULT_FARMER_CAPACITY = 5;
    
    
    protected final int farmerCapacity;
    
    protected final int productionCapacity;
	
	public FarmTile(int productionCapacity, int farmerCapacity){
        super();
        this.productionCapacity = productionCapacity;
        this.isDestroyed = false;
        this.farmerCapacity = farmerCapacity;
		
	}
	
    public FarmTile() {
        this(FarmTile.DEFAULT_PRODUCTION_CAPACITY, FarmTile.DEFAULT_FARMER_CAPACITY);
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
	        return this == o || o.productionCapacity == this.productionCapacity && o.isDestroyed == this.isDestroyed 
	        		&& o.farmerCapacity==this.farmerCapacity;
	    }	    
	    

	    // Change
	    @Override
	    public void disassemble(CityResources res) {
	        if (!this.isDestroyed) {
	        	res.increaseFoodCapacity(-this.productionCapacity);
	        	int n = res.food - Math.min(res.foodCapacity,res.food);
	            res.spendF(n);
	            this.isDestroyed = true;

	            res.increaseFarmerCapacity(-this.farmerCapacity);
	            res.fireFarmer(res.getFarmer()-Math.min(res.getFarmerCapacity(),res.getFarmer()));
	        }
	        
	    }

	    @Override
	    public void update(CityResources res) {
	        if (!this.isDestroyed) {	            
	            /**
	             * La nourriture est mise à jour dans GameBoard dans nextState().
	             * 
	             * Le x sert à continuer d'augmenter le nombre de travailleurs même si celui ci est en 
	             * dessous de 4. On augmente au maximum d'un quart du nombre de chômeur le nombre de fermiers parce qu'il y a 4 métiers.
	             */
	            
	            int x;
	            if (res.getUnworkingPopulation() < 4 && res.getUnworkingPopulation() != 0){
	            	x = 1;            	
	            }
	            else{
	            	x = Math.round(res.getUnworkingPopulation()/4);
	            }     
	            
	            final int extraWorkingPopulation = Math.min(x, res.getFarmerCapacity()-res.getFarmer());
	            res.hireFarmer(extraWorkingPopulation);

	        }
	    }
    
	    private final static FarmTile INSTANCE = new FarmTile();

    
	    public static FarmTile getDefault() {
	        // Provide always the same instance since Farm is not changing. Usefull for tests
	        return FarmTile.INSTANCE;
	    }


}
