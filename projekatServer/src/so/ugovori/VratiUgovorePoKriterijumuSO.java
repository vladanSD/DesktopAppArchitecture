/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.ugovori;

import db.DBBroker;
import domen.Ugovori;
import domen.Zaposleni;
import java.util.List;
import konstante.Konstante;
import so.OpstaSO;

/**
 *
 * @author Vladan
 */
public class VratiUgovorePoKriterijumuSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Ugovori u = (Ugovori) objekat;
        u.postaviUslov(Konstante.VRATI_UGOVORE_PO_KRITERIJUMU);
        List<Ugovori> lista = DBBroker.getInstance().vratiObjektePoUslovu(u);
        super.setObjekat(lista);
    }
    
}
