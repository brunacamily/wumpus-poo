public class Game {
  /**
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("Hello World");
    App app = new App();

    GameManager gameManager = new GameManager(app);
    gameManager.startGame();
  }
}
