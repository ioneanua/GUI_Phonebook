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
//aceastra clasa reprezinta fereastra de dialog care va aparea atunci cand vom vrea sa ADAUGAM un abonat
public class ComponentaAdaugare {
    private JPanel mainPanel = new JPanel();
    private JTextField jNume = new JTextField(9);
    private JTextField jPrenume = new JTextField(9);
    private JTextField jCod = new JTextField(9);
    private JTextField jTelefon = new JTextField(9);

public ComponentaAdaugare() {
    mainPanel.setLayout(new GridLayout(4, 2, 5, 5));
    mainPanel.add(new JLabel("Nume Abonat: "));
    mainPanel.add(jNume);
    mainPanel.add(new JLabel("Prenume Abonat: "));
    mainPanel.add(jPrenume);
    mainPanel.add(new JLabel("CNP Abonat: "));
    mainPanel.add(jCod);
    mainPanel.add(new JLabel("Telefon Abonat: "));
    mainPanel.add(jTelefon);

}

public String getNume() {
    return jNume.getText();
}

public String getPrenume() {
    return jPrenume.getText();
}
public String getCod() {
    return jCod.getText();
}

public String getTelefon() {
    return jTelefon.getText();
}

public JComponent getComponent() {
    return mainPanel;
}
}
