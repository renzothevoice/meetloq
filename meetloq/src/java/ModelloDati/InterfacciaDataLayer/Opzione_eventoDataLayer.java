/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.InterfacciaDataLayer;

import ModelloDati.InterfacciaDati.Opzione_evento;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Alessio
 */
public interface Opzione_eventoDataLayer {

    List<Opzione_evento> getListopzioni(int idevento) throws SQLException;

    void createOpzione(Opzione_evento opz) throws SQLException;

    void createOpzioni(List<Opzione_evento> opzioni) throws SQLException;

    boolean checkOpzioneDisponibile(Opzione_evento opzione) throws SQLException;

    boolean isValidDate(String input);

    Opzione_evento readOpzione(int idopzione) throws SQLException;

    void updateOpzione(Opzione_evento opz) throws SQLException;

    void deleteOpzione(int idopzione) throws SQLException;

    boolean checkVotazione(int idevento, int idinvitato) throws SQLException;

    void makeVoto(int idinvitato, int idopzione, int idevento) throws SQLException;

    List<Opzione_evento> getAllopz(int idluogo) throws SQLException;

    boolean checkConflitti(Opzione_evento opz1, Opzione_evento opz2);
}