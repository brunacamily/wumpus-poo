public class Lumpus extends Wumpus {
  @Override
  public int attackPlayer(int life) {
    life = Math.floorDiv(life, 2);
    return life;
  }
}
