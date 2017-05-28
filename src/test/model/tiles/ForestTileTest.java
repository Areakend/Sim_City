package test.model.tiles;
import org.junit.Assert;
import org.junit.Test;

import localization.FRTexts;
import localization.LocalizedTexts;
import model.CityResources;
import model.GameBoard;
import model.tiles.ForestTile;
import model.tiles.GrassTile;
import model.tiles.ResidentialTile;
import model.tiles.Tile;
import model.tiles.WellTile;
import model.tools.LumberjackConstructionTool;


public class ForestTileTest {

    @Test
    public void testupdate() {
    	LocalizedTexts text = new FRTexts();
        GameBoard gb = new GameBoard(10,text);
        CityResources resources = new CityResources(100);
        WellTile WT = new WellTile();
        WT.update(resources);
        ResidentialTile ppt = new ResidentialTile();
        LumberjackConstructionTool FC = new LumberjackConstructionTool();
        ForestTile FT = new ForestTile();
        FC.innerEffect(GrassTile.getDefault(), resources);
        ppt.evolve(resources);
        ppt.update(resources);
        int initialValue2 = resources.getLumberjack();
        FT.update(resources);;
        int cost2 =1;
        Assert.assertEquals(resources.getLumberjack(), initialValue2 + cost2 );

    }

    @Test
    public void testDisassemble() {
    	LocalizedTexts text = new FRTexts();
        GameBoard gb = new GameBoard(10,text);
    	CityResources resources = new CityResources(100);
        WellTile WT = new WellTile();
        WT.update(resources);
        ResidentialTile ppt = new ResidentialTile();
        LumberjackConstructionTool FC = new LumberjackConstructionTool();
        ForestTile FT = new ForestTile();
        FC.innerEffect(GrassTile.getDefault(), resources);
        ppt.update(resources);
        ppt.evolve(resources);
        FT.update(resources);
        int initialValue = resources.getWood();
        int initialValue2 = resources.getLumberjackCapacity();
        int initialValue3 = resources.getWoodCapacity();  
        int initialValue4 = resources.getLumberjack();
        int cost = FT.productionCapacity;
        int cost2 = FT.lumberjackCapacity;
        int cost4 = Math.min(resources.getLumberjackCapacity(),resources.getLumberjack());
        int cost3 = Math.min(resources.woodCapacity,resources.wood);
        if (resources.getWood() < 101) { cost3 = 0;};
        FT.disassemble(resources);
        Assert.assertEquals(resources.getWoodCapacity(), initialValue3 - cost);
        Assert.assertEquals(resources.getLumberjack(),  initialValue4 - cost4);
        Assert.assertEquals(resources.getLumberjackCapacity(),  initialValue2 - cost2);
        Assert.assertEquals(resources.getWood(),  initialValue - cost3);

    }
    
    
}
