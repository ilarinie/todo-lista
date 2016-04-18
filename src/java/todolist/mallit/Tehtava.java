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
public class Tehtava {
    
    private int id;
    private String otsikko;
    private String kuvaus;
    private int prioriteetti;
    private Kayttaja kayttaja;
    private ArrayList<Kategoria> kategoriat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public int getPrioriteetti() {
        return prioriteetti;
    }

    public void setPrioriteetti(int prioriteetti) {
        this.prioriteetti = prioriteetti;
    }

    public Kayttaja getKayttaja() {
        return kayttaja;
    }

    public void setKayttaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }

    public ArrayList<Kategoria> getKategoriat() {
        return kategoriat;
    }

    public void setKategoriat(ArrayList<Kategoria> kategoriat) {
        this.kategoriat = kategoriat;
    }
    
    
    public static ArrayList<Tehtava> findAll() throws SQLException, NamingException{
        
        Connection yhteys = Tietokanta.getYhteys();
        String sql = "SELECT id, otsikko, kuvaus, prioriteetti, kayttaja_id from tehtava";
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet rs = kysely.executeQuery();
        ArrayList<Tehtava> tehtavat = new ArrayList<Tehtava>();
        while (rs.next()) {
            //Haetaan tietoa riviltä
            Tehtava t = new Tehtava();
            t.setId(rs.getInt("id"));
            t.setOtsikko(rs.getString("otsikko"));
            t.setKuvaus(rs.getString("kuvaus"));
            t.setPrioriteetti(rs.getInt("prioriteetti"));
            t.setKayttaja(Kayttaja.getKayttaja(rs.getInt("id")));

            tehtavat.add(t);

        }

        return tehtavat;
        
    }
    
}
