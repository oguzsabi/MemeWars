import java.util.ArrayList;

public class Deck {
    ArrayList<Card> deck1 = new ArrayList<>();
    ArrayList<Card> deck2 = new ArrayList<>();
    ArrayList<Card> cards = new ArrayList<>();
    DefaultCards defaultCards = new DefaultCards();
    public void decks(){

        cards = defaultCards.getCards();
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

        // Second deck begins


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

    public ArrayList<Card> getDeck1(){
        return deck1;
    }
    public ArrayList<Card> getDeck2(){
        return deck2;
    }

}
