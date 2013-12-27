/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.InterfacciaDataLayer;

import ModelloDati.InterfacciaDati.Utente;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Alessio
 */
public interface UtenteDataLayer {

boolean checkPassword(String username, String password) throws SQLException;
Utente readUtente(String username) throws SQLException;
Utente readUtente(int idutente) throws SQLException;
boolean checkUsername(String username) throws SQLException;
public void createUtente(Utente user) throws SQLException;
public void deleteUtente(int idutente) throws SQLException;
public void updateUtente(Utente user) throws SQLException;
public List<Utente> getListUtenti() throws SQLException, ClassNotFoundException;
}
