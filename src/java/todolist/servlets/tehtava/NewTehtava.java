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
 *
 * @author ile
 */
public class NewTehtava extends HttpServlet {

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
        String otsikko = request.getParameter("otsikko");
        String kuvaus = request.getParameter("kuvaus");
        String prioriteetti = request.getParameter("prioriteetti");
        HttpSession session = request.getSession();
        Kayttaja kirjautunut = (Kayttaja) session.getAttribute("kirjautunut");

        if (kirjautunut != null) {

            if (otsikko == null || kuvaus == null || prioriteetti == null) {
                naytaJSP("newtehtava.jsp", request, response);
                return;
            }

            if (otsikko.equals("")) {
                asetaVirhe("Anna otsikko", request);
                naytaJSP("newtehtava", request, response);
            }
            if (kuvaus.equals("")){
                asetaVirhe("Anna kuvaus", request);
                naytaJSP("newtehtava", request, response);
            }
            
            int tid = Tehtava.save(otsikko, kuvaus, Integer.parseInt(prioriteetti),kirjautunut.getId());
            System.out.println(tid);
            if (tid != 0) {
                response.sendRedirect("tehtava?id="+tid);
            } else {
                response.sendRedirect("newtehtava");
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
            Logger.getLogger(NewTehtava.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewTehtava.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(NewTehtava.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewTehtava.class.getName()).log(Level.SEVERE, null, ex);
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