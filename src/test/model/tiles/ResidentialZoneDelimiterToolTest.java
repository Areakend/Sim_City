package test.model.tiles;
import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.ResidentialTile;
import model.tiles.GrassTile;
import model.tiles.RiverTile;
import model.tiles.Tile;
import model.tools.ResidentialZoneDelimiterTool;

public class ResidentialZoneDelimiterToolTest {
	
    @Test
    public void testCanAffect() {
    	ResidentialZoneDelimiterTool ppt = new ResidentialZoneDelimiterTool();
        Tile tile = GrassTile.getDefault();
        Tile tile2 = RiverTile.getDefault();
        Assert.assertEquals( ppt.canEffect(tile), tile == GrassTile.getDefault());
        Assert.assertEquals( ppt.canEffect(tile2), tile2 == GrassTile.getDefault());
    }
    
    
    @Test
    public void testisAffordable() {
    	ResidentialZoneDelimiterTool ppt = new ResidentialZoneDelimiterTool();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getWood();
        int cost = ResidentialZoneDelimiterTool.Wood_COST;
        Assert.assertEquals( ppt.isAfordable(GrassTile.getDefault(), resources), cost<initialValue);
    }
    
    @Test
    public void testgetCost() {
    	ResidentialZoneDelimiterTool ppt = new ResidentialZoneDelimiterTool();
        int initialValue = ResidentialZoneDelimiterTool.Wood_COST;
        Tile tile = GrassTile.getDefault();
        Tile tile2 = RiverTile.getDefault();
        Assert.assertEquals( ppt.getCost(tile), initialValue);
        Assert.assertEquals( ppt.getCost(tile2), initialValue);

    }

    
    @Test
    public void testinnerEffect() {
    	ResidentialZoneDelimiterTool ppt = new ResidentialZoneDelimiterTool();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getWood();
        int cost = ResidentialZoneDelimiterTool.Wood_COST;
        int FarmerCap = ResidentialTile.DEFAULT_INHABITANTS_CAPACITY;
        int initFarm = resources.getPopulationCapacity();
        Tile tile = ppt.innerEffect(GrassTile.getDefault(), resources);
        Assert.assertEquals(resources.getWood(), initialValue - cost);
        Assert.assertEquals(resources.getPopulationCapacity(), initFarm + FarmerCap);
    }
    
    
}
