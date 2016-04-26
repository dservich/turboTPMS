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
            Thread srv = new TransactionServer(9898);
            srv.start();
        }
        catch(Exception e)
        {
            System.out.println("Problem");
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
                
		DataInputStream in = new DataInputStream(client.getInputStream());
		String message = in.readUTF();
		System.out.println("Message from " + client.getRemoteSocketAddress() + ": " + message);
		DataOutputStream out = new DataOutputStream(client.getOutputStream());
		out.writeUTF("CONNECTED");
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
