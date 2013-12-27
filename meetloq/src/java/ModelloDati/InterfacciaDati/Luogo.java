/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.InterfacciaDati;

import java.util.List;

/**
 *
 * @author Alessio
 */
public interface Luogo {

    int getIdluogo();

    String getIndirizzo();

    String getLatitudine();

    String getLongitudine();

    String getNome_luogo();

    int getPosti();

    String getTipo_luogo();

    void setIdluogo(int idluogo);

    void setIndirizzo(String indirizzo);

    void setLatitudine(String latitudine);

    void setLongitudine(String longitudine);

    void setNome_luogo(String nome_luogo);

    void setPosti(int posti);

    void setTipo_luogo(String tipo_luogo);
    
    public List<Attrezzatura> getAttrezzature();

    public void setAttrezzature(List<Attrezzatura> attrezzature);
}
