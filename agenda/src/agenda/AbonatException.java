/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

/**
 *
 * @author ioneanua@gmail.com
 */
//se creeaza o clasa AbonatException folosita care are 2 constructori:unul in care introducem un string(mesajul erorii) si unu in care introducem un abonat(de exemplu cand avem abonati identici - 2 abonati cu acelasi CNP)
class AbonatException extends RuntimeException {

    private Abonat persoana;
    String mesaj;
   
    public AbonatException(Abonat a) {
        persoana=a;
    }
    public AbonatException(String s) {
        mesaj=s;
    }

    public String getNume() {
        return persoana.getNume();
    }
    public String getPrenume() {
        return persoana.getPrenume();
    }
    @Override
    public String getMessage(){
        return mesaj;
    }
    
}
