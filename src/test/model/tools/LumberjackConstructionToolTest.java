package test.model.tools;
import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.ForestTile;
import model.tiles.GrassTile;
import model.tiles.RiverTile;
import model.tiles.Tile;
import model.tools.FarmerConstructionTool;
import model.tools.LumberjackConstructionTool;

public class LumberjackConstructionToolTest {
	
    @Test
    public void testCanAffect() {
        LumberjackConstructionTool ppt = new LumberjackConstructionTool();
        Tile tile = GrassTile.getDefault();
        Tile tile2 = RiverTile.getDefault();
        Assert.assertEquals( ppt.canEffect(tile), tile == GrassTile.getDefault());
        Assert.assertEquals( ppt.canEffect(tile2), tile2 == GrassTile.getDefault());
    }
    
    
    @Test
    public void testisAffordable() {
        FarmerConstructionTool ppt = new FarmerConstructionTool();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getCurrency();
        int cost = LumberjackConstructionTool.CURRENCY_COST;
        Assert.assertEquals( ppt.isAfordable(GrassTile.getDefault(), resources), cost<initialValue);
    }
    
    @Test
    public void testgetCost() {
        LumberjackConstructionTool ppt = new LumberjackConstructionTool();
        int initialValue = LumberjackConstructionTool.CURRENCY_COST;
        Tile tile = GrassTile.getDefault();
        Tile tile2 = RiverTile.getDefault();
        Assert.assertEquals( ppt.getCost(tile), initialValue);
        Assert.assertEquals( ppt.getCost(tile2), initialValue);

    }

    
    @Test
    public void testinnerEffect() {
    	LumberjackConstructionTool ppt = new LumberjackConstructionTool();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getCurrency();
        int cost = LumberjackConstructionTool.CURRENCY_COST;
        int FarmerCap = ForestTile.DEFAULT_LUMBERJACK_CAPACITY;
        int initFarm = resources.getFarmerCapacity();
        int FoodCap = ForestTile.DEFAULT_PRODUCTION_CAPACITY;
        int initFood = resources.getFoodCapacity();
        Tile tile = ppt.innerEffect(GrassTile.getDefault(), resources);
        Assert.assertEquals(resources.getCurrency(), initialValue - cost);
        Assert.assertEquals(resources.getWoodCapacity(), initFood + FoodCap);
        Assert.assertEquals(resources.getLumberjackCapacity(), initFarm + FarmerCap);
    }
    
    
}
