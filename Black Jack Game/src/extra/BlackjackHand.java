/**
 * 
 */
package extra;

/** Takes the constructor from the hand class adds a value method
 * 
 * @author Aaron Kc Hsu
 *
 */
public class BlackjackHand extends Hand{


	
	/**
	 * Takes each card in a hand and converts the total value into Blackjack scoring
	 * @return the total score as an Int
	 */
	public int value(){
		
		// Keeps track of the total value
		int total = 0;
		
		// Loops around every card in hand 
		for(int j = 0; j < cardCount(); j++){
		
			// Gets each card at certain index of hand
			PlayingCard currentCard = getCard(j);
			
			CardValue currentValue = currentCard.getValue();
			
			// If the card is a jack queen or king add 10 to the total
			if(currentValue.equals(CardValue.JACK) || currentValue.equals(CardValue.QUEEN) || currentValue.equals(CardValue.KING)){
				total += 10;
			}
			
			//In black jack you treat the Ace as 11 if it does not send the total above 21
			else if(total < 11 && currentValue.equals(CardValue.ACE)){
				total += 11;
			}
			
			// If it isn't a special case then add the enums ordinal value to total
			else{		
				
				//The ordinal value plus 1 because it starts at 0
				total += currentValue.ordinal() + 1;
			}
		}
		
		// Returns the total after all the cards have been counted for
		return total;
	}
	
	public String toString(){
		
		return super.toString() + " has Blackjack value " + value();
	}

}
