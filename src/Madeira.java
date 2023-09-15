
import java.awt.Point;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bruna
 */
public class Madeira {
    private int madeira;
    private Point position;
    
    public Madeira(){
        
    }
    
    public Madeira(Point newPosition){
        madeira = 0;
        position = newPosition;
    }
    
    public Point getPosition() {
      return position;
    }
    
    public int  getMadeira(){
        return this.madeira;
    }
    
    public void setMadeira(){
        madeira++;
    }
}
