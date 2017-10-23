/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolekcija;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import domen.Koordinator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Vladan
 */
public class UlogovaniKoordinatori {
    private static UlogovaniKoordinatori instance;
    private List<Koordinator> lk;
    private List<Date> ld;

    public UlogovaniKoordinatori() {
        lk = new ArrayList<Koordinator>();
        ld = new ArrayList<Date>();
    }
    
    public static UlogovaniKoordinatori getInstance(){
        if(instance==null) instance = new UlogovaniKoordinatori();
        return instance;
    }
    
    public void dodaj(Koordinator koordinator, Date vremeLogovanja){
        lk.add(koordinator);
        ld.add(vremeLogovanja);
    }
    
    public void obrisi(Koordinator koordinator){
    
        ld.remove(lk.indexOf(koordinator));
        lk.remove(koordinator);
    }
    
    public List<Koordinator> vratiListuUlogovanihKoordinatora(){
        return lk;
    }
    
    public List<Date> vratiVremenaLogovanja(){
        return ld;
    }
    
}
