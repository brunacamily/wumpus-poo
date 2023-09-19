
import java.awt.Point;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bruna
 */

public class Lumpus extends Wumpus {    
  public Lumpus(Point newPosition){
      super(newPosition);
  }
  
  @Override
  public int attackPlayer(int life){
      life = Math.floorDiv(life, 2);
      return life;
  }  
}