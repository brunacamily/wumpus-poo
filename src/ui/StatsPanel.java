package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.*;

public class StatsPanel extends JPanel {
  public StatsPanel() {
    init();
  }

  private void init() {
    setBackground(Color.YELLOW);
  }

  public static GridBagConstraints getConstraints() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;

    return gbc;
  }
}
