
import java.awt.Point;

public class Wumpus {
  private Point position;

  public Point getPosition() {
    return position;
  }

  public void setPosition(Point newPosition) {
    position = newPosition;
  }

  public int attackPlayer(int life) {
    life = 0;
    return life;
  }

  public void killWumpus() {
    System.out.println("Wumpus morreu");
  }
}
