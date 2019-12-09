import java.util.ArrayList;

public class Card {
    private int banana;
    private int damage;
    private int health;
    private String event;
    private ArrayList<Object> eventDetails;
    private boolean attackable;
    private boolean active;
    private Card target;

    public int getBanana() {
        return banana;
    }

    public void setBanana(int banana) {
        this.banana = banana;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public ArrayList<Object> getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(ArrayList<Object> eventDetails) {
        this.eventDetails = eventDetails;
    }

    public boolean isAttackable() {
        return attackable;
    }

    public void setAttackable(boolean attackable) {
        this.attackable = attackable;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Card getTarget() {
        return target;
    }

    public void setTarget(Card target) {
        this.target = target;
    }
}
