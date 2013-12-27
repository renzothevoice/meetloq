/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;

import ModelloDati.InterfacciaDataLayer.InvitatoDataLayer;
import ModelloDati.InterfacciaDati.Invitato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvitatoDataLayerImpl extends DataLayerImpl implements InvitatoDataLayer {

    private PreparedStatement cInvitato;
    private PreparedStatement rInvitato;
    private PreparedStatement uInvitato;
    private PreparedStatement dInvitato;
    private PreparedStatement getRubrica;
    private PreparedStatement getListeinvitato;
    private PreparedStatement addInvitatoaevento;
    private PreparedStatement removeInvitatoaevento;
    

    public InvitatoDataLayerImpl(Connection c) throws ClassNotFoundException, SQLException {
        this.getRubrica = c.prepareStatement("SELECT * from invitato WHERE idutente=?");
        this.getListeinvitato = c.prepareStatement("SELECT invitato.*,evento_has_invitato.key FROM invitato \n"
                + "LEFT JOIN evento_has_invitato on invitato.idinvitato=evento_has_invitato.idinvitato\n"
                + "WHERE idevento=?");
        this.addInvitatoaevento = c.prepareStatement("INSERT INTO `meetloq`.`evento_has_invitato`(`idevento`,`idinvitato`) VALUES(?,?)");
        this.removeInvitatoaevento = c.prepareStatement("DELETE FROM `meetloq`.`evento_has_invitato` WHERE idinvitato=? and idevento=?");
        this.rInvitato = c.prepareStatement("SELECT * from invitato where idinvitato=?");
        this.cInvitato = c.prepareStatement("INSERT INTO invitato (email,nome,cognome,idutente) VALUES(?,?,?,?)");
        
        this.dInvitato=c.prepareStatement("DELETE FROM invitato where idinvitato=?");
       
        this.uInvitato = c.prepareStatement("UPDATE `meetloq`.`invitato`\n"
                + "SET\n"
                + "`idinvitato` = ?,\n"
                + "`email` = ?,\n"
                + "`nome` = ?,\n"
                + "`cognome` = ?,\n"
                + "`idutente` = ?\n"
                + "WHERE idinvitato=?");

    }

    @Override
    public void createInvitato(Invitato invitato) throws SQLException {
        this.cInvitato.setString(1, invitato.getEmail());
        this.cInvitato.setString(2, invitato.getNome());
        this.cInvitato.setString(3, invitato.getCognome());
        this.cInvitato.setInt(4, invitato.getIdutente());
        this.cInvitato.executeUpdate();
        ResultSet rs = cInvitato.executeQuery("SELECT LAST_INSERT_ID() from invitato");
        if (rs.next()) {
            invitato.setIdinvitato(rs.getInt("LAST_INSERT_ID()"));
            rs.close();
        } else {
            rs.close();
        }
    }

    @Override
    public Invitato readInvitato(int idinvitato) throws SQLException {
        ResultSet rs;
        this.rInvitato.setInt(1, idinvitato);
        rs = this.rInvitato.executeQuery();
        if (rs.next()) {
            Invitato inv = new InvitatoImpl(this, rs);
            rs.close();
            return inv;
        } else {
            rs.close();
            return null;
        }
    }

    public void updateInvitato(Invitato invitato) throws SQLException {
        this.uInvitato.setString(1, invitato.getEmail());
        this.uInvitato.setString(2, invitato.getNome());
        this.uInvitato.setString(3, invitato.getCognome());
        this.uInvitato.setInt(4, invitato.getIdutente());
        this.uInvitato.executeUpdate();
    }

    @Override
    public List<Invitato> getRubrica(int idutente) throws SQLException {
        ResultSet rs;
        List<Invitato> rubrica = new ArrayList();
        this.getRubrica.setInt(1, idutente);
        rs = this.getRubrica.executeQuery();
        while (rs.next()) {
            Invitato inv = new InvitatoImpl(this, rs);
            rubrica.add(inv);
        }
        rs.close();
        return rubrica;

    }

    @Override
    public List<Invitato> getInvitatievento(int idevento) throws SQLException {
        ResultSet rs;
        List<Invitato> invitati = new ArrayList();
        this.getListeinvitato.setInt(1, idevento);
        rs = this.getListeinvitato.executeQuery();
        while (rs.next()) {
            Invitato inv = new InvitatoImpl(this, rs);
            invitati.add(inv);
        }
        rs.close();        
        return invitati;
    }

    @Override
    public void addInvitatoaevento(List<Invitato> invitati, int idevento) throws SQLException {
        for (int i = 0; i < invitati.size(); i++) {
            Invitato inv = invitati.get(i);
            this.addInvitatoaevento.setInt(1, idevento);
            this.addInvitatoaevento.setInt(2, inv.getIdinvitato());
            this.addInvitatoaevento.executeUpdate();
        }
    }
    
    @Override
    public void addInvitatoaevento(Invitato invitato, int idevento) throws SQLException {
        
            this.addInvitatoaevento.setInt(1, idevento);
            this.addInvitatoaevento.setInt(2, invitato.getIdinvitato());
            this.addInvitatoaevento.executeUpdate();
    }

    @Override
    public void removeInvitatoaevento(int idinvitato, int idevento) throws SQLException {
        this.removeInvitatoaevento.setInt(1, idinvitato);
        this.removeInvitatoaevento.setInt(2, idevento);
        this.removeInvitatoaevento.executeUpdate();
    }

    @Override
    public List<Invitato> getRubricaAndInvitati(int idutente, int idevento) throws SQLException {
        
        List<Invitato> rubrica = getRubrica(idutente);
        List<Invitato> invitatoev=getInvitatievento(idevento);        
               
        for(int i=0;i<rubrica.size();i++)
        {
            Invitato invrub=rubrica.get(i);
            boolean flag=false;
            for(int j=0;j<invitatoev.size();j++)
            {                
                if(invrub.getIdinvitato()==invitatoev.get(j).getIdinvitato()){flag=true;}
            }
            invrub.setIsinvitatoallevento(flag);
        }
           
        return rubrica;
    }
    
    @Override
    public boolean checkInvitatotoevento(int idinvitato,int idevento) throws SQLException
    {
        List <Invitato> inv=this.getInvitatievento(idevento);
        for(int i=0;i<inv.size();i++)
        {
            Invitato inv2=inv.get(i);
            if(inv2.getIdinvitato()==idinvitato)return true;
        }
        return false;
    }
    
    @Override
    public void deleteInvitato(int idinvitato) throws SQLException
    {
        this.dInvitato.setInt(1,idinvitato);
        this.dInvitato.executeUpdate();
    }
}
