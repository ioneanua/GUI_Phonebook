/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.JTableHeader;

/**
 *
 * @author ioneanua@gmail.com
 */
public class GUI extends javax.swing.JFrame {
    private List<Abonat> listaAbonati;//lista cu abonatii din tabel
    private List<Abonat> listaAbonatiFiltrati;//lista cu abonatii din tabel dupa filtrare
    private CarteDeTelefon m;//modelul lui jTabel
    private File saveFile;//fisierul folosit pt salvarea datelor
    private int indiceCauta=0;//folosit pt a afisa/ascunde panoul de cautare
    private int aFostSalvat=0;//folosit pt a vedea daca fisierul a fost sau nu salvat
    private int aGeneratExceptie=0;//folosit pt a vedea daca s-au generat exceptii la adaugare,cautare,stergere sau modificare
    private int setareButonPoza=0;//folosit pt a afisa si a inchide panelu cu butoanele adauga,cauta,sterge,modifca
    private String[] reclame={"reclama1.jpg","reclama2.jpg","reclama3.jpg","reclama4.jpg","reclama5.jpg","reclama6.jpg","reclama7.jpg","reclama8.jpg"};//tabloul cu reclamele ce vor fi incarcate
    private int randSelectat;//folosit pt a vedea ce rand a fost selectat cu jPopupMenu
    private int coloanaSelectata;//folosit pt a vedea ce coloana a fost selectata cu jPopupMenu
    private int tastaApasata;//folosit pt a sterge sau a modifica un rand din tabel (DELETE, respectiv ENTER)
    
    //se afiseaza o reclama in mod aleator
    TimerTask taskReclame=new TimerTask(){
            @Override
            public void run(){ 
                for(String reclama:reclame){
                    try {
                    jReclame.setIcon(new ImageIcon(getClass().getResource(reclama)));
                    Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        return;
                    }
                }
            }
        };
    Timer timerReclame=new Timer();
    
    //se salveaza baza de date odata la 5 minunte
    TimerTask taskSalvare=new TimerTask(){
            @Override
            public void run(){ 
                if(aFostSalvat!=0){
                    try {
                    m.salvare(saveFile);
                    }catch(IOException e){
                        JOptionPane.showMessageDialog(null, "Nu s-a putut scrie in fisier ","EROARE",JOptionPane.ERROR_MESSAGE);                        
                    }
                }
            }
        };
    Timer timerSalvare=new Timer();
    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        
        //afisat frameul pe mijlocul ecranului
        Dimension screenResolution = this.getToolkit().getScreenSize();
        this.setLocation((screenResolution.width - this.getWidth()) / 2, (screenResolution.height - this.getHeight()) / 2);
        //
        
        listaAbonati=new LinkedList<Abonat>();
        m=new CarteDeTelefon(listaAbonati);
        jTabel.setModel(m);
        
        //partea de sortare dand click pe headerul coloanei
        JTableHeader header = jTabel.getTableHeader();
        header.setUpdateTableInRealTime(true);
        header.addMouseListener(m.new ColumnListener(jTabel));
        header.setReorderingAllowed(true);
        
