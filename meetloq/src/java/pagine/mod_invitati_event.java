/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagine;

import ModelloDati.ImplementazioneDati.DataLayerImpl;
import ModelloDati.InterfacciaDataLayer.DataLayer;
import ModelloDati.InterfacciaDataLayer.InvitatoDataLayer;
import ModelloDati.InterfacciaDati.Invitato;
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

/**
 *
 * @author Alessio
 */
public class mod_invitati_event extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            DataLayer dl = new DataLayerImpl();
            
            int size = Integer.parseInt(request.getParameter("sizelista"));
            int idevento=Integer.parseInt(request.getParameter("idevento"));
            
            List<Invitato> invitatilista = new ArrayList();
            
            InvitatoDataLayer idl = dl.getInvitatoDataLayer(dl.getC());
            List <Invitato> tmp=idl.getInvitatievento(idevento);
            
            for (int i = 0; i < size; i++) {
                String check = request.getParameter("checkbox" + i);


                if (check != null) {
                    if (check.equals("1")) {
                        String idinvitato = request.getParameter("idinvitato" + i);
                        int idinv = Integer.parseInt(idinvitato);
                        Invitato invitato = idl.readInvitato(idinv);
                        out.println(invitato.getCognome());
                        invitatilista.add(invitato);
                    }
                    
                }
                else{
                        String idinvitato = request.getParameter("idinvitato" + i);
                        int idinv = Integer.parseInt(idinvitato);  
                        out.println(idinv);
                        idl.removeInvitatoaevento(idinv, idevento);
                    }
            }
            
            
            if(!invitatilista.isEmpty()){
            for(int i=0;i<invitatilista.size();i++)
            {
                boolean flag=false;
                for(int j=0;j<tmp.size();j++)
                {
                    if(invitatilista.get(i).getIdinvitato()==tmp.get(j).getIdinvitato()){flag=true;}                   
                }
                if(!flag){                
                idl.addInvitatoaevento(invitatilista.get(i),idevento);
                }              
               
            }
           
            
            }
            else
            {
            for(int i=0;i<tmp.size();i++)
            {
            idl.removeInvitatoaevento(tmp.get(i).getIdinvitato(), idevento);
            }
            }
            
            
        response.sendRedirect("add_invitato?id="+idevento);

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
        } catch (SQLException ex) {
            Logger.getLogger(mod_invitati_event.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(mod_invitati_event.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(mod_invitati_event.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(mod_invitati_event.class.getName()).log(Level.SEVERE, null, ex);
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
