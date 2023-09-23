import java.awt.Point;

public class Grid {
  private Tile[][] tileMap;
  private int size;

  public Grid(int size) {
    this.size = size;
    tileMap = new Tile[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        tileMap[i][j] = new Tile();
      }
    }
  }

  public void discoverTile(Point position) {
    tileMap[position.x][position.y].setTileDiscovered(true);
  }

  public Tile getTileFromPosition(Point position) {
    return tileMap[position.x][position.y];
  }

  public void addTileEntity(Point position, String value) {
    tileMap[position.x][position.y].addEntity(value);
  }

  public void removeTileEntity(Point position, String value) {
    tileMap[position.x][position.y].removeEntity(value);
  }

  public boolean isValidPosition(Point position) {
    return position.x < size &&
        position.y < size &&
        position.x >= 0 &&
        position.y >= 0;
  }
}
