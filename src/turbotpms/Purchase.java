package turbotpms;

/**
 *
 * @author Administrator
 */
public class Purchase extends Transaction 
{
    private final InventoryItem purchasedItem;
    private final int quantity;
    private final double total;
    //currently only supports one type of item per purchase
    
    public Purchase(InventoryItem item, int q) 
    {
        super();
        this.purchasedItem = item;
        this.quantity = q;
        this.total = item.getPrice() * (double)q;
    }
    public InventoryItem getItem()
    {
        return this.purchasedItem;
    }
    public int getQuantity()
    {
        return this.quantity;
    }
    public double getTotal()
    {
        return this.total;
    }
}
