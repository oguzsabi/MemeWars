import java.util.ArrayList;
import java.util.Arrays;

public class DefaultCards {
    public ArrayList<Card> cards;

    public DefaultCards() {
        cards = new ArrayList<>();

        cards.add(youHaveNoPowerHere);
        cards.add(whyUNoAttack);
        cards.add(heNeedSomeMilk);
        cards.add(soAnyWay);
        cards.add(kobe);
        cards.add(civilWar);
        cards.add(djKhaled);
        cards.add(billCosby);
        cards.add(badLuck);
        cards.add(itsRaw);
        cards.add(slapRoof);
        cards.add(confusedGandalf);
        cards.add(screamingPatrick);
        cards.add(chuckles);


    }

    public static Card defaultMinion = new Card(1, 1, 1,"", true, false,true,true, null);

    private Card youHaveNoPowerHere = new Card(8, 4, 4,"BC", true, false,true,true, new ArrayList<>(Arrays.asList("DecreaseDamageEveryone", 1)));
    private Card whyUNoAttack = new Card(3, 2, 3,"BC", true, true,true,true, new ArrayList<>(Arrays.asList("DamageTarget", 1)));
    private Card heNeedSomeMilk = new Card(3,4,3,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("HealTarget",1)));
    private Card soAnyWay = new Card(3,3,2,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageRandomTargets",3,1)));
    private Card kobe = new Card(3,2,2,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageTarget",2)));
    private Card civilWar = new Card(4,2,5,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageTarget",2)));
    private Card djKhaled = new Card(5,2,4,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("SpawnMinion",1,2,4)));
    private Card billCosby = new Card(3,3,4,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("Sleep",1)));
    private Card badLuck = new Card(2,2,2,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("SpawnMinion",1,2,2)));
    private Card itsRaw = new Card(2,2,2,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DecreaseDamageOfTarget",2)));
    private Card slapRoof = new Card(4,2,4,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageTarget",2)));
    private Card confusedGandalf = new Card(2,1,2,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageRandomTargets",3,1)));
    private Card screamingPatrick = new Card(1,1,1,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageEveryone",1)));
    private Card chuckles = new Card(1,1,1,"BC", false,false,true,true, null);
    private Card oprah = new Card(5,4,8,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("DamageEveryone",1)));
    private Card fuckmeRight = new Card(5,6,12,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("DestroyRandom",1)));
    private Card sike = new Card(4 ,3,2,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("SpawnMinion",1,3,2)));
    private Card thirdworld = new Card(3 ,3,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("SpawnMinion",3,1,1)));
    private Card trollFace = new Card(6 ,4,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("SpawnMinion",2,2,2)));
    private Card lambSauce = new Card(4 ,2,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("SpawnMinion",2,2,2)));
    private Card frodo = new Card(4 ,2,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("DamageRandomTarget",1,3)));
    private Card snape = new Card(4 ,2,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("DamageRandomTargets",1,3)));
}
