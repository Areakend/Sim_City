package model.tools;

import model.CityResources;
import model.tiles.ForestTile;
import model.tiles.GrassTile;
import model.tiles.Tile;

public final class LumberjackConstructionTool extends Tool{
    private final static int Wood_COST = 40;

	@Override
	public boolean canEffect(Tile aTarget) {
		return aTarget instanceof GrassTile;
		}
	
	@Override
	public boolean equals(Object o) {
	    return this == o || o instanceof FarmerConstructionTool;

	}

	@Override
	public boolean isAfordable(Tile aTarget, CityResources r) {
        return LumberjackConstructionTool.Wood_COST <= r.getWood();
	}

	@Override
	public int getCost(Tile aTarget) {
        return LumberjackConstructionTool.Wood_COST;
	}
	
    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

	@Override
	protected Tile innerEffect(Tile aTarget, CityResources r) {
        assert this.canEffect(aTarget);
        assert this.isAfordable(aTarget, r);

        r.spendW(LumberjackConstructionTool.Wood_COST);

        return new ForestTile();
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
