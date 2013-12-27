/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagine;

import ModelloDati.ImplementazioneDati.DataLayerImpl;
import ModelloDati.ImplementazioneDati.LuogoImpl;
import ModelloDati.InterfacciaDataLayer.AttrezzaturaDataLayer;
import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDataLayer.LuogoDataLayer;
import ModelloDati.InterfacciaDati.Luogo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author R
 */
public class ex_add_luogo extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String nome = request.getParameter("nome");
            String indirizzo = request.getParameter("indirizzo");
            String tipoluogo = request.getParameter("tipoluogo");
            
            int posti=0;
            if(!request.getParameter("posti").isEmpty()){
            posti = Integer.parseInt(request.getParameter("posti"));}
            
            
            int n=Integer.parseInt(request.getParameter("sizelista"));
            out.println(n);
            if(nome.isEmpty() || indirizzo.isEmpty() || tipoluogo.isEmpty() || posti==0){
              
                
                
                
              response.sendRedirect("add_luogo?msg=CI SONO CAMPI VUOTI!!");   
            }else{
               
                
                DataLayer dl = new DataLayerImpl();
                LuogoDataLayer ldl = dl.getLuogoDataLayer(dl.getC());
                AttrezzaturaDataLayer attdl=dl.getAttrezaturaDataLayer(dl.getC());
                Luogo tmp=new LuogoImpl(0,nome,indirizzo,null,null,tipoluogo,posti);
                ldl.createLuogo(tmp);
                for(int i=0;i<n;i++)
                {
                    if(request.getParameter("checkbox" + i)!=null){
                int idattrezzatura = Integer.parseInt(request.getParameter("checkbox" + i));
                attdl.addAttrezzaturabyluogo(idattrezzatura,tmp.getIdluogo());
                    }
                
                }
               response.sendRedirect("add_luogo?msg=IL LUOGO E' STATO AGGIUNTO!!");
            }
            
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ex_add_luogo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ex_add_luogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ex_add_luogo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ex_add_luogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
