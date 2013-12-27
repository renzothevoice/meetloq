/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.InterfacciaDataLayer;

import ModelloDati.InterfacciaDati.Allegato;
import java.sql.SQLException;

/**
 *
 * @author Alessio
 */
public interface AllegatoDataLayer {

    void createAllegato(Allegato al) throws SQLException;

    Allegato readAllegato(int idevento) throws SQLException;
    
    void updateAllegato(Allegato al) throws SQLException;
}
