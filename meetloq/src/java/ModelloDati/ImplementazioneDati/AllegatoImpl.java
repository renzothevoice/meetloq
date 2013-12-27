
package ModelloDati.ImplementazioneDati;

import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDati.Allegato;
import java.sql.ResultSet;
import java.sql.SQLException;

/*CREATE  TABLE IF NOT EXISTS `meetloq`.`allegato` (
  `idallegato` INT(11) NOT NULL AUTO_INCREMENT ,
  `url` TEXT NULL DEFAULT NULL ,
  `nome` VARCHAR(45) NULL DEFAULT NULL ,
  `idevento` INT(11) NOT NULL ,
  PRIMARY KEY (`idallegato`) ,
  INDEX `fk_allegato_evento1_idx` (`idevento` ASC) ,
  CONSTRAINT `fk_allegato_evento1`
    FOREIGN KEY (`idevento` )
    REFERENCES `meetloq`.`evento` (`idevento` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8*/
public class AllegatoImpl implements Allegato {
    private int idallegato;
    private String url;
    private String nome;
    private int idevento;

    public AllegatoImpl(int idallegato, String url, String nome, int idevento) {
        this.idallegato = idallegato;
        this.url = url;
        this.nome = nome;
        this.idevento = idevento;
    }
    
    public AllegatoImpl(DataLayer dl, ResultSet rs) throws SQLException
    {
        this.idallegato = rs.getInt("idallegato");
        this.url = rs.getString("url");
        this.nome = rs.getString("nome");
        this.idevento = rs.getInt("idevento");
    }

    @Override
    public int getIdallegato() {
        return idallegato;
    }

    @Override
    public void setIdallegato(int idallegato) {
        this.idallegato = idallegato;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
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
    public int getIdevento() {
        return idevento;
    }

    @Override
    public void setIdevento(int idevento) {
        this.idevento = idevento;
    }
    
    
}
