
import java.util.Scanner;
import java.awt.Point;

public class GameManager {
  private static final int GRID_SIZE = 15;
  private static final int INITIAL_POSITION_X = 14;
  private static final int INITIAL_POSITION_Y = 0;
  private static final int INITIAL_WUMPUS_POSITION_X = 0;
  private static final int INITIAL_WUMPUS_POSITION_Y = 14;

  private static final String MOVE_UP = "1";
  private static final String MOVE_RIGHT = "2";
  private static final String MOVE_DOWN = "3";
  private static final String MOVE_LEFT = "4";

  private App uiApp;

  private boolean gameOver = false;
  public boolean canMakeTurns = false;
  private int turnCount;

  private Jogador jogador;
  private Wumpus wumpus;
  private Grid grid;
  private Scanner scanner;

  public GameManager(App uiApp) {
    scanner = new Scanner(System.in);
    this.uiApp = uiApp;
  }

  public void startGame() {
    System.out.println("Game Started");
    grid = new Grid(GRID_SIZE);
    jogador = new Jogador();
    wumpus = new Wumpus();

    setPlayerPosition(new Point(INITIAL_POSITION_X, INITIAL_POSITION_Y));
    setWumpusPosition(new Point(INITIAL_WUMPUS_POSITION_X, INITIAL_WUMPUS_POSITION_Y));

    turnCount = 0;
    runNextTurn();
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

  private void runNextTurn() {
    turnCount++;
    uiApp.update(gameOver, jogador, grid);
    System.out.println();
    System.out.println("Turno " + turnCount);
    runPlayerTurn();
  }

  private void runPlayerTurn() {
    canMakeTurns = true;

    // while (!hasInputSucceeded) {
    // System.out.println("Escolha a direção para se mover:");
    // System.out.println("1. Cima:");
    // System.out.println("2. Direita:");
    // System.out.println("3. Baixo:");
    // System.out.println("4. Esquerda:");

    // String input = scanner.nextLine();

    // hasInputSucceeded = makeAction(input);
    // if (!hasInputSucceeded) {
    // System.out.println();
    // System.out.println("Ação mal sucedida. Tente novamente.");
    // }
    // }
  }

  private void finishPlayerTurn() {
    runEnemyTurn();
    runNextTurn();
  }

  private void runEnemyTurn() {
    Point playerPosition = jogador.getPosition();
    Point wumpusPosition = wumpus.getPosition();
    double directionX = (double) playerPosition.x - wumpusPosition.x;
    double directionY = (double) playerPosition.y - wumpusPosition.y;

    int normalizedX = directionX >= 0
        ? (int) Math.ceil(directionX / 14)
        : (int) Math.floor(directionX / 14);

    int normalizedY = directionY >= 0
        ? (int) Math.ceil(directionY / 14)
        : (int) Math.floor(directionY / 14);

    if (Math.abs(directionX) < Math.abs(directionY)) {
      setWumpusPosition(new Point(wumpusPosition.x, wumpusPosition.y + normalizedY));
      return;
    }

    setWumpusPosition(new Point(wumpusPosition.x + normalizedX, wumpusPosition.y));
  }

  public void makeAction(String input) {
    boolean hasInputSucceeded = false;

    if (canMakeTurns) {
      if (input.equals(MOVE_UP)) {
        hasInputSucceeded = setPlayerPosition(
            new Point(jogador.getPosition().x - 1, jogador.getPosition().y));
      }
      if (input.equals(MOVE_RIGHT)) {
        hasInputSucceeded = setPlayerPosition(
            new Point(jogador.getPosition().x, jogador.getPosition().y + 1));
      }
      if (input.equals(MOVE_DOWN)) {
        hasInputSucceeded = setPlayerPosition(
            new Point(jogador.getPosition().x + 1, jogador.getPosition().y));
      }
      if (input.equals(MOVE_LEFT)) {
        hasInputSucceeded = setPlayerPosition(
            new Point(jogador.getPosition().x, jogador.getPosition().y - 1));
      }

      if (!hasInputSucceeded) {
        System.out.println();
        System.out.println("Ação mal sucedida. Tente novamente.");
      }

      if (hasInputSucceeded) {
        finishPlayerTurn();
      }
    }
  }

  private boolean setPlayerPosition(Point newPosition) {
    if (!grid.isValidPosition(newPosition)) {
      System.out.println();
      System.out.println("Posição nova inválida.");
      return false;
    }

    if (jogador.getPosition() != null) {
      grid.removeTileEntity(jogador.getPosition(), "Player");
    }

    jogador.setPosition(newPosition);
    System.out.printf(
        "Posição jogador: (%d, %d)\n",
        jogador.getPosition().x,
        jogador.getPosition().y);
    grid.discoverTile(newPosition);
    grid.addTileEntity(newPosition, "Player");
    return true;
  }

  private void setWumpusPosition(Point newPosition) {
    if (wumpus.getPosition() != null) {
      grid.removeTileEntity(wumpus.getPosition(), "Wumpus");
      removeAura(wumpus, "Fedor");
    }

    wumpus.setPosition(newPosition);
    grid.addTileEntity(newPosition, "Wumpus");
    addAura(wumpus, "Fedor");

    System.out.printf(
        "Posição wumpus: (%d, %d)\n",
        wumpus.getPosition().x,
        wumpus.getPosition().y);
  }

  private Point[] getNearestPoints(Entity entity) {
    Point position = entity.getPosition();
    Point[] nearestPoints = new Point[4];

    nearestPoints[0] = new Point(position.x - 1, position.y);
    nearestPoints[1] = new Point(position.x, position.y + 1);
    nearestPoints[2] = new Point(position.x + 1, position.y);
    nearestPoints[3] = new Point(position.x, position.y - 1);

    return nearestPoints;
  }

  private void removeAura(Entity entity, String value) {
    Point[] nearestPoints = getNearestPoints(entity);
    for (Point point : nearestPoints) {
      if (grid.isValidPosition(point)) {
        grid.removeTileEntity(point, value);
      }
    }
  }

  private void addAura(Entity entity, String value) {
    Point[] nearestPoints = getNearestPoints(entity);
    for (Point point : nearestPoints) {
      if (grid.isValidPosition(point)) {
        grid.addTileEntity(point, value);
      }
    }
  }
}
