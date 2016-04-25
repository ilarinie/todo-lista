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

    public Kayttaja() {

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
        String sql = "SELECT id, nimi, salasana from Kayttaja";
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
        return kayttajat;
    }

    public static Kayttaja getKayttaja(int id) throws NamingException, SQLException {
        Connection yhteys = Tietokanta.getYhteys();
        String sql = "SELECT id, nimi, salasana from Kayttaja WHERE id='" + id + "';";
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet rs = kysely.executeQuery();

        if (rs.next()) {
            //Haetaan tietoa riviltä
            Kayttaja k = new Kayttaja();
            k.setId(rs.getInt("id"));
            k.setNimi(rs.getString("nimi"));
            k.setSalasana(rs.getString("salasana"));
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
        } else {
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
            return null;
        }

    }
        public static Kayttaja getKayttajaNimella(String nimi) throws NamingException, SQLException {
        Connection yhteys = Tietokanta.getYhteys();
        String sql = "SELECT * from Kayttaja WHERE nimi='" + nimi + "';";
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet rs = kysely.executeQuery();

        if (rs.next()) {
            //Haetaan tietoa riviltä
            Kayttaja k = new Kayttaja();
            k.setId(rs.getInt("id"));
            k.setNimi(rs.getString("nimi"));
            k.setSalasana(rs.getString("salasana"));
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
        } else {
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
            return null;
        }

    }

    public static boolean save(String nimi, String salasana) throws NamingException, SQLException {
        if (getKayttajaNimella(nimi) != null){
            return true;
        }
        
        Connection yhteys = Tietokanta.getYhteys();
        String sql = "INSERT INTO Kayttaja (nimi, salasana) VALUES (?,?)";
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, nimi);
        kysely.setString(2, salasana);
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

    public static Kayttaja etsiKayttajaTunnuksilla(String kayttaja, String salasana) throws NamingException, SQLException {
        String sql = "SELECT id,nimi, salasana from Kayttaja where nimi = ? AND salasana = ?";
        Connection yhteys = Tietokanta.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, kayttaja);
        kysely.setString(2, salasana);
        ResultSet rs = kysely.executeQuery();

        //Alustetaan muuttuja, joka sisältää löydetyn käyttäjän
        Kayttaja kirjautunut = null;

        //next-metodia on kutsuttava aina, kun käsitellään 
        //vasta kannasta saatuja ResultSet-olioita.
        //ResultSet on oletuksena ensimmäistä edeltävällä -1:llä rivillä.
        //Kun sitä kutsuu ensimmäisen kerran siirtyy se ensimmäiselle riville 0.
        //Samalla metodi myös palauttaa tiedon siitä onko seuraavaa riviä olemassa.
        if (rs.next()) {
            //Kutsutaan sopivat tiedot vastaanottavaa konstruktoria 
            //ja asetetaan palautettava olio:
            kirjautunut = new Kayttaja();
            kirjautunut.setId(rs.getInt("id"));
            kirjautunut.setNimi(rs.getString("nimi"));
            kirjautunut.setSalasana(rs.getString("salasana"));
        }

        //Jos kysely ei tuottanut tuloksia käyttäjä on nyt vielä null.
        //Suljetaan kaikki resurssit:
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

        //Käyttäjä palautetaan vasta täällä, kun resurssit on suljettu onnistuneesti.
        return kirjautunut;
    }
}
