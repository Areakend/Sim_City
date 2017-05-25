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

package ui;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import model.GameBoard;

public class GameBoardView extends JPanel implements Observer {

    private static final long serialVersionUID = 1L;

    private TileUI[][] tiles;
    
    private int hzoom, wzoom, xzoom, yzoom, worldH, worldW;

    /**
     * Constructor.
     */
    public GameBoardView(GameBoard w, int vheigth, int vwidth) {
        super();
        this.hzoom=vheigth;
        this.wzoom=vwidth;
        this.xzoom=0;
        this.yzoom=0;
        this.worldH=w.getHeight();
        this.worldW=w.getWidth();
        GridLayout gl = new GridLayout(hzoom, wzoom);
        this.setLayout(gl);
        this.tiles = new TileUI[w.getHeight()][w.getWidth()];
        for (int i = 0; i < w.getHeight(); i++) {
            for (int j = 0; j < w.getWidth(); j++) {
                TileUI cc = new TileUI(w, i, j);
                this.tiles[i][j] = cc;
                if(i < hzoom && j < wzoom)
                this.add(cc);
            }
        }
    }

    /**
     * To update the GameBoardView, we update all tiles.
     */
    @Override
    public void update(Observable o, Object arg) {
        assert o instanceof GameBoard;
        //GameBoard world = (GameBoard) o;
        for (int i = xzoom; i < hzoom+xzoom; i++) {
            for (int j = yzoom; j < wzoom+yzoom; j++) {
                this.tiles[i][j].update();
            }
        }
    }

    /**
     * Move the sreen (up).
     */
    public boolean moveZoomUp() {
    	//True si je peux encore avancer
    	assert(xzoom>0);
    	xzoom--;
    	resetLayout();
    	return xzoom>0;
    }

    /**
     * Move the sreen (left).
     */  
    public boolean moveZoomLeft() {
    	//True si je peux encore avancer
    	assert(yzoom>0);
    	yzoom--;
    	resetLayout();
    	return yzoom>0;
    }

    /**
     * Move the sreen (down).
     */
    public boolean moveZoomDown() {
    	//True si je peux encore avancer
    	assert(xzoom+hzoom<worldH);
    	xzoom++;
    	resetLayout();
    	return xzoom+hzoom<worldH;
    }

    /**
     * Move the sreen (right).
     */
    public boolean moveZoomRight() {
    	//True si je peux encore avancer
    	assert(yzoom+wzoom<worldW);
    	yzoom++;
    	resetLayout();
    	return yzoom+wzoom<worldW;
    }

    /**
     * Reset the layout.
     */
    public void resetLayout() {
    	this.removeAll();
    	GridLayout gl = new GridLayout(hzoom, wzoom);
        this.setLayout(gl);
        for (int i = xzoom; i < hzoom+xzoom; i++) {
            for (int j = yzoom; j < wzoom+yzoom; j++) {
                this.add(this.tiles[i][j]);
                this.tiles[i][j].update();
            }
        }
        this.validate();
    }
}
