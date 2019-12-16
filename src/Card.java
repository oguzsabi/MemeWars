import java.util.ArrayList;

public class Card {
    private int banana;
    private int damage;
    private int health;
    private String effectType;
    private boolean attackable;
    private boolean active;
    private boolean effectNotActivated;
    private boolean firstTimePlayed;
    private Card target;
    private ArrayList<Object> effectDetails;

    public Card(int banana, int damage, int health, String effectType, boolean attackable, boolean active, boolean effectNotActivated,boolean firstTimePlayed, ArrayList<Object> effectDetails) {
        this.banana = banana;
        this.damage = damage;
        this.health = health;
        this.effectType = effectType;
        this.attackable = attackable;
        this.active = active;
        this.effectNotActivated = effectNotActivated;
        this.firstTimePlayed = firstTimePlayed;
        this.effectDetails = effectDetails;
    }

    public Card(ArrayList<Object> cardDetails) {
        this.banana = (int) cardDetails.get(0);
        this.damage = (int) cardDetails.get(1);
        this.health = (int) cardDetails.get(2);
        this.effectType = (String)cardDetails.get(3);
        this.attackable = (boolean) cardDetails.get(4);
        this.active = (boolean) cardDetails.get(5);
        this.effectNotActivated = (boolean)cardDetails.get(6);
        this.effectDetails = (ArrayList<Object>) cardDetails.get(7);
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

    public boolean isEffectNotActivated() {
        return effectNotActivated;
    }

    public void setEffectNotActivated(boolean effectNotActivated) {
        this.effectNotActivated = effectNotActivated;
    }

    public String getEffectType() {
        return effectType;
    }

    public void setEffectType(String effectType) {
        this.effectType = effectType;
    }

    public boolean isFirstTimePlayed() {
        return firstTimePlayed;
    }

    public void setFirstTimePlayed(boolean firstTimePlayed) {
        this.firstTimePlayed = firstTimePlayed;
    }

    @Override
    public String toString() {
        return String.format("Banana: %d, Damage: %d, Health: %d, Effect Type : %s, Attackable: %b, Active: %b", banana, damage, health, effectType, attackable, active);
    }
}
