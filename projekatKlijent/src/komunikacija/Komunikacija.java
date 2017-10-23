/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import transfer.TransferObject;

/**
 *
 * @author Vladan
 */
public class Komunikacija {
    private static Komunikacija instance;
    private Socket s;

    public Komunikacija() throws UnknownHostException,IOException {
        s = new Socket("127.0.0.1", 9000);
    }
    
    public static Komunikacija getInstance() throws UnknownHostException,IOException{
        if(instance==null) instance = new Komunikacija();
        return instance;
    }
    
    public void posalji(TransferObject to) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        oos.writeObject(to);
    }
    
    public TransferObject procitaj() throws IOException, ClassNotFoundException{
    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
    return (TransferObject) ois.readObject();
    }
}
