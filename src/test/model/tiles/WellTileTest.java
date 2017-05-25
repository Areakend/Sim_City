package test.model.tiles;
import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.WellTile;

public class WellTileTest {
    
    @Test
    public void testInit() {
        WellTile ppt = new WellTile();
        Assert.assertEquals(WellTile.DEFAULT_PRODUCTION_CAPACITY, ppt.getProductionCapacity());
        ppt = new WellTile(10);
        Assert.assertEquals(10, ppt.getProductionCapacity());
    }
    
    @Test
    public void testUpdate() {
        WellTile ppt = new WellTile();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getWaterProduction();
        ppt.update(resources);
        Assert.assertEquals(initialValue + ppt.getProduction(), resources.getWaterProduction());
    }
    
    @Test
    public void testDisassemble() {
        WellTile ppt = new WellTile();
        CityResources resources = new CityResources(100);
        ppt.update(resources);
        int initialProduction = resources.getWaterProduction();
        ppt.disassemble(resources);
        Assert.assertEquals(Math.max(0, initialProduction - ppt.getProductionCapacity()), resources.getWaterProduction());
    }
    
    @Test
    public void testIsDestroyed() {
        WellTile ppt = new WellTile();
        CityResources resources = new CityResources(100);
        ppt.disassemble(resources);
        Assert.assertEquals(true, ppt.isDestroyed());
    }
    
    @Test
    public void testIsEquals() {
        WellTile ppt1 = new WellTile();
        CityResources resources = new CityResources(100);
        ppt1.update(resources);
        ppt1.disassemble(resources);
        WellTile ppt2 = new WellTile();
        ppt2.update(resources);
        ppt2.disassemble(resources);
        Assert.assertEquals(true, ppt1.equals(ppt2));
    }
    
    
}
