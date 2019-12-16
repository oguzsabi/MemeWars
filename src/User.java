public class User {
    private int ID;
    private int health;
    private int banana;
    private String name;
    private String heroName;
    private Hand hand;
    private Deck deck;

    public User() {
        this.ID = -1;
        this.health = 0;
        this.banana = 0;
        this.name = null;
        this.heroName = null;
        this.hand = null;
        this.deck = null;
    }

    public User(int ID, int health, int banana, String name, String heroName, Hand hand, Deck deck) {
        this.ID = ID;
        this.health = health;
        this.banana = banana;
        this.name = name;
        this.heroName = heroName;
        this.hand = hand;
        this.deck = deck;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getBanana() {
        return banana;
    }

    public void setBanana(int banana) {
        this.banana = banana;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
