
/**
 * Card Object.
 *
 * Cards should be not be accessible by users. Once set, the cards cannot be changed
 * for the purpose of this game.
 */

public class Card {

 	//Card data should be not modified
 	private String suit;
 	private String sym;
 	private int value;

 	public Card() {
 		suit = null;
 		sym = null;
 		value = 0;
 	}

 	public String get_suit() {
 		return this.suit;
 	}

 	public void set_suit(String str) {
 		this.suit = str;
 	}

 	public String get_sym() {
 		return this.sym;
 	}

 	public void set_sym(String s) {
 		this.sym = s;
 	}

 	public int get_value() {
 		return this.value;
 	}

 	public void set_value(int val) {
 		this.value = val;
 	}

 	public String toString() {
 		StringBuilder sb = new StringBuilder();
        sb.append("{" + this.suit + ", " + this.sym + "}");
        return sb.toString();
 	}

 }

 