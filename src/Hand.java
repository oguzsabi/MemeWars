import java.security.SecureRandom;
import java.util.ArrayList;

public class Hand {
    // This class is not being used anymore
    ArrayList<Card> hand = new ArrayList<>();
    SecureRandom random = new SecureRandom();

    public ArrayList<Card> draw(ArrayList<Card> deck){
        int cardToDrawn = random.nextInt(deck.size());
        if(hand.size()<10){
            hand.add(deck.get(cardToDrawn));
            deck.remove(cardToDrawn);
        }
        return hand;
    }

    public Card play(ArrayList<Card> hand, Card card){
        hand.remove(card);
        return card;
    }
}
