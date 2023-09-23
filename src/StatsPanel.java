
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

public class StatsPanel extends JPanel {
  JLabel hpLabel;

  public StatsPanel() {
    hpLabel = new JLabel();
    init();
  }

  private void init() {
    setLayout(new GridBagLayout());

    Font defaultFont = new Font("SansSerif", Font.BOLD, 16);
    GridBagConstraints labelGbc = new GridBagConstraints();
    labelGbc.gridwidth = GridBagConstraints.REMAINDER;
    labelGbc.insets = new Insets(24, 24, 0, 24);
    labelGbc.weightx = 1.0;
    labelGbc.fill = GridBagConstraints.HORIZONTAL;
    labelGbc.anchor = GridBagConstraints.FIRST_LINE_START;

    hpLabel.setText("HP: 2/2");
    hpLabel.setFont(defaultFont);
    add(hpLabel, labelGbc);
    JLabel arrowsLabel = new JLabel("Flechas");
    arrowsLabel.setFont(defaultFont);
    add(arrowsLabel, labelGbc);

    GridBagConstraints blankGbc = new GridBagConstraints();
    blankGbc.weighty = 1.0;
    add(new JLabel(" "), blankGbc);
  }

  public void update(Jogador jogador) {
    hpLabel.setText("HP: " + jogador.getHealth() + "/2");
  }

  public static GridBagConstraints getConstraints() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.weightx = 1.0;
    gbc.weighty = 0.5;
    gbc.fill = GridBagConstraints.BOTH;

    return gbc;
  }
}
