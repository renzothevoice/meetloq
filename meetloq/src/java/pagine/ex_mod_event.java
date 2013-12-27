/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagine;

import ModelloDati.ImplementazioneDati.AllegatoImpl;
import ModelloDati.ImplementazioneDati.DataLayerImpl;
import ModelloDati.InterfacciaDataLayer.AllegatoDataLayer;
import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDataLayer.EventoDataLayer;
import ModelloDati.InterfacciaDati.Allegato;
import ModelloDati.InterfacciaDati.Evento;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class ex_mod_event extends HttpServlet {

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
        
        if(request.getSession(false)==null){
            response.sendRedirect("index");
        }
        if(request.getSession(false)!=null){
            HttpSession s = request.getSession(false);
            int idtipo=(Integer)s.getAttribute("idtipo");
            String username=(String)s.getAttribute("usersession");
            int idutente=(Integer)s.getAttribute("idutente");
            
            String titolo = request.getParameter("titolo");
            String descrizione = request.getParameter("descrizione");
            String datainiziovoto = request.getParameter("datainiziovoto");
            String datafinevoto = request.getParameter("datafinevoto");
            String pubblico = request.getParameter("pubblico");
            boolean pub = Boolean.parseBoolean(pubblico);
            String idevento = request.getParameter("id");
            int ideven = Integer.parseInt(idevento);
            
            if(!titolo.isEmpty() && !descrizione.isEmpty() && !datainiziovoto.isEmpty() && !datafinevoto.isEmpty()){
                DataLayer dl = new DataLayerImpl();

                EventoDataLayer edl = dl.getEventoDataLayer(dl.getC());
                Evento evento = edl.readEvento(ideven);

                evento.setDescrizione(descrizione);
                evento.setTitolo(titolo);
                evento.setIniziovoto(datainiziovoto);
                evento.setFinevoto(datafinevoto);
                evento.setPubblico(pub);

                edl.updateEvento(evento);

                AllegatoDataLayer adl = dl.getAllegatoDataLayer(dl.getC());
                Allegato allegato = adl.readAllegato(ideven);

                HttpSession session = request.getSession(false);
                out.println((String)session.getAttribute("nomefile"));
                if(allegato!=null){
                allegato.setNome((String)session.getAttribute("nomefile"));
                allegato.setUrl((String)session.getAttribute("url"));
                adl.updateAllegato(allegato);
                }
                else{
                Allegato al= new AllegatoImpl(0,(String)session.getAttribute("url"),(String)session.getAttribute("nomefile"),ideven);
                adl.updateAllegato(al);
                dl.getC().close();                
                
            }
                response.sendRedirect("mod_event?success=1&idevento="+idevento);
            }
            
            else{
                response.sendRedirect("mod_event?success=0&idevento="+idevento);
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
            Logger.getLogger(ex_mod_event.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ex_mod_event.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ex_mod_event.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ex_mod_event.class.getName()).log(Level.SEVERE, null, ex);
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
