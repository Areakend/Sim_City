package test.model.tiles;
import org.junit.Assert;
import org.junit.Test;

import localization.FRTexts;
import localization.LocalizedTexts;
import model.CityResources;
import model.GameBoard;
import model.tiles.MineTile;
import model.tiles.GrassTile;
import model.tiles.ResidentialTile;
import model.tiles.Tile;
import model.tiles.WellTile;
import model.tools.MineConstructionTool;


public class MineTileTest {

    @Test
    public void testupdate() {
    	LocalizedTexts text = new FRTexts();
        GameBoard gb = new GameBoard(10,text);
        CityResources resources = new CityResources(100);
        WellTile WT = new WellTile();
        WT.update(resources);
        ResidentialTile ppt = new ResidentialTile();
        MineConstructionTool FC = new MineConstructionTool();
        MineTile FT = new MineTile();
        FC.innerEffect(GrassTile.getDefault(), resources);
        ppt.evolve(resources);
        ppt.update(resources);
        int initialValue2 = resources.getMiner();
        FT.update(resources);;
        int cost2 =1;
        Assert.assertEquals(resources.getMiner(), initialValue2 + cost2 );

    }

    @Test
    public void testDisassemble() {
    	LocalizedTexts text = new FRTexts();
        GameBoard gb = new GameBoard(10,text);
    	CityResources resources = new CityResources(100);
        WellTile WT = new WellTile();
        WT.update(resources);
        ResidentialTile ppt = new ResidentialTile();
        MineConstructionTool FC = new MineConstructionTool();
        MineTile FT = new MineTile();
        FC.innerEffect(GrassTile.getDefault(), resources);
        ppt.update(resources);
        ppt.evolve(resources);
        FT.update(resources);
        int initialValue = resources.getRock();
        int initialValue2 = resources.getMinerCapacity();
        int initialValue3 = resources.getRockCapacity();  
        int initialValue4 = resources.getMiner();
        int initialValue5 = resources.getSteelCapacity();  
        int cost = FT.productionCapacity;
        int cost2 = FT.minerCapacity;
        int cost4 = Math.min(resources.getMinerCapacity(),resources.getMiner());
        int cost3 = Math.min(resources.rockCapacity,resources.rock);
        int cost5 = Math.min(resources.steelCapacity,resources.steel);
        if (resources.getRock() < 101) { cost3 = 0;};
        FT.disassemble(resources);
        Assert.assertEquals(resources.getRockCapacity(), initialValue3 - cost);
        Assert.assertEquals(resources.getMiner(),  initialValue4 - cost4);
        Assert.assertEquals(resources.getMinerCapacity(),  initialValue2 - cost2);
        Assert.assertEquals(resources.getRock(),  initialValue - cost3);
        Assert.assertEquals(resources.getSteel(),  initialValue5 - cost5);

    }
    
    
}
