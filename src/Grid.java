import java.awt.Point;

public class Grid {
  private String[][] gridMap;
  private int size;

  public Grid(int size, Point initalPosition) {
    this.size = size;
    gridMap = new String[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        gridMap[i][j] = "?";
      }
    }
    discoverTile(initalPosition);
  }

  public String getPosition(int x, int y) {
    return gridMap[x][y];
  }

  public void discoverTile(Point position) {
    if (gridMap[position.x][position.y].contains("?")) {
      gridMap[position.x][position.y] = "";
    }
  }

  public boolean isValidPosition(Point position) {
    return position.x < size &&
        position.y < size &&
        position.x >= 0 &&
        position.y >= 0;
  }
}
