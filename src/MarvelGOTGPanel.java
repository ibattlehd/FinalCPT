import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class MarvelGOTGPanel extends JPanel{
	
	// Properties
	BufferedImage mainmenu;
	
	
	// Methods
	public void paintComponent(Graphics g){ // Overriding JPanel's paintComponent method
		g.drawImage(mainmenu, 0, 0, null);
	}
	
	
	// Constructor
	public MarvelGOTGPanel(){
		super();
		// Images
		try{
			mainmenu = ImageIO.read(new File("../img/mainmenu.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
	}
	
		
}
