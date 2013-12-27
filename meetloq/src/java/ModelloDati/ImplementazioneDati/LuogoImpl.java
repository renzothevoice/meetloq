/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;


import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDati.Attrezzatura;
import ModelloDati.InterfacciaDati.Luogo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *CREATE  TABLE IF NOT EXISTS `meetloq`.`luogo` (
  `idluogo` INT(11) NOT NULL AUTO_INCREMENT ,
  `nome_luogo` VARCHAR(45) NULL DEFAULT NULL ,
  `indirizzo` VARCHAR(45) NULL DEFAULT NULL ,
  `latitudine` VARCHAR(45) NULL DEFAULT NULL ,
  `longitudine` VARCHAR(45) NULL DEFAULT NULL ,
  `tipo_luogo` VARCHAR(45) NULL DEFAULT NULL ,
  `posti` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`idluogo`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 * @author Alessio
 */
public class LuogoImpl implements Luogo {
    private int idluogo;
    private String nome_luogo;
    private String indirizzo;
    private String latitudine;
    private String longitudine;
    private String tipo_luogo;
    private int posti;
    
    private List<Attrezzatura> attrezzature=null;

    public LuogoImpl(int idluogo, String nome_luogo, String indirizzo, String latitudine, String longitudine, String tipo_luogo, int posti) {
        this.idluogo = idluogo;
        this.nome_luogo = nome_luogo;
        this.indirizzo = indirizzo;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.tipo_luogo = tipo_luogo;
        this.posti = posti;
    }
    
   
    public LuogoImpl(DataLayer dl,ResultSet rs) throws SQLException {
        this.idluogo = rs.getInt("idluogo");
        this.nome_luogo = rs.getString("nome_luogo");
        this.indirizzo = rs.getString("indirizzo");
        this.latitudine = rs.getString("latitudine");
        this.longitudine = rs.getString("longitudine");
        this.tipo_luogo = rs.getString("tipo_luogo");
        this.posti = rs.getInt("posti");
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
    public String getNome_luogo() {
        return nome_luogo;
    }

    @Override
    public void setNome_luogo(String nome_luogo) {
        this.nome_luogo = nome_luogo;
    }

    @Override
    public String getIndirizzo() {
        return indirizzo;
    }

    @Override
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Override
    public String getLatitudine() {
        return latitudine;
    }

    @Override
    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }

    @Override
    public String getLongitudine() {
        return longitudine;
    }

    @Override
    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    @Override
    public String getTipo_luogo() {
        return tipo_luogo;
    }

    @Override
    public void setTipo_luogo(String tipo_luogo) {
        this.tipo_luogo = tipo_luogo;
    }

    @Override
    public int getPosti() {
        return posti;
    }

    @Override
    public void setPosti(int posti) {
        this.posti = posti;
    }

    @Override
    public List<Attrezzatura> getAttrezzature() {
        return attrezzature;
    }

    @Override
    public void setAttrezzature(List<Attrezzatura> attrezzature) {
        this.attrezzature = attrezzature;
    }
    
    
}
