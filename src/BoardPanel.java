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
	BufferedImage hydrasoldier1;
	BufferedImage hydrasoldier2;
	BufferedImage hydrasoldier3;
	BufferedImage hydrasoldier4;
	BufferedImage hydrasoldier5;
	BufferedImage hydrasoldier6;
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
	BufferedImage hela1;
	BufferedImage hela2;
	BufferedImage rednexus;
	BufferedImage bluenexus;
	//BufferedImage board;
	
	// Heroes
	// (blnDead, intPower, blnSpecialPiece, blnSpecialAbility, intRow, intCol)
	character Thor = new character(false, 12, false, true, 0, 3);
	character IronMan = new character(false, 11, false, false, 1, 3);
	character Hulk = new character(false, 10, false, false, 1, 4);
	character DrStrange = new character(false, 9, false, false, 0, 2);
	character CaptainAmerica = new character(false, 8, false, false, 0, 5);
	character BlackPanther = new character(false, 7, false, true, 1, 1);
	character Spiderman = new character(false, 6, false, true, 1, 6);
	character Wanda = new character(false, 5, false, false, 0, 7);
	character StarLord = new character(false, 4, false, false, 0, 0);
	character Hawkeye = new character(false, 3, false, false, 0, 8);
	character AntMan = new character(false, 2, false, false, 2, 8);
	character Vision = new character(false, 1, false, false, 0, 1);
	character Loki1 = new character(false, 14, false, true, 1, 0);
	character Loki2 = new character(false, 14, false, true, 1, 8);
	character ShieldAgent1 = new character(false, 13, false, true, 2, 2);
	character ShieldAgent2 = new character(false, 13, false, true, 2, 3);
	character ShieldAgent3 = new character(false, 13, false, true, 2, 4);
	character ShieldAgent4 = new character(false, 13, false, true, 2, 5);
	character ShieldAgent5 = new character(false, 13, false, true, 2, 6);
	character ShieldAgent6 = new character(false, 13, false, true, 2, 0);
	character BlueNexus = new character(false, 0, true, false, 0, 4);
	
	// Villians
	character Thanos = new character(false, 12, false, true, 7, 3);
	character Ultron = new character(false, 11, false, false, 6, 3);
	character Dormammu = new character(false, 10, false, false, 6, 4);
	character DrDoom = new character(false, 9, false, false, 7, 2);
	character RedSkull = new character(false, 8, false, false, 7, 5);
	character KillMonger = new character(false, 7, false, true, 6, 1);
	character Venom = new character(false, 6, false, true, 6, 6);
	character DocOck = new character(false, 5, false, false, 7, 7);
	character Ronan = new character(false, 4, false, false, 7, 0);
	character Modok = new character(false, 3, false, false, 7, 8);
	character Yellowjacket = new character(false, 2, false, false, 5, 8);
	character Punisher = new character(false, 1, false, false, 7, 1);
	character Hela1 = new character(false, 14, false, true, 6, 0);
	character Hela2 = new character(false, 14, false, true, 6, 8);
	character HydraSoldier1 = new character(false, 13, false, true, 5, 2);
	character HydraSoldier2 = new character(false, 13, false, true, 5, 3);
	character HydraSoldier3 = new character(false, 13, false, true, 5, 4);
	character HydraSoldier4 = new character(false, 13, false, true, 5, 5);
	character HydraSoldier5 = new character(false, 13, false, true, 5, 6);
	character HydraSoldier6 = new character(false, 13, false, true, 5, 0);
	
	character RedNexus = new character(false, 0, true, false, 7, 4);
	
	// Methods
	public void paintComponent(Graphics g){ // Overriding JPanel's paintComponent method
		/*
		g.drawImage(board, 0, 0, null);
		
		// Draw heroes
		g.drawImage(antman, AntMan.intX*90, AntMan.intY*90, null);
		g.drawImage(ironman, IronMan.intX*90, IronMan.intY*90, null);
		g.drawImage(hulk, Hulk.intX*90, Hulk.intY*90, null);
		g.drawImage(drstrange, DrStrange.intX*90, DrStrange.intY*90, null);
		g.drawImage(captainamerica, CaptainAmerica.intX*90, CaptainAmerica.intY*90, null);
		g.drawImage(blackpanther, BlackPanther.intX*90, BlackPanther.intY*90, null);
		g.drawImage(spiderman, Spiderman.intX*90, Spiderman.intY*90, null);
		g.drawImage(wanda, Wanda.intX*90, Wanda.intY*90, null);
		g.drawImage(starlord, StarLord.intX*90, StarLord.intY*90, null);
		g.drawImage(hawkeye, Hawkeye.intX*90, Hawkeye.intY*90, null);
		g.drawImage(thor, Thor.intX*90, Thor.intY*90, null);
		g.drawImage(vision, Vision.intX*90, Vision.intY*90, null);
		g.drawImage(shieldagent1, ShieldAgent1.intX*90, ShieldAgent1.intY*90, null);
		g.drawImage(bluenexus, BlueNexus.intX*90, BlueNexus.intY*90, null);
		g.drawImage(loki1, Loki1.intX*90, Loki1.intY*90, null);
		g.drawImage(loki2, Loki2.intX*90, Loki2.intY*90, null);
		g.drawImage(shieldagent2, ShieldAgent2.intX*90, ShieldAgent2.intY*90, null);
		g.drawImage(shieldagent3, ShieldAgent3.intX*90, ShieldAgent3.intY*90, null);
		g.drawImage(shieldagent4, ShieldAgent4.intX*90, ShieldAgent4.intY*90, null);
		g.drawImage(shieldagent5, ShieldAgent5.intX*90, ShieldAgent5.intY*90, null);
		g.drawImage(shieldagent6, ShieldAgent6.intX*90, ShieldAgent6.intY*90, null);
		
		g.drawImage(hydrasoldier4, HydraSoldier4.intX*90, HydraSoldier4.intY*90, null);
		g.drawImage(hydrasoldier5, HydraSoldier5.intX*90, HydraSoldier5.intY*90, null);
		g.drawImage(hydrasoldier6, HydraSoldier6.intX*90, HydraSoldier6.intY*90, null);
		g.drawImage(thanos, Thanos.intX*90, Thanos.intY*90, null);
		g.drawImage(ultron, Ultron.intX*90, Ultron.intY*90, null);
		g.drawImage(dormammu, Dormammu.intX*90, Dormammu.intY*90, null);
		g.drawImage(drdoom, DrDoom.intX*90, DrDoom.intY*90, null);
		g.drawImage(redskull, RedSkull.intX*90, RedSkull.intY*90, null);
		g.drawImage(killmonger, KillMonger.intX*90, KillMonger.intY*90, null);
		g.drawImage(venom, Venom.intX*90, Venom.intY*90, null);
		g.drawImage(docock, DocOck.intX*90, DocOck.intY*90, null);
		g.drawImage(ronan, Ronan.intX*90, Ronan.intY*90, null);
		g.drawImage(modok, Modok.intX*90, Modok.intY*90, null);
		g.drawImage(yellowjacket, Yellowjacket.intX*90, Yellowjacket.intY*90, null);
		g.drawImage(punisher, Punisher.intX*90, Punisher.intY*90, null);
		g.drawImage(hela1, Hela1.intX*90, Hela1.intY*90, null);
		g.drawImage(hydrasoldier1, HydraSoldier1.intX*90, HydraSoldier1.intY*90, null);
		g.drawImage(rednexus, RedNexus.intX*90, RedNexus.intY*90, null);
		g.drawImage(hela2, Hela2.intX*90, Hela2.intY*90, null);
		g.drawImage(hydrasoldier2, HydraSoldier2.intX*90, HydraSoldier2.intY*90, null);
		g.drawImage(hydrasoldier3, HydraSoldier3.intX*90, HydraSoldier3.intY*90, null);
		*/
	}
	
	// Constructor
	public BoardPanel(){
		super();
		// Images
		/*
		try{
			board = ImageIO.read(new File("../img/board.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
		*/
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
			hydrasoldier1 = ImageIO.read(new File("../characterimg/Resized Images/hydrasoldiers.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			hydrasoldier2 = ImageIO.read(new File("../characterimg/Resized Images/hydrasoldiers.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			hydrasoldier3 = ImageIO.read(new File("../characterimg/Resized Images/hydrasoldiers.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			hydrasoldier4 = ImageIO.read(new File("../characterimg/Resized Images/hydrasoldiers.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			hydrasoldier5 = ImageIO.read(new File("../characterimg/Resized Images/hydrasoldiers.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			hydrasoldier6 = ImageIO.read(new File("../characterimg/Resized Images/hydrasoldiers.jpg"));
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
			hela1 = ImageIO.read(new File("../characterimg/Resized Images/hela.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			hela2 = ImageIO.read(new File("../characterimg/Resized Images/hela.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}try{
			rednexus = ImageIO.read(new File("../characterimg/Resized Images/evilnexus.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
	}
}
