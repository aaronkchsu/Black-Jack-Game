package extra;

/**
 * Represents the four possible suits of a playing card.
 * 
 * @author Erin Parker
 */
public enum CardSuit {
	
	CLUBS, DIAMONDS, HEARTS, SPADES;
	
	/**
	 * Returns the value of this card suit as a formatted string.
	 */
	public String toString() {
		if(this == CLUBS)
			return "clubs";
		if(this == DIAMONDS)
			return "diamonds";
		if(this == HEARTS) 
			return "hearts";
		return "spades";
	}
}
