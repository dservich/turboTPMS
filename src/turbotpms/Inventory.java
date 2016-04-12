package turbotpms;

import java.util.HashMap;
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
    public void addItem(InventoryItem i)
    {
        inventory.put(i.getUID(), i);
    }
    public int getNextId()
    {
        this.mapIdMarker++;
        return this.mapIdMarker-1; //who cares
    }
}
