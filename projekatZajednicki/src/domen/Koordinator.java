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
public class Koordinator implements Serializable, OpstiDomenskiObjekat {
    private int koordinatorID;
    private String imeIPrezime;
    private String korisnickoIme;
    private String korisnickaLozinka;
    
    private int uslov;

    public Koordinator() {
    }

    public Koordinator(int koordinatorID, String imeIPrezime, String korisnickoIme, String korisnickaLozinka) {
        this.koordinatorID = koordinatorID;
        this.imeIPrezime = imeIPrezime;
        this.korisnickoIme = korisnickoIme;
        this.korisnickaLozinka = korisnickaLozinka;
    }

    public int getKoordinatorID() {
        return koordinatorID;
    }

    public void setKoordinatorID(int koordinatorID) {
        this.koordinatorID = koordinatorID;
    }

    public String getImeIPrezime() {
        return imeIPrezime;
    }

    public void setImeIPrezime(String imeIPrezime) {
        this.imeIPrezime = imeIPrezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getKorisnickaLozinka() {
        return korisnickaLozinka;
    }

    public void setKorisnickaLozinka(String korisnickaLozinka) {
        this.korisnickaLozinka = korisnickaLozinka;
    }

    @Override
    public String toString() {
        return imeIPrezime;
    }
    
    public boolean equals(Object obj){
        if(obj != null && obj instanceof Koordinator){
            Koordinator k = (Koordinator) obj;
            return k.getKorisnickoIme().equals(this.getKorisnickoIme());
        }
        return false;
    }
    
    

    @Override
    public String vratiNazivTabele() {
        return "koordinatori";
    }

    @Override
    public String vratiVrednostiZaInsert(int uslov) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiNazivKolone() {
        return "imePrezime";
    }

    @Override
    public String vratiVrednostZaSet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    @Override
    public String vratiVrednostZaFrom() {
        return "koordinatori";
    }

    @Override
    public String vratiVrednostZaWhere() {
        return String.format("korisnickoIme = '%s'", korisnickoIme);
    }

    @Override
    public String vratiVrednostZaSelect() {
        return "koordinatorID, imePrezime, korisnickoIme, korisnickaLozinka";
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
         if (rs.next()) {
            koordinatorID = rs.getInt("koordinatorID");
            imeIPrezime = rs.getString("imePrezime");
            korisnickoIme = rs.getString("korisnickoIme");
            korisnickaLozinka = rs.getString("korisnickaLozinka");
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
          List<Koordinator> listaKoordinatora = new ArrayList<>();
        while (rs.next()) {
            int koorID = rs.getInt("koordinatorID");
            String imePrezime = rs.getString("imePrezime");
            String korIme = rs.getString("korisnickoIme");
            String lozinka = rs.getString("korisnickaLozinka");
            Koordinator kord = new Koordinator(koorID, imePrezime, korIme, lozinka);
            listaKoordinatora.add(kord);
        }
        return listaKoordinatora;
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
