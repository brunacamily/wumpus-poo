import java.util.ArrayList;

public class Tile {
  private boolean isTileDiscovered = false;
  private ArrayList<String> entities;

  public Tile() {
    entities = new ArrayList<String>();
  }

  public boolean isTileDiscovered() {
    return isTileDiscovered;
  }

  public void setTileDiscovered(boolean isTileDiscovered) {
    this.isTileDiscovered = isTileDiscovered;
  }

  public ArrayList<String> getEntities() {
    return entities;
  }

  public void addEntity(String value) {
    entities.add(value);
  }

  public void removeEntity(String value) {
    if (entities.indexOf(value) != -1) {
      entities.remove(entities.indexOf(value));
    }
  }
}
