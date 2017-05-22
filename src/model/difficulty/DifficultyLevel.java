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

package model.difficulty;

/**
 * Represents the difficulty parameters of the game.
 */
public class DifficultyLevel {

    // Constant (Standard level)
    /**
     * Initial currency of the standard difficulty.
     */
    private final static int STANDARD_CURRENCY = 20000;

    /**
     * Standard difficulty.
     */
    public final static DifficultyLevel STANDARD_LEVEL = new DifficultyLevel(DifficultyLevel.STANDARD_CURRENCY);

    // Constant
    /**
     * 1: {@link DifficultyLevel#getInitialCurrency()}
     */
    public final static String TO_STRING_TEMPLATE = "Difficulty {%s ¤}";

    // Implementation
    /**
     * {@link #getInitialCurrency()}
     */
    private final int initialCurrency;
    
    /**
     * {@link #getInitialWood()}
     */
    private final int initialWood;
    
    /**
     * {@link #getInitialFood()}
     */
    private final int initialFood;
    
    /**
     * {@link #getInitialSteel()}
     */
    private final int initialSteel;
    
    /**
     * {@link #getInitialRock()}
     */
    private final int initialRock;

    // Creation
    /**
     *
     * @param aCurrency
     *            - {@link #getInitialCurrency()}
     * @param aBuildozerToolCost
     *            - {@link #getBuildozerCost()}
     * @param aZoneDelimiterCost
     *            - {@link #getZoneDelimiterCost()}
     */
    public DifficultyLevel(int aCurrency) {
        this.initialCurrency = aCurrency;
        this.initialWood =aCurrency/2;
        this.initialFood =aCurrency/2;
        this.initialSteel =aCurrency/2;
        this.initialRock =aCurrency/2;
    }

    // Access
    /**
     * @return Currency at startup time.
     */
    public int getInitialCurrency() {
        return this.initialCurrency;
    }
    
    /**
     * @return Wood at startup time.
     */
    public int getInitialWood() {
        return this.initialWood;
    }
    
    /**
     * @return Food at startup time.
     */
    public int getInitialFood() {
        return this.initialFood;
    }
    
    /**
     * @return Steel at startup time.
     */
    public int getInitialSteel() {
        return this.initialSteel;
    }
    
    /**
     * @return Rock at startup time.
     */
    public int getInitialRock() {
        return this.initialRock;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 17 + this.initialCurrency;
        return result;
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return o instanceof DifficultyLevel && this.equals((DifficultyLevel) o);
    }

    /**
     * @param o
     * @return Is {@value o} equals to this?
     */
    public boolean equals(DifficultyLevel o) {
        return this == o || o.initialCurrency == this.initialCurrency;
    }

    // Debugging
    @Override
    public String toString() {
        return String.format(DifficultyLevel.TO_STRING_TEMPLATE, this.initialCurrency);
    }

}
