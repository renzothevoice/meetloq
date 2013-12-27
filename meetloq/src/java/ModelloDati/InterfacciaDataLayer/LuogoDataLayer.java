/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.InterfacciaDataLayer;

import ModelloDati.InterfacciaDati.Luogo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Alessio
 */
public interface LuogoDataLayer {

    List<Luogo> readLuoghi() throws SQLException;
    public Luogo readLuogobyid(int idluogo) throws SQLException;
    public void deleteLuogo(int idluogo) throws SQLException;
    public void createLuogo(Luogo luogo) throws SQLException;
      public void updateLuogo(Luogo luogo) throws SQLException;
}
