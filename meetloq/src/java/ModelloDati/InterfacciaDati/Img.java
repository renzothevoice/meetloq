/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.InterfacciaDati;

/**
 *
 * @author Alessio
 */
public interface Img {

    String getAlt();

    int getIdevento();

    int getIdimg();

    String getUrl();

    void setAlt(String alt);

    void setIdevento(int idevento);

    void setIdimg(int idimg);

    void setUrl(String url);
    
}
