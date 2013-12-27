/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagine;

import ModelloDati.ImplementazioneDati.DataLayerImpl;
import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDataLayer.EventoDataLayer;
import ModelloDati.InterfacciaDataLayer.InvitatoDataLayer;
import ModelloDati.InterfacciaDataLayer.LuogoDataLayer;
import ModelloDati.InterfacciaDataLayer.Opzione_eventoDataLayer;
import ModelloDati.InterfacciaDati.Evento;
import ModelloDati.InterfacciaDati.Opzione_evento;
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
public class votazione extends HttpServlet {

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
        Template t = c.getTemplate("votazione.ftl.html");
        DataLayer dl = new DataLayerImpl();
        InvitatoDataLayer idl=dl.getInvitatoDataLayer(dl.getC());
        EventoDataLayer edl = dl.getEventoDataLayer(dl.getC());
        Opzione_eventoDataLayer oedl = dl.getOpzione_eventoDataLayer(dl.getC());
        
        Map <String,Object> data = new HashMap <String,Object>();
        try {
            String votokey = request.getParameter("votokey");
            String idinvitato = request.getParameter("idinvitato");
            int idinv = Integer.parseInt(idinvitato);
            data.put("idinvitato",idinvitato);
            String error=new String();
            int keydaver = Integer.parseInt(votokey);
            Evento evento = edl.readEventobykey(keydaver);
            data.put("idevento",evento.getIdevento());
            if(!idl.checkInvitatotoevento(idinv,evento.getIdevento()))
            {
                //out.println("NON PUOI VOTARE!");
                error="7";
            }
            else{
            
            if(!oedl.checkVotazione(evento.getIdevento(), idinv))
            {
                out.println("hai gia votato");
                error="8";
            }
            else{    
            if(votokey!=null && idinvitato!=null){             
                                
                LuogoDataLayer ldl = dl.getLuogoDataLayer(dl.getC());
                if(evento.getKey()==keydaver){
                    List <Opzione_evento> opzioni = oedl.getListopzioni(evento.getIdevento());
                    for(int i=0;i<opzioni.size();i++){
                        Opzione_evento temp= opzioni.get(i);
                        temp.setLuogoevento(ldl.readLuogobyid(opzioni.get(i).getIdluogo()));
                    }
                    if(!opzioni.isEmpty()){
                        if(opzioni.size()!=0){
                            data.put("opzioni",opzioni);
                        }else{
                            data.put("message","NON CI SONO OPZIONI PER QUESTO EVENTO!");
                        }
                    }
                    t.process(data,response.getWriter());
                    dl.getC().close();
                }
                else{
                    dl.getC().close();
                    response.sendRedirect("index");
                }
            }else{
                response.sendRedirect("index");
            }
            }
            }
            response.sendRedirect("index?errorvot="+error);
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
            Logger.getLogger(votazione.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(votazione.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(votazione.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(votazione.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(votazione.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(votazione.class.getName()).log(Level.SEVERE, null, ex);
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
