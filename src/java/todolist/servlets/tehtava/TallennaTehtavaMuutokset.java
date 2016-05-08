/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.servlets.tehtava;

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
 * Tällä luokalla tallennetaan muokatun tehtävän muutokset
 *
 * @author ile
 */
public class TallennaTehtavaMuutokset extends HttpServlet {

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
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");

        //Haetaan kirjautunut käyttäjä
        HttpSession session = request.getSession();
        Kayttaja kirjautunut = (Kayttaja) session.getAttribute("kirjautunut");

        if (kirjautunut != null) {
            //Parsetaan muokattavan tehtävän id
            int id = 0;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (Exception e) {

            }
            //luodaan uusi tehtävä-olio muokatuilla tiedoilla
            Tehtava muokkaus = new Tehtava();
            muokkaus.setOtsikko(request.getParameter("otsikko"));
            muokkaus.setPrioriteetti(Integer.parseInt(request.getParameter("prioriteetti")));
            muokkaus.setKuvaus(request.getParameter("kuvaus"));
            
            //Tarkistetaan, onko muokatut tiedot kelvollisia ja tallennetaan muutokset kantaan jos näin on
            //Ohjataan tämän jälkeen tehtävän esittelysivulle
            if (muokkaus.onKelvollinen()) {
                Tehtava.update(id, muokkaus);
                response.sendRedirect("tehtava?id=" + id);
            } else {
                //Jos muokatut tiedot eivät ole kelvollisia, ohjataan uudelleen muokkausnäkymään,
                //johon lisätään muokatut tietot ja virheviestit
                request.setAttribute("tehtava", muokkaus);
                request.setAttribute("virheet", muokkaus.getVirheet());
                naytaJSP("edittehtava.jsp", request, response);
            }

        } else {
            //Jos kirjautumista ei ole, pyydetään kirjautumaan sisään
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
        } catch (SQLException ex) {
            Logger.getLogger(TallennaTehtavaMuutokset.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TallennaTehtavaMuutokset.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(TallennaTehtavaMuutokset.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TallennaTehtavaMuutokset.class.getName()).log(Level.SEVERE, null, ex);
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
