
import java.awt.Point;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bruna
 */
public class Ouro extends Item {
    private int ouro;

    public Ouro( Point newPosition ) {
        super(newPosition);
        this.ouro = 1;
    }
    
    public int  getOuro(){
        return this.ouro;
    }
    
    public void setOuro(){
        ouro++;
    }
}
