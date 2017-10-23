/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vladan
 */
public class Usluge implements Serializable, OpstiDomenskiObjekat {
    private int uslugaID;
    private String naziv;
    private String sifraUsluge;
    
    private int uslov;

    public Usluge() {
    }

    public Usluge(int uslugeID, String naziv, String sifraUsluge) {
        this.uslugaID = uslugeID;
        this.naziv = naziv;
        this.sifraUsluge = sifraUsluge;
    }

    public int getUslugeID() {
        return uslugaID;
    }

    public void setUslugeID(int uslugeID) {
        this.uslugaID = uslugeID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSifraUsluge() {
        return sifraUsluge;
    }

    public void setSifraUsluge(String sifraUsluge) {
        this.sifraUsluge = sifraUsluge;
    }

    @Override
    public String toString() {
        return naziv;
    }
    

    @Override
    public String vratiNazivTabele() {
        return "usluge";
    }

    @Override
    public String vratiVrednostiZaInsert(int uslov) {
        return "("+uslugaID+",'"+naziv+"', '"+sifraUsluge+"')";
    }

    @Override
    public String vratiNazivKolone() {
        return "uslugeID";
    }

    @Override
    public String vratiVrednostZaSet() {
    return "";
    }

    @Override
    public String vratiVrednostZaFrom() {
        return "usluge";
    }

    @Override
    public String vratiVrednostZaWhere() {
        return "";
    }

    @Override
    public String vratiVrednostZaSelect() {
        return "uslugeID, naziv, sifraUsluge";
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        if (rs.next()) {
            uslugaID = rs.getInt("uslugeID");
            naziv = rs.getString("naziv");
            sifraUsluge = rs.getString("sifraUsluge");
            return this;
        }
        return null;
    }

    @Override
    public void postaviUslov(int uslov) {
        this.uslov = uslov;
    }

    @Override
    public int vratiUslov() {
        return uslov;
    }

    @Override
    public List vratiListu(ResultSet rs) throws Exception {
        List<Usluge> listaUsluga = new ArrayList<>();
        while (rs.next()) {
            int uslugID = rs.getInt("uslugeID");
            String naz = rs.getString("naziv");
            String sifra = rs.getString("sifraUsluge");
            Usluge usluga = new Usluge(uslugID, naz, sifra);
            listaUsluga.add(usluga);
        }
        return listaUsluga;
    }

    @Override
    public boolean imaSlabeObjekte() {
        return false;
    }

    @Override
    public void napuniObjekatSlabimObjektima(ResultSet tabela) throws Exception {
    
    }

    @Override
    public int brojSlabihObjekata() {
        return 0;
    }
    
    
}
