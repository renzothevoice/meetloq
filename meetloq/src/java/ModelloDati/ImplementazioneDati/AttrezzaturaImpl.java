/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;

import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDati.Attrezzatura;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *CREATE  TABLE IF NOT EXISTS `meetloq`.`attrezzatura` (
  `idattrezzatura` INT(11) NOT NULL AUTO_INCREMENT ,
  `nome_attrezzatura` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`idattrezzatura`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 * @author Alessio
 */
public class AttrezzaturaImpl implements Attrezzatura {
    private int idattrezzatura;
    private String nome_attrezzatura;
    private boolean ispresent;

    public AttrezzaturaImpl(int idattrezzatura, String nome_attrezzatura) {
        this.idattrezzatura = idattrezzatura;
        this.nome_attrezzatura = nome_attrezzatura;
    }
    
    public AttrezzaturaImpl(DataLayer dl, ResultSet rs) throws SQLException {
        this.idattrezzatura = rs.getInt("idattrezzatura");
        this.nome_attrezzatura = rs.getString("nome_attrezzatura");
    }

    @Override
    public int getIdattrezzatura() {
        return idattrezzatura;
    }

    @Override
    public void setIdattrezzatura(int idattrezzatura) {
        this.idattrezzatura = idattrezzatura;
    }

    @Override
    public String getNome_attrezzatura() {
        return nome_attrezzatura;
    }

    @Override
    public void setNome_attrezzatura(String nome_attrezzatura) {
        this.nome_attrezzatura = nome_attrezzatura;
    }

    @Override
    public boolean isIspresent() {
        return ispresent;
    }

    @Override
    public void setIspresent(boolean ispresent) {
        this.ispresent = ispresent;
    }
    
    
}
