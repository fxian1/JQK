
import java.util.Scanner;
import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;
import java.io.Console;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Thread;

/** Double King Card Game program */
public final class JQK {

    // Hush checkstyle
    private JQK() {}

    /**
     * The main function.
     * @param args Not used.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Console con = System.console();
        Random rand = new Random();
        Timer timer = new Timer();

        //Array to hold player information
        String[] p_names = new String[3];
        char[] p_suits = new char[3];

        //Initialize an ArrayList of Players
        ArrayList<Player> players = new ArrayList<Player>(3);

        //Initialize an arraylist with Suits
        ArrayList<Character> suits = new ArrayList<>(4);
        suits.add('S');
        suits.add('H');
        suits.add('C');
        suits.add('D');

        boolean has_suit;
        char input;

        System.out.println("\n\n\nWelcome to JQK Card Game...\n");
        System.out.println("Rules:\n");
        System.out.println("There will be 13 turns in total, the Dealer will distribute one");
        System.out.println("card each turn: A for 1 point, Q for 12 points and K for 13 points\n");
        System.out.println("Given a hand of 13 cards from (A-K), each player picks one card to bet.");
        System.out.println("Try to bet higher than your opponents for cards that are worth more points\n");

        System.out.println("Player with the most points at the end of 13 turns wins!\n");
        System.out.println("When Ready, Press Enter to get started...\n\n\n\n");

        //For Enter Key
        scan.nextLine();

        //Retrieve User Input of Player names and suits
        for (int i = 1; i < 4; i++) {
            System.out.printf("Player %d enter a name: \n", i);
            p_names[i-1] = scan.next();
            System.out.printf("Player %d enter a suit ", i);
            System.out.print(suits);
            System.out.print(": \n");
            input = scan.next(".").charAt(0);
            while (!is_Suit(input)) {
            	System.err.println("Invalid Suit Entered, Try again please: ");
            	input = scan.next(".").charAt(0);
            	has_suit = false;
            	for (int j = 0; j < suits.size(); j++) {
            		if (input == suits.get(j)) {
            			has_suit = true;
            			suits.remove(j);
            		}
            	}
            	if (!has_suit) {
            		System.err.println("Suit already picked, please pick from:\n");
            		System.out.print(suits);
            	}
            }
            p_suits[i-1] = input;
            for (int j = 0; j < suits.size(); j++) {
                if (input == suits.get(j)) {
                    suits.remove(j);
                }
            }
        }

        //Initialize Player Objects and NPC Ref
        Player p1 = new Player(p_names[0], convert_suit(p_suits[0]));
        players.add(p1);
        Player p2 = new Player(p_names[1], convert_suit(p_suits[1]));
        players.add(p2);
        Player p3 = new Player(p_names[2], convert_suit(p_suits[2]));
        players.add(p3);
        Dealer dealer = new Dealer(convert_suit(suits.get(0)));

        String winner;
        int total_points;

        //Main Game Loop
        for (int turn = 1; turn < 14; turn++) {

            try {
                Thread.sleep(2000);
                System.out.println("Isabelle NPCs are battling it out for a random card:");
                Thread.sleep(1000);
                System.out.println("...");
                Thread.sleep(1000);
                System.out.println("...");
                Thread.sleep(1000);
                System.out.println("...");
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            
        	//Initialize needed variables
            String card_sym;
            Card card, dealer_card, p1_card, p2_card, p3_card;
            int p1_val, p2_val, p3_val, rand_int;
            Player p;
            char[] hidden_input;
            Card[] turn_cards = new Card[3];


            rand_int = rand.nextInt(dealer.get_size());
      		dealer_card = dealer.get_card(rand_int);

            try {
                dealer.remove_card(dealer_card.get_sym());
            }
            catch (EmptyDeckException e) {
                System.err.println("Deck is Empty, Cannot Remove");
            }
            catch (CardNotFoundException e) {
                System.err.println("Card not found, Cannot Remove");
            }

      		System.out.printf("\nTurn %d: \n", 13 - dealer.get_size());
           	System.out.printf("Bet for Card %s of %s\n", dealer_card.get_sym(), dealer_card.get_suit());


            //Inputs from the three players
            for (int player = 1; player < 4; player++) {
            	p = players.get(player - 1);
            	System.out.printf("\nPlayer %s's turn: \n", p.get_name());
                System.out.printf("You hand is: %s\nEnter a Card:\n", p.show_cards());
                
                //while loop to give user multiple chances for input
                int count = 0;
                int maxTries = 3;
                while(true) {
                    try {
                        hidden_input = con.readPassword();
                        card = p.remove_card(String.valueOf(hidden_input));
                        turn_cards[player - 1] = card;
                        break;
                    } catch (CardNotFoundException e) {
                        System.err.println("Card not found, Cannot Remove");
                        System.out.printf("You hand is: %s\nEnter a Card(A-K):\n", p.show_cards());
                        if (++count == maxTries) break;
                    } catch (EmptyDeckException e) {
                        System.err.println("Deck is Empty, Cannot Remove");
                        if (++count == maxTries) break;
                    }
                }
            }  
            try {
                Thread.sleep(1000);
                System.out.println("And the results is...");
                Thread.sleep(1000);
                System.out.println("...");
                Thread.sleep(1000);
                System.out.println("...");
                Thread.sleep(1000);
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            //Determine who won the round 
            //Three way tie: no one gets points
            p1_card = turn_cards[0];
            p2_card = turn_cards[1];
            p3_card = turn_cards[2];
            p1_val = p1_card.get_value();
            p2_val = p2_card.get_value();
            p3_val = p3_card.get_value();

            System.out.printf("\n%s: %s, %s: %s, %s: %s\n", p1.get_name(), p1_card.toString(),
            	p2.get_name(), p2_card.toString(), p3.get_name(), p3_card.toString());

            wait(5000);

            if (p1_val == p2_val && p1_val == p3_val) {
            	System.out.println("\nSAME BETS, NO ONE GETS ANY POINT!\n");
            }
            else if (p1_val == p2_val) {
            	p3.add_points(dealer_card.get_value());
            	System.out.printf("\n%s and %s match, %s wins!\n", p1.get_name(), p2.get_name(), p3.get_name());
            }
            else if (p1_val == p3_val) {
            	p2.add_points(dealer_card.get_value());
            	System.out.printf("\n%s and %s match, %s wins!\n", p1.get_name(), p3.get_name(), p2.get_name());
            }
            else if (p2_val == p3_val) {
            	p1.add_points(dealer_card.get_value());
            	System.out.printf("\n%s and %s match, %s wins!\n", p2.get_name(), p3.get_name(), p1.get_name());
            }

           	else if (p1_val > p2_val && p1_val > p3_val) {
           		p1.add_points(dealer_card.get_value());
            	System.out.printf("\n%s wins the round!\n", p1.get_name());
           	}
           	else if (p2_val > p1_val && p2_val > p3_val) {
           		p2.add_points(dealer_card.get_value());
            	System.out.printf("\n%s wins the round!\n", p2.get_name());
           	}
           	else if (p3_val > p1_val && p3_val > p2_val) {
           		p3.add_points(dealer_card.get_value());
            	System.out.printf("\n%s wins the round!\n", p3.get_name());
           	}

            wait(1000);
            System.out.println("Press k and hit enter to advance\n");
            scan.next();

            //End Game Condition, check who has highest points
            if (dealer.get_size() == 0) {
                winner = p1.get_name();
                total_points = p1.get_points();
                if (p2.get_points() > p1.get_points() && p2.get_points() > p3.get_points()) {
                    winner = p2.get_name();
                    total_points = p2.get_points();
                }
                else if (p3.get_points() > p1.get_points()) {
                    winner = p3.get_name();
                    total_points = p3.get_points();
                }
                System.out.printf("\nGame Over, Winner is %s with %d points\n", winner, total_points);
            }
        } // Game Loop close
	} // Main class close

     /** Method to check validity of user input
     *  @param c indicates suit character representation
     *  @return true if character is valid symbol
     */
    public static boolean is_Suit(char c) {
    	return 'S' == c || 'H' == c || 'C' == c || 'D' == c;
    }

    public static String convert_suit(char c) {
        if ('S' == c) {
            return "Spades";
        }
        if ('H' == c) {
            return "Heart";
        }
        if ('C' == c) {
            return "Clubs";
        }
        if ('D' == c) {
            return "Diamond";
        }
        return null;
    }

    public static void wait(int time) {
        try {
                Thread.sleep(time);
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
    }


}
