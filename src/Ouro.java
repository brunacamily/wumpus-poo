
import java.awt.Point;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bruna
 */
public class Ouro {
    private int ouro;
    private Point position;
    
    public Ouro(){
        
    }
    public Ouro(Point newPosition){
        ouro = 1;
        position = newPosition;
    }
    
    public Point getPosition() {
      return position;
    }
    
    public int  getOuro(){
        return this.ouro;
    }
    
    public void setOuro(){
        ouro++;
    }
}
