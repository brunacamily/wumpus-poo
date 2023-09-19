package ui;

import java.awt.*;
import javax.swing.*;

public class App extends JFrame {
  public App() {
    init();
  }

  public void init() {
    // Caracteristicas da Janela Principal
    setTitle("World of Wumpus");
    setSize(1024, 768);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    setLayout(new GridBagLayout());

    add(new GridPanel(), GridPanel.getConstraints());
    add(new LogPanel(), LogPanel.getConstraints());
    add(new StatsPanel(), StatsPanel.getConstraints());
    add(new ActionsPanel(), ActionsPanel.getConstraints());

    setVisible(true);
  }
}
