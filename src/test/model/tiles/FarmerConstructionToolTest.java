package test.model.tiles;
import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.FarmTile;
import model.tiles.GrassTile;
import model.tiles.RiverTile;
import model.tiles.Tile;
import model.tools.BridgeConstructionTool;
import model.tools.FarmerConstructionTool;

public class FarmerConstructionToolTest {
	
    @Test
    public void testCanAffect() {
        FarmerConstructionTool ppt = new FarmerConstructionTool();
        Tile tile = GrassTile.getDefault();
        Tile tile2 = RiverTile.getDefault();
        Assert.assertEquals( ppt.canEffect(tile), tile == GrassTile.getDefault());
        Assert.assertEquals( ppt.canEffect(tile2), tile2 == GrassTile.getDefault());
    }
    
    
    @Test
    public void testisAffordable() {
        FarmerConstructionTool ppt = new FarmerConstructionTool();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getWood();
        int initialValue2 = resources.getCurrency();
        int cost = FarmerConstructionTool.Wood_COST;
        int cost2 = FarmerConstructionTool.cout;
        Assert.assertEquals( ppt.isAfordable(GrassTile.getDefault(), resources), (cost<initialValue && cost2<initialValue2));
    }
    
    @Test
    public void testgetCost() {
        FarmerConstructionTool ppt = new FarmerConstructionTool();
        int initialValue = FarmerConstructionTool.Wood_COST;
        Tile tile = GrassTile.getDefault();
        Tile tile2 = RiverTile.getDefault();
        Assert.assertEquals( ppt.getCost(tile), initialValue);
        Assert.assertEquals( ppt.getCost(tile2), initialValue);

    }

    
    @Test
    public void testinnerEffect() {
        FarmerConstructionTool ppt = new FarmerConstructionTool();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getWood();
        int initialValue2 = resources.getCurrency();
        int cost = FarmerConstructionTool.Wood_COST;
        int cost2 = FarmerConstructionTool.cout;
        int FarmerCap = FarmTile.DEFAULT_FARMER_CAPACITY;
        int initFarm = resources.getFarmerCapacity();
        int FoodCap = FarmTile.DEFAULT_PRODUCTION_CAPACITY;
        int initFood = resources.getFoodCapacity();
        Tile tile = ppt.innerEffect(GrassTile.getDefault(), resources);
        Assert.assertEquals(resources.getWood(), initialValue - cost);
        Assert.assertEquals(resources.getFoodCapacity(), initFood + FoodCap);
        Assert.assertEquals(resources.getFarmerCapacity(), initFarm + FarmerCap);
        Assert.assertEquals(resources.getCurrency(), initialValue2 - cost2);
    }
    
    
}
