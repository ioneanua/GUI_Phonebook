/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

/**
 *
 * @author ioneanua@gmail.com
 */
//clasa prin care definim un CNP
class CNP {
    private String cod;
    //constructorul clasei CNP care face verificarile de rigoare
    public CNP(String nr){
        if(nr == null || nr.length()==0){
            throw new IllegalArgumentException("CNP-ul nu poate lipsi!");
        }
        if(!nr.matches("[0-9]+")){
            throw new IllegalArgumentException("CNP-ul trebuie sa contina doar cifre!");
        }
        if(nr.charAt(0)!='1' && nr.charAt(0)!='2'){
           throw new IllegalArgumentException("Prima cifra a CNP-ului trebuie sa fie 1 sau 2!");
        }
        if(nr.length()!=13){
            throw new IllegalArgumentException("CNP-ul trebuie sa aiba 13 cifre!");
        }
        cod=nr;
    }    
    @Override
    public String toString(){
        return cod;
    }
    

    
}
