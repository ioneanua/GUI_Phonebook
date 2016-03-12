/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.util.Comparator;

/**
 *
 * @author ioneanua@gmail.com
 */
//aceastra clasa corespunde criteriului de compararea a doi abonati dupa telefon
public class ComparatorTelefon implements Comparator {
     private boolean isSortAsc;

  public ComparatorTelefon( boolean sortAsc) {
    isSortAsc = sortAsc;
  }
     @Override
    public int compare(Object o1, Object o2) {
                       Abonat a1 = (Abonat) o1;
                       Abonat a2 = (Abonat) o2;
                       int result=a1.getTelefon().toString().compareToIgnoreCase(a2.getTelefon().toString());
                       if (!isSortAsc)
                            result = -result;
                       return result;
                    }
}

