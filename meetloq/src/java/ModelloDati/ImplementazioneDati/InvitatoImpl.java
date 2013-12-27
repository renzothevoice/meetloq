/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;


import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDati.Invitato;
import ModelloDati.InterfacciaDati.Utente;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *CREATE  TABLE IF NOT EXISTS `meetloq`.`invitato` (
  `idinvitato` INT(11) NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(45) NULL DEFAULT NULL ,
  `nome` VARCHAR(45) NULL DEFAULT NULL ,
  `cognome` VARCHAR(45) NULL DEFAULT NULL ,
  `idutente` INT(11) NOT NULL ,
  PRIMARY KEY (`idinvitato`) ,
  INDEX `fk_invitato_utente1_idx` (`idutente` ASC) ,
  CONSTRAINT `fk_invitato_utente1`
    FOREIGN KEY (`idutente` )
    REFERENCES `meetloq`.`utente` (`idutente` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 * @author Alessio
 */
public class InvitatoImpl implements Invitato {
    private int idinvitato;
    private String email;
    private String nome;
    private String cognome;
    private int idutente;
    private boolean isinvitatoallevento=false;
    
    private Utente organizzatore;

    public InvitatoImpl(int idinvitato, String email, String nome, String cognome, int idutente) {
        this.idinvitato = idinvitato;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.idutente = idutente;
    }
    
    public InvitatoImpl(DataLayer dl,ResultSet rs) throws SQLException {
        this.idinvitato = rs.getInt("idinvitato");
        this.email = rs.getString("email");
        this.nome = rs.getString("nome");
        this.cognome = rs.getString("cognome");
        this.idutente = rs.getInt("idutente");
    }
    
    public InvitatoImpl(DataLayer dl,ResultSet rs,int idevento) throws SQLException {
        this.idinvitato = rs.getInt("idinvitato");
        this.email = rs.getString("email");
        this.nome = rs.getString("nome");
        this.cognome = rs.getString("cognome");
        this.idutente = rs.getInt("idutente");
        if(rs.getInt("idevento")==idevento) this.isinvitatoallevento=true; //verifica se l'invitato nella rubrica è invitato all'evento
        
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
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
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
    public int getIdutente() {
        return idutente;
    }

    @Override
    public void setIdutente(int idutente) {
        this.idutente = idutente;
    }

    @Override
    public boolean isIsinvitatoallevento() {
        return isinvitatoallevento;
    }

    @Override
    public void setIsinvitatoallevento(boolean isinvitatoallevento) {
        this.isinvitatoallevento = isinvitatoallevento;
    }
    
    
}
