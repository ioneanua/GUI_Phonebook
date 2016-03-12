/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author ioneanua@gmail.com
 */
//aceastra clasa reprezinta fereastra de dialog care va aparea atunci cand vom vrea sa CAUTAM un abonat
public class ComponentaCautare {
    private JPanel mainPanel = new JPanel();
    private JPanel panou=new JPanel();
    private JTextField jNume = new JTextField(10);
    private JTextField jPrenume = new JTextField(10);
    private JTextField jCod = new JTextField(10);
    private JTextField jTelefon = new JTextField(10);
    private JTable jTabel=new JTable();
    private JLabel jLabelNume=new JLabel("Nume Abonat: ");
    private JLabel jLabelPrenume=new JLabel("Prenume Abonat: ");
    private JLabel jLabelCod=new JLabel("CNP Abonat: ");
    private JLabel jLabelTelefon=new JLabel("Telefon Abonat: ");
    private JScrollPane jScrollPane1=new JScrollPane();
    private GridBagConstraints gridBagConstraints;
public ComponentaCautare() {
    mainPanel.setLayout(new GridBagLayout());
    

        panou.setLayout(new java.awt.GridLayout(4, 2));
        panou.add(jLabelNume);
        panou.add(jNume);
        panou.add(jLabelPrenume);
        panou.add(jPrenume);
        panou.add(jLabelCod);
        panou.add(jCod);
        panou.add(jLabelTelefon);
        panou.add(jTelefon);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        mainPanel.add(panou, gridBagConstraints);
        jTabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nume", "Prenume", "CNP", "Telefon"
            }
        ));
        jScrollPane1.setViewportView(jTabel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        mainPanel.add(jScrollPane1, gridBagConstraints);

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
