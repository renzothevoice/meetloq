/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagine;

import ModelloDati.ImplementazioneDati.DataLayerImpl;
import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDataLayer.EventoDataLayer;
import ModelloDati.InterfacciaDataLayer.Opzione_eventoDataLayer;
import ModelloDati.InterfacciaDati.Evento;
import ModelloDati.InterfacciaDati.Opzione_evento;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
public class ex_ufficializza extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
             if(request.getSession(false)==null){
            response.sendRedirect("index");
        }else{
            HttpSession s = request.getSession(false);
            int idutente = (Integer)s.getAttribute("idutente");          
            String msg;
            int idopzione=Integer.parseInt(request.getParameter("idopzione"));
            int idevento=Integer.parseInt(request.getParameter("idevento"));
            
            DataLayer dl=new DataLayerImpl();
            EventoDataLayer edl=dl.getEventoDataLayer(dl.getC());
            Opzione_eventoDataLayer oedl=dl.getOpzione_eventoDataLayer(dl.getC());
            
            Opzione_evento oe=oedl.readOpzione(idopzione);//opzione da ufficializzare
            if(oedl.checkOpzioneDisponibile(oe))
            {
                Evento etmp= edl.readEvento(idevento);
                etmp.setIdopzioneufficiale(idopzione);
                etmp.setEventodefinito(true);
                edl.updateEvento(etmp);
                
               List<Opzione_evento> tmp=oedl.getAllopz(oe.getIdluogo());
               for(int i=0;i<tmp.size();i++)
               {
                   //rimuovi le opzioni conflittuanti
                   if(!oedl.checkConflitti(oe,tmp.get(i))){
                   oedl.deleteOpzione(tmp.get(i).getIdopzione());}
               }
               oedl.createOpzione(oe);
               out.println(oe.getIdopzione());
               etmp.setIdopzioneufficiale(oe.getIdopzione());
               etmp.setEventodefinito(true);
               edl.updateEvento(etmp);
               msg="Opzione ufficializzata con successo!";
            response.sendRedirect("view_event?idevento="+idevento+"&msg="+msg);
            }
            else{
            msg="l'opzione non puo' essere ufficializzata!";
            response.sendRedirect("view_event?idevento="+idevento+"&msg="+msg);
            }
            
            
             
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
            Logger.getLogger(ex_ufficializza.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ex_ufficializza.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ex_ufficializza.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ex_ufficializza.class.getName()).log(Level.SEVERE, null, ex);
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
