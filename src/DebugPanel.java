
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DebugPanel extends JPanel {
  private ActionsHelper actionsHelper;

  public DebugPanel(ActionsHelper actionsHelper) {
    this.actionsHelper = actionsHelper;
    init();
  }

  private void init() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.ipadx = 16;
    gbc.ipady = 16;
    JButton debugButton = new JButton("Debug");
    debugButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        actionsHelper.makeAction("8");
      }
    });
    add(debugButton, gbc);
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
