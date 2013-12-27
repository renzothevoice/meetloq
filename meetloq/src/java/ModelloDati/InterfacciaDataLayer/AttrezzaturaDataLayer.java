/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloDati.InterfacciaDataLayer;

import ModelloDati.InterfacciaDati.Attrezzatura;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Alessio
 */
public interface AttrezzaturaDataLayer {

    List<Attrezzatura> readAttrezzatura(int idluogo) throws SQLException;

    List<Attrezzatura> readAttrezzatura() throws SQLException;

    void addAttrezzaturabyluogo(int idattrezzatura, int idluogo) throws SQLException;

    void createAttrezzatura(Attrezzatura att) throws SQLException;

    void deleteAttrezzatura(int id) throws SQLException;
    public void modListAtt(List <Attrezzatura> latt,int idluogo) throws SQLException;
     public boolean checkAttLuogo(int idatt, int idluogo) throws SQLException;
      public void deleteAttrezzaturabyluogo(int idluogo) throws SQLException;
}
