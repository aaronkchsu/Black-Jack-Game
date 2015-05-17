package extra;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Represents a playing card (e.g, ace of spades, 2 of clubs, king of hearts).
 * 
 * @author  CS 1410 class Modified by AAron KC HSU
 */
public class PlayingCard implements Comparable<PlayingCard> {
		
	private final CardSuit suit;   // Takes enums from the CardSuit Class
	private final CardValue value;   // Takes enums from the CardValue Class
	private BufferedImage cardImage;
		
	/**
	 * Our constructor uses enums to form a simple playing card
	 * @param _suit Takes an enum from the CardSuit class
	 * @param _value Takes an enum from the CardValue Class
	 * @throws IOException 
	 */
	public PlayingCard(CardSuit _suit, CardValue _value) throws IOException { //throws IOException {   
		
		//Updates both values so that the playing card is identified
		suit = _suit;
		value = _value;
		

		// Based on value of card get file associated with card
		String imgPath = _suit + " (" + _value + ")" + ".gif";
		System.out.println(imgPath);
		cardImage = ImageIO.read(new File(imgPath));
		
	}
	
	/**
	 * Returns the suit of this playing card.
	 */
	public CardSuit getSuit() {
		return suit;
	}
	

	/**
	 * Returns the value of this playing card.
	 */
	public CardValue getValue() {    // TO DO: Update return type and body to use enum for card value.
		return value;
	}
	
	/**
	 * This will be used to get the image to paint onto the panel
	 */
	public BufferedImage getCardImage() {
		return cardImage;
	}
	
	/**
	 * Returns a formatted string representation of this card (e.g., "ace of spades, "9 of hearts").
	 * 
	 * @return formatted string
	 */
	public String toString() {

		//Returns the format based on the to string methods of the enum classes
		return value.toString() + " of " + suit.toString();
	}

	
	/** Returns true if the card represented by this object is equal to the
	 * other card.
	 * 
	 * @param other
	 * @return true if this is equal to other
	 */
	public boolean equals(Object other) {
		if(!(other instanceof PlayingCard))
			return false;
		
		PlayingCard rhs = (PlayingCard)other;
		
		// Note that enums may be compared with ==.
		return suit == rhs.suit && value == rhs.value;
	}
	
	/**
	 * Used to set a standard of comparison for cards 
	 * @param other A playing Card object
	 * @return an int revealing where it stands on the scale
	 */
	public int compareTo(PlayingCard other){
		
		//Compare the values if they are equal then compare the suits
		if(this.value.compareTo(other.value) != 0){
			return this.value.compareTo(other.value);
		}
		
		//This compares the suits and gives us an int of comparison
		else {
			return this.suit.compareTo(other.suit);
		}
	}
}
