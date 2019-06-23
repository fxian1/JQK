

import java.util.ArrayList;


/**
 * Player Object.
 *
 * Users can determine names for themselves and should be able to see what cards they have
 * in their hands at beginning of each turn.
 * 
 * Each player initialized with card set
 */

public class Player implements Human {

 	private final int MAX = 13;

 	private ArrayList<Card> cards = new ArrayList<Card>();
 	private String playername;
 	private int points;

 	public Player() {
 		this.obtainDeck("Spades");
 		this.playername = "npc";
 		this.points = 0;
 	}

 	public Player(String pname, String suit) {
 		this.obtainDeck(suit);
 		this.playername = pname;
 		this.points = 0;
 	}

 	//User determines what Suit they want
 	private void obtainDeck(String suit) {

 		for (int i = 1; i <= 13; i++) {
 			Card temp = new Card();
 			String sym;
 			temp.set_suit(suit);
 			temp.set_value(i);
 			sym = Integer.toString(i);
 			if ("1".equals(sym)) {
 				temp.set_sym("A");
 				this.cards.add(temp);
 			}
 			else if ("11".equals(sym)) {
 				temp.set_sym("J");
 				this.cards.add(temp);
 			}
 			else if ("12".equals(sym)) {
 				temp.set_sym("Q");
 				this.cards.add(temp);
 			}
 			else if ("13".equals(sym)) {
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

 	public void set_name(String n) {
 		this.playername = n;
 	}

 	public String get_name() {
 		return this.playername;
 	}

 	public int get_points() {
 		return this.points;
 	}

 	public void add_points(int p) {
 		this.points += p;
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

 