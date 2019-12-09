import java.util.ArrayList;
import java.util.Arrays;

public class DefaultCards {
    public ArrayList<Card> cards;

    public DefaultCards() {
        cards = new ArrayList<>();

        cards.add(youHaveNoPowerHere);
        cards.add(youHaveNoPowerHere2);
    }

    public static Card defaultMinion = new Card(1, 1, 1, true, false, null);

    private Card youHaveNoPowerHere = new Card(8, 4, 4, true, false, new ArrayList<>(Arrays.asList("DecreaseDamage", 1)));
    private Card youHaveNoPowerHere2 = new Card(8, 4, 4, true, false, new ArrayList<>(Arrays.asList("DecreaseDamage", 1)));
    private Card whyUNoAttack = new Card(3, 2, 2, true, true, new ArrayList<>(Arrays.asList("DamageTarget", 1)));
}
