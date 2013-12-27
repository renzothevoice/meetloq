/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.InterfacciaDataLayer;

import ModelloDati.InterfacciaDati.Invitato;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Alessio
 */
public interface InvitatoDataLayer {

    List<Invitato> getRubrica(int idutente) throws SQLException;
    List<Invitato> getInvitatievento(int idevento) throws SQLException;
    void addInvitatoaevento(List <Invitato> invitati, int idevento) throws SQLException;
    void addInvitatoaevento(Invitato invitato, int idevento) throws SQLException;
    Invitato readInvitato(int idinvitato) throws SQLException;
    void createInvitato(Invitato invitato) throws SQLException;
    List <Invitato> getRubricaAndInvitati(int idutente,int idevento) throws SQLException;
    void removeInvitatoaevento(int idinvitato, int idevento) throws SQLException;
    public boolean checkInvitatotoevento(int idinvitato,int idevento) throws SQLException;
    public void deleteInvitato(int idinvitato) throws SQLException;
}
