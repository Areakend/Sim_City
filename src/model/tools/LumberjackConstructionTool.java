package model.tools;

import model.CityResources;
import model.tiles.ForestTile;
import model.tiles.GrassTile;
import model.tiles.Tile;

public final class LumberjackConstructionTool extends Tool{
    private final static int CURRENCY_COST = 40;

	@Override
	public boolean canEffect(Tile aTarget) {
		return aTarget instanceof GrassTile;
		}
	
	@Override
	public boolean equals(Object o) {
	    return this == o || o instanceof LumberjackConstructionTool;

	}

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

	@Override
	protected Tile innerEffect(Tile aTarget, CityResources r) {
        assert this.canEffect(aTarget);
        assert this.isAfordable(aTarget, r);

        r.spend(LumberjackConstructionTool.CURRENCY_COST);

        return new ForestTile();
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
