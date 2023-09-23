public class ActionsHelper {
  GameManager gameManager;

  public void setGameManager(GameManager gameManager) {
    this.gameManager = gameManager;
  }

  public void makeAction(String action) {
    if (gameManager.canMakeTurns) {
      gameManager.makeAction(action);
    }
  }
}
