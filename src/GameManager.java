import java.util.Random;

import java.awt.Point;

public class GameManager {
  private static final int GRID_SIZE = 15;
  private static final int PITS_COUNT = 5;
  private static final int WOOD_COUNT = 2;
  private static final int INITIAL_POSITION_X = GRID_SIZE - 1;
  private static final int INITIAL_POSITION_Y = 0;

  private static final String VICTORY = "Victory";
  private static final String DEFEAT = "Defeat";

  private static final String MOVE_UP = "1";
  private static final String MOVE_RIGHT = "2";
  private static final String MOVE_DOWN = "3";
  private static final String MOVE_LEFT = "4";
  private static final String LOOT = "5";
  private static final String FIRE = "6";

  private App uiApp;

  public boolean canMakeTurns = false;

  private Jogador jogador;
  private Wumpus wumpus;
  private Lumpus lumpus;
  private Pit[] pits;
  private Grid grid;

  public GameManager(App uiApp) {
    this.uiApp = uiApp;
  }

  public void startGame() {
    System.out.println("Game Started");
    grid = new Grid(GRID_SIZE);
    jogador = new Jogador();
    wumpus = new Wumpus();
    lumpus = new Lumpus();
    pits = new Pit[PITS_COUNT];

    for (int i = 0; i < PITS_COUNT; i++) {
      pits[i] = new Pit();
      Point position = findPlacementPoint();
      pits[i].setPosition(position);
      grid.addTileEntity(position, pits[i].getId());
      grid.addAura(pits[i].getPosition(), pits[i].getAuraId());
    }

    for (int i = 0; i < WOOD_COUNT; i++) {
      Point position = findPlacementPoint();
      grid.addTileEntity(position, "Madeira");
    }

    setPlayerPosition(new Point(INITIAL_POSITION_X, INITIAL_POSITION_Y));

    Point wumpusPosition = findPlacementPoint();
    setWumpusPosition(wumpus, wumpusPosition);

    Point lumpusPosition = findPlacementPoint();
    setWumpusPosition(lumpus, lumpusPosition);

    Point goldPosition = findPlacementPoint();
    grid.addTileEntity(goldPosition, "Ouro");

    runNextTurn();
  }

  public void endGame(String result) {
    uiApp.update(jogador, grid);

    String input = uiApp.endGame(result);

    switch (input) {
      case "1":
        startGame();
        break;
      case "2":
        uiApp.dispose();
        break;
      default:
        endGame(result);
        break;
    }
  }

  private void runNextTurn() {
    uiApp.update(jogador, grid);
    runPlayerTurn();
  }

  private void runPlayerTurn() {
    canMakeTurns = true;
  }

  private void finishPlayerTurn() {
    if (!wumpus.isDead()) {
      runEnemyTurn(wumpus);
    }

    if (!lumpus.isDead()) {
      runEnemyTurn(lumpus);
    }

    runNextTurn();
  }

  private void runEnemyTurn(Wumpus wumpus) {
    setWumpusPosition(wumpus, findNextWumpusPosition(wumpus));

    if (jogador.getPosition().equals(wumpus.getPosition())) {
      jogador.takeDamage(wumpus.getPower());

      if (jogador.getHealth() == 0) {
        endGame(DEFEAT);
      }
    }
  }

  public void makeAction(String input) {
    if (canMakeTurns) {
      canMakeTurns = false;

      boolean hasInputSucceeded = getPlayerInput(input);

      if (!hasInputSucceeded) {
        System.out.println("Ação mal sucedida. Tente novamente.");
        canMakeTurns = true;
      }

      if (hasInputSucceeded) {
        Point initialPosition = new Point(INITIAL_POSITION_X, INITIAL_POSITION_Y);

        if (jogador.getPosition().equals(initialPosition) && jogador.hasGold()) {
          endGame(VICTORY);
          return;
        }

        finishPlayerTurn();
      }
    }
  }

  private boolean setPlayerPosition(Point newPosition) {
    if (grid.hasPitOnPosition(newPosition)) {
      System.out.println("Posição nova inválida.");
      return false;
    }

    if (jogador.getPosition() != null) {
      grid.removeTileEntity(jogador.getPosition(), jogador.getId());
    }

    jogador.setPosition(newPosition);
    grid.discoverTile(newPosition);
    grid.addTileEntity(newPosition, jogador.getId());

    return true;
  }

  private void setWumpusPosition(Wumpus wumpus, Point newPosition) {
    Point wumpusOldPosition = wumpus.getPosition();

    if (wumpusOldPosition != null) {
      grid.removeTileEntity(wumpusOldPosition, wumpus.getId());
      grid.removeAura(wumpusOldPosition, wumpus.getAuraId());
    }

    wumpus.setPosition(newPosition);
    grid.addTileEntity(newPosition, wumpus.getId());
    grid.addAura(newPosition, wumpus.getAuraId());
  }

