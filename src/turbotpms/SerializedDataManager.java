package turbotpms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Administrator
 */
public class SerializedDataManager 
{
    public void writeTransactionLog()
    {
        try
	{
            try (FileOutputStream fOut = new FileOutputStream("tLog.dat"); 
                    ObjectOutputStream oOut = new ObjectOutputStream(fOut)) 
            {
                oOut.writeObject(ManagerController.getTransactionManager().getTransactionLog());
            }
	}
	catch(Exception e)
        {
            
        }
    }
    public TransactionLog readTransactionLog()
    {
        TransactionLog t = new TransactionLog();
        File f = new File("tLog.dat");
        if(!f.exists())
        {
            try
            {
            f.createNewFile();
            }
            catch(Exception e)
            {
                
            }
        }
        try (FileInputStream fIn = new FileInputStream(f); 
                ObjectInputStream oIn = new ObjectInputStream(fIn)) 
        {
            t =  (TransactionLog)oIn.readObject();
        }
        catch(Exception e)
        {
            
        }
        return t;
    }
    
    public void writeInventory()
    {
        try
	{
            try (FileOutputStream fOut = new FileOutputStream("inv.dat"); 
                    ObjectOutputStream oOut = new ObjectOutputStream(fOut)) 
            {
                oOut.writeObject(ManagerController.getInventoryManager().getInventory());
            }
	}
	catch(Exception e)
        {
            
        }
    }
    public Inventory readInventory()
    {
        Inventory i = new Inventory();
        File f = new File("inv.dat");
        if(!f.exists())
        {
            try
            {
            f.createNewFile();
            }
            catch(Exception e)
            {
                
            }
        }
        try (FileInputStream fIn = new FileInputStream(f); 
                ObjectInputStream oIn = new ObjectInputStream(fIn)) 
        {
            i =  (Inventory)oIn.readObject();
        }
        catch(Exception e)
        {
            
        }
        return i;
    }
}
