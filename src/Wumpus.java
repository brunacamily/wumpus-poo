public class Wumpus extends Agente {
  private int power;
  private boolean isDead;

  public Wumpus() {
    this.setId("Wumpus");
    this.setAuraId("Fedor");
    setPower(10);
    isDead = false;
  }

  public void killWumpus() {
    System.out.println("Wumpus morreu");
  }

  public int getPower() {
    return power;
  }

  public void setPower(int power) {
    this.power = power;
  }

  public boolean isDead() {
    return isDead;
  }

  public void die() {
    isDead = true;
    System.out.println("GAHRRRR");
  }
}
