/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.radnesmene;

import db.DBBroker;
import domen.RadneSmene;
import java.util.List;
import konstante.Konstante;
import so.OpstaSO;

/**
 *
 * @author Vladan
 */
public class IzmeniRadnuSmenuSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
        OpstaSO os = new VratiListuRadnihSmenaSO();
        os.izvrsiOperaciju(new RadneSmene());
        List<RadneSmene> lista = (List<RadneSmene>) os.getObjekat();
        if(lista.isEmpty()) 
            return;
        
        RadneSmene smena  = (RadneSmene) objekat;
        for(RadneSmene rsmene : lista){
            if(rsmene.getRadneSmeneID()==smena.getRadneSmeneID()){
                  if(rsmene.getKoordinatorID().getKoordinatorID()!= smena.getKoordinatorID().getKoordinatorID())
                throw new Exception("Radnu smenu može da izmeni samo prvobitni koordinator! ");
            }
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        RadneSmene smena = (RadneSmene) objekat;
        smena.postaviUslov(Konstante.IZMENI_RADNUSMENU);
        DBBroker.getInstance().izmeniObjekat(smena);
    }
    
}
