package model.tools;

import model.CityResources;
import model.tiles.FarmTile;
import model.tiles.GrassTile;
import model.tiles.Tile;

public final class FarmerConstructionTool extends Tool {
	
    public final static int Wood_COST = 40;

    /**
     * canEffect returns true if the given Tile is buildable, false otherwise.
     */
	@Override
	public boolean canEffect(Tile aTarget) {
		return aTarget instanceof GrassTile;
		}
	
	@Override
	public boolean equals(Object o) {
	    return this == o || o instanceof FarmerConstructionTool;

	}
	
    /**
     * isAfordable returns true if the user can apply the FarmerConstruction Tool, false
     * otherwise.
     */
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

    /**
     * innerEffect apply the FarmerConstruction tool to the given tile and update the
     * given CityResources.
     */
	@Override
	public Tile innerEffect(Tile aTarget, CityResources r) {
        assert this.canEffect(aTarget);
        assert this.isAfordable(aTarget, r);

        r.spendW(FarmerConstructionTool.Wood_COST);
        r.increaseFoodCapacity(FarmTile.DEFAULT_PRODUCTION_CAPACITY);
        r.increaseFarmerCapacity(FarmTile.DEFAULT_FARMER_CAPACITY);
               
        return new FarmTile();
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
	}


