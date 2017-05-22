package model.tools;

import model.CityResources;
import model.tiles.ForestTile;
import model.tiles.GrassTile;
import model.tiles.Tile;

public final class LumberjackConstructionTool extends Tool{
    private final static int CURRENCY_COST = 40;


    /**
     * canEffect returns true if the given Tile is buildable, false otherwise.
     */
	@Override
	public boolean canEffect(Tile aTarget) {
		return aTarget instanceof GrassTile;
		}
	
	@Override
	public boolean equals(Object o) {
	    return this == o || o instanceof LumberjackConstructionTool;

	}
	
    /**
     * isAfordable returns true if the user can apply the LumberjackConstruction Tool, false
     * otherwise.
     */
	@Override
	public boolean isAfordable(Tile aTarget, CityResources r) {
        return LumberjackConstructionTool.CURRENCY_COST <= r.getCurrency();
	}

	@Override
	public int getCost(Tile aTarget) {
        return LumberjackConstructionTool.CURRENCY_COST;
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
	protected Tile innerEffect(Tile aTarget, CityResources r) {
        assert this.canEffect(aTarget);
        assert this.isAfordable(aTarget, r);
        r.increaseWoodCapacity(ForestTile.DEFAULT_PRODUCTION_CAPACITY);
        r.spend(LumberjackConstructionTool.CURRENCY_COST);
        r.increaseLumberjackCapacity(ForestTile.DEFAULT_LUMBERJACK_CAPACITY);

        return new ForestTile();
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
