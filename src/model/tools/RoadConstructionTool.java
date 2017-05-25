package model.tools;

import model.CityResources;
import model.tiles.RiverTile;
import model.tiles.BridgeTile;
import model.tiles.Tile;

public final class RoadConstructionTool extends Tool {
	
    private final static int Wood_COST = 5;

	@Override
	public boolean canEffect(Tile aTarget) {
		return aTarget instanceof RiverTile;
		}
	
	@Override
	public boolean equals(Object o) {
	    return this == o || o instanceof RoadConstructionTool;

	}

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

	@Override
	protected Tile innerEffect(Tile aTarget, CityResources r) {
        assert this.canEffect(aTarget);
        assert this.isAfordable(aTarget, r);

        r.spendW(RoadConstructionTool.Wood_COST);

        return new BridgeTile();
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
	}


