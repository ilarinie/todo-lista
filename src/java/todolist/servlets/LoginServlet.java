/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.servlets;

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
import todolist.mallit.Kayttaja;
import static todolist.mallit.naytaJSP.asetaVirhe;
import static todolist.mallit.naytaJSP.naytaJSP;

/**
 *
 * @author ile
 */
public class LoginServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String nimi = request.getParameter("nimi");
        String salasana = request.getParameter("salasana");

        if (nimi == null || salasana == null) {
            naytaJSP("login.jsp", request, response);
            return;
        }

        //Tarkistetaan että vaaditut kentät on täytetty:
        if (nimi == null || nimi.equals("")) {
            asetaVirhe("Kirjautuminen epäonnistui! Et antanut käyttäjätunnusta.", request);
            naytaJSP("login.jsp", request, response);
            return;
        }

        /* Välitetään näkymille tieto siitä, mikä tunnus yritti kirjautumista */
        request.setAttribute("kayttaja", nimi);

        if (salasana == null || salasana.equals("")) {
            asetaVirhe("Kirjautuminen epäonnistui! Et antanut salasanaa.", request);
            naytaJSP("login.jsp", request, response);
            return;
        }
        Kayttaja kirjautuja = null;

        try {
            /*Tarkistetaan onko tunnukset oikeat*/
            kirjautuja = Kayttaja.etsiKayttajaTunnuksilla(nimi, salasana);
        } catch (Exception e) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, e);
        }

        if (kirjautuja != null) {
 
            HttpSession session = request.getSession();
      
            session.setAttribute("kirjautunut", kirjautuja);
            response.sendRedirect("index");

        } else {
            asetaVirhe("Käyttäjätunnus tai salasana virheellinen", request);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
