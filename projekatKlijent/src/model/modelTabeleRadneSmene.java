/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.RadneSmene;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Vladan
 */
public class modelTabeleRadneSmene extends AbstractTableModel{
    String[] kolone = {"datum", "vreme", "zaposleni"};
    List<RadneSmene> lista;

    public modelTabeleRadneSmene(List<RadneSmene> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
    
    

    
    
    
    @Override
    public int getRowCount() {
        if(lista==null){ return 0;}
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RadneSmene smena = lista.get(rowIndex);
        
        SimpleDateFormat sdf = new SimpleDateFormat("MM.yyyy.");
        String datum = sdf.format(smena.getDatumRadneSmene());
        
        switch(columnIndex){
            case 0: return datum;
            case 1: return smena.getVremeRadneSmene();
            case 2: return smena.getZaposleni().getImeIPrezime();
            default: return "nista";
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public void setList(List<RadneSmene> l){
        this.lista = l;
        fireTableDataChanged();
    }
    
    public void dodaj(RadneSmene smena){
        lista.add(smena);
        fireTableDataChanged();
    }
    
    public void obrisi(int red){
        lista.remove(red);
        fireTableDataChanged();
    }
    
    public RadneSmene vratiSmenu(int red){
        RadneSmene smena = lista.get(red);
        return smena;
    }
    
    
}
