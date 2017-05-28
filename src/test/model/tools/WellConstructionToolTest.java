package test.model.tools;
import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.GrassTile;
import model.tiles.WellTile;
import model.tiles.RiverTile;
import model.tiles.Tile;
import model.tools.WellConstructionTool;

public class WellConstructionToolTest {
	
    @Test
    public void testCanAffect() {
        WellConstructionTool ppt = new WellConstructionTool();
        Tile tile = GrassTile.getDefault();
        Tile tile2 = RiverTile.getDefault();
        Assert.assertEquals( ppt.canEffect(tile), tile == GrassTile.getDefault());
        Assert.assertEquals( ppt.canEffect(tile2), tile2 == GrassTile.getDefault());
    }
    
    
    @Test
    public void testisAffordable() {
        WellConstructionTool ppt = new WellConstructionTool();
        CityResources resources = new CityResources(100);
        int initialValue2 = resources.getCurrency();
        int cost2 = WellConstructionTool.CURRENCY_COST;
        Assert.assertEquals( ppt.isAfordable(GrassTile.getDefault(), resources), cost2<initialValue2);
    }
    
    @Test
    public void testgetCost() {
        WellConstructionTool ppt = new WellConstructionTool();
        int initialValue = WellConstructionTool.CURRENCY_COST;
        Tile tile = GrassTile.getDefault();
        Tile tile2 = RiverTile.getDefault();
        Assert.assertEquals( ppt.getCost(tile), initialValue);
        Assert.assertEquals( ppt.getCost(tile2), initialValue);

    }

    
    @Test
    public void testinnerEffect() {
        WellConstructionTool ppt = new WellConstructionTool();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getRock();
        int initialValue2 = resources.getCurrency();
        int cost2 = WellConstructionTool.CURRENCY_COST;
        Tile tile = ppt.innerEffect(GrassTile.getDefault(), resources);
        Assert.assertEquals(resources.getCurrency(), initialValue2 - cost2);
    }
    
    
}
