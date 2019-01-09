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
	JFrame frame;
	MarvelGOTGPanel panel;
	Timer timer;
	JButton buttonPlay;
	JButton buttonScores;
	JButton buttonHelp;
	JButton buttonQuit;
	
	
	// Methods
	public void actionPerformed(ActionEvent evt){ // Action listener
		if(evt.getSource() == this.timer){
			this.panel.repaint(); // Repaint the panel based on timer (60 fps).
		}
		else if(evt.getSource() == this.buttonPlay){
			System.out.println("Play");
		}
		else if(evt.getSource() == this.buttonScores){
			System.out.println("High scores");
		}
		else if(evt.getSource() == this.buttonHelp){
			System.out.println("Help");
		}
		else if(evt.getSource() == this.buttonQuit){
			System.out.println("Quit");
			System.exit(0);
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
		
		this.panel = new MarvelGOTGPanel();
		this.panel.setLayout(null);
		this.panel.setPreferredSize(new Dimension(1280, 720));
		
		////////////////////////////////////////////////////////////////
		
		this.timer = new Timer(1000/60, this);
		this.timer.start();
		
		////////////////////////////////////////////////////////////////
		// JButtons
		this.buttonPlay = new JButton("Play"); // Play button
		this.buttonPlay.setSize(200, 50); // 200 pixels by 50 pixels
		this.buttonPlay.setLocation(540, 230); // x and y coordinates (540, 230)
		this.buttonPlay.setFocusPainted(false); // Remove focus ring
		this.buttonPlay.setBackground(Color.RED);
		this.buttonPlay.setFont(new Font("Arial", Font.PLAIN, 30)); // Arial text font size 30
		this.buttonPlay.setForeground(Color.WHITE); // Make text white
		this.buttonPlay.addActionListener(this); // Add action listener to play button
		this.panel.add(this.buttonPlay); // Add play button to the panel
		
		this.buttonScores = new JButton("High Scores"); // High scores button
		this.buttonScores.setSize(200, 50); // 200 pixels by 50 pixels
		this.buttonScores.setLocation(540, 300); // x and y coordinates (540, 300)
		this.buttonScores.setFocusPainted(false); // Remove focus ring
		this.buttonScores.setBackground(Color.RED);
		this.buttonScores.setFont(new Font("Arial", Font.PLAIN, 30)); // Arial text font size 30
		this.buttonScores.setForeground(Color.WHITE); // Make text white
		this.buttonScores.addActionListener(this); // Add action listener to high scores button
		this.panel.add(this.buttonScores); // Add high scores button to the panel
		
		this.buttonHelp = new JButton("Help"); // Help button
		this.buttonHelp.setSize(200, 50); // 200 pixels by 50 pixels
		this.buttonHelp.setLocation(540, 370); // x and y coordinates (540, 370)
		this.buttonHelp.setFocusPainted(false); // Remove focus ring
		this.buttonHelp.setBackground(Color.RED);
		this.buttonHelp.setFont(new Font("Arial", Font.PLAIN, 30)); // Arial text font size 30
		this.buttonHelp.setForeground(Color.WHITE); // Make text white
		this.buttonHelp.addActionListener(this); // Add action listener to help button
		this.panel.add(this.buttonHelp); // Add help button to the panel
		
		this.buttonQuit = new JButton("Quit"); // Quit button
		this.buttonQuit.setSize(200, 50); // 200 pixels by 50 pixels
		this.buttonQuit.setLocation(540, 440); // x and y coordinates (540, 440)
		this.buttonQuit.setFocusPainted(false); // Remove focus ring
		this.buttonQuit.setBackground(Color.RED);
		this.buttonQuit.setFont(new Font("Arial", Font.PLAIN, 30)); // Arial text font size 30
		this.buttonQuit.setForeground(Color.WHITE); // Make text white
		this.buttonQuit.addActionListener(this); // Add action listener to help button
		this.panel.add(this.buttonQuit); // Add help button to the panel
		
		////////////////////////////////////////////////////////////////
		
		this.frame = new JFrame("Game Of The Generals Marvel Edition");
		// Puts the panel inside the frame.
		this.frame.setContentPane(panel);
		
		// Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
		this.frame.pack();
		
		// Exit Java program when the frame is closed.
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Disables window resizing.
		this.frame.setResizable(false);
		
		// Shows the frame.
		this.frame.setVisible(true);
		
		////////////////////////////////////////////////////////////////
		
	}
	
	
	// Main Method
	public static void main(String[] args){
		new MarvelGameOfTheGenerals();
	}
	
	
}
