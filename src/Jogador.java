
public class Jogador extends Agente {
    private int vida;
    private int lanterna;
    private int flechas;
    private Ouro ouro;
    private Madeira madeira;
    
    public Jogador(){
        vida = 10;
        lanterna = 2;
        flechas = 1;
        ouro = new Ouro(this.getPosition());
        madeira = new Madeira(this.getPosition());
    }
    
    public void pegarMadeira(){
        this.madeira.setMadeira();
    }
    
    public void fazerFlecha(){
        this.flechas++;
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