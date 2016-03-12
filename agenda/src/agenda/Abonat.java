/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

/**
 *
 * @author ioneanuaa@gmail.com
 */
public class Abonat{
    private String nume;
    private String prenume;
    private CNP cod;
    private NrTel telefon;
    //in constructorul clasei abonat se fac verificarile de rigoare ale datele introduse de utilizator
    public Abonat(String _nume,String _prenume,CNP _cod,NrTel _telefon){
        if(_nume == null || _nume.length()==0){
            throw new IllegalArgumentException("Numele nu poate lipsi!");
        }
        if(!_nume.matches("[A-Za-z]+"))
            throw new IllegalArgumentException("Numele trebuie sa contina doar litere!");
        if(_prenume == null || _prenume.length()==0){
            throw new IllegalArgumentException("Prenumele nu poate lipsi!");
        }
        if(!_prenume.matches("[A-Za-z]+"))
            throw new IllegalArgumentException("Prenumele trebuie sa contina doar litere!");
        nume=_nume;
        prenume=_prenume;
        cod=_cod;
        telefon=_telefon;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public CNP getCod() {
        return cod;
    }

    public NrTel getTelefon() {
        return telefon;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setCod(CNP cod) {
        this.cod = cod;
    }

    public void setTelefon(NrTel telefon) {
        this.telefon = telefon;
    }
    
    
    @Override
    public String toString(){
        return nume+" "+prenume+" "+cod+" "+telefon;
    }
    //functie folosita pt ordonarea abonatiilor la cautare (afisandu-se mai intai cei mai relevanti abonati
    public int getPrioritate(String _nume,String _prenume,String _cod, String _telefon){
            int prioritate=0;
                if(nume.contains(_nume) && _nume.length()!=0)
                    prioritate+=nume.length()-_nume.length();
                if(prenume.contains(_prenume) && _prenume.length()!=0)
                    prioritate+=prenume.length()-_prenume.length();
                if(cod.toString().contains(_cod.toString()) && _cod.toString().length()!=0)
                    prioritate+=cod.toString().length()-_cod.toString().length();
                if(telefon.toString().contains(_telefon.toString()) && _telefon.toString().length()!=0)
                    prioritate+=telefon.toString().length()-_telefon.toString().length();
            System.out.println(prioritate);
            return prioritate;
    }
    public boolean equals(Abonat persoana){
        if(nume.equals(persoana.getNume())&&prenume.equals(persoana.getPrenume())&&cod.toString().equals(persoana.getCod().toString())&&telefon.toString().equals(persoana.getTelefon().toString()))
            return true;
        else return false;
    }
    
}
