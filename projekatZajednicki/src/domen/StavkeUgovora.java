/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Vladan
 */
public class StavkeUgovora implements Serializable {
    
    private int rbStavke;
    private Date datumOd;
    private Date datumDo;
    private double cena;
    
    

    public StavkeUgovora() {
    }

    public StavkeUgovora(int rbStavke, Date datumOd, Date datumDo, double cena) {
        this.rbStavke = rbStavke;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.cena = cena;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }


    

    
    
}