  private boolean lootItem() {
    Tile tile = grid.getTileFromPosition(jogador.getPosition());

    if (tile.getEntities().contains("Ouro")) {
      jogador.addGold();
      grid.removeTileEntity(jogador.getPosition(), "Ouro");
      return true;
    }

    if (tile.getEntities().contains("Madeira")) {
      jogador.addArrow();
      grid.removeTileEntity(jogador.getPosition(), "Madeira");
      return true;
    }

    return false;
  }

  private boolean fireArrow() {
    if (jogador.getArrows() == 0) {
      System.out.println("Não há flechas para disparar");
      return false;
    }

    String input = uiApp.selectArrowDirection();
    int numberInput = 0;

    try {
      numberInput = Integer.parseInt(input);
    } catch (NumberFormatException error) {
      System.out.println("Caractere inválido.");
      return false;
    }

    if (numberInput < 1 || numberInput > 4) {
      System.out.println("Seleção inválida.");
      return false;
    }

    jogador.fireArrow();
    Point position = new Point(jogador.getPosition());

    switch (numberInput) {
      case 1:
        position.translate(-1, 0);
        break;
      case 2:
        position.translate(0, 1);
        break;
      case 3:
        position.translate(1, 0);
        break;
      case 4:
        position.translate(0, -1);
        break;
    }

    if (position.equals(wumpus.getPosition())) {
      wumpus.die();
      grid.removeTileEntity(position, wumpus.getId());
      grid.removeAura(position, wumpus.getAuraId());
    }

    if (position.equals(lumpus.getPosition())) {
      lumpus.die();
      grid.removeTileEntity(position, lumpus.getId());
      grid.removeAura(position, lumpus.getAuraId());
    }

    return true;
  }

  private Point findPlacementPoint() {
    Random random = new Random();

    int x = 0;
    int y = 0;
    boolean hasPit = true;
    Point position = new Point(x, y);
    while (x == INITIAL_POSITION_X && y == INITIAL_POSITION_Y || hasPit) {
      x = random.nextInt(GRID_SIZE);
      y = random.nextInt(GRID_SIZE);
      position.setLocation(x, y);

      hasPit = grid.hasPitOnPosition(position);
    }

    return position;
  }

  private Point findNextWumpusPosition(Wumpus wumpus) {
    Random random = new Random();
    int randomNumber = random.nextInt(4);
    int randomDirection = random.nextInt(2) % 2 == 0 ? 1 : -1;
    Point wumpusPosition = wumpus.getPosition();
    Point newPosition = new Point(wumpusPosition);

    String wumpusType = wumpus.getId();

    int mainAxisJump = wumpusType == "Wumpus" ? 1 : 2;
    int secondaryAxisJump = wumpusType == "Wumpus" ? 0 : randomDirection;

    switch (randomNumber) {
      case 0:
        newPosition.setLocation(
            wumpusPosition.x + mainAxisJump,
            wumpusPosition.y + secondaryAxisJump);
        break;
      case 1:
        newPosition.setLocation(
            wumpusPosition.x - mainAxisJump,
            wumpusPosition.y + secondaryAxisJump);
        break;
      case 2:
        newPosition.setLocation(
            wumpusPosition.x + secondaryAxisJump,
            wumpusPosition.y + mainAxisJump);
        break;
      case 3:
        newPosition.setLocation(
            wumpusPosition.x + secondaryAxisJump,
            wumpusPosition.y - mainAxisJump);
        break;
    }

    boolean hasPit = grid.hasPitOnPosition(newPosition);

    if (hasPit) {
      return findNextWumpusPosition(wumpus);
    }

    return newPosition;
  }

  private boolean getPlayerInput(String input) {
    Point playerPosition = jogador.getPosition();

    switch (input) {
      case MOVE_UP:
        return setPlayerPosition(
            new Point(playerPosition.x - 1, playerPosition.y));
      case MOVE_RIGHT:
        return setPlayerPosition(
            new Point(playerPosition.x, playerPosition.y + 1));
      case MOVE_DOWN:
        return setPlayerPosition(
            new Point(playerPosition.x + 1, playerPosition.y));
      case MOVE_LEFT:
        return setPlayerPosition(
            new Point(playerPosition.x, playerPosition.y - 1));
      case LOOT:
        return lootItem();
      case FIRE:
        return fireArrow();
      default:
        return false;
    }
  }
}
