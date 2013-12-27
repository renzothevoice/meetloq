/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.InterfacciaDati;

/**
 *
 * @author Alessio
 */
public interface Invitato {

    String getCognome();

    String getEmail();

    int getIdinvitato();

    int getIdutente();

    String getNome();

    void setCognome(String cognome);

    void setEmail(String email);

    void setIdinvitato(int idinvitato);

    void setIdutente(int idutente);

    void setNome(String nome);

     public boolean isIsinvitatoallevento();

    public void setIsinvitatoallevento(boolean isinvitatoallevento);
    
}
