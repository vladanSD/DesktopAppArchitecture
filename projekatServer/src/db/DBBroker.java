/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Konstante;

/**
 *
 * @author Nikola
 */
public class DBBroker {

    private static DBBroker instance;
    private Connection konekcija;

    public DBBroker() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
    }

    public static DBBroker getInstance() throws Exception {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public void pokreniTransakciju() throws SQLException {
        konekcija.setAutoCommit(false);
    }

    public void potvrdiTransakciju() {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ponistiTransakciju() {
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sacuvajObjekat(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "INSERT INTO " + odo.vratiNazivTabele() + " VALUES " + odo.vratiVrednostiZaInsert(0);
        System.out.println(upit);
        Statement st = konekcija.createStatement();
        st.executeUpdate(upit);

        if (odo.imaSlabeObjekte()) {
            odo.postaviUslov(Konstante.SLABI_OBJEKTI);
            for (int i = 0; i < odo.brojSlabihObjekata(); i++) {
                upit = "INSERT INTO " + odo.vratiNazivTabele() + " VALUES " + odo.vratiVrednostiZaInsert(i);
                st.executeUpdate(upit);
            }
        }
    }

  

    public void izmeniObjekat(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "UPDATE " + odo.vratiNazivTabele() + " SET " + odo.vratiVrednostZaSet() + " WHERE " + odo.vratiVrednostZaWhere();
        System.out.println(upit);
        Statement st = konekcija.createStatement();
        st.executeUpdate(upit);

        if (odo.imaSlabeObjekte()) {
            odo.postaviUslov(Konstante.SLABI_OBJEKTI);
            obrisiObjekat(odo);
            for (int i = 0; i < odo.brojSlabihObjekata(); i++) {
                upit = "INSERT INTO " + odo.vratiNazivTabele() + " VALUES " + odo.vratiVrednostiZaInsert(i);
                st.executeUpdate(upit);
            }
        }
    }

    public void obrisiObjekat(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "DELETE FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiVrednostZaWhere();
        System.out.println(upit);
        Statement st = konekcija.createStatement();
        st.executeUpdate(upit);
    }

    public Object vratiObjekat(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String upit = "SELECT " + odo.vratiVrednostZaSelect() + " FROM " + odo.vratiVrednostZaFrom() + " WHERE " + odo.vratiVrednostZaWhere();
            Statement st = konekcija.createStatement();
            ResultSet rs = st.executeQuery(upit);
            odo = (OpstiDomenskiObjekat) odo.vratiObjekat(rs);

            if (odo.imaSlabeObjekte()) {
                odo.postaviUslov(Konstante.SLABI_OBJEKTI);
                upit = "SELECT " + odo.vratiVrednostZaSelect()
                        + " FROM " + odo.vratiVrednostZaFrom() + " WHERE " + odo.vratiVrednostZaWhere();
                rs = st.executeQuery(upit);
                odo.napuniObjekatSlabimObjektima(rs);
            }
            return odo;
        } catch (Exception e) {
            throw new Exception("Greška u metodi vratiObjekat");
        }
    }

    public List vratiSveObjekte(OpstiDomenskiObjekat odo) throws Exception {
        String upit = "SELECT " + odo.vratiVrednostZaSelect() + " FROM " + odo.vratiVrednostZaFrom() + " ORDER BY " + odo.vratiNazivKolone();
        Statement st = konekcija.createStatement();
        ResultSet rs = st.executeQuery(upit);
        List lista = odo.vratiListu(rs);

        if (odo.imaSlabeObjekte()) {
            for (Object object : lista) {
                odo = (OpstiDomenskiObjekat) object;
                odo.postaviUslov(Konstante.SLABI_OBJEKTI);
                upit = "SELECT " + odo.vratiVrednostZaSelect()
                        + " FROM " + odo.vratiVrednostZaFrom() + " WHERE " + odo.vratiVrednostZaWhere();
                rs = st.executeQuery(upit);
                odo.napuniObjekatSlabimObjektima(rs);
            }
        }

        return lista;
    }

    public List vratiObjektePoUslovu(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String upit = "SELECT " + odo.vratiVrednostZaSelect() + " FROM " + odo.vratiVrednostZaFrom() + " WHERE " + odo.vratiVrednostZaWhere();
            Statement st = konekcija.createStatement();
            ResultSet rs = st.executeQuery(upit);
            List lista = odo.vratiListu(rs);

            if (odo.imaSlabeObjekte()) {
                for (Object object : lista) {
                    odo = (OpstiDomenskiObjekat) object;
                    odo.postaviUslov(Konstante.SLABI_OBJEKTI);
                    upit = "SELECT " + odo.vratiVrednostZaSelect()
                            + " FROM " + odo.vratiVrednostZaFrom() + " WHERE " + odo.vratiVrednostZaWhere();
                    rs = st.executeQuery(upit);
                    odo.napuniObjekatSlabimObjektima(rs);
                }
            }
            return lista;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
//"Greška u metodi vratiObjektePoUslovu"