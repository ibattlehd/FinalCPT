import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/**
* <h1>Game of the Generals - Marvel Edition</h1>
* The Marvel edition of Game of the Generals in Java.
* <p>
* For more information visit https://ibattlehd.github.io/FinalCPT/
* @author  ibattlehd
* @version 1.0
*/

public class MarvelGameOfTheGenerals implements ActionListener, KeyListener, MouseListener{
	// Properties
	JFrame menuframe;
	JFrame boardframe;
	MenuPanel menupanel;
	BoardPanel boardpanel;
	Timer timer;
	SuperSocketMaster ssm;
	// Main Menu
	JButton buttonPlay;
	JButton buttonScores;
	JButton buttonHelp;
	JButton buttonQuit;
	Font font_1;
	Font font_2;
	// Help Menu
	JButton buttonMainMenu;
	JButton buttonNext;
	JButton buttonPrevious;
	JTextArea RulesOfGame;
	// Used to setup game
	JLabel labelIP;
	JButton buttonHost;
	JButton buttonClient;
	JTextField EnterIP;
	JButton buttonBack;
	JButton buttonBack2;
	// Used to setup board
	JButton button[][] = new JButton[8][9];
	String strArray[][] = new String[8][9];
	int intRow;
	int intCol;
	// Chat
	JTextArea ChatBox = new JTextArea();
	JScrollPane ChatBoxScroll = new JScrollPane(ChatBox); 
	JTextField SendMessage = new JTextField();
	// Help Menu - Instructions (String)
	int intHelpPage = 0; // Set initial value of Help Menu Pages to 0
	// Objective Instruction
	String strObjective = "OBJECTIVE";
	String strObjectiveDescription = "The Objective of the game is to eliminate the nexus of your opponent";
	// Pieces Information
	String strNote = "NOTE: If both characters are of equal power, BOTH are eliminated.";
	// Movement Rules
	String strMovementTitle = "MOVEMENT";
	String strMovementDescription = "1. Heroes always make the first move. Players move alternately"+"\n"+"2. A player is allowed to move only one piece at a time."+"\n"+"3. A move consists of moving a piece to a square, either forward, backward or sideward. A diagonal move or a move of more than one square is illegal";
	// Battling between players Rules
	String strBattleTitle = "BATTLE";
	String strBattleDescription = "As the game progresses, battles are made resulting in the elimination of marvel characters. A battle is made when a character moves into the same square occupied by an opposing character. When a battle occurs the following rules of elimation apply:";
	String strBattleDescriptionCont = "1. A higher powered character eliminates a lower powered character from the board"+"\n"+"2. If both characters power level are of equal, both are eliminated."+"\n"+"3. Loki(Heroes)/Hela(Villian) eliminates any character with a power level of 12 down to a character with a power level of 1"+"\n"+"4. The Nexus can be eliminated by any piece"+"\n"+"5. Only Shield Agents(Heroes) and Hydra Soldiers(Villians) can eliminate the infiltrator(Loki/Hela)";
	// How the game ends
	String strEndGameTitle = "HOW THE GAME ENDS";
	String strEndGameDescription = "The game ends:";
	String strEndGameDescriptionCont = "1. When the Nexus is eliminated"+"\n"+"2. When a player resigns"+"\n"+"3. When both players agree on a draw";
	// Heroes
	// (blnDead, intPower, blnSpecialPiece, blnSpecialAbility, intArrayX, intArrayY)
	character Thor = new character(false, 12, false, true, 1, 1);
	character IronMan = new character(false, 11, false, false, 0, 1);
	character Hulk = new character(false, 10, false, false, 0, 2);
	character DrStrange = new character(false, 9, false, false, 0, 3);
	character CaptainAmerica = new character(false, 8, false, false, 0, 4);
	character BlackPanther = new character(false, 7, false, true, 0, 5);
	character Spiderman = new character(false, 6, false, true, 0, 6);
	character Wanda = new character(false, 5, false, false, 0, 7);
	character StarLord = new character(false, 4, false, false, 0, 8);
	character Hawkeye = new character(false, 3, false, false, 1, 0);
	character AntMan = new character(false, 2, false, false, 0, 0);
	character Vision = new character(false, 1, false, false, 1, 2);
	character Loki1 = new character(false, 14, false, true, 1, 3);
	character Loki2 = new character(false, 14, false, true, 1, 6);
	character ShieldAgent1 = new character(false, 13, false, true, 1, 4);
	character ShieldAgent2 = new character(false, 13, false, true, 1, 7);
	character ShieldAgent3 = new character(false, 13, false, true, 1, 8);
	character ShieldAgent4 = new character(false, 13, false, true, 2, 0);
	character ShieldAgent5 = new character(false, 13, false, true, 2, 1);
	character ShieldAgent6 = new character(false, 13, false, true, 2, 2);
	character BlueNexus = new character(false, 0, true, false, 1, 5);
	
