
import java.awt.*;
import javax.swing.*;

public class App extends JFrame {
  private GridPanel gridPanel;
  private DebugPanel debugPanel;
  private StatsPanel statsPanel;
  private ActionsPanel actionsPanel;

  public App(ActionsHelper actionsHelper) {
    gridPanel = new GridPanel();
    debugPanel = new DebugPanel(actionsHelper);
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
    add(debugPanel, DebugPanel.getConstraints());
    add(statsPanel, StatsPanel.getConstraints());
    add(actionsPanel, ActionsPanel.getConstraints());

    setVisible(true);
  }

  public void update(Jogador jogador, Grid grid, boolean isDebugModeOn) {
    gridPanel.update(grid, isDebugModeOn);
    statsPanel.update(jogador, isDebugModeOn);

    revalidate();
    repaint();
  }

  public String selectArrowDirection() {
    return JOptionPane
        .showInputDialog("Selecione a direção de disparo da flecha:\n1-Cima\n2-Direita\n3-Baixo\n4-Esquerda");
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
