/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagine;

import ModelloDati.ImplementazioneDati.DataLayerImpl;
import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDataLayer.Opzione_eventoDataLayer;
import ModelloDati.InterfacciaDati.Opzione_evento;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author R
 */
public class ex_mod_option extends HttpServlet {

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
            throws ServletException, IOException, TemplateException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        Map <String,Object> data = new HashMap <String,Object>();
        
        DataLayer dl=new DataLayerImpl();
        
        if(request.getSession(false)==null){
            response.sendRedirect("index");
        }
        if(request.getSession(false)!=null){
            data.put("login",1);
            HttpSession s = request.getSession(false);
            int idtipo=(Integer)s.getAttribute("idtipo");
            String username=(String)s.getAttribute("usersession");
            int idutente=(Integer)s.getAttribute("idutente");
            data.put("username",username);
            if(idtipo==1){
                data.put("tipo","1");//setta pannello admin
            }
            if(idtipo==2){
                data.put("tipo","2");//setta pannello organizzatore              
            }
            
            String datainizioevento = request.getParameter("datainizioevento");
            String datafineevento = request.getParameter("datafineevento");
            String luogo = request.getParameter("luogo");
            int idluogo = Integer.parseInt(luogo);
            String idopzione = request.getParameter("idopzione");
             String idevento = request.getParameter("idevento");
            int idopz = Integer.parseInt(idopzione);
            String error = new String();
            
            if(datainizioevento.isEmpty() || datafineevento.isEmpty()){
                error = "1";
            }
            else{
            
            
            Opzione_eventoDataLayer oedl = dl.getOpzione_eventoDataLayer(dl.getC());
            Opzione_evento opzione = oedl.readOpzione(idopz);
            opzione.setDatafine(datafineevento);
            opzione.setDatainizio(datainizioevento);
            opzione.setIdluogo(idluogo);
            opzione.setIdopzione(idopz);
            
           
            
            if(oedl.checkOpzioneDisponibile(opzione)){
            oedl.updateOpzione(opzione);
            
            }
            else{error="2";
            }
            
            }
            
            if(error.isEmpty()){
            response.sendRedirect("view_event?succ=1&idevento="+idevento+"&id="+idopzione);
            }else{
                response.sendRedirect("mod_option?error="+error+"&idevento="+idevento+"&id="+idopzione);
            }
        
            
        }
        dl.getC().close();
        

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
        } catch (TemplateException ex) {
            Logger.getLogger(ex_mod_option.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ex_mod_option.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ex_mod_option.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (TemplateException ex) {
            Logger.getLogger(ex_mod_option.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ex_mod_option.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ex_mod_option.class.getName()).log(Level.SEVERE, null, ex);
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
