import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class BoardPanel extends JPanel{
	// Properties
	BufferedImage antman;
	BufferedImage ironman;
	BufferedImage hulk;
	BufferedImage drstrange;
	BufferedImage captainamerica;
	BufferedImage blackpanther;
	BufferedImage spiderman;
	BufferedImage wanda;
	BufferedImage starlord;
	BufferedImage hawkeye;
	BufferedImage thor;
	BufferedImage vision;
	BufferedImage loki1;
	BufferedImage loki2;
	BufferedImage shieldagent1;
	BufferedImage shieldagent2;
	BufferedImage shieldagent3;
	BufferedImage shieldagent4;
	BufferedImage shieldagent5;
	BufferedImage shieldagent6;
	BufferedImage thanos;
	BufferedImage hydrasoldiers;
	BufferedImage ultron;
	BufferedImage drdoom;
	BufferedImage redskull;
	BufferedImage dormammu;
	BufferedImage killmonger;
	BufferedImage venom;
	BufferedImage docock;
	BufferedImage ronan;
	BufferedImage modok;
	BufferedImage yellowjacket;
	BufferedImage punisher;
	BufferedImage hela;
	BufferedImage rednexus;
	BufferedImage bluenexus;
	
	// Methods
	public void paintComponent(Graphics g){ // Overriding JPanel's paintComponent method
	}
	
	// Constructor
	public BoardPanel(){
		super();
		// Images
		try{
			antman = ImageIO.read(new File("../characterimg/Resized Images/antman.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			ironman = ImageIO.read(new File("../characterimg/Resized Images/ironman.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			hulk = ImageIO.read(new File("../characterimg/Resized Images/hulk.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			drstrange = ImageIO.read(new File("../characterimg/Resized Images/drstrange.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			captainamerica = ImageIO.read(new File("../characterimg/Resized Images/captainamerica.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			blackpanther = ImageIO.read(new File("../characterimg/Resized Images/blackpanther.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			spiderman = ImageIO.read(new File("../characterimg/Resized Images/spiderman.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			wanda = ImageIO.read(new File("../characterimg/Resized Images/wanda.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			starlord = ImageIO.read(new File("../characterimg/Resized Images/starlord.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			hawkeye = ImageIO.read(new File("../characterimg/Resized Images/hawkeye.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			thor = ImageIO.read(new File("../characterimg/Resized Images/thor.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			vision = ImageIO.read(new File("../characterimg/Resized Images/vision.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			loki1 = ImageIO.read(new File("../characterimg/Resized Images/loki.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			loki2 = ImageIO.read(new File("../characterimg/Resized Images/loki.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			shieldagent1 = ImageIO.read(new File("../characterimg/Resized Images/shieldagent1.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			shieldagent2 = ImageIO.read(new File("../characterimg/Resized Images/shieldagent2.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			shieldagent3 = ImageIO.read(new File("../characterimg/Resized Images/shieldagent3.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			shieldagent4 = ImageIO.read(new File("../characterimg/Resized Images/shieldagent4.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			shieldagent5 = ImageIO.read(new File("../characterimg/Resized Images/shieldagent5.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			shieldagent6 = ImageIO.read(new File("../characterimg/Resized Images/shieldagent6.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			bluenexus = ImageIO.read(new File("../characterimg/Resized Images/goodnexus.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			thanos = ImageIO.read(new File("../characterimg/Resized Images/thanos.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			hydrasoldiers = ImageIO.read(new File("../characterimg/Resized Images/hydrasoldiers.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			ultron = ImageIO.read(new File("../characterimg/Resized Images/ultron.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			dormammu = ImageIO.read(new File("../characterimg/Resized Images/dormammu.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			drdoom = ImageIO.read(new File("../characterimg/Resized Images/drdoom.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			redskull = ImageIO.read(new File("../characterimg/Resized Images/redskull.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			killmonger = ImageIO.read(new File("../characterimg/Resized Images/killmonger.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			venom = ImageIO.read(new File("../characterimg/Resized Images/venom.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			docock = ImageIO.read(new File("../characterimg/Resized Images/docock.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			ronan = ImageIO.read(new File("../characterimg/Resized Images/ronan.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			modok = ImageIO.read(new File("../characterimg/Resized Images/modok.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			yellowjacket = ImageIO.read(new File("../characterimg/Resized Images/yellowjacket.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			punisher = ImageIO.read(new File("../characterimg/Resized Images/punisher.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			hela = ImageIO.read(new File("../characterimg/Resized Images/hela.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			rednexus = ImageIO.read(new File("../characterimg/Resized Images/evilnexus.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
	}
}
