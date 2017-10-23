/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Koordinator;
import domen.RadneSmene;
import domen.Ugovori;
import domen.Usluge;
import domen.Zaposleni;
import java.util.ArrayList;
import java.util.List;
import so.OpstaSO;
import so.koordinator.IzlogujSeSO;
import so.koordinator.UlogujSeSo;
import so.koordinator.VratiListuKoordinatoraSO;
import so.radnesmene.IzmeniRadnuSmenuSO;
import so.radnesmene.SacuvajRadnuSmenuSO;
import so.radnesmene.VratiListuRadnihSmenaSO;
import so.radnesmene.VratiRadneSmenePoKriterijumuSO;
import so.ugovori.SacuvajUgovorSO;
import so.ugovori.VratiListuUgovoraSO;
import so.ugovori.VratiUgovorePoKoordinatoruSO;
import so.ugovori.VratiUgovorePoKriterijumuSO;
import so.usluge.VratiListuUslugaSO;
import so.zaposleni.IzmeniZaposlenogSO;
import so.zaposleni.ObrisiZaposlenogSO;
import so.zaposleni.SacuvajZaposlenogSO;
import so.zaposleni.VratiDetaljeZaposlenogSO;
import so.zaposleni.VratiListuZaposlenihSO;
import so.zaposleni.VratiZaposlenePoKriterijumuSO;

/**
 *
 * @author Vladan
 */
public class Kontroler {
    private static Kontroler instance;

    public Kontroler() {
    }
    
    public static Kontroler getInstance(){
        if(instance==null) instance = new Kontroler();
        return instance;
    }
    
           public List<Koordinator> vratiKoordinatore() throws Exception {
        OpstaSO os = new VratiListuKoordinatoraSO();
        os.izvrsiOperaciju(new Koordinator());
        List<Koordinator> lr = (List<Koordinator>) os.getObjekat();
        return lr;
    }
     
     public Koordinator ulogujSe(Koordinator r) throws Exception{
        OpstaSO os = new UlogujSeSo();
        os.izvrsiOperaciju(r);
        r = (Koordinator) os.getObjekat();
        return r;
    }

    public void izlogujSe(Koordinator k) throws Exception{
        OpstaSO os = new IzlogujSeSO();
        os.izvrsiOperaciju(k);
    }
    
    public List<Usluge> vratiUsluge() throws Exception{
        OpstaSO so = new VratiListuUslugaSO();
        so.izvrsiOperaciju(new Usluge());
        List<Usluge> lista = (List<Usluge>) so.getObjekat();
        return lista;
    
    }
    
    public List<RadneSmene> vratiRadneSmene() throws Exception{
        OpstaSO os = new VratiListuRadnihSmenaSO();
        os.izvrsiOperaciju(new RadneSmene());
        List<RadneSmene> lista = (List<RadneSmene>) os.getObjekat();
        return lista;
    }
    
    public List<Zaposleni> vratiZaposlene() throws Exception{
        OpstaSO os = new VratiListuZaposlenihSO();
        os.izvrsiOperaciju(new Zaposleni());
        List<Zaposleni> lista = (List<Zaposleni>) os.getObjekat();
        return lista;
    
    }
    
    public void sacuvajZaposlenog(Zaposleni z) throws Exception{
    OpstaSO os = new SacuvajZaposlenogSO();
    os.izvrsiOperaciju(z);
    }
    
    public void sacuvajRadnuSmenu(RadneSmene rs) throws Exception{
        OpstaSO os = new SacuvajRadnuSmenuSO();
        os.izvrsiOperaciju(rs);
    }
    
    public List<Ugovori> vratiUgovore() throws Exception{
        OpstaSO os = new VratiListuUgovoraSO();
        os.izvrsiOperaciju(new Ugovori());
        List<Ugovori> lista = (List<Ugovori>) os.getObjekat();
        return lista;
    }
    
    public void sacuvajUgovor(Ugovori u) throws Exception{
        OpstaSO os = new SacuvajUgovorSO();
        os.izvrsiOperaciju(u);
    }
    public List<Zaposleni> vratiZaposlenePoKriterijumu(Zaposleni z) throws Exception {
        OpstaSO os = new VratiZaposlenePoKriterijumuSO();
        os.izvrsiOperaciju(z);
        List<Zaposleni> lista = (List<Zaposleni>) os.getObjekat();
        return lista;
    }
    
    public List<RadneSmene> vratiRadneSmenePoKriterijumu(RadneSmene smena) throws Exception {
        OpstaSO os = new VratiRadneSmenePoKriterijumuSO();
        os.izvrsiOperaciju(smena);
        List<RadneSmene> lista = (List<RadneSmene>) os.getObjekat();
        return lista;
    }
    
    public List<Ugovori> vratiUgovorePoKriterijumu(Ugovori u) throws Exception {
        OpstaSO os = new VratiUgovorePoKriterijumuSO();
        os.izvrsiOperaciju(u);
        List<Ugovori> lista = (List<Ugovori>) os.getObjekat();
        return lista;
    }
    
    public List<Ugovori> vratiUgovorePoKoordinatoru(Ugovori u) throws Exception {
        OpstaSO os = new VratiUgovorePoKoordinatoruSO();
        os.izvrsiOperaciju(u);
        List<Ugovori> lista = (List<Ugovori>) os.getObjekat();
        return lista;
    }
    
    public void ObrisiZaposlenog(Zaposleni z) throws Exception{
        OpstaSO os = new ObrisiZaposlenogSO();
        os.izvrsiOperaciju(z);
    }
    
    public void IzmeniZaposlenog(Zaposleni z) throws Exception{
        OpstaSO os = new IzmeniZaposlenogSO();
        os.izvrsiOperaciju(z);
    }
    
    public void IzmeniRadnuSmenu(RadneSmene smena) throws Exception{
        OpstaSO os = new IzmeniRadnuSmenuSO();
        os.izvrsiOperaciju(smena);
    }
    
    public Zaposleni vratiDetaljeZaposlenog(Zaposleni z) throws Exception{
        OpstaSO os = new VratiDetaljeZaposlenogSO();
        os.izvrsiOperaciju(z);
        Zaposleni zap = (Zaposleni) os.getObjekat();
        return zap;
    }
}
