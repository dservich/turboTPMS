package turbotpms;

/**
 * 
 * @author Administrator
 */
public final class ManagerController 
{
    private static SerializedDataManager theDataManager;
    private static AccountManager theAccountManager;
    private static InventoryManager theInventoryManager;
    private static TransactionManager theTransactionManager;
    private static DatabaseManager theDatabaseManager;
    private static String hello = "hello";
    
    private ManagerController()
    {
  
    }
    
    public static void initialize()
    {
        System.out.println("Launching Overseer");
        theDataManager = new SerializedDataManager();
        theAccountManager = new AccountManager();
        theInventoryManager = new InventoryManager();
        theTransactionManager = new TransactionManager();
        theDatabaseManager = new DatabaseManager();
    }
    
    public static AccountManager getAccountMananger()
    {
        return theAccountManager;
    }
    public static InventoryManager getInventoryManager()
    {
        return theInventoryManager;
    }
    public static TransactionManager getTransactionManager()
    {
        return theTransactionManager;
    }
    public static SerializedDataManager getDataManager()
    {
        return theDataManager;
    }
    public static DatabaseManager getDatabaseManager()
    {
        return theDatabaseManager;
    }
}