	// Villians
	character Thanos = new character(false, 12, false, true, 6, 0);
	character Ultron = new character(false, 11, false, false, 6, 1);
	character Dormammu = new character(false, 10, false, false, 6, 2);
	character DrDoom = new character(false, 9, false, false, 6, 3);
	character RedSkull = new character(false, 8, false, false, 6, 4);
	character KillMonger = new character(false, 7, false, true, 6, 5);
	character Venom = new character(false, 6, false, true, 6, 6);
	character DocOck = new character(false, 5, false, false, 6, 7);
	character Ronan = new character(false, 4, false, false, 6, 8);
	character Modok = new character(false, 3, false, false, 7, 0);
	character Yellowjacket = new character(false, 2, false, false, 7, 1);
	character Punisher = new character(false, 1, false, false, 7, 2);
	character Hela = new character(false, 14, false, true, 7, 3);
	character HydraSoldier1 = new character(false, 13, false, true, 7, 4);
	character HydraSoldier2 = new character(false, 13, false, true, 7, 7);
	character HydraSoldier3 = new character(false, 13, false, true, 7, 8);
	character HydraSoldier4 = new character(false, 13, false, true, 5, 0);
	character HydraSoldier5 = new character(false, 13, false, true, 5, 1);
	character HydraSoldier6 = new character(false, 13, false, true, 5, 2);
	character RedNexus = new character(false, 0, true, false, 7, 5);
	
	// Methods
	@Override
	public void actionPerformed(ActionEvent evt){ // Action listener
		if(evt.getSource() == timer){
			menupanel.repaint(); // Repaint the panel based on timer (60 fps).
		}
		else if(evt.getSource() == buttonPlay){
			// hide menu JButtons
			buttonPlay.setVisible(false);
			buttonScores.setVisible(false);
			buttonHelp.setVisible(false);
			buttonQuit.setVisible(false);
			// show necessary JButtons
			buttonHost.setVisible(true);
			buttonClient.setVisible(true);
			buttonBack2.setVisible(true);
		}
		else if(evt.getSource() == buttonHost){
			labelIP.setVisible(true); // show host IP address
			buttonHost.setVisible(false); // hide host JButton
			buttonClient.setVisible(false); // hide client JButton
			buttonBack.setVisible(true); // show back JButton
		}
		else if(evt.getSource() == buttonClient){
			buttonHost.setVisible(false); // hide host JButton
			buttonClient.setVisible(false); // hide client JButton
			buttonBack.setVisible(true); // show back JButton
			EnterIP.setVisible(true); // show EnterIP JTextFielda
		}
		else if(evt.getSource() == buttonBack){
			buttonBack.setVisible(false);
			buttonHost.setVisible(true);
			buttonClient.setVisible(true);
			labelIP.setVisible(false);
			EnterIP.setVisible(false);
		}
		else if(evt.getSource() == buttonBack2){
			buttonBack2.setVisible(false);
			buttonPlay.setVisible(true);
			buttonScores.setVisible(true);
			buttonHelp.setVisible(true);
			buttonQuit.setVisible(true);
			buttonHost.setVisible(false);
			buttonClient.setVisible(false);
			buttonBack.setVisible(false);
		}
		else if(evt.getSource() == buttonScores){
		}
		else if(evt.getSource() == buttonHelp){  //User selects Help Button
			intHelpPage = intHelpPage + 1; // Plus 1 to make the 1st page of help menu appear on screen
			//intHelpPage = 1;
			RulesOfGame.setText(strObjective+"\n"+"\n"+strObjectiveDescription+"\n"+"\n"+strNote); 
			buttonPlay.setVisible(false);
			buttonScores.setVisible(false);
			buttonHelp.setVisible(false);
			buttonQuit.setVisible(false);
			RulesOfGame.setVisible(true);
			buttonMainMenu.setVisible(true);
			buttonNext.setVisible(true);	
		}
		else if(evt.getSource() == buttonMainMenu){ // User selects Main Menu Button in Help menu
			intHelpPage = 0; // Return back to main menu
			buttonPlay.setVisible(true);
			buttonScores.setVisible(true);
			buttonHelp.setVisible(true);
			buttonQuit.setVisible(true);
			RulesOfGame.setVisible(false);
			buttonMainMenu.setVisible(false);
			buttonNext.setVisible(false);
			buttonPrevious.setVisible(false);
		}
		else if(evt.getSource() == buttonNext){ // User selects next button
			intHelpPage = intHelpPage + 1; // Goes to next page of text area (RulesOfGame)
			RulesOfGame.setText("");

			// 1st page of Help Menu
			if(intHelpPage == 1){
				buttonPrevious.setVisible(false); // set to false because there is no previous pages to go back to
				buttonNext.setVisible(true); // Allow user to go next page
				RulesOfGame.setText(strObjective+"\n"+"\n"+strObjectiveDescription+"\n"+"\n"+strNote); // All the instructions(String) for the first page
				
				
			// 2nd Page of Help Menu
			}else if(intHelpPage == 2){
				buttonPrevious.setVisible(true); // allows user to go back to 1st page
				buttonNext.setVisible(true); // allow user to go to next page
				RulesOfGame.setText(strMovementTitle+"\n"+"\n"+strMovementDescription+"\n"+"\n"+strBattleTitle+"\n"+"\n"+strBattleDescription+"\n"+"\n"+strBattleDescriptionCont); // Instructions on the 2nd page
			// 3rd Page of Help Menu
			}else if(intHelpPage == 3){
				RulesOfGame.setText(strEndGameTitle+"\n"+"\n"+strEndGameDescription+"\n"+"\n"+strEndGameDescriptionCont); // Instructions on the third page (last page)
				buttonNext.setVisible(false); // no more pages 
				buttonPrevious.setVisible(true); // go back to previous pages
			}
		}
		// Button to go back to previous pages
		else if(evt.getSource() == buttonPrevious){
			intHelpPage = intHelpPage - 1;
				if(intHelpPage == 1){
					buttonPrevious.setVisible(false);
					buttonNext.setVisible(true);
					RulesOfGame.setText(strObjective+"\n"+"\n"+strObjectiveDescription+"\n"+"\n"+strNote);					
					
				}else if(intHelpPage == 2){
					buttonPrevious.setVisible(true);
					buttonNext.setVisible(true);
					RulesOfGame.setText(strMovementTitle+"\n"+"\n"+strMovementDescription+"\n"+"\n"+strBattleTitle+"\n"+"\n"+strBattleDescription+"\n"+"\n"+strBattleDescriptionCont);
					
				}else if(intHelpPage == 3){
					buttonPrevious.setVisible(true);
					buttonNext.setVisible(false);	
					RulesOfGame.setText(strEndGameTitle+"\n"+"\n"+strEndGameDescription+"\n"+"\n"+strEndGameDescriptionCont);	
				}
		}
		else if(evt.getSource() == buttonQuit){
				System.exit(0);
		}
		/*
		else if(evt.getSource() == button[0][0]){
			System.out.println("Pressed button 1 on board");
			for(intRow = 0; intRow < 8; intRow++){
				for(intCol = 0; intCol < 9; intCol++){
					if(button[intRow][intCol].getIcon() != null){
						button[intRow][intCol].setIcon(null);
					}
				}
			}
			button[0][0].setIcon(new ImageIcon(boardpanel.antman));
		}
		else if(evt.getSource() == button[0][1]){
			System.out.println("Pressed button 2 on board");
			for(intRow = 0; intRow < 8; intRow++){
				for(intCol = 0; intCol < 9; intCol++){
					if(button[intRow][intCol].getIcon() != null){
						button[intRow][intCol].setIcon(null);
					}
				}
			}
			button[0][1].setIcon(new ImageIcon(boardpanel.antman));
		}
		*/
	}
		
