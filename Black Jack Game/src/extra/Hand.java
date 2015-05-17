package extra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class Hand {

	private ArrayList<PlayingCard> hand;

	/**
	 * A constructor that uses ArrayList to keep track of a hand
	 */
	public Hand() {
		hand = new ArrayList<PlayingCard>();
	}

	/**
	 * Adds a Card to your hand 
	 * @param c Takes a PlayingCard object
	 */
	public void addCard(PlayingCard c) {   

		//Adds a specific playing card instance to hand
		hand.add(c);
	}

	/**
	 * Takes out a specific card from the hand
	 * @param c Takes in a PlayingCard object
	 */
	public void removeCard(PlayingCard c) {

		//Does nothing if c is not present
		//Removes all instances of C and there should be one instance of c in a deck
		hand.remove(c);
	}

	/**
	 * Essentially this method self destructs the hand by removing all the cards form the hand
	 */
	public void removeAllCards() {

		//Loops through until all cards are removed
		for(int j = hand.size(); j > 0; j--)
			hand.remove(hand.size() - 1);
	}
	/**
	 * Finds the number of cards in a hand
	 * @return the number of cards in a hand
	 */
	public int cardCount() {
		//returns the number of cards in the hand
		return hand.size();
	}

	/**
	 * Sorts the hand from lowest to highest playing cards
	 *  to sort the cards in the hand, putting lowest card at index 0
	 */
	public void sort() {

		//Takes method from the collections class and sorts the hand from lowest to highest
		Collections.sort(hand);

		// HINT: The static sort method of Java's Collections class 
		// takes an ArrayList as its parameter and sorts it using the
		// ordering defined by the compareTo method of the list element
		// type (i.e., PlayingCard).  See java.util.Collections.
	}

	/**
	 * Returns the card at index i
	 * @param i a input index
	 * @return a playing card object at the index
	 */
	public PlayingCard getCard(int i) {

		// FILL IN to return the card at index i, throwing an
		// IndexOutOfBoundsExcpetion if i is out of range
		if(i >= hand.size()){
			throw new IndexOutOfBoundsException("i is out of range");
		}
		return hand.get(i);
	}

	/**
	 * Takes the highest valued card in a hand
	 * @return A PlayingCard instance which is the highest valued card of the hand
	 */
	public PlayingCard getHighestCard() {
		// Return the highest card in the hand
		// throwing a NoSuchElementException if there are no cards in the hand

		if(0 == hand.size()){
			throw new NoSuchElementException("There are no cards in the hand");
		}
		

		//Sorts the array from lowest to highest
		sort();

		//Returns the highest card in the sorted hand
		return hand.get(hand.size() - 1);
	}

	/**
	 * Finds the lowest valued card in the 
	 * @return
	 */
	public PlayingCard getLowestCard() {
		// Returns the lowest card in the hand
		// throwing a NoSuchElementException if there are no cards in the hand
		if(0 == hand.size()){
			throw new NoSuchElementException("There are no cards in the hand");
		}

		//Sorts the array from lowest to highest
		sort();

		//Returns the highest card in the sorted hand
		return hand.get(0);
	}

	/**
	 * Returns a string version of the hand based on playing card two string method
	 */
	public String toString() {
		// To return a formatted string representing the hand
		// E.g., "4 of clubs, 6 of hearts, king of spades

		//Holds the entire string
		String masterString = "";

		//Loops through the whole hand
		for(int j = 0; j < hand.size(); j++){
			masterString += hand.get(j).toString();

			//Only add this if it is not the last index
			if(j != hand.size() - 1){
				masterString += ", ";
			}
		}

		//The master string is then returned the to the call to give a complete center of tastieness
		return masterString;
	}
}
