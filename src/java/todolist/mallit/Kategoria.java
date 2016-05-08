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
import java.util.Collections;
import java.util.List;
import javax.naming.NamingException;
import static todolist.mallit.Tehtava.addKategoriat;
import todolist.tietokanta.Tietokanta;

/**
 *
 * @author ile
 */
public class Kategoria {

    private String otsikko;
    private int kayttajaId;
    private int id;
    private List<String> virheet = new ArrayList<String>();

    public Kategoria() {
    }

    public Kategoria(String otsikko, int id, int kayttajaId) {
        this.otsikko = otsikko;
        this.id = id;
        this.kayttajaId = kayttajaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKayttajaId() {
        return kayttajaId;
    }

    public void setKayttajaId(int kayttajaId) {
        this.kayttajaId = kayttajaId;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public void setOtsikko(String otsikko) {
        if (otsikko.isEmpty()){
            virheet.add("Otsikko ei voi olla tyhjä");
        }
        this.otsikko = otsikko;
    }
    
    public boolean onKelvollinen(){
        return this.virheet.isEmpty();
    }
    
    public List<String> getVirheet(){
        return this.virheet;
    }

    public static boolean save(Kategoria k, int kayttajaId) throws NamingException, SQLException {
        Connection yhteys = Tietokanta.getYhteys();
        String sql = "INSERT INTO Kategoria (otsikko, kayttaja_id) VALUES (?,?)";
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, k.getOtsikko());
        kysely.setInt(2, kayttajaId);

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
    
    public static boolean update(int id, int muokkaajaId, String otsikko) throws NamingException, SQLException{
        Connection yhteys = Tietokanta.getYhteys();
        String sql = "UPDATE Kategoria SET otsikko=? WHERE id ="+id+";";
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, otsikko);
        
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

    public static Kategoria findOne(int id) throws SQLException, NamingException {
        Connection yhteys = Tietokanta.getYhteys();
        String sql = "SELECT id, otsikko, kayttaja_id FROM Kategoria WHERE id =" + id + ";";
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet rs = kysely.executeQuery();
        Kategoria k = null;
        if (rs.next()) {
            k = new Kategoria(rs.getString("otsikko"), rs.getInt("id"), rs.getInt("kayttaja_id"));
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
        return k;
    }

    public static ArrayList<Kategoria> findAll() throws NamingException, SQLException {

        Connection yhteys = Tietokanta.getYhteys();
        String sql = "SELECT id, otsikko, kayttaja_id FROM Kategoria";
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet rs = kysely.executeQuery();
        ArrayList<Kategoria> kategoriat = new ArrayList<Kategoria>();
        while (rs.next()) {
            //Haetaan tietoa riviltä
            Kategoria k = new Kategoria();
            k.setOtsikko(rs.getString("otsikko"));
            k.setId(rs.getInt("id"));
            k.setKayttajaId(rs.getInt("kayttaja_id"));
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

    public static ArrayList<Kategoria> etsiKayttajanKategoriat(int kayttajaId) throws NamingException, SQLException {

        Connection yhteys = Tietokanta.getYhteys();
        String sql = "SELECT id, otsikko, kayttaja_id FROM Kategoria WHERE kayttaja_id = " + kayttajaId + ";";
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet rs = kysely.executeQuery();
        ArrayList<Kategoria> kategoriat = new ArrayList<Kategoria>();
        while (rs.next()) {
            //Haetaan tietoa riviltä
            Kategoria k = new Kategoria();
            k.setOtsikko(rs.getString("otsikko"));
            k.setId(rs.getInt("id"));
            k.setKayttajaId(rs.getInt("kayttaja_id"));
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

    public static ArrayList<Tehtava> getKategoriaTehtavat(int kategoriaId) throws NamingException, SQLException {
        Connection yhteys = Tietokanta.getYhteys();
        String sql = "SELECT Tehtava.id FROM Tehtava, Tehtavakategoria, Kategoria "
                + "WHERE Tehtava.id = Tehtavakategoria.tehtava_id "
                + "AND Tehtavakategoria.kategoria_id = Kategoria.id "
                + "AND Kategoria.id = " + kategoriaId + ";";

        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet rs = kysely.executeQuery();
        ArrayList<Tehtava> tehtavat = new ArrayList<Tehtava>();

        while (rs.next()) {
            tehtavat.add(Tehtava.find(rs.getInt("id")));
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

        return tehtavat;

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kategoria other = (Kategoria) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    

}