	@Override
	public void keyTyped(KeyEvent evt){ // Called when a key is typed.
		if(evt.getSource() == EnterIP){
			String strAddress = EnterIP.getText();
			if(strAddress.equalsIgnoreCase(labelIP.getText())){
				System.out.println("Entered correct host IP");
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent evt){ // Called when a key is pressed.
	}
		
	@Override
	public void keyReleased(KeyEvent evt){ // Called when a key is released.
	}
		
	@Override
	public void mouseClicked(MouseEvent evt){ // Called after the user clicks the listened-to component.
	}
		
	@Override
	public void mouseEntered(MouseEvent evt){ // Called after the cursor enters the bounds of the listened-to component.
		if(evt.getSource() == buttonPlay){
			buttonPlay.setBackground(Color.RED);
			buttonPlay.setOpaque(true);
			buttonPlay.setBorderPainted(false);
		}
		if(evt.getSource() == buttonScores){
			buttonScores.setBackground(Color.RED);
			buttonScores.setOpaque(true);
			buttonScores.setBorderPainted(false);
		}
		if(evt.getSource() == buttonHelp){
			buttonHelp.setBackground(Color.RED);
			buttonHelp.setOpaque(true);
			buttonHelp.setBorderPainted(false);
		}
		if(evt.getSource() == buttonQuit){
			buttonQuit.setBackground(Color.RED);
			buttonQuit.setOpaque(true);
			buttonQuit.setBorderPainted(false);
		}
		if(evt.getSource() == buttonHost){
			buttonHost.setBackground(Color.RED);
			buttonHost.setOpaque(true);
			buttonHost.setBorderPainted(false);
		}
		if(evt.getSource() == buttonClient){
			buttonClient.setBackground(Color.RED);
			buttonClient.setOpaque(true);
			buttonClient.setBorderPainted(false);
		}
		if(evt.getSource() == buttonBack){
			buttonBack.setBackground(Color.RED);
			buttonBack.setOpaque(true);
			buttonBack.setBorderPainted(false);
		}
		if(evt.getSource() == buttonBack2){
			buttonBack2.setBackground(Color.RED);
			buttonBack2.setOpaque(true);
			buttonBack2.setBorderPainted(false);
		}
		if(evt.getSource() == buttonMainMenu){
			buttonMainMenu.setBackground(Color.RED);
			buttonMainMenu.setOpaque(true);
			buttonMainMenu.setBorderPainted(false);
		}
		if(evt.getSource() == buttonNext){
			buttonNext.setBackground(Color.RED);
			buttonNext.setOpaque(true);
			buttonNext.setBorderPainted(false);
		}
		if(evt.getSource() == buttonPrevious){
			buttonPrevious.setBackground(Color.RED);
			buttonPrevious.setOpaque(true);
			buttonPrevious.setBorderPainted(false);
		}
	}
		
	@Override
	public void mouseExited(MouseEvent evt){ // Called after the cursor exits the bounds of the listened-to component.
		if(evt.getSource() == buttonPlay){
			buttonPlay.setBackground(Color.BLACK);
			buttonPlay.setOpaque(true);
			buttonPlay.setBorderPainted(false);
		}
		if(evt.getSource() == buttonScores){
			buttonScores.setBackground(Color.BLACK);
			buttonScores.setOpaque(true);
			buttonScores.setBorderPainted(false);
		}
		if(evt.getSource() == buttonHelp){
			buttonHelp.setBackground(Color.BLACK);
			buttonHelp.setOpaque(true);
			buttonHelp.setBorderPainted(false);
		}
		if(evt.getSource() == buttonQuit){
			buttonQuit.setBackground(Color.BLACK);
			buttonQuit.setOpaque(true);
			buttonQuit.setBorderPainted(false);
		}
		if(evt.getSource() == buttonHost){
			buttonHost.setBackground(Color.BLACK);
			buttonHost.setOpaque(true);
			buttonHost.setBorderPainted(false);
		}
		if(evt.getSource() == buttonClient){
			buttonClient.setBackground(Color.BLACK);
			buttonClient.setOpaque(true);
			buttonClient.setBorderPainted(false);
		}
		if(evt.getSource() == buttonMainMenu){
			buttonMainMenu.setBackground(Color.BLACK);
			buttonMainMenu.setOpaque(true);
			buttonMainMenu.setBorderPainted(false);
		}
		if(evt.getSource() == buttonNext){
			buttonNext.setBackground(Color.BLACK);
			buttonNext.setOpaque(true);
			buttonNext.setBorderPainted(false);
		}
		if(evt.getSource() == buttonPrevious){
			buttonPrevious.setBackground(Color.BLACK);
			buttonPrevious.setOpaque(true);
			buttonPrevious.setBorderPainted(false);
		}
		if(evt.getSource() == buttonBack){
			buttonBack.setBackground(Color.BLACK);
			buttonBack.setOpaque(true);
			buttonBack.setBorderPainted(false);
		}
		if(evt.getSource() == buttonBack2){
			buttonBack2.setBackground(Color.BLACK);
			buttonBack2.setOpaque(true);
			buttonBack2.setBorderPainted(false);
		}
	}
			
	@Override
	public void mousePressed(MouseEvent evt){ // Called after the user presses a mouse button while the cursor is over the listened-to component.
	}
			
	@Override
	public void mouseReleased(MouseEvent evt){ // Called after the user releases a mouse button after a mouse press over the listened-to component.
	}
	
	// Constructor
	public MarvelGameOfTheGenerals(){
		
		menupanel = new MenuPanel();
		menupanel.setLayout(null);
		menupanel.setPreferredSize(new Dimension(1280, 720));
		
		boardpanel = new BoardPanel();
		boardpanel.setLayout(new GridLayout(8,9));
		//boardpanel.setLayout(null);
		boardpanel.setPreferredSize(new Dimension(810, 720));
		
		timer = new Timer(1000/60, this);
		timer.start();
		
		////////////////////
		// JButtons
		////////////////////
		
		for(intRow = 0; intRow < 8; intRow++){
			for(intCol = 0; intCol < 9; intCol++){
				button[intRow][intCol] = new JButton();
				button[intRow][intCol].setSize(90, 90);
				button[intRow][intCol].setBackground(Color.BLACK); // Set background to black
				button[intRow][intCol].setOpaque(true);
				button[intRow][intCol].addActionListener(this);
				boardpanel.add(button[intRow][intCol]);
			}
		}
		strArray[AntMan.intX][AntMan.intY] = "antman";
		strArray[IronMan.intX][IronMan.intY] = "ironman";
		strArray[Hulk.intX][Hulk.intY] = "hulk";
		strArray[DrStrange.intX][DrStrange.intY] = "drstrange";
		strArray[CaptainAmerica.intX][CaptainAmerica.intY] = "captainamerica";
		strArray[BlackPanther.intX][BlackPanther.intY] = "blackpanther";
		strArray[Spiderman.intX][Spiderman.intY] = "spiderman";
		strArray[Wanda.intX][Wanda.intY] = "wanda";
		strArray[StarLord.intX][StarLord.intY] = "starlord";
		strArray[Hawkeye.intX][Hawkeye.intY] = "hawkeye";
		strArray[Thor.intX][Thor.intY] = "thor";
		strArray[Vision.intX][Vision.intY] = "vision";
		strArray[ShieldAgent1.intX][ShieldAgent1.intY] = "shieldagent1";
		strArray[BlueNexus.intX][BlueNexus.intY] = "bluenexus";
		strArray[Loki1.intX][Loki1.intY] = "loki1";
		strArray[Loki2.intX][Loki2.intY] = "loki2";
		strArray[ShieldAgent2.intX][ShieldAgent2.intY] = "shieldagent2";
		strArray[ShieldAgent3.intX][ShieldAgent3.intY] = "shieldagent3";
		strArray[ShieldAgent4.intX][ShieldAgent4.intY] = "shieldagent4";
		strArray[ShieldAgent5.intX][ShieldAgent5.intY] = "shieldagent5";
		strArray[ShieldAgent6.intX][ShieldAgent6.intY] = "shieldagent6";
		
		strArray[5][0] = "hydrasoldiers";
		strArray[5][1] = "hydrasoldiers";
		strArray[5][2] = "hydrasoldiers";
		strArray[6][0] = "thanos";
		strArray[6][1] = "ultron";
		strArray[6][2] = "dormammu";
		strArray[6][3] = "drdoom";
		strArray[6][4] = "redskull";
		strArray[6][5] = "killmonger";
		strArray[6][6] = "venom";
		strArray[6][7] = "docock";
		strArray[6][8] = "ronan";
		strArray[7][0] = "modok";
		strArray[7][1] = "yellowjacket";
		strArray[7][2] = "punisher";
		strArray[7][3] = "hela";
		strArray[7][4] = "hydrasoldiers";
		strArray[7][5] = "rednexus";
		strArray[7][6] = "hela";
		strArray[7][7] = "hydrasoldiers";
		strArray[7][8] = "hydrasoldiers";
		if(strArray[0][0].equals("antman")){
			button[0][0].setIcon(new ImageIcon(boardpanel.antman));
		}if(strArray[0][1].equals("ironman")){
			button[0][1].setIcon(new ImageIcon(boardpanel.ironman));
		}if(strArray[0][2].equals("hulk")){
			button[0][2].setIcon(new ImageIcon(boardpanel.hulk));
		}if(strArray[0][3].equals("drstrange")){
			button[0][3].setIcon(new ImageIcon(boardpanel.drstrange));
		}if(strArray[0][4].equals("captainamerica")){
			button[0][4].setIcon(new ImageIcon(boardpanel.captainamerica));
		}if(strArray[0][5].equals("blackpanther")){
			button[0][5].setIcon(new ImageIcon(boardpanel.blackpanther));
		}if(strArray[0][6].equals("spiderman")){
			button[0][6].setIcon(new ImageIcon(boardpanel.spiderman));
		}if(strArray[0][7].equals("wanda")){
			button[0][7].setIcon(new ImageIcon(boardpanel.wanda));
		}if(strArray[0][8].equals("starlord")){
			button[0][8].setIcon(new ImageIcon(boardpanel.starlord));
		}if(strArray[1][0].equals("hawkeye")){
			button[1][0].setIcon(new ImageIcon(boardpanel.hawkeye));
		}if(strArray[1][1].equals("thor")){
			button[1][1].setIcon(new ImageIcon(boardpanel.thor));
		}if(strArray[1][2].equals("vision")){
			button[1][2].setIcon(new ImageIcon(boardpanel.vision));
		}if(strArray[1][3].equals("loki1")){
			button[1][3].setIcon(new ImageIcon(boardpanel.loki1));
		}if(strArray[1][4].equals("shieldagent1")){
			button[1][4].setIcon(new ImageIcon(boardpanel.shieldagent1));
		}if(strArray[1][5].equals("bluenexus")){
			button[1][5].setIcon(new ImageIcon(boardpanel.bluenexus));
		}if(strArray[1][6].equals("loki2")){
			button[1][6].setIcon(new ImageIcon(boardpanel.loki2));
		}if(strArray[1][7].equals("shieldagent2")){
			button[1][7].setIcon(new ImageIcon(boardpanel.shieldagent2));
		}if(strArray[1][8].equals("shieldagent3")){
			button[1][8].setIcon(new ImageIcon(boardpanel.shieldagent3));
		}if(strArray[2][0].equals("shieldagent4")){
			button[2][0].setIcon(new ImageIcon(boardpanel.shieldagent4));
		}if(strArray[2][1].equals("shieldagent5")){
			button[2][1].setIcon(new ImageIcon(boardpanel.shieldagent5));
		}if(strArray[2][2].equals("shieldagent6")){
			button[2][2].setIcon(new ImageIcon(boardpanel.shieldagent6));
		}
		// villians
		if(strArray[7][0].equals("modok")){
			button[7][0].setIcon(new ImageIcon(boardpanel.modok));
		}if(strArray[7][1].equals("yellowjacket")){
			button[7][1].setIcon(new ImageIcon(boardpanel.yellowjacket));
		}if(strArray[7][2].equals("punisher")){
			button[7][2].setIcon(new ImageIcon(boardpanel.punisher));
		}if(strArray[7][3].equals("hela")){
			button[7][3].setIcon(new ImageIcon(boardpanel.hela));
		}if(strArray[7][4].equals("hydrasoldiers")){
			button[7][4].setIcon(new ImageIcon(boardpanel.hydrasoldiers));
		}if(strArray[7][5].equals("rednexus")){
			button[7][5].setIcon(new ImageIcon(boardpanel.rednexus));
		}if(strArray[7][6].equals("hela")){
			button[7][6].setIcon(new ImageIcon(boardpanel.hela));
		}if(strArray[7][7].equals("hydrasoldiers")){
			button[7][7].setIcon(new ImageIcon(boardpanel.hydrasoldiers));
		}if(strArray[7][8].equals("hydrasoldiers")){
			button[7][8].setIcon(new ImageIcon(boardpanel.hydrasoldiers));
		}if(strArray[6][0].equals("thanos")){
			button[6][0].setIcon(new ImageIcon(boardpanel.thanos));
		}if(strArray[6][1].equals("ultron")){
			button[6][1].setIcon(new ImageIcon(boardpanel.ultron));
		}if(strArray[6][2].equals("dormammu")){
			button[6][2].setIcon(new ImageIcon(boardpanel.dormammu));
		}if(strArray[6][3].equals("drdoom")){
			button[6][3].setIcon(new ImageIcon(boardpanel.drdoom));
		}if(strArray[6][4].equals("redskull")){
			button[6][4].setIcon(new ImageIcon(boardpanel.redskull));
		}if(strArray[6][5].equals("killmonger")){
			button[6][5].setIcon(new ImageIcon(boardpanel.killmonger));
		}if(strArray[6][6].equals("venom")){
			button[6][6].setIcon(new ImageIcon(boardpanel.venom));
		}if(strArray[6][7].equals("docock")){
			button[6][7].setIcon(new ImageIcon(boardpanel.docock));
		}if(strArray[6][8].equals("ronan")){
			button[6][8].setIcon(new ImageIcon(boardpanel.ronan));
		}if(strArray[5][0].equals("hydrasoldiers")){
			button[5][0].setIcon(new ImageIcon(boardpanel.hydrasoldiers));
		}if(strArray[5][1].equals("hydrasoldiers")){
			button[5][1].setIcon(new ImageIcon(boardpanel.hydrasoldiers));
		}if(strArray[5][2].equals("hydrasoldiers")){
			button[5][2].setIcon(new ImageIcon(boardpanel.hydrasoldiers));
		}
		
		/*
		 * ***** *    * **** ****** ****** ***   ***     ***** ****** **   * ****** ******
		 * *     *    * *      **   *    * *  * *  *     *     *    * * *  *   **   *
		 * *     *    * ****   **   *    * *   *   *     ***** *    * *  * *   **   ******
		 * *     *    *    *   **   *    * *       *     *     *    * *   **   **        *
		 * ***** ****** ****   **   ****** *       *     *     ****** *    *   **   ******
		 */
		try {
			font_1 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("../fonts/font-1.ttf")).deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("../fonts/font-1.ttf")));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
			font_1 = new Font("Helvetica", Font.BOLD, 30);
		}
		
