/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ioneanua@gmail.com
 */
//aceastra clasa este modelul pt tabelul in care vor fi afisati abonatii
public class CarteDeTelefon extends AbstractTableModel {
    
    private List<Abonat> abonati;
    private int sortCol = 0;//coloana ce se sorteaza
    private  boolean isSortAsc = true;//ne spune daca se face ordonare crescatoare sau descrescatoare
    public CarteDeTelefon(List lista) {  
       abonati=lista;
    }
    

    @Override
    public int getRowCount() {
        return abonati.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex,int columnIndex) {
        Abonat a=abonati.get(rowIndex);
        switch(columnIndex){
            case 0: return a.getNume(); 
            case 1: return a.getPrenume();
            case 2: return a.getCod();
            case 3: return a.getTelefon(); 
            default: return "ERROR";
        }
    }
    @Override
    public void setValueAt(Object valoare,int indexRow,int indexColumn){
        Abonat persoana=abonati.get(indexRow);
        try{
        switch(indexColumn){
            case 0:abonati.add(new Abonat(valoare.toString(),persoana.getPrenume(),persoana.getCod(),persoana.getTelefon()));
                   abonati.remove(indexRow);
                   break;
            case 1:abonati.add(new Abonat(persoana.getNume(),valoare.toString(),persoana.getCod(),persoana.getTelefon()));
                   abonati.remove(indexRow);
                   break;
            case 2:abonati.add(new Abonat(persoana.getNume(),persoana.getPrenume(),new CNP(valoare.toString()),persoana.getTelefon()));
                   abonati.remove(indexRow);
                   break;
            case 3:abonati.add(new Abonat(persoana.getNume(),persoana.getPrenume(),persoana.getCod(),new NrTel(valoare.toString())));       
                   abonati.remove(indexRow);
                   break;
        }
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"EROARE",JOptionPane.ERROR_MESSAGE);
        }
        fireTableDataChanged();
        
    }
    //folosit pt sortare dupa headerul unei coloane (gasit pe un site)
     class ColumnListener extends MouseAdapter {
         protected JTable table;

         public ColumnListener(JTable t) {
            table = t;
         }

        public void mouseClicked(MouseEvent e) {
            TableColumnModel colModel = table.getColumnModel();
            int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
            int modelIndex = colModel.getColumn(columnModelIndex).getModelIndex();

      if (modelIndex < 0)
        return;
      if (sortCol == modelIndex)
        isSortAsc = !isSortAsc;
      else
        sortCol = modelIndex;

      for (int i = 0; i < 4; i++) { 
        TableColumn column = colModel.getColumn(i);
        column.setHeaderValue(getColumnName(column.getModelIndex()));
      }
      table.getTableHeader().repaint();
      switch(sortCol){
          case 0:ordoneaza(CriteriuOrdonare.Nume);break;
          case 1:ordoneaza(CriteriuOrdonare.Prenume);break;
          case 2:ordoneaza(CriteriuOrdonare.CNP);break;
          case 3:ordoneaza(CriteriuOrdonare.Telefon);break;
      }
      
      table.tableChanged(new TableModelEvent(CarteDeTelefon.this));
      table.repaint();
    }
  }
