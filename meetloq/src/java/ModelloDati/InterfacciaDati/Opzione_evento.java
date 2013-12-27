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
public interface Opzione_evento {

    int getContatore();

    String getDatafine();

    String getDatainizio();

    int getIdevento();

    int getIdluogo();

    int getIdopzione();

    boolean isDisponibile();

    

    void setContatore(int contatore);

    void setDatafine(String datafine);

    void setDatainizio(String datainizio);

    void setDisponibile(boolean disponibile);

    void setIdevento(int idevento);

    void setIdluogo(int idluogo);

    void setIdopzione(int idopzione);

    

    Luogo getLuogoevento();

    void setLuogoevento(Luogo luogoevento);

    List<Voto> getVotievento();

    void setVotievento(List<Voto> votievento);
}
