/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.radnesmene;

import db.DBBroker;
import domen.RadneSmene;
import domen.Zaposleni;
import java.util.List;
import konstante.Konstante;
import so.OpstaSO;

/**
 *
 * @author Vladan
 */
public class VratiRadneSmenePoKriterijumuSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        RadneSmene smena = (RadneSmene) objekat;
        smena.postaviUslov(Konstante.VRATI_SMENE_PO_KRITERIJUMU);
        List<RadneSmene> lista = DBBroker.getInstance().vratiObjektePoUslovu(smena);
        super.setObjekat(lista);
    }
    
}
