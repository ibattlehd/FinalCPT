import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class GamePanel extends JPanel{
	
	// Properties
	int intPage = 1;
	BufferedImage mainmenu;
	
	
	// Methods
	public void paintComponent(Graphics g){ // Overriding JPanel's paintComponent method
		if(intPage == 1){
			g.drawImage(mainmenu, 0, 0, null);
		}
	}
	
	
	// Constructor
	public GamePanel(){
		super();
		// Images
		try{
			mainmenu = ImageIO.read(new File("../img/mainmenu.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
	}
	
		
}
