/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;

import ModelloDati.InterfacciaDataLayer.Opzione_eventoDataLayer;
import ModelloDati.InterfacciaDati.Opzione_evento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Opzione_eventoDataLayerImpl extends DataLayerImpl implements Opzione_eventoDataLayer {

    private PreparedStatement cOpzione;
    private PreparedStatement rOpzione;
    private PreparedStatement uOpzione;
    private PreparedStatement dOpzione;
    private PreparedStatement getListeopzioni;
    private PreparedStatement OpzioniUfficiali;
    private PreparedStatement checkVotazione;
    private PreparedStatement makeVoto;
    private PreparedStatement rOpzione2;

    public Opzione_eventoDataLayerImpl(Connection c) throws ClassNotFoundException, SQLException {
        this.rOpzione = c.prepareStatement("SELECT * FROM opzione_evento WHERE idopzione=?");
        this.rOpzione2 = c.prepareStatement("SELECT * FROM opzione_evento WHERE idluogo=?");
        this.getListeopzioni = c.prepareStatement("SELECT * FROM opzione_evento WHERE idevento=?");
        this.cOpzione = c.prepareStatement("INSERT INTO `meetloq`.`opzione_evento` (`datainizio`,`datafine`,`idevento`,`idluogo`,`contatore`,`disponibile`)VALUES(?,?,?,?,?,?)");
        this.OpzioniUfficiali = c.prepareStatement("select opzione_evento.*,luogo.* from opzione_evento LEFT JOIN evento on opzione_evento.idopzione=evento.idopzioneufficiale LEFT JOIN luogo on opzione_evento.idluogo=luogo.idluogo where opzione_evento.idopzione=evento.idopzioneufficiale");
        this.uOpzione = c.prepareStatement("UPDATE `meetloq`.`opzione_evento`\n"
                + "SET\n"
                + "`datainizio` = ?,\n"
                + "`datafine` = ?,\n"
                + "`idevento` = ?,\n"
                + "`idluogo` = ?,\n"
                + "`contatore` = ?,\n"
                + "`disponibile` = ?\n"
                + "WHERE idopzione=?");
        this.dOpzione=c.prepareStatement("DELETE FROM `meetloq`.`opzione_evento` WHERE idopzione=?;");
        this.checkVotazione=c.prepareStatement("SELECT * from voto where idevento=? and idinvitato=?");
        this.makeVoto=c.prepareStatement("INSERT INTO `meetloq`.`voto` (`idinvitato`,`idopzione`,`idevento`) VALUES (?,?,?)");
    }

    @Override
    public List<Opzione_evento> getListopzioni(int idevento) throws SQLException {
        ResultSet rs;
        this.getListeopzioni.setInt(1, idevento);
        rs = this.getListeopzioni.executeQuery();
        List<Opzione_evento> opzioni = new ArrayList();
        while (rs.next()) {
            Opzione_evento opzione = new Opzione_eventoImpl(this, rs);
            opzioni.add(opzione);
        }
        rs.close();
        return opzioni;

    }
    
    @Override
    public List<Opzione_evento> getAllopz(int idluogo) throws SQLException{
        this.rOpzione2.setInt(1,idluogo);
        ResultSet rs=this.rOpzione2.executeQuery();
        List<Opzione_evento> tmp=new ArrayList();
        while(rs.next())
        {
            Opzione_evento opz=new Opzione_eventoImpl(this,rs);
            tmp.add(opz);
        }
        rs.close();
        return tmp;
    }

    @Override
    public void createOpzione(Opzione_evento opz) throws SQLException {
        this.cOpzione.setString(1, opz.getDatainizio());
        this.cOpzione.setString(2, opz.getDatafine());
        this.cOpzione.setInt(3, opz.getIdevento());
        this.cOpzione.setInt(4, opz.getIdluogo());
        this.cOpzione.setInt(5, opz.getContatore());
        this.cOpzione.setBoolean(6, opz.isDisponibile());
        this.cOpzione.executeUpdate();
        ResultSet rs = cOpzione.executeQuery("SELECT LAST_INSERT_ID() from Opzione_evento");
        if (rs.next()) {
            opz.setIdopzione(rs.getInt("LAST_INSERT_ID()"));
            rs.close();
        } else {
            rs.close();
        }
    }

    @Override
    public Opzione_evento readOpzione(int idopzione) throws SQLException {
        this.rOpzione.setInt(1, idopzione);
        ResultSet rs = this.rOpzione.executeQuery();
        if (rs.next()) {
            Opzione_evento op = new Opzione_eventoImpl(this, rs);
            rs.close();
            return op;
        } else {
            rs.close();
            return null;
        }
    }

    @Override
    public void updateOpzione(Opzione_evento opz) throws SQLException {
        this.uOpzione.setString(1, opz.getDatainizio());
        this.uOpzione.setString(2, opz.getDatafine());
        this.uOpzione.setInt(3, opz.getIdevento());
        this.uOpzione.setInt(4, opz.getIdluogo());
        this.uOpzione.setInt(5, opz.getContatore());
        this.uOpzione.setBoolean(6, opz.isDisponibile());
        this.uOpzione.setInt(7, opz.getIdopzione());
        this.uOpzione.executeUpdate();
        
    }

    @Override
    public void deleteOpzione(int idopzione) throws SQLException
    {
        this.dOpzione.setInt(1,idopzione);
        this.dOpzione.executeUpdate();
    }
    
    @Override
    public void createOpzioni(List<Opzione_evento> opzioni) throws SQLException {

        for (int i = 0; i < opzioni.size(); i++) {

            createOpzione(opzioni.get(i));

        }
    }

    @Override
    public boolean checkOpzioneDisponibile(Opzione_evento opzione) throws SQLException {
        ResultSet rs;
        boolean flag = true;

        rs = this.OpzioniUfficiali.executeQuery();

        if (rs.next() && flag) {

            Opzione_evento opz2 = new Opzione_eventoImpl(this, rs);

            flag = checkConflitti(opzione, opz2);

        }
        return flag;
    }

    public boolean checkConflitti(Opzione_evento opz1, Opzione_evento opz2) {
        String[] temp1ini;
        String[] temp1fin;
        String[] temp2ini;
        String[] temp2fin;

        String temp = opz1.getDatainizio();
        String temp2 = opz1.getDatafine();
        System.out.println(temp);
        System.out.println(temp2);
        temp1ini = temp.split(" ");
        temp1fin = temp2.split(" ");

        temp = opz2.getDatainizio();
        temp2 = opz2.getDatafine();
        System.out.println(temp);
        System.out.println(temp2);
        temp2ini = temp.split(" ");
        temp2fin = temp2.split(" ");
        System.out.println(temp2ini[2]);
        System.out.println(temp2fin[2]);
        System.out.println(temp1ini[2]);
        System.out.println(temp1fin[2]);
        if (temp2ini[0].equals(temp1ini[0]) || temp2ini[0].equals(temp1fin[0]) || temp2fin[0].equals(temp1ini[0]) || temp2fin[0].equals(temp1fin[0])) {
            System.out.println(temp2ini[2]);
            System.out.println(temp2fin[2]);
            System.out.println(temp1ini[2]);
            System.out.println(temp1fin[2]);
             System.out.println("ciao");
            temp1ini[1] = temp1ini[2].replace(":", "");
            //out.println(temp1ini[1]);
            float timeini1 = Float.parseFloat(temp1ini[1]);
            System.out.println(timeini1);
            temp1fin[1] = temp1fin[2].replace(":", "");
            //out.println(temp1fin[1]);
            float timefin1 = Float.parseFloat(temp1fin[1]);
            System.out.println(timefin1);
            temp2fin[1] = temp2fin[2].replace(":", "");
            //out.println(temp1fin[1]);
            float timefin2 = Float.parseFloat(temp2fin[1]);
            System.out.println(timefin2);
            temp2ini[1] = temp2ini[2].replace(":", "");
            //out.println(temp1ini[1]);
            float timeini2 = Float.parseFloat(temp2ini[1]);
            System.out.println(timeini2);
            //out.println(timeini1);
            //out.println(timefin1);
            //out.println(timeini2);
            //out.println(timefin2);

            if (timeini1 <= timeini2 && timeini2 < timefin1) {
                System.out.println("false");
                return false;
            }
            if (timeini1 < timefin2 && timefin2 <= timefin1) {
                System.out.println("false");
                return false;
            }
            if (timeini1 == timefin2 || timefin2 == timefin1) {
                System.out.println("false");
                return false;
            }
        }
        
        return true;
    }

    @Override
    public boolean isValidDate(String input) {
        SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/dd - HH:mm");
        try {
            format.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }


    }
    
    @Override
    public boolean checkVotazione(int idevento, int idinvitato) throws SQLException
    {
        ResultSet rs;
        this.checkVotazione.setInt(1,idevento);
        this.checkVotazione.setInt(2,idinvitato);
        rs=this.checkVotazione.executeQuery();
        if(rs.next()){return false;} //ha gia votato
        else return true; // può votare!
    }
    
    @Override
    public void makeVoto(int idinvitato, int idopzione, int idevento) throws SQLException
    {
        this.makeVoto.setInt(1,idinvitato);
        this.makeVoto.setInt(2,idopzione);
        this.makeVoto.setInt(3,idevento);
        this.makeVoto.executeUpdate();
    }
    
}
