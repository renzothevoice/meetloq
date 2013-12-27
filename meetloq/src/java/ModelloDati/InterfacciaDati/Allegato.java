/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.InterfacciaDati;

/**
 *
 * @author Alessio
 */
public interface Allegato {

    int getIdallegato();

    int getIdevento();

    String getNome();

    String getUrl();

    void setIdallegato(int idallegato);

    void setIdevento(int idevento);

    void setNome(String nome);

    void setUrl(String url);
    
}
