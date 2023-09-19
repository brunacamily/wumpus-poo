
public class Jogador extends Agente {
    private int health;
    private int battery;
    private int arrows;
    private boolean hasGold;
    private int woods;
    private boolean hasCarryingWood;
    
    public Jogador(){
        health = 10;
        battery = 2;
        arrows = 0;
        hasGold = false;
        woods = 0;
        hasCarryingWood = false;
    }
    
    public void addWood() {
        woods++;
    }
    
    public void addArrow(){
        arrows++;
    }
    
    public void addGold(){
        hasGold = true;
    }
    
    public void arrowAttack(Wumpus wumpus){
        arrows--;
        // TODO: Checar se wumpus está na posição de disparo
        if (true) {
            wumpus.killWumpus();
        } 
    }

    public int getHealth() {
        return health;
    }

    public int getBattery() {
        return battery;
    }

    public int getArrows() {
        return arrows;
    }

    public boolean isHasGold() {
        return hasGold;
    }

    public int getWoods() {
        return woods;
    }

    public boolean isHasCarryingWood() {
        return hasCarryingWood;
    }
}