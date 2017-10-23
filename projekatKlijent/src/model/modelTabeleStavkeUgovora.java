/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.StavkeUgovora;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Vladan
 */
public class modelTabeleStavkeUgovora extends AbstractTableModel{
    
    List<StavkeUgovora> lista = new ArrayList<>();
    String[] kolone = {"Rb","Datum od","Datum do","Cena"};
    private boolean izmena = true;
    
    

    @Override
    public int getRowCount() {
        
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkeUgovora stavka = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        switch(columnIndex){
            case 0: return stavka.getRbStavke();
            case 1: return sdf.format(stavka.getDatumOd());
            case 2: return sdf.format(stavka.getDatumDo());
            case 3: return stavka.getCena();
            default: return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
    public void dodaj(){
        StavkeUgovora stavka = new StavkeUgovora(1, new Date(), new Date(), 0);
        lista.add(stavka);
        int brojac = 1;
        for(StavkeUgovora stav : lista){
            stav.setRbStavke(brojac);
            brojac++;
        }
        fireTableDataChanged();
    }
    
    public void obrisi(int stavka){
        if(stavka== -1){JOptionPane.showMessageDialog(null, "Izaberite red");
            return;
        }
        
        lista.remove(stavka);
        int brojac = 1;
        for(StavkeUgovora stav : lista){
            stav.setRbStavke(brojac);
            brojac++;
        }
        fireTableDataChanged();
    }

    public List<StavkeUgovora> vratiListu(){
        return lista;
    }
    
    public void postaviListu(List<StavkeUgovora> lista){
        this.lista = lista;
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 0 || !izmena) return false;
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        StavkeUgovora stavka = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        switch(columnIndex){
            case 0: break;
            case 1: 
                Date date;
        {
            try {
                 date = sdf.parse((String)aValue);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Datum mora biti u dd/MM/yyyy obliku");
                return;
            }
            stavka.setDatumOd(date);
            stavka.setDatumDo(date);
            fireTableDataChanged();
        }       
                break;
            case 2: Date date2;
        {
            try {
               date2 = sdf.parse((String)aValue);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Datum mora biti u dd/MM/yyyy obliku");
                return;
            } 
            if(date2.after(stavka.getDatumOd())){
            stavka.setDatumDo(date2);
            } else{
                JOptionPane.showMessageDialog(null, "DatumDo mora biti nakon DatumOd");
                
            }
        }
                break;
            case 3: double cena;
                try {
                    String cenaS = (String) aValue;
                    cena = Double.parseDouble(cenaS);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Nepravilno uneta vrednost u kolonu cena.");
                    return;
                }
                stavka.setCena(cena);
                break;
                
        }
    }
    
    
    public void zabraniIzmenu(){
        izmena = false;
    }
    
}
