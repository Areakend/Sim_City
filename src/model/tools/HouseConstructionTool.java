package model.tools;

import model.CityResources;
import model.tiles.GrassTile;
import model.tiles.MineTile;
import model.tiles.Tile;

public final class HouseConstructionTool extends Tool {
	
    private final static int Wood_COST = 20;

	
	@Override
	public boolean canEffect(Tile aTarget) {
		return aTarget instanceof GrassTile;
		}
	
	@Override
	public boolean equals(Object o) {
	    return this == o || o instanceof HouseConstructionTool;

	}

	@Override
	public boolean isAfordable(Tile aTarget, CityResources r) {
        return (HouseConstructionTool.Wood_COST <= r.getWood());
	}

	@Override
	public int getCost(Tile aTarget) {
        return HouseConstructionTool.Wood_COST;
	}
	
    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

	@Override
	protected Tile innerEffect(Tile aTarget, CityResources r) {
        assert this.canEffect(aTarget);
        assert this.isAfordable(aTarget, r);

        r.spendW(HouseConstructionTool.Wood_COST);

        return new MineTile();
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
	}


