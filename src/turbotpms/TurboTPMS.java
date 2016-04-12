package turbotpms;

/**
 *
 * @author Administrator
 */
public class TurboTPMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        //InterfaceController ic = new InterfaceController();
        //InventoryItem testItem = new InventoryItem();
        
        ManagerController.initialize();
        
        
        if(ManagerController.getTransactionManager() != null)
        {
            System.out.println("Manager constructed");
        }
    }
    
}
