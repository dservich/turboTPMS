package turbotpms;

/**
 *
 * @author Administrator
 */
public class TransactionProcessor implements Runnable
{
    private final TransactionManager parentManager;
    private Transaction currentTransaction;
    private Thread t;
    
    public TransactionProcessor(TransactionManager parent)
    {
        System.out.println("Processor created");
        this.parentManager = parent;
        this.currentTransaction = null;
    }
    
    private void queueTransaction()
    {
        synchronized(this.parentManager)
        {
            if(this.parentManager.getQueueLength() > 0)
            {
                this.currentTransaction = this.parentManager.fetchNextTransaction();
                //Thread.yield();
                synchronized(ManagerController.getAccountMananger().getDemo())
                {
                    if(!ManagerController.getAccountMananger().getDemo().getLock())
                    {
                        ManagerController.getAccountMananger().getDemo().setLock(true);
                        tick();
                        ManagerController.getAccountMananger().getDemo().setLock(false);
                    }
                }
            }
        }
    }
    private void tick()
    {
        System.out.println("Processing transaction");
            currentTransaction.setStatus(1);
            
            if(currentTransaction.getType() == 0)//order
            {
                boolean safeFlag = true;
                //TO-DO
            }
            else if(currentTransaction.getType() == 1)//purchase
            {
                Purchase p = (Purchase)currentTransaction;
                if((ManagerController.getInventoryManager().getInventory().lookup(p.getItem().getUID())) != null)//ITEM CAN BE FOUND
                {
                    ManagerController.getInventoryManager().sellQuantity(p.getItem().getUID(), p.getQuantity());
                    ManagerController.getAccountMananger().getDemo().deposit(p.getTotal());
                    currentTransaction.setProcessTimestamp();
                    currentTransaction.setStatus(2);
                    System.out.println("Purchase complete, sold "+p.getQuantity()+" of item "+p.getItem().getName());
                    System.out.println("Final balance: $"+ManagerController.getAccountMananger().getDemo().getBalance());
                    ManagerController.getDatabaseManager().addTransactionToRecord("purchase",p.getItem().getName(), Integer.toString(p.getQuantity()), Double.toString(p.getTotal()));
                }
                
                
            }
            else if (currentTransaction.getType() == 2)//retrun
            {
                Return r = (Return)currentTransaction;
                Purchase p = r.getReturnedPurchase();
                if((ManagerController.getInventoryManager().getInventory().lookup(r.getReturnedPurchase().getItem().getUID())) != null)//ITEM CAN BE FOUND
                {
                    ManagerController.getInventoryManager().stockQuantity(p.getID(), p.getQuantity());
                    ManagerController.getAccountMananger().getDemo().withdraw(r.getReturnedPurchase().getTotal());
                    currentTransaction.setProcessTimestamp();
                    currentTransaction.setStatus(2);
                    System.out.println("Return complete, took "+p.getQuantity()+" of item "+p.getItem().getName());
                    System.out.println("Final balance: $"+ManagerController.getAccountMananger().getDemo().getBalance());
                    ManagerController.getDatabaseManager().addTransactionToRecord("return",p.getItem().getName(), Integer.toString(p.getQuantity()), Double.toString(p.getTotal()));
                }
                
            }
            else//probably error
            {
                
            }
        }
    
    public void start ()
   {
      System.out.println("Launching processor");
      if (t == null)
      {
         t = new Thread (this);
         t.start ();
      }
   }

    @Override
    public void run() 
    {
        while(true)
        {
            queueTransaction();
            Thread.yield();
        }
    }
}
