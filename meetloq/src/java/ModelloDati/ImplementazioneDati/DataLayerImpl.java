/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;
import ModelloDati.InterfacciaDataLayer.AttrezzaturaDataLayer;
import ModelloDati.InterfacciaDataLayer.AllegatoDataLayer;
import ModelloDati.InterfacciaDataLayer.InvitatoDataLayer;
import ModelloDati.InterfacciaDataLayer.Opzione_eventoDataLayer;
import ModelloDati.InterfacciaDataLayer.EventoDataLayer;
import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDataLayer.LuogoDataLayer;
import ModelloDati.InterfacciaDataLayer.UtenteDataLayer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author Alessio
 */
public class DataLayerImpl implements DataLayer {
    private Connection c;
    private UtenteDataLayer udl;
    private EventoDataLayer edl;
    private Opzione_eventoDataLayer oedl;
    private InvitatoDataLayer idl;
    private LuogoDataLayer ldl;
    private AllegatoDataLayer adl;
    private AttrezzaturaDataLayer attdl;
    
    public DataLayerImpl() throws ClassNotFoundException {
    try {
            
            Class.forName("com.mysql.jdbc.Driver");
            this.c = DriverManager.getConnection("jdbc:mysql://localhost/meetloq","root","");
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

 
    @Override
    public Connection getC() {
        return this.c;
    }

    @Override
    public UtenteDataLayer getUtenteDataLayer(Connection c) throws ClassNotFoundException, SQLException
    {
        if(this.udl==null){
        this.udl=new UtenteDataLayerImpl(c);
        return udl;
        }
        else return null;
    }
    
    @Override
    public EventoDataLayer getEventoDataLayer(Connection c) throws ClassNotFoundException, SQLException
    {
        if(this.edl==null){
        this.edl=new EventoDataLayerImpl(c);
        return edl;
        }
        else return null;
    }
    
    @Override
    public Opzione_eventoDataLayer getOpzione_eventoDataLayer(Connection c) throws ClassNotFoundException, SQLException
    {
        if(this.oedl==null){
        this.oedl=new Opzione_eventoDataLayerImpl(c);
        return oedl;
        }
        else return null;
    }
 
    @Override
    public InvitatoDataLayer getInvitatoDataLayer(Connection c) throws ClassNotFoundException, SQLException
    {
        if(this.idl==null){
        this.idl=new InvitatoDataLayerImpl(c);
        return idl;
        }
        else return null;
    }
    
    @Override
    public LuogoDataLayer getLuogoDataLayer(Connection c) throws ClassNotFoundException, SQLException
    {
        if(this.ldl==null){
        this.ldl=new LuogoDataLayerImpl(c);
        return ldl;
        }
        else return null;
    
    }
   

    @Override
    public AllegatoDataLayer getAllegatoDataLayer(Connection c) throws ClassNotFoundException, SQLException
    {
        if(this.adl==null){
        this.adl=new AllegatoDataLayerImpl(c);
        return adl;
        }
        else return null;
    }
    
    @Override
    public AttrezzaturaDataLayer getAttrezaturaDataLayer(Connection c) throws ClassNotFoundException, SQLException
    {
        if(this.attdl==null){
        this.attdl=new AttrezzaturaDataLayerImpl(c);
        return attdl;
        }
        else return null;
    }
	






    
    
    
}
