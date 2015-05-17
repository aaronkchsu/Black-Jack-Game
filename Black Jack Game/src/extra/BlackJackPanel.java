package extra;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class BlackJackPanel extends JFrame implements ActionListener {

	private JPanel mainPanel;

	private JMenuBar menuBar;
	private JMenu play; // Creates a little Icon for the selections
	private JMenuItem rules;
	private JMenuItem newGame;
	private JMenuItem endGame;

	// Containers for the card panels
	private HandPanel dealerPanel;
	private HandPanel playerPanel;
	private JPanel hands;

	private JButton hit;
	private JButton stand;
	private JPanel buttonPanel;

	private JLabel dealerText;
	private JLabel playerText;

	private int dealerScore;
	private int playerScore;

	private Deck blackjackDeck; // Universal Deck

	private BlackjackHand dealerHand;
	private BlackjackHand playerHand;

	// Keeps track of cash
	private int playerCash;
	private int playerBet;
	private JPanel cashPanel;
	private JLabel cashLabel;
	private JRadioButton five;
	private JRadioButton ten;
	private JRadioButton twenty;
	private JRadioButton fifty;
	private ButtonGroup cashButtons;

	public BlackJackPanel() {

		// Exit on close
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Starting cash
		playerCash = 80;

		// Creates main panel
		mainPanel = new JPanel();

		// Creation of the MenuBar
		menuBar = new JMenuBar(); // Creates the bar
		play = new JMenu("Play"); // Contain some options for game playing
		newGame = new JMenuItem("New Game");
		newGame.addActionListener(this);
		newGame.setToolTipText("Start a New Game");
		endGame = new JMenuItem("Give Up");
		endGame.setEnabled(false); // Not true until game has started
		endGame.setToolTipText("You give up the game to the dealer!");
		endGame.addActionListener(this);
		rules = new JMenuItem("Rules");
		rules.addActionListener(this);
		rules.setToolTipText("The Rules of Blackjack");
		play.add(newGame); // Option to start a new Game
		play.add(endGame);
		play.add(rules); // Option for displaying some rules
		menuBar.add(play); // Options List

		// Set up center GUI
		hands = new JPanel();
		dealerPanel = new HandPanel();
		playerPanel = new HandPanel();
		hands.setLayout(new BoxLayout(hands, 3));

		buttonPanel = new JPanel(); // panel to hold buttos
		buttonPanel.setLayout(new FlowLayout());
		hit = new JButton("Hit");
		hit.setEnabled(false);
		hit.addActionListener(this);
		stand = new JButton("Stand");
		stand.setEnabled(false);
		stand.addActionListener(this); // Set up the two buttons above

		hands.add(dealerPanel); // Using box layout add these items in this order
		dealerText = new JLabel("Dealers Hands");
		dealerText.setVisible(false);
		hands.add(dealerText);
		hands.add(buttonPanel);
		playerText = new JLabel("Players Hand");
		playerText.setVisible(false);
		hands.add(playerText);
		hands.add(playerPanel); // Add both hands to panel

		buttonPanel.add(hit);
		buttonPanel.add(stand); // Two buttons added to the panel

		//Set up panel for cash
		cashPanel = new JPanel();
		//Set up cash
		cashLabel = new JLabel("Current Cash: " + playerCash + " What is your bet?");
		five = new JRadioButton("5", true);
		playerBet = 5; // Bet is default 5
		ten = new JRadioButton("10", false);
		twenty = new JRadioButton("20", false);
		fifty = new JRadioButton("50", false);
		cashButtons = new ButtonGroup(); // Group buttons so only one can be setEnabled
		cashButtons.add(five);
		cashButtons.add(ten);
		cashButtons.add(twenty);
		cashButtons.add(fifty);
		cashPanel.setLayout(new GridLayout(1, 3)); // New layout for panel
		cashPanel.add(cashLabel);
		cashPanel.add(five);
		cashPanel.add(ten);
		cashPanel.add(twenty);
		cashPanel.add(fifty);
		five.addActionListener(this);
		ten.addActionListener(this);
		twenty.addActionListener(this);
		fifty.addActionListener(this); // Add all the action listeners

		new BorderLayout(); // Sets layout
		mainPanel.add(cashPanel, BorderLayout.SOUTH);
		mainPanel.add(hands, BorderLayout.CENTER);


		// Pack contents
		setPreferredSize(new Dimension(1100, 750));
		setJMenuBar(menuBar); // Sets menubar to frame
		setContentPane(mainPanel);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource() instanceof JMenuItem){

			JMenuItem mAction = (JMenuItem) e.getSource(); // We know it is a JMenuItem so we get the object
			if(mAction.getText().equals("New Game")){

				five.setEnabled(false); // Cannot change your bet;)
				ten.setEnabled(false);
				twenty.setEnabled(false);
				fifty.setEnabled(false);

				try {
					blackjackDeck = new Deck();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // Starts with getting deck

				dealerHand = new BlackjackHand(); // To digitally keep track of the hands
				playerHand = new BlackjackHand();

				blackjackDeck.shuffle();
				blackjackDeck.shuffle(); // Shuffles once or twice

				// Deal four cards
				PlayingCard holder = blackjackDeck.deal(); // Use this so that we can add it to two components 
				PlayingCard holderOne = blackjackDeck.deal();
				PlayingCard holderTwo = blackjackDeck.deal();
				PlayingCard holderThree = blackjackDeck.deal();

				dealerPanel.add(new CardPanel(holder)); // Keeps track of cards on panel and on digital copy
				dealerHand.addCard(holder);

				playerPanel.add(new CardPanel(holderOne));
				playerHand.addCard(holderOne);

				dealerPanel.add(new CardPanel(holderTwo));
				dealerHand.addCard(holderTwo);

				playerPanel.add(new CardPanel(holderThree));
				playerHand.addCard(holderThree);

				// Show the text
				dealerScore = dealerHand.value();
				playerScore = playerHand.value();

				dealerText.setVisible(true);
				dealerText.setText("Dealers Hands: Score " +  dealerScore);
				playerText.setVisible(true);
				playerText.setText("Players Hands: Score " +  playerScore);

				// Set the buttons to enable
				hit.setEnabled(true);
				stand.setEnabled(true);
				newGame.setEnabled(false); // Cannot Create two new games
				endGame.setEnabled(true);
				revalidate();

				checkBlackjack(); // Check if they drew a blackjack at the beginning
				revalidate();
			}

			else if(mAction.getText().equals("Rules")) {
				JOptionPane.showMessageDialog(null, "Your goal is to beat the Dealer by having your cards = 21 or "
						+ "really close to it without going over:" + "\n" + "BlackJack Values:" + "\n" + "Ace: 1 or 11 depending on what does not put your over"
						+ "2-10: These values are respective to their integer value"
						+ "Jack, Queen, King: These are worth 10"); // Message to the user
			}
			else if(mAction.getText().equals("Give Up")){
				// If user decides to end the game clear the board
				playerCash -= playerBet; // subtract cash
				clearGame();
			}
		}
		else if(e.getSource() instanceof JButton) {

			JButton bAction = (JButton) e.getSource(); // Turns source into a button

			if(bAction.getText().equals("Hit")) {
				PlayingCard hitCard = blackjackDeck.deal(); // Adds card to panel if player decides to hit
				playerPanel.add(new CardPanel(hitCard));
				playerHand.addCard(hitCard);
				playerScore = playerHand.value(); // Update Score
				playerText.setText("Players Hands: Score " +  playerScore);
				revalidate();
				checkBust();
			}
			else if(bAction.getText().equals("Stand")) {

				// Switches to dealers turns only occurs if dealer is not already winning
				if(playerScore > dealerScore){
					while(dealerScore < 17){ // Dealer does this until he hits 17
						PlayingCard hitCard = blackjackDeck.deal();
						dealerPanel.add(new CardPanel(hitCard));
						dealerHand.addCard(hitCard);
						dealerScore = dealerHand.value();
						dealerText.setText("Dealers Hands: Score " +  dealerScore);
						revalidate();
						checkBust();
					}
				}
				// Only checks this if dealer had not bust
				if(dealerScore <= 21){
					// Sees who won!
					checkVictor();
				}
			}
		}
		// Changes bet
		else if(e.getSource() instanceof JRadioButton) {
			if(five.isSelected())
				playerBet = 5;
			else if(ten.isSelected())
				playerBet = 10;
			else if(twenty.isSelected())
				playerBet = 20;
			else
				playerBet = 50;
		}
	}


	/**
	 * Clears Game panel once game is over
	 */
	public void clearGame() {

		dealerPanel.removeAll();
		playerPanel.removeAll();
		playerHand.removeAllCards();
		dealerHand.removeAllCards(); // Gets rid of all the cards in hands and panels
		hit.setEnabled(false);
		stand.setEnabled(false);
		newGame.setEnabled(true); // Revert Buttons to default status
		endGame.setEnabled(false);
		dealerText.setVisible(false);
		playerText.setVisible(false);
		cashLabel.setText("Current Cash: " + playerCash + " What is your bet?"); // Changes text of new label
		five.setEnabled(true); 
		ten.setEnabled(true);
		twenty.setEnabled(true);
		fifty.setEnabled(true);
		revalidate();

		if(playerCash <= 0){
			newGame.setEnabled(false);
			rules.setEnabled(false);
			JOptionPane.showMessageDialog(null, "You are broke!! You just got kicked out of the casino!!");
			System.exit(0); // Exits console
		}

	}


	/**
	 * Check the players for a blackjack
	 */
	public void checkBlackjack(){
		if(playerScore == 21 && dealerScore != 21){
			//If the dealer does not also have a black jack
			JOptionPane.showMessageDialog(null, "BlackJack!! Wow you beat the dealer without trying");
			playerCash += playerBet;
			clearGame();
		}
		else if(playerScore == 21 && dealerScore == 21) {
			JOptionPane.showMessageDialog(null, "What the you both got a blackjack... tie");
			clearGame();
		}
		else if(dealerScore == 21){
			JOptionPane.showMessageDialog(null, "Wow! Tough Luck Dealer got a BlackJack:L");
			playerCash -= playerBet;
			clearGame();
		}
	}

	/**
	 * This method checks if either player busts
	 */
	public void checkBust(){
		if(dealerScore > 21){
			JOptionPane.showMessageDialog(null, "Ha! Dealer Busted! You WIN!");
			playerCash += playerBet;
			clearGame();
		}
		else if(playerScore > 21){
			JOptionPane.showMessageDialog(null, "You Busted Loser... GG.. You Lose.");
			playerCash -= playerBet;
			clearGame();
		}
	}

	/**
	 * Sees who Won! displays message
	 */
	public void checkVictor(){
		if(playerScore == dealerScore){
			JOptionPane.showMessageDialog(null, "Dang Wow.. It was a Tie!! >:( how boring");
		}
		else if(playerScore > dealerScore){
			JOptionPane.showMessageDialog(null, "YOU WON!!!! you BEAT the Dealer!");
			playerCash += playerBet;
		}
		else if(playerScore < dealerScore){
			JOptionPane.showMessageDialog(null, "You Lose... How sad.. Maybe next time?");
			playerCash -= playerBet;
		}
		clearGame();
	}
}
