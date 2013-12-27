/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//riga kenta C:\\xampp\\htdocs\\meetloq2\\web\\templates\\uploads
package pagine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author R
 */
public class upload_mod extends HttpServlet {

    private FileItem parseRequest(HttpServletRequest request) throws FileUploadException {
        if (ServletFileUpload.isMultipartContent(request)) {
            //Apache FileUpload
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items;
            items = upload.parseRequest(request);
            for (FileItem item : items) {
                String nome = item.getFieldName();
                if (!item.isFormField() && nome.equals("file") && item.getSize() > 0) {
                    return item;
                }
            }
        }
        return null;
    }

    private void action_upload(HttpServletRequest request, HttpServletResponse response, FileItem file_to_upload) throws SQLException, IOException, NamingException, NoSuchAlgorithmException, Exception {


        PreparedStatement s = null;
        int fileID = 0;

        //we want the sha-1 file digest of the uploaded file
        //vogliamo creare il digest sha-1 del file
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        //create a file (with a unique name) and copy the uploaded file to it
        //creiamo un nuovo file (con nome univoco) e copiamoci il file scaricato

        String fileName = file_to_upload.getName();

        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }

        File uploaded_file = File.createTempFile("upload_", "."+extension, new File("C:\\xampp\\htdocs\\meetloq2\\web\\templates\\uploads"));
        String uploadedFile = uploaded_file.getName();
        InputStream is = file_to_upload.getInputStream();
        OutputStream os = new FileOutputStream(uploaded_file);
        byte[] buffer = new byte[1024];
        int read;
        while ((read = is.read(buffer)) > 0) {
            //durante la copia, aggreghiamo i byte del file nel digest sha-1
            //while copying, we aggregate the file bytes in the sha-1 digest
            md.update(buffer, 0, read);
            os.write(buffer, 0, read);
        }
        is.close();
        os.close();

        //get the file digest as a string
        //covertiamo il digest in una stringa
        byte[] digest = md.digest();
        String sdigest = "";
        for (byte b : digest) {
            sdigest += String.valueOf(b);
        }
        
        HttpSession session = request.getSession(false);
        session.setAttribute("nomefile", fileName);
        session.setAttribute("url", uploadedFile);
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
            throws ServletException, IOException, FileUploadException, SQLException, NamingException, NoSuchAlgorithmException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if(request.getSession(false)==null){
            response.sendRedirect("index");
            }
            if(request.getSession(false)!=null){
                String idevento = request.getParameter("id");
                FileItem file_to_upload = parseRequest(request);
                out.println(file_to_upload);
                if (file_to_upload != null) {
                    action_upload(request, response, file_to_upload);
                    response.sendRedirect("mod_event?file=1&idevento="+idevento);
                } else {
                    response.sendRedirect("mod_event?file=0&idevento="+idevento);
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
        } catch (FileUploadException ex) {
            Logger.getLogger(upload_mod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(upload_mod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(upload_mod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(upload_mod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(upload_mod.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (FileUploadException ex) {
            Logger.getLogger(upload_mod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(upload_mod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(upload_mod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(upload_mod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(upload_mod.class.getName()).log(Level.SEVERE, null, ex);
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
