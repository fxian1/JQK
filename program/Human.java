
/**
 * Interface for player/ref
 */

public interface Human {


	//Method to remove a played/used card from deck
	public Card remove_card(String sym) throws EmptyDeckException, CardNotFoundException;

	//Method to display the cards of player/dealer
	public String show_cards();

}