package test.model.tools;
import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.FarmTile;
import model.tiles.GrassTile;
import model.tiles.RiverTile;
import model.tiles.Tile;
import model.tools.BulldozerTool;

public class BulldozerToolTest {
	
    @Test
    public void testCanAffect() {
        BulldozerTool ppt = new BulldozerTool();
        Tile tile = FarmTile.getDefault();
        Assert.assertEquals( ppt.canEffect(tile), tile != GrassTile.getDefault() && tile !=RiverTile.getDefault());
    }
    
    
    @Test
    public void testisAffordable() {
    	BulldozerTool ppt = new BulldozerTool();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getCurrency();
        int cost = BulldozerTool.CURRENCY_COST;
        Assert.assertEquals( ppt.isAfordable(FarmTile.getDefault(), resources), cost<initialValue);
    }
    
    @Test
    public void testgetCost() {
    	BulldozerTool ppt = new BulldozerTool();
        int initialValue = BulldozerTool.CURRENCY_COST;
        Tile tile = FarmTile.getDefault();
        Assert.assertEquals( ppt.getCost(tile), initialValue);

    }

    
}
