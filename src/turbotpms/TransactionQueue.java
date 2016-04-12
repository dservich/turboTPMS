package turbotpms;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class TransactionQueue
{
    private ArrayList<Transaction> transactionQueue;
    
    public TransactionQueue()
    {
        this.transactionQueue = new ArrayList<>();
    }
    
    public void add(Transaction t)
    {
        this.transactionQueue.add(t);
    }
    public Transaction peek()
    {
        return this.transactionQueue.get(0);
    }
    public Transaction remove()
    {
        Transaction t = this.transactionQueue.get(0);
        this.transactionQueue.remove(0);
        return t;
    }
    public int length()
    {
        return this.transactionQueue.size();
    }
}
