/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.mallit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import todolist.tietokanta.Tietokanta;

/**
 *
 * @author ile
 */
public class Kayttaja {


    private int id;
    private String nimi;
    private String salasana;

    public Kayttaja(int id, String tunnus, String salasana) {
        this.id = id;
        this.nimi = tunnus;
        this.salasana = salasana;
    }

    private Kayttaja() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public static ArrayList<Kayttaja> getKayttajat() throws NamingException, SQLException {
        Connection yhteys = Tietokanta.getYhteys();
        String sql = "SELECT id, tunnus, salasana from kayttajat";
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet rs = kysely.executeQuery();
        ArrayList<Kayttaja> kayttajat = new ArrayList<Kayttaja>();
        while (rs.next()) {
            //Haetaan tietoa riviltä
            Kayttaja k = new Kayttaja();
            k.setId(rs.getInt("id"));
            k.setNimi(rs.getString("nimi"));
            k.setSalasana(rs.getString("salasana"));

            kayttajat.add(k);

        }

        return kayttajat;
    }
}
