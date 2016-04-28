package turbotpms;

/**
 *
 * @author Administrator
 */
public class Return extends Transaction 
{
    private final Purchase returnedPurchase; //marking purchase to be returned
    private final InventoryItem itemToReturn;
    private final int returnQuantity;
    
    public Return(Purchase p) 
    {
        super(2);
        this.returnedPurchase = p;
        this.itemToReturn = null;
        this.returnQuantity = -1;
    }
    
    public Return(InventoryItem item, int quantity)
    {
        super(2);
        this.itemToReturn = item;
        this.returnQuantity = quantity;
        this.returnedPurchase = null;
    }
    
    public Purchase getReturnedPurchase()
    {
        return this.returnedPurchase;
    }
}
