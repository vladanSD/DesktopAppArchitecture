/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Zaposleni;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Vladan
 */
public class modelTabeleZaposleni extends AbstractTableModel{
    List<Zaposleni> lista = null;
    String[] kolone = {"Ime i prezime", "Strucna sprema", "Godine iskustva"};

    public modelTabeleZaposleni(List<Zaposleni> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        if(lista==null) return 0;
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zaposleni z = lista.get(rowIndex);
        switch(columnIndex){
            case 0: return z.getImeIPrezime();
            case 1: return z.getStrucnaSprema();
            case 2: return z.getRadniStaz();
            default: return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public Zaposleni vratiZaposlenog(int red){
        return lista.get(red);
        
    }
    
}
