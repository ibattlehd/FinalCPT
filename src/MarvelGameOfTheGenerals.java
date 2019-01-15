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
	GamePanel gamepanel;
	Timer timer;
	JButton buttonPlay;
	JButton buttonScores;
	JButton buttonHelp; 
	JButton buttonMainMenu;
	JButton buttonNext;
	JButton buttonPrevious;
	JTextArea RulesOfGame;
	JButton buttonQuit;
	JLabel labelIP;
	SuperSocketMaster ssm;
	JButton buttonHost;
	JButton buttonClient;
	
	JTextField EnterIP;
	
	////////////////////////////////////////////////////////////////////
	
	// *** Help Menu - Instructions (String) ***
	int intHelpPage = 0; // Set initial value of Help Menu Pages to 0
	
	// Objective Instruction
	String strObjective = "OBJECTIVE";
	String strObjectiveDescription = "The Objective of the game is to eliminate the nexus of your opponent";
	
	// Pieces Information
	// Still need to include heroes and villians (pieces info)
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

	
	////////////////////////////////////////////////////////////////////
	
	// Methods
	@Override
	public void actionPerformed(ActionEvent evt){ // Action listener
		if(evt.getSource() == this.timer){
			this.gamepanel.repaint(); // Repaint the panel based on timer (60 fps).
		}
		else if(evt.getSource() == this.buttonPlay){
			System.out.println("Play");
			buttonPlay.setVisible(false);
			buttonScores.setVisible(false);
			buttonHelp.setVisible(false);
			buttonQuit.setVisible(false);
			buttonHost.setVisible(true);
			buttonClient.setVisible(true);	
		}
		else if(evt.getSource() == this.buttonHost){
			labelIP.setVisible(true);
			buttonHost.setVisible(false);
			buttonClient.setVisible(false);
		}
		else if(evt.getSource() == this.buttonClient){
			buttonHost.setVisible(false);
			buttonClient.setVisible(false);
			this.EnterIP.setVisible(true);
		}

		else if(evt.getSource() == this.buttonScores){
			System.out.println("High scores");
		}
		else if(evt.getSource() == this.buttonHelp){  //User selects Help Button
			System.out.println("Help");
			intHelpPage = intHelpPage + 1; // Plus 1 to make the 1st page of help menu appear on screen
			buttonPlay.setVisible(false);
			buttonScores.setVisible(false);
			buttonHelp.setVisible(false);
			buttonQuit.setVisible(false);
			RulesOfGame.setVisible(true);
			buttonMainMenu.setVisible(true);
			buttonNext.setVisible(true);	
		}
		else if(evt.getSource() == this.buttonMainMenu){ // User selects Main Menu Button in Help menu
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
		else if(evt.getSource() == this.buttonNext){ // User selects next button
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
		else if(evt.getSource() == this.buttonPrevious){
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
		
		else if(evt.getSource() == this.buttonQuit){
				System.out.println("Quit");
				System.exit(0);
		}
	}
		
	@Override
	public void keyTyped(KeyEvent evt){ // Called when a key is typed.
		if(evt.getSource() == this.EnterIP){
			String strAddress = EnterIP.getText();
			if(strAddress.equalsIgnoreCase(labelIP.getText())){
				System.out.println("Testing");
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
			if(evt.getSource() == this.buttonPlay){
				this.buttonPlay.setBackground(Color.RED);
				this.buttonPlay.setOpaque(true);
				this.buttonPlay.setBorderPainted(false);
			}
			if(evt.getSource() == this.buttonScores){
				this.buttonScores.setBackground(Color.RED);
				this.buttonScores.setOpaque(true);
				this.buttonScores.setBorderPainted(false);
			}
			if(evt.getSource() == this.buttonHelp){
				this.buttonHelp.setBackground(Color.RED);
				this.buttonHelp.setOpaque(true);
				this.buttonHelp.setBorderPainted(false);
			}
			if(evt.getSource() == this.buttonQuit){
				this.buttonQuit.setBackground(Color.RED);
				this.buttonQuit.setOpaque(true);
				this.buttonQuit.setBorderPainted(false);
			}
			if(evt.getSource() == this.buttonHost){
				this.buttonHost.setBackground(Color.RED);
				this.buttonHost.setOpaque(true);
				this.buttonHost.setBorderPainted(false);
			}
			if(evt.getSource() == this.buttonClient){
				this.buttonClient.setBackground(Color.RED);
				this.buttonClient.setOpaque(true);
				this.buttonClient.setBorderPainted(false);
			}
			if(evt.getSource() == this.buttonMainMenu){
				this.buttonMainMenu.setBackground(Color.RED);
				this.buttonMainMenu.setOpaque(true);
				this.buttonMainMenu.setBorderPainted(false);
			}
			if(evt.getSource() == this.buttonNext){
				this.buttonNext.setBackground(Color.RED);
				this.buttonNext.setOpaque(true);
				this.buttonNext.setBorderPainted(false);
			}
			if(evt.getSource() == this.buttonPrevious){
				this.buttonPrevious.setBackground(Color.RED);
				this.buttonPrevious.setOpaque(true);
				this.buttonPrevious.setBorderPainted(false);
			}
		}
		
		@Override
		public void mouseExited(MouseEvent evt){ // Called after the cursor exits the bounds of the listened-to component.
		if(evt.getSource() == this.buttonPlay){
			this.buttonPlay.setBackground(Color.BLACK);
			this.buttonPlay.setOpaque(true);
			this.buttonPlay.setBorderPainted(false);
		}
		if(evt.getSource() == this.buttonScores){
			this.buttonScores.setBackground(Color.BLACK);
			this.buttonScores.setOpaque(true);
			this.buttonScores.setBorderPainted(false);
		}
		if(evt.getSource() == this.buttonHelp){
			this.buttonHelp.setBackground(Color.BLACK);
			this.buttonHelp.setOpaque(true);
			this.buttonHelp.setBorderPainted(false);
		}
		if(evt.getSource() == this.buttonQuit){
			this.buttonQuit.setBackground(Color.BLACK);
			this.buttonQuit.setOpaque(true);
			this.buttonQuit.setBorderPainted(false);
		}
		if(evt.getSource() == this.buttonHost){
			this.buttonHost.setBackground(Color.BLACK);
			this.buttonHost.setOpaque(true);
			this.buttonHost.setBorderPainted(false);
		}
		if(evt.getSource() == this.buttonClient){
			this.buttonClient.setBackground(Color.BLACK);
			this.buttonClient.setOpaque(true);
			this.buttonClient.setBorderPainted(false);
		}
		if(evt.getSource() == this.buttonMainMenu){
			this.buttonMainMenu.setBackground(Color.BLACK);
			this.buttonMainMenu.setOpaque(true);
			this.buttonMainMenu.setBorderPainted(false);
		}
		if(evt.getSource() == this.buttonNext){
			this.buttonNext.setBackground(Color.BLACK);
			this.buttonNext.setOpaque(true);
			this.buttonNext.setBorderPainted(false);
		}
		if(evt.getSource() == this.buttonPrevious){
			this.buttonPrevious.setBackground(Color.BLACK);
			this.buttonPrevious.setOpaque(true);
			this.buttonPrevious.setBorderPainted(false);
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
		
		this.gamepanel = new GamePanel();
		this.gamepanel.setLayout(null);
		this.gamepanel.setPreferredSize(new Dimension(1280, 720));
		
		////////////////////////////////////////////////////////////////
		
		this.timer = new Timer(1000/60, this);
		this.timer.start();
		
		////////////////////////////////////////////////////////////////
		// JButtons
		this.buttonPlay = new JButton("Play"); // Play button
		this.buttonPlay.setSize(200, 50); // 200 pixels by 50 pixels
		this.buttonPlay.setLocation(540, 230); // x and y coordinates (540, 230)
		this.buttonPlay.setFocusPainted(false); // Remove focus ring
		this.buttonPlay.setBackground(Color.BLACK); // Set background to black
		this.buttonPlay.setOpaque(true);
		this.buttonPlay.setBorderPainted(false);
		this.buttonPlay.setFont(new Font("Arial", Font.PLAIN, 20)); // Arial text font size 20
		this.buttonPlay.setForeground(Color.WHITE); // Make text white
		this.buttonPlay.addActionListener(this); // Add action listener to play button
		this.buttonPlay.addMouseListener(this); // Add mouse listener to play button
		this.gamepanel.add(this.buttonPlay); // Add play button to the panel
		
		// *** Networking ***
		////////////////////////////////////////////////////////////////
		this.buttonHost = new JButton("Host");
		this.buttonHost.setSize(250, 100);
		this.buttonHost.setLocation(300, 300);
		this.buttonHost.setFocusPainted(false);
		this.buttonHost.setBackground(Color.BLACK);
		this.buttonHost.setOpaque(true);
		this.buttonHost.setBorderPainted(false);
		this.buttonHost.setFont(new Font("Arial", Font.PLAIN, 20)); // Arial text font size 20
		this.buttonHost.setForeground(Color.WHITE); // Make text white
		this.buttonHost.addActionListener(this); // Add action listener to play button
		this.buttonHost.addMouseListener(this); // Add mouse listener to play button
		this.buttonHost.setVisible(false);
		this.gamepanel.add(this.buttonHost); // Add play button to the panel
		
		this.buttonClient = new JButton("Client");
		this.buttonClient.setSize(250, 100);
		this.buttonClient.setLocation(750, 300);
		this.buttonClient.setFocusPainted(false);
		this.buttonClient.setBackground(Color.BLACK);
		this.buttonClient.setOpaque(true);
		this.buttonClient.setBorderPainted(false);
		this.buttonClient.setFont(new Font("Arial", Font.PLAIN, 20)); // Arial text font size 20
		this.buttonClient.setForeground(Color.WHITE); // Make text white
		this.buttonClient.addActionListener(this); // Add action listener to play button
		this.buttonClient.addMouseListener(this); // Add mouse listener to play button
		this.buttonClient.setVisible(false);
		this.gamepanel.add(this.buttonClient); // Add play button to the panel
		
		this.EnterIP = new JTextField();
		this.EnterIP.setSize(200, 100);
		this.EnterIP.setLocation(550, 300);
		this.EnterIP.addKeyListener(this);
		this.EnterIP.setVisible(false);
		this.gamepanel.add(this.EnterIP);
		
		
		
		////////////////////////////////////////////////////////////////
		
		
		this.buttonScores = new JButton("High Scores"); // High scores button
		this.buttonScores.setSize(200, 50); // 200 pixels by 50 pixels
		this.buttonScores.setLocation(540, 300); // x and y coordinates (540, 300)
		this.buttonScores.setFocusPainted(false); // Remove focus ring
		this.buttonScores.setBackground(Color.BLACK); // Set background to black
		this.buttonScores.setOpaque(true);
		this.buttonScores.setBorderPainted(false);
		this.buttonScores.setFont(new Font("Arial", Font.PLAIN, 20)); // Arial text font size 20
		this.buttonScores.setForeground(Color.WHITE); // Make text white
		this.buttonScores.addActionListener(this); // Add action listener to high scores button
		this.buttonScores.addMouseListener(this); // Add mouse listener to high scores button
		this.gamepanel.add(this.buttonScores); // Add high scores button to the panel
		
		this.buttonHelp = new JButton("Help"); // Help button
		this.buttonHelp.setSize(200, 50); // 200 pixels by 50 pixels
		this.buttonHelp.setLocation(540, 370); // x and y coordinates (540, 370)
		this.buttonHelp.setFocusPainted(false); // Remove focus ring
		this.buttonHelp.setBackground(Color.BLACK); // Set background to black
		this.buttonHelp.setOpaque(true);
		this.buttonHelp.setBorderPainted(false);
		this.buttonHelp.setFont(new Font("Arial", Font.PLAIN, 20)); // Arial text font size 20
		this.buttonHelp.setForeground(Color.WHITE); // Make text white
		this.buttonHelp.addActionListener(this); // Add action listener to help button
		this.buttonHelp.addMouseListener(this); // Add mouse listener to help button
		this.gamepanel.add(this.buttonHelp); // Add help button to the panel
		
		
		////////////////////////////////////////////////////////////////
		// ***HELP MENU JBUTTONS***
		// Main Menu button
		this.buttonMainMenu = new JButton("Main Menu"); // Button to return to main menu
		this.buttonMainMenu.setFont(new Font("Times New Roman", Font.BOLD, 15)); // Times New Roman text font size 15, bolded
		this.buttonMainMenu.setForeground(Color.WHITE); // Make text white
		this.buttonMainMenu.setBackground(Color.BLACK); // Set background to black
		this.buttonMainMenu.setOpaque(true);
		this.buttonMainMenu.setFocusPainted(false); // Remove focus ring
		this.buttonMainMenu.setBorderPainted(false);
		this.buttonMainMenu.addActionListener(this); // Add action listener to button main menu
		this.buttonMainMenu.setSize(130,30); // 130 x 30 pixels
		this.buttonMainMenu.setLocation(580,615); // x and y coordinates (580, 615)
		this.buttonMainMenu.setVisible(false);
		this.buttonMainMenu.addMouseListener(this);
		this.gamepanel.add(buttonMainMenu); // Add main menu button to the panel
		
		// Next button 
		this.buttonNext = new JButton("Next"); // Button to go to next page
		this.buttonNext.setFont(new Font("Times New Roman", Font.BOLD, 15));  // Times New Roman text font size 15, bolded
		this.buttonNext.setForeground(Color.WHITE); // make text white
		this.buttonNext.setBackground(Color.BLACK); // set background to black
		this.buttonNext.setOpaque(true);
		this.buttonNext.setFocusPainted(false);  // remove focus ring
		this.buttonNext.setBorderPainted(false);
		this.buttonNext.addActionListener(this); // Add action listener to next button
		this.buttonNext.setSize(130,30); // 130 x 30 pixels
		this.buttonNext.setLocation(727,615); // x and y coordinates (727, 615)
		this.buttonNext.setVisible(false);
		this.buttonNext.addMouseListener(this);
		this.gamepanel.add(buttonNext); // Add next button to the panel
		
		// Previous button
		this.buttonPrevious = new JButton("Previous"); // Button to go back to previous page
		this.buttonPrevious.setFont(new Font("Times New Roman", Font.BOLD, 15)); // Times New Roman text font size 15, bolded
		this.buttonPrevious.setForeground(Color.WHITE); // make text white
		this.buttonPrevious.setBackground(Color.BLACK); // set background to black
		this.buttonPrevious.setOpaque(true);
		this.buttonPrevious.setFocusPainted(false);  // remove focus ring
		this.buttonPrevious.setBorderPainted(false);
		this.buttonPrevious.addActionListener(this); // Add action listener to previous button
		this.buttonPrevious.setSize(130,30); // 130 x 30 pixels
		this.buttonPrevious.setLocation(420,615); // x and y coordinates (420, 615)
		this.buttonPrevious.setVisible(false);	
		this.buttonPrevious.addMouseListener(this);
		this.gamepanel.add(buttonPrevious); // Add previous button to the panel
		
		
		// RulesOfGame - Text Area
		this.RulesOfGame = new JTextArea(strObjective+"\n"+"\n"+strObjectiveDescription+"\n"+"\n"+strNote);
		this.RulesOfGame.setFont(new Font("Times New Roman", Font.PLAIN, 15)); // Times New Roman text font size 15
		this.RulesOfGame.setForeground(Color.WHITE); // Change text colour to black
		this.RulesOfGame.setBackground(new Color(214,0,0)); // Change background colour to custom red colour
		this.RulesOfGame.setSize(438,470); // 438 x 470 pixels
		this.RulesOfGame.setLocation(420,137); // x and y coordinates (420, 137)
		this.RulesOfGame.setEditable(false); // Prevent user from editing the text area
		this.RulesOfGame.setVisible(false);
		this.RulesOfGame.setLineWrap(true); // Set to true. The lines will be wrapped if they are too long to fit within the allocated width of the textarea
		this.gamepanel.add(RulesOfGame); // Add textarea to the panel
		
		////////////////////////////////////////////////////////////////
		
		this.buttonQuit = new JButton("Quit"); // Quit button
		this.buttonQuit.setSize(200, 50); // 200 pixels by 50 pixels
		this.buttonQuit.setLocation(540, 440); // x and y coordinates (540, 440)
		this.buttonQuit.setFocusPainted(false); // Remove focus ring
		this.buttonQuit.setBackground(Color.BLACK); // Set background to black
		this.buttonQuit.setOpaque(true);
		this.buttonQuit.setBorderPainted(false);
		this.buttonQuit.setFont(new Font("Arial", Font.PLAIN, 20)); // Arial text font size 20
		this.buttonQuit.setForeground(Color.WHITE); // Make text white
		this.buttonQuit.addActionListener(this); // Add action listener to quit button
		this.buttonQuit.addMouseListener(this); // Add mouse listener to quit button
		this.gamepanel.add(this.buttonQuit); // Add help button to the panel
		
		////////////////////////////////////////////////////////////////
		ssm = new SuperSocketMaster(1337, this);
		System.out.println("My server ip is: "+ssm.getMyAddress());
		ssm.connect();
		this.labelIP = new JLabel(ssm.getMyAddress());
		this.labelIP.setLocation(550, 300);
		this.labelIP.setSize(200,50);
		this.labelIP.setFont(new Font("Arial", Font.PLAIN, 30)); // Arial text font size 20
		this.labelIP.setForeground(Color.WHITE); // Make text white
		this.gamepanel.add(this.labelIP);
		this.labelIP.setVisible(false);
		
		////////////////////////////////////////////////////////////////
		this.frame = new JFrame("Game Of The Generals Marvel Edition");
		// Puts the panel inside the frame.
		this.frame.setContentPane(gamepanel);
		
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
