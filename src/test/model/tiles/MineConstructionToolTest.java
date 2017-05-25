package test.model.tiles;
import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.GrassTile;
import model.tiles.MineTile;
import model.tiles.RiverTile;
import model.tiles.Tile;
import model.tools.MineConstructionTool;

public class MineConstructionToolTest {
	
    @Test
    public void testCanAffect() {
        MineConstructionTool ppt = new MineConstructionTool();
        Tile tile = GrassTile.getDefault();
        Tile tile2 = RiverTile.getDefault();
        Assert.assertEquals( ppt.canEffect(tile), tile == GrassTile.getDefault());
        Assert.assertEquals( ppt.canEffect(tile2), tile2 == GrassTile.getDefault());
    }
    
    
    @Test
    public void testisAffordable() {
        MineConstructionTool ppt = new MineConstructionTool();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getRock();
        int cost = MineConstructionTool.Rock_COST;
        Assert.assertEquals( ppt.isAfordable(GrassTile.getDefault(), resources), cost<initialValue);
    }
    
    @Test
    public void testgetCost() {
        MineConstructionTool ppt = new MineConstructionTool();
        int initialValue = MineConstructionTool.Rock_COST;
        Tile tile = GrassTile.getDefault();
        Tile tile2 = RiverTile.getDefault();
        Assert.assertEquals( ppt.getCost(tile), initialValue);
        Assert.assertEquals( ppt.getCost(tile2), initialValue);

    }

    
    @Test
    public void testinnerEffect() {
        MineConstructionTool ppt = new MineConstructionTool();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getRock();
        int cost = MineConstructionTool.Rock_COST;
        int FarmerCap = MineTile.DEFAULT_MINER_CAPACITY;
        int initFarm = resources.getFarmerCapacity();
        int FoodCap = MineTile.DEFAULT_PRODUCTION_CAPACITY;
        int initFood = resources.getFoodCapacity();
        Tile tile = ppt.innerEffect(GrassTile.getDefault(), resources);
        Assert.assertEquals(resources.getRock(), initialValue - cost);
        Assert.assertEquals(resources.getRockCapacity(), initFood + FoodCap);
        Assert.assertEquals(resources.getMinerCapacity(), initFarm + FarmerCap);
    }
    
    
}
