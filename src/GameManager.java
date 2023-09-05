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

  private Point position;
  private Grid grid;
  private Scanner scanner;

  public void startGame() {
    System.out.println("Game Started");
    position = new Point(INITIAL_POSITION_X, INITIAL_POSITION_Y);
    grid = new Grid(GRID_SIZE, new Point(INITIAL_POSITION_X, INITIAL_POSITION_Y));
    scanner = new Scanner(System.in);

    while (!gameOver) {
      System.out.printf("Posição atual: (%d, %d)\n", position.x, position.y);
      System.out.println("Escolha a direção para se mover:");
      System.out.println("1. Cima:");
      System.out.println("2. Direita:");
      System.out.println("3. Baixo:");
      System.out.println("4. Esquerda:");

      String input = scanner.nextLine();

      makeAction(input);
    }

    scanner.close();
  }

  private void makeAction(String input) {
    if (input.equals(MOVE_UP)) {
      System.out.println("move up");
      setPosition(new Point(position.x, position.y + 1));
    }
    if (input.equals(MOVE_RIGHT)) {
      setPosition(new Point(position.x + 1, position.y));
    }
    if (input.equals(MOVE_DOWN)) {
      setPosition(new Point(position.x, position.y - 1));
    }
    if (input.equals(MOVE_LEFT)) {
      setPosition(new Point(position.x - 1, position.y));
    }
  }

  private void setPosition(Point newPosition) {
    if (!grid.isValidPosition(newPosition)) {
      System.out.println("Posição inválida.");
      return;
    }

    position = newPosition;
    grid.discoverTile(newPosition);
  }
}
