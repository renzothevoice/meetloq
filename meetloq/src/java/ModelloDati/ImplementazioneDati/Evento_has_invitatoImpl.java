/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;

import ModelloDati.InterfacciaDati.Evento_has_invitato;

/**
 *CREATE  TABLE IF NOT EXISTS `meetloq`.`evento_has_invitato` (
  `idevento` INT(11) NOT NULL ,
  `idinvitato` INT(11) NOT NULL ,
  `key` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`idevento`, `idinvitato`) ,
  INDEX `fk_evento_has_invitato_invitato1_idx` (`idinvitato` ASC) ,
  INDEX `fk_evento_has_invitato_evento1_idx` (`idevento` ASC) ,
  CONSTRAINT `fk_evento_has_invitato_evento1`
    FOREIGN KEY (`idevento` )
    REFERENCES `meetloq`.`evento` (`idevento` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_evento_has_invitato_invitato1`
    FOREIGN KEY (`idinvitato` )
    REFERENCES `meetloq`.`invitato` (`idinvitato` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 * @author Alessio
 */
public class Evento_has_invitatoImpl implements Evento_has_invitato {
    private int idevento;
    private int idinvitato;
    private String key;

    public Evento_has_invitatoImpl(int idevento, int idinvitato, String key) {
        this.idevento = idevento;
        this.idinvitato = idinvitato;
        this.key = key;
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
    public int getIdinvitato() {
        return idinvitato;
    }

    @Override
    public void setIdinvitato(int idinvitato) {
        this.idinvitato = idinvitato;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }
    
    
}
