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

import java.text.MessageFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import launcher.SimCityUI;
import localization.LocalizedTexts;
import model.GameBoard;

public class PropertiesView extends JPanel implements Observer {

    private static final long serialVersionUID = 1L;

    private JLabel currency;
    private JLabel energy;
    private JLabel unworkingPop;
    private JLabel workingPop;
//    private JLabel products;
    private JLabel wood;
    private JLabel food;
    private JLabel steel;
    private JLabel rock;
    private JLabel day;

    public PropertiesView(GameBoard w, LocalizedTexts texts) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new JLabel(texts.getCurrencyLabel()));
        this.currency = new JLabel(Integer.toString(w.getCurrency()));
        this.add(this.currency);

        this.add(new JLabel(texts.getWoodLabel()));
        this.wood = new JLabel(Integer.toString(w.getWood()));
        this.add(this.wood);
        
        this.add(new JLabel(texts.getRockLabel()));
        this.rock = new JLabel(Integer.toString(w.getRock()));
        this.add(this.rock);
        
        this.add(new JLabel(texts.getSteelLabel()));
        this.steel = new JLabel(Integer.toString(w.getSteel()));
        this.add(this.steel);

        this.add(new JLabel(texts.getUnconsumedEnergyLabel()));
        this.energy = new JLabel(Integer.toString(w.getEnergy()));
        this.add(this.energy);
        
 
        this.add(new JLabel(texts.getUnconsumedFoodLabel()));
        this.food = new JLabel(Integer.toString(w.getFood()));
        this.add(this.food);

        this.add(new JLabel(texts.getUnworkingPopulationLabel()));
        this.unworkingPop = new JLabel(Integer.toString(w.getUnworkingPopulation()));
        this.add(this.unworkingPop);

        this.add(new JLabel(texts.getWorkingPopulationLabel()));
        this.workingPop = new JLabel(Integer.toString(w.getWorkingPopulation()));
        this.add(this.workingPop);
        
        this.add(new JLabel(texts.getJourLabel()));
        this.day = new JLabel(Integer.toString(w.getJour()));
        this.add(this.day);
    }

    @Override
    public void update(Observable o, Object arg) {
        assert o instanceof GameBoard;
        GameBoard world = (GameBoard) o;

        this.currency.setText(MessageFormat.format(world.getTexts().getCurrencyMsg(), world.getCurrency()));
        this.wood.setText(MessageFormat.format(world.getTexts().getWoodMsg(), world.getWood()));
        this.rock.setText(MessageFormat.format(world.getTexts().getRockMsg(), world.getRock()));
        this.steel.setText(MessageFormat.format(world.getTexts().getSteelMsg(), world.getSteel()));
        this.energy.setText("" + world.getEnergy());
        this.food.setText("" + world.getFood());
        this.unworkingPop.setText("" + world.getUnworkingPopulation());
        this.workingPop.setText("" + world.getWorkingPopulation());        
        this.day.setText("" + world.getJour());        

    }

}
