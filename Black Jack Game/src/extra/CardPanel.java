package extra;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class CardPanel extends JPanel {
	
	// Instance variable to keep track of what card it is
	private PlayingCard cardNow;
	private BufferedImage cardImage;
	
	public CardPanel(PlayingCard card) {
		cardImage = card.getCardImage();
		setPreferredSize(new Dimension(cardImage.getWidth(), cardImage.getHeight()));
		repaint();
	}
	
	/**
	 * Draws us in the panel the image
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(cardImage, 0, 0, this);
	}

}
