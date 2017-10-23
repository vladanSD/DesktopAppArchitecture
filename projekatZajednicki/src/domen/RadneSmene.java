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
public class RadneSmene implements Serializable, OpstiDomenskiObjekat {

    private int radneSmeneID;
    private Date datumRadneSmene;
    private int vremeRadneSmene;
    private Zaposleni zaposleni;
    private Koordinator koordinator;

    private int uslov;

    public RadneSmene() {
    }

    public RadneSmene(int radneSmeneID, Date datumRadneSmene, int vremeRadneSmene, Zaposleni zaposleniRB, Koordinator koordinatorID) {
        this.radneSmeneID = radneSmeneID;
        this.datumRadneSmene = datumRadneSmene;
        this.vremeRadneSmene = vremeRadneSmene;
        this.zaposleni = zaposleniRB;
        this.koordinator = koordinatorID;
    }

    public int getRadneSmeneID() {
        return radneSmeneID;
    }

    public void setRadneSmeneID(int radneSmeneID) {
        this.radneSmeneID = radneSmeneID;
    }

    public Date getDatumRadneSmene() {
        return datumRadneSmene;
    }

    public void setDatumRadneSmene(Date datumRadneSmene) {
        this.datumRadneSmene = datumRadneSmene;
    }

    public int getVremeRadneSmene() {
        return vremeRadneSmene;
    }

    public void setVremeRadneSmene(int vremeRadneSmene) {
        this.vremeRadneSmene = vremeRadneSmene;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Koordinator getKoordinatorID() {
        return koordinator;
    }

    public void setKoordinatorID(Koordinator koordinatorID) {
        this.koordinator = koordinatorID;
    }

    @Override
    public String vratiNazivTabele() {
        return "radnesmene";
    }

    @Override
    public String vratiVrednostiZaInsert(int uslov) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String datumStr = dateFormat.format(datumRadneSmene);
        return "(" + radneSmeneID + ", '" + datumStr + "', " + vremeRadneSmene + ", " + zaposleni.getZaposleniRB()+ ", " + koordinator.getKoordinatorID() + ")";
    }

    @Override
    public String vratiNazivKolone() {
        return "radnesmeneID";
    }
    
    
//    private int radneSmeneID;
//    private Date datumRadneSmene;
//    private int vremeRadneSmene;
//    private Zaposleni zaposleni;
//    private Koordinator koordinator;
    
    
    @Override
    public String vratiVrednostZaSet() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String datumStr = dateFormat.format(datumRadneSmene);
        return String.format("zaposleniRb='%s', datumRadneSmene='%s', vremeRadneSmene='%s' ", zaposleni.getZaposleniRB(), datumStr, vremeRadneSmene);
    }

    @Override
    public String vratiVrednostZaFrom() {
        return "radnesmene rs join zaposleni z on rs.zaposleniRb=z.zaposleniRb join koordinatori k on rs.koordinatorID=k.koordinatorID";
    }

    @Override
    public String vratiVrednostZaWhere() {
        if (uslov == Konstante.VRATI_SMENE_PO_KRITERIJUMU) {
            return "rs.vremeRadneSmene = " + vremeRadneSmene + " OR z.imePrezimeZ = '"+zaposleni.getImeIPrezime()+ "'";
        }
        return String.format("radneSmeneID  = '%s'", radneSmeneID);
    }

    @Override
    public String vratiVrednostZaSelect() {
        return " z.zaposleniRb, z.imePrezimeZ, z.strucnaSprema, z.radniStaz, k.koordinatorID, k.imePrezime, k.korisnickoIme, k.korisnickaLozinka,"
                + "rs.radneSmeneID, rs.vremeRadneSmene, rs.datumRadneSmene ";
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        if (rs.next()) {
            int zaposleniRB = rs.getInt("zaposleniRb");
            String imeIPrezime = rs.getString("imePrezimeZ");
            String strucnaSprema = rs.getString("strucnaSprema");
            int radniStaz = rs.getInt("radniStaz");
            
            Zaposleni z = new Zaposleni(zaposleniRB,imeIPrezime,strucnaSprema,radniStaz);
            
            int koordinatorID = rs.getInt("koordinatorID");
            String ime = rs.getString("imePrezime");
            String kor = rs.getString("korisnickoIme");
            String loz = rs.getString("korisnickaLozinka");
            
            Koordinator k = new Koordinator(koordinatorID, ime, kor, loz);
            zaposleni = z;
            koordinator = k;
            radneSmeneID = rs.getInt("radneSmeneID");
            datumRadneSmene = new Date(rs.getDate("datumRadneSmene").getTime());
            vremeRadneSmene = rs.getInt("vremeRadneSmene");
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
        List<RadneSmene> lista = new ArrayList<>();
        while (rs.next()) {
            int koorID = rs.getInt("koordinatorID");
            String imePrezime = rs.getString("imePrezime");
            String korIme = rs.getString("korisnickoIme");
            String lozinka = rs.getString("korisnickaLozinka");
            Koordinator kord = new Koordinator(koorID, imePrezime, korIme, lozinka);
            
            int zaposleniRB = rs.getInt("zaposleniRb");
            String imeIPrezime = rs.getString("imePrezimeZ");
            String strucnaSprema = rs.getString("strucnaSprema");
            int radniStaz = rs.getInt("radniStaz");
            
            Zaposleni z = new Zaposleni(zaposleniRB,imeIPrezime,strucnaSprema,radniStaz);
            
            int rsID = rs.getInt("radneSmeneID");
            Date datum = new Date(rs.getDate("datumRadneSmene").getTime());
            int vreme = rs.getInt("vremeRadneSmene");
            
            RadneSmene smena = new RadneSmene(rsID, datum, vreme, z, kord);
            lista.add(smena);
            
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
        return String.valueOf(vremeRadneSmene);
    }
    
    
}
