/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ioneanua@gmail.com
 */
//aceastra clasa reprezinta fereastra de dialog care va aparea atunci cand vom vrea sa MODIFICAM un abonat
public class ComponentaModifica {
    

    private JPanel mainPanel = new JPanel();
    private JTextField jNumeNou = new JTextField(9);
    private JTextField jPrenumeNou = new JTextField(9);
    private JTextField jCodNou = new JTextField(9);
    private JTextField jTelefonNou = new JTextField(9);
    private JTextField jNumeVechi = new JTextField(9);
    private JTextField jPrenumeVechi = new JTextField(9);
    private JTextField jCodVechi = new JTextField(9);
    private JTextField jTelefonVechi = new JTextField(9);

public ComponentaModifica() {
    mainPanel.setLayout(new GridLayout(4, 4, 5, 5));
    mainPanel.add(new JLabel("Nume Abonat Vechi: "));
    mainPanel.add(jNumeVechi);
    mainPanel.add(new JLabel("Nume Abonat Nou: "));
    mainPanel.add(jNumeNou);
    mainPanel.add(new JLabel("Prenume Abonat Vechi: "));
    mainPanel.add(jPrenumeVechi);
    mainPanel.add(new JLabel("Prenume Abonat Nou: "));
    mainPanel.add(jPrenumeNou);
    mainPanel.add(new JLabel("CNP Abonat Vechi: "));
    mainPanel.add(jCodVechi);
    mainPanel.add(new JLabel("CNP Abonat Nou: "));
    mainPanel.add(jCodNou);
    mainPanel.add(new JLabel("Telefon Abonat Vechi: "));
    mainPanel.add(jTelefonVechi);
    mainPanel.add(new JLabel("Telefon Abonat Nou: "));
    mainPanel.add(jTelefonNou);

}

public String getNumeNou() {
    return jNumeNou.getText();
}

public String getPrenumeNou() {
    return jPrenumeNou.getText();
}
public String getCodNou() {
    return jCodNou.getText();
}

public String getTelefonNou() {
    return jTelefonNou.getText();
}
public String getNumeVechi() {
    return jNumeVechi.getText();
}

public String getPrenumeVechi() {
    return jPrenumeVechi.getText();
}
public String getCodVechi() {
    return jCodVechi.getText();
}

public String getTelefonVechi() {
    return jTelefonVechi.getText();
}
public JComponent getComponent() {
    return mainPanel;
}
}