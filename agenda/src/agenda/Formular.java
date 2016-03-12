/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

/**
 *
 * @author ioneanua@gmail.com
 */
//aceastra clasa reprezinta formularul de completat pt rubrica SUGESTII si RECLAMATII
public class Formular {
    private String[] sugestiiReclamatii={"Sugestii","Reclamatii","Vreau reclama mea","Vreau aplicatia mea"};
    private JPanel mainPanel = new JPanel();
    private JPanel panou=new JPanel();
    private JTextField jNume = new JTextField(10);
    private JTextField jPrenume = new JTextField(10);
    private JTextField jEmail = new JTextField(10);
    private JTextField jTelefon = new JTextField(10);
    private JTextArea jMesaj = new JTextArea(10, 10);
    private JLabel jLabelNume=new JLabel("Nume: ");
    private JLabel jLabelPrenume=new JLabel("Prenume: ");
    private JLabel jLabelEmail=new JLabel("Email: ");
    private JLabel jLabelTelefon=new JLabel("Telefon: ");
    private JLabel jLabelMesaj=new JLabel("Mesajul dumneavoastra: ");
    private JComboBox comboBox=new JComboBox(sugestiiReclamatii);
    private JLabel jLabelComboBox=new JLabel("Tipul mesajului");
    private GridBagConstraints gridBagConstraints;
public Formular() {
        mainPanel.setLayout(new GridBagLayout());
        panou.setLayout(new java.awt.GridLayout(6, 2));
        panou.add(jLabelComboBox);
        panou.add(comboBox);
        panou.add(jLabelNume);
        panou.add(jNume);
        panou.add(jLabelPrenume);
        panou.add(jPrenume);
        panou.add(jLabelEmail);
        panou.add(jEmail);
        panou.add(jLabelTelefon);
        panou.add(jTelefon);
        panou.add(jLabelMesaj);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        mainPanel.add(panou, gridBagConstraints);
        

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        mainPanel.add(jMesaj, gridBagConstraints);

}

public String getNume() {
    return jNume.getText();
}

public String getPrenume() {
    return jPrenume.getText();
}
public String getEmail() {
    return jEmail.getText();
}

public String getTelefon() {
    return jTelefon.getText();
}
public String getMesaj(){
    return jMesaj.getText();
}
public String getTipMesaj(){
    return comboBox.getSelectedItem().toString();
}
public JComponent getComponent() {
    return mainPanel;
}
    
}
