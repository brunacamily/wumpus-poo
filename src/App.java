
import java.awt.*;
import javax.swing.*;

public class App extends JFrame {
  private GridPanel gridPanel;
  private LogPanel logPanel;
  private StatsPanel statsPanel;
  private ActionsPanel actionsPanel;

  public App(ActionsHelper actionsHelper) {
    gridPanel = new GridPanel();
    logPanel = new LogPanel();
    statsPanel = new StatsPanel();
    actionsPanel = new ActionsPanel(actionsHelper);
    init();
  }

  public void init() {
    // Caracteristicas da Janela Principal
    setTitle("World of Wumpus");
    setSize(1024, 768);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    setLayout(new GridBagLayout());

    add(gridPanel, GridPanel.getConstraints());
    add(logPanel, LogPanel.getConstraints());
    add(statsPanel, StatsPanel.getConstraints());
    add(actionsPanel, ActionsPanel.getConstraints());

    setVisible(true);
  }

  public void update(boolean isGameOver, Jogador jogador, Grid grid) {
    gridPanel.update(grid);
    statsPanel.update(jogador);
  }
}
