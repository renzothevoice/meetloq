/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;

import ModelloDati.InterfacciaDati.Invitato;
import ModelloDati.InterfacciaDati.Voto;
import java.util.List;

/**
 *CREATE  TABLE IF NOT EXISTS `meetloq`.`voto` (
  `idvoto` INT(11) NOT NULL AUTO_INCREMENT ,
  `idinvitato` INT(11) NOT NULL ,
  `idopzione` INT(11) NOT NULL ,
  PRIMARY KEY (`idvoto`) ,
  INDEX `fk_voto_invitato1_idx` (`idinvitato` ASC) ,
  INDEX `fk_voto_opzione_evento1_idx` (`idopzione` ASC) ,
  CONSTRAINT `fk_voto_invitato1`
    FOREIGN KEY (`idinvitato` )
    REFERENCES `meetloq`.`invitato` (`idinvitato` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_voto_opzione_evento1`
    FOREIGN KEY (`idopzione` )
    REFERENCES `meetloq`.`opzione_evento` (`idopzione` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 * @author Alessio
 */
public class VotoImpl implements Voto {
    private int idvoto;
    private int idinvitato;
    private int idopzione;
    private List<Invitato> votante;
    

    public VotoImpl(int idvoto, int idinvitato, int idopzione) {
        this.idvoto = idvoto;
        this.idinvitato = idinvitato;
        this.idopzione = idopzione;
    }

    @Override
    public int getIdvoto() {
        return idvoto;
    }

    @Override
    public void setIdvoto(int idvoto) {
        this.idvoto = idvoto;
    }

    @Override
    public int getIdinvitato() {
        return idinvitato;
    }

    @Override
    public void setIdinvitato(int idinvitato) {
        this.idinvitato = idinvitato;
    }

    @Override
    public int getIdopzione() {
        return idopzione;
    }

    @Override
    public void setIdopzione(int idopzione) {
        this.idopzione = idopzione;
    }
    
    
}
