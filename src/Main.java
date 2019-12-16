import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Event event = new Event(new User());
        DefaultCards defaultCards = new DefaultCards();
        System.out.println("Card: 1 " + defaultCards.cards.get(1));
        System.out.println("Card: 2 " + defaultCards.cards.get(2));

        event.play(defaultCards.cards.get(1),defaultCards.cards.get(1));
        event.play(defaultCards.cards.get(2), defaultCards.cards.get(1));
        event.battle(defaultCards.cards.get(1), defaultCards.cards.get(2));

        System.out.println("Card: 1 " + defaultCards.cards.get(1));
        System.out.println("Card: 2 " + defaultCards.cards.get(2));
    }
}
