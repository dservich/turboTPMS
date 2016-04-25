package turbotpms;

/**
 *
 * @author Administrator
 */
public class Return extends Transaction 
{
    private final Purchase returnedPurchase; //marking purchase to be returned
    
    public Return(Purchase p) 
    {
        super(2);
        this.returnedPurchase = p;
    }
    
    public Purchase getReturnedPurchase()
    {
        return this.returnedPurchase;
    }
}
