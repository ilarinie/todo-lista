/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.mallit;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ile
 */
public class naytaJSP extends HttpServlet {

    public naytaJSP() {
    }

    //Metodi joka asettaa seuraavan sivun näytettäväksi
    public static void naytaJSP(String sivu, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher dispatcher = request.getRequestDispatcher(sivu);

        dispatcher.forward(request, response);

    }

    //virheet
    public static void asetaVirhe(String virheViesti, HttpServletRequest request) {
        request.setAttribute("virheViesti", virheViesti);
    }

}
