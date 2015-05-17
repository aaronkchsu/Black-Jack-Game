package extra;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public class HandPanel extends JPanel {
	
	// Main purpose of this class is to have a grid panel that expands
	public HandPanel(){
		setLayout(new FlowLayout());
	}
	
	/**
	 * Adds a card Panel to display
	 * @param cp
	 */
	public void addCardPanel(CardPanel cp) {
		add(cp);
	}
	
	/**
	 * Adds a card Panel to display
	 * @param cp
	 */
	public void remove() {
		removeAll();
	}

}
