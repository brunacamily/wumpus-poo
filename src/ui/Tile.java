package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Tile extends JPanel {
  public Tile() {
    init();
  }

  private void init() {
    setBorder(BorderFactory.createLineBorder(Color.black));
    setPreferredSize(new Dimension(32, 32));
  }
}
