package test.model.tiles;
import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.RoadTile;
import model.tiles.GrassTile;
import model.tiles.RiverTile;
import model.tiles.Tile;
import model.tools.RoadConstructionTool;


public class RoadConstructionToolTest {

	@Test
    public void testCanAffect() {
        RoadConstructionTool ppt = new RoadConstructionTool();
        Tile tile = GrassTile.getDefault();
        Tile tile2 = RiverTile.getDefault();
        Assert.assertEquals( ppt.canEffect(tile), tile == RiverTile.getDefault());
        Assert.assertEquals( ppt.canEffect(tile2), tile2 == RiverTile.getDefault());
    }
    
    
    @Test
    public void testisAffordable() {
        RoadConstructionTool ppt = new RoadConstructionTool();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getWood();
        int cost = RoadConstructionTool.Wood_COST;
        Assert.assertEquals( ppt.isAfordable(RiverTile.getDefault(), resources), cost<initialValue);
    }
    
    @Test
    public void testgetCost() {
        RoadConstructionTool ppt = new RoadConstructionTool();
        int initialValue = RoadConstructionTool.Wood_COST;
        Tile tile = GrassTile.getDefault();
        Tile tile2 = RiverTile.getDefault();
        Assert.assertEquals( ppt.getCost(tile), initialValue);
        Assert.assertEquals( ppt.getCost(tile2), initialValue);

    }

    
    @Test
    public void testinnerEffect() {
        RoadConstructionTool ppt = new RoadConstructionTool();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getWood();
        int cost = RoadConstructionTool.Wood_COST;
        Tile tile = ppt.innerEffect(RiverTile.getDefault(), resources);
        Assert.assertEquals(resources.getWood(), initialValue - cost);
    }
    
    
}
