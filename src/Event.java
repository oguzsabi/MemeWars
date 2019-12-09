import java.util.ArrayList;

public class Event {
    private final String eventDeath = "Death";
    private final String eventAttack = "Attack";
    private final String eventDefense = "Defense";
    private final String eventPlay = "Play";

    public void doDamageToEveryone(int damageAmount) {
        ArrayList<ArrayList<Card>> twoDimensional = Table.getCards();
        for (ArrayList player: twoDimensional) {
            for (Card card: player) {
                card.setHealth(card.getHealth() - damageAmount);
                if (attacker.getHealth() <= 0 && attacker.getEvent().equals("DR"))
                    activateDeathRattle(card);
            }
        }
    }

    public void doDamageToTarget(Card targetCard, int damageAmount) {
        targetCard.setHealth(targetCard.getHealth() - damageAmount);
    }

    public void spawnMinionCard(int numberOfMinionsToBeSpawned, int damage, int health) {
        for (int i = 0; i < numberOfMinionsToBeSpawned; i++) {
            cardToBeSpawned = DefaultCards.getCards().get(61);
            cardToBeSpawned.setHealth(health);
            cardToBeSpawned.setDamage(damage);
            Table.getCards().get(0).add(cardToBeSpawned);
        }
    }

    public void attack(Card attacker, Card defender) {
        attacker.setHealth(attacker.getHealth() - defender.getDamage());
        defender.setHealth(defender.getHealth() - attacker.getDamage());

        if (attacker.getHealth() <= 0 && attacker.getEvent().equals("DR"))
            activateDeathRattle(card);
    }

    public void activateDeathRattle(Card card) {

        String[] eventDetails = card.getEvent();
//                Object[] event = {"DamageEveryone", 1};
//                Object[] event = {"DamageTarget", 1};

        switch (eventDetails[0]) {
            case "DamageEveryone":
                doDamageToEveryone(eventDetails[1]);
                break;
            case "DamageTarget":
                doDamageToTarget("defender", eventDetails[1]);
        }
    }
}
