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
//aceastra clasa corespunde criteriului de compararea a doi abonati dupa nume
public class ComparatorNume implements Comparator {
     private boolean isSortAsc;

  public ComparatorNume( boolean sortAsc) {
    isSortAsc = sortAsc;
  }
     @Override
    public int compare(Object o1, Object o2) {
                       Abonat a1 = (Abonat) o1;
                       Abonat a2 = (Abonat) o2;
                       int result=a1.getNume().compareToIgnoreCase(a2.getNume());
                       if (!isSortAsc)
                            result = -result;
                       return result;
                    }
}
