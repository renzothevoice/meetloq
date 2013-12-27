/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.InterfacciaDati;

/**
 *
 * @author Alessio
 */
public interface Attrezzatura {

    int getIdattrezzatura();

    String getNome_attrezzatura();

    void setIdattrezzatura(int idattrezzatura);

    void setNome_attrezzatura(String nome_attrezzatura);
    
    public boolean isIspresent();

    public void setIspresent(boolean ispresent);
}
