/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.InterfacciaDataLayer;

import ModelloDati.InterfacciaDati.Evento;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Alessio
 */
public interface EventoDataLayer {

    List<Evento> getListeventi(int idutente) throws SQLException, ClassNotFoundException;

    Evento readEvento(int idevento) throws SQLException;

    void createEvento(Evento event) throws SQLException;

    boolean isValidDate(String input);

    void updateEvento(Evento event) throws SQLException;

    void deleteEvento(int idevento) throws SQLException;
    
    List<Evento> getListeventipubblici() throws SQLException, ClassNotFoundException;
    Evento readEventobykey(int key) throws SQLException;
}