		try {
			font_2 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("../fonts/font-2.ttf")).deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("../fonts/font-2.ttf")));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
			font_2 = new Font("Helvetica", Font.BOLD, 30);
		}
		
		buttonPlay = new JButton("Play"); // Play button
		buttonPlay.setSize(200, 50); // 200 pixels by 50 pixels
		buttonPlay.setLocation(540, 230); // x and y coordinates (540, 230)
		buttonPlay.setFocusPainted(false); // Remove focus ring
		buttonPlay.setBackground(Color.BLACK); // Set background to black
		buttonPlay.setOpaque(true);
		buttonPlay.setBorderPainted(false);
		buttonPlay.setFont(font_1); // custom font
		buttonPlay.setForeground(Color.WHITE); // Make text white
		buttonPlay.addActionListener(this); // Add action listener to play button
		buttonPlay.addMouseListener(this); // Add mouse listener to play button
		menupanel.add(buttonPlay); // Add play button to the panel
		
		buttonScores = new JButton("High Scores"); // High scores button
		buttonScores.setSize(200, 50); // 200 pixels by 50 pixels
		buttonScores.setLocation(540, 300); // x and y coordinates (540, 300)
		buttonScores.setFocusPainted(false); // Remove focus ring
		buttonScores.setBackground(Color.BLACK); // Set background to black
		buttonScores.setOpaque(true);
		buttonScores.setBorderPainted(false);
		buttonScores.setFont(font_1); // custom font
		buttonScores.setForeground(Color.WHITE); // Make text white
		buttonScores.addActionListener(this); // Add action listener to high scores button
		buttonScores.addMouseListener(this); // Add mouse listener to high scores button
		menupanel.add(buttonScores); // Add high scores button to the panel
		
		buttonHelp = new JButton("Help"); // Help button
		buttonHelp.setSize(200, 50); // 200 pixels by 50 pixels
		buttonHelp.setLocation(540, 370); // x and y coordinates (540, 370)
		buttonHelp.setFocusPainted(false); // Remove focus ring
		buttonHelp.setBackground(Color.BLACK); // Set background to black
		buttonHelp.setOpaque(true);
		buttonHelp.setBorderPainted(false);
		buttonHelp.setFont(font_1); // custom font
		buttonHelp.setForeground(Color.WHITE); // Make text white
		buttonHelp.addActionListener(this); // Add action listener to help button
		buttonHelp.addMouseListener(this); // Add mouse listener to help button
		menupanel.add(buttonHelp); // Add help button to the panel
		
		buttonQuit = new JButton("Quit"); // Quit button
		buttonQuit.setSize(200, 50); // 200 pixels by 50 pixels
		buttonQuit.setLocation(540, 440); // x and y coordinates (540, 440)
		buttonQuit.setFocusPainted(false); // Remove focus ring
		buttonQuit.setBackground(Color.BLACK); // Set background to black
		buttonQuit.setOpaque(true);
		buttonQuit.setBorderPainted(false);
		buttonQuit.setFont(font_1); // custom font
		buttonQuit.setForeground(Color.WHITE); // Make text white
		buttonQuit.addActionListener(this); // Add action listener to quit button
		buttonQuit.addMouseListener(this); // Add mouse listener to quit button
		menupanel.add(buttonQuit); // Add quit button to the panel
		
		buttonHost = new JButton("Host");
		buttonHost.setSize(250, 100);
		buttonHost.setLocation(300, 300);
		buttonHost.setFocusPainted(false);
		buttonHost.setBackground(Color.BLACK);
		buttonHost.setOpaque(true);
		buttonHost.setBorderPainted(false);
		buttonHost.setFont(font_1); // custom font
		buttonHost.setForeground(Color.WHITE); // Make text white
		buttonHost.addActionListener(this); // Add action listener to host button
		buttonHost.addMouseListener(this); // Add mouse listener to host button
		buttonHost.setVisible(false);
		menupanel.add(buttonHost); // Add host button to the panel
		
		buttonClient = new JButton("Client");
		buttonClient.setSize(250, 100);
		buttonClient.setLocation(750, 300);
		buttonClient.setFocusPainted(false);
		buttonClient.setBackground(Color.BLACK);
		buttonClient.setOpaque(true);
		buttonClient.setBorderPainted(false);
		buttonClient.setFont(font_1); // custom font
		buttonClient.setForeground(Color.WHITE); // Make text white
		buttonClient.addActionListener(this); // Add action listener to client button
		buttonClient.addMouseListener(this); // Add mouse listener to client button
		buttonClient.setVisible(false);
		menupanel.add(buttonClient); // Add client button to the panel
		
		EnterIP = new JTextField();
		EnterIP.setSize(200, 100);
		EnterIP.setLocation(550, 300);
		EnterIP.addKeyListener(this);
		EnterIP.setVisible(false);
		menupanel.add(EnterIP);
		
		/*
		// Text field that will be used to enter name
		P1 = new JTextField();
		P1.setSize(300, 50);
		P1.setLocation(490, 225);
		P1.setVisible(false);
		P1.addActionListener(this);
		menupanel.add(P1);
		*/
		
		////////////////////
		// Help Menu JButtons
		////////////////////
		
		// Main Menu button
		buttonMainMenu = new JButton("Menu"); // Button to return to main menu
		buttonMainMenu.setFont(font_2); // custom font
		buttonMainMenu.setForeground(Color.WHITE); // Make text white
		buttonMainMenu.setBackground(Color.BLACK); // Set background to black
		buttonMainMenu.setOpaque(true);
		buttonMainMenu.setFocusPainted(false); // Remove focus ring
		buttonMainMenu.setBorderPainted(false);
		buttonMainMenu.addActionListener(this); // Add action listener to button main menu
		buttonMainMenu.setSize(130,30); // 130 x 30 pixels
		buttonMainMenu.setLocation(580,615); // x and y coordinates (580, 615)
		buttonMainMenu.setVisible(false);
		buttonMainMenu.addMouseListener(this);
		menupanel.add(buttonMainMenu); // Add main menu button to the panel
		
		// Next button 
		buttonNext = new JButton("Next"); // Button to go to next page
		buttonNext.setFont(font_2); // custom font
		buttonNext.setForeground(Color.WHITE); // make text white
		buttonNext.setBackground(Color.BLACK); // set background to black
		buttonNext.setOpaque(true);
		buttonNext.setFocusPainted(false); // Remove focus ring
		buttonNext.setBorderPainted(false);
		buttonNext.addActionListener(this); // Add action listener to next button
		buttonNext.setSize(130,30); // 130 x 30 pixels
		buttonNext.setLocation(727,615); // x and y coordinates (727, 615)
		buttonNext.setVisible(false);
		buttonNext.addMouseListener(this);
		menupanel.add(buttonNext); // Add next button to the panel
		
		// Previous button
		buttonPrevious = new JButton("Previous"); // Button to go back to previous page
		buttonPrevious.setFont(font_2); // custom font
		buttonPrevious.setForeground(Color.WHITE); // make text white
		buttonPrevious.setBackground(Color.BLACK); // set background to black
		buttonPrevious.setOpaque(true);
		buttonPrevious.setFocusPainted(false); // Remove focus ring
		buttonPrevious.setBorderPainted(false);
		buttonPrevious.addActionListener(this); // Add action listener to previous button
		buttonPrevious.setSize(130,30); // 130 x 30 pixels
		buttonPrevious.setLocation(420,615); // x and y coordinates (420, 615)
		buttonPrevious.setVisible(false);	
		buttonPrevious.addMouseListener(this);
		menupanel.add(buttonPrevious); // Add previous button to the panel
		
		// Back button
		buttonBack = new JButton("Back");
		buttonBack.setFont(font_2);
		buttonBack.setForeground(Color.WHITE); // make text white
		buttonBack.setBackground(Color.BLACK); // set background to black
		buttonBack.setOpaque(true);
		buttonBack.setFocusPainted(false); // remove focus ring
		buttonBack.setBorderPainted(false);
		buttonBack.addActionListener(this); // Add action listener to previous button
		buttonBack.setSize(130,30); // 130 x 30 pixels
		buttonBack.setLocation(420,615); // x and y coordinates (420, 615)
		buttonBack.setVisible(false);	
		buttonBack.addMouseListener(this);
		menupanel.add(buttonBack);
		
		// Back button 2
		buttonBack2 = new JButton("Back");
		buttonBack2.setFont(font_2);
		buttonBack2.setForeground(Color.WHITE); // make text white
		buttonBack2.setBackground(Color.BLACK); // set background to black
		buttonBack2.setOpaque(true);
		buttonBack2.setFocusPainted(false); // remove focus ring
		buttonBack2.setBorderPainted(false);
		buttonBack2.addActionListener(this); // Add action listener to previous button
		buttonBack2.setSize(130,30); // 130 x 30 pixels
		buttonBack2.setLocation(420,615); // x and y coordinates (420, 615)
		buttonBack2.setVisible(false);	
		buttonBack2.addMouseListener(this);
		menupanel.add(buttonBack2);
		
		// RulesOfGame - Text Area
		RulesOfGame = new JTextArea(strObjective+"\n"+"\n"+strObjectiveDescription+"\n"+"\n"+strNote);
		RulesOfGame.setFont(new Font("Times New Roman", Font.PLAIN, 15)); // Times New Roman font size 15
		RulesOfGame.setForeground(Color.WHITE); // Change text colour to white
		RulesOfGame.setBackground(new Color(20,19,19)); // Change background colour to custom black colour
		RulesOfGame.setSize(438,470); // 438 x 470 pixels
		RulesOfGame.setLocation(420,137); // x and y coordinates (420, 137)
		RulesOfGame.setEditable(false); // Prevent user from editing the text area
		RulesOfGame.setVisible(false);
		RulesOfGame.setLineWrap(true); // Set to true. The lines will be wrapped if they are too long to fit within the allocated width of the textarea
		menupanel.add(RulesOfGame); // Add textarea to the panel
		
		ssm = new SuperSocketMaster(1337, this);
		System.out.println("My server ip is: "+ssm.getMyAddress());
		ssm.connect();
		
		labelIP = new JLabel(ssm.getMyAddress());
		labelIP.setLocation(550, 300);
		labelIP.setSize(200,50);
		labelIP.setFont(new Font("Helvetica", Font.BOLD, 30)); // Helvetica text font size 30, bolded
		labelIP.setForeground(Color.WHITE); // Make text white
		labelIP.setVisible(false);
		menupanel.add(labelIP);
		
		// Chat Box
		menupanel.add(ChatBox);
		menupanel.add(ChatBoxScroll);
		menupanel.add(SendMessage);
		
		ChatBoxScroll.setSize(300, 500);
		ChatBoxScroll.setLocation(900,100);
		
		
		SendMessage.setSize(300,100);
		SendMessage.setLocation(900, 600);
		SendMessage.addActionListener(this);
		
		////////////////////
		// Frame
		////////////////////
		
		menuframe = new JFrame("Game Of The Generals Marvel Edition");
		boardframe = new JFrame("Board");
		
		// Puts the panel inside the frame.
		menuframe.setContentPane(menupanel);
		boardframe.setContentPane(boardpanel);
		
		// Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
		menuframe.pack();
		boardframe.pack();
				
		// Exit Java program when the frame is closed.
		menuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boardframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Disables window resizing.
		menuframe.setResizable(false);
		boardframe.setResizable(false);
		
		// Shows the frame.
		menuframe.setVisible(true);
		boardframe.setVisible(true);
	}
	
	// Main Method
	public static void main(String[] args){
		new MarvelGameOfTheGenerals();
	}
}
