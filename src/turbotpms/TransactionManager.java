package turbotpms;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class TransactionManager 
{
    private final TransactionQueue theTransactionQueue;
    private ArrayList<TransactionProcessor> theTransactionProcessorList;
    private final TransactionLog theTransactionLog;
    
    public TransactionManager()
    {
        this.theTransactionQueue = new TransactionQueue();
        this.theTransactionProcessorList = new ArrayList<>();
        this.theTransactionLog = ManagerController.getDataManager().readTransactionLog();
        
        theTransactionProcessorList.add(new TransactionProcessor(this));
        theTransactionProcessorList.add(new TransactionProcessor(this));
        theTransactionProcessorList.add(new TransactionProcessor(this));
        
        launchProcessors();
    }
    
    private void launchProcessors()
    {
        for(TransactionProcessor tp : theTransactionProcessorList)
        {
            tp.start();
        }
    }
    
    public void queueTransaction(Transaction t)
    {
        this.theTransactionQueue.add(t);
        this.theTransactionLog.add(t);
    }
    public int getTransactionStatus()
    {
        //implement transaction lookup by ID
        return -1;
    }
    public ArrayList<TransactionProcessor> getProcessorList()
    {
        return this.theTransactionProcessorList;
    }
    public TransactionLog getTransactionLog()
    {
        return this.theTransactionLog;
    }
    public int getQueueLength()
    {
        return this.theTransactionQueue.length();
    }
    public Transaction fetchNextTransaction()
    {
        return this.theTransactionQueue.remove();
    }
    public void dump()
    {
        ManagerController.getDataManager().writeTransactionLog();
    }
}