        //adaugam listnerul butoanelor adauga,cauta,sterge,modifica din meniu ca fiind functia listnerului din meniu din dreapta (nu are rost sa rescriem cod de 2 ori)
        jAdauga.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButonAdaugaActionPerformed(evt);
            }
        });
        jCauta.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButonCautaActionPerformed(evt);
            }
        });
        jSterge.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButonStergeActionPerformed(evt);
            }
        });
        jModifica.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButonModificaActionPerformed(evt);
            }
        });
        
        //dezactivarea butoanelor (in afara de butonul Inregistrare) pana la inregistrarea efectiva
        jButonPoza.setIcon(new ImageIcon(getClass().getResource("pozaButon2.jpg")));
        jOpen.setEnabled(false);
        jSave.setEnabled(false);
        jSaveAs.setEnabled(false);
        jAdauga.setEnabled(false);
        jCauta.setEnabled(false);
        jSterge.setEnabled(false);
        jModifica.setEnabled(false);
        jButonPoza.setEnabled(false);
        jPanel1.setVisible(false);
        jPanel5.setVisible(false);
        //jVreiReclamaTa.setIcon(new ImageIcon(getClass().getResource("reclamataaici.gif")));
        
        timerReclame.schedule(taskReclame,0,3000);//afisarea reclamelor odata la 3 secunde
        timerSalvare.schedule(taskSalvare,0,300000);//salvarea odata la 5 min=5000(5secunde)*(1 minut are 60 secunde)
        
        //setarea mnemonicelor pt butoanele Adauga,Sterge,Cauta,Modifica
        jButonAdauga.setMnemonic(KeyEvent.VK_A);
        jButonCauta.setMnemonic(KeyEvent.VK_C);
        jButonSterge.setMnemonic(KeyEvent.VK_S);
        jButonModifica.setMnemonic(KeyEvent.VK_M);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jStergerePopUp = new javax.swing.JMenuItem();
        jModificarePopUp = new javax.swing.JMenuItem();
        jFileChooserOpen = new javax.swing.JFileChooser();
        jFileChooserSave = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jButonAdauga = new javax.swing.JButton();
        jButonCauta = new javax.swing.JButton();
        jButonSterge = new javax.swing.JButton();
        jButonModifica = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTabel = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jReclame = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButonPoza = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jNumeLabel = new javax.swing.JLabel();
        jNume = new javax.swing.JTextField();
        jPrenumeLabel = new javax.swing.JLabel();
        jPrenume = new javax.swing.JTextField();
        jCodLabel = new javax.swing.JLabel();
        jCod = new javax.swing.JTextField();
        jTelefonLabel = new javax.swing.JLabel();
        jTelefon = new javax.swing.JTextField();
        jFiltrare = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jFile = new javax.swing.JMenu();
        jOpen = new javax.swing.JMenuItem();
        jSave = new javax.swing.JMenuItem();
        jSaveAs = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jIesire = new javax.swing.JMenuItem();
        jAbonati = new javax.swing.JMenu();
        jAdauga = new javax.swing.JMenuItem();
        jCauta = new javax.swing.JMenuItem();
        jSterge = new javax.swing.JMenuItem();
        jModifica = new javax.swing.JMenuItem();
        jHelp = new javax.swing.JMenu();
        jInregistrare = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jAbout = new javax.swing.JMenuItem();

        jStergerePopUp.setText("Sterge");
        jStergerePopUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStergerePopUpActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jStergerePopUp);

        jModificarePopUp.setText("Modifica");
        jModificarePopUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jModificarePopUpActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jModificarePopUp);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridLayout(4, 1));

        jButonAdauga.setText("Adauga");
        jButonAdauga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButonAdaugaMouseEntered(evt);
            }
        });
        jButonAdauga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButonAdaugaActionPerformed(evt);
            }
        });
        jPanel1.add(jButonAdauga);

        jButonCauta.setText("Cauta");
        jButonCauta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButonCautaMouseEntered(evt);
            }
        });
        jButonCauta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButonCautaActionPerformed(evt);
            }
        });
        jPanel1.add(jButonCauta);

        jButonSterge.setText("Sterge");
        jButonSterge.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButonStergeMouseEntered(evt);
            }
        });
        jButonSterge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButonStergeActionPerformed(evt);
            }
        });
        jPanel1.add(jButonSterge);

        jButonModifica.setText("Modifica");
        jButonModifica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButonModificaMouseEntered(evt);
            }
        });
        jButonModifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButonModificaActionPerformed(evt);
            }
        });
        jPanel1.add(jButonModifica);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        getContentPane().add(jPanel1, gridBagConstraints);

        jTabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelMouseClicked(evt);
            }
        });
        jTabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabelKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTabel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jScrollPane3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));
        jPanel2.add(jReclame);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jButonPoza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButonPozaActionPerformed(evt);
            }
        });
        jPanel3.add(jButonPoza);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridLayout(5, 2));

        jNumeLabel.setText("Nume abonat:");
        jPanel5.add(jNumeLabel);
        jPanel5.add(jNume);

        jPrenumeLabel.setText("Prenume abonat:");
        jPanel5.add(jPrenumeLabel);
        jPanel5.add(jPrenume);

        jCodLabel.setText("CNP abonat:");
        jPanel5.add(jCodLabel);
        jPanel5.add(jCod);

        jTelefonLabel.setText("Telefon Abonat");
        jPanel5.add(jTelefonLabel);
        jPanel5.add(jTelefon);

        jFiltrare.setText("Filtrare");
        jFiltrare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFiltrareActionPerformed(evt);
            }
        });
        jPanel5.add(jFiltrare);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel5, gridBagConstraints);

        jFile.setText("File");

        jOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jOpen.setText("Open");
        jOpen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jOpenMouseEntered(evt);
            }
        });
        jOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOpenActionPerformed(evt);
            }
        });
        jFile.add(jOpen);

        jSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jSave.setText("Save");
        jSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jSaveMouseEntered(evt);
            }
        });
        jSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveActionPerformed(evt);
            }
        });
        jFile.add(jSave);

        jSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jSaveAs.setText("Save As");
        jSaveAs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jSaveAsMouseEntered(evt);
            }
        });
        jSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveAsActionPerformed(evt);
            }
        });
        jFile.add(jSaveAs);
        jFile.add(jSeparator1);

        jIesire.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jIesire.setText("Iesire");
        jIesire.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jIesireMouseEntered(evt);
            }
        });
        jIesire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIesireActionPerformed(evt);
            }
        });
        jFile.add(jIesire);

        jMenuBar1.add(jFile);

        jAbonati.setText("Abonati");

        jAdauga.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jAdauga.setText("Adauga");
        jAdauga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jAdaugaMouseEntered(evt);
            }
        });
        jAbonati.add(jAdauga);

        jCauta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jCauta.setText("Cauta");
        jCauta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCautaMouseEntered(evt);
            }
        });
        jAbonati.add(jCauta);

        jSterge.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jSterge.setText("Sterge");
        jSterge.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jStergeMouseEntered(evt);
            }
        });
        jAbonati.add(jSterge);

        jModifica.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK));
        jModifica.setText("Modifica");
        jModifica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jModificaMouseEntered(evt);
            }
        });
        jModifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jModificaActionPerformed(evt);
            }
        });
        jAbonati.add(jModifica);

        jMenuBar1.add(jAbonati);

        jHelp.setText("Help");

        jInregistrare.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jInregistrare.setText("Inregistrare");
        jInregistrare.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jInregistrareMouseEntered(evt);
            }
        });
        jInregistrare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInregistrareActionPerformed(evt);
            }
        });
        jHelp.add(jInregistrare);
        jHelp.add(jSeparator2);

        jAbout.setText("About");
        jAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jAboutMouseEntered(evt);
            }
        });
        jAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAboutActionPerformed(evt);
            }
        });
        jHelp.add(jAbout);

        jMenuBar1.add(jHelp);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Incarcarea unui fisier cu abonati(o inregistrarea a unui abonat este de forma:Tiriac,Ion,1234567891111,0251777888)
    private void jOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOpenActionPerformed
        if (jFileChooserOpen.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File fisier = jFileChooserOpen.getSelectedFile();
            
            if(fisier.exists() && fisier.isFile() && fisier.getName().toLowerCase().endsWith(".txt")){
                try {
                    m.incarcare(fisier);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Nu s-a putut citi din fisier ","EROARE",JOptionPane.ERROR_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(this, "Nu ati ales un fisier .txt!","EROARE",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }//GEN-LAST:event_jOpenActionPerformed

    //salvrea datelor din tabel intr-un fisier (mai intai ni se cere sa specificam fisierul apoi apasare ulterioare a butonului Save se vor salva datele in fisierul respectiv)
    private void jSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveActionPerformed
        
        if(aFostSalvat!=0){
            try {
                m.salvare(saveFile);
            } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Nu s-a putut scrie in fisier ","EROARE",JOptionPane.ERROR_MESSAGE);
            }
        }else  
            jSaveAsActionPerformed(evt);
        
    }//GEN-LAST:event_jSaveActionPerformed

    //salvarea datelor intr-un fisier specificat de noi de fiecare data la apasarea butonului de Save As
    private void jSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveAsActionPerformed
        if (jFileChooserSave.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File fisier = jFileChooserSave.getSelectedFile();
            if(fisier.exists() && fisier.isFile() && fisier.getName().toLowerCase().endsWith(".txt")){
                try {
                    m.salvare(fisier);
                    saveFile=fisier;
                    aFostSalvat=1;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Nu s-a putut scrie in fisier ","EROARE",JOptionPane.ERROR_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(this, "Nu ati ales un fisier .txt!","EROARE",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }//GEN-LAST:event_jSaveAsActionPerformed
    
    //parasirea aplicatiei
    private void jIesireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIesireActionPerformed
        int selectedOption = JOptionPane.showConfirmDialog(this, 
                                  "Doriti sa parasiti aplicatia?", 
                                  "Alegeti", 
                                  JOptionPane.YES_NO_OPTION); 
        if (selectedOption == JOptionPane.YES_OPTION) {
                System.exit(0);
        }

    }//GEN-LAST:event_jIesireActionPerformed

    //adaugarea unui abonat in lista (nu se permit doi abonati cu acelasi CNP si nici doi abonati cu aceleasi date)
    private void jButonAdaugaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButonAdaugaActionPerformed
        try{
            aGeneratExceptie=1;
            ComponentaAdaugare componenta=new ComponentaAdaugare();
        int result = JOptionPane.showConfirmDialog(this,componenta.getComponent(),"Adauga Abonat!",JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            Abonat persoana=new Abonat(componenta.getNume(), componenta.getPrenume(),new CNP(componenta.getCod()), new NrTel(componenta.getTelefon())); 
            m.adaugare(persoana);
        }
        }catch(IllegalArgumentException e){
            aGeneratExceptie=0;
            JOptionPane.showMessageDialog(this, "Date introduse sunt incorecte! "+e.getMessage(),"EROARE",JOptionPane.ERROR_MESSAGE);
        }catch(AbonatException e){
            aGeneratExceptie=0;
            JOptionPane.showMessageDialog(this, "Abonatul  "+e.getNume()+" "+e.getPrenume()+" exista deja in sistem! Nu pot exista doua persoana cu acelasi CNP!","EROARE",JOptionPane.ERROR_MESSAGE);              
        }finally{
            if(aGeneratExceptie==0){
                jButonAdaugaActionPerformed(evt);
            }
        }
        
    }//GEN-LAST:event_jButonAdaugaActionPerformed

    //cautarea unui abonat(cand se apasa pe butonul Cauta ni se va afisa un panou cu functile de cautare, la a doua apasare se va inchide acel panou)
    private void jButonCautaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButonCautaActionPerformed
        if(indiceCauta%2==0){
                jPanel5.setVisible(true);
                
        }else{
            jPanel5.setVisible(false);
            jTabel.setModel(m);
        }
        indiceCauta++;
    }//GEN-LAST:event_jButonCautaActionPerformed

    //stergerea unui abonat din lista (prin specificarea datelor abonatului)
    private void jButonStergeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButonStergeActionPerformed
        try{
            aGeneratExceptie=1;
            ComponentaAdaugare componenta=new ComponentaAdaugare();
        int result = JOptionPane.showConfirmDialog( null,componenta.getComponent(),"Sterge Abonat!",JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            Abonat persoana=new Abonat(componenta.getNume(), componenta.getPrenume(),new CNP(componenta.getCod()), new NrTel(componenta.getTelefon())); 
            m.stergere(persoana);
        }
        }catch(IllegalArgumentException e){
            aGeneratExceptie=0;
            JOptionPane.showMessageDialog(this, "Date introduse sunt incorecte! "+e.getMessage(),"EROARE",JOptionPane.ERROR_MESSAGE);
        }catch(AbonatException e){
            aGeneratExceptie=0; 
            JOptionPane.showMessageDialog(this, "Abonatul  "+e.getNume()+" "+e.getPrenume()+" nu exista in sistem!","EROARE",JOptionPane.ERROR_MESSAGE);              
        }finally{
            if(aGeneratExceptie==0){
                jButonStergeActionPerformed(evt);
            }
        }
    }//GEN-LAST:event_jButonStergeActionPerformed

    //inregistrarea propriu-zisa. parola este adidas.dupa inregistrarea vor deveni active toate celelalte butoane
    private void jInregistrareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInregistrareActionPerformed
       
            ComponentaInregistrare componenta=new ComponentaInregistrare();
            int result = JOptionPane.showConfirmDialog( null,componenta.getComponent(),"Introduceti codul de inregistrare!",JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            if(componenta.toString().equals("adidas")){
                jInregistrare.setEnabled(false);
                jOpen.setEnabled(true);
                jSave.setEnabled(true);
                jSaveAs.setEnabled(true);
                jAdauga.setEnabled(true);
                jCauta.setEnabled(true);
                jSterge.setEnabled(true);
                jModifica.setEnabled(true);
                jButonPoza.setEnabled(true);
               // jPanel4.setVisible(false);
                jPanel2.setVisible(false);
                timerReclame.cancel();
            }else{
                JOptionPane.showMessageDialog(this, "Cod invalid!","EROARE",JOptionPane.ERROR_MESSAGE);
                jInregistrareActionPerformed(evt);
            }              
        }
    }//GEN-LAST:event_jInregistrareActionPerformed

    //aici vor fi afisare niste date despre autor precum si posibilitatea de a trimite sugestii si reclamatii sau comenzi pe o adresa de mail specificata de mine
    private void jAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAboutActionPerformed
  
    class ComponentaAbout {
        private JPanel mainPanel = new JPanel();
        private JLabel jLabelAutor=new JLabel("Autor: Dabuleanu Adrian Marius! Pt comenzi si reclamatii (si botezuri) dati click");
        private JButton jButonAici=new JButton("aici");
        private GridBagConstraints gridBagConstraints;

        public ComponentaAbout() {
            mainPanel.setLayout(new GridBagLayout());
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
            mainPanel.add(jLabelAutor, gridBagConstraints);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
            mainPanel.add(jButonAici,gridBagConstraints);
            /*
            jButonAici.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButonAiciActionPerformed(evt);
                    }
                });*/
        }
        /*
        private void jButonAiciActionPerformed(java.awt.event.ActionEvent evt) {
        
                Formular componenta=new Formular();
                int result = JOptionPane.showConfirmDialog(null,componenta.getComponent(),"Completati formularul!",JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                     sendEmail(componenta.getTipMesaj(), componenta.getNume(), componenta.getPrenume(), componenta.getEmail(),componenta.getTelefon(), componenta.getMesaj());
                }
        
        }

    */
            public JComponent getComponent() {
                 return mainPanel;
            }

    } 
    ComponentaAbout componentaAbout=new ComponentaAbout();        
        JOptionPane.showMessageDialog(this, componentaAbout.getComponent(),"About",JOptionPane.INFORMATION_MESSAGE); 
                
    }//GEN-LAST:event_jAboutActionPerformed

    //cand se apasa pe butonul caruia i-am adaugat noi un icon se deschide un panou cu posibilitatea de a Adauga,Cauta,Sterge,Modifica un abonat.la o a2a apasare vor disparea butoanele respective.
    private void jButonPozaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButonPozaActionPerformed
        if(setareButonPoza%2==0){
            jPanel1.setVisible(true);
            jButonPoza.setIcon(new ImageIcon(getClass().getResource("pozaButon1.jpg")));
        }
        else{
            jPanel1.setVisible(false);
            jButonPoza.setIcon(new ImageIcon(getClass().getResource("pozaButon2.jpg")));
        }
        setareButonPoza++;
    }//GEN-LAST:event_jButonPozaActionPerformed
    
    /*
    //am adaugat un buton care face ca apasarea lui sa ne trimita la un formular unde putem trimite un mail cu o eventuala comanda a unei aplicatii sau afisarea unei reclame in aplicatie    */
    //acest buton ne va afisa lista de abonati din tabel filtrata in functie de datele pe care le-am introdus in campurile respective
    private void jFiltrareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFiltrareActionPerformed
       try{
            listaAbonatiFiltrati=m.cautare(jNume.getText(),jPrenume.getText(),jCod.getText(),jTelefon.getText());
            CarteDeTelefon modelFiltrare=new CarteDeTelefon(listaAbonatiFiltrati);
            jTabel.setModel(modelFiltrare);
            JOptionPane.showMessageDialog(this, "Atentie!Tabelul afiseaza lista filtratra! \n Pentru a parasi filtru apasati inca odata pe butonu Cauta","ATENTIE",JOptionPane.INFORMATION_MESSAGE);      
       }catch(AbonatException e){
            JOptionPane.showMessageDialog(this, e.getMessage(),"EROARE",JOptionPane.ERROR_MESSAGE);      
       }
    }//GEN-LAST:event_jFiltrareActionPerformed
    
    //hint pt butonu Adauga (atunci cand trecem cu mouse-ul peste el)
    private void jButonAdaugaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButonAdaugaMouseEntered
        jButonAdauga.setToolTipText("Adauga un nou abonat in tabel!");
    }//GEN-LAST:event_jButonAdaugaMouseEntered

    //hint pt butonu Cauta (atunci cand trecem cu mouse-ul peste el)
    private void jButonCautaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButonCautaMouseEntered
        jButonCauta.setToolTipText("Cauta un abonat din tabel!");
    }//GEN-LAST:event_jButonCautaMouseEntered

    //hint pt butonu Sterge (atunci cand trecem cu mouse-ul peste el)
    private void jButonStergeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButonStergeMouseEntered
        jButonSterge.setToolTipText("Sterge un abonat din tabel!");
    }//GEN-LAST:event_jButonStergeMouseEntered

    //hint pt butonu Modifica (atunci cand trecem cu mouse-ul peste el)
    private void jButonModificaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButonModificaMouseEntered
        jButonModifica.setToolTipText("Modifica un abonat din tabel!");
    }//GEN-LAST:event_jButonModificaMouseEntered

    //hint pt butonu Open (atunci cand trecem cu mouse-ul peste el)
    private void jOpenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jOpenMouseEntered
         jOpen.setToolTipText("Incarca abonati dintr-un fisier .txt!");
    }//GEN-LAST:event_jOpenMouseEntered

    //hint pt butonu Save (atunci cand trecem cu mouse-ul peste el)
    private void jSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSaveMouseEntered
        jSave.setToolTipText("Salveaza abonatii intr-un fisier .txt!");
    }//GEN-LAST:event_jSaveMouseEntered

    //hint pt butonu SaveAs (atunci cand trecem cu mouse-ul peste el)
    private void jSaveAsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSaveAsMouseEntered
        jSaveAs.setToolTipText("Salveaza abonatii intr-un fisier .txt!");
    }//GEN-LAST:event_jSaveAsMouseEntered

    //hint pt butonu Iesire (atunci cand trecem cu mouse-ul peste el)
    private void jIesireMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jIesireMouseEntered
        jIesire.setToolTipText("Parasiti aplicatia!");
    }//GEN-LAST:event_jIesireMouseEntered

    //hint pt butonu Adauga din meniu(atunci cand trecem cu mouse-ul peste el)
    private void jAdaugaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAdaugaMouseEntered
        jAdauga.setToolTipText("Adauga un nou abonat in tabel!");
    }//GEN-LAST:event_jAdaugaMouseEntered

    //hint pt butonu Cauta din meniu(atunci cand trecem cu mouse-ul peste el)
    private void jCautaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCautaMouseEntered
        jCauta.setToolTipText("Cauta un abonat din tabel!");
    }//GEN-LAST:event_jCautaMouseEntered


    //hint pt butonu Sterge din meniu(atunci cand trecem cu mouse-ul peste el)
    private void jStergeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jStergeMouseEntered
        jSterge.setToolTipText("Sterge un abonat din tabel!");
    }//GEN-LAST:event_jStergeMouseEntered

    //hint pt butonu Modifica din meniu(atunci cand trecem cu mouse-ul peste el)
    private void jModificaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jModificaMouseEntered
        jModifica.setToolTipText("Modifica un abonat din tabel!");
    }//GEN-LAST:event_jModificaMouseEntered

    //hint pt butonu Inregistrare din meniu(atunci cand trecem cu mouse-ul peste el)
    private void jInregistrareMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jInregistrareMouseEntered
        jInregistrare.setToolTipText("Inregistrati-va inainte de a putea utiliza aplicatia!");
    }//GEN-LAST:event_jInregistrareMouseEntered

    //hint pt butonu About (atunci cand trecem cu mouse-ul peste el)
    private void jAboutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAboutMouseEntered
        jAbout.setToolTipText("Date despre autor!");
    }//GEN-LAST:event_jAboutMouseEntered

    //realizarea stergerii unuia sau mai multor abonati dand click dreapta pe ei si apasand butonu Sterge (jPopupMenu)
    private void jStergerePopUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStergerePopUpActionPerformed
        int[] _selectedRows = jTabel.getSelectedRows();
        int _deletedRowsCount = 0;
        int result = JOptionPane.showConfirmDialog(this,(_selectedRows.length==1?"Doriti sa stergeti abonatul?":"Doriti sa stergeti abonatii?"),"Stergere abonat!",JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
                   for(int i=0; i<_selectedRows.length; i++){
                       m.stergere(_selectedRows[i] - (_deletedRowsCount++));//stergem randurile selectate din tabel (cand stergem un rand urmatorul rand shifteaza in sus, de ex stergem randul 2 si 3, daca stergem randu 2 randul 3 devine randul 2 si trebuie de fapt sa stergem iar randul 2, de aici rezulta _selectedRows[i] - (_deletedRowsCount++))
                    }
                    m.fireTableDataChanged();
             }
        
    }//GEN-LAST:event_jStergerePopUpActionPerformed

    //realizarea unui abonat dand click dreapta pe ei si apasand butonu Modifica (jPopupMenu)
    private void jModificarePopUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jModificarePopUpActionPerformed
        int[] _selectedRows = jTabel.getSelectedRows();
        int result = JOptionPane.showConfirmDialog(this,(_selectedRows.length==1?"Doriti sa modificati abonatul selectat?":"Atentie! Mai multi abonati selectati! Doriti sa modificati ultimul abonat selectat?"),"Modificare abonat!",JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
                    ComponentaAdaugare componenta=new ComponentaAdaugare();
                    int resultAdaugare = JOptionPane.showConfirmDialog(this,componenta.getComponent(),"Modifica Abonat!",JOptionPane.OK_CANCEL_OPTION);
                    if (resultAdaugare == JOptionPane.OK_OPTION) {
                        Abonat persoana=new Abonat(componenta.getNume(), componenta.getPrenume(),new CNP(componenta.getCod()), new NrTel(componenta.getTelefon()));
                        m.modificare(_selectedRows[_selectedRows.length-1],persoana);//modificam ultimul rand selectat;am constatat un bug:cand dau enter trece pe linia urmatoare si apoi intra eventul (de aceea am pus argumentu i[i.length-1]-1)
                        m.fireTableDataChanged();
                    }
                }   
        
    }//GEN-LAST:event_jModificarePopUpActionPerformed

    //cand dam click dreapta ne apar optiunile Sterge si Modifica
    private void jTabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelMouseClicked
        if(evt.getButton()==java.awt.event.MouseEvent.BUTTON3 && evt.getClickCount()==1){
            jPopupMenu1.show(jTabel, evt.getX(), evt.getY());
            randSelectat=jTabel.rowAtPoint(evt.getPoint());
            coloanaSelectata=jTabel.columnAtPoint(evt.getPoint());
            if(jTabel.getSelectedRows().length==1)
                jModificarePopUp.setEnabled(true);
            else 
                jModificarePopUp.setEnabled(false);
        }
    }//GEN-LAST:event_jTabelMouseClicked

    private void jButonModificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButonModificaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButonModificaActionPerformed

    //putem sterge unul sau mai multi abonati apasand tasta DELETE si putem modifica unu abonat apasand tasta 
    private void jTabelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabelKeyPressed
        tastaApasata=evt.getKeyCode();
        int[] _selectedRows = jTabel.getSelectedRows();
        if(tastaApasata == 127){ //am apasat tasta DELETE
            int _deletedRowsCount = 0;
             int result = JOptionPane.showConfirmDialog(this,(_selectedRows.length==1?"Doriti sa stergeti abonatul?":"Doriti sa stergeti abonatii?"),"Stergere abonat!",JOptionPane.OK_CANCEL_OPTION);
              if (result == JOptionPane.OK_OPTION) {
                   for(int i=0; i<_selectedRows.length; i++){
                       m.stergere(_selectedRows[i] - (_deletedRowsCount++));//stergem randurile selectate din tabel (cand stergem un rand urmatorul rand shifteaza in sus, de ex stergem randul 2 si 3, daca stergem randu 2 randul 3 devine randul 2 si trebuie de fapt sa stergem iar randul 2, de aici rezulta _selectedRows[i] - (_deletedRowsCount++))
                    }
                    m.fireTableDataChanged();
             }
        }else
        if(tastaApasata == 10){//am apasat tasta ENTER
            int result = JOptionPane.showConfirmDialog(this,(_selectedRows.length==1?"Doriti sa modificati abonatul selectat?":"Atentie! Mai multi abonati selectati! Doriti sa modificati ultimul abonat selectat?"),"Modificare abonat!",JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                    ComponentaAdaugare componenta=new ComponentaAdaugare();
                    int resultAdaugare = JOptionPane.showConfirmDialog(this,componenta.getComponent(),"Modifica Abonat!",JOptionPane.OK_CANCEL_OPTION);
                    if (resultAdaugare == JOptionPane.OK_OPTION) {
                        Abonat persoana=new Abonat(componenta.getNume(), componenta.getPrenume(),new CNP(componenta.getCod()), new NrTel(componenta.getTelefon()));
                        m.modificare(_selectedRows[_selectedRows.length-1],persoana);//modificam ultimul rand selectat;am constatat un bug:cand dau enter trece pe linia urmatoare si apoi intra eventul (de aceea am pus argumentu i[i.length-1]-1)
                        m.fireTableDataChanged();
                    }
                }   
        }else{
            
        }
        
    }//GEN-LAST:event_jTabelKeyPressed
    
    /*
    //TREBUIE INSTALAT API-UL JAVA MAIL!!!
    
    
    //trimitere mail pe o adresa configurata (folosit pt rubrica de sugestii si reclamatii) ->cod de pe net
   private void sendEmail(String tipMesaj,String nume,String prenume,String email,String telefon,String mesaj){
   
      
      // Recipient's email ID needs to be mentioned.
      String to = "dabuleanu.adrian@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "web@gmail.com";

      // Assuming you are sending email from localhost
      String host = "localhost";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject(tipMesaj);

         // Now set the actual message
         message.setText("Nume: "+nume+"\n Prenume: "+prenume+ "\n Telefon: "+telefon+"\n Email: "+email+"\n Mesaj: "+mesaj);

         // Send message
         Transport.send(message);
         JOptionPane.showMessageDialog(this, "Mail expediat cu succes!","Mail expediat",JOptionPane.INFORMATION_MESSAGE);      
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
*/
  

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jAbonati;
    private javax.swing.JMenuItem jAbout;
    private javax.swing.JMenuItem jAdauga;
    private javax.swing.JButton jButonAdauga;
    private javax.swing.JButton jButonCauta;
    private javax.swing.JButton jButonModifica;
    private javax.swing.JButton jButonPoza;
    private javax.swing.JButton jButonSterge;
    private javax.swing.JMenuItem jCauta;
    private javax.swing.JTextField jCod;
    private javax.swing.JLabel jCodLabel;
    private javax.swing.JMenu jFile;
    private javax.swing.JFileChooser jFileChooserOpen;
    private javax.swing.JFileChooser jFileChooserSave;
    private javax.swing.JButton jFiltrare;
    private javax.swing.JMenu jHelp;
    private javax.swing.JMenuItem jIesire;
    private javax.swing.JMenuItem jInregistrare;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jModifica;
    private javax.swing.JMenuItem jModificarePopUp;
    private javax.swing.JTextField jNume;
    private javax.swing.JLabel jNumeLabel;
    private javax.swing.JMenuItem jOpen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JTextField jPrenume;
    private javax.swing.JLabel jPrenumeLabel;
    private javax.swing.JLabel jReclame;
    private javax.swing.JMenuItem jSave;
    private javax.swing.JMenuItem jSaveAs;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem jSterge;
    private javax.swing.JMenuItem jStergerePopUp;
    private javax.swing.JTable jTabel;
    private javax.swing.JTextField jTelefon;
    private javax.swing.JLabel jTelefonLabel;
    // End of variables declaration//GEN-END:variables
}
