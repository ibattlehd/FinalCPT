import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MarvelGameOfTheGenerals implements ActionListener{
	// Properties
	JFrame theframe = new JFrame("Game Of The Generals Marvel Edition");
	JPanel thepanel = new JPanel();
	Timer thetimer = new Timer(1000/60, this);
	
	
	// Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			thepanel.repaint();
		
		}
		
	}
	
	
	// Constructor
	public MarvelGameOfTheGenerals(){
	
	thepanel.setLayout(null);
	thepanel.setPreferredSize(new Dimension(1280, 720));
	
	theframe.setContentPane(thepanel);
	theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	theframe.pack();
	
	
	theframe.setVisible(true);
	
	thetimer.start();
	
	}
	
	
	
	// Main Method
	public static void main(String[] args){
		new MarvelGameOfTheGenerals();
	}




}
