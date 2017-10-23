/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.koordinator;

import db.DBBroker;
import domen.Koordinator;
import java.util.Date;
import java.util.List;
import kolekcija.UlogovaniKoordinatori;
import konstante.Konstante;
import so.OpstaSO;

/**
 *
 * @author Vladan
 */
public class UlogujSeSo extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
        Koordinator r = (Koordinator) objekat;

        r.postaviUslov(Konstante.VRATI_KOORDINATORE);
        List<Koordinator> lr = DBBroker.getInstance().vratiSveObjekte(new Koordinator());

        Koordinator recepcionar = null;
        for (Koordinator rec : lr) {
            if(r.getKorisnickoIme().equals(rec.getKorisnickoIme())){
                recepcionar = rec;
                break;
            }
        }
        if(recepcionar == null)
            throw new Exception("Sistem ne prepoznaje upisane informacije, molimo vas proverite korisničko ime i šifru!");

        for (Koordinator rec : lr) {
            if((r.getKorisnickaLozinka().equals(rec.getKorisnickaLozinka())) && (r.getKorisnickoIme().equals(rec.getKorisnickoIme()))){
                List<Koordinator> listaUlogovanih = kolekcija.UlogovaniKoordinatori.getInstance().vratiListuUlogovanihKoordinatora();
                for (Koordinator recep : listaUlogovanih) {
                    if(r.getKorisnickoIme().equals(recep.getKorisnickoIme()))
                        throw new Exception("Koordinator je već ulogovan.");
                }
                return;
            }
        }
        
        throw new Exception("Pogrešno korisničko ime ili šifra!");}

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
     Koordinator r = (Koordinator) objekat;
        r = (Koordinator) DBBroker.getInstance().vratiObjekat(r);
        Date vremeLogovanja = new Date();
        UlogovaniKoordinatori.getInstance().dodaj(r, vremeLogovanja);
        super.setObjekat(r);
    }
    
}
