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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import javax.naming.NamingException;
import todolist.tietokanta.Tietokanta;

/**
 *
 * @author ile
 */
public class Tehtava implements Comparable<Tehtava> {

    private int id;
    private String otsikko;
    private String kuvaus;
    private int prioriteetti;
    private boolean suoritettu;
    private Kayttaja kayttaja;
    private ArrayList<Kategoria> kategoriat;

    public Tehtava() {
    }

    public boolean isSuoritettu() {
        return suoritettu;
    }

    public void setSuoritettu(boolean suoritettu) {
        this.suoritettu = suoritettu;
    }

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

    public static Tehtava find(int id) throws NamingException, SQLException {
        Connection yhteys = Tietokanta.getYhteys();
        String sql = "SELECT * from tehtava where id =" + id + ";";
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet rs = kysely.executeQuery();
        Tehtava t = null;
        if (rs.next()) {
            t = new Tehtava();
            t.setId(rs.getInt("id"));
            t.setOtsikko(rs.getString("otsikko"));
            t.setKuvaus(rs.getString("kuvaus"));
            t.setPrioriteetti(rs.getInt("prioriteetti"));
            t.setKayttaja(Kayttaja.getKayttaja(rs.getInt("id")));
            t.setSuoritettu(rs.getBoolean("suoritettu"));
            t.setKategoriat(addKategoriat(t.id));

        }
        try {
            rs.close();
        } catch (Exception e) {
        }
        try {
            kysely.close();
        } catch (Exception e) {
        }
        try {
            yhteys.close();
        } catch (Exception e) {
        }
        return t;

    }

    public static ArrayList<Tehtava> findAll() throws SQLException, NamingException {

        Connection yhteys = Tietokanta.getYhteys();
        String sql = "SELECT id, otsikko, kuvaus, prioriteetti, kayttaja_id, suoritettu from tehtava";
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
            t.setKayttaja(Kayttaja.getKayttaja(rs.getInt("kayttaja_id")));
            t.setSuoritettu(rs.getBoolean("suoritettu"));
            t.setKategoriat(addKategoriat(t.id));
            tehtavat.add(t);

        }
        try {
            rs.close();
        } catch (Exception e) {
        }
        try {
            kysely.close();
        } catch (Exception e) {
        }
        try {
            yhteys.close();
        } catch (Exception e) {
        }
        Collections.sort(tehtavat);
        return tehtavat;

    }

    public static int save(String otsikko, String kuvaus, int prioriteetti, int kayttaja_id) throws NamingException, SQLException {
        Connection yhteys = Tietokanta.getYhteys();

        String sql = "INSERT INTO Tehtava (otsikko, kuvaus, prioriteetti,suoritettu, kayttaja_id) VALUES (?,?,?,?,?) RETURNING id";

        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, otsikko);
        kysely.setString(2, kuvaus);
        kysely.setInt(3, prioriteetti);
        kysely.setBoolean(4, false);
        kysely.setInt(5, kayttaja_id);

        ResultSet rs;
        rs = kysely.executeQuery();
        int tid = 0;
        if (rs.next()) {
            tid = rs.getInt(1);
        }

        try {
            rs.close();
        } catch (Exception e) {
        }

        try {
            kysely.close();
        } catch (Exception e) {
        }
        try {
            yhteys.close();
        } catch (Exception e) {
        }

        return tid;

    }

    public static ArrayList<Tehtava> getKayttajanTehtavat(int kayttajaId) throws NamingException, SQLException {
        Connection yhteys = Tietokanta.getYhteys();
        String sql = "SELECT id, otsikko, kuvaus, prioriteetti, kayttaja_id, suoritettu from tehtava where kayttaja_id =" + kayttajaId + ";";
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet rs = kysely.executeQuery();
        ArrayList<Tehtava> tehtavat = new ArrayList<Tehtava>();
        while (rs.next()) {
            Tehtava t = new Tehtava();
            t.setId(rs.getInt("id"));
            t.setOtsikko(rs.getString("otsikko"));
            t.setKuvaus(rs.getString("kuvaus"));
            t.setPrioriteetti(rs.getInt("prioriteetti"));
            t.setKayttaja(Kayttaja.getKayttaja(rs.getInt("kayttaja_id")));
            t.setSuoritettu(rs.getBoolean("suoritettu"));
            t.setKategoriat(addKategoriat(1));
            tehtavat.add(t);
        }
        try {
            rs.close();
        } catch (Exception e) {
        }
        try {
            kysely.close();
        } catch (Exception e) {
        }
        try {
            yhteys.close();
        } catch (Exception e) {
        }
        Collections.sort(tehtavat);
        return tehtavat;
    }

    public static boolean destroy(int id) throws NamingException, SQLException {
        Connection yhteys = Tietokanta.getYhteys();
        String sql = "DELETE FROM Tehtava WHERE id = " + id;
        PreparedStatement kysely = yhteys.prepareStatement(sql);

        boolean palautus = kysely.execute();

        try {
            kysely.close();
        } catch (Exception e) {
        }
        try {
            yhteys.close();
        } catch (Exception e) {
        }

        return palautus;

    }

    public static boolean update(int id) throws NamingException, SQLException {
        Connection yhteys = Tietokanta.getYhteys();
        String sql = "UPDATE Tehtava SET suoritettu=true WHERE id = " + id;
        PreparedStatement kysely = yhteys.prepareStatement(sql);

        boolean palautus = kysely.execute();

        try {
            kysely.close();
        } catch (Exception e) {
        }
        try {
            yhteys.close();
        } catch (Exception e) {
        }

        return palautus;

    }

    public static ArrayList<Kategoria> addKategoriat(int tehtavaId) throws NamingException, SQLException {
        Connection yhteys = Tietokanta.getYhteys();
        String sql = "SELECT Kategoria.otsikko, Kategoria.id FROM Kategoria, Tehtavakategoria, Tehtava "
                + "WHERE Kategoria.id = Tehtavakategoria.kategoria_id "
                + "AND Tehtavakategoria.tehtava_id = Tehtava.id "
                + "AND Tehtava.id = " + tehtavaId + ";";
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet rs = kysely.executeQuery();
        ArrayList<Kategoria> kategoriat = new ArrayList<Kategoria>();
        while (rs.next()) {
            Kategoria k = new Kategoria(rs.getString("otsikko"), rs.getInt("id"));
            kategoriat.add(k);
        }

        try {
            rs.close();
        } catch (Exception e) {
        }
        try {
            kysely.close();
        } catch (Exception e) {
        }
        try {
            yhteys.close();
        } catch (Exception e) {
        }

        return kategoriat;
    }

    public static boolean addKategoria(int tehtavaId, int kategoriaId) throws NamingException, SQLException {
        Connection yhteys = Tietokanta.getYhteys();
        String sql = "INSERT INTO Tehtavakategoria (tehtava_id, kategoria_id) VALUES (?,?)";
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setInt(1, tehtavaId);
        kysely.setInt(2, kategoriaId);

        boolean palautus = kysely.execute();

        try {
            kysely.close();
        } catch (Exception e) {
        }
        try {
            yhteys.close();
        } catch (Exception e) {
        }

        return palautus;
    }

    public int compareTo(Tehtava o) {
        if (this.suoritettu == o.suoritettu) {
            return this.prioriteetti - o.prioriteetti;
        }
        if (this.suoritettu) {
            return 1;
        } else {
            return -1;
        }
    }

}
