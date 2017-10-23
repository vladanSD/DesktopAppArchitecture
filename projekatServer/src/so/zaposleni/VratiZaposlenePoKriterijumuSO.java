/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaposleni;

import db.DBBroker;
import domen.Zaposleni;
import java.util.List;
import konstante.Konstante;
import so.OpstaSO;

/**
 *
 * @author Vladan
 */
public class VratiZaposlenePoKriterijumuSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Zaposleni z = (Zaposleni) objekat;
        z.postaviUslov(Konstante.VRATI_ZAPOSLENE_PO_KRITERIJUMU);
        List<Zaposleni> lista = DBBroker.getInstance().vratiObjektePoUslovu(z);
        super.setObjekat(lista);
    }
    
}
