public class Game {
  /**
   * @param args
   */

  public static void main(String[] args) {
    System.out.println("Hello World");
    ActionsHelper actionsHelper = new ActionsHelper();
    App app = new App(actionsHelper);
    GameManager gameManager = new GameManager(app);
    actionsHelper.setGameManager(gameManager);
    gameManager.startGame();
  }
}
