/*CREATE  TABLE IF NOT EXISTS `meetloq`.`utente` (
  `idutente` INT(11) NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(45) NULL DEFAULT NULL ,
  `cognome` VARCHAR(45) NULL DEFAULT NULL ,
  `bday` VARCHAR(45) NULL DEFAULT NULL ,
  `username` VARCHAR(45) NULL DEFAULT NULL ,
  `password` VARCHAR(45) NULL DEFAULT NULL ,
  `email` VARCHAR(45) NULL DEFAULT NULL ,
  `azienda` VARCHAR(45) NULL DEFAULT NULL ,
  `ruolo` VARCHAR(45) NULL DEFAULT NULL ,
  `idtipo_utente` INT(11) NOT NULL ,
  PRIMARY KEY (`idutente`) ,
  INDEX `fk_utente_tipo_utente_idx` (`idtipo_utente` ASC) ,
  CONSTRAINT `fk_utente_tipo_utente`
    FOREIGN KEY (`idtipo_utente` )
    REFERENCES `meetloq`.`tipo_utente` (`idtipo_utente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8*/
package ModelloDati.ImplementazioneDati;


import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDataLayer.EventoDataLayer;
import ModelloDati.InterfacciaDati.Evento;
import ModelloDati.InterfacciaDati.Invitato;
import ModelloDati.InterfacciaDati.Utente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UtenteImpl implements Utente {
    private int idutente;
    private String nome;
    private String cognome;
    private String bday;
    private String username;
    private String password;
    private String email;
    private String azienda;
    private String ruolo;
    private int idtipo_utente;
    private List<Evento> eventi;
    private List<Invitato> invitati;

    public UtenteImpl(String nome, String cognome, String bday, String username, String password, String email, String azienda, String ruolo, int idtipo_utente) {
        this.idutente = 0;
        this.nome = nome;
        this.cognome = cognome;
        this.bday = bday;
        this.username = username;
        this.password = password;
        this.email = email;
        this.azienda = azienda;
        this.ruolo = ruolo;
        this.idtipo_utente = idtipo_utente;
        this.eventi=null;
        this.invitati=null;
    }
    
    public UtenteImpl(DataLayer dl,ResultSet rs) throws SQLException, ClassNotFoundException
    {
        
        EventoDataLayer edl=dl.getEventoDataLayer(dl.getC());
        this.idutente =rs.getInt("idutente");
        this.nome = rs.getString("nome");
        this.cognome = rs.getString("cognome");
        this.bday = rs.getString("bday");
        this.username = rs.getString("username");
        this.password = rs.getString("password");
        this.email = rs.getString("email");
        this.azienda = rs.getString("azienda");
        this.ruolo = rs.getString("ruolo");
        this.idtipo_utente = rs.getInt("idtipo_utente");
        this.eventi=edl.getListeventi(idutente);
        this.invitati=null;
    }
    
     public UtenteImpl(ResultSet rs) throws SQLException, ClassNotFoundException
    {
        
        
        this.idutente =rs.getInt("idutente");
        this.nome = rs.getString("nome");
        this.cognome = rs.getString("cognome");
        this.bday = rs.getString("bday");
        this.username = rs.getString("username");
        this.password = rs.getString("password");
        this.email = rs.getString("email");
        this.azienda = rs.getString("azienda");
        this.ruolo = rs.getString("ruolo");
        this.idtipo_utente = rs.getInt("idtipo_utente");
        this.eventi=null;
        this.invitati=null;
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
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getCognome() {
        return cognome;
    }

    @Override
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String getBday() {
        return bday;
    }

    @Override
    public void setBday(String bday) {
        this.bday = bday;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getAzienda() {
        return azienda;
    }

    @Override
    public void setAzienda(String azienda) {
        this.azienda = azienda;
    }

    @Override
    public String getRuolo() {
        return ruolo;
    }

    @Override
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public int getIdtipo_utente() {
        return idtipo_utente;
    }

    @Override
    public void setIdtipo_utente(int idtipo_utente) {
        this.idtipo_utente = idtipo_utente;
    }

    @Override
    public List<Evento> getEventi() {
        return eventi;
    }

    @Override
    public void setEventi(List<Evento> eventi) {
        this.eventi = eventi;
    }

    @Override
    public List<Invitato> getInvitati() {
        return invitati;
    }

    @Override
    public void setInvitati(List<Invitato> invitati) {
        this.invitati = invitati;
    }
    
    
}
