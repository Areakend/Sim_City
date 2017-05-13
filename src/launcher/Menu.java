package launcher;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JFrame{
	public void initMenu() {
		JPanel optionsPane = new JPanel(new GridLayout(3, 1));
        JPanel pane = null;
	      // IP address input
	      pane = new JPanel();
	      JButton mp = new JButton("Jouer");
	      mp.addActionListener(new ActionAdapter());
	      mp.setActionCommand("jouer");
	      pane = new JPanel(new GridLayout(1, 1));
	      pane.add(mp);
	      optionsPane.add(pane);
	      pane = new JPanel();
	      JButton yt = new JButton("Options");
	      yt.addActionListener(new ActionAdapter());
	      yt.setActionCommand("option");
	      pane = new JPanel(new GridLayout(1, 1));
	      pane.add(yt);
	      optionsPane.add(pane);
	      pane = new JPanel((LayoutManager) new FlowLayout(FlowLayout.RIGHT));
	      JButton tw = new JButton("Quitter");
	      tw.addActionListener(new ActionAdapter());
	      tw.setActionCommand("quitter");
	      pane = new JPanel(new GridLayout(1, 1));
	      pane.add(tw);
	      optionsPane.add(pane);
	      add(optionsPane);
	}
}
class ActionAdapter implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("jouer")){
			//JOptionPane.showMessageDialog(null, "Lancement");
			SimCityUI.State = 1;
		} else if(e.getActionCommand().equals("option")){
			//JOptionPane.showMessageDialog(null, "Ouverture des parametres");
		}else if(e.getActionCommand().equals("quitter")){	
			//JOptionPane.showMessageDialog(null, "Fermeture du jeu");
			SimCityUI.State = 3;
		}
	}
}

