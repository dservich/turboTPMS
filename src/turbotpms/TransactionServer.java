/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turbotpms;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author delan
 */
public class TransactionServer extends Thread
{
    ServerSocket socket;
    ArrayList<Socket> clients;
    
    public TransactionServer(int port) throws IOException
    {
        this.socket = new ServerSocket(port);
        this.clients = new ArrayList<>();
    }
    public void launch()
    {
        try
        {
            //Thread srv = new TransactionServer(5001);
            this.start();
        }
        catch(Exception e)
        {
            System.out.println("Server Launch Error");
            System.out.println(e.getMessage());
        }
    }
    
    public void getConnections()
    {
        while(true)
        {
            try
            {
		Socket client = socket.accept();
		this.clients.add(client);
                System.out.println("CLIENT HOOKED");
		DataInputStream in = new DataInputStream(client.getInputStream());
		Thread serverMessageHandler = new TransactionServerHandler(clients.size()-1, this.clients);
		serverMessageHandler.start();
            }
            catch(Exception e)
            {
                System.out.println("server connection exception");
                return;
            }
        }       
    }
    public void run()
    {
        this.getConnections();
        return;
    }
}
