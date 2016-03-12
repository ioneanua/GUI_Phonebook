/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

/**
 *
 * @author ioneanua@gmail.com
 */
//clasa prin care definim un numar de telefon
class NrTel{
    private String telefon;
    //constructorul care ne face verificarile respective unui nr de telefon
    public NrTel(String nr){
        if(nr == null || nr.length()==0){
            throw new IllegalArgumentException("Numarul de telefon nu poate lipsi!");
        }
        if(!nr.matches("[0-9]+")){
            throw new IllegalArgumentException("Numarul trebuie sa contina doar cifre!");
        }
        if(nr.charAt(0)!='0'){
            throw new IllegalArgumentException("Numarul trebuie sa inceapa cu cifra 0!");
        }
        if(nr.charAt(1)!='2'&&nr.charAt(1)!='3'&&nr.charAt(1)!='7'){
            throw new IllegalArgumentException("Numerele din Roamnia incep cu 02,03 sau 07!");
        }
        if(nr.length()!=10){
            throw new IllegalArgumentException("Numarul trebuie sa aiba 10 litere!");
        }
        
        telefon=nr;
    }
    @Override
    public String toString(){
        return telefon;
    }
    
   }
    
