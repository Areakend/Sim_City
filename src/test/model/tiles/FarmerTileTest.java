package test.model.tiles;
import org.junit.Assert;
import org.junit.Test;

import localization.FRTexts;
import localization.LocalizedTexts;
import model.CityResources;
import model.GameBoard;
import model.tiles.FarmTile;
import model.tiles.GrassTile;
import model.tiles.ResidentialTile;
import model.tiles.Tile;
import model.tiles.WellTile;
import model.tools.FarmerConstructionTool;


public class FarmerTileTest {

    @Test
    public void testupdate() {
    	LocalizedTexts text = new FRTexts();
        GameBoard gb = new GameBoard(10,text);
        CityResources resources = new CityResources(100);
        WellTile WT = new WellTile();
        WT.update(resources);
        ResidentialTile ppt = new ResidentialTile();
        FarmerConstructionTool FC = new FarmerConstructionTool();
        FarmTile FT = new FarmTile();
        FC.innerEffect(GrassTile.getDefault(), resources);
        ppt.evolve(resources);
        ppt.update(resources);
        int initialValue2 = resources.getFarmer();
        FT.update(resources);;
        int cost2 =1;
        Assert.assertEquals(resources.getFarmer(), initialValue2 + cost2 );

    }

    @Test
    public void testDisassemble() {
    	LocalizedTexts text = new FRTexts();
        GameBoard gb = new GameBoard(10,text);
    	CityResources resources = new CityResources(100);
        WellTile WT = new WellTile();
        WT.update(resources);
        ResidentialTile ppt = new ResidentialTile();
        FarmerConstructionTool FC = new FarmerConstructionTool();
        FarmTile FT = new FarmTile();
        FC.innerEffect(GrassTile.getDefault(), resources);
        ppt.update(resources);
        ppt.evolve(resources);
        FT.update(resources);
        int initialValue = resources.getFood();
        int initialValue2 = resources.getFarmerCapacity();
        int initialValue3 = resources.getFoodCapacity();  
        int initialValue4 = resources.getFarmer();
        int cost = FT.productionCapacity;
        int cost2 = FT.farmerCapacity;
        int cost4 = Math.min(resources.getFarmerCapacity(),resources.getFarmer());
        int cost3 = Math.min(resources.foodCapacity,resources.food);
        if (resources.getFood() < 101) { cost3 = 0;};
        FT.disassemble(resources);
        Assert.assertEquals(resources.getFoodCapacity(), initialValue3 - cost);
        Assert.assertEquals(resources.getFarmer(),  initialValue4 - cost4);
        Assert.assertEquals(resources.getFarmerCapacity(),  initialValue2 - cost2);
        Assert.assertEquals(resources.getFood(),  initialValue - cost3);

    }
    
    
}
