import java.util.ArrayList;

/**
 * Dealer Object.
 *
 * Dealer sets down a card randomly, game is over when ref runs out of cards 
 *
 */

public class Dealer implements Human {

 	private final int MAX = 13;

 	private ArrayList<Card> cards = new ArrayList<Card>(MAX);

 	public Dealer(String suit) {
 		this.obtainDeck(suit);
 	}

 	//Dealer gets the leftover suit
 	private void obtainDeck(String suit) {

 		for (int i = 1; i <= 13; i++) {
 			Card temp = new Card();
 			String sym;
 			temp.set_suit(suit);
 			temp.set_value(i);
 			sym = Integer.toString(i);
 			if (i == 1) {
 				temp.set_sym("A");
 				this.cards.add(temp);
 			}
 			else if (i == 11) {
 				temp.set_sym("J");
 				this.cards.add(temp);
 			}
 			else if (i == 12) {
 				temp.set_sym("Q");
 				this.cards.add(temp);
 			}
 			else if (i == 13) {
 				temp.set_sym("K");
 				this.cards.add(temp);
 			}
 			else {
 				temp.set_sym(sym);
 				this.cards.add(temp);
 			}
 		}
 	}

	@Override
 	public Card remove_card(String sym) throws EmptyDeckException, CardNotFoundException{
 		boolean exists = false;
 		Card temp = null;

 		if (cards.size() == 0) {
 			throw new EmptyDeckException("Cannot Remove From Empty Deck");
 		}

 		for (int i = 0; i < cards.size(); i++) {
 			if (cards.get(i).get_sym().equals(sym)) {
 				temp = cards.get(i);
 				cards.remove(i);
 				exists = true;
 			}
 		}

 		if (!exists) {
 			throw new CardNotFoundException("Card to be removed does not exist");
 		}
 		return temp;
 	}
 	
 	public int get_size() {
 		return this.cards.size();
 	}

 	public Card get_card(int i) {
 		return this.cards.get(i);
 	}

 	@Override
 	public String show_cards() {
 		StringBuilder sb = new StringBuilder();
 		sb.append("{");
 		for (int i = 0; i < this.cards.size(); i++) {
 			sb.append(this.cards.get(i).get_sym());
 			if (i != this.cards.size() - 1) {
 				sb.append(", ");
 			}
 		}
 		sb.append("}");
        return sb.toString();
 	}

 }

 