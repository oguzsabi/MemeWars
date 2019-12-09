import java.security.SecureRandom;
import java.util.ArrayList;

public class Event {
    private final String eventDeath = "Death";
    private final String eventAttack = "Attack";
    private final String eventDefense = "Defense";
    private final String eventPlay = "Play";
    SecureRandom random;

    public void damageEveryone(int damageAmount) {
        ArrayList<ArrayList<Card>> twoDimensional = Table.getCards();
        for (ArrayList player: twoDimensional) {
            for (Card card: player) {
                card.setHealth(card.getHealth() - damageAmount);
                if (attacker.getHealth() <= 0 && attacker.getEvent().equals("DR"))
                    activateDeathRattle(card);
            }
        }
    }

    public void damageTarget(Card targetCard, int damageAmount) {
        targetCard.setHealth(targetCard.getHealth() - damageAmount);

        if (attacker.getHealth() <= 0 && attacker.getEvent().equals("DR"))
            activateDeathRattle(card);
    }

    public void damageRandomTargets(int numberOfTargets, int damageAmount, boolean onlyOpponent) {
        ArrayList<ArrayList<Card>> twoDimensional = Table.getCards();

        if (!onlyOpponent) {
            int targetPlayer = random.nextInt(1);
            ArrayList<Card> cardsOfTargetPlayer = twoDimensional.get(targetPlayer);
            int targetCardIndex = random.nextInt(cardsOfTargetPlayer.size());
            Card targetCard = cardsOfTargetPlayer.get(targetCardIndex);
            targetCard.setHealth(targetCard.getHealth() - damageAmount);

            if (attacker.getHealth() <= 0 && attacker.getEvent().equals("DR"))
                activateDeathRattle(card);
        } else {
            int targetPlayer = user.getID();
            ArrayList<Card> cardsOfTargetPlayer = twoDimensional.get(targetPlayer);
            int targetCardIndex = random.nextInt(cardsOfTargetPlayer.size());
            Card targetCard = cardsOfTargetPlayer.get(targetCardIndex);
            targetCard.setHealth(targetCard.getHealth() - damageAmount);

            if (attacker.getHealth() <= 0 && attacker.getEvent().equals("DR"))
                activateDeathRattle(card);
        }
    }

    public void healEveryone(int healAmount) {
        ArrayList<ArrayList<Card>> twoDimensional = Table.getCards();
        for (ArrayList player: twoDimensional) {
            for (Card card: player) {
                card.setHealth(card.getHealth() + healAmount);
            }
        }
    }

    public void healTarget(Card targetCard, int healAmount) {
        targetCard.setHealth(targetCard.getHealth() + healAmount);
    }

    public void healRandomTargets(int numberOfTargets, int healAmount, boolean onlySelf) {
        ArrayList<ArrayList<Card>> twoDimensional = Table.getCards();

        if (!onlySelf) {
            int targetPlayer = random.nextInt(1);
            ArrayList<Card> cardsOfTargetPlayer = twoDimensional.get(targetPlayer);
            int targetCardIndex = random.nextInt(cardsOfTargetPlayer.size());
            Card targetCard = cardsOfTargetPlayer.get(targetCardIndex);
            targetCard.setHealth(targetCard.getHealth() + healAmount);
        } else {
            int targetPlayer = user.getID();
            ArrayList<Card> cardsOfTargetPlayer = twoDimensional.get(targetPlayer);
            int targetCardIndex = random.nextInt(cardsOfTargetPlayer.size());
            Card targetCard = cardsOfTargetPlayer.get(targetCardIndex);
            targetCard.setHealth(targetCard.getHealth() + healAmount);
        }
    }

    public void spawnMinionCard(int numberOfMinionsToBeSpawned, int damage, int health) {
        for (int i = 0; i < numberOfMinionsToBeSpawned; i++) {
            cardToBeSpawned = DefaultCards.getCards().get(61);
            cardToBeSpawned.setHealth(health);
            cardToBeSpawned.setDamage(damage);
            Table.getCards().get(0).add(cardToBeSpawned);
        }
    }

    public void battle(Card attacker, Card defender) {
        attacker.setHealth(attacker.getHealth() - defender.getDamage());
        defender.setHealth(defender.getHealth() - attacker.getDamage());

        if (attacker.getHealth() <= 0 && attacker.getEvent().equals("DR"))
            activateDeathRattle(attacker);

        if (defender.getHealth() <= 0 && defender.getEvent().equals("DR"))
            activateDeathRattle(defender);
    }

    public void activateDeathRattle(Card card) {

        ArrayList<Object> eventDetails = card.getEventDetails();
//                Object[] event = {"DamageEveryone", 1};
//                Object[] event = {"DamageTarget", 1};
//                Object[] event = {"DamageRandomTargets", 2, 1, false};

        switch ((String)eventDetails.get(0)) {
            case "DamageEveryone":
                damageEveryone((int)eventDetails.get(1));
                break;
            case "DamageTarget":
                damageTarget(new Card(), (int)eventDetails.get(1));
                break;
            case "DamageRandomTargets":
                damageRandomTargets((int)eventDetails.get(1), (int)eventDetails.get(2), (boolean)eventDetails.get(3));
                break;
            case "HealEveryone":
                healEveryone((int)(eventDetails.get(1)));
                break;
            case "HealTarget":
                healTarget(new Card(), (int)eventDetails.get(1));
                break;
            case "HealRandomTargets":
                healRandomTargets((int)eventDetails.get(1), (int)eventDetails.get(2), (boolean)eventDetails.get(3));
                break;
        }
    }
}
