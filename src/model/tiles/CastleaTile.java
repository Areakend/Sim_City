package model.tiles;

import model.CityResources;

/**
 * State-less tile that represents castle tiles.
 */
public final class CastleaTile extends Tile {
	public final static int DEFAULT_KNIGHT_CAPACITY = 100;

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
	public void disassemble(CityResources res){}

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
        /**
         * Les chevaliers coûtent 50 de fer chacun. On ne peut donc pas avoir de nouveaux chevaliers tant que la réserve de fer est insuffisante.
         */

        final int extraWorkingPopulation = Math.min(x, res.getKnightCapacity()-res.getKnight());
        if (extraWorkingPopulation*50 <= res.steel){
            res.hireKnight(extraWorkingPopulation);
            res.spendS(extraWorkingPopulation*50);
        }
        else{
        	int n = Math.round(res.getSteel()/50);
        	int m = Math.min(res.getUnworkingPopulation(), n);
        	res.hireKnight(m);
        	res.spendS(m*50);
        }
	}

}
