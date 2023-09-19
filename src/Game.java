import ui.App;

public class Game {
  /**
   * @param args
   */
  public static void main(String[] args) {
    GameManager gameManager = new GameManager();
    System.out.println("Hello World");

    new App();

    gameManager.startGame();
  }
}
