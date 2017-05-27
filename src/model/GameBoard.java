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

package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import launcher.SimCityUI;
import localization.LocalizedTexts;
import model.difficulty.DifficultyLevel;
import model.event.Event;
import model.event.EventFactory;
import model.tiles.CastleaTile;
import model.tiles.CastlebTile;
import model.tiles.CastlecTile;
import model.tiles.CastledTile;
import model.tiles.Evolvable;
import model.tiles.FarmTile;
import model.tiles.ForestTile;
import model.tiles.GrassTile;
import model.tiles.MineTile;
import model.tiles.RiverTile;
import model.tiles.Tile;
import model.tools.BulldozerTool;
import model.tools.FarmerConstructionTool;
import model.tools.LumberjackConstructionTool;
import model.tools.MineConstructionTool;
// Add when implemented
//import model.tools.CommercialZoneDelimiterTool;
//import model.tools.IndustrialZoneDelimiterTool;
import model.tools.WellConstructionTool;
import model.tools.ResidentialZoneDelimiterTool;
import model.tools.BridgeConstructionTool;
import model.tools.Tool;

public class GameBoard extends Observable {

    // Constant
    public final static DifficultyLevel DEFAULT_DIFFICULTY = DifficultyLevel.STANDARD_LEVEL;

    public final static TilePosition DEFAULT_SELECTED_LOCATION = new TilePosition(0, 0);

    public final static int DEFAULT_SELECTED_TOOL = 0;

    public final static int MAX_HANDLED_EVOLUTIONS = 5;

    public static String NOTHING_MESSAGE = "";

    public final static AtomicInteger ROUNDCOUNTER = new AtomicInteger(0);

    // Implementation
    /**
     * Map of the world.
     */
    private final Tile[][] tiles;

    /**
     * Available tools.
     */
    public static List<Tool> tools;

    /**
     * {@link #getSelectedTool()}
     */
    private Tool selectedTool;

    /**
     * {@link #getSelectedTile()}
     */
    private Tile selectedTile;

    /**
     * Pending evolutions.
     */
    private final Queue<Evolvable> pendingEvolutions;

    /**
     * Events to be applied to the world at the next refresh.
     */
    private final List<Event> pendingEventsList;

    /**
     * Available money.
     */
    private CityResources resources;

    /**
     * Status message.
     */
    private String message;

    /**
     * {@link #getTexts()}
     */
    private final LocalizedTexts texts;
    
    /**
     * Status famine message.
     */

	private String messageFamine;

    // Creation
    /**
     * Create a rectangle world with {@value height * width} tiles.
     *
     * @param height
     *            - {@link #getHeight()}
     * @param width
     *            - {@link #getWidth()}
     * @param difficulty
     *            - Game difficulty level.
     * @param texts
     *            - {@link #getTexts()}
     */
    public GameBoard(int height, int width, DifficultyLevel difficulty, LocalizedTexts texts) {
        assert width > 0 && height > 0 : "Dimensions incorrectes";

        this.tiles = new Tile[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
            	if(i==14 & j==14) this.tiles[i][j] = CastleaTile.getDefault();
            	else if(i==14 & j==15) this.tiles[i][j] = CastlebTile.getDefault();
            	else if(i==15 & j==14) this.tiles[i][j] = CastlecTile.getDefault();
            	else if(i==15 & j==15) this.tiles[i][j] = CastledTile.getDefault();
            	else if(j>19 && j<22) this.tiles[i][j] = RiverTile.getDefault();
            	else
                this.tiles[i][j] = GrassTile.getDefault();
            }
        }
        this.selectedTile = this.getTile(GameBoard.DEFAULT_SELECTED_LOCATION.getRow(), GameBoard.DEFAULT_SELECTED_LOCATION.getColumn());

        tools = new ArrayList<>();
        tools.add(new BulldozerTool());
        tools.add(new BridgeConstructionTool());
        tools.add(new WellConstructionTool());
        tools.add(new ResidentialZoneDelimiterTool());
        tools.add(new FarmerConstructionTool());
        tools.add(new MineConstructionTool());
        tools.add(new LumberjackConstructionTool());

