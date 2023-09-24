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

  private void discoverTile(Point position) {
    if (isValidPosition(position)) {
      tileMap[position.x][position.y].setTileDiscovered(true);
    }
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

  public boolean hasPitOnPosition(Point position) {
    if (!isValidPosition(position)) {
      return true;
    }

    Tile tile = getTileFromPosition(position);
    return tile.getEntities().contains("Poço");
  }

  private Point[] getNearestPoints(Point position) {
    Point[] nearestPoints = new Point[4];

    nearestPoints[0] = new Point(position.x - 1, position.y);
    nearestPoints[1] = new Point(position.x, position.y + 1);
    nearestPoints[2] = new Point(position.x + 1, position.y);
    nearestPoints[3] = new Point(position.x, position.y - 1);

    return nearestPoints;
  }

  public void removeAura(Point position, String value) {
    Point[] nearestPoints = getNearestPoints(position);
    for (Point point : nearestPoints) {
      if (isValidPosition(point)) {
        removeTileEntity(point, value);
      }
    }
  }

  public void addAura(Point position, String value) {
    Point[] nearestPoints = getNearestPoints(position);
    for (Point point : nearestPoints) {
      if (isValidPosition(point)) {
        addTileEntity(point, value);
      }
    }
  }

  public void removeFog(Point position) {
    discoverTile(position);
    Point[] nearestPoints = getNearestPoints(position);
    for (Point point : nearestPoints) {
      discoverTile(point);
    }
  }
}
