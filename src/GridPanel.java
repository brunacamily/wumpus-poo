
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.*;

public class GridPanel extends JPanel {
  private TilePanel tilePanel[][];
  private int gridSize = 15;

  public GridPanel() {
    tilePanel = new TilePanel[gridSize][gridSize];
    init();
  }

  private void init() {
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        tilePanel[i][j] = new TilePanel();
        add(tilePanel[i][j]);
      }
    }

    setLayout(new GridLayout(gridSize, gridSize));
  }

  public void update(Grid grid, boolean isDebugModeOn) {
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        Point position = new Point(i, j);
        tilePanel[i][j].update(grid.getTileFromPosition(position), isDebugModeOn);
      }
    }
  }

  public static GridBagConstraints getConstraints() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.gridheight = 2;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;

    return gbc;
  }
}
