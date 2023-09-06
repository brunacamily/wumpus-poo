package personagens;

public class Agente {
    
    private Grid grid;
    private Point position;
    
    public void setPosition( Point newPosition){
        if(!grid.isValidPosition(newPosition)){
            System.out.println("Posição inválida.");
            return;
        }
        position = newPosition;
    }

    public Point getPosition(Agente agent){
        return agent.position;
    }
}
