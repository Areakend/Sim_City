/**
 * TNCity
 * Copyright (c) 2017
 *  Jean-Philippe Eisenbarth,
 *  Victorien Elvinger
 *  Martine Gautier,
 *  Quentin Laporte-Chabasse
 *
 *  This file is part of TNCity.
 *
 *  TNCity is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  TNCity is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with TNCity.  If not, see <http://www.gnu.org/licenses/>.
 */

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
 * The NothingEvent does nothing.
 */
public class EarthquakeEvent extends Event {

    /**
     * Default Constructor.
     */
	public EarthquakeEvent() {
        super();
    }

    /**
     * Apply no effects.
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
