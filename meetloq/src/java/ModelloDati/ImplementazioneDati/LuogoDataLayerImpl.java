/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;

import ModelloDati.InterfacciaDataLayer.LuogoDataLayer;
import ModelloDati.InterfacciaDati.Luogo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LuogoDataLayerImpl extends DataLayerImpl implements LuogoDataLayer {
    private PreparedStatement cLuogo;
    private PreparedStatement rLuogo;
    private PreparedStatement rLuogobyid;
    private PreparedStatement uLuogo;
    private PreparedStatement dLuogo;
     
    

    public LuogoDataLayerImpl(Connection c) throws ClassNotFoundException, SQLException {        
        this.rLuogo=c.prepareStatement("SELECT * FROM luogo");
        this.rLuogobyid=c.prepareStatement("SELECT * FROM luogo where idluogo=?");    
        this.dLuogo=c.prepareStatement("DELETE FROM luogo where idluogo=?");
        this.cLuogo=c.prepareStatement("INSERT INTO `meetloq`.`luogo`(`nome_luogo`,`indirizzo`,`latitudine`,`longitudine`,`tipo_luogo`,`posti`)VALUES(?,?,?,?,?,?)");
        this.uLuogo=c.prepareStatement("UPDATE `meetloq`.`luogo`\n" +
"SET\n" +
"`nome_luogo` = ?,\n" +
"`indirizzo` = ?,\n" +
"`latitudine` = ?,\n" +
"`longitudine` = ?,\n" +
"`tipo_luogo` = ?,\n" +
"`posti` = ?\n" +
"WHERE idluogo=?;");
    }
    
    @Override
    public void createLuogo(Luogo luogo) throws SQLException
    {
        this.cLuogo.setString(1,luogo.getNome_luogo());
        this.cLuogo.setString(2,luogo.getIndirizzo());
        this.cLuogo.setString(3,luogo.getLatitudine());
        this.cLuogo.setString(4,luogo.getLongitudine());
        this.cLuogo.setString(5,luogo.getTipo_luogo());
        this.cLuogo.setInt(6,luogo.getPosti());
        this.cLuogo.executeUpdate();
        ResultSet rs = cLuogo.executeQuery("SELECT LAST_INSERT_ID() from luogo");
        if (rs.next()) {
            luogo.setIdluogo(rs.getInt("LAST_INSERT_ID()"));
            rs.close();
        }
        else rs.close();
    }
    
    @Override
    public void updateLuogo(Luogo luogo) throws SQLException
    {
        this.uLuogo.setString(1,luogo.getNome_luogo());
        this.uLuogo.setString(2,luogo.getIndirizzo());
        this.uLuogo.setString(3,luogo.getLatitudine());
        this.uLuogo.setString(4,luogo.getLongitudine());
        this.uLuogo.setString(5,luogo.getTipo_luogo());
        this.uLuogo.setInt(6,luogo.getPosti());
        this.uLuogo.setInt(7,luogo.getIdluogo());
        this.uLuogo.executeUpdate();
        
    }
    
    @Override
    public List<Luogo> readLuoghi() throws SQLException
    {
        ResultSet rs;
        rs=this.rLuogo.executeQuery();
        List<Luogo> luoghi=new ArrayList();
        while(rs.next())
        {
            Luogo luogo=new LuogoImpl(this,rs);
            luoghi.add(luogo);
        }
        rs.close();
        return luoghi;
    }
    
    @Override
    public Luogo readLuogobyid(int idluogo) throws SQLException
    {
        this.rLuogobyid.setInt(1,idluogo);
        ResultSet rs=this.rLuogobyid.executeQuery();
        if(rs.next())
        {
            Luogo l=new LuogoImpl(this,rs);
            rs.close();
            return l;
        }
        else {rs.close(); return null;}
    }
    
    @Override
    public void deleteLuogo(int idluogo) throws SQLException
    {
        this.dLuogo.setInt(1,idluogo);
        this.dLuogo.executeUpdate();
    }
    
}
