import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

/**
* <h1>Game of the Generals - Marvel Edition</h1>
* The Marvel edition of Game of the Generals in Java.
* <p>
* For more information visit https://ibattlehd.github.io/FinalCPT/
* @author  ibattlehd (Nick), kennethtse14 (Kenneth), jkaz2001 (John)
* @version 1.0
*/
public class MarvelGameOfTheGenerals implements ActionListener, KeyListener, MouseListener{
	// Properties
	JFrame theframe;
	MarvelGOTGPanel thepanel;
	Timer thetimer;
	
	
	// Methods
	public void actionPerformed(ActionEvent evt){ // Action listener
		if(evt.getSource() == this.thetimer){
			this.thepanel.repaint(); // Repaint the panel based on timer (60 fps).
		}	
	}
	
	public void keyTyped(KeyEvent evt){ // Called when a key is typed.
		
	}
	
	public void keyPressed(KeyEvent evt){ // Called when a key is pressed.
		
	}
	
	public void keyReleased(KeyEvent evt){ // Called when a key is released.
		
	}
	
	public void mouseClicked(MouseEvent evt){ // Called after the user clicks the listened-to component.
		
	}
	
	public void mouseEntered(MouseEvent evt){ // Called after the cursor enters the bounds of the listened-to component.
		
	}
	
	public void mouseExited(MouseEvent evt){ // Called after the cursor exits the bounds of the listened-to component.
	
	}
	
	public void mousePressed(MouseEvent evt){ // Called after the user presses a mouse button while the cursor is over the listened-to component.
	
	}
	
	public void mouseReleased(MouseEvent evt){ // Called after the user releases a mouse button after a mouse press over the listened-to component.
	
	}
	
	
	// Constructor
	public MarvelGameOfTheGenerals(){
		
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
