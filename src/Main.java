import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Event event = new Event(new User());
        DefaultCards defaultCards = new DefaultCards();
        ArrayList<Card> clone = (ArrayList<Card>) defaultCards.cards.clone();
        System.out.println(clone.get(0));

        event.battle(clone.get(1), defaultCards.cards.get(0));

        System.out.println(defaultCards.cards.get(0));
    }
}
