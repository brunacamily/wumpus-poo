
import java.awt.Point;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bruna
 */
public class Item {
    private Point position;
    
    public Item(Point newPosition){
        position = newPosition;
    }
    
    public Point getPosition() {
    return position;
    }   
}
