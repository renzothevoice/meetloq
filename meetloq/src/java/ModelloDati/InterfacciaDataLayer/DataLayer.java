/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.InterfacciaDataLayer;

import java.sql.Connection;
import java.sql.SQLException;


/**
 *
 * @author Alessio
 */
public interface DataLayer {

    

    Connection getC();
    UtenteDataLayer getUtenteDataLayer(Connection c) throws ClassNotFoundException, SQLException;
    EventoDataLayer getEventoDataLayer(Connection c) throws ClassNotFoundException, SQLException;
    Opzione_eventoDataLayer getOpzione_eventoDataLayer(Connection c) throws ClassNotFoundException, SQLException;
    InvitatoDataLayer getInvitatoDataLayer(Connection c) throws ClassNotFoundException, SQLException;
    LuogoDataLayer getLuogoDataLayer(Connection c) throws ClassNotFoundException, SQLException;
    AllegatoDataLayer getAllegatoDataLayer(Connection c) throws ClassNotFoundException, SQLException;
    AttrezzaturaDataLayer getAttrezaturaDataLayer(Connection c) throws ClassNotFoundException, SQLException;
    
}
