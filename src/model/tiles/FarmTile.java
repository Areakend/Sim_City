package model.tiles;

import model.CityResources;

public class FarmTile extends Tile implements Destroyable{
	public boolean isDestroyed;

	public final static int EXTRA_FOOD_PRODUCTION = 5;

	public final static int DEFAULT_PRODUCTION_CAPACITY = 100;

	public final static int DEFAULT_FARMER_CAPACITY = 5;

	/**
	 * Maximum number of farmer per farm.
	 */
	public final int farmerCapacity;

	/**
	 * Maximum amount of food that a farm can store.
	 */
	public final int productionCapacity;

	/**
	 * @param productionCapacity
	 *           
	 * @param farmerCapacity
	 *            
	 */

	public FarmTile(int productionCapacity, int farmerCapacity){
		super();
		this.productionCapacity = productionCapacity;
		this.isDestroyed = false;
		this.farmerCapacity = farmerCapacity;

	}

	/**
	 * Create with default settings.
	 */
	public FarmTile() {
		this(FarmTile.DEFAULT_PRODUCTION_CAPACITY, FarmTile.DEFAULT_FARMER_CAPACITY);
	}

	public boolean isDestroyed() {
		return this.isDestroyed=false;
	}

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
			 * La nourriture est mise a jour dans GameBoard dans nextState().
			 * 
			 * Le x sert a continuer d'augmenter le nombre de travailleurs meme si celui ci est en 
			 * dessous de 4. On augmente au maximum d'un quart du nombre de chomeur le nombre de fermiers parce qu'il y a 4 metiers.
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
