
import java.awt.Point;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bruna
 */
public class Madeira extends Item {
    private int madeira;
   
    public Madeira(Point newPosition){
        super(newPosition);
        madeira = 0; 
    }
    
    public int  getMadeira(){
        return this.madeira;
    }
    
    public void setMadeira(){
        madeira++;
    }
}
