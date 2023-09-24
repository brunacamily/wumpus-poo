
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ActionsPanel extends JPanel {
  private ActionsHelper actionsHelper;

  public ActionsPanel(ActionsHelper actionsHelper) {
    this.actionsHelper = actionsHelper;
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

    JButton upButton = new JButton("Cima");
    upButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        actionsHelper.makeAction("1");
      }
    });

    directions.add(upButton, directionsGbc);
    directionsGbc.gridx = 0;
    directionsGbc.gridy = 2;

    JButton leftButton = new JButton("Esquerda");
    leftButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        actionsHelper.makeAction("4");
      }
    });
    directions.add(leftButton, directionsGbc);
    directionsGbc.gridx = 1;
    directionsGbc.gridy = 2;

    JButton downButton = new JButton("Baixo");
    downButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        actionsHelper.makeAction("3");
      }
    });
    directions.add(downButton, directionsGbc);
    directionsGbc.gridx = 2;
    directionsGbc.gridy = 2;

    JButton rightButton = new JButton("Direita");
    rightButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        actionsHelper.makeAction("2");
      }
    });
    directions.add(rightButton, directionsGbc);

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

    JButton collectButton = new JButton("Coletar");
    collectButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        actionsHelper.makeAction("5");
      }
    });
    actions.add(collectButton, actionsGbc);

    JButton fireButton = new JButton("Atirar");
    fireButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        actionsHelper.makeAction("6");
      }
    });
    actions.add(fireButton, actionsGbc);

    JButton flashlightButton = new JButton("Lanterna");
    flashlightButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        actionsHelper.makeAction("7");
      }
    });
    actions.add(flashlightButton, actionsGbc);

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
