import java.awt.Point;

public class Entity {
  private Point position;
  private String id;
  private String auraId;

  public Point getPosition() {
    return position;
  }

  public void setPosition(Point newPosition) {
    this.position = newPosition;
  }

  public String getId() {
    return id;
  }

  protected void setId(String id) {
    this.id = id;
  }

  public String getAuraId() {
    return auraId;
  }

  protected void setAuraId(String auraId) {
    this.auraId = auraId;
  }
}
