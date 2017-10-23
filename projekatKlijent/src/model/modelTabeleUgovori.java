/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Ugovori;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Vladan
 */
public class modelTabeleUgovori extends AbstractTableModel{
    List<Ugovori> lista = new ArrayList<>();
    String[] kolone = {"ID","Kompanija","Usluge"};

    public modelTabeleUgovori(List<Ugovori> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        if(lista.isEmpty()) return 0;
        
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ugovori ugovor = lista.get(rowIndex);
        switch(columnIndex){
            case 0: return ugovor.getUgovorID();
            case 1: return ugovor.getNazivFirme();
            case 2: return ugovor.getUsluga().getNaziv();
            default: return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public Ugovori vratiSelektovanUgovor(int red){
        Ugovori u = lista.get(red);
        return u;
    }
    
}
