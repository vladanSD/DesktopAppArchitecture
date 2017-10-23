/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaposleni;

import db.DBBroker;
import domen.Zaposleni;
import konstante.Konstante;
import so.OpstaSO;

/**
 *
 * @author Vladan
 */
public class IzmeniZaposlenogSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {}

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Zaposleni z = (Zaposleni) objekat;
        z.postaviUslov(Konstante.IZMENI_ZAPOSLENOG);
        DBBroker.getInstance().izmeniObjekat(z);
    }
    
}
