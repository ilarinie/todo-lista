/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.servlets.tehtava;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import todolist.mallit.Kategoria;
import todolist.mallit.Kayttaja;
import todolist.mallit.Tehtava;
import static todolist.mallit.naytaJSP.asetaVirhe;
import static todolist.mallit.naytaJSP.naytaJSP;

/**
 * Servlet tehtävän esittelyyn
 *
 * @author ile
 */
public class ShowTehtava extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String idParam = request.getParameter("id");
        int id;
        
        //haetaan kirjautunut käyttäjä
        HttpSession session = request.getSession();
        Kayttaja kirjautunut = (Kayttaja) session.getAttribute("kirjautunut");

        if (kirjautunut != null) {
            
            try {
                id = Integer.parseInt(idParam);
            } catch (Exception e) {
                id = 0;
            }
            //etsitään tehtävä parametrinä saadulla id:llä
            Tehtava tehtava = Tehtava.find(id);
            if (tehtava != null) {
                //haetaan käyttäjän kategoriat lisäystä varten
                ArrayList<Kategoria> lisattavat = Kategoria.etsiKayttajanKategoriat(kirjautunut.getId());
                //poistetaan niistä tehtävässä jo olevat kategoriat
                lisattavat.removeAll(tehtava.getKategoriat());
                request.setAttribute("lisattavat", lisattavat);
                request.setAttribute("tehtava", tehtava);
                naytaJSP("tehtava.jsp", request, response);
            } else {
                request.setAttribute("tehtava", null);
                asetaVirhe("Tehtävää ei löytynyt", request);
                naytaJSP("tehtava.jsp", request, response);
            }
        } else {
            asetaVirhe("Kirjaudu ensin sisään", request);
            naytaJSP("login.jsp", request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
        } catch (NamingException ex) {
            Logger.getLogger(ShowTehtava.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShowTehtava.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
        } catch (NamingException ex) {
            Logger.getLogger(ShowTehtava.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShowTehtava.class.getName()).log(Level.SEVERE, null, ex);
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
