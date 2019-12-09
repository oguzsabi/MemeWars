import java.security.SecureRandom;
import java.util.ArrayList;

public class Event {
    private final String eventDeath = "Death";
    private final String eventAttack = "Attack";
    private final String eventDefense = "Defense";
    private final String eventPlay = "Play";
    private boolean effectActivated = true;
    private User user;
    SecureRandom random;

    public Event(User user) {
        this.user = user;
    }

    public void damageEveryone(int damageAmount) {
        ArrayList<ArrayList<Card>> twoDimensional = Table.cards;
        for (ArrayList player: twoDimensional) {
            for (int i = 0; i < player.size(); i++) {
                Card card = (Card) player.get(i);
                card.setHealth(card.getHealth() - damageAmount);
                if (card.getHealth() <= 0)
                    activateCardEffect(card);
            }
        }
    }

    public void damageTarget(Card targetCard, int damageAmount) {
        targetCard.setHealth(targetCard.getHealth() - damageAmount);
        if (targetCard.getHealth() <= 0)
            activateCardEffect(targetCard);
    }

    public void damageRandomTargets(int numberOfTargets, int damageAmount, boolean onlyOpponent) {
        ArrayList<ArrayList<Card>> twoDimensional = Table.cards;

        for (int i = 0; i < numberOfTargets; i++) {
            if (!onlyOpponent) {
                int targetPlayer = random.nextInt(1);
                ArrayList<Card> cardsOfTargetPlayer = twoDimensional.get(targetPlayer);
                int targetCardIndex = random.nextInt(cardsOfTargetPlayer.size());
                Card targetCard = cardsOfTargetPlayer.get(targetCardIndex);
                targetCard.setHealth(targetCard.getHealth() - damageAmount);

                if (targetCard.getHealth() <= 0)
                    activateCardEffect(targetCard);
            } else {
                int targetPlayer = user.getID();
                ArrayList<Card> cardsOfTargetPlayer = twoDimensional.get(targetPlayer);
                int targetCardIndex = random.nextInt(cardsOfTargetPlayer.size());
                Card targetCard = cardsOfTargetPlayer.get(targetCardIndex);
                targetCard.setHealth(targetCard.getHealth() - damageAmount);

                if (targetCard.getHealth() <= 0)
                    activateCardEffect(targetCard);
            }
        }
    }

    public void healEveryone(int healAmount) {
        ArrayList<ArrayList<Card>> twoDimensional = Table.cards;
        for (ArrayList player: twoDimensional) {
            for (int i = 0; i < player.size(); i++) {
                Card card = (Card) player.get(i);
                card.setHealth(card.getHealth() + healAmount);
            }
        }
    }

    public void healTarget(Card targetCard, int healAmount) {
        targetCard.setHealth(targetCard.getHealth() + healAmount);
    }

    public void healRandomTargets(int numberOfTargets, int healAmount, boolean onlySelf) {
        ArrayList<ArrayList<Card>> twoDimensional = Table.cards;

        for (int i = 0; i < numberOfTargets; i++) {
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
    }

    public void spawnMinion(int numberOfMinionsToBeSpawned, int damage, int health) {
        for (int i = 0; i < numberOfMinionsToBeSpawned; i++) {
            Card cardToBeSpawned = DefaultCards.defaultMinion;
            cardToBeSpawned.setHealth(health);
            cardToBeSpawned.setDamage(damage);
            Table.cards.get(0).add(cardToBeSpawned);
        }
    }

    public void decreaseDamageOfEveryone(int decreaseAmount) {

    }

    public void battle(Card attacker, Card defender) {
        System.out.println("in battle");
        attacker.setTarget(defender);
        defender.setTarget(attacker);

        System.out.println("Attacker Damage :" + attacker.getDamage());
        System.out.println("Defender Damage :" + defender.getDamage());

        attacker.setHealth(attacker.getHealth() - defender.getDamage());
        if (attacker.hashCode() != defender.hashCode())
            defender.setHealth(defender.getHealth() - attacker.getDamage());

        if (attacker.getHealth() <= 0)
            activateCardEffect(attacker);

        if (defender.getHealth() <= 0)
            activateCardEffect(defender);
    }

    public void activateCardEffect(Card card) {

        System.out.println("Card effect of " + card + " is activated.");
        ArrayList<Object> effectDetails = card.getEffectDetails();
//                Object[] event = {"DamageEveryone", 1};
//                Object[] event = {"DamageTarget", 1};
//                Object[] event = {"DamageRandomTargets", 2, 1, false};

        if (effectDetails != null && effectActivated) {
            switch ((String)effectDetails.get(0)) {
                case "DamageEveryone":
                    effectActivated = false;
                    damageEveryone((int)effectDetails.get(1));
                    break;
                case "DamageTarget":
                    effectActivated = false;
                    damageTarget(card.getTarget(), (int)effectDetails.get(1));
                    break;
                case "DamageRandomTargets":
                    effectActivated = false;
                    damageRandomTargets((int)effectDetails.get(1), (int)effectDetails.get(2), (boolean)effectDetails.get(3));
                    break;
                case "HealEveryone":
                    effectActivated = false;
                    healEveryone((int)(effectDetails.get(1)));
                    break;
                case "HealTarget":
                    effectActivated = false;
                    healTarget(card.getTarget(), (int)effectDetails.get(1));
                    break;
                case "HealRandomTargets":
                    effectActivated = false;
                    healRandomTargets((int)effectDetails.get(1), (int)effectDetails.get(2), (boolean)effectDetails.get(3));
                    break;
                case "DecreaseDamageOfEveryone":

                    break;
                case "SpawnMinion":
                    effectActivated = false;
                    spawnMinion((int)effectDetails.get(1), (int)effectDetails.get(2), (int)effectDetails.get(3));
                    break;
            }
        }
    }

//    public void activateBattleCry(Card card) {
//        ArrayList<Object> eventDetails = card.getEventDetails();
//        switch ((String)eventDetails.get(0)) {
//            case "DamageEveryone":
//                damageEveryone((int)eventDetails.get(1));
//                break;
//            case "DamageTarget":
//                damageTarget(new Card(), (int)eventDetails.get(1));
//                break;
//            case "DamageRandomTargets":
//                damageRandomTargets((int)eventDetails.get(1), (int)eventDetails.get(2), (boolean)eventDetails.get(3));
//                break;
//            case "HealEveryone":
//                healEveryone((int)(eventDetails.get(1)));
//                break;
//            case "HealTarget":
//                healTarget(new Card(), (int)eventDetails.get(1));
//                break;
//            case "HealRandomTargets":
//                healRandomTargets((int)eventDetails.get(1), (int)eventDetails.get(2), (boolean)eventDetails.get(3));
//                break;
//        }
//    }
}
