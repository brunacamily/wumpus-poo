import java.awt.Point;

public class Pit {
    private Point position;
    private boolean isPitFilled; 

    public Pit(Point newPosition ) {
        position = newPosition;
        isPitFilled = false;
    }
    
    public void tamparPoço(){
        isPitFilled = true;
    }
    
    public Point getPosition() {
        return position;
    }
    
    public boolean isPitFilled() {
        return isPitFilled;
    }
}