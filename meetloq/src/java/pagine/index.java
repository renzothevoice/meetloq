/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagine;


import ModelloDati.ImplementazioneDati.DataLayerImpl;
import ModelloDati.ImplementazioneDati.EventoDataLayerImpl;
import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDataLayer.EventoDataLayer;
import ModelloDati.InterfacciaDati.Evento;
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
 * @author Alessio
 */
public class index extends HttpServlet {
    private Object data;

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
        Configuration c = new Configuration();
        c.setDefaultEncoding("ISO-8859-1"); // setta il decoding della pagina in ingresso
        c.setOutputEncoding("ISO-8859-1"); // in output
        c.setNumberFormat("");// per i numeri in virgola mobile e non
        
        c.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER); //fa si che se voi passate al template un oggetto
        //viene visto come un associazione chiave valore da usare nel template
        
        c.setServletContextForTemplateLoading(getServletContext(),"templates"); //vatti a caricare questo template in base al contesto c
        c.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER); //handler di errori (default:statti zitto) cambia se vuoi debuggare
        
        //carica il template(il template non si reinderizza senza modellodati!)
        Template t = c.getTemplate("index.ftl.html");
        
        Map <String,Object> data = new HashMap <String,Object>();
        
        data.put("onHome","on");
        DataLayer dl = new DataLayerImpl();
        EventoDataLayer edl = dl.getEventoDataLayer(dl.getC());
        List <Evento> eventipubblici = new ArrayList();
        eventipubblici=edl.getListeventipubblici();
        data.put("eventipubblici",eventipubblici);
        String jjj=request.getParameter("errorvot");
                if(jjj!=null){
                if(jjj.equals("7")){
                    data.put("messaggiovoto","Non puoi votare !");
                }
                if(jjj.equals("8")){
                    data.put("messaggiovoto","Hai gia votato !");
                }
                if(jjj.equals("9"))
                {
                    data.put("messaggiovoto","Grazie per aver votato!");
                }}
        
        if(request.getSession(false)==null){
            data.put("login",0);
            String check=request.getParameter("par");
            if(check!=null){
                if(check.equals("1")){
                    data.put("errorreg","* Ci sono campi vuoti!!");
                }
                if(check.equals("2")){
                    data.put("errorreg","* Le password immesse non sono uguali!!");
                }
                if(check.equals("3")){
                    data.put("errorreg","* Lo username inserito esiste già!!");
                }
                if(check.equals("5")){
                    data.put("confirm",1);
                    data.put("messaggio","complimenti ti sei registrato con successo su meetloq !");
                }
                if(check.equals("6")){
                    data.put("errorreg","data non valida reinserirla !");
                }
                if(check.equals("4")){
                    data.put("errorlogin","* Username o password errati!");
                }
                
                
                String nome = request.getParameter("nome");
                String cognome = request.getParameter("cognome");
                String bday = request.getParameter("bday");
                String user = request.getParameter("user");
                String pass = request.getParameter("pass");
                String email = request.getParameter("email");
                String azienda = request.getParameter("azienda");
                String ruolo = request.getParameter("ruolo");
                data.put("nome",nome);
                data.put("cognome",cognome);
                data.put("bday",bday);
                data.put("user",user);
                data.put("pass",pass);
                data.put("email",email);
                data.put("azienda",azienda);
                data.put("ruolo",ruolo);
            }
        }
        if(request.getSession(false)!=null){
            data.put("login",1);
            HttpSession s = request.getSession(false);
            String user=(String)s.getAttribute("usersession");
            int idtipo=(Integer)s.getAttribute("idtipo");
            if(idtipo==1){
                data.put("tipo","1");//setta pannello admin
            }
            if(idtipo==2){
                data.put("tipo","2");//setta pannello admin
            }
            data.put("username",user);
        }
        
       //ce.createUtente("nome","cognome",null,"username","password","email","azienda","ruolo",1);
       //Utente user=ce.readUtente(4);
       //if(ce.checkPassword("username","")){prova="true";}else{ prova="false";}
       //data.put("esempio2",prova);
       //Utente user2=ce.readUtente(1);
       //data.put("esempio",user2.getCognome());
        
        try {
            //faccio output
            t.process(data,response.getWriter());
        } catch (TemplateException ex) {
            //catturo l'eccezione
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
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
