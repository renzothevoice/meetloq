/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagine;

import ModelloDati.ImplementazioneDati.DataLayerImpl;
import ModelloDati.InterfacciaDataLayer.AttrezzaturaDataLayer;
import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDataLayer.UtenteDataLayer;
import ModelloDati.InterfacciaDati.Attrezzatura;
import ModelloDati.InterfacciaDati.Utente;
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
public class gestisciattrezzature extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException, TemplateException {
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
        Template t = c.getTemplate("gestisciattrezzature.ftl.html");
        
        Map <String,Object> data = new HashMap <String,Object>();
        
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
            DataLayer dl=new DataLayerImpl();
            UtenteDataLayer utentedl = dl.getUtenteDataLayer(dl.getC());
            Utente utente = utentedl.readUtente(idutente);
            if(idtipo==1){
                data.put("tipo","1");//setta pannello admin
            }
            if(idtipo==2){
                data.put("tipo","2");//setta pannello organizzatore            
                response.sendRedirect("index");
            }
            data.put("message",request.getParameter("msg"));
            AttrezzaturaDataLayer adl = dl.getAttrezaturaDataLayer(dl.getC());
            List <Attrezzatura> attrezzatura = new ArrayList(); 
            attrezzatura = adl.readAttrezzatura();
            data.put("attrezzatura",attrezzatura);
            dl.getC().close();
        }
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(gestisciattrezzature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(gestisciattrezzature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TemplateException ex) {
            Logger.getLogger(gestisciattrezzature.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(gestisciattrezzature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(gestisciattrezzature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TemplateException ex) {
            Logger.getLogger(gestisciattrezzature.class.getName()).log(Level.SEVERE, null, ex);
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
