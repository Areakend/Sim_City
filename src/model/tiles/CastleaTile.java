package model.tiles;

import model.CityResources;

/**
 * State-less tile that represents castle tiles.
 */
public final class CastleaTile extends Tile {
	public final static int DEFAULT_KNIGHT_CAPACITY = 100;


	protected int knight;
	protected final int knightCapacity;
	// Constant
	/**
	 * Default instance.
	 */
	private final static CastleaTile INSTANCE = new CastleaTile(CastleaTile.DEFAULT_KNIGHT_CAPACITY);

	// Factory
	/**
	 * @return Default castle tile.
	 */
	public static CastleaTile getDefault() {
		// Provide always the same instance since Castle is not changing.
		return CastleaTile.INSTANCE;
	}

	// Creation
	/**
	 * Prefer use {@link CastleaTile#getDefault()} instead.
	 */
	private CastleaTile(int knightCapacity) {
		this.knight = 0;
		this.knightCapacity = knightCapacity;
	}
	
	private CastleaTile(){
		this(CastleaTile.DEFAULT_KNIGHT_CAPACITY);
	}

	// Access
	@Override
	public int hashCode() {
		return 0;
	}

	// Status
	@Override
	public boolean equals(Object o) {
		return this == o || o instanceof CastleaTile;
	}

	// Change
	@Override
	public void update(CityResources res) {

        /**
         * Le x sert à continuer d'augmenter le nombre de travailleurs même si celui ci est en 
         * dessous de 4. On augmente au maximum d'un quart du nombre de chômeur le nombre de chevaliers parce qu'il y a 4 métiers.
         */
        
        int x;
        if (res.getUnworkingPopulation() < 4 && res.getUnworkingPopulation() != 0){
        	x = 1;            	
        }
        else{
        	x = Math.round(res.getUnworkingPopulation()/4);
        }           
        

        final int extraWorkingPopulation = Math.min(x, this.knightCapacity-this.knight);
        this.knight += extraWorkingPopulation;
        res.hireWorkers(extraWorkingPopulation);
	}

}
