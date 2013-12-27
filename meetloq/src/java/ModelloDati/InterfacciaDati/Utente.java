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
public interface Utente {

    String getAzienda();

    String getBday();

    String getCognome();

    String getEmail();

    int getIdtipo_utente();

    int getIdutente();

    String getNome();

    String getPassword();

    String getRuolo();

    String getUsername();

    void setAzienda(String azienda);

    void setBday(String bday);

    void setCognome(String cognome);

    void setEmail(String email);

    void setIdtipo_utente(int idtipo_utente);

    void setIdutente(int idutente);

    void setNome(String nome);

    void setPassword(String password);

    void setRuolo(String ruolo);

    void setUsername(String username);

    List<Evento> getEventi();

    void setEventi(List<Evento> eventi);

    List<Invitato> getInvitati();

    void setInvitati(List<Invitato> invitati);
}
