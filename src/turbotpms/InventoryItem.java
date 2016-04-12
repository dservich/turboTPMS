package turbotpms;

import java.util.UUID;

/**
 *
 * @author Administrator
 */
public class InventoryItem 
{
    private final UUID UID;
    private final String itemName;
    private int numberOnHand;
    private double price;
    
    public InventoryItem(String n,int q, int p)
    {
        this.UID = UUID.randomUUID();
        this.itemName = n;
        this.numberOnHand = q;
        this.price = p;
    }
    public void sellQuantity(int n)
    {
        this.numberOnHand -= n;
    }
    public void addQuantity(int n)
    {
        this.numberOnHand += n;
    }
    public int getNumberOnHand()
    {
        return this.numberOnHand;
    }
    public UUID getUID()
    {
        return this.UID;
    }
    public double getPrice()
    {
        return this.price;
    }
}
