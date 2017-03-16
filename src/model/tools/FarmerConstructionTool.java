package model.tools;

import model.CityResources;
import model.tiles.GrassTile;
import model.tiles.PowerPlantTile;
import model.tiles.Tile;

public class FarmerConstructionTool extends Tool {
	
    private final static int Wood_COST = 40;

	@Override
	public boolean canEffect(Tile aTarget) {
		return aTarget instanceof GrassTile;
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
	protected Tile innerEffect(Tile aTarget, CityResources r) {
        assert this.canEffect(aTarget);
        assert this.isAfordable(aTarget, r);

        r.spend(FarmerConstructionTool.Wood_COST);

        return new PowerPlantTile();
    }
	}


