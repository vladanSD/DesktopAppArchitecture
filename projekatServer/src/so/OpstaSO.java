/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;

/**
 *
 * @author Nikola
 */
public abstract class OpstaSO {
    
    private Object objekat;

    public Object getObjekat() {
        return objekat;
    }
    
    protected void setObjekat(Object objekat) {
        this.objekat = objekat;
    }
    
    public void izvrsiOperaciju(Object objekat) throws Exception {
        try {
            pokreniTransakciju();
            proveriPreduslov(objekat);
            izvrsiKonkretnuOperaciju(objekat);
            potvrdiTranskaciju();
        } catch (Exception ex) {
            ponistiTransakciju();          
            throw new Exception(ex.getMessage());
        }
    }
        
    private void pokreniTransakciju() throws Exception {
        DBBroker.getInstance().pokreniTransakciju();
    }
    
    protected abstract void proveriPreduslov(Object objekat) throws Exception;

    protected abstract void izvrsiKonkretnuOperaciju(Object objekat) throws Exception;
    
    private void potvrdiTranskaciju() throws Exception {
        DBBroker.getInstance().potvrdiTransakciju();
    }
    
    private void ponistiTransakciju() throws Exception {
        DBBroker.getInstance().ponistiTransakciju();
    }

}
