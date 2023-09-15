import java.awt.Point;

public class Jogador extends Agente {
    private int vida;
    private int lanterna;
    private boolean flechas;
    private Ouro ouro;
    private Madeira madeira;
    
    public Jogador(){
        vida = 10;
        lanterna = 2;
        flechas = true;
        ouro = new Ouro();
        madeira = new Madeira();
    }
    
    public void pegarMadeira(){
        this.madeira.setMadeira();
    }
    
    public void pegarouro(){
        this.ouro.setOuro();
    }
    
    public void flechadaWmpus(Wumpus w){
        w.matarWumpus();
    }
    
    public void flechadaWmpus(Wumpus2 w){
        w.matarWumpus();
    }
}