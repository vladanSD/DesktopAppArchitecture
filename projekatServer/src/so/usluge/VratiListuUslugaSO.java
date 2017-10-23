/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.usluge;

import db.DBBroker;
import domen.Usluge;
import so.OpstaSO;

/**
 *
 * @author Vladan
 */
public class VratiListuUslugaSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        super.setObjekat(DBBroker.getInstance().vratiSveObjekte((Usluge)objekat));
    }
    
}