//se incheie ce am gasit pe site
    
    public void ordoneaza(CriteriuOrdonare c) {
        switch (c) {
            case Nume:
                Collections.sort(abonati, new ComparatorNume(isSortAsc));
                break;
            case Prenume:
                Collections.sort(abonati, new ComparatorPrenume(isSortAsc));
                break;
            case CNP:
                Collections.sort(abonati, new ComparatorCNP(isSortAsc));
                break;
                case Telefon:
                Collections.sort(abonati, new ComparatorTelefon(isSortAsc));    
                break;
        }
        fireTableDataChanged();
    }
    public String getColumnName(int column) {
        String s1="Nume";
        String s2="Prenume";
        String s3="CNP";
        String s4="Telefon";
        switch(column){
            case 0:
                if(column==sortCol)
                    s1=s1+(isSortAsc ? " >>" : " <<");
                return s1;
            case 1:
                if(column==sortCol)
                    s2=s2+(isSortAsc ? " >>" : " <<");
                return s2;
            case 2:
                if(column==sortCol)
                    s3=s3+(isSortAsc ? " >>" : " <<");
                return s3;
            case 3:
                if(column==sortCol)
                    s4=s4+(isSortAsc ? " >>" : " <<");
                return s4;
            default:return "Eroare de ordonare";   
        }
    }
     public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex>0;
  }

    public void adaugare(Abonat persoana){
        for (Abonat i : abonati) {
            if(i.equals(persoana)||i.getCod().toString().equals(persoana.getCod().toString()))
                 throw new AbonatException(persoana);
        }       
        abonati.add(persoana);
        fireTableDataChanged();
    }
    public void stergere(Abonat persoana){
        for (int i=0;i<abonati.size();i++) {
            if(abonati.get(i).equals(persoana)){
                 abonati.remove(i);
                 fireTableDataChanged();
            }else
                 throw new AbonatException(persoana);   
            
        }   
    }
    //aceastra functie este folosita pt a stergere un abonat din lista (cand stim indexul abonatului, util cand vrem sa stergem un rand din jTable si nu ne intereseaza datele respective,doar pozitia)
    public void stergere(int indexAbonat){ 
        abonati.remove(indexAbonat);
        fireTableDataChanged();;
    }
    public void modificare(Abonat persoanaVeche,Abonat persoanaNoua){
        for (int i=0;i<abonati.size();i++) {
            if(abonati.get(i).equals(persoanaVeche)){
                abonati.remove(i);
                abonati.add(persoanaNoua);
                fireTableDataChanged();
                return;
            }
        }
       throw new AbonatException(persoanaVeche);
    }
    //aceastra functie este folosita pt a modifica un abonat din lista(cand stim indexul abonatului,util cand vrem sa modificam un rand din jTable 
    public void modificare(int indexAbonat,Abonat persoanaNoua){ 
        abonati.remove(indexAbonat);
        abonati.add(persoanaNoua);
        fireTableDataChanged();
   }
    public List<Abonat> cautare(final String nume,final String prenume,final String cod, final String telefon){
        List<Abonat> lista=new LinkedList<>();
        int OK=0;//cu OK testam daca nu s-a gasit niciun abonat
        for (Abonat i : abonati) {
            String _nume=i.getNume();
            String _prenume=i.getPrenume();
            CNP _cod=i.getCod();
            NrTel _telefon=i.getTelefon();
            if(_nume.contains(nume)&&_prenume.contains(prenume)&&_cod.toString().contains(cod)&&_telefon.toString().contains(telefon)){
                lista.add(i);
                OK=1;
            }
            
        }
        if(OK==0) 
              throw new AbonatException("Nu s-a gasit niciun abonat!");
        
        //se incearca o cautare ordonata in functie de cel mai relevant match 
        //folosind o sortare dupa prioritatea fiecarui abonat
        //prin prioritate intelegem cat de apropiat este cuvantul introdus fata de abonatii nostrii
        //o prioritate mai mica este mai buna
        Collections.sort(lista,new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    Abonat a1=(Abonat) o1;
                    Abonat a2=(Abonat) o2;
                    if(a1.getPrioritate(nume,prenume,cod,telefon) > (a2.getPrioritate(nume,prenume,cod,telefon)))
                        return 1;
                    else
                        if(a1.getPrioritate(nume,prenume,cod,telefon) == (a2.getPrioritate(nume,prenume,cod,telefon)))
                            return 0;
                        else return -1;
                
                }
       
            });
        afisareAbonat(lista);
        fireTableDataChanged();
        return lista;
    }
    public void salvare(File fisier) throws IOException{
        FileWriter fw=new FileWriter(fisier);
        BufferedWriter bw=new BufferedWriter(fw);
        for(int i=0;i<abonati.size();i++){
            Abonat persoana=abonati.get(i);
            String nume=persoana.getNume();
            String prenume=persoana.getPrenume();
            CNP cod=persoana.getCod();
            NrTel telefon=persoana.getTelefon();
            bw.write(nume+","+prenume+","+cod.toString()+","+telefon.toString());
            bw.newLine();
        }
        bw.close();      
    }
    public void incarcare(File fisier) throws IOException{
        abonati.clear();
        FileReader fr=new FileReader(fisier);
        BufferedReader br=new BufferedReader(fr);
        String _line;
        while((_line = br.readLine()) != null){
            String[] sir=_line.split(",");
            Abonat persoana=new Abonat(sir[0],sir[1],new CNP(sir[2]),new NrTel(sir[3]));
            abonati.add(persoana);
            fireTableDataChanged();
        }
       br.close();
    }
    public void afisareAbonat(List<Abonat> lista){//folosita pt verificare
        for(Abonat i:lista){
            System.out.println(i.getNume()+" "+i.getPrenume()+" "+i.getCod()+" "+i.getTelefon());
        }
        System.out.println();
    }
      
}
