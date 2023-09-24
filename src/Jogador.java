
public class Jogador extends Agente {
  private int health;
  private int battery;
  private int arrows;
  private int woods;
  private boolean hasGold;

  public Jogador() {
    health = 10;
    battery = 2;
    arrows = 0;
    hasGold = false;
    this.setId("Jogador");
  }

  public void craftArrow() {
    woods--;
    arrows++;
  }

  public void removeWood() {
    woods--;
  }

  public void addWood() {
    woods++;
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

  public void useBattery() {
    battery--;
  }

  public int getBattery() {
    return battery;
  }

  public int getArrows() {
    return arrows;
  }

  public int getWoods() {
    return woods;
  }

  public boolean hasGold() {
    return hasGold;
  }
}
