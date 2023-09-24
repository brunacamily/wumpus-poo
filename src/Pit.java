public class Pit extends Entity {
  public final String id = "Poço";
  private boolean isPitFilled;

  public Pit() {
    isPitFilled = false;
    this.setId("Poço");
    this.setAuraId("Brisa");
  }

  public void fillPit() {
    isPitFilled = true;
  }

  public boolean isPitFilled() {
    return isPitFilled;
  }
}
