package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

public class ActionsPanel extends JPanel {
  public ActionsPanel() {
    init();
  }

  private void init() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(0, 0, 0, 64);
    JPanel directions = getDirectionsPanel();
    add(directions, gbc);

    JPanel actions = getActionsPanel();
    add(actions);
  }

  private JPanel getDirectionsPanel() {
    JPanel directions = new JPanel(new GridBagLayout());

    GridBagConstraints directionsGbc = new GridBagConstraints();
    directionsGbc.anchor = GridBagConstraints.NORTH;
    directionsGbc.gridx = 1;
    directionsGbc.gridy = 0;
    directionsGbc.insets = new Insets(0, 0, 16, 0);
    directions.add(new JLabel("Mover"), directionsGbc);
    directionsGbc.gridx = 1;
    directionsGbc.gridy = 1;
    directionsGbc.ipadx = 16;
    directionsGbc.ipady = 16;
    directionsGbc.insets = new Insets(4, 4, 4, 4);
    directions.add(new JButton("Cima"), directionsGbc);
    directionsGbc.gridx = 0;
    directionsGbc.gridy = 2;
    directions.add(new JButton("Esquerda"), directionsGbc);
    directionsGbc.gridx = 1;
    directionsGbc.gridy = 2;
    directions.add(new JButton("Baixo"), directionsGbc);
    directionsGbc.gridx = 2;
    directionsGbc.gridy = 2;
    directions.add(new JButton("Direita"), directionsGbc);

    return directions;
  }

  private JPanel getActionsPanel() {
    JPanel actions = new JPanel(new GridBagLayout());

    GridBagConstraints actionsGbc = new GridBagConstraints();
    actionsGbc.anchor = GridBagConstraints.NORTH;
    actionsGbc.gridx = 1;
    actionsGbc.gridy = 0;
    actionsGbc.insets = new Insets(0, 0, 16, 0);
    actions.add(new JLabel("Ações"), actionsGbc);
    actionsGbc.gridx = GridBagConstraints.RELATIVE;
    actionsGbc.gridy = 1;
    actionsGbc.ipadx = 16;
    actionsGbc.ipady = 16;
    actionsGbc.insets = new Insets(4, 4, 4, 4);
    actions.add(new JButton("Atirar"), actionsGbc);
    actions.add(new JButton("Coletar"), actionsGbc);
    actions.add(new JButton("Lanterna"), actionsGbc);

    return actions;
  }

  public static GridBagConstraints getConstraints() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.weightx = 1.0;
    gbc.weighty = 2.0;
    gbc.fill = GridBagConstraints.BOTH;

    return gbc;
  }
}
