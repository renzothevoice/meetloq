package ModelloDati.ImplementazioneDati;

import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDati.Allegato;
import ModelloDati.InterfacciaDati.Evento;
import ModelloDati.InterfacciaDati.Img;
import ModelloDati.InterfacciaDati.Invitato;
import ModelloDati.InterfacciaDati.Opzione_evento;
import ModelloDati.InterfacciaDati.Utente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;

/*CREATE  TABLE IF NOT EXISTS `meetloq`.`evento` (
 `idevento` INT NOT NULL AUTO_INCREMENT ,
 `titolo` VARCHAR(45) NULL ,
 `descrizione` TEXT NULL ,
 `iniziovoto` VARCHAR(45) NULL ,
 `finevoto` VARCHAR(45) NULL ,
 `idutente` INT NOT NULL COMMENT 'idutente organizzatore' ,
 `idopzioneufficiale` INT NULL ,
 `eventodefinito` TINYINT(1) NULL ,
 `pubblico` TINYINT(1) NULL ,
 PRIMARY KEY (`idevento`) ,
 INDEX `fk_evento_utente1_idx` (`idutente` ASC) ,
 CONSTRAINT `fk_evento_utente1`
 FOREIGN KEY (`idutente` )
 REFERENCES `meetloq`.`utente` (`idutente` )
 ON DELETE RESTRICT
 ON UPDATE RESTRICT)
 ENGINE = InnoDB*/
public class EventoImpl implements Evento {

    private int idevento;
    private String titolo;
    private String descrizione;
    private String iniziovoto;
    private String finevoto;
    private int idutente;
    private boolean eventodefinito;
    private boolean pubblico;
    private int key;
    private int idopzioneufficiale;
    private List<Opzione_evento> opzioni;
    private List<Invitato> invitatievento;
    private Img locandina;
    private Allegato allegato;
    private Utente organizzatore;
    private Opzione_evento opzioneufficiale;

    //creazione oggetto iniziale
    public EventoImpl(int idevento, String titolo, String descrizione, String iniziovoto, String finevoto, int idutente, boolean eventodefinito, boolean pubblico) {
        this.idevento = idevento;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.iniziovoto = iniziovoto;
        this.finevoto = finevoto;
        this.idutente = idutente;
        this.eventodefinito = eventodefinito;
        this.pubblico = pubblico;
        this.opzioni = null;
        this.invitatievento = null;
        this.locandina = null;
        this.allegato = null;
        this.organizzatore = null;
        this.opzioneufficiale = null;
        this.idopzioneufficiale = 0;
    }

    public EventoImpl(DataLayer dl, ResultSet rs) throws SQLException, ClassNotFoundException {


        this.idevento = rs.getInt("idevento");
        this.titolo = rs.getString("titolo");
        this.descrizione = rs.getString("descrizione");
        this.iniziovoto = rs.getString("iniziovoto");
        this.finevoto = rs.getString("finevoto");
        this.idutente = rs.getInt("idutente");
        this.idopzioneufficiale = rs.getInt("idopzioneufficiale");
        this.eventodefinito = rs.getBoolean("eventodefinito");
        this.pubblico = rs.getBoolean("pubblico");
        this.key = rs.getInt("invitokey");
        this.opzioni = null;
        this.invitatievento = null;
        this.locandina = null;
        this.allegato = null;
        this.organizzatore = null;
        this.opzioneufficiale = null;

    }

    @Override
    public int getIdevento() {
        return idevento;
    }

    @Override
    public void setIdevento(int idevento) {
        this.idevento = idevento;
    }

    @Override
    public String getTitolo() {
        return titolo;
    }

    @Override
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    @Override
    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String getIniziovoto() {
        return iniziovoto;
    }

    @Override
    public void setIniziovoto(String iniziovoto) {
        this.iniziovoto = iniziovoto;
    }

    @Override
    public String getFinevoto() {
        return finevoto;
    }

    @Override
    public void setFinevoto(String finevoto) {
        this.finevoto = finevoto;
    }

    @Override
    public int getIdutente() {
        return idutente;
    }

    @Override
    public void setIdutente(int idutente) {
        this.idutente = idutente;
    }

    @Override
    public boolean isEventodefinito() {
        return eventodefinito;
    }

    @Override
    public void setEventodefinito(boolean eventodefinito) {
        this.eventodefinito = eventodefinito;
    }

    @Override
    public boolean isPubblico() {
        return pubblico;
    }

    @Override
    public void setPubblico(boolean pubblico) {
        this.pubblico = pubblico;
    }

    @Override
    public List<Opzione_evento> getOpzioni() {
        return opzioni;
    }

    @Override
    public void setOpzioni(List<Opzione_evento> opzioni) {
        this.opzioni = opzioni;
    }

    @Override
    public List<Invitato> getInvitatievento() {
        return invitatievento;
    }

    @Override
    public void setInvitatievento(List<Invitato> invitatievento) {
        this.invitatievento = invitatievento;
    }

    @Override
    public Img getLocandina() {
        return locandina;
    }

    @Override
    public void setLocandina(Img locandina) {
        this.locandina = locandina;
    }

    @Override
    public Allegato getAllegato() {
        return allegato;
    }

    @Override
    public void setAllegato(Allegato allegato) {
        this.allegato = allegato;
    }

    @Override
    public Utente getOrganizzatore() {
        return organizzatore;
    }

    @Override
    public void setOrganizzatore(Utente organizzatore) {
        this.organizzatore = organizzatore;
    }

    @Override
    public Opzione_evento getOpzioneufficiale() {
        return opzioneufficiale;
    }

    @Override
    public void setOpzioneufficiale(Opzione_evento opzioneufficiale) {
        this.opzioneufficiale = opzioneufficiale;
    }

    public boolean isValidDate(String input) {
        SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/dd");
        try {
            format.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public int getIdopzioneufficiale() {
        return idopzioneufficiale;
    }

    @Override
    public void setIdopzioneufficiale(int idopzioneufficiale) {
        this.idopzioneufficiale = idopzioneufficiale;
    }
}
