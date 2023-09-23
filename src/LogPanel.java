
import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.*;

public class LogPanel extends JPanel {
  public LogPanel() {
    init();
  }

  private void init() {
    setBackground(Color.RED);
  }

  public static GridBagConstraints getConstraints() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;

    return gbc;
  }
}
