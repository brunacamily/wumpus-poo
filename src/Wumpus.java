
import java.awt.Point;

public class Wumpus {
  private Point position;
  private boolean energia;

  public Wumpus(){
      energia = true;
  }
  
  public Point getPosition() {
    return position;
  }

  public void setPosition(Point newPosition) {
    position = newPosition;
  }
  
  public int danoWumpus(int vida){
      vida = 0;
      return vida;
  }
  
  public boolean matarWumpus(){
      energia = false;
      return energia;
  }
  
  
}
