import java.util.ArrayList;

public class Deck {
    ArrayList<Card> deck1 = new ArrayList<>();
    ArrayList<Card> deck2 = new ArrayList<>();
    ArrayList<Card> cards = new ArrayList<>();
    DefaultCards defaultCards = new DefaultCards();
    public void decks(){

        cards = defaultCards.getCards();
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));
        deck1.add(cards.get(0));

        // Second deck begins


        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));
        deck2.add(cards.get(0));

    }

    public ArrayList<Card> getDeck1(){
        return deck1;
    }
    public ArrayList<Card> getDeck2(){
        return deck2;
    }

}
