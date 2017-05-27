package test.model.tiles;
import org.junit.Assert;
import org.junit.Test;

import model.CityResources;
import model.tiles.GrassTile;
import model.tiles.RiverTile;
import model.tiles.Tile;
import model.tools.BridgeConstructionTool;


public class BridgeConstructionToolTest {

	@Test
    public void testCanAffect() {
        BridgeConstructionTool ppt = new BridgeConstructionTool();
        Tile tile = GrassTile.getDefault();
        Tile tile2 = RiverTile.getDefault();
        Assert.assertEquals( ppt.canEffect(tile), tile == RiverTile.getDefault());
        Assert.assertEquals( ppt.canEffect(tile2), tile2 == RiverTile.getDefault());
    }
    
    
    @Test
    public void testisAffordable() {
        BridgeConstructionTool ppt = new BridgeConstructionTool();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getWood();
        int initialValue2 = resources.getCurrency();
        int cost = BridgeConstructionTool.Wood_COST;
        int cost2 = BridgeConstructionTool.cout;
        Assert.assertEquals( ppt.isAfordable(RiverTile.getDefault(), resources), (cost<initialValue && cost2<initialValue2));
    }
    
    @Test
    public void testgetCost() {
        BridgeConstructionTool ppt = new BridgeConstructionTool();
        int initialValue = BridgeConstructionTool.Wood_COST;
        Tile tile = GrassTile.getDefault();
        Tile tile2 = RiverTile.getDefault();
        Assert.assertEquals( ppt.getCost(tile), initialValue);
        Assert.assertEquals( ppt.getCost(tile2), initialValue);

    }

    
    @Test
    public void testinnerEffect() {
        BridgeConstructionTool ppt = new BridgeConstructionTool();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getWood();
        int initialValue2 = resources.getCurrency();
        int cost = BridgeConstructionTool.Wood_COST;
        int cost2 = BridgeConstructionTool.cout;
        Tile tile = ppt.innerEffect(RiverTile.getDefault(), resources);
        Assert.assertEquals(resources.getWood(), initialValue - cost);
        Assert.assertEquals(resources.getCurrency(), initialValue2 - cost2);

    }
    
    
}
