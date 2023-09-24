
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

  public void update(Jogador jogador, Grid grid) {
    gridPanel.update(grid);
    statsPanel.update(jogador);
  }

  public String endGame(String result) {
    if (result == "Victory") {
      return JOptionPane.showInputDialog("Você venceu!\n\tDeseja jogar novamente?\n1-Sim\n2-Não");
    }

    if (result == "Defeat") {
      return JOptionPane.showInputDialog("Você perdeu!\n\tDeseja jogar novamente?\n1-Sim\n2-Não");
    }

    return "";
  }
}
