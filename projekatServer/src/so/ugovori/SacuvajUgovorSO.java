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
import so.OpstaSO;
import so.zaposleni.VratiListuZaposlenihSO;

/**
 *
 * @author Vladan
 */
public class SacuvajUgovorSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
         OpstaSO os = new VratiListuUgovoraSO();
        os.izvrsiOperaciju(new Ugovori());
        List<Ugovori> lista = (List<Ugovori>) os.getObjekat();
        if(lista.isEmpty()) 
            return;
        
        Ugovori u  = (Ugovori) objekat;
        for(Ugovori ugovor : lista){
            if(ugovor.getUgovorID()==u.getUgovorID()){
                throw new Exception("Postoji ugovor sa tim brojem: "+u.getUgovorID());
            }
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        DBBroker.getInstance().sacuvajObjekat((Ugovori)objekat);
    }
    
}
