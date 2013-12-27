/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagine;

import ModelloDati.ImplementazioneDati.AllegatoImpl;
import ModelloDati.ImplementazioneDati.DataLayerImpl;
import ModelloDati.ImplementazioneDati.EventoImpl;
import ModelloDati.ImplementazioneDati.Opzione_eventoImpl;
import ModelloDati.InterfacciaDataLayer.AllegatoDataLayer;
import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDataLayer.EventoDataLayer;
import ModelloDati.InterfacciaDataLayer.Opzione_eventoDataLayer;
import ModelloDati.InterfacciaDati.Allegato;
import ModelloDati.InterfacciaDati.Evento;
import ModelloDati.InterfacciaDati.Opzione_evento;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * @author R
 */
public class ex_create_event extends HttpServlet {

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
     try{
        if(request.getSession(false)==null){
            response.sendRedirect("index");
        }else{
            HttpSession s = request.getSession(false);
            int idutente = (Integer)s.getAttribute("idutente");
           
            DataLayer dl = new DataLayerImpl();
            String error=new String();
            
            //descrizione evento
            String titolo = request.getParameter("titolo");
            String descrizione = request.getParameter("descrizione");
            String datainiziovoto = request.getParameter("datainiziovoto");
            String datafinevoto = request.getParameter("datafinevoto");
            String pubblico = request.getParameter("pubblico");
            boolean pub = Boolean.parseBoolean(pubblico);
            
            //controllo tabella evento
            Opzione_eventoDataLayer oedl = dl.getOpzione_eventoDataLayer(dl.getC());
            EventoDataLayer edl = dl.getEventoDataLayer(dl.getC());
            
            if(!edl.isValidDate(datainiziovoto) || !edl.isValidDate(datafinevoto)){
                error="2";                
            }
            if(titolo.isEmpty() || descrizione.isEmpty() || datainiziovoto.isEmpty() || datafinevoto.isEmpty()){
                error="1";
            }
            
            
            List <Opzione_evento> listaoe = new ArrayList();
            
            
            Evento evento = new EventoImpl(0,titolo,descrizione,datainiziovoto,datafinevoto,idutente,false,pub);
            edl.createEvento(evento);
            Allegato al=new AllegatoImpl(0,(String)s.getAttribute("nomefile"),(String)s.getAttribute("url"),evento.getIdevento());
            AllegatoDataLayer adl=dl.getAllegatoDataLayer(dl.getC());
            adl.createAllegato(al);
            //controllo opzioni
            
            for(int i=1; i<6; i++){
                    String datainizioevento = request.getParameter("datainizioevento"+i);

                    String datafineevento = request.getParameter("datafineevento"+i);
                    
                    String luogo = request.getParameter("luogo"+i);
                    
                    int location = Integer.parseInt(luogo);
                    
                    String indirizzo = request.getParameter("indirizzo"+i);
                    
                    if(!(datainizioevento.isEmpty()) && !(datafineevento.isEmpty())){ //se i dati non sono vuoti li valuto
                        
                        if(!oedl.isValidDate(datainizioevento) || !oedl.isValidDate(datafineevento)){
                            error="3";
                            
                        }else{
                                
                                Opzione_evento oe = new Opzione_eventoImpl(0,datainizioevento,datafineevento,evento.getIdevento(),location,0,false); //getIdevento ancora non lo sa senza query
                                listaoe.add(oe);
                                
                            }
                        }else{
                        if(i==1){
                        error="1"; // se i campi della prima opzione sono vuoti errore
                    }
                }
            }
            
            int j=0;
            boolean flag=true;
            while(j<listaoe.size() && flag)
            {

                Opzione_evento pp=listaoe.get(j);
                
                flag=oedl.checkOpzioneDisponibile(pp);
                
                out.println(oedl.checkOpzioneDisponibile(pp)); /////////////////////
                j++;

            }

            if(!flag){
                error="4";
            }
            //se gli errori ci sono rimanada a create_event
            if(!error.isEmpty()){
                edl.deleteEvento(evento.getIdevento());
                response.sendRedirect("create_event?error="+error);
            }else{
            
                //aggiungo l'evento 
                
                //aggiungo le opzioni
                for(int i=0;i<listaoe.size();i++){
                    out.println(listaoe.get(i).getDatainizio());
                    out.println(listaoe.get(i).getDatafine());
                    out.println(listaoe.get(i).getIdevento());
                    out.println(listaoe.get(i).getIdluogo());
                    oedl.createOpzione(listaoe.get(i)); //non la fa
                    
                }
                dl.getC().close();
                s.setAttribute("idevento",evento.getIdevento());
                response.sendRedirect("add_invitati_event");
            }
            dl.getC().close();
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
            Logger.getLogger(ex_create_event.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ex_create_event.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ex_create_event.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ex_create_event.class.getName()).log(Level.SEVERE, null, ex);
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
