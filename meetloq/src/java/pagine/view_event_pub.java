/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagine;

import ModelloDati.ImplementazioneDati.DataLayerImpl;
import ModelloDati.InterfacciaDataLayer.AllegatoDataLayer;
import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDataLayer.EventoDataLayer;
import ModelloDati.InterfacciaDataLayer.InvitatoDataLayer;
import ModelloDati.InterfacciaDataLayer.LuogoDataLayer;
import ModelloDati.InterfacciaDataLayer.Opzione_eventoDataLayer;
import ModelloDati.InterfacciaDataLayer.UtenteDataLayer;
import ModelloDati.InterfacciaDati.Allegato;
import ModelloDati.InterfacciaDati.Evento;
import ModelloDati.InterfacciaDati.Invitato;
import ModelloDati.InterfacciaDati.Luogo;
import ModelloDati.InterfacciaDati.Opzione_evento;
import ModelloDati.InterfacciaDati.Utente;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class view_event_pub extends HttpServlet {

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
        Template t = c.getTemplate("view_event_pub.ftl.html");
        DataLayer dl = new DataLayerImpl(); 
            EventoDataLayer edl = dl.getEventoDataLayer(dl.getC());
        
        Map <String,Object> data = new HashMap <String,Object>();
        String idevento = request.getParameter("id");
        if(idevento!=null){
            int idev = Integer.parseInt(idevento); 
            
                Evento even = edl.readEvento(idev);                
                LuogoDataLayer ldl = dl.getLuogoDataLayer(dl.getC());
                Opzione_eventoDataLayer oedl = dl.getOpzione_eventoDataLayer(dl.getC());
                
                int idopzuff=even.getIdopzioneufficiale();
                AllegatoDataLayer adl = dl.getAllegatoDataLayer(dl.getC());               
                List <Opzione_evento> opzionievento = oedl.getListopzioni(idev);   
                for(int i=0;i<opzionievento.size();i++)
                {
                    int tmp=opzionievento.get(i).getIdluogo();
                    Luogo ltmp=ldl.readLuogobyid(tmp);
                    opzionievento.get(i).setLuogoevento(ltmp);
                    
                } 
                
                InvitatoDataLayer idl=dl.getInvitatoDataLayer(dl.getC());
               
                List <Invitato> invitatievento =idl.getInvitatievento(idev);
                data.put("titolo",even.getTitolo());
                data.put("descrizione",even.getDescrizione());
                data.put("iniziovoto",even.getIniziovoto());
                data.put("finevoto",even.getFinevoto());
                Allegato allegato = adl.readAllegato(idev);
                String urlallegato = new String();
                urlallegato = "http://localhost:8084/meetloq2/templates/uploads/"+allegato.getNome();
                data.put("linkallegato",urlallegato);
                data.put("nomeallegato",allegato.getNome());
                data.put("opzionievento",opzionievento);
                data.put("invitatievento",invitatievento);
                
                
                data.put("eventodefinito",even.isEventodefinito());
                
                
                
                data.put("luogo","");
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
        } catch (TemplateException ex) {
            Logger.getLogger(view_event_pub.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(view_event_pub.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(view_event_pub.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(view_event_pub.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(view_event_pub.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(view_event_pub.class.getName()).log(Level.SEVERE, null, ex);
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
