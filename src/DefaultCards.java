import java.util.ArrayList;
import java.util.Arrays;

public class DefaultCards {
    public ArrayList<Card> cards;

    public DefaultCards() {
        cards = new ArrayList<>();
        // All cards are added to cards ArrayList in order to reach from other classes
        cards.add(youHaveNoPowerHere); // 0
        cards.add(whyUNoAttack); // 1
        cards.add(heNeedSomeMilk); // 2
        cards.add(soAnyWay); // 3
        cards.add(kobe); // 4
        cards.add(civilWar); // 5
        cards.add(djKhaled); // 6
        cards.add(billCosby); // 7
        cards.add(badLuck); // 8
        cards.add(itsRaw); // 9
        cards.add(slapsRoof); // 10
        cards.add(confusedGandalf); // 11
        cards.add(screamingPatrick); // 12
        cards.add(chuckles); // 13
        cards.add(oprah); // 14
        cards.add(fuckMeRight); // 15
        cards.add(sike); // 16
        cards.add(thirdworld); // 17
        cards.add(trollFace); // 18
        cards.add(lambSauce); // 19
        cards.add(frodo); // 20
        cards.add(snape); // 21
        cards.add(howAboutNo); // 22
        cards.add(imgonnaFind); // 23
        cards.add(absolute); // 24
        cards.add(FBI); // 25
        cards.add(arabicSquad); // 26
        cards.add(ohBoy); // 27
        cards.add(flexTape); // 28
        cards.add(aight); // 29
        cards.add(doge); // 30
        cards.add(cat); // 31
        cards.add(tooDamn); // 32
        cards.add(hereIs); // 33
        cards.add(gollum); // 34
        cards.add(obiWann); // 35
        cards.add(bender); // 36
        cards.add(boromir); // 37
        cards.add(minions); // 38
        cards.add(chuckNorris); // 39
        cards.add(ipulled); // 40
        cards.add(isYouDead); // 41


    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public static Card defaultMinion = new Card("Minion","", 1, 1, 1,"", true, false,true,true, null);
    // All cards are created here
    private Card youHaveNoPowerHere = new Card("You Have No Power Here","Images/YouHaveNoPowerHere.png",6, 4, 4,"BC", true, false, true, true, new ArrayList<>(Arrays.asList("DecreaseDamageEveryone", 1)));
    private Card whyUNoAttack = new Card("Why U No Attack","Images/YuNo.png",3, 2, 3,"BC", true, true,true,true, new ArrayList<>(Arrays.asList("DamageTarget", 1)));
    private Card heNeedSomeMilk = new Card("He Need Some Milk","Images/HeNeedSomeMilk.png", 3,4,3,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("HealTarget",1)));
    private Card soAnyWay = new Card("So Anyway","Images/SoAnyWay.png", 3,3,2,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageRandomTargets",3,1)));
    private Card kobe = new Card("Kobe","Images/Kobe.png", 3,2,2,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageTarget",2)));
    private Card civilWar = new Card("Civil War","Images/CivilWar.png", 4,2,5,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageTarget",2)));
    private Card djKhaled = new Card("DJ Khaled","Images/DjKhaled.png", 5,2,4,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("SpawnMinion",1,2,4)));
    private Card billCosby = new Card("Bill Cosby","Images/BillCosby.png", 3,3,4,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("Sleep",1)));
    private Card badLuck = new Card("Bad Luck","Images/BadLuckBrian.png", 2,2,2,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("SpawnMinion",1,2,2)));
    private Card itsRaw = new Card("It's Raw","Images/ItsRaw.jpg", 2,2,2,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DecreaseDamageOfTarget",2)));
    private Card slapsRoof = new Card("Slaps roof","Images/SlapsRoof.png", 4,2,4,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageTarget",2)));
    private Card confusedGandalf = new Card("Confused Gandalf","Images/ConfusedGandalf.png" ,2,1,2,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageRandomTargets",3,1)));
    private Card screamingPatrick = new Card("Screaming Patrick","Images/ScreamingPatrick.png", 1,1,1,"BC", true,false,true,true, new ArrayList<>(Arrays.asList("DamageEveryone",1)));
    private Card chuckles = new Card("Chuckles","Images/ImInDanger.png", 1,1,1,"BC", false,false,true,true, null);
    private Card oprah = new Card("Oprah","Images/Oprah.png",5,4,8,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("DamageEveryone",1)));
    private Card fuckMeRight = new Card("**** Me Right","Images/FuckMeRight.png", 5,6,12,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("DestroyRandom",1)));
    private Card sike = new Card("Sike","Images/SikeNigga.png", 4 ,3,2,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("SpawnMinion",1,3,2)));
    private Card thirdworld = new Card("Third World","Images/ThirldWorldSuccess.png", 3 ,3,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("SpawnMinion",3,1,1)));
    private Card trollFace = new Card("Troll Face","Images/TrollFace.png", 6 ,4,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("SpawnMinion",2,3,3)));
    private Card lambSauce = new Card("Lamb Sauce","Images/LambSauce.png", 4 ,2,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("SpawnMinion",2,2,2)));
    private Card frodo = new Card("Frodo","Images/Frodo.png", 2,2,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("DamageRandomTarget",1,3)));
    private Card snape = new Card("Snape","Images/Snape.png", 5 ,4,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("SpawnMinion",1,2)));
    private Card howAboutNo = new Card("How About No","Images/HowAboutNo.png", 4 ,3,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("SpawnMinion",3,3)));
    private Card imgonnaFind = new Card("I'm gonna Find you","Images/ImGonnaFindYou.png", 4 ,3,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("DestroyRandomEnemyCard")));
    private Card absolute = new Card("Absolute","Images/AbsoluteWin.png", 4 ,3,3,"DR", false,false,true,true,  new ArrayList<>(Arrays.asList("DamageHero",1)));
    private Card FBI = new Card("FBI","Images/FBI.png", 4 ,3,2,"NaN", false,true,true,true,  null);
    private Card arabicSquad = new Card("Arabic Squad","Images/ArabicSquad.png", 4 ,3,2,"NaN", false,false,true,true,  new ArrayList<>(Arrays.asList("DecreaseDamageEveryone", 1)));
    private Card ohBoy = new Card("Oh boy","Images/OhBoy.png", 5 ,4,2,"NaN", false,true,true,true,  null);
    private Card flexTape = new Card("Flex Tape","Images/FlexTape.png", 8 ,8,2,"NaN", false,false,true,true,  null);
    private Card aight = new Card("Aight","Images/Aight.png", 4 ,3,2,"NaN", false,true,true,true,  null);
    private Card doge = new Card("Doge","Images/Doge.png", 1 ,2,1,"NaN", false,false,true,true,  null);
    private Card cat = new Card("Cat","Images/Cat.png", 1 ,2,1,"NaN", false,false,true,true,  null);
    private Card tooDamn = new Card("Too Damn High","Images/TooDamnHigh.png", 6 ,2,6,"NaN", false,false,true,true,  null);
    private Card hereIs = new Card("Here is Johnny","Images/HeresJohnny.png", 2 ,3,1,"NaN", false,true,true,true,  null);
    private Card gollum = new Card("Gollum","Images/Gollum.png", 3 ,2,3,"NaN", false,false,true,true,  null);
    private Card obiWann = new Card("Obi Wann","Images/ObiWann.png", 5 ,5,5,"NaN", false,false,true,true,  null);
    private Card bender = new Card("Bender","Images/Bender.png", 2 ,1,3,"NaN", false,false,true,true,  null);
    private Card boromir = new Card("Boromir","Images/Boromir.png", 5 ,5,5,"NaN", false,false,true,true,  null);
    private Card minions = new Card("minions","Images/Minions.png", 1 ,1,1,"NaN", false,false,true,true,  null);
    private Card chuckNorris = new Card("Chuck Norris","Images/ChuckNorris.png", 8,8,2,"NaN", false,false,true,true,  null);
    private Card ipulled = new Card("I pulled","Images/Ipulled.png", 3,2,3,"NaN", false,false,true,true,  null);
    private Card isYouDead = new Card("Is you dead","Images/IsYouDead.png", 2,1,3,"NaN", false,false,true,true,  null);


}
