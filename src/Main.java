public class Main {
    public static void main(String[] args) {
        Event event = new Event();
        System.out.println("Wow.");
        System.out.println("Hello.");

        Card card = new Card();
        Card card2 = new Card();
        event.attack(card, card2);
    }
}
