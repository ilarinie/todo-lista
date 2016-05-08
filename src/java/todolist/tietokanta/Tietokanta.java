/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.tietokanta;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Tietokantayhteyden luonti tapahtuu tässä luokassa
 * 
 * @author ile
 */
public class Tietokanta {

    public static Connection getYhteys() throws NamingException, SQLException  {

        InitialContext cxt = new InitialContext();
        DataSource yhteysVarasto = (DataSource) cxt.lookup("java:/comp/env/jdbc/niil");
        return yhteysVarasto.getConnection();

    }
    
    
        
    
}
