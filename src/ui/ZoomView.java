package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ZoomView extends JPanel {

    // Constant
    private static final long serialVersionUID = 1L;
    
    JButton up, down, left, right;

    // Creation
    public ZoomView(GameBoardView w) {
        super();
        this.setLayout(new BorderLayout());
        up = new JButton("^");
        up.setEnabled(false);
        up.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!w.moveZoomUp()) up.setEnabled(false);
                if(!down.isEnabled()) down.setEnabled(true);
            }
        });
        
        up.addMouseListener(new MouseListener(){
        	Timer timer;
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				timer = new Timer(200, new ActionListener() {
		    	    @Override
		    	    public void actionPerformed(ActionEvent evt) {
		    	    	if(up.isEnabled()) {
		    	    	if(!w.moveZoomUp()) up.setEnabled(false);
		                if(!down.isEnabled()) down.setEnabled(true);
		    	    	}
		    	    }
		    	});
				timer.start();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				timer.stop();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        });
        this.add(up,BorderLayout.NORTH);
        
        left = new JButton("<");
        left.setEnabled(false);
        left.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!w.moveZoomLeft()) left.setEnabled(false);
                if(!right.isEnabled()) right.setEnabled(true);
            }
        });
        left.addMouseListener(new MouseListener(){
        	Timer timer;
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				timer = new Timer(200, new ActionListener() {
		    	    @Override
		    	    public void actionPerformed(ActionEvent evt) {
		    	    	if(left.isEnabled()) {
		                    if(!w.moveZoomLeft()) left.setEnabled(false);
		                    if(!right.isEnabled()) right.setEnabled(true);
		    	    	}
		    	    }
		    	});
				timer.start();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				timer.stop();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        });
        this.add(left,BorderLayout.WEST);
        
        down = new JButton("v");
        down.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!w.moveZoomDown()) down.setEnabled(false);
                if(!up.isEnabled()) up.setEnabled(true);
            }
        });
        down.addMouseListener(new MouseListener(){
        	Timer timer;
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				timer = new Timer(200, new ActionListener() {
		    	    @Override
		    	    public void actionPerformed(ActionEvent evt) {
		    	    	if(down.isEnabled()) {
		    	    		if(!w.moveZoomDown()) down.setEnabled(false);
		                    if(!up.isEnabled()) up.setEnabled(true);
		    	    	}
		    	    }
		    	});
				timer.start();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				timer.stop();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        });
        this.add(down,BorderLayout.SOUTH);
        
        right = new JButton(">");
        right.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!w.moveZoomRight()) right.setEnabled(false);
                if(!left.isEnabled()) left.setEnabled(true);
            }
        });
        right.addMouseListener(new MouseListener(){
        	Timer timer;
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				timer = new Timer(200, new ActionListener() {
		    	    @Override
		    	    public void actionPerformed(ActionEvent evt) {
		    	    	if(right.isEnabled()) {
		    	    		if(!w.moveZoomRight()) right.setEnabled(false);
		                    if(!left.isEnabled()) left.setEnabled(true);
		    	    	}
		    	    }
		    	});
				timer.start();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				timer.stop();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        });
        this.add(right,BorderLayout.EAST);
        
        this.add(w,BorderLayout.CENTER);
    }

}
