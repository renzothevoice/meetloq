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
public interface Evento {

    String getDescrizione();

    String getFinevoto();

    int getIdevento();

    int getIdutente();

    String getIniziovoto();

    String getTitolo();

    boolean isEventodefinito();

    boolean isPubblico();

    void setDescrizione(String descrizione);

    void setEventodefinito(boolean eventodefinito);

    void setFinevoto(String finevoto);

    void setIdevento(int idevento);

    void setIdutente(int idutente);

    void setIniziovoto(String iniziovoto);

    void setPubblico(boolean pubblico);

    void setTitolo(String titolo);

    List<Opzione_evento> getOpzioni();

    void setOpzioni(List<Opzione_evento> opzioni);

    List<Invitato> getInvitatievento();

    void setInvitatievento(List<Invitato> invitatievento);

    Img getLocandina();

    void setLocandina(Img locandina);

    Allegato getAllegato();

    void setAllegato(Allegato allegato);

    Utente getOrganizzatore();

    void setOrganizzatore(Utente organizzatore);

    Opzione_evento getOpzioneufficiale();
    

    void setOpzioneufficiale(Opzione_evento opzioneufficiale);
    
    public int getKey();

    public void setKey(int key);
    
    public int getIdopzioneufficiale();

    public void setIdopzioneufficiale(int idopzioneufficiale);
    
}
