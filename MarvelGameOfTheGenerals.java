import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MarvelGameOfTheGenerals implements ActionListener{
	// Properties
	JFrame theframe;
	MarvelGOTGPanel thepanel;
	Timer thetimer;
	
	
	
	// Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			this.thepanel.repaint();
		}	
	}
	
	
	
	// Constructor
	public MarvelGameOfTheGenerals(){
		
		////////////////////////////////////////////////////////////////
		
		this.thepanel = new MarvelGOTGPanel();
		this.thepanel.setLayout(null);
		this.thepanel.setPreferredSize(new Dimension(1280, 720));
		
		////////////////////////////////////////////////////////////////
		
		this.thetimer = new Timer(1000/60, this);
		this.thetimer.start();
		
		////////////////////////////////////////////////////////////////
		
		this.theframe = new JFrame("Game Of The Generals Marvel Edition");
		// Puts the panel inside the frame.
		this.theframe.setContentPane(thepanel);
		
		// Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
		this.theframe.pack();
		
		// Exit Java program when the frame is closed.
		this.theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Disables window resizing.
		this.theframe.setResizable(false);
		
		// Shows the frame.
		this.theframe.setVisible(true);
		
		////////////////////////////////////////////////////////////////
		
	}
	
	
	
	// Main Method
	public static void main(String[] args){
		new MarvelGameOfTheGenerals();
	}
	
	
	
}
