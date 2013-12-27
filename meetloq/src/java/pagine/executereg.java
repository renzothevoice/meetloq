/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagine;


import ModelloDati.ImplementazioneDati.DataLayerImpl;
import ModelloDati.ImplementazioneDati.UtenteDataLayerImpl;
import ModelloDati.ImplementazioneDati.UtenteImpl;
import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDataLayer.UtenteDataLayer;
import ModelloDati.InterfacciaDati.Utente;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;
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
public class executereg extends HttpServlet {
    
    public static boolean isDate(String string) {

        try {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd",Locale.ITALIAN);

        sf.setLenient(false);

        sf.parse(string);

        return true;

        } catch (Exception e) {

        return false;

        }

        }

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
        DataLayer dlp=new DataLayerImpl();
        UtenteDataLayer dl = dlp.getUtenteDataLayer(dlp.getC());
        
        try {
            String nome=request.getParameter("nome");
            String cognome=request.getParameter("cognome");
            String bday=request.getParameter("bday");
            
            String user=request.getParameter("user");
            String pass=request.getParameter("pass");
            String confpass=request.getParameter("confpass");
            String email=request.getParameter("email");
            String azienda=request.getParameter("azienda");
            String ruolo=request.getParameter("ruolo");
            String idtipout = "2"; //gli utenti che si registrano sono organizzatori
            String error = new String();
            
            if(!confpass.equals(pass)){
                error = "2";
            }
            if(!bday.isEmpty()){
                if(!isDate(bday)){
                    error="6";
                }
            }
            if(dl.checkUsername(user)){
                error = "3";
            }
            if(nome.isEmpty() || cognome.isEmpty() || user.isEmpty() || pass.isEmpty() || email.isEmpty()
                    || confpass.isEmpty() || azienda.isEmpty() || ruolo.isEmpty() || bday.isEmpty()){
                error = "1";
            }
                //fine controllo errori
                if(error.isEmpty()){
                    //dl.createUtente(nome,cognome,null,user,pass,email,azienda,ruolo,idtipout);
                    
                    Utente oggutente = new UtenteImpl(nome,cognome,bday,user,pass,email,azienda,ruolo,2);
                    out.println(oggutente.getAzienda());
                    dl.createUtente(oggutente);
                    
                    response.sendRedirect("index?par=5");
                }else{
                    
                    response.sendRedirect("index?par=" + error + "&nome=" + nome + "&cognome=" + cognome + "&bday=" + bday +
                            "&user=" + user + "&pass=" + pass + "&email=" + email + "&azienda=" + azienda + "&ruolo=" + ruolo);
                }
                dlp.getC().close();
            }
            
         finally {            
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
            Logger.getLogger(executereg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(executereg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TemplateException ex) {
            Logger.getLogger(executereg.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(executereg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(executereg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TemplateException ex) {
            Logger.getLogger(executereg.class.getName()).log(Level.SEVERE, null, ex);
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
