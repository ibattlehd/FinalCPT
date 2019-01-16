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
	JFrame menuframe;
	JFrame boardframe;
	MenuPanel menupanel;
	BoardPanel boardpanel;
	Timer timer;
	
	// Main Menu Buttons
	JButton buttonPlay;
	JButton buttonScores;
	JButton buttonHelp;
	JButton buttonQuit;
	
	// Help Menu
	JButton buttonMainMenu;
	JButton buttonNext;
	JButton buttonPrevious;
	JTextArea RulesOfGame;
	
	JLabel labelIP;
	SuperSocketMaster ssm;
	JButton buttonHost;
	JButton buttonClient;
	JTextField EnterIP;
	JButton buttonBack;
	JButton buttonBack2;

	JButton button[][] = new JButton[8][9];
	int intRow;
	int intCol;

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
	
	// Methods
	@Override
	public void actionPerformed(ActionEvent evt){ // Action listener
		if(evt.getSource() == timer){
			menupanel.repaint(); // Repaint the panel based on timer (60 fps).
		}
		else if(evt.getSource() == buttonPlay){
			buttonPlay.setVisible(false);
			buttonScores.setVisible(false);
			buttonHelp.setVisible(false);
			buttonQuit.setVisible(false);
			buttonHost.setVisible(true);
			buttonClient.setVisible(true);	
			buttonBack2.setVisible(true);
		}
		else if(evt.getSource() == buttonHost){
			labelIP.setVisible(true);
			buttonHost.setVisible(false);
			buttonClient.setVisible(false);
			buttonBack.setVisible(true);
		}
		else if(evt.getSource() == buttonClient){
			buttonHost.setVisible(false);
			buttonClient.setVisible(false);
			buttonBack.setVisible(true);
			EnterIP.setVisible(true);
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
			System.out.println("Pressed high scores");
		}
		else if(evt.getSource() == buttonHelp){  //User selects Help Button
			intHelpPage = intHelpPage + 1; // Plus 1 to make the 1st page of help menu appear on screen
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
		else if(evt.getSource() == button[0][0]){
			System.out.println("Pressed button 1 on board");
			for(intRow = 0; intRow < 8; intRow++){
				for(intCol = 0; intCol < 9; intCol++){
					if(button[intRow][intCol].getIcon() != null){
						button[intRow][intCol].setIcon(null);
					}
				}
			}
			/*
			if(button[0][1].getIcon() != null){
				button[0][1].setIcon(null);
			}
			*/
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
			/*
			if(button[0][0].getIcon() != null){
				button[0][0].setIcon(null);
			}
			*/
			button[0][1].setIcon(new ImageIcon(boardpanel.antman));
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
				button[intRow][intCol].addActionListener(this);
				
				button[intRow][intCol].setBackground(Color.BLACK);
				button[intRow][intCol].setFocusPainted(false);
				button[intRow][intCol].setOpaque(true);
				
				boardpanel.add(button[intRow][intCol]);
			}
		}
		
		button[0][0].setIcon(new ImageIcon(boardpanel.antman));
		
		buttonPlay = new JButton("Play"); // Play button
		buttonPlay.setSize(200, 50); // 200 pixels by 50 pixels
		buttonPlay.setLocation(540, 230); // x and y coordinates (540, 230)
		buttonPlay.setFocusPainted(false); // Remove focus ring
		buttonPlay.setBackground(Color.BLACK); // Set background to black
		buttonPlay.setOpaque(true);
		buttonPlay.setBorderPainted(false);
		buttonPlay.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Times New Roman text font size 20, bolded
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
		buttonScores.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Times New Roman text font size 20, bolded
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
		buttonHelp.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Times New Roman text font size 20, bolded
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
		buttonQuit.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Times New Roman text font size 20, bolded
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
		buttonHost.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Times New Roman text font size 20, bolded
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
		buttonClient.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Times New Roman text font size 20, bolded
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
		
		////////////////////
		// Help Menu JButtons
		////////////////////
		
		// Main Menu button
		buttonMainMenu = new JButton("Main Menu"); // Button to return to main menu
		buttonMainMenu.setFont(new Font("Times New Roman", Font.BOLD, 15)); // Times New Roman text font size 15, bolded
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
		buttonNext.setFont(new Font("Times New Roman", Font.BOLD, 15));  // Times New Roman text font size 15, bolded
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
		buttonPrevious.setFont(new Font("Times New Roman", Font.BOLD, 15)); // Times New Roman text font size 15, bolded
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
		buttonBack.setFont(new Font("Times New Roman", Font.BOLD, 20));
		buttonBack.setForeground(Color.WHITE); // make text white
		buttonBack.setBackground(Color.BLACK); // set background to black
		buttonBack.setOpaque(true);
		buttonBack.setFocusPainted(false);  // remove focus ring
		buttonBack.setBorderPainted(false);
		buttonBack.addActionListener(this); // Add action listener to previous button
		buttonBack.setSize(130,30); // 130 x 30 pixels
		buttonBack.setLocation(420,615); // x and y coordinates (420, 615)
		buttonBack.setVisible(false);	
		buttonBack.addMouseListener(this);
		menupanel.add(buttonBack);
		
		// Back button 2
		buttonBack2 = new JButton("Back");
		buttonBack2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		buttonBack2.setForeground(Color.WHITE); // make text white
		buttonBack2.setBackground(Color.BLACK); // set background to black
		buttonBack2.setOpaque(true);
		buttonBack2.setFocusPainted(false);  // remove focus ring
		buttonBack2.setBorderPainted(false);
		buttonBack2.addActionListener(this); // Add action listener to previous button
		buttonBack2.setSize(130,30); // 130 x 30 pixels
		buttonBack2.setLocation(420,615); // x and y coordinates (420, 615)
		buttonBack2.setVisible(false);	
		buttonBack2.addMouseListener(this);
		menupanel.add(buttonBack2);
		
		// RulesOfGame - Text Area
		RulesOfGame = new JTextArea(strObjective+"\n"+"\n"+strObjectiveDescription+"\n"+"\n"+strNote);
		RulesOfGame.setFont(new Font("Times New Roman", Font.PLAIN, 15)); // Times New Roman text font size 15
		RulesOfGame.setForeground(Color.WHITE); // Change text colour to black
		RulesOfGame.setBackground(new Color(214,0,0)); // Change background colour to custom red colour
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
		labelIP.setFont(new Font("Times New Roman", Font.BOLD, 30)); // Times New Roman text font size 30, bolded
		labelIP.setForeground(Color.WHITE); // Make text white
		labelIP.setVisible(false);
		menupanel.add(labelIP);
		
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
		//frame.setResizable(false);
		
		// Shows the frame.
		menuframe.setVisible(true);
		boardframe.setVisible(true);
	}
	
	////////////////////
	// GAME PANEL
	////////////////////
	public class MenuPanel extends JPanel{
		// Properties
		int intPage = 1;
		BufferedImage mainmenu;
		BufferedImage antman;
		
		// Methods
		public void paintComponent(Graphics g){ // Overriding JPanel's paintComponent method
			if(intPage == 1){
				g.drawImage(mainmenu, 0, 0, null);
			}
		}
		
		// Constructor
		public MenuPanel(){
			super();
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
	
	public class BoardPanel extends JPanel{
		// Properties
		BufferedImage antman;
		
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
			}
		}
	}
	
	
	// Main Method
	public static void main(String[] args){
		new MarvelGameOfTheGenerals();
	}
}
