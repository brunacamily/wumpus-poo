
import java.awt.Point;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bruna
 */
public class Poço extends Item {
    private boolean ativo; 
// se a madeira e o poço destiverem na msm posição o poço fica inativo 
    
    public Poço( Point newPosition ) {
        super(newPosition);
        ativo = true;
    }
    
    public void tamparPoço(Madeira m){
        if(this.getPosition() == m.getPosition())
            ativo = false;
    }
    
}