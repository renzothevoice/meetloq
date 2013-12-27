/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagine;

import ModelloDati.ImplementazioneDati.DataLayerImpl;
import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDataLayer.LuogoDataLayer;
import ModelloDati.InterfacciaDataLayer.Opzione_eventoDataLayer;
import ModelloDati.InterfacciaDati.Luogo;
import ModelloDati.InterfacciaDati.Opzione_evento;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class mod_option extends HttpServlet {

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
        Configuration c = new Configuration();
        c.setDefaultEncoding("ISO-8859-1"); // setta il decoding della pagina in ingresso
        c.setOutputEncoding("ISO-8859-1"); // in output
        c.setNumberFormat("");// per i numeri in virgola mobile e non
        
        c.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER); //fa si che se voi passate al template un oggetto
        //viene visto come un associazione chiave valore da usare nel template
        
        c.setServletContextForTemplateLoading(getServletContext(),"templates"); //vatti a caricare questo template in base al contesto c
        c.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER); //handler di errori (default:statti zitto) cambia se vuoi debuggare
        
        //carica il template(il template non si reinderizza senza modellodati!)
        Template t = c.getTemplate("mod_option.ftl.html");
        
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
            String error = request.getParameter("error");
            
            if(error!=null){
            if(error.equals("1")){
                data.put("errorop", "CI SONO CAMPI VUOTI!!");
            }
            if(error.equals("2")){
                data.put("errorop", "DATA NON DISPONIBILE!!");
            }
            }
            
            Opzione_eventoDataLayer oedl = dl.getOpzione_eventoDataLayer(dl.getC());
            LuogoDataLayer ldl = dl.getLuogoDataLayer(dl.getC());
            List <Luogo> luoghi = new ArrayList();
            luoghi = ldl.readLuoghi();
            String idopzione = request.getParameter("id");
            int idopz = Integer.parseInt(idopzione);
            Opzione_evento opzione = oedl.readOpzione(idopz);
            
            data.put("luoghi",luoghi);
            data.put("datainizioevento",opzione.getDatainizio());
            data.put("datafineevento",opzione.getDatafine());
            data.put("idopzione",idopz);
            
            String idevento = request.getParameter("idevento");
            data.put("idevento",idevento);
        }
        dl.getC().close();
        try {
            t.process(data,response.getWriter());
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
            Logger.getLogger(mod_option.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(mod_option.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(mod_option.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(mod_option.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(mod_option.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(mod_option.class.getName()).log(Level.SEVERE, null, ex);
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
