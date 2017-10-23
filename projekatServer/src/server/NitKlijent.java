/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import domen.Koordinator;
import domen.RadneSmene;
import domen.Ugovori;
import domen.Zaposleni;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Konstante;
import kontroler.Kontroler;
import transfer.TransferObject;

/**
 *
 * @author Vladan
 */
public class NitKlijent extends Thread {
    private Socket s;
    private boolean kraj;

    public NitKlijent(Socket s) {
        this.s = s;
        start();
    }

    @Override
    public void run() {kraj = false;
        while (!kraj) {
            try {
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                TransferObject to = (TransferObject) ois.readObject();
                obradiZahtev(to);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    private void obradiZahtev(TransferObject to) throws IOException {
        try {
            switch (to.getOperacija()) {
                
                case Konstante.VRATI_KOORDINATORE:
                    to.setOdgovor(Kontroler.getInstance().vratiKoordinatore());
                    posaljiOdgovor(to);
                    break;
                case Konstante.ULOGUJ_SE:
                    Koordinator rcp = (Koordinator) to.getParametar();
                    to.setOdgovor(Kontroler.getInstance().ulogujSe(rcp));
                    posaljiOdgovor(to);
                    break;
                case Konstante.IZLOGUJ_SE:
                    Kontroler.getInstance().izlogujSe((Koordinator) to.getParametar());
                    posaljiOdgovor(to);
                    break;
                case Konstante.KRAJ_RADA:
                    kraj = true;
                    posaljiOdgovor(to);
                    s.close();
                case Konstante.VRATI_USLUGE:
                    to.setOdgovor(Kontroler.getInstance().vratiUsluge());
                    posaljiOdgovor(to);
                    break;
                case Konstante.VRATI_RADNESMENE:
                    to.setOdgovor(Kontroler.getInstance().vratiRadneSmene());
                    posaljiOdgovor(to);
                    break;
                case Konstante.VRATI_ZAPOSLENE:
                    to.setOdgovor(Kontroler.getInstance().vratiZaposlene());
                    posaljiOdgovor(to);
                    break;
                case Konstante.SACUVAJ_ZAPOSLENOG:
                    Kontroler.getInstance().sacuvajZaposlenog((Zaposleni) to.getParametar());
                    posaljiOdgovor(to);
                    break;
                case Konstante.SACUVAJ_SMENU:
                    Kontroler.getInstance().sacuvajRadnuSmenu((RadneSmene) to.getParametar());
                    posaljiOdgovor(to);
                    break;
                case Konstante.VRATI_UGOVORE:
                    to.setOdgovor(Kontroler.getInstance().vratiUgovore());
                    posaljiOdgovor(to);
                    break;
                case Konstante.SACUVAJ_UGOVOR:
                    Kontroler.getInstance().sacuvajUgovor((Ugovori) to.getParametar());
                    posaljiOdgovor(to);
                    break;
                case Konstante.VRATI_ZAPOSLENE_PO_KRITERIJUMU:
                    to.setOdgovor(Kontroler.getInstance().vratiZaposlenePoKriterijumu((Zaposleni) to.getParametar()));
                    posaljiOdgovor(to);
                    break;
                case Konstante.VRATI_SMENE_PO_KRITERIJUMU:
                    to.setOdgovor(Kontroler.getInstance().vratiRadneSmenePoKriterijumu((RadneSmene) to.getParametar()));
                    posaljiOdgovor(to);
                    break;
                case Konstante.VRATI_UGOVORE_PO_KRITERIJUMU:
                    to.setOdgovor(Kontroler.getInstance().vratiUgovorePoKriterijumu((Ugovori) to.getParametar()));
                    posaljiOdgovor(to);
                    break;
                case Konstante.VRATI_UGOVORE_PO_KOORDINATORU:
                    to.setOdgovor(Kontroler.getInstance().vratiUgovorePoKoordinatoru((Ugovori) to.getParametar()));
                    posaljiOdgovor(to);
                    break;
                case Konstante.OBRISI_ZAPOSLENOG:
                    Kontroler.getInstance().ObrisiZaposlenog((Zaposleni) to.getParametar());
                    posaljiOdgovor(to);
                    break;
                case Konstante.IZMENI_ZAPOSLENOG:
                    Kontroler.getInstance().IzmeniZaposlenog((Zaposleni) to.getParametar());
                    posaljiOdgovor(to);
                    break;
                case Konstante.IZMENI_RADNUSMENU:
                    Kontroler.getInstance().IzmeniRadnuSmenu((RadneSmene) to.getParametar());
                    posaljiOdgovor(to);
                    break;
                case Konstante.DETALJI_ZAPOSLENOG:
                    Zaposleni z = (Zaposleni) to.getParametar();
                    to.setOdgovor(Kontroler.getInstance().vratiDetaljeZaposlenog(z));
                    posaljiOdgovor(to);
                    break;
            }
        } catch (Exception e) {
            to.setPoruka(e.getMessage());
            posaljiOdgovor(to);
        }
    }
    
    
      private void posaljiOdgovor(TransferObject to) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        oos.writeObject(to);
    }
}
