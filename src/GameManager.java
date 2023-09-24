import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.text.Position;

import java.awt.Point;

public class GameManager {
  private static final int GRID_SIZE = 15;
  private static final int PITS_COUNT = 5;
  private static final int INITIAL_POSITION_X = GRID_SIZE - 1;
  private static final int INITIAL_POSITION_Y = 0;

  private static final String VICTORY = "Victory";
  private static final String DEFEAT = "Defeat";

  private static final String MOVE_UP = "1";
  private static final String MOVE_RIGHT = "2";
  private static final String MOVE_DOWN = "3";
  private static final String MOVE_LEFT = "4";
  private static final String LOOT = "5";

  private App uiApp;

  private boolean gameOver = false;
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
      grid.addTileEntity(position, "Poço");
      grid.addAura(pits[i].getPosition(), "Brisa");
    }

    setPlayerPosition(new Point(INITIAL_POSITION_X, INITIAL_POSITION_Y));

    Point wumpusPosition = findPlacementPoint();
    setWumpusPosition(wumpusPosition);

    Point lumpusPosition =  findPlacementPoint();
    setLumpusPosition(lumpusPosition);


    Point goldPosition = findPlacementPoint();
    grid.addTileEntity(goldPosition, "Ouro");

    runNextTurn();
  }

  public void endGame(String result) {
    uiApp.update(gameOver, jogador, grid);
    String input = null;

    if (result == VICTORY) {
      System.out.println("Você venceu");
      input  = JOptionPane.showInputDialog("Você venceu!\n\tDeseja jogar novamente?\n1-Sim\n2-Não");
    }

    if (result == DEFEAT) {
      System.out.println("Você perdeu");
      input  = JOptionPane.showInputDialog("Você perdeu!\n\tDeseja jogar novamente?\n1-Sim\n2-Não");
    }

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
    uiApp.update(gameOver, jogador, grid);
    runPlayerTurn();
  }

  private void runPlayerTurn() {
    canMakeTurns = true;
  }

  private void finishPlayerTurn() {
    runEnemyTurn();
    runEnemy2Turn();
    runNextTurn();
  }

  private void runEnemyTurn() {
    Point nextWumpusPosition = findNextWumpusPosition();
    setWumpusPosition(nextWumpusPosition);

    if (jogador.getPosition().equals(wumpus.getPosition()))
      endGame(DEFEAT);
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
      grid.removeTileEntity(jogador.getPosition(), "Player");
    }

    jogador.setPosition(newPosition);
    grid.discoverTile(newPosition);
    grid.addTileEntity(newPosition, "Player");

    return true;
  }

  private boolean lootItem() {
    Tile tile = grid.getTileFromPosition(jogador.getPosition());

    if (tile.getEntities().contains("Ouro")) {
      jogador.addGold();
      grid.removeTileEntity(jogador.getPosition(), "Ouro");
      return true;
    }

    if (tile.getEntities().contains("Madeira")) {
      jogador.addWood();
      grid.removeTileEntity(jogador.getPosition(), "Madeira");
      return true;
    }

    return false;
  }

  private void setWumpusPosition(Point newPosition) {
    Point wumpusOldPosition = wumpus.getPosition();

    if (wumpusOldPosition != null) {
      grid.removeTileEntity(wumpusOldPosition, "Wumpus");
      grid.removeAura(wumpusOldPosition, "Fedor");
    }

    wumpus.setPosition(newPosition);
    grid.addTileEntity(newPosition, "Wumpus");
    grid.addAura(newPosition, "Fedor");
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

      hasPit = grid.hasPitOnPosition(position);
    }

    return position;
  }

  private Point findNextWumpusPosition() {
    Random random = new Random();
    int randomNumber = random.nextInt(3);
    Point wumpusPosition = wumpus.getPosition();
    Point position = new Point(wumpusPosition);

    switch (randomNumber) {
      case 0:
        position = new Point(wumpusPosition.x + 1, wumpusPosition.y);
        break;
      case 1:
        position = new Point(wumpusPosition.x - 1, wumpusPosition.y);
        break;
      case 2:
        position = new Point(wumpusPosition.x, wumpusPosition.y + 1);
        break;
      case 3:
        position = new Point(wumpusPosition.x, wumpusPosition.y - 1);
        break;
    }

    boolean hasPit = grid.hasPitOnPosition(position);

    if (hasPit) {
      return findNextWumpusPosition();
    }

    return position;
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
      default:
        return false;
    }
  }

  private void runEnemy2Turn() {

    Point nextLumpusPosition = findNextLumpusPosition();
    setLumpusPosition(nextLumpusPosition);

    if (jogador.getPosition().equals(lumpus.getPosition()))
      jogador.setHealth(lumpus.attackPlayer(jogador.getHealth()));

  }

  private Point findNextLumpusPosition() {
    Random random = new Random();
    int randomNumber = random.nextInt(3);
    Point lumpusPosition = lumpus.getPosition();
    Point position = new Point(lumpusPosition);

      switch (randomNumber) {
        case 0:
          position = new Point(lumpusPosition.x + 1, lumpusPosition.y);
          position = new Point(position.x , moveXorY(position.y));
          break;
        case 1:
          position = new Point(lumpusPosition.x - 1, lumpusPosition.y);
          position = new Point(position.x , moveXorY(position.y));
          break;
        case 2:
          position = new Point(lumpusPosition.x, lumpusPosition.y + 1);
          position = new Point(moveXorY(position.x) , position.y);
          break;
        case 3:
          position = new Point(lumpusPosition.x, lumpusPosition.y - 1);
          position = new Point(moveXorY(position.x) , position.y);
          break;
    }

    boolean hasPit = grid.hasPitOnPosition(position);

    if (hasPit) {
      return findNextLumpusPosition();
    }

    return position;
  }

  private int moveXorY (int position) {
    Random random = new Random();
    int randomNumber = random.nextInt(3);

    position += randomNumber%2==0
          ? 1
          : -1;

    return position;
  }


  private void setLumpusPosition(Point newPosition) {
    lumpus.setPosition(newPosition);
}

}
