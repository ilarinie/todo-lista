/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.servlets.kayttaja;

import java.io.IOException;
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
import static todolist.mallit.naytaJSP.asetaVirhe;
import static todolist.mallit.naytaJSP.naytaJSP;

/**
 *
 * @author ile
 */
public class MuokkaaKayttaja extends HttpServlet {

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
        HttpSession session = request.getSession();
        Kayttaja kirjautunut = (Kayttaja) session.getAttribute("kirjautunut");

        if (kirjautunut != null) {
            int id = 0;
            //Parsetaan id parametreistä
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (Exception e) {
                asetaVirhe("Yrititkö kirjoittaa osoitetta käsin?", request);
                naytaJSP("error.jsp", request, response);
                return;
            }
            //Haetaan tehtävä
            Kayttaja k = Kayttaja.getKayttaja(id);
            //Tarkistetaan että kyseessä on kirjautuneen käyttäjän oma tehtävä
            if (k.getId() == kirjautunut.getId() || kirjautunut.isAdmin()){
                request.setAttribute("kayttaja", k);
                naytaJSP("editkayttaja.jsp", request, response);
                
            }else {
                asetaVirhe("Yritit muokata käyttäjää ilman oikeuksia", request);
                naytaJSP("error.jsp", request, response);
                return;
            }
            
            
            
        } else {
            asetaVirhe("kirjaudu sisään poistaaksesi todon", request);
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
            Logger.getLogger(MuokkaaKayttaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MuokkaaKayttaja.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MuokkaaKayttaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MuokkaaKayttaja.class.getName()).log(Level.SEVERE, null, ex);
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
