/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.servlets.kayttaja;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import todolist.mallit.Kayttaja;
import todolist.mallit.Tehtava;
import static todolist.mallit.naytaJSP.asetaVirhe;
import static todolist.mallit.naytaJSP.naytaJSP;

/**
 *
 * @author ile
 */
public class NewKayttajaServlet extends HttpServlet {

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
        String nimi = request.getParameter("nimi");
        String salasana = request.getParameter("salasana");

        HttpSession session = request.getSession();
        Kayttaja kirjautunut = (Kayttaja) session.getAttribute("kirjautunut");

        if (kirjautunut == null) {

            if (nimi == null || salasana == null) {
                naytaJSP("signup.jsp", request, response);
                return;
            }

            if (nimi == null || nimi.equals("")) {
                asetaVirhe("Anna käyttäjätunnus", request);
                naytaJSP("signup.jsp", request, response);
            }
            if (salasana == null || salasana.equals("")) {
                asetaVirhe("Anna salasana", request);
                naytaJSP("signup.jsp", request, response);
            }

            if (!Kayttaja.save(nimi,salasana)) {
                asetaVirhe("Eikä ollu! Käyttäjä rekisteröity! Kirjaudu nyt sisään.", request);
                naytaJSP("login.jsp", request, response);
            } else {
                asetaVirhe("Käyttäjätunnus jo käytössä :(", request);
                naytaJSP("login.jsp", request, response);
            }
        } else {
            asetaVirhe("Olet jo kirjautunut sisään, silly", request);
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
            Logger.getLogger(NewKayttajaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewKayttajaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(NewKayttajaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewKayttajaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
