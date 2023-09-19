
import java.util.Scanner;
import java.awt.Point;

public class GameManager {
  private static final int GRID_SIZE = 15;
  private static final int INITIAL_POSITION_X = 0;
  private static final int INITIAL_POSITION_Y = 0;

  private static final String MOVE_UP = "1";
  private static final String MOVE_RIGHT = "2";
  private static final String MOVE_DOWN = "3";
  private static final String MOVE_LEFT = "4";

  private boolean gameOver = false;
  private int turnCount;
  
  private Jogador jogador;
  private Wumpus wumpus;
  private Grid grid;
  private Scanner scanner;

  public GameManager() {
    scanner = new Scanner(System.in);
  }

  public void startGame() {
    System.out.println("Game Started");
    jogador = new Jogador();
    jogador.setPosition(new Point(INITIAL_POSITION_X, INITIAL_POSITION_Y));
    wumpus = new Wumpus(new Point(14, 14));
    grid = new Grid(GRID_SIZE, new Point(INITIAL_POSITION_X, INITIAL_POSITION_Y));
    turnCount = 1;
    beginTurns();
  }

  public void endGame() {
    String input = "0";

    while (!input.equals("2")) {
      System.out.println("Fim de jogo. Deseja jogar novamente?");
      System.out.println("1. Sim");
      System.out.println("2. Não");
      input = scanner.nextLine();

      if (input.equals("1")) {
        startGame();
      }

      if (input.equals("2")) {
        scanner.close();
      }
    }
  }

  private void beginTurns() {
    while (!gameOver) {
      System.out.println();
      System.out.println("Turno " + turnCount);
      runPlayerTurn();
      runEnemyTurn();
      System.out.println("wumpus1: " + wumpus.getPosition());
      turnCount++;
    }

    endGame();
  }

  private void runPlayerTurn() {
    boolean hasInputSucceeded = false;

    while (!hasInputSucceeded) {
      System.out.println();
      System.out.printf("Posição atual: (%d, %d)\n", jogador.getPosition().x, jogador.getPosition().y);
      System.out.println("Escolha a direção para se mover:");
      System.out.println("1. Cima:");
      System.out.println("2. Direita:");
      System.out.println("3. Baixo:");
      System.out.println("4. Esquerda:");

      String input = scanner.nextLine();

      hasInputSucceeded = makeAction(input);
      if (!hasInputSucceeded) {
        System.out.println();
        System.out.println("Ação mal sucedida. Tente novamente.");
      }
    }
  }

  private void runEnemyTurn() {
    Boolean b = false;
    if ( (jogador.getPosition().x - wumpus.getPosition().x) < (jogador.getPosition().y - wumpus.getPosition().y) ) {
      if((jogador.getPosition().x - wumpus.getPosition().x) < 0) {  
        b = grid.isValidPosition(new Point( wumpus.getPosition().x - 1 , wumpus.getPosition().y ));
        if(b == false){
          wumpus.setPosition(new Point( wumpus.getPosition().x + 1 , wumpus.getPosition().y ));
        } else {
          wumpus.setPosition(new Point( wumpus.getPosition().x - 1 , wumpus.getPosition().y ));
        }
      }else{ 
        b = grid.isValidPosition(new Point( wumpus.getPosition().x + 1 , wumpus.getPosition().y ));
        if(b == false){
          wumpus.setPosition(new Point( wumpus.getPosition().x - 1 , wumpus.getPosition().y ));
        } else {
          wumpus.setPosition(new Point( wumpus.getPosition().x + 1 , wumpus.getPosition().y ));
        }
      }
    } else if ( (jogador.getPosition().x - wumpus.getPosition().x) > (jogador.getPosition().y - wumpus.getPosition().y) ) {
      if((jogador.getPosition().y - wumpus.getPosition().y) < 0) {
        b = grid.isValidPosition(new Point( wumpus.getPosition().x, wumpus.getPosition().y - 1 ));
        if(b == false){
          wumpus.setPosition(new Point( wumpus.getPosition().x, wumpus.getPosition().y + 1 ));
        } else {
          wumpus.setPosition(new Point( wumpus.getPosition().x, wumpus.getPosition().y - 1 ));
        }
      }else{ 
        b = grid.isValidPosition(new Point( wumpus.getPosition().x, wumpus.getPosition().y + 1 ));
        if(b == false){
          wumpus.setPosition(new Point( wumpus.getPosition().x, wumpus.getPosition().y - 1 ));
        } else {
          wumpus.setPosition(new Point( wumpus.getPosition().x, wumpus.getPosition().y + 1 ));
        }
      }
    }else{
      b = grid.isValidPosition(new Point( wumpus.getPosition().x - 1 , wumpus.getPosition().y ));
      if(b == false){
        wumpus.setPosition(new Point( wumpus.getPosition().x + 1 , wumpus.getPosition().y ));
      } else {
        wumpus.setPosition(new Point( wumpus.getPosition().x - 1 , wumpus.getPosition().y ));
      }
    }
  }
  

  private boolean makeAction(String input) {
    if (input.equals(MOVE_UP)) {
      return setPosition(new Point(jogador.getPosition().x, jogador.getPosition().y + 1));
    }
    if (input.equals(MOVE_RIGHT)) {
      return setPosition(new Point(jogador.getPosition().x + 1, jogador.getPosition().y));
    }
    if (input.equals(MOVE_DOWN)) {
      return setPosition(new Point(jogador.getPosition().x, jogador.getPosition().y - 1));
    }
    if (input.equals(MOVE_LEFT)) {
      return setPosition(new Point(jogador.getPosition().x - 1, jogador.getPosition().y));
    }

    return false;
  }

  private boolean setPosition(Point newPosition) {
    if (!grid.isValidPosition(newPosition)) {
      System.out.println();
      System.out.println("Posição nova inválida.");
      return false;
    }

    jogador.setPosition(newPosition);
    grid.discoverTile(newPosition);
    return true;
  }
}