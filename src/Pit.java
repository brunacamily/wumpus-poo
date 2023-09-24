public class Pit extends Entity {
  private boolean isPitFilled;

  public Pit() {
    isPitFilled = false;
  }

  public void fillPit() {
    isPitFilled = true;
  }

  public boolean isPitFilled() {
    return isPitFilled;
  }
}
