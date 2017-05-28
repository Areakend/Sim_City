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

/**
 * French localized texts.
 */
public class FRTexts extends LocalizedTexts {

    // Messages
    @Override
    public String getToolCannotAffectMsg() {
        return "Vous ne pouvez pas affecter cette case";
    }

    @Override
    public String getCurrencyMsg() {
        return "{0} Ecus";
    }
    public String getWoodMsg() {
        return "{0} Bois";
    }
    public String getSteelMsg() {
        return "{0} Fer";
    }
    public String getRockMsg() {
        return "{0} Pierre";
    }
    
    public String getJourMsg() {
        return "{0} Pierre";
    }

    //@Override
    //public String getEarthQuakeMsg() {
       // return "Il y a eu un tremblemment de terre a la position [ {0} ]";
    //}

    @Override
    public String getMissingResourcesMsg() {
        return "Manque de ressources";
    }

    @Override
    public String getRoundMsg() {
        return "Round #{0} : {1}";
    }

    // Labels
    @Override
    public String getCurrencyLabel() {
        return "Ecus";
    }

    @Override
    public String getUnconsumedWaterLabel() {
        return "Eau non consommee";
    }

    @Override
    public String getWoodLabel() {
        return "Bois";
    }

    @Override
    public String getUnconsumedFoodLabel() {
        return "Nourriture restante";
    }

    @Override
    public String getSteelLabel() {
        return "Fer";
    }

    @Override
    public String getRockLabel() {
        return "Pierre";
    }

    @Override
    public String getStoredProductsLabel() {
        return "Produits stockes";
    }

    @Override
    public String getUnworkingPopulationLabel() {
        return "Chomeurs";
    }
    
    @Override
    public String getWorkingPopulationLabel() {
        return "Travailleurs";
    }
    
    @Override
    public String getFarmerLabel() {
        return "Fermiers";
    }
    
    @Override
    public String getMinerLabel() {
        return "Mineurs";
    }
    
    @Override
    public String getLumberjackLabel() {
        return "Bucherons";
    }
    
    @Override
    public String getKnightLabel() {
        return "Chevaliers";
    }
    
    @Override
    public String getJourLabel() {
        return "Jour";
    }

}
