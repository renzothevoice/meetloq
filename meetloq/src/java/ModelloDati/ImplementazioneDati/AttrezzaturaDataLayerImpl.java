/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;


import ModelloDati.InterfacciaDataLayer.AttrezzaturaDataLayer;
import ModelloDati.InterfacciaDati.Attrezzatura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AttrezzaturaDataLayerImpl extends DataLayerImpl implements AttrezzaturaDataLayer {
    private PreparedStatement cAttrezzatura;
    private PreparedStatement rAttrezzaturabyluogo;
    private PreparedStatement rAttrezzatura;
    private PreparedStatement addAttrezzaturabyluogo;
    private PreparedStatement uAttrezzatura;
    private PreparedStatement dAttrezzatura;
    private PreparedStatement dAttrezzaturabyluogo;
    private PreparedStatement modListAtt;
    private PreparedStatement checkAttLuogo;

    public AttrezzaturaDataLayerImpl(Connection c) throws ClassNotFoundException, SQLException {
        this.rAttrezzaturabyluogo=c.prepareStatement("SELECT * FROM attrezzatura\n" +
"left join attrezzatura_has_luogo on attrezzatura.idattrezzatura=attrezzatura_has_luogo.idattrezzatura\n" +
"where idluogo=?");
        this.rAttrezzatura=c.prepareStatement("SELECT * FROM attrezzatura");
        this.addAttrezzaturabyluogo=c.prepareStatement("INSERT INTO `meetloq`.`attrezzatura_has_luogo`\n" +
"(`idattrezzatura`,\n" +
"`idluogo`)\n"+
"VALUES(?,?)");
    
    this.cAttrezzatura=c.prepareStatement("INSERT INTO `meetloq`.`attrezzatura`\n" +
"(`nome_attrezzatura`)\n" +
"VALUES\n" +
"(?)");
    
    this.dAttrezzatura=c.prepareStatement("DELETE FROM `meetloq`.`attrezzatura`\n" +
"WHERE idattrezzatura=?;");
    
    this.modListAtt=c.prepareStatement("SELECT * FROM attrezzatura_has_luogo where idluogo=?");
    
    this.checkAttLuogo=c.prepareStatement("SELECT * from attrezzatura_has_evento where idattrezzatura=? and idluogo=?");
    this.dAttrezzaturabyluogo=c.prepareStatement("DELETE FROM `meetloq`.`attrezzatura_has_luogo`\n" +
"WHERE idluogo=?;");
    }
    
    @Override
    public boolean checkAttLuogo(int idatt, int idluogo) throws SQLException
    {
        this.checkAttLuogo.setInt(1,idatt);
        this.checkAttLuogo.setInt(2,idluogo);
        ResultSet rs=this.checkAttLuogo.executeQuery();
        if(rs.next()){rs.close(); return true;}
        else{rs.close(); return false;}
    }
    
    @Override
    public void createAttrezzatura(Attrezzatura att) throws SQLException
    {
        this.cAttrezzatura.setString(1,att.getNome_attrezzatura());
        this.cAttrezzatura.executeUpdate();
        ResultSet rs = cAttrezzatura.executeQuery("SELECT LAST_INSERT_ID() from evento");
        if (rs.next()) {
            att.setIdattrezzatura(rs.getInt("LAST_INSERT_ID()"));            
            rs.close();
        } else {
            rs.close();
        }
        
    }
    
    @Override
    public void deleteAttrezzatura(int id) throws SQLException
    {
        this.dAttrezzatura.setInt(1,id);
        this.dAttrezzatura.executeUpdate();
    }
    
    @Override
    public void deleteAttrezzaturabyluogo(int idluogo) throws SQLException
    {
        this.dAttrezzaturabyluogo.setInt(1,idluogo);
        this.dAttrezzaturabyluogo.executeUpdate();
    }
    
    @Override
    public void modListAtt(List <Attrezzatura> latt,int idluogo) throws SQLException
    {
        this.modListAtt.setInt(1,idluogo);
        ResultSet rs=this.modListAtt.executeQuery();   
       
        while(rs.next())
        {
            
            for(int i=0;i<latt.size();i++)
            {
                if(latt.get(i).getIdattrezzatura()==rs.getInt("idattrezzatura")){latt.get(i).setIspresent(true);}
                
            }
        }
        
        rs.close();
    }
    
    
    
    
    @Override
    public List<Attrezzatura> readAttrezzatura(int idluogo) throws SQLException
    {
        this.rAttrezzaturabyluogo.setInt(1,idluogo);
        ResultSet rs=this.rAttrezzaturabyluogo.executeQuery();
        List <Attrezzatura> tmp=new ArrayList();
        while(rs.next())
        {
            Attrezzatura att=new AttrezzaturaImpl(this,rs);
            tmp.add(att);
        }
        rs.close();
        return tmp;
    }
    
    @Override
    public void addAttrezzaturabyluogo(int idattrezzatura,int idluogo) throws SQLException
    {
        this.addAttrezzaturabyluogo.setInt(1,idattrezzatura);
        this.addAttrezzaturabyluogo.setInt(2,idluogo);
        this.addAttrezzaturabyluogo.executeUpdate();
        
    }
    
    @Override
    public List<Attrezzatura> readAttrezzatura() throws SQLException
    {
        ResultSet rs=this.rAttrezzatura.executeQuery();
        List <Attrezzatura> tmp = new ArrayList();
        while(rs.next())
        {
            Attrezzatura tmp2=new AttrezzaturaImpl(this,rs);
            tmp.add(tmp2);
        }
        rs.close();
        return tmp;
        
    }
    
}
