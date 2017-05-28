package test.model.tiles;
import org.junit.Assert;
import org.junit.Test;

import localization.FRTexts;
import localization.LocalizedTexts;
import model.CityResources;
import model.GameBoard;
import model.tiles.FarmTile;
import model.tiles.ResidentialTile;
import model.tiles.Tile;
import model.tiles.WellTile;


public class FarmerTileTest {

    @Test
    public void testupdate() {
    	LocalizedTexts text = new FRTexts();
        GameBoard gb = new GameBoard(10,text);
        CityResources resources = new CityResources(100);
        WellTile WT = new WellTile();
        WT.update(resources);
        ResidentialTile ppt = new ResidentialTile();
        FarmTile FT = new FarmTile();
        ppt.evolve(resources);
        ppt.update(resources);
        FT.update(resources);
        FT.update(resources);
        int initialValue2 = resources.getFarmer();
        int cost2 = 1;
        Assert.assertEquals(resources.getFarmer(), initialValue2 + cost2);

    }
	
    @Test
    public void testDisassemble() {
    	LocalizedTexts text = new FRTexts();
        GameBoard gb = new GameBoard(10,text);
    	CityResources resources = new CityResources(100);
        WellTile WT = new WellTile();
        WT.update(resources);
        ResidentialTile ppt = new ResidentialTile();
        FarmTile FT = new FarmTile();
        ppt.update(resources);
        ppt.evolve(resources);
        FT.update(resources);
        int initialValue = resources.getFood();
        int initialValue2 = resources.getFarmerCapacity();
        int initialValue3 = resources.getFoodCapacity();        
        int cost = FT.productionCapacity;
        int cost2 = FT.farmerCapacity;
        //int cost3 = FT.foodCapacity;
        ppt.disassemble(resources);
        Assert.assertEquals(resources.getFoodCapacity(), initialValue3 - cost);
        Assert.assertEquals(resources.getFarmerCapacity(), initialValue2 - cost2);

    }
    
    
}
