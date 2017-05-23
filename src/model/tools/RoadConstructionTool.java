package model.tools;

import model.CityResources;
import model.tiles.RiverTile;
import model.tiles.RoadTile;
import model.tiles.Tile;

public final class RoadConstructionTool extends Tool {
	
    public final static int Wood_COST = 5;

    /**
     * canEffect returns true if the given Tile is buildable, false otherwise.
     */
	@Override
	public boolean canEffect(Tile aTarget) {
		return aTarget instanceof RiverTile;
		}
	
	@Override
	public boolean equals(Object o) {
	    return this == o || o instanceof RoadConstructionTool;

	}
	
    /**
     * isAfordable returns true if the user can apply the RoadConstruction Tool, false
     * otherwise.
     */
	@Override
	public boolean isAfordable(Tile aTarget, CityResources r) {
        return RoadConstructionTool.Wood_COST <= r.getWood();
	}

	@Override
	public int getCost(Tile aTarget) {
        return RoadConstructionTool.Wood_COST;
	}
	
    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

    /**
     * innerEffect apply the RoadConstruction tool to the given tile and update the
     * given CityResources.
     */
	@Override
	public Tile innerEffect(Tile aTarget, CityResources r) {
        assert this.canEffect(aTarget);
        assert this.isAfordable(aTarget, r);

        r.spendW(RoadConstructionTool.Wood_COST);

        return new RoadTile();
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
	}


