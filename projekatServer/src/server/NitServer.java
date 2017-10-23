/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vladan
 */
public class NitServer extends Thread {
    private ServerSocket ss;
    private List<Socket> ls;
    private boolean kraj;

    public NitServer() {
    ls = new ArrayList<>();
    start();
    }

    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);
            kraj = false;
            while(!kraj){
                Socket soket = ss.accept();
                ls.add(soket);
                new NitKlijent(soket);
                
            }
        } catch (Exception e) {
        }
    }
    
    public boolean zatvoreniSoketi(){
        for(Socket socket : ls){
            if(!socket.isClosed()) return false;
        }
        return true;
    }
    
    public void zaustaviServer() throws Exception{
        if(!zatvoreniSoketi())
            throw new Exception("nisu zatvoreni svi soketi!");
        kraj = true;
        ss.close();
    
    }
    
}
