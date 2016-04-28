package turbotpms;

import java.io.*;
import java.net.*;
import java.util.*;

public class TransactionServerHandler extends Thread
{
	ArrayList<Socket> clientList;
	Socket clientConnection;
	int clientNumber;
	String message = "";
	public TransactionServerHandler(int clientNum, ArrayList<Socket> cList)
	{
		this.clientList = cList;
		this.clientConnection = this.clientList.get(clientNum);
		this.clientNumber = this.clientList.indexOf(clientConnection);
	}
	
	public void listenForTransactions()
	{
		boolean keepGoing = true;
		while(keepGoing)
		{
			try
			{
				DataInputStream dataInputStream = new DataInputStream(this.clientConnection.getInputStream());
				message = dataInputStream.readUTF();
                                
				if(message.equals("quit"))
				{
					killConnection();
					return;
				}
				if(!message.equals(""))
				{
                                    ArrayList<String> data = new ArrayList<>();
                                    
                                    StringTokenizer tok = new StringTokenizer(message,"|");
                                    
                                    while(tok.hasMoreElements())
                                    {
                                        data.add((String)tok.nextElement());
                                    }
                                    
                                    InventoryItem i = ManagerController.getInventoryManager().getInventory().lookupByName(data.get(1));
                                    
                                    System.out.println("Found "+i.getName());
                                    
                                    if(data.get(0).equals("P") && i != null)
                                    {
                                        System.out.println("Transaction processed");
                                        Purchase p = new Purchase(i,Integer.parseInt(data.get(2)));
                                        ManagerController.getTransactionManager().queueTransaction(p);
                                    }
                                    else if (data.get(0).equals("R") && i != null)
                                    {
                                        Return r = new Return(i,Integer.parseInt(data.get(2)));
                                        ManagerController.getTransactionManager().queueTransaction(r);
                                    }
                                    message = "";
				}
			}
			catch(IOException e)
			{
				System.out.println("IOException in ServerMessageHandler transactions");
                                e.printStackTrace();
			}
		}
	}
	
	public void sendInventory()
	{
            String data = "";
            System.out.println("Sending Data here");
            for(InventoryItem i : ManagerController.getInventoryManager().getInventory().getInventory())
            {
                data+=i.getName() + "|" + Integer.toString(i.getNumberOnHand())+"|"+Double.toString(i.getPrice())+"|";
            }
		try
		{
			for(int k = 0; k < clientList.size(); k++)
			{
				DataOutputStream dataOutToClient = new DataOutputStream(this.clientList.get(k).getOutputStream());
				dataOutToClient.writeUTF(data);
			}
		}
		catch(IOException e)
		{
			System.out.println("Problem Sending Inventory");
		}
		return;
	}
	
        public void sendResponse(int status, Socket client) //1 = success, 2 = failed
        {
            try //1 = success, 2 = failed
            {
                DataOutputStream dataOutToClient = new DataOutputStream(client.getOutputStream());
                dataOutToClient.writeUTF(Integer.toString(status));
            } catch (IOException ex) {
                System.out.println("problem sending response");
            }
        }
	public void killConnection()
	{
		try
		{
			this.clientConnection.close();
			this.clientList.remove(this.clientList.indexOf(clientConnection));
		}
		catch(IOException e)
		{
			System.out.println("IOException in ServerMessageHandler killConnection()");
		}
		return;
		
	}
	
	public void run()
	{
            System.out.println("Connection Established");
            this.sendInventory();
            this.listenForTransactions();
            return;
	}
}