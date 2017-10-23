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
import konstante.Konstante;

/**
 *
 * @author Vladan
 */
public class Zaposleni implements Serializable, OpstiDomenskiObjekat{
    private int zaposleniRB;
    private String imeIPrezime;
    private String strucnaSprema;
    private int radniStaz;
    
    private int uslov;

    public Zaposleni() {
    }

    public Zaposleni(int zaposleniID, String imeIPrezime, String strucnaSprema, int radniStaz) {
        this.zaposleniRB = zaposleniID;
        this.imeIPrezime = imeIPrezime;
        this.strucnaSprema = strucnaSprema;
        this.radniStaz = radniStaz;
    }

    public int getZaposleniRB() {
        return zaposleniRB;
    }

    public void setZaposleniRB(int zaposleniID) {
        this.zaposleniRB = zaposleniID;
    }

    public String getImeIPrezime() {
        return imeIPrezime;
    }

    public void setImeIPrezime(String imeIPrezime) {
        this.imeIPrezime = imeIPrezime;
    }

    public String getStrucnaSprema() {
        return strucnaSprema;
    }

    public void setStrucnaSprema(String strucnaSprema) {
        this.strucnaSprema = strucnaSprema;
    }

    public int getRadniStaz() {
        return radniStaz;
    }

    public void setRadniStaz(int radniStaz) {
        this.radniStaz = radniStaz;
    }

    @Override
    public String vratiNazivTabele() {
        return "zaposleni";
    }

    @Override
    public String vratiVrednostiZaInsert(int uslov) {
        return "("+zaposleniRB+", '"+imeIPrezime+"', '"+strucnaSprema+"', "+radniStaz+")";
    }

    @Override
    public String vratiNazivKolone() {
        return "imePrezimeZ";
    }

    @Override
    public String vratiVrednostZaSet() {
        return String.format("imePrezimeZ='%s', strucnaSprema='%s', radniStaz='%s' ", imeIPrezime, strucnaSprema, radniStaz);
    }

    @Override
    public String vratiVrednostZaFrom() {
        return "zaposleni";
    }

    @Override
    public String vratiVrednostZaWhere() {
        if (uslov == Konstante.VRATI_ZAPOSLENE_PO_KRITERIJUMU) {
            return "imePrezimeZ = '" + imeIPrezime + "' OR strucnaSprema = '" + strucnaSprema + "'";
        }
        return String.format("zaposleniRb  = '%s'", zaposleniRB);
    }

    @Override
    public String vratiVrednostZaSelect() {
        return "zaposleniRb, imePrezimeZ, strucnaSprema, radniStaz";
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        if(rs.next()){
        
            zaposleniRB = rs.getInt("zaposleniRb");
            imeIPrezime = rs.getString("imePrezimeZ");
            strucnaSprema = rs.getString("strucnaSprema");
            radniStaz = rs.getInt("radniStaz");
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
        List<Zaposleni> lista = new ArrayList<>();
        while(rs.next()){
            int zapRb = rs.getInt("zaposleniRb");
            String ime = rs.getString("imePrezimeZ");
            String ss = rs.getString("strucnaSprema");
            int staz = rs.getInt("radniStaz");
            Zaposleni z = new Zaposleni(zapRb, ime, ss, staz);
            lista.add(z);
        }
        return lista;
    }

    @Override
    public boolean imaSlabeObjekte() {
        return false;
    }

    @Override
    public void napuniObjekatSlabimObjektima(ResultSet tabela) throws Exception {}

    @Override
    public int brojSlabihObjekata() {
        return 0;
    }

    @Override
    public String toString() {
        return imeIPrezime;
    }

    @Override
      public boolean equals(Object obj) {
        if (obj != null && obj instanceof Zaposleni) {
            Zaposleni z = (Zaposleni) obj;
            return z.getZaposleniRB()==this.zaposleniRB;
        }
        return false;
    }

}
    
    
    

