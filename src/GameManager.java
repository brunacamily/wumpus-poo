
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.awt.Point;

public class GameManager {
  private static final int GRID_SIZE = 15;
  private static final int PITS_COUNT = 5;
  private static final int INITIAL_POSITION_X = GRID_SIZE - 1;
  private static final int INITIAL_POSITION_Y = 0;

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
  private Pit[] pits;
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
    pits = new Pit[PITS_COUNT];

    for (int i = 0; i < PITS_COUNT; i++) {
      pits[i] = new Pit();
      Point position = findPlacementPoint();
      pits[i].setPosition(position);
      grid.addTileEntity(position, "Pit");
      addAura(pits[i], "Brisa");
    }

    setPlayerPosition(new Point(INITIAL_POSITION_X, INITIAL_POSITION_Y));

    Point wumpusPosition = findPlacementPoint();
    setWumpusPosition(wumpusPosition);

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
    canMakeTurns = false;
    runEnemyTurn();
    runNextTurn();
  }

  private void runEnemyTurn() {
    Point playerPosition = jogador.getPosition();
    Point wumpusPosition = wumpus.getPosition();
    double directionX = (double) playerPosition.x - wumpusPosition.x;
    double directionY = (double) playerPosition.y - wumpusPosition.y;

    int normalizedX = directionX >= 0
        ? (int) Math.ceil(directionX / (GRID_SIZE - 1))
        : (int) Math.floor(directionX / (GRID_SIZE - 1));

    int normalizedY = directionY >= 0
        ? (int) Math.ceil(directionY / (GRID_SIZE - 1))
        : (int) Math.floor(directionY / (GRID_SIZE - 1));

    System.out.println(directionX);
    System.out.println(directionY);
    System.out.println(normalizedX);
    System.out.println(normalizedY);

    if (normalizedX == 0) {
      setWumpusPosition(new Point(wumpusPosition.x, wumpusPosition.y + normalizedY));
      return;
    }

    if (normalizedY == 0) {
      setWumpusPosition(new Point(wumpusPosition.x + normalizedX, wumpusPosition.y));
      return;
    }

    if (Math.abs(directionX) < Math.abs(directionY)) {
      setWumpusPosition(new Point(wumpusPosition.x + normalizedX, wumpusPosition.y));
      return;
    }

    setWumpusPosition(new Point(wumpusPosition.x, wumpusPosition.y + normalizedY));
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

  private Point findPlacementPoint() {
    Random random = new Random();

    int x = 0;
    int y = 0;
    boolean hasPit = true;
    Point position = new Point(x, y);
    while (x == INITIAL_POSITION_X && y == INITIAL_POSITION_Y || hasPit) {
      x = random.nextInt(GRID_SIZE - 1);
      y = random.nextInt(GRID_SIZE - 1);
      position.setLocation(x, y);
      ArrayList<String> entities = grid.getTileFromPosition(position).getEntities();

      if (!entities.contains("Pit")) {
        hasPit = false;
      }
    }

    return position;
  }
}
