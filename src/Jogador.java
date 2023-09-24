
public class Jogador extends Agente {
  private int health;
  private int battery;
  private int arrows;
  private boolean hasGold;
  private boolean hasCarryingWood;

  public Jogador() {
    health = 2;
    battery = 2;
    arrows = 0;
    hasGold = false;
    hasCarryingWood = false;
    this.setId("Jogador");
  }

  public void addArrow() {
    arrows++;
  }

  public void addGold() {
    hasGold = true;
  }

  public void fireArrow() {
    arrows--;
  }

  public int getHealth() {
    return health;
  }

  public void takeDamage(int damage) {
    health = Math.max(0, health - damage);
  }

  public int getBattery() {
    return battery;
  }

  public int getArrows() {
    return arrows;
  }

  public boolean hasGold() {
    return hasGold;
  }

  public boolean isCarryingWood() {
    return hasCarryingWood;
  }
}
