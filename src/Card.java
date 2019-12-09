import java.util.ArrayList;

public class Card {
    private int banana;
    private int damage;
    private int health;
    private boolean attackable;
    private boolean active;
    private Card target;
    private ArrayList<Object> effectDetails;

    public Card(int banana, int damage, int health, boolean attackable, boolean active, ArrayList<Object> effectDetails) {
        this.banana = banana;
        this.damage = damage;
        this.health = health;
        this.attackable = attackable;
        this.active = active;
        this.effectDetails = effectDetails;
    }

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

    public ArrayList<Object> getEffectDetails() {
        return effectDetails;
    }

    public void setEffectDetails(ArrayList<Object> effectDetails) {
        this.effectDetails = effectDetails;
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

    @Override
    public String toString() {
        return String.format("Banana: %d, Damage: %d, Health: %d, Attackable: %b, Active: %b", banana, damage, health, attackable, active);
    }
}
