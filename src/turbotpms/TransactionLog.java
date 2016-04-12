package turbotpms;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class TransactionLog 
{
    private final ArrayList<Transaction> data;
    
    public TransactionLog()
    {
        this.data = new ArrayList<>();
    }
    
    public void add(Transaction t)
    {
        this.data.add(t);
    }
    public Transaction lookup(int key)
    {
        //lookup a transaction in the log by key
        return new Purchase(null,0);
    }
    //No reason for transaction in log to be modified or deleted
}
