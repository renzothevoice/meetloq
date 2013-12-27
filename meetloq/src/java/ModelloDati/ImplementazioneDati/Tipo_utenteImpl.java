/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;

import ModelloDati.InterfacciaDati.Tipo_utente;

/**
 CREATE  TABLE IF NOT EXISTS `meetloq`.`tipo_utente` (
  `idtipo_utente` INT NOT NULL AUTO_INCREMENT ,
  `tipo` VARCHAR(45) NULL ,
  PRIMARY KEY (`idtipo_utente`) )
ENGINE = InnoDB
 */
public class Tipo_utenteImpl implements Tipo_utente {
    private int idtipo_utente;
    private String tipo;

    public Tipo_utenteImpl(int idtipo_utente, String tipo) {
        this.idtipo_utente = idtipo_utente;
        this.tipo = tipo;
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
    public String getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
}
