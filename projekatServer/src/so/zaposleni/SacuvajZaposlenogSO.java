/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaposleni;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Zaposleni;
import java.util.List;
import so.OpstaSO;

/**
 *
 * @author Vladan
 */
public class SacuvajZaposlenogSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
        OpstaSO os = new VratiListuZaposlenihSO();
        os.izvrsiOperaciju(new Zaposleni());
        List<Zaposleni> lista = (List<Zaposleni>) os.getObjekat();
        if(lista.isEmpty()) 
            return;
        
        Zaposleni z  = (Zaposleni) objekat;
        for(Zaposleni zaposleni : lista){
            if(zaposleni.getZaposleniRB()==z.getZaposleniRB()){
                throw new Exception("Postoji zaposleni sa tim rednim brojem: "+z.getZaposleniRB());
            }
        }
        
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        DBBroker.getInstance().sacuvajObjekat((Zaposleni) objekat);
    }
    
}
