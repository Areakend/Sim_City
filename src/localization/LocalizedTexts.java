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

package localization;

import java.io.Serializable;

/***
 * Texts used by the game.
 */
public abstract class LocalizedTexts implements Serializable {

    // Message
    /**
     * @return Message that indicates that the tool cannot affect a given tile.
     */
    public abstract String getToolCannotAffectMsg();

    /**
     * {0}: currency
     *
     * @return Currency message
     */
    public abstract String getCurrencyMsg();
    
    /**
     * {0}: wood
     *
     * @return Wood message
     */
    public abstract String getWoodMsg();
    
    /**
     * {0}: rock
     *
     * @return Rock message
     */
    public abstract String getRockMsg();
    
    /**
     * {0}: steel
     *
     * @return Steel message
     */
    public abstract String getSteelMsg();


    //public abstract String getEarthQuakeMsg();

    /**
     * @return Message that indicates that some resources are missing to
     *         complete a task.
     */
    public abstract String getMissingResourcesMsg();

    /**
     * {0}: round number {1}: action/event message
     *
     * @return Message that indicates an action/event and the attached round.
     */
    public abstract String getRoundMsg();

    // Labels
    /**
     * @return Currency label.
     */
    public abstract String getCurrencyLabel();
    /**
     * @return Wood label.
     */
    public abstract String getWoodLabel();
    /**
     * @return Steel label.
     */
    public abstract String getSteelLabel();
    /**
     * @return Rock label.
     */
    public abstract String getRockLabel();

    /**
     * @return No-consumed water label.
     */

    public abstract String getUnconsumedWaterLabel();

    /**
     * @return Consumed water label.
     */
    public abstract String getUnconsumedFoodLabel();


    /**
     * @return Stored products label.
     */
    public abstract String getStoredProductsLabel();

    /**
     * @return No-working population label.
     */
    public abstract String getUnworkingPopulationLabel();
    /**
     * @return Working population label.
     */

	public abstract String getWorkingPopulationLabel();
    /**
     * @return Day label.
     */
	
	public abstract String getJourLabel();
    /**
     * @return Farmer label.
     */

	public abstract String getFarmerLabel();
    /**
     * @return No-working population label.
     */

	public abstract String getMinerLabel();
    /**
     * @return Lumberjack label.
     */
	public abstract String getLumberjackLabel();
    /**
     * @return Knight label.
     */

	public abstract String getKnightLabel();


}
