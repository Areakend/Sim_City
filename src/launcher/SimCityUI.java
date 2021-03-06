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

package launcher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import localization.LocalizedTexts;
import localization.FRTexts;
import model.GameBoard;
import ui.ToolsView;
import ui.ZoomView;
import ui.MessagesView;
import ui.PropertiesView;
import ui.RefreshView;
import ui.GameBoardView;

public final class SimCityUI extends JFrame {

    // Constants
    private final static long serialVersionUID = 1L;

    private final static int DEFAULT_HEIGHT = 40;
    
    private final static int DEFAULT_WIDTH = 40;
    
    public static int jour = 0;
    
    // Entry point
    /**
     * Run without arguments or with two arguments:
     *
     * First argument: height Second argument: width
     *
     * @param args
     */
    public static void main(String[] args) {
    	
    	
    	JFrame menu = new JFrame("Menu");
    	JPanel optionsPane = new JPanel(new GridLayout(3, 1));
        JButton mp = new JButton("Nouvelle partie");

        mp.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            SimCityUI.startGame(args, null);
            menu.setVisible(false);
            }
        });
	    optionsPane.add(mp);
	    JButton yt = new JButton("Continuer");
	    yt.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	          SimCityUI.startGame(args,GameBoard.loadGame("SimCity.save"));
	          }
	    	});
		optionsPane.add(yt);
		JButton tw = new JButton("Quitter");
		tw.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	            System.exit(0);
	            }
	        });
		optionsPane.add(tw);
		menu.add(optionsPane);
	    menu.setSize(new Dimension(200,200));
	    menu.setPreferredSize(new Dimension(300,200)); 
    	menu.setTitle("Menu");
    	menu.setDefaultCloseOperation(EXIT_ON_CLOSE);    
    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        menu.setLocation(dim.width/2-menu.getSize().width/2, dim.height/2-menu.getSize().height/2);
    	menu.setVisible(true);
    	menu.pack();
    }
    //}

    // Creation
    
    public static void startGame(String[] args, GameBoard gb) {
    	final int height;
        final int width;

        if (args.length == 2) {
            final Scanner hSc = new Scanner(args[0]);
            final Scanner wSc = new Scanner(args[1]);

            if (hSc.hasNextInt()) { // if it is an integer
                height = hSc.nextInt();
            } else {
                height = SimCityUI.DEFAULT_HEIGHT;
                System.err.println("pasing: First argument must be an integer");
            }

            if (wSc.hasNextInt()) { // if it is an integer
                width = wSc.nextInt();
            } else {
                width = SimCityUI.DEFAULT_WIDTH;
                System.err.println("pasing: Second argument must be an integer");
            }

            hSc.close();
            wSc.close();
        } else {
            height = SimCityUI.DEFAULT_HEIGHT;
            width = SimCityUI.DEFAULT_WIDTH;

            if (args.length != 0) {
                System.err.println("pasing: Wrong number of arguments");
            }
        }
        
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int tmp = (int)dimension.getHeight();
        tmp /= 48;
        tmp -=6;
        final int case_screenH = tmp;
        tmp  = (int)dimension.getWidth();
        tmp /= 48;
        tmp -=7;
        final int case_screenW = tmp;
        
        /*System.err.println(case_screenW+" "+case_screenH);
        playSound("ost.mp3");
        System.out.println(new java.io.File("").getAbsolutePath());
        System.out.println(SimCityUI.class.getClassLoader().getResource("").getPath());
        System.out.println(SimCityUI.class.getResourceAsStream("ost.mp3"));
        */
        // Pour que ce soit le thread graphique qui construise les composants
        // graphiques
        SwingUtilities.invokeLater(() -> new SimCityUI(height, width, case_screenH, case_screenW,gb));
    	
    }
    
    /**
     * Play a sound given
     *
     * 
     * @param file
     */
    
    public static synchronized void playSound(final String file) {
    	  new Thread(new Runnable() {
    	    public void run() {
    	      try {
    	        Clip clip = AudioSystem.getClip();
    	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
    	        SimCityUI.class.getResourceAsStream("/src/resources/musics/"+file));
    	        clip.open(inputStream);
    	        clip.start(); 
    	      } catch (Exception e) {
    	    	  e.printStackTrace();
    	      }
    	    }
    	  }).start();
    	}
    
    public SimCityUI(int hauteur, int largeur, int vhauteur, int vlargeur, GameBoard gb) {
        super("TNCity");

        //this.setPreferredSize(new Dimension(DEFAULT_WIDTH*49+100+200,DEFAULT_HEIGHT*49+200));
        
        // Choix de la langue
        final LocalizedTexts texts = new FRTexts();

        // Creation du monde
        GameBoard monde;
        if(gb !=null) monde = gb;
        else
        monde = new GameBoard(hauteur, largeur, texts);

        
        // Creation de la vue du monde, placee au centre de la fenetre
        GameBoardView vm = new GameBoardView(monde, vhauteur, vlargeur);
        vm.setPreferredSize(new Dimension(48*vlargeur,48*vhauteur));
        monde.addObserver(vm);
        
        ZoomView zv = new ZoomView(vm);
        this.add(zv, BorderLayout.CENTER);

        // Creation de la palette des elements de jeu, placee a gauche
        ToolsView ve = new ToolsView(monde);
        monde.addObserver(ve);
        this.add(ve, BorderLayout.WEST);

        // Creation du panneau d'informations
        PropertiesView vi = new PropertiesView(monde, texts);
        monde.addObserver(vi);

        // Creation du panneau de rafraichissement
        RefreshView rv = new RefreshView(monde);
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.add(vi);
        right.add(Box.createVerticalGlue());
        right.add(rv);
        
        this.add(right, BorderLayout.EAST);
        
        // Creation du panneau de message
        MessagesView mv = new MessagesView();
        monde.addObserver(mv);
        this.add(mv, BorderLayout.SOUTH);
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.setResizable(false);
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
    }

}
