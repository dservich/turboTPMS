package turbotpms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Administrator
 */
public class Inventory 
{
    private Map <UUID,InventoryItem> inventory;
    private int mapIdMarker;
    
    public Inventory()
    {
        this.inventory = new HashMap<>();
        this.mapIdMarker = 0;
    }
    
    public InventoryItem lookup(UUID id)
    {
        return inventory.get(id);
    }
    public InventoryItem lookupByName(String name)
    {
        System.out.println("Looking up " +name);
        Iterator it = inventory.entrySet().iterator();
        
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry)it.next();
            InventoryItem i = (InventoryItem)pair.getValue();
            System.out.println("Checking against "+i.getName());
            if(i.getName().equals(name))
            {
                System.out.println("Found item");
                return i;
            }
        }
        return null;
    }
    public void addItem(InventoryItem i)
    {
        inventory.put(i.getUID(), i);
    }
    public int getNextId()
    {
        this.mapIdMarker++;
        return this.mapIdMarker-1; //who cares
    }
    public ArrayList<InventoryItem> getInventory()
    {
        ArrayList<InventoryItem> data = new ArrayList<>();
        
        Iterator it = inventory.entrySet().iterator();
        
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry)it.next();
            data.add((InventoryItem)pair.getValue());
        }
        return data;
    }
}
