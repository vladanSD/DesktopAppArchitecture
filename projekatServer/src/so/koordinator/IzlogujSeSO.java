/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.koordinator;

import domen.Koordinator;
import so.OpstaSO;

/**
 *
 * @author Vladan
 */
public class IzlogujSeSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Koordinator k = (Koordinator) objekat;
        kolekcija.UlogovaniKoordinatori.getInstance().obrisi(k);
    }
    
}