        // Add when implemented
//        this.tools.add(new IndustrialZoneDelimiterTool());
//        this.tools.add(new CommercialZoneDelimiterTool());

        this.selectedTool = tools.get(GameBoard.DEFAULT_SELECTED_TOOL);

        this.pendingEvolutions = new LinkedList<>();
        this.pendingEventsList = new LinkedList<>();
        this.resources = new CityResources(difficulty.getInitialCurrency());

        this.message = GameBoard.NOTHING_MESSAGE;
        this.texts = texts;
    }

    /**
     * Create a rectangle world with {@value height * width} tiles.
     *
     * @param height
     *            - {@link #getHeight()}
     * @param width
     *            - {@link #getWidth()}
     * @param texts
     *            - {@link #getTexts()}
     */
    public GameBoard(int height, int width, LocalizedTexts texts) {
        this(height, width, GameBoard.DEFAULT_DIFFICULTY, texts);
    }

    /**
     * Create a square world with {@value length * length} tiles.
     *
     * @param length
     *            - {@link #getWidth()} and {@link #getHeight()}
     * @param texts
     *            - {@link #getTexts()}
     */
    public GameBoard(int length, LocalizedTexts texts) {
        this(length, length, texts);
    }

    // Access
    /**
     * 
     * @return texts.
     */
    public LocalizedTexts getTexts() {
        return this.texts;
    }

    // Access (World)
    /**
     * @return Height of the world in row count.
     */
    public int getHeight() {
        return this.tiles.length;
    }

    /**
     * @return Width of the world in column count.
     */
    public int getWidth() {
        return this.tiles[0].length;
    }

    /**
     * @param row
     *            - Row number
     * @param column
     *            - Column number
     * @return Cell with at location ({@value row}, {@value column}).
     * @exception AssertionError
     *                if {@value row} or {@value column} is invalid.
     */
    public Tile getTile(int row, int column) {
        assert row >= 0 && row < this.getHeight() && column >= 0 && column < this.getWidth() : "Ligne/Colonne incorrecte";
        return this.tiles[row][column];
    }

    // Access (Tool)
    /**
     * @return Number of available tools.
     */
    public int getToolCount() {
        return tools.size();
    }

    /**
     * 
     * @return resources.
     */
    public CityResources getResources() {
		return resources;
	}

	/**
     * @return Tools' iterator of available tools.
     */
    public Iterator<Tool> toolIterator() {
        return tools.iterator();
    }

    // Access (Selection)
    /**
     * @return Selected tile.
     */
    public Tile getSelectedTile() {
        return this.selectedTile;
    }
    
    /**
     * @return Selected Tool.
     */
    public Tool getSelectedTool() {
        return this.selectedTool;
    }

    // Access (City Resources)
    
    /**
     * @return Currency amount.
     */
    public int getCurrency() {
        return this.resources.getCurrency();
    }
    
    /**
     * @return Wood amount.
     */
    public int getWood() {
        return this.resources.getWood();
    }
    
    /**
     * @return Rock amount.
     */
    public int getRock() {
        return this.resources.getRock();
    }
    
    /**
     * @return Steel amount.
     */
    public int getSteel() {
        return this.resources.getSteel();
    }
    
    /**
     * @return Farmer amount.
     */
    public int getFarmer() {
        return this.resources.getFarmer();
    }
    
    /**
     * @return Miner amount.
     */
    public int getMiner() {
        return this.resources.getMiner();
    }
    
    /**
     * @return Lumberjack amount.
     */
    public int getLumberjack() {
        return this.resources.getLumberjack();
    }
    
    /**
     * @return Knight amount.
     */
    public int getKnight() {
        return this.resources.getKnight();
    }
    
    /**
     * @return Unworking population amount.
     */
    public int getUnworkingPopulation() {
        return this.resources.getUnworkingPopulation();
    }
    
    /**
     * @return Working population amount.
     */
    public int getWorkingPopulation() {
        return this.resources.getWorkingPopulation();
    }
    
    /**
     * @return Water amount.
     */

    public int getWater() {
        return this.resources.getUnconsumedWater();
    }



    
    /**
     * @return Food amount.
     */
    public int getFood() {
        return this.resources.getFood();
    }
    
    /**
     * @return Products amount.
     */
    public int getProducts() {
        return this.resources.getProductsCount();
    }
    
    /**
     * @return Day number.
     */
    public int getJour() {
    	return SimCityUI.jour;
    }

    // Access (Status)
    /**
     * @return Status message.
     */
    public String getMessage() {
        return this.message;
    }
    
    /**
     * @return Status messageFamine.
     */
    
	public String getMessageFamine() {
		return this.messageFamine;
	}

    // Change (Selection)
    /**
     *
     * @param tool
     *            - {@link #getSelectedTool()}.
     */
    public void setSelectedTool(Tool tool) {
        this.selectedTool = tool;
        if (selectedTool== GameBoard.tools.get(0)) {
        	this.message="Buldozer, il permet de détruire une construction pour 10Ecus";
        }
        if (selectedTool== GameBoard.tools.get(1)) {
        	this.message="Pont coute 5 bois";
        }
        if (selectedTool== GameBoard.tools.get(2)) {
        	this.message="Puit : coûte x pierres permet de produire de l'eau à raison de X unité";
        }
        if (selectedTool== GameBoard.tools.get(3)) {
        	this.message="Maison : coûte 20 bois et permet d'acceuillir 10 habitants, ils mangent 2 rations par jour";
        }
        if (selectedTool== GameBoard.tools.get(4)) {
        	this.message="Ferme : coûte 40 bois et permet de produire 5 unités de nourriture par fermier";
        }
        if (selectedTool== GameBoard.tools.get(5)) {
        	this.message="Mine : coûte 40 pierre et permet de produire 5 fer par mineur";
        }
        if (selectedTool== GameBoard.tools.get(6)) {
        	this.message="Camp de bûcheron : coûte 40 écus et permet de produire 5 bois par bûcheron";
        }
        this.notifyViews();
        //
    }

    /**
     * Select the tile at location ({@value row}, {@value column}).
     *
     * @param row
     * @param column
     */
    public void setSelectedTile(int row, int column) {
        this.selectedTile = this.getTile(row, column);
        this.notifyViews();
    }

    /**
     * Return a set of TilePosition defining a square created from the given
     * <code>startingTile</code> and the <code>areaSize</code>.
     *
     * @param startingTile
     * @param areaSize
     * @return Set of TilePosition
     */
    public Set<TilePosition> getTilesArea(TilePosition startingTile, int areaSize) {
        Set<TilePosition> tilesArea = new HashSet<>();

        for (int i = 0; i < areaSize; i++) {
            for (int j = 0; j < areaSize; j++) {
                int newRow = startingTile.getRow() + i < this.getHeight() ? startingTile.getRow() + i : this.getHeight() - 1;
                int newColumn = startingTile.getColumn() + j < this.getWidth() ? startingTile.getColumn() + j : this.getWidth() - 1;
                TilePosition newTile = new TilePosition(newRow, newColumn);
                tilesArea.add(newTile);
            }
        }
        return tilesArea;
    }

    // Change (World)
    /**
     * Effect the tile at location {@value row}, {@value column}) with
     * {@link #getSelectedTool()} if possible.
     *
     * @param row
     * @param column
     */
    public void effectTile(int row, int column) {
        assert row >= 0 && row < this.getHeight() && column >= 0 && column < this.getWidth() : "Ligne/Colonne incorrecte";

        final Tile currentTile = this.tiles[row][column];

        if (this.selectedTool.canEffect(currentTile)) {
            if (this.selectedTool.isAfordable(currentTile, this.resources)) {

                final Tile newTile = this.selectedTool.effect(currentTile, this.resources);
                this.tiles[row][column] = newTile;

                this.pendingEvolutions.remove(currentTile);
                if (newTile instanceof Evolvable) {
                    this.pendingEvolutions.add((Evolvable) newTile);
                }
            } else {
                this.message = this.texts.getMissingResourcesMsg();
            }
        } else {
            this.message = this.texts.getToolCannotAffectMsg();
        }

        this.notifyViews();
    }

    /**
     * Compute the next world state.
     */
    public void nextState() {
        GameBoard.ROUNDCOUNTER.incrementAndGet();
        final int extraProductionF = Math.min(FarmTile.EXTRA_FOOD_PRODUCTION*resources.getFarmer(), resources.foodCapacity - resources.food);
        resources.creditF(extraProductionF);  //Met à jour la production de nourriture.
        
        final int extraProductionS = Math.min(MineTile.EXTRA_PRODUCTION*resources.getMiner(), resources.steelCapacity - resources.steel);
        resources.creditS(extraProductionS); //Met à jour la production de fer.
        
        final int extraProductionR = Math.min(MineTile.EXTRA_PRODUCTION*resources.getMiner(), resources.rockCapacity - resources.rock);
        resources.creditR(extraProductionR); //Met à jour la production de pierre.
       
        final int extraProductionW = Math.min(ForestTile.EXTRA_WOOD_PRODUCTION*resources.getLumberjack(), resources.woodCapacity - resources.wood);
        resources.creditW(extraProductionW); //Met à jour la production de bois.
        
        this.dailyConsumed();
        this.applyPendingEvents();
        this.applyNewEvent();
        this.updateTiles();
        this.applyEvolutions();
        this.notifyViews();
        SimCityUI.jour +=1;
    }
    
    /**
     * Update resources.
     */
    public void dailyConsumed() {
        //this.unworkingPopulation = this.population;
    	this.resources.unconsumedWater=this.resources.waterProduction;
    	if (this.resources.getPopulation()*2 > this.resources.getFood()){
    		int n = this.resources.getPopulation()*2 - this.resources.getFood();
    		this.resources.decreasePopulation(Math.round(n/2));
            this.messageFamine = "Nourriture insuffisante. Votre population meurt de faim";
            
    	}else{
    		this.message = null;
    	}
        this.resources.spendF(Math.min(this.resources.getPopulation()*2, this.resources.getFood()));
        this.resources.currency += this.resources.getWorkingPopulation()*this.resources.getVat();
    }

    /**
     * Applies the effects of all the pending events (resulting from the
     * previous one).
     */
    private void applyPendingEvents() {
        List<Event> entry;
        for (Event event : this.pendingEventsList) {
            entry = event.applyEffects(this);
            this.pendingEventsList.addAll(entry);
        }
    }

    /**
     * Generates a new event and applies its effects.
     */
    private void applyNewEvent() {
        Event event = EventFactory.generateEvent(this);
        List<Event> resultingEvents = event.applyEffects(this);
        assert resultingEvents != null;
        String eventMessage = event.getMessage(this.texts);
        assert eventMessage != null : "The event message must not be null.";
        this.message = eventMessage.isEmpty() ? GameBoard.NOTHING_MESSAGE : eventMessage;
        this.pendingEventsList.addAll(resultingEvents);
    }

    // Implementation (Notification)
    /**
     * Notify view of a state change.
     */
    private void notifyViews() {
        this.setChanged();
        this.notifyObservers();
        this.message = GameBoard.NOTHING_MESSAGE;
    }

    /**
     * Apply evolutions in the order where it was registered.
     */
    private void applyEvolutions() {
        final int count = Math.min(GameBoard.MAX_HANDLED_EVOLUTIONS, this.pendingEvolutions.size());

        for (int i = 0; i < count; i++) {
            final Evolvable e = this.pendingEvolutions.poll(); // Not null

            e.evolve(this.resources);

            if (e.canEvolve()) {
                this.pendingEvolutions.add(e);
            }
        }
    }
    public void resetTile(int i, int j){
    	final Tile t = GrassTile.getDefault();
    	this.tiles[i][j] = t;
    }
    /**
     * Update all tiles via {@link Tile#update(CityResources)}.
     */
    private void updateTiles() {
        for (final Tile[] rows : this.tiles) {
            for (final Tile t : rows) {            	
                t.update(this.resources);
            }
        }
    }



}