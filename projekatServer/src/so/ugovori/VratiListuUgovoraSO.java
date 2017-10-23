/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.ugovori;

import db.DBBroker;
import domen.Ugovori;
import so.OpstaSO;

/**
 *
 * @author Vladan
 */
public class VratiListuUgovoraSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        super.setObjekat(DBBroker.getInstance().vratiSveObjekte((Ugovori)objekat));
    }
    
}
