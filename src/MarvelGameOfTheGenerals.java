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
* @version 1.0
*/
public class MarvelGameOfTheGenerals implements ActionListener, KeyListener, MouseListener{
	// Properties
	JFrame menuframe;
	JFrame boardframe;
	MenuPanel menupanel;
	BoardPanel boardpanel;
	ChatPanel chatpanel;
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
	// Used to setup game (Networking)
	JLabel labelIP;
	JButton buttonHost;
	JButton buttonClient;
	JTextField EnterIP;
	JButton buttonBack;
	JButton buttonBack2;
	JButton buttonEnter2;
	JButton buttonEnter;
	// High scores
	
	/*
	MarvelGOTGHighscores scores = new MarvelGOTGHighscores();
	String highscores[][] = scores.readMapArray();
	HighscoresCount numberscores = new HighscoresCount();
	int intScoresCount = numberscores.ScoresCount();
	JLabel highscoreslabel[];
	*/
	JTextArea highscoresarea;
	
	// Used to setup board
	JButton button[][] = new JButton[8][9];
	String strArray[][] = new String[8][9];
	int intRow;
	int intCol;
	boolean blnMoveUp = false;
	boolean blnMoveDown = false;
	boolean blnMoveRight = false;
	boolean blnMoveLeft = false;
	static int checkPotentialMoveCounter = 0; // This means that the checkPotentialMove methods have not been called yet
	// Chat (Networking)
	JTextArea ChatBox = new JTextArea();
	JScrollPane ChatScroll = new JScrollPane(ChatBox); 
	JTextField ChatMessage = new JTextField();
	// Help Menu - Instructions (String)
	int intHelpPage = 0; // Set initial value of Help Menu Pages to 0
	// Objective Instruction
	String strObjective = "OBJECTIVE";
	String strObjectiveDescription = "The Objective of the game is to eliminate the nexus of your opponent";
	// Pieces Information
	String strNote = "NOTE: If both characters are of equal power, BOTH are eliminated.";
	String strPiecesNote = "Consult the github website to learn about the rules of the pieces.";
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
	@Override
	public void actionPerformed(ActionEvent evt){ // Action listener
		if(evt.getSource() == timer){
			// Repaint the panel based on timer (60 fps).
			menupanel.repaint();
			boardpanel.repaint();
		}
		if(evt.getSource() == buttonPlay){
			// hide menu JButtons
			buttonPlay.setVisible(false);
			buttonScores.setVisible(false);
			buttonHelp.setVisible(false);
			buttonQuit.setVisible(false);
			// show necessary JButtons
			buttonHost.setVisible(true);
			buttonClient.setVisible(true);
			buttonBack2.setVisible(true);
			buttonEnter.setVisible(false);
		}
		if(evt.getSource() == buttonHost){
			labelIP.setVisible(true); // show host IP address
			buttonHost.setVisible(false); // hide host JButton
			buttonClient.setVisible(false); // hide client JButton
			buttonBack.setVisible(true); // show back JButton
			buttonEnter.setVisible(true);
		}
		if(evt.getSource() == buttonClient){
			buttonHost.setVisible(false); // hide host JButton
			buttonClient.setVisible(false); // hide client JButton
			buttonBack.setVisible(true); // show back JButton
			EnterIP.setVisible(true); // show EnterIP JTextField
			buttonEnter2.setVisible(true);
		}
		// client
		if(evt.getSource() == buttonEnter2){
			String strEnterIP = EnterIP.getText();
			System.out.println(strEnterIP);
			
			ssm = new SuperSocketMaster(strEnterIP, 1337, this);
			ssm.connect();
			//System.out.println("Server joined");
			
			ChatBox.setVisible(true);
			ChatScroll.setVisible(true);
			ChatMessage.setVisible(true);
			boardframe.setVisible(true);
			menuframe.setVisible(false);
			buttonEnter2.setVisible(false);
			buttonBack.setVisible(false);
			buttonBack2.setVisible(false);
			EnterIP.setVisible(false);
		}
		// host
		if(evt.getSource() == buttonEnter){
			buttonHost.setVisible(false);
			buttonClient.setVisible(false);
			buttonBack.setVisible(false);
			buttonEnter.setVisible(false);
			buttonBack2.setVisible(false);
			menuframe.setVisible(false);
			boardframe.setVisible(true);
			ChatBox.setVisible(true);
			ChatScroll.setVisible(true);
			ChatMessage.setVisible(true);
			
			ssm.connect();
		
		}
		
		if(evt.getSource() == ChatMessage){
			
			String strData;
			strData = ssm.readText();
			
			ssm.sendText("Sent: " + ChatMessage.getText());

			ChatBox.append("Username: " + ChatMessage.getText() + "\n");

			ChatMessage.setText("");
			
		}
		
		if(evt.getSource() == ssm){
			String strData;
			strData = ssm.readText();
	
			ssm.sendText("Sent: " + ChatMessage.getText());
			ChatBox.append("Opponent: " + strData + "\n");
			ChatMessage.setText("");
					
		}	
		if(evt.getSource() == buttonBack){
			buttonBack.setVisible(false);
			buttonHost.setVisible(true);
			buttonClient.setVisible(true);
			labelIP.setVisible(false);
			EnterIP.setVisible(false);
			buttonEnter.setVisible(false);
			buttonEnter.setVisible(false);
			buttonEnter2.setVisible(false);
		}
		if(evt.getSource() == buttonBack2){
			buttonBack2.setVisible(false);
			buttonPlay.setVisible(true);
			buttonScores.setVisible(true);
			buttonHelp.setVisible(true);
			buttonQuit.setVisible(true);
			buttonHost.setVisible(false);
			buttonClient.setVisible(false);
			buttonBack.setVisible(false);
			buttonEnter.setVisible(false);
			buttonEnter.setVisible(false);
			buttonEnter2.setVisible(false);
		}
		if(evt.getSource() == buttonScores){
			buttonPlay.setVisible(false);
			buttonScores.setVisible(false);
			buttonHelp.setVisible(false);
			buttonQuit.setVisible(false);
			highscoresarea.setVisible(true);
		}
		if(evt.getSource() == buttonHelp){  //User selects Help Button
			intHelpPage = intHelpPage + 1; // Plus 1 to make the 1st page of help menu appear on screen
			RulesOfGame.setText(strObjective+"\n"+"\n"+strObjectiveDescription+"\n"+"\n"+strNote+"\n"+"\n"+strPiecesNote); 
			buttonPlay.setVisible(false);
			buttonScores.setVisible(false);
			buttonHelp.setVisible(false);
			buttonQuit.setVisible(false);
			RulesOfGame.setVisible(true);
			buttonMainMenu.setVisible(true);
			buttonNext.setVisible(true);	
			buttonEnter.setVisible(false);
		}
		if(evt.getSource() == buttonMainMenu){ // User selects Main Menu Button in Help menu
			intHelpPage = 0; // Return back to main menu
			buttonPlay.setVisible(true);
			buttonScores.setVisible(true);
			buttonHelp.setVisible(true);
			buttonQuit.setVisible(true);
			RulesOfGame.setVisible(false);
			buttonMainMenu.setVisible(false);
			buttonNext.setVisible(false);
			buttonPrevious.setVisible(false);
			buttonEnter.setVisible(false);
		}
		if(evt.getSource() == buttonNext){ // User selects next button
			intHelpPage = intHelpPage + 1; // Goes to next page of text area (RulesOfGame)
			RulesOfGame.setText("");

			// 1st page of Help Menu
			if(intHelpPage == 1){
				buttonPrevious.setVisible(false); // set to false because there is no previous pages to go back to
				buttonNext.setVisible(true); // Allow user to go next page
				RulesOfGame.setText(strObjective+"\n"+"\n"+strObjectiveDescription+"\n"+"\n"+strNote+"\n"+"\n"+strPiecesNote); // All the instructions(String) for the first page
				
				
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
		if(evt.getSource() == buttonPrevious){
			intHelpPage = intHelpPage - 1;
				if(intHelpPage == 1){
					buttonPrevious.setVisible(false);
					buttonNext.setVisible(true);
					RulesOfGame.setText(strObjective+"\n"+"\n"+strObjectiveDescription+"\n"+"\n"+strNote+"\n"+"\n"+strPiecesNote);					
					
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
		if(evt.getSource() == buttonQuit){
				System.exit(0);
		}
		/*
		for(intRow = 0; intRow < 8; intRow++){
			for(intCol = 0; intCol < 9; intCol++){
				if(evt.getSource() == button[intRow][intCol]){
					checkPotentialMoveDown(intRow, intCol);
					checkPotentialMoveLeft(intRow, intCol);
					checkPotentialMoveRight(intRow, intCol);
					checkPotentialMoveUp(intRow, intCol);
				}
			}
		}
		*/
		if(evt.getSource() == button[0][0]){
			checkPotentialMoveDown(0, 0);
			checkPotentialMoveLeft(0, 0);
			checkPotentialMoveRight(0, 0);
			checkPotentialMoveUp(0, 0);
		}
		if(evt.getSource() == button[1][0]){
			checkPotentialMoveDown(1, 0);
			checkPotentialMoveLeft(1, 0);
			checkPotentialMoveRight(1, 0);
			checkPotentialMoveUp(1, 0);
		}
		if(evt.getSource() == button[2][0]){
			checkPotentialMoveDown(2, 0);
			checkPotentialMoveLeft(2, 0);
			checkPotentialMoveRight(2, 0);
			checkPotentialMoveUp(2, 0);
		}
		if(evt.getSource() == button[3][0]){
			checkPotentialMoveDown(3, 0);
			checkPotentialMoveLeft(3, 0);
			checkPotentialMoveRight(3, 0);
			checkPotentialMoveUp(3, 0);
		}
		if(evt.getSource() == button[4][0]){
			checkPotentialMoveDown(4, 0);
			checkPotentialMoveLeft(4, 0);
			checkPotentialMoveRight(4, 0);
			checkPotentialMoveUp(4, 0);
		}
		if(evt.getSource() == button[5][0]){
			checkPotentialMoveDown(5, 0);
			checkPotentialMoveLeft(5, 0);
			checkPotentialMoveRight(5, 0);
			checkPotentialMoveUp(5, 0);
		}
		if(evt.getSource() == button[6][0]){
			checkPotentialMoveDown(6, 0);
			checkPotentialMoveLeft(6, 0);
			checkPotentialMoveRight(6, 0);
			checkPotentialMoveUp(6, 0);
		}
		if(evt.getSource() == button[7][0]){
			checkPotentialMoveDown(7, 0);
			checkPotentialMoveLeft(7, 0);
			checkPotentialMoveRight(7, 0);
			checkPotentialMoveUp(7, 0);
		}
		if(evt.getSource() == button[0][1]){
			checkPotentialMoveDown(0, 1);
			checkPotentialMoveLeft(0, 1);
			checkPotentialMoveRight(0, 1);
			checkPotentialMoveUp(0, 1);
		}
		if(evt.getSource() == button[1][1]){
			checkPotentialMoveDown(1, 1);
			checkPotentialMoveLeft(1, 1);
			checkPotentialMoveRight(1, 1);
			checkPotentialMoveUp(1, 1);
		}
		if(evt.getSource() == button[2][1]){
			checkPotentialMoveDown(2, 1);
			checkPotentialMoveLeft(2, 1);
			checkPotentialMoveRight(2, 1);
			checkPotentialMoveUp(2, 1);
		}
		if(evt.getSource() == button[3][1]){
			checkPotentialMoveDown(3, 1);
			checkPotentialMoveLeft(3, 1);
			checkPotentialMoveRight(3, 1);
			checkPotentialMoveUp(3, 1);
		}
		if(evt.getSource() == button[4][1]){
			checkPotentialMoveDown(4, 1);
			checkPotentialMoveLeft(4, 1);
			checkPotentialMoveRight(4, 1);
			checkPotentialMoveUp(4, 1);
		}
		if(evt.getSource() == button[5][1]){
			checkPotentialMoveDown(5, 1);
			checkPotentialMoveLeft(5, 1);
			checkPotentialMoveRight(5, 1);
			checkPotentialMoveUp(5, 1);
		}
		if(evt.getSource() == button[6][1]){
			checkPotentialMoveDown(6, 1);
			checkPotentialMoveLeft(6, 1);
			checkPotentialMoveRight(6, 1);
			checkPotentialMoveUp(6, 1);
		}
		if(evt.getSource() == button[7][1]){
			checkPotentialMoveDown(7, 1);
			checkPotentialMoveLeft(7, 1);
			checkPotentialMoveRight(7, 1);
			checkPotentialMoveUp(7, 1);
		}
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
		}
		if(evt.getSource() == buttonScores){
			buttonScores.setBackground(Color.RED);
		}
		if(evt.getSource() == buttonHelp){
			buttonHelp.setBackground(Color.RED);
		}
		if(evt.getSource() == buttonQuit){
			buttonQuit.setBackground(Color.RED);
		}
		if(evt.getSource() == buttonHost){
			buttonHost.setBackground(Color.RED);
		}
		if(evt.getSource() == buttonClient){
			buttonClient.setBackground(Color.RED);
		}
		if(evt.getSource() == buttonBack){
			buttonBack.setBackground(Color.RED);
		}
		if(evt.getSource() == buttonBack2){
			buttonBack2.setBackground(Color.RED);
		}
		if(evt.getSource() == buttonMainMenu){
			buttonMainMenu.setBackground(Color.RED);
		}
		if(evt.getSource() == buttonNext){
			buttonNext.setBackground(Color.RED);
		}
		if(evt.getSource() == buttonPrevious){
			buttonPrevious.setBackground(Color.RED);
		}
		if(evt.getSource() == buttonEnter){
			buttonEnter.setBackground(Color.RED);
		}
		if(evt.getSource() == buttonEnter2){
			buttonEnter2.setBackground(Color.RED);
		}
	}
		
	@Override
	public void mouseExited(MouseEvent evt){ // Called after the cursor exits the bounds of the listened-to component.
		if(evt.getSource() == buttonPlay){
			buttonPlay.setBackground(Color.BLACK);
		}
		if(evt.getSource() == buttonScores){
			buttonScores.setBackground(Color.BLACK);
		}
		if(evt.getSource() == buttonHelp){
			buttonHelp.setBackground(Color.BLACK);
		}
		if(evt.getSource() == buttonQuit){
			buttonQuit.setBackground(Color.BLACK);
		}
		if(evt.getSource() == buttonHost){
			buttonHost.setBackground(Color.BLACK);
		}
		if(evt.getSource() == buttonClient){
			buttonClient.setBackground(Color.BLACK);
		}
		if(evt.getSource() == buttonMainMenu){
			buttonMainMenu.setBackground(Color.BLACK);
		}
		if(evt.getSource() == buttonNext){
			buttonNext.setBackground(Color.BLACK);
		}
		if(evt.getSource() == buttonPrevious){
			buttonPrevious.setBackground(Color.BLACK);
		}
		if(evt.getSource() == buttonBack){
			buttonBack.setBackground(Color.BLACK);
		}
		if(evt.getSource() == buttonBack2){
			buttonBack2.setBackground(Color.BLACK);
		}
		if(evt.getSource() == buttonEnter){
			buttonEnter.setBackground(Color.BLACK);
		}
		if(evt.getSource() == buttonEnter2){
			buttonEnter2.setBackground(Color.BLACK);
		}
	}
			
	@Override
	public void mousePressed(MouseEvent evt){ // Called after the user presses a mouse button while the cursor is over the listened-to component.
	}
			
	@Override
	public void mouseReleased(MouseEvent evt){ // Called after the user releases a mouse button after a mouse press over the listened-to component.
	}
	
	public void checkPotentialMoveDown(int intRow, int intCol){
		checkPotentialMoveCounter++;
		blnMoveDown = false;
		if(button[intRow][intCol].getIcon() != null && intRow+1 < 8){
			if(button[intRow+1][intCol].getIcon() == null){
				button[intRow+1][intCol].setBackground(Color.RED);
				blnMoveDown = true;
			}
		}
		System.out.println("Move down: "+blnMoveDown);
		System.out.println("");
	}
	
	public void checkPotentialMoveUp(int intRow, int intCol){
		blnMoveUp = false;
		if(button[intRow][intCol].getIcon() != null && intRow-1 > 0){
			if(button[intRow-1][intCol].getIcon() == null){
				button[intRow-1][intCol].setBackground(Color.RED);
				blnMoveUp = true;
			}
		}
		System.out.println("Move up: "+blnMoveUp);
		System.out.println("");
	}
	
	public void checkPotentialMoveLeft(int intRow, int intCol){
		blnMoveLeft = false;
		if(button[intRow][intCol].getIcon() != null && intCol-1 > 0){
			if(button[intRow][intCol-1].getIcon() == null){
				button[intRow][intCol-1].setBackground(Color.RED);
				blnMoveLeft = true;
			}
		}
		System.out.println("Move left: "+blnMoveLeft);
		System.out.println("");
	}
	
	public void checkPotentialMoveRight(int intRow, int intCol){
		blnMoveRight = false;
		if(button[intRow][intCol].getIcon() != null && intCol+1 < 9){
			if(button[intRow][intCol+1].getIcon() == null){
				button[intRow][intCol+1].setBackground(Color.RED);
				blnMoveRight = true;				
			}
		}
		System.out.println("Move right: "+blnMoveRight);
		System.out.println("");
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
		
		chatpanel = new ChatPanel();
		chatpanel.setLayout(null);
		chatpanel.setPreferredSize(new Dimension(470, 720));
		
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
				button[intRow][intCol].addMouseListener(this);
				boardpanel.add(button[intRow][intCol]);
			}
		}
		strArray[AntMan.intY][AntMan.intX] = "antman";
		strArray[IronMan.intY][IronMan.intX] = "ironman";
		strArray[Hulk.intY][Hulk.intX] = "hulk";
		strArray[DrStrange.intY][DrStrange.intX] = "drstrange";
		strArray[CaptainAmerica.intY][CaptainAmerica.intX] = "captainamerica";
		strArray[BlackPanther.intY][BlackPanther.intX] = "blackpanther";
		strArray[Spiderman.intY][Spiderman.intX] = "spiderman";
		strArray[Wanda.intY][Wanda.intX] = "wanda";
		strArray[StarLord.intY][StarLord.intX] = "starlord";
		strArray[Hawkeye.intY][Hawkeye.intX] = "hawkeye";
		strArray[Thor.intY][Thor.intX] = "thor";
		strArray[Vision.intY][Vision.intX] = "vision";
		strArray[ShieldAgent1.intY][ShieldAgent1.intX] = "shieldagent1";
		strArray[BlueNexus.intY][BlueNexus.intX] = "bluenexus";
		strArray[Loki1.intY][Loki1.intX] = "loki1";
		strArray[Loki2.intY][Loki2.intX] = "loki2";
		strArray[ShieldAgent2.intY][ShieldAgent2.intX] = "shieldagent2";
		strArray[ShieldAgent3.intY][ShieldAgent3.intX] = "shieldagent3";
		strArray[ShieldAgent4.intY][ShieldAgent4.intX] = "shieldagent4";
		strArray[ShieldAgent5.intY][ShieldAgent5.intX] = "shieldagent5";
		strArray[ShieldAgent6.intY][ShieldAgent6.intX] = "shieldagent6";
		
		strArray[HydraSoldier4.intY][HydraSoldier4.intX] = "hydrasoldier4";
		strArray[HydraSoldier5.intY][HydraSoldier5.intX] = "hydrasoldier5";
		strArray[HydraSoldier6.intY][HydraSoldier6.intX] = "hydrasoldier6";
		strArray[Thanos.intY][Thanos.intX] = "thanos";
		strArray[Ultron.intY][Ultron.intX] = "ultron";
		strArray[Dormammu.intY][Dormammu.intX] = "dormammu";
		strArray[DrDoom.intY][DrDoom.intX] = "drdoom";
		strArray[RedSkull.intY][RedSkull.intX] = "redskull";
		strArray[KillMonger.intY][KillMonger.intX] = "killmonger";
		strArray[Venom.intY][Venom.intX] = "venom";
		strArray[DocOck.intY][DocOck.intX] = "docock";
		strArray[Ronan.intY][Ronan.intX] = "ronan";
		strArray[Modok.intY][Modok.intX] = "modok";
		strArray[Yellowjacket.intY][Yellowjacket.intX] = "yellowjacket";
		strArray[Punisher.intY][Punisher.intX] = "punisher";
		strArray[Hela1.intY][Hela1.intX] = "hela1";
		strArray[HydraSoldier1.intY][HydraSoldier1.intX] = "hydrasoldier1";
		strArray[RedNexus.intY][RedNexus.intX] = "rednexus";
		strArray[Hela2.intY][Hela2.intX] = "hela2";
		strArray[HydraSoldier2.intY][HydraSoldier2.intX] = "hydrasoldier2";
		strArray[HydraSoldier3.intY][HydraSoldier3.intX] = "hydrasoldier3";
		
		if(strArray[AntMan.intY][AntMan.intX].equals("antman")){
			button[AntMan.intY][AntMan.intX].setIcon(new ImageIcon(boardpanel.antman));
		}if(strArray[IronMan.intY][IronMan.intX].equals("ironman")){
			button[IronMan.intY][IronMan.intX].setIcon(new ImageIcon(boardpanel.ironman));
		}if(strArray[Hulk.intY][Hulk.intX].equals("hulk")){
			button[Hulk.intY][Hulk.intX].setIcon(new ImageIcon(boardpanel.hulk));
		}if(strArray[DrStrange.intY][DrStrange.intX].equals("drstrange")){
			button[DrStrange.intY][DrStrange.intX].setIcon(new ImageIcon(boardpanel.drstrange));
		}if(strArray[CaptainAmerica.intY][CaptainAmerica.intX].equals("captainamerica")){
			button[CaptainAmerica.intY][CaptainAmerica.intX].setIcon(new ImageIcon(boardpanel.captainamerica));
		}if(strArray[BlackPanther.intY][BlackPanther.intX].equals("blackpanther")){
			button[BlackPanther.intY][BlackPanther.intX].setIcon(new ImageIcon(boardpanel.blackpanther));
		}if(strArray[Spiderman.intY][Spiderman.intX].equals("spiderman")){
			button[Spiderman.intY][Spiderman.intX].setIcon(new ImageIcon(boardpanel.spiderman));
		}if(strArray[Wanda.intY][Wanda.intX].equals("wanda")){
			button[Wanda.intY][Wanda.intX].setIcon(new ImageIcon(boardpanel.wanda));
		}if(strArray[StarLord.intY][StarLord.intX].equals("starlord")){
			button[StarLord.intY][StarLord.intX].setIcon(new ImageIcon(boardpanel.starlord));
		}if(strArray[Hawkeye.intY][Hawkeye.intX].equals("hawkeye")){
			button[Hawkeye.intY][Hawkeye.intX].setIcon(new ImageIcon(boardpanel.hawkeye));
		}if(strArray[Thor.intY][Thor.intX].equals("thor")){
			button[Thor.intY][Thor.intX].setIcon(new ImageIcon(boardpanel.thor));
		}if(strArray[Vision.intY][Vision.intX].equals("vision")){
			button[Vision.intY][Vision.intX].setIcon(new ImageIcon(boardpanel.vision));
			
		}if(strArray[Loki1.intY][Loki1.intX].equals("loki1")){
			button[Loki1.intY][Loki1.intX].setIcon(new ImageIcon(boardpanel.loki1));
		}if(strArray[ShieldAgent1.intY][ShieldAgent1.intX].equals("shieldagent1")){
			button[ShieldAgent1.intY][ShieldAgent1.intX].setIcon(new ImageIcon(boardpanel.shieldagent1));
		}if(strArray[BlueNexus.intY][BlueNexus.intX].equals("bluenexus")){
			button[BlueNexus.intY][BlueNexus.intX].setIcon(new ImageIcon(boardpanel.bluenexus));
		}if(strArray[Loki1.intY][Loki2.intX].equals("loki2")){
			button[Loki1.intY][Loki2.intX].setIcon(new ImageIcon(boardpanel.loki2));
			
		}if(strArray[ShieldAgent2.intY][ShieldAgent1.intX].equals("shieldagent2")){
			button[ShieldAgent2.intY][ShieldAgent1.intX].setIcon(new ImageIcon(boardpanel.shieldagent2));
		}if(strArray[ShieldAgent3.intY][ShieldAgent3.intX].equals("shieldagent3")){
			button[ShieldAgent3.intY][ShieldAgent3.intX].setIcon(new ImageIcon(boardpanel.shieldagent3));
		}if(strArray[ShieldAgent4.intY][ShieldAgent4.intX].equals("shieldagent4")){
			button[ShieldAgent4.intY][ShieldAgent4.intX].setIcon(new ImageIcon(boardpanel.shieldagent4));
		}if(strArray[ShieldAgent5.intY][ShieldAgent5.intX].equals("shieldagent5")){
			button[ShieldAgent5.intY][ShieldAgent5.intX].setIcon(new ImageIcon(boardpanel.shieldagent5));
		}if(strArray[ShieldAgent6.intY][ShieldAgent6.intX].equals("shieldagent6")){
			button[ShieldAgent6.intY][ShieldAgent6.intX].setIcon(new ImageIcon(boardpanel.shieldagent6));
		}
		// villians
		if(strArray[Modok.intY][Modok.intX].equals("modok")){
			button[Modok.intY][Modok.intX].setIcon(new ImageIcon(boardpanel.modok));
		}if(strArray[Yellowjacket.intY][Yellowjacket.intX].equals("yellowjacket")){
			button[Yellowjacket.intY][Yellowjacket.intX].setIcon(new ImageIcon(boardpanel.yellowjacket));
		}if(strArray[Punisher.intY][Punisher.intX].equals("punisher")){
			button[Punisher.intY][Punisher.intX].setIcon(new ImageIcon(boardpanel.punisher));
		
		}if(strArray[Hela1.intY][Hela1.intX].equals("hela1")){
			button[Hela1.intY][Hela1.intX].setIcon(new ImageIcon(boardpanel.hela1));
			
			
			
		}if(strArray[HydraSoldier1.intY][HydraSoldier1.intX].equals("hydrasoldier1")){
			button[HydraSoldier1.intY][HydraSoldier1.intX].setIcon(new ImageIcon(boardpanel.hydrasoldier1));
		}if(strArray[RedNexus.intY][RedNexus.intX].equals("rednexus")){
			button[RedNexus.intY][RedNexus.intX].setIcon(new ImageIcon(boardpanel.rednexus));
		}if(strArray[Hela2.intY][Hela2.intX].equals("hela2")){
			button[Hela2.intY][Hela2.intX].setIcon(new ImageIcon(boardpanel.hela2));
		}if(strArray[HydraSoldier2.intY][HydraSoldier2.intX].equals("hydrasoldier2")){
			button[HydraSoldier2.intY][HydraSoldier2.intX].setIcon(new ImageIcon(boardpanel.hydrasoldier2));
		}if(strArray[HydraSoldier3.intY][HydraSoldier3.intX].equals("hydrasoldier3")){
			button[HydraSoldier3.intY][HydraSoldier3.intX].setIcon(new ImageIcon(boardpanel.hydrasoldier3));
		
		}if(strArray[Thanos.intY][Thanos.intX].equals("thanos")){
			button[Thanos.intY][Thanos.intX].setIcon(new ImageIcon(boardpanel.thanos));
		}if(strArray[Ultron.intY][Ultron.intX].equals("ultron")){
			button[Ultron.intY][Ultron.intX].setIcon(new ImageIcon(boardpanel.ultron));
		}if(strArray[Dormammu.intY][Dormammu.intX].equals("dormammu")){
			button[Dormammu.intY][Dormammu.intX].setIcon(new ImageIcon(boardpanel.dormammu));
		}if(strArray[DrDoom.intY][DrDoom.intX].equals("drdoom")){
			button[DrDoom.intY][DrDoom.intX].setIcon(new ImageIcon(boardpanel.drdoom));
		}if(strArray[RedSkull.intY][RedSkull.intX].equals("redskull")){
			button[RedSkull.intY][RedSkull.intX].setIcon(new ImageIcon(boardpanel.redskull));
		}if(strArray[KillMonger.intY][KillMonger.intX].equals("killmonger")){
			button[KillMonger.intY][KillMonger.intX].setIcon(new ImageIcon(boardpanel.killmonger));
		}if(strArray[Venom.intY][Venom.intX].equals("venom")){
			button[Venom.intY][Venom.intX].setIcon(new ImageIcon(boardpanel.venom));
		}if(strArray[DocOck.intY][DocOck.intX].equals("docock")){
			button[DocOck.intY][DocOck.intX].setIcon(new ImageIcon(boardpanel.docock));
		}if(strArray[Ronan.intY][Ronan.intX].equals("ronan")){
			button[Ronan.intY][Ronan.intX].setIcon(new ImageIcon(boardpanel.ronan));
		}if(strArray[HydraSoldier4.intY][HydraSoldier4.intX].equals("hydrasoldier4")){
			button[HydraSoldier4.intY][HydraSoldier4.intX].setIcon(new ImageIcon(boardpanel.hydrasoldier4));
		}if(strArray[HydraSoldier5.intY][HydraSoldier5.intX].equals("hydrasoldier5")){
			button[HydraSoldier5.intY][HydraSoldier5.intX].setIcon(new ImageIcon(boardpanel.hydrasoldier5));
		}if(strArray[HydraSoldier6.intY][HydraSoldier6.intX].equals("hydrasoldier6")){
			button[HydraSoldier6.intY][HydraSoldier6.intX].setIcon(new ImageIcon(boardpanel.hydrasoldier6));
		}
		
		
		// Custom fonts
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
		buttonBack.setLocation(575,615); // x and y coordinates (575, 615)
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
		buttonBack2.setLocation(575,615); // x and y coordinates (575, 615)
		buttonBack2.setVisible(false);	
		buttonBack2.addMouseListener(this);
		menupanel.add(buttonBack2);
		
		// Enter Button (enter into game HOST)
		buttonEnter = new JButton("Enter");
		buttonEnter.setFont(font_2);
		buttonEnter.setForeground(Color.WHITE); // make text white
		buttonEnter.setBackground(Color.BLACK); // set background to black
		buttonEnter.setOpaque(true);
		buttonEnter.setFocusPainted(false); // remove focus ring
		buttonEnter.setBorderPainted(false);
		buttonEnter.addActionListener(this); // Add action listener to previous button
		buttonEnter.setSize(130,30); // 130 x 30 pixels
		buttonEnter.setLocation(900,325); // x and y coordinates (900, 325)
		buttonEnter.setVisible(false);	
		buttonEnter.addMouseListener(this);
		menupanel.add(buttonEnter);
		
		// Enter Button (enter into game CLIENT)
		buttonEnter2 = new JButton("Enter");
		buttonEnter2.setFont(font_2);
		buttonEnter2.setForeground(Color.WHITE); // make text white
		buttonEnter2.setBackground(Color.BLACK); // set background to black
		buttonEnter2.setOpaque(true);
		buttonEnter2.setFocusPainted(false); // remove focus ring
		buttonEnter2.setBorderPainted(false);
		buttonEnter2.addActionListener(this); // Add action listener to previous button
		buttonEnter2.setSize(130,30); // 130 x 30 pixels
		buttonEnter2.setLocation(900,325); // x and y coordinates (900, 325)
		buttonEnter2.setVisible(false);	
		buttonEnter2.addMouseListener(this);
		menupanel.add(buttonEnter2);
		
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
		
		// Networking 
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
		
		
		// Highscores
		highscoresarea = new JTextArea();
		highscoresarea.setSize(438,470); // 438 x 470 pixels
		highscoresarea.setLocation(420,137); // x and y coordinates (420, 137)
		menupanel.add(highscoresarea);
		highscoresarea.setVisible(false);
		highscoresarea.setForeground(Color.WHITE); // Change text colour to white
		highscoresarea.setBackground(new Color(20,19,19)); // Change background colour to custom black colour
		highscoresarea.setEditable(false); // Prevent user from editing the text area
		highscoresarea.setLineWrap(true); // Set to true. The lines will be wrapped if they are too long to fit within the allocated width of the textarea
		
		// Chat Box
		chatpanel.add(ChatBox);
		chatpanel.add(ChatScroll);
		chatpanel.add(ChatMessage);
		
		ChatBox.setSize(300,500);
		ChatBox.setLocation(120,50);
		ChatBox.setVisible(false);
		ChatBox.setEditable(false);
		
		
		ChatScroll.setSize(300, 500);
		ChatScroll.setLocation(120,50);
		ChatScroll.setVisible(false);
		
		ChatMessage.setSize(300,100);
		ChatMessage.setLocation(120, 550);
		ChatMessage.addActionListener(this);
		ChatMessage.setVisible(false);
		
		////////////////////
		// Frame
		////////////////////
		
		menuframe = new JFrame("Game Of The Generals Marvel Edition");
		boardframe = new JFrame("Board");
		
		// Puts the panel inside the frame.
		menuframe.setContentPane(menupanel);
		boardframe.add(boardpanel, BorderLayout.WEST);
		boardframe.add(chatpanel, BorderLayout.EAST);
		
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
		boardframe.setVisible(false);
	}
	
	// Main Method
	public static void main(String[] args){
		new MarvelGameOfTheGenerals();
	}
}
