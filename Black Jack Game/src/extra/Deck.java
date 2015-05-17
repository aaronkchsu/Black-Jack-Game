package extra;

import java.io.IOException;
import java.util.Random;

/**
 * Modifed to fit enums
 * 
 * @author Aaron Kc Hsu
 *
 */
public class Deck {

	private PlayingCard[] cards;
	private int nextCardIndex;
	
	public Deck() throws IOException {
		cards = new PlayingCard[52];
		
		//Two Arrays to put all the Enums of both classes in.
		CardSuit[] suits = { CardSuit.CLUBS, CardSuit.DIAMONDS, CardSuit.HEARTS, CardSuit.SPADES };
		CardValue[] values = { CardValue.ACE, CardValue.TWO, CardValue.THREE, CardValue.FOUR, CardValue.FIVE, CardValue.SIX, 
				CardValue.SEVEN, CardValue.EIGHT, CardValue.NINE, CardValue.TEN, CardValue.JACK, CardValue.QUEEN, CardValue.KING}; 
		
		//Switches the Index so that we can fill each space in the Array
		int cardCount = 0;
		
		//The two loops helps loop through both classes with the outer loop changing so that there is 13 cards of each suit
		for(int i = 0; i < suits.length; i++){
			for(int j = 0; j < values.length; j++){
				
				// Fills each space with a unique playing card
				cards[cardCount++] = new PlayingCard(suits[i], values[j]);
				
			}
		}
		
		//Resets each deck at index 0 saying its a new deck
		nextCardIndex = 0;
	}

	/**
	 * Deals the next card from the deck.
	 * 
	 * @return the next card
	 */
	public PlayingCard deal() {
		if(nextCardIndex >= cards.length)
			throw new RuntimeException("Out of cards");
		
		PlayingCard result = cards[nextCardIndex];
		nextCardIndex++;
		return result;
	}
	
	/**
	 * Deals the next count cards from the deck.
	 * 
	 * @param count
	 * @return the next count cards
	 */
	public PlayingCard[] deal(int count) {
		if(count < 0)
			throw new RuntimeException("deal count must be non-negative");
		
		PlayingCard[] hand = new PlayingCard[count];
		for(int i = 0; i < count; i++)
			hand[i] = deal();
		return hand;
	}
	
	/** 
	 * Randomly shuffles the deck.
	 */
	public void shuffle() {
		
		//Random class object to use to make the 
		Random rng = new Random();
		for(int i = 0; i < cards.length; i++)
			swap(i, rng.nextInt(cards.length));
		nextCardIndex = 0;
	}
	
	/**
	 * A private helper method that swaps the cards at indexes i and j.
	 * 
	 * @param i
	 * @param j
	 */
	private void swap(int i, int j) {
		PlayingCard temp = cards[i];
		cards[i] = cards[j];
		cards[j] = temp;                 
	}
}
