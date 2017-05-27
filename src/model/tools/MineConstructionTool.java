package model.tools;

import model.CityResources;
import model.tiles.GrassTile;
import model.tiles.MineTile;
import model.tiles.Tile;

public final class MineConstructionTool extends Tool {
	
    public final static int Rock_COST = 40;
    public static int cout =20;


    /**
     * canEffect returns true if the given Tile is buildable, false otherwise.
     */
	@Override
	public boolean canEffect(Tile aTarget) {
		return aTarget instanceof GrassTile;
		}
	
	@Override
	public boolean equals(Object o) {
	    return this == o || o instanceof MineConstructionTool;

	}
	
    /**
     * isAfordable returns true if the user can apply the MineConstruction Tool, false
     * otherwise.
     */
	@Override
	public boolean isAfordable(Tile aTarget, CityResources r) {
        return (MineConstructionTool.Rock_COST <= r.getRock() && cout<r.getCurrency());
	}

	@Override
	public int getCost(Tile aTarget) {
        return MineConstructionTool.Rock_COST;
	}
	
    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

    /**
     * innerEffect apply the LumberjackConstruction tool to the given tile and update the
     * given CityResources.
     */
	@Override
	public Tile innerEffect(Tile aTarget, CityResources r) {
        assert this.canEffect(aTarget);
        assert this.isAfordable(aTarget, r);

        r.spendR(MineConstructionTool.Rock_COST);
        r.spend(MineConstructionTool.cout);
        r.increaseRockCapacity(MineTile.DEFAULT_PRODUCTION_CAPACITY);
        r.increaseSteelCapacity(MineTile.DEFAULT_PRODUCTION_CAPACITY);
        r.increaseMinerCapacity(MineTile.DEFAULT_MINER_CAPACITY);

        return new MineTile();
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
	}


