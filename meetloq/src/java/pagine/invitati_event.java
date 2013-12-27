/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagine;

import ModelloDati.ImplementazioneDati.DataLayerImpl;
import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDataLayer.EventoDataLayer;
import ModelloDati.InterfacciaDataLayer.InvitatoDataLayer;
import ModelloDati.InterfacciaDati.Invitato;
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
public class invitati_event extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, TemplateException, SQLException {
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
        Template t = c.getTemplate("invitati_fine.ftl.html");
        
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
            DataLayer dl = new DataLayerImpl();
            if(idtipo==1){
                data.put("tipo","1");//setta pannello admin
            }
            if(idtipo==2){
                data.put("tipo","2");//setta pannello organizzatore
            }
            String sizelista = request.getParameter("sizelista");
            int size = Integer.parseInt(sizelista);
            List <Invitato> invitatilista = new ArrayList();
            InvitatoDataLayer idl = dl.getInvitatoDataLayer(dl.getC());
            for(int i=0;i<size;i++){
                String check = request.getParameter("checkbox"+i);
                
                
                if(check!=null){
                if(check.equals("1")){
                    String idinvitato = request.getParameter("idinvitato"+i);
                    int idinv = Integer.parseInt(idinvitato);
                    Invitato invitato = idl.readInvitato(idinv);
                    invitatilista.add(invitato);
                   
                }
                        }
}
            int idevento = (Integer)s.getAttribute("idevento");
          
            idl.addInvitatoaevento(invitatilista, idevento);
            
            data.put("messaggio","<p>Congratulazioni il tuo evento è stato creato!!</p>");
            EventoDataLayer edl = dl.getEventoDataLayer(dl.getC());
            int key = edl.readEvento(idevento).getKey();
            
            List <String> inviti=new ArrayList();
            for(int k=0;k<invitatilista.size();k++)
            {
                inviti.add("http://localhost:8084/meetloq2/votazione?votokey="+key+"&idinvitato="+invitatilista.get(k).getIdinvitato()+" - <b>da inviare a:</b>"+invitatilista.get(k).getEmail());
            }
            
            //data.put("link","http://localhost:8084/meetloq2/votazione?votokey="+key+"&idinvitato=");
            data.put("inviti",inviti);
            
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
            Logger.getLogger(invitati_event.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TemplateException ex) {
            Logger.getLogger(invitati_event.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(invitati_event.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(invitati_event.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TemplateException ex) {
            Logger.getLogger(invitati_event.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(invitati_event.class.getName()).log(Level.SEVERE, null, ex);
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
