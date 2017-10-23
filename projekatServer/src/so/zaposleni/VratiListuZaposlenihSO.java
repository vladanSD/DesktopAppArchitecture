/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaposleni;

import db.DBBroker;
import domen.Zaposleni;
import so.OpstaSO;

/**
 *
 * @author Vladan
 */
public class VratiListuZaposlenihSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {}

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        super.setObjekat(DBBroker.getInstance().vratiSveObjekte(new Zaposleni()));
    }
    
}
