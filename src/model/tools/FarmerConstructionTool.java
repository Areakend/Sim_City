package model.tools;

import model.CityResources;
import model.tiles.FarmTile;
import model.tiles.GrassTile;
import model.tiles.Tile;

public final class FarmerConstructionTool extends Tool {
	
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
        return FarmerConstructionTool.Wood_COST <= r.getWood();
	}

	@Override
	public int getCost(Tile aTarget) {
        return FarmerConstructionTool.Wood_COST;
	}
	
    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

	@Override
	protected Tile innerEffect(Tile aTarget, CityResources r) {
        assert this.canEffect(aTarget);
        assert this.isAfordable(aTarget, r);

        r.spendW(FarmerConstructionTool.Wood_COST);
        r.increaseFoodCapacity(FarmTile.DEFAULT_PRODUCTION_CAPACITY);
               
        return new FarmTile();
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
	}


