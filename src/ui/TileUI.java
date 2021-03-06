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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.GameBoard;
import model.tiles.Tile;
import model.tools.Tool;

public class TileUI extends JLabel {

    private static final long serialVersionUID = 1L;

    private int row;

    private int column;

    public static GameBoard model;

    public TileUI(GameBoard m, final int row, final int column) {
        super(" ");
        TileUI.model = m;
        this.row = row;
        this.column = column;
        this.setBorder(null);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TileUI.model.effectTile(row, column);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                TileUI.model.setSelectedTile(row, column);
            }
        });

        this.update();

    }
    // Rafraîchissement du composant
    public void update() {
        final Tile elt = TileUI.model.getTile(this.row, this.column);
        final Tool selectedTool = TileUI.model.getSelectedTool();
        if (selectedTool !=null && selectedTool.canEffect(elt)) {
            final int cost = selectedTool.getCost(elt);
            if (selectedTool== GameBoard.tools.get(3)) {
                this.setToolTipText(MessageFormat.format(TileUI.model.getTexts().getWoodMsg(), cost));
            }
            if (selectedTool== GameBoard.tools.get(2)) {
                this.setToolTipText(MessageFormat.format(TileUI.model.getTexts().getCurrencyMsg(), cost));
            }
            if (selectedTool== GameBoard.tools.get(4)) {
                this.setToolTipText(MessageFormat.format(TileUI.model.getTexts().getWoodMsg(), cost));
            }
            if (selectedTool== GameBoard.tools.get(5)) {
                this.setToolTipText(MessageFormat.format(TileUI.model.getTexts().getRockMsg(), cost));
            }
            if (selectedTool== GameBoard.tools.get(6)) {
                this.setToolTipText(MessageFormat.format(TileUI.model.getTexts().getCurrencyMsg(), cost));
            }
            if (selectedTool== GameBoard.tools.get(0)) {
                this.setToolTipText(MessageFormat.format(TileUI.model.getTexts().getCurrencyMsg(), cost));
            }
            if (selectedTool== GameBoard.tools.get(1)) {
                this.setToolTipText(MessageFormat.format(TileUI.model.getTexts().getCurrencyMsg(), cost));
            }
            
        } else {
            this.setToolTipText(TileUI.model.getTexts().getToolCannotAffectMsg());
        }

        ImageIcon ii = IconFactory.getInstance().getTileIcon(elt);
        this.setIcon(ii);
    }

}
