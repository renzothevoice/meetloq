/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;

import ModelloDati.InterfacciaDataLayer.UtenteDataLayer;
import ModelloDati.InterfacciaDati.Utente;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UtenteDataLayerImpl extends DataLayerImpl implements UtenteDataLayer {
    private PreparedStatement cUtente;
    private PreparedStatement rUtente;
    private PreparedStatement rUtentebyusername;
    private PreparedStatement uUtente;
    private PreparedStatement dUtente;
    private PreparedStatement checkPassword;
    private PreparedStatement checkUsername;
    private PreparedStatement getListUtenti;

    public UtenteDataLayerImpl(Connection c) throws ClassNotFoundException, SQLException {      
        
        cUtente = c.prepareStatement("INSERT INTO utente (nome,cognome,bday,username,password,email,azienda,ruolo,idtipo_utente) VALUES (?,?,?,?,?,?,?,?,?)");
        rUtente = c.prepareStatement("SELECT * FROM utente WHERE idutente=?");
        rUtentebyusername = c.prepareStatement("SELECT * FROM utente WHERE username=?");
        uUtente = c.prepareStatement("UPDATE utente SET nome=?,cognome=?,bday=?,username=?,password=?,email=?,azienda=?,ruolo=?,idtipo_utente=? WHERE idutente=?");
        dUtente = c.prepareStatement("DELETE FROM utente WHERE idutente=?");
        checkPassword = c.prepareStatement("SELECT * FROM utente WHERE username=? AND password=?");
        checkUsername = c.prepareStatement("SELECT * FROM utente WHERE username=?");
        getListUtenti = c.prepareStatement("SELECT * FROM utente");
    }
    
    @Override
    public void createUtente(Utente user) throws SQLException
    {
        
        this.cUtente.setString(1,user.getNome());
        this.cUtente.setString(2,user.getCognome());
        this.cUtente.setString(3,user.getBday());
        this.cUtente.setString(4,user.getUsername());
        this.cUtente.setString(5,user.getPassword());
        this.cUtente.setString(6,user.getEmail());
        this.cUtente.setString(7,user.getAzienda());
        this.cUtente.setString(8,user.getRuolo());
        this.cUtente.setInt(9,user.getIdtipo_utente());
        this.cUtente.executeUpdate();
        ResultSet rs = cUtente.executeQuery("SELECT LAST_INSERT_ID() from utente");
        if (rs.next()) {
            user.setIdutente(rs.getInt("LAST_INSERT_ID()"));
            rs.close();
        }
        else rs.close();
        
    }
    
    @Override
    public boolean checkPassword(String username, String password) throws SQLException {
        ResultSet rs;
        this.checkPassword.setString(1, username);
        this.checkPassword.setString(2, password);
        rs = checkPassword.executeQuery();
        if (rs.next()) {
            rs.close();
            return true;
        } else {
            rs.close();
            return false;
        }
    }
    
    @Override
    public Utente readUtente(String username) throws SQLException
    {
        ResultSet rs;
        this.rUtentebyusername.setString(1,username);
        rs=this.rUtentebyusername.executeQuery();
        Utente utente=null;
        if(rs.next())
        {
            try {
                utente=new UtenteImpl(this,rs);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UtenteDataLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            rs.close();
            return utente;
        }
        else 
        {
        rs.close();
        return null;
        }
        
    }
    
    @Override
    public Utente readUtente(int idutente) throws SQLException
    {
        ResultSet rs;
        this.rUtente.setInt(1,idutente);
        rs=this.rUtente.executeQuery();
        Utente utente=null;
        if(rs.next())
        {
            try {
                utente=new UtenteImpl(this,rs);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UtenteDataLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            rs.close();
            return utente;
        }
        else 
        {
        rs.close();
        return null;
        }
        
    }
    
    @Override
    public boolean checkUsername(String username) throws SQLException
    {
        ResultSet rs;
        this.checkUsername.setString(1,username);
        rs=this.checkUsername.executeQuery();
        if(rs.next()){rs.close(); return true;}
        else{rs.close(); return false;}
    }
    
    
    @Override
    public void updateUtente(Utente user) throws SQLException
    {
        System.out.println(user.getIdtipo_utente());
        this.uUtente.setString(1,user.getNome());
        this.uUtente.setString(2,user.getCognome());
        this.uUtente.setString(3,user.getBday());
        this.uUtente.setString(4,user.getUsername());
        this.uUtente.setString(5,user.getPassword());
        this.uUtente.setString(6,user.getEmail());
        this.uUtente.setString(7,user.getAzienda());
        this.uUtente.setString(8,user.getRuolo());
        this.uUtente.setInt(9,user.getIdtipo_utente());
        this.uUtente.setInt(10,user.getIdutente());
        this.uUtente.executeUpdate();
    }
    
    @Override
    public void deleteUtente(int idutente) throws SQLException
    {
        this.dUtente.setInt(1,idutente);
        this.dUtente.executeUpdate();
    }
    
    @Override
    public List<Utente> getListUtenti() throws SQLException, ClassNotFoundException
    {
        ResultSet rs=this.getListUtenti.executeQuery();
        List <Utente> lu=new ArrayList();
        while(rs.next())
        {
            Utente user=new UtenteImpl(rs);
            lu.add(user);
        }
        return lu;
    }
}
