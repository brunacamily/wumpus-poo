public class Wumpus extends Agente {
  private int power;

  public Wumpus() {
    this.setId("Wumpus");
    this.setAuraId("Fedor");
    setPower(10);
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
}
