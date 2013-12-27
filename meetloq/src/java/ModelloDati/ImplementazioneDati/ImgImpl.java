/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.ImplementazioneDati;

import ModelloDati.InterfacciaDati.Img;

/**
 *CREATE  TABLE IF NOT EXISTS `meetloq`.`img` (
  `idimg` INT(11) NOT NULL AUTO_INCREMENT ,
  `url` TEXT NULL DEFAULT NULL ,
  `alt` VARCHAR(45) NULL DEFAULT NULL ,
  `idevento` INT(11) NOT NULL ,
  PRIMARY KEY (`idimg`) ,
  INDEX `fk_img_evento1_idx` (`idevento` ASC) ,
  CONSTRAINT `fk_img_evento1`
    FOREIGN KEY (`idevento` )
    REFERENCES `meetloq`.`evento` (`idevento` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 * @author Alessio
 */
public class ImgImpl implements Img {
    private int idimg;
    private String url;
    private String alt;
    private int idevento;

    public ImgImpl(int idimg, String url, String alt, int idevento) {
        this.idimg = idimg;
        this.url = url;
        this.alt = alt;
        this.idevento = idevento;
    }

    @Override
    public int getIdimg() {
        return idimg;
    }

    @Override
    public void setIdimg(int idimg) {
        this.idimg = idimg;
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
    public String getAlt() {
        return alt;
    }

    @Override
    public void setAlt(String alt) {
        this.alt = alt;
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
