/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Vladan
 */
public interface OpstiDomenskiObjekat {
    
    public String vratiNazivTabele();
    public String vratiVrednostiZaInsert(int uslov);
    public String vratiNazivKolone();
    public String vratiVrednostZaSet();
    public String vratiVrednostZaFrom();
    public String vratiVrednostZaWhere();
    public String vratiVrednostZaSelect();
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception;
    public void postaviUslov(int uslov);
    public int vratiUslov();
    public List vratiListu(ResultSet rs) throws Exception;
    public boolean imaSlabeObjekte();
    public void napuniObjekatSlabimObjektima(ResultSet tabela) throws Exception;
    public int brojSlabihObjekata();
    
}
