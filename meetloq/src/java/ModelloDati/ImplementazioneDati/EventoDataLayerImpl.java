/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;

import ModelloDati.InterfacciaDataLayer.EventoDataLayer;
import ModelloDati.InterfacciaDati.Evento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventoDataLayerImpl extends DataLayerImpl implements EventoDataLayer {

    private PreparedStatement cEvento;
    private PreparedStatement rEvento;
    private PreparedStatement uEvento;
    private PreparedStatement dEvento;
    private PreparedStatement getListeventi;
    private PreparedStatement getListeventipubblici;
    private PreparedStatement rEventobykey;
    private PreparedStatement getListeinvitato;

    public EventoDataLayerImpl(Connection c) throws ClassNotFoundException, SQLException {

        this.cEvento = c.prepareStatement("INSERT INTO evento (titolo,descrizione,iniziovoto,finevoto,idutente,idopzioneufficiale,eventodefinito,pubblico,invitokey) VALUES(?,?,?,?,?,?,?,?,?)");
        this.rEvento = c.prepareStatement("SELECT * FROM evento WHERE idevento=?");
        this.uEvento = c.prepareStatement("UPDATE `meetloq`.`evento` \n"
                + "SET\n"
                + "`titolo` = ?,\n"
                + "`descrizione` = ?,\n"
                + "`iniziovoto` = ?,\n"
                + "`finevoto` = ?,\n"
                + "`idutente` = ?,\n"
                + "`idopzioneufficiale` = ?,\n"
                + "`eventodefinito` = ?,\n"
                + "`pubblico` = ?,\n"
                + "`invitokey` = ?\n"
                + "WHERE idevento=?;");
        this.dEvento = c.prepareStatement("DELETE FROM evento WHERE idevento=?");
        this.getListeventi = c.prepareStatement("SELECT * FROM evento WHERE idutente=?");
        this.getListeinvitato = c.prepareStatement("SELECT invitato.*,evento_has_invitato.key FROM invitato \n"
                + "LEFT JOIN evento_has_invitato on invitato.idinvitato=evento_has_invitato.idinvitato\n"
                + "WHERE idevento=?");
        this.getListeventipubblici = c.prepareStatement("Select * from evento where pubblico=1");
        this.rEventobykey=c.prepareStatement("Select * from evento where invitokey=?");
    }

    @Override
    public void createEvento(Evento event) throws SQLException {
        int n = (int) (Math.random() * 10000);
        this.cEvento.setString(1, event.getTitolo());
        this.cEvento.setString(2, event.getDescrizione());
        this.cEvento.setString(3, event.getIniziovoto());
        this.cEvento.setString(4, event.getFinevoto());
        this.cEvento.setInt(5, event.getIdutente());
        this.cEvento.setInt(6, 0);
        this.cEvento.setBoolean(7, event.isEventodefinito());
        this.cEvento.setBoolean(8, event.isPubblico());
        this.cEvento.setInt(9, n);
        this.cEvento.executeUpdate();
        ResultSet rs = cEvento.executeQuery("SELECT LAST_INSERT_ID() from evento");
        if (rs.next()) {
            event.setIdevento(rs.getInt("LAST_INSERT_ID()"));

            event.setKey(n);
            rs.close();
        } else {
            rs.close();
        }

    }

    @Override
    public void updateEvento(Evento event) throws SQLException {

        this.uEvento.setString(1, event.getTitolo());
        this.uEvento.setString(2, event.getDescrizione());
        this.uEvento.setString(3, event.getIniziovoto());
        this.uEvento.setString(4, event.getFinevoto());
        this.uEvento.setInt(5, event.getIdutente());
        this.uEvento.setInt(6, event.getIdopzioneufficiale());
        this.uEvento.setBoolean(7, event.isEventodefinito());
        this.uEvento.setBoolean(8, event.isPubblico());
        this.uEvento.setInt(9, event.getKey());
        this.uEvento.setInt(10,event.getIdevento());
        this.uEvento.executeUpdate();
    }

    @Override
    public List<Evento> getListeventi(int idutente) throws SQLException, ClassNotFoundException {
        ResultSet rs;
        this.getListeventi.setInt(1, idutente);
        rs = getListeventi.executeQuery();
        List<Evento> eventi = new ArrayList();
        while (rs.next()) {
            Evento event = new EventoImpl(this, rs);
            eventi.add(event);
        }
        rs.close();
        return eventi;

    }
    
    @Override
    public List<Evento> getListeventipubblici() throws SQLException, ClassNotFoundException {
        ResultSet rs;        
        rs = getListeventipubblici.executeQuery();
        List<Evento> eventi = new ArrayList();
        while (rs.next()) {
            Evento event = new EventoImpl(this, rs);
            eventi.add(event);
        }
        rs.close();
        return eventi;

    }

    @Override
    public Evento readEvento(int idevento) throws SQLException {
        ResultSet rs;
        this.rEvento.setInt(1, idevento);
        rs = this.rEvento.executeQuery();
        Evento event = null;
        if (rs.next()) {
            try {
                event = new EventoImpl(this, rs);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EventoDataLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            rs.close();
            return event;
        } else {
            rs.close();
            return null;
        }
    }
    
    @Override
     public Evento readEventobykey(int key) throws SQLException {
        ResultSet rs;
        this.rEventobykey.setInt(1, key);
        rs = this.rEventobykey.executeQuery();
        Evento event = null;
        if (rs.next()) {
            try {
                event = new EventoImpl(this, rs);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EventoDataLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            rs.close();
            return event;
        } else {
            rs.close();
            return null;
        }
    }
    
    @Override
    public void deleteEvento(int idevento) throws SQLException
    {
        this.dEvento.setInt(1,idevento);
        this.dEvento.executeUpdate();
    }

    @Override
    public boolean isValidDate(String input) {

        SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/dd");
        try {
            format.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }

    }
    
    
}
