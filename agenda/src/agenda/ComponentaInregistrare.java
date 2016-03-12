/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author ioneanua@gmail.com
 */
//aceastra clasa reprezinta fereastra de dialog care va aparea atunci cand vom vrea sa ne inregistram (parola este )
class ComponentaInregistrare {
    private JPanel mainPanel = new JPanel();
    private JPasswordField jInregistrare = new JPasswordField();
    public ComponentaInregistrare() {
        mainPanel.setLayout(new GridLayout(1, 2, 5, 5));
        mainPanel.add(new JLabel("Cod de inregistrare: "));
        mainPanel.add(jInregistrare);
    }
    @Override
    public String toString() {
        return jInregistrare.getText();
}

public JComponent getComponent() {
    return mainPanel;
}
}
