import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class MenuPanel extends JPanel{
	// Properties
	int intPage = 1;
	BufferedImage mainmenu;
	BufferedImage antman;
	Font font = null;
	
	// Methods
	public void paintComponent(Graphics g){ // Overriding JPanel's paintComponent method
		if(intPage == 1){
			g.drawImage(mainmenu, 0, 0, null);
		}
	}
	
	// Constructor
	public MenuPanel(){
		super();
		try{
			font = new Font("../fonts/Roboto-Regular.ttf", Font.PLAIN, 40);
		}catch(Exception e){
			System.out.println("Unable to load font");
		}	
		// Images
		try{
			mainmenu = ImageIO.read(new File("../img/mainmenu.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
		try{
			antman = ImageIO.read(new File("../characterimg/Resized Images/antman.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
	}
}
