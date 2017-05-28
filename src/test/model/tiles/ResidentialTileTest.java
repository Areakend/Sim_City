package test.model.tiles;
import org.junit.Assert;
import org.junit.Test;

import localization.FRTexts;
import localization.LocalizedTexts;
import model.CityResources;
import model.GameBoard;
import model.tiles.ResidentialTile;
import model.tiles.Tile;
import model.tiles.WellTile;


public class ResidentialTileTest {

    @Test
    public void testevolve() {
    	LocalizedTexts text = new FRTexts();
    	GameBoard gb = new GameBoard(10,text);
        CityResources resources = new CityResources(100);
        WellTile WT = new WellTile();
        WT.update(resources);
        ResidentialTile ppt = new ResidentialTile();
        ppt.evolve(resources);
        ppt.update(resources);
        int initialValue2 = resources.getPopulationCapacity();
        int cost2 = ppt.inhabitantsCapacity;
        ppt.evolve(resources);
        Assert.assertEquals(resources.getPopulationCapacity(), initialValue2 + cost2);

    }
	
    @Test
    public void testDisassemble() {
    	LocalizedTexts text = new FRTexts();
        GameBoard gb = new GameBoard(10,text);
        CityResources resources = new CityResources(100);
        WellTile WT = new WellTile();
        WT.update(resources);
        ResidentialTile ppt = new ResidentialTile();
        ppt.update(resources);
        ppt.evolve(resources);
        int initialValue = resources.getPopulation();
        int initialValue2 = resources.getPopulationCapacity();
        int cost = ResidentialTile.DEFAULT_MAX_JOINING_INHABITANTS;
        int cost2 = ppt.inhabitantsCapacity;
        ppt.disassemble(resources);
        Assert.assertEquals(resources.getPopulation(),(Math.max(0, initialValue - cost)));
        Assert.assertEquals(resources.getPopulationCapacity(), initialValue2 - cost2);

    }
    
    
}
