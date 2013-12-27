/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;

import ModelloDati.InterfacciaDati.Attrezzatura_has_luogo;

/**
 *CREATE  TABLE IF NOT EXISTS `meetloq`.`attrezzatura_has_luogo` (
  `idattrezzatura` INT(11) NOT NULL AUTO_INCREMENT ,
  `idluogo` INT(11) NOT NULL ,
  `quantita` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`idattrezzatura`, `idluogo`) ,
  INDEX `fk_attrezzatura_has_luogo_luogo1_idx` (`idluogo` ASC) ,
  INDEX `fk_attrezzatura_has_luogo_attrezzatura1_idx` (`idattrezzatura` ASC) ,
  CONSTRAINT `fk_attrezzatura_has_luogo_attrezzatura1`
    FOREIGN KEY (`idattrezzatura` )
    REFERENCES `meetloq`.`attrezzatura` (`idattrezzatura` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_attrezzatura_has_luogo_luogo1`
    FOREIGN KEY (`idluogo` )
    REFERENCES `meetloq`.`luogo` (`idluogo` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 * @author Alessio
 */
public class Attrezzatura_has_luogoImpl implements Attrezzatura_has_luogo {
    private int idattrezzatura;
    private int idluogo;
    private int quantita;

    public Attrezzatura_has_luogoImpl(int idattrezzatura, int idluogo, int quantita) {
        this.idattrezzatura = idattrezzatura;
        this.idluogo = idluogo;
        this.quantita = quantita;
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
    public int getIdluogo() {
        return idluogo;
    }

    @Override
    public void setIdluogo(int idluogo) {
        this.idluogo = idluogo;
    }

    @Override
    public int getQuantita() {
        return quantita;
    }

    @Override
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
    
    
}
