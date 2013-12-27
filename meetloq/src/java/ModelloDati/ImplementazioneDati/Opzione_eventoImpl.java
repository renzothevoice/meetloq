/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;


import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDati.Luogo;
import ModelloDati.InterfacciaDati.Opzione_evento;
import ModelloDati.InterfacciaDati.Voto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *CREATE  TABLE IF NOT EXISTS `meetloq`.`opzione_evento` (
  `idopzione` INT(11) NOT NULL AUTO_INCREMENT ,
  `datainizio` VARCHAR(45) NULL DEFAULT NULL ,
  `datafine` VARCHAR(45) NULL DEFAULT NULL ,
  `idevento` INT(11) NOT NULL ,
  `idluogo` INT(11) NOT NULL ,
  `contatore` INT(11) NULL DEFAULT NULL ,
  `disponibile` TINYINT(1) NULL DEFAULT NULL ,
  `ufficiale` TINYINT(1) NULL ,
  PRIMARY KEY (`idopzione`) ,
  INDEX `fk_opzione_evento_evento1_idx` (`idevento` ASC) ,
  INDEX `fk_opzione_evento_luogo1_idx` (`idluogo` ASC) ,
  CONSTRAINT `fk_opzione_evento_evento1`
    FOREIGN KEY (`idevento` )
    REFERENCES `meetloq`.`evento` (`idevento` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_opzione_evento_luogo1`
    FOREIGN KEY (`idluogo` )
    REFERENCES `meetloq`.`luogo` (`idluogo` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 * @author Alessio
 */
public class Opzione_eventoImpl implements Opzione_evento {
    private int idopzione;
    private String datainizio;
    private String datafine;
    private int idevento;
    private int idluogo;
    private int contatore;
    private boolean disponibile;
  
    
    private Luogo luogoevento;
    private List <Voto> votievento;
    
    

    public Opzione_eventoImpl(int idopzione, String datainizio, String datafine, int idevento, int idluogo, int contatore, boolean disponibile) {
        this.idopzione = idopzione;
        this.datainizio = datainizio;
        this.datafine = datafine;
        this.idevento = idevento;
        this.idluogo = idluogo;
        this.contatore = contatore;
        this.disponibile = disponibile;
              
    }

    public Opzione_eventoImpl(DataLayer dl,ResultSet rs) throws SQLException {
        
        this.idopzione = rs.getInt("idopzione");
        this.datainizio = rs.getString("datainizio");
        this.datafine = rs.getString("datafine");
        this.idevento = rs.getInt("idevento");
        this.idluogo = rs.getInt("idluogo");
        this.contatore = rs.getInt("contatore");
        this.disponibile = rs.getBoolean("disponibile");        
        this.luogoevento=null;
        this.votievento=null;
    }
    
    
   

    @Override
    public int getIdopzione() {
        return idopzione;
    }

    @Override
    public void setIdopzione(int idopzione) {
        this.idopzione = idopzione;
    }

    @Override
    public String getDatainizio() {
        return datainizio;
    }

    @Override
    public void setDatainizio(String datainizio) {
        this.datainizio = datainizio;
    }

    @Override
    public String getDatafine() {
        return datafine;
    }

    @Override
    public void setDatafine(String datafine) {
        this.datafine = datafine;
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
    public int getIdluogo() {
        return idluogo;
    }

    @Override
    public void setIdluogo(int idluogo) {
        this.idluogo = idluogo;
    }

    @Override
    public int getContatore() {
        return contatore;
    }

    @Override
    public void setContatore(int contatore) {
        this.contatore = contatore;
    }

    @Override
    public boolean isDisponibile() {
        return disponibile;
    }

    @Override
    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

  

    @Override
    public Luogo getLuogoevento() {
        return luogoevento;
    }

    @Override
    public void setLuogoevento(Luogo luogoevento) {
        this.luogoevento = luogoevento;
    }

    @Override
    public List<Voto> getVotievento() {
        return votievento;
    }

    @Override
    public void setVotievento(List<Voto> votievento) {
        this.votievento = votievento;
    }
    
    
}
