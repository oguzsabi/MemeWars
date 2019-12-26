import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck1;
    private ArrayList<Card> deck2;

    public Deck(){
        deck1 = new ArrayList<>();
        deck2 = new ArrayList<>();
        DefaultCards defaultCards = new DefaultCards();
        ArrayList<Card> cards = defaultCards.getCards();
        // Deck 1 is created
        deck1.add(cards.get(0));
        deck1.add(cards.get(1));
        deck1.add(cards.get(2));
        deck1.add(cards.get(3));
        deck1.add(cards.get(4));
        deck1.add(cards.get(5));
        deck1.add(cards.get(6));
        deck1.add(cards.get(7));
        deck1.add(cards.get(8));
        deck1.add(cards.get(9));
        deck1.add(cards.get(10));
        deck1.add(cards.get(11));
        deck1.add(cards.get(12));
        deck1.add(cards.get(13));
        deck1.add(cards.get(14));
        deck1.add(cards.get(15));
        deck1.add(cards.get(16));
        deck1.add(cards.get(17));
        deck1.add(cards.get(18));
        deck1.add(cards.get(19));
        deck1.add(cards.get(20));
        deck1.add(cards.get(22));
        deck1.add(cards.get(24));
        deck1.add(cards.get(26));
        deck1.add(cards.get(28));
        deck1.add(cards.get(30));
        deck1.add(cards.get(32));
        deck1.add(cards.get(34));
        deck1.add(cards.get(36));
        deck1.add(cards.get(38));
        deck1.add(cards.get(40));

        // Deck 2 is created
        deck2.add(cards.get(0));
        deck2.add(cards.get(1));
        deck2.add(cards.get(2));
        deck2.add(cards.get(3));
        deck2.add(cards.get(4));
        deck2.add(cards.get(5));
        deck2.add(cards.get(6));
        deck2.add(cards.get(7));
        deck2.add(cards.get(8));
        deck2.add(cards.get(9));
        deck2.add(cards.get(10));
        deck2.add(cards.get(11));
        deck2.add(cards.get(12));
        deck2.add(cards.get(13));
        deck2.add(cards.get(14));
        deck2.add(cards.get(15));
        deck2.add(cards.get(16));
        deck2.add(cards.get(17));
        deck2.add(cards.get(18));
        deck2.add(cards.get(19));
        deck2.add(cards.get(21));
        deck2.add(cards.get(23));
        deck2.add(cards.get(25));
        deck2.add(cards.get(27));
        deck2.add(cards.get(29));
        deck2.add(cards.get(31));
        deck2.add(cards.get(33));
        deck2.add(cards.get(35));
        deck2.add(cards.get(37));
        deck2.add(cards.get(39));
        deck2.add(cards.get(41));
    }

    public ArrayList<Card> getDeck1(){ // Returns the cards of the Deck 1
        shuffle(deck1);
        return deck1;
    }
    public ArrayList<Card> getDeck2(){ // Returns the cards of the Deck 2
        shuffle(deck2);
        return deck2;
    }

    private void shuffle(ArrayList<Card> deck){ // shuffles the deck randomly at the beginning of the game
        Collections.shuffle(deck);
    }
}
