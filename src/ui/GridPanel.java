package ui;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.*;

public class GridPanel extends JPanel {
  public GridPanel() {
    init();
  }

  private void init() {
    for (int i = 0; i < 225; i++) {
      add(new Tile());
    }

    setLayout(new GridLayout(15, 15));
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
