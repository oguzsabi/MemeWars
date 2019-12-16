import java.util.ArrayList;
import java.util.Arrays;

public class DefaultCards {
    public ArrayList<Card> cards;

    public DefaultCards() {
        cards = new ArrayList<>();

        cards.add(youHaveNoPowerHere);
        cards.add(whyUNoAttack);
        cards.add(decreaseDamageTargetTest);

    }

    public static Card defaultMinion = new Card(1, 1, 1,"", true, false,true,true, null);

    private Card youHaveNoPowerHere = new Card(8, 4, 4,"DR", true, false,true,true, new ArrayList<>(Arrays.asList("DecreaseDamage", 1)));
    private Card whyUNoAttack = new Card(3, 2, 3,"BC", true, true,true,true, new ArrayList<>(Arrays.asList("DamageTarget", 1)));
    private Card decreaseDamageTargetTest = new Card(1,5,2,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DecreaseDamageOfTarget",1)));
}
