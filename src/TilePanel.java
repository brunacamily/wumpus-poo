import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.*;

public class TilePanel extends JPanel {
  public TilePanel() {
    init();
  }

  private void init() {
    setBorder(BorderFactory.createLineBorder(Color.black));
    setPreferredSize(new Dimension(32, 32));
  }

  public void update(Tile tile) {
    removeAll();
    // if (!tile.isTileDiscovered()) {
    // add(new JLabel("Desconhecido"));
    // return;
    // }
    ArrayList<String> entities = tile.getEntities();

    for (String entity : entities) {
      add(new JLabel(entity));
    }

    revalidate();
    repaint();
  }
}
