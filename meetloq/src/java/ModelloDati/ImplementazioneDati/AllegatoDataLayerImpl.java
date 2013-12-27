/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;

import ModelloDati.InterfacciaDataLayer.AllegatoDataLayer;
import ModelloDati.InterfacciaDati.Allegato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AllegatoDataLayerImpl extends DataLayerImpl implements AllegatoDataLayer {
    private PreparedStatement cAllegato;
    private PreparedStatement rAllegato;
    private PreparedStatement uAllegato;
    private PreparedStatement dAllegato;
    private PreparedStatement rAllegatobyevento;

    public AllegatoDataLayerImpl(Connection c) throws ClassNotFoundException, SQLException {
         this.cAllegato = c.prepareStatement("INSERT INTO allegato (url,nome,idevento) VALUES (?,?,?)");
         this.rAllegatobyevento=c.prepareStatement("SELECT * FROM allegato where idevento=?");
         this.uAllegato=c.prepareStatement("UPDATE allegato SET idallegato=?,url=?,nome=? WHERE idevento=?");
    }
    
    @Override
    public void createAllegato(Allegato al) throws SQLException
    {
        
        this.cAllegato.setString(1,al.getUrl());
        this.cAllegato.setString(2,al.getNome());
        this.cAllegato.setInt(3,al.getIdevento());
        this.cAllegato.executeUpdate();
        ResultSet rs = cAllegato.executeQuery("SELECT LAST_INSERT_ID() from allegato");
        if (rs.next()) {
            al.setIdevento(rs.getInt("LAST_INSERT_ID()"));
            rs.close();
        }
    }
    
    @Override
    public Allegato readAllegato(int idevento) throws SQLException
    {
        this.rAllegatobyevento.setInt(1,idevento);
        ResultSet rs=this.rAllegatobyevento.executeQuery();
        if(rs.next())
        {
            Allegato al=new AllegatoImpl(this,rs);
            return al;
        }
        else return null;
    }
    
    @Override
    public void updateAllegato(Allegato al) throws SQLException
    {
        this.uAllegato.setInt(1,al.getIdallegato());
        this.uAllegato.setString(2,al.getUrl());
        this.uAllegato.setString(3,al.getNome());
        this.uAllegato.setInt(4,al.getIdevento());        
        this.uAllegato.executeUpdate();
        
    }
    
}
