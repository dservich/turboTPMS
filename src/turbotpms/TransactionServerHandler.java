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
		System.out.println("Index of this ServerMessageHandler in clientList: " + clientNumber);
	}
	
	public void listenForTransactions()
	{
		boolean keepGoing = true;
		System.out.println("clientCount in listenForMessage: " + this.clientList.size());
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
                                    String[] data = message.split("|");
                                    
                                    InventoryItem i = ManagerController.getInventoryManager().getInventory().lookupByName(data[1]);
                                    
                                    if(data[0].equals("P") && i != null)
                                    {
                                        Purchase p = new Purchase(i,Integer.parseInt(data[2]));
                                    }
                                    else if (data[0].equals("R") && i != null)
                                    {
                                        Return r = new Return(i,Integer.parseInt(data[2]));
                                    }
                                    message = "";
				}
			}
			catch(IOException e)
			{
				System.out.println("IOException in ServerMessageHandler listenForMessage()");
			}
		}
	}
	
	public void sendInventory()
	{
            String data = "";
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
		this.listenForTransactions();
		return;
	}
}