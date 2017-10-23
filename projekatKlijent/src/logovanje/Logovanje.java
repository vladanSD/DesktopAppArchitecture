/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logovanje;

import domen.Koordinator;
import sun.security.jca.GetInstance;

/**
 *
 * @author Vladan
 */
public class Logovanje {
    private static Logovanje instance;
    private Koordinator ulogovaniKoordinator;
    
    public static Logovanje getInstance(){
        if(instance==null) instance = new Logovanje();
        return instance;
    }

    public Koordinator getUlogovaniKoordinator() {
        return ulogovaniKoordinator;
    }

    public void setUlogovaniKoordinator(Koordinator ulogovaniKoordinator) {
        this.ulogovaniKoordinator = ulogovaniKoordinator;
    }
    
    
}
