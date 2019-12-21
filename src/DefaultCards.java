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
        cards.add(slapsRoof);
        cards.add(confusedGandalf);
        cards.add(screamingPatrick);
        cards.add(chuckles);
        cards.add(oprah);
        cards.add(fuckMeRight);
        cards.add(sike);
        cards.add(thirdworld);
        cards.add(trollFace);
        cards.add(lambSauce);
        cards.add(frodo);
        cards.add(snape);
        cards.add(howAboutNo);
        cards.add(imgonnaFind);
        cards.add(absolute);
        cards.add(FBI);
        cards.add(arabicSquad);
        cards.add(ohBoy);
        cards.add(flexTape);
        cards.add(aight);
        cards.add(doge);
        cards.add(cat);
        cards.add(tooDamn);
        cards.add(hereIs);
        cards.add(gollum);
        cards.add(obiWann);
        cards.add(bender);
        cards.add(boromir);
        cards.add(minions);
        cards.add(chuckNorris);
        cards.add(ipulled);
        cards.add(isYouDead);


    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public static Card defaultMinion = new Card("Minion", 1, 1, 1,"", true, false,true,true, null);

    private Card youHaveNoPowerHere = new Card("You Have No Power Here",8, 4, 4,"BC", true, false, true, true, new ArrayList<>(Arrays.asList("DecreaseDamageEveryone", 1)));
    private Card whyUNoAttack = new Card("Why U No Attack",3, 2, 3,"BC", true, true,true,true, new ArrayList<>(Arrays.asList("DamageTarget", 1)));
    private Card heNeedSomeMilk = new Card("He Need Some Milk", 3,4,3,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("HealTarget",1)));
    private Card soAnyWay = new Card("So Anyway", 3,3,2,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageRandomTargets",3,1)));
    private Card kobe = new Card("Kobe", 3,2,2,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageTarget",2)));
    private Card civilWar = new Card("Civil War", 4,2,5,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageTarget",2)));
    private Card djKhaled = new Card("DJ Khaled", 5,2,4,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("SpawnMinion",1,2,4)));
    private Card billCosby = new Card("Bill Cosby", 3,3,4,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("Sleep",1)));
    private Card badLuck = new Card("Bad Luck", 2,2,2,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("SpawnMinion",1,2,2)));
    private Card itsRaw = new Card("It's Raw", 2,2,2,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DecreaseDamageOfTarget",2)));
    private Card slapsRoof = new Card("Slaps roof", 4,2,4,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageTarget",2)));
    private Card confusedGandalf = new Card("Confused Gandalf", 2,1,2,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageRandomTargets",3,1)));
    private Card screamingPatrick = new Card("Screaming Patrick", 1,1,1,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageEveryone",1)));
    private Card chuckles = new Card("Chuckles", 1,1,1,"BC", false,false,true,true, null);
    private Card oprah = new Card("Oprah",5,4,8,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("DamageEveryone",1)));
    private Card fuckMeRight = new Card("**** Me Right", 5,6,12,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("DestroyRandom",1)));
    private Card sike = new Card("Sike", 4 ,3,2,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("SpawnMinion",1,3,2)));
    private Card thirdworld = new Card("Third World", 3 ,3,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("SpawnMinion",3,1,1)));
    private Card trollFace = new Card("Troll Face", 6 ,4,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("SpawnMinion",2,3,3)));
    private Card lambSauce = new Card("Lamb Sauce", 4 ,2,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("SpawnMinion",2,2,2)));
    private Card frodo = new Card("Frodo", 2,2,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("DamageRandomTarget",1,3)));
    private Card snape = new Card("Snape", 5 ,4,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("SpawnMinion",1,2)));
    private Card howAboutNo = new Card("How About No", 4 ,3,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("SpawnMinion",3,3)));
    private Card imgonnaFind = new Card("I'm gonna Find you", 4 ,3,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("DestroyRandomEnemyCard")));
    private Card absolute = new Card("Absolute", 4 ,3,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("DamageHero",1)));
    private Card FBI = new Card("FBI", 4 ,3,2,"NaN", false,true,true,true,  null);
    private Card arabicSquad = new Card("Arabic Squad", 4 ,3,2,"NaN", false,false,true,true,  new ArrayList<>(Arrays.asList("DecreaseDamageEveryone", 1)));
    private Card ohBoy = new Card("Oh boy ", 5 ,4,2,"NaN", false,true,true,true,  null);
    private Card flexTape = new Card("Flex Tape", 8 ,8,2,"NaN", false,false,true,true,  null);
    private Card aight = new Card("Aight ", 4 ,3,2,"NaN", false,true,true,true,  null);
    private Card doge = new Card("Doge ", 1 ,2,1,"NaN", false,false,true,true,  null);
    private Card cat = new Card("Cat ", 1 ,2,1,"NaN", false,false,true,true,  null);
    private Card tooDamn = new Card("Too Damn High ", 6 ,2,6,"NaN", false,false,true,true,  null);
    private Card hereIs = new Card("Here is Johnny ", 2 ,3,1,"NaN", false,true,true,true,  null);
    private Card gollum = new Card("Gollum ", 3 ,2,3,"NaN", false,false,true,true,  null);
    private Card obiWann = new Card("Obi Wann ", 5 ,5,5,"NaN", false,false,true,true,  null);
    private Card bender = new Card("Bender", 2 ,1,3,"NaN", false,false,true,true,  null);
    private Card boromir = new Card("Boromir", 5 ,5,5,"NaN", false,false,true,true,  null);
    private Card minions = new Card("minions", 1 ,1,1,"NaN", false,false,true,true,  null);
    private Card chuckNorris = new Card("Chuck Norris", 8,8,2,"NaN", false,false,true,true,  null);
    private Card ipulled = new Card("I pulled", 3,2,3,"NaN", false,false,true,true,  null);
    private Card isYouDead = new Card("Is you dead", 2,1,3,"NaN", false,false,true,true,  null);


}
