package model.tools;

import model.CityResources;
import model.tiles.GrassTile;
import model.tiles.PowerPlantTile;
import model.tiles.Tile;

public final class MineConstructionTool extends Tool {
	
    private final static int Rock_COST = 40;

	
	@Override
	public boolean canEffect(Tile aTarget) {
		return aTarget instanceof GrassTile;
		}
	
	@Override
	public boolean equals(Object o) {
	    return this == o || o instanceof MineConstructionTool;

	}

	@Override
	public boolean isAfordable(Tile aTarget, CityResources r) {
        return MineConstructionTool.Rock_COST <= r.getRock();
	}

	@Override
	public int getCost(Tile aTarget) {
        return MineConstructionTool.Rock_COST;
	}
	
    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

	@Override
	protected Tile innerEffect(Tile aTarget, CityResources r) {
        assert this.canEffect(aTarget);
        assert this.isAfordable(aTarget, r);

        r.spendR(MineConstructionTool.Rock_COST);

        return new PowerPlantTile();
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
	}


