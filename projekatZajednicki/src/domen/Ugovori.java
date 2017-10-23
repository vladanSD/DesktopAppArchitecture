/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import konstante.Konstante;

/**
 *
 * @author Vladan
 */
public class Ugovori implements Serializable, OpstiDomenskiObjekat{
    private int ugovorID;
    private String nazivFirme;
    private String pib;
    private String opis;
    private String napomene;
    private Usluge usluga;
    private Koordinator koordinator;
    private List<StavkeUgovora> listaStavki;
    
    private int uslov;

    public Ugovori() {
        listaStavki = new ArrayList<>();
    }

    public Ugovori(int ugovorID, String nazivFirme, String pib, String opis, String napomene, Usluge usluga, Koordinator koordinator) {
        this.ugovorID = ugovorID;
        this.nazivFirme = nazivFirme;
        this.pib = pib;
        this.opis = opis;
        this.napomene = napomene;
        this.usluga = usluga;
        this.koordinator = koordinator;
        listaStavki = new ArrayList<>();
    }

    public int getUgovorID() {
        return ugovorID;
    }

    public void setUgovorID(int ugovorID) {
        this.ugovorID = ugovorID;
    }

    public String getNazivFirme() {
        return nazivFirme;
    }

    public void setNazivFirme(String nazivFirme) {
        this.nazivFirme = nazivFirme;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getNapomene() {
        return napomene;
    }

    public void setNapomene(String napomene) {
        this.napomene = napomene;
    }

    public Usluge getUsluga() {
        return usluga;
    }

    public void setUsluge(Usluge usluga) {
        this.usluga = usluga;
    }

    public Koordinator getKoordinator() {
        return koordinator;
    }

    public void setKoordinator(Koordinator koordinator) {
        this.koordinator = koordinator;
    }

    public List<StavkeUgovora> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<StavkeUgovora> listaStavki) {
        this.listaStavki = listaStavki;
    }

    @Override
    public String vratiNazivTabele() {
        if (uslov == Konstante.SLABI_OBJEKTI) {
            return "stavkeugovora";
        }
        return "ugovori";
    }

    @Override
    public String vratiVrednostiZaInsert(int i) {
        if (uslov == Konstante.SLABI_OBJEKTI) {
            StavkeUgovora stavka = listaStavki.get(i);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String datumOd = dateFormat.format(stavka.getDatumOd());
            String datumDo = dateFormat.format(stavka.getDatumDo());
            return "(" +ugovorID+", '"+stavka.getRbStavke()+"', '"+datumOd+"', '"+datumDo+"', '"+stavka.getCena()+"')";
        }
        
         return "("+ugovorID+", '"+nazivFirme+"', '"+pib+"', '"+opis+"', '"+napomene+"', "+usluga.getUslugeID()+", "+koordinator.getKoordinatorID()+")";
    }

    @Override
    public String vratiNazivKolone() {
        return "ugovoriID";
    }

    @Override
    public String vratiVrednostZaSet() {
       return "";
    }

    @Override
    public String vratiVrednostZaFrom() {
        if (uslov == Konstante.SLABI_OBJEKTI) {
            return "stavkeugovora";
        }
        return "ugovori ug join usluge us on ug.uslugeID = us.uslugeID join koordinatori k on ug.koordinatorID=k.koordinatorID";
    }

    @Override
    public String vratiVrednostZaWhere() {
        if (uslov == Konstante.VRATI_UGOVORE_PO_KOORDINATORU) {
            return "ug.koordinatorID = "+koordinator.getKoordinatorID()+"";
        }
        if (uslov == Konstante.VRATI_UGOVORE_PO_KRITERIJUMU) {
            return "ug.koordinatorID = "+koordinator.getKoordinatorID()+" AND "
                    + "(nazivFirme='"+nazivFirme+"' || pib='"+pib+"' || ugovoriID="+ugovorID+")";
        }
        return "ugovoriID ="+ugovorID;
    }

    @Override
    public String vratiVrednostZaSelect() {
        if (uslov == Konstante.SLABI_OBJEKTI) {
            return " rbStavke, datumOd, datumDo, cena ";
        }
        return " ug.ugovoriID, ug.nazivFirme, ug.pib, ug.opis, ug.napomene, us.uslugeID, us.naziv, us.sifraUsluge,"
                + " k.koordinatorID, k.imePrezime, k.korisnickoIme, k.korisnickaLozinka ";
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        if(rs.next()){
            ugovorID = rs.getInt("ugovoriID");
            nazivFirme = rs.getString("nazivFirme");
            pib = rs.getString("pib");
            opis = rs.getString("opis");
            napomene = rs.getString("napomene");
            
            int uslugeID = rs.getInt("uslugeID");
            String naziv = rs.getString("naziv");
            String sifraUsluge = rs.getString("sifraUsluge");
            usluga = new Usluge(uslugeID,naziv,sifraUsluge);
            
            int koordinatorID = rs.getInt("koordinatorID");
            String imePrezime = rs.getString("imePrezime");
            String korisnickoIme = rs.getString("korisnickoIme");
            String korisnickaLozinka = rs.getString("korisnickaLozinka");
            koordinator = new Koordinator(koordinatorID, imePrezime, korisnickoIme, korisnickaLozinka);
            
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
        List<Ugovori> lista = new ArrayList<>();
        while(rs.next()){
            int ugID = rs.getInt("ugovoriID");
            String nazivF = rs.getString("nazivFirme");
            String pibL = rs.getString("pib");
            String opi = rs.getString("opis");
            String napo = rs.getString("napomene");
            
            int uslugeID = rs.getInt("uslugeID");
            String naziv = rs.getString("naziv");
            String sifraUsluge = rs.getString("sifraUsluge");
            Usluge u = new Usluge(uslugeID,naziv,sifraUsluge);
            
            int koordinatorID = rs.getInt("koordinatorID");
            String imePrezime = rs.getString("imePrezime");
            String korisnickoIme = rs.getString("korisnickoIme");
            String korisnickaLozinka = rs.getString("korisnickaLozinka");
            Koordinator koor = new Koordinator(koordinatorID, imePrezime, korisnickoIme, korisnickaLozinka);
            
            Ugovori ugo = new Ugovori(ugID, nazivF, pibL, opi, napo, u, koor);
            lista.add(ugo);
        
        }
        return lista;
        
    }

    @Override
    public boolean imaSlabeObjekte() {
        return true;
    }

    @Override
    public void napuniObjekatSlabimObjektima(ResultSet rs) throws Exception {
        listaStavki.clear();
        while(rs.next()){
            int redniBroj = rs.getInt("rbStavke");
            Date datumOd = new Date(rs.getDate("datumOd").getTime());
            Date datumDo = new Date(rs.getDate("datumDo").getTime());
            double cena = rs.getDouble("cena");
            
            StavkeUgovora stavka  = new StavkeUgovora(redniBroj, datumOd, datumDo, cena);
            listaStavki.add(stavka);
            
        }
    }

    @Override
    public int brojSlabihObjekata() {
        return listaStavki.size();
    }

    @Override
    public String toString() {
        return nazivFirme + ", "+ugovorID;
    }
    
    
    

    
    
    
}
