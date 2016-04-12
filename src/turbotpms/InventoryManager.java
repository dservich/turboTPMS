package turbotpms;

import java.util.UUID;

/**
 *
 * @author Administrator
 */
public class InventoryManager 
{
    private final Inventory i;
    
    public InventoryManager()
    {
        this.i = ManagerController.getDataManager().readInventory();
    }
    
    public Inventory getInventory()
    {
        return this.i;
    }
    public int numberOnHand(UUID uid)
    {
        return i.lookup(uid).getNumberOnHand();
    }
    public void sellQuantity(UUID u,int q) //NOT A GUARDED ACTION
    {
        i.lookup(u).sellQuantity(q);
    }
    public void stockQuantity(UUID u,int q)
    {
        i.lookup(u).addQuantity(q);
    }
    public void addItemToInventory(InventoryItem i)
    {
        this.i.addItem(i);
    }
    public void dump()
    {
        ManagerController.getDataManager().writeInventory();
    }
}
