package model.event;
import java.util.ArrayList;
import java.util.List;
import localization.LocalizedTexts;
import model.GameBoard;
import model.tiles.FarmTile;
import model.tiles.ForestTile;
import model.tiles.MineTile;
import model.tiles.ResidentialTile;
import model.tiles.Tile;
import model.tiles.WellTile;


/**
 * The EarthquakeEvent does damages to the city. Indeed it can destroy tiles.
 */
public class EarthquakeEvent extends Event {

    /**
     * Default Constructor.
     */
	public EarthquakeEvent() {
        super();
    }
	

    /**
     * Destroys a tile with a probability of 10%.
     */
	@Override
    public List<Event> applyEffects(GameBoard gameBoard) {
		int h = gameBoard.getHeight();
		int w = gameBoard.getWidth();
		for (int i=0 ; i<h ; i++){
			for (int j=0 ; j<w ; j++){
				Tile tmp = gameBoard.getTile(i,j);
				if (tmp instanceof ResidentialTile || tmp instanceof FarmTile ||
						tmp instanceof ForestTile || tmp instanceof MineTile 
						|| tmp instanceof WellTile){
					if (Math.random()<0.1){
						tmp.disassemble(gameBoard.getResources());
						gameBoard.resetTile(i,j);
					}
				}
			}
		}
        return new ArrayList<>(0);
    }

    /**
     * Return an empty message.
     */
	@Override
    public String getMessage(LocalizedTexts texts) {
        return "Tremblement de terre ! Votre ville a reçu des dommages.";
    }

}
