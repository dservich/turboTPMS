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
        System.out.println("TurboTPMS is running!");
        //InterfaceController ic = new InterfaceController();
        //InventoryItem testItem = new InventoryItem();
        
        ManagerController.initialize();
        
        
        if(ManagerController.getTransactionManager() != null)
        {
            System.out.println("Manager constructed");
        }
        try 
        {
            Tester t = new Tester();
            TransactionServer srv = new TransactionServer(5001);
            srv.launch();
        } 
        catch (Exception ex) 
        {
            System.out.println("Server Init Error");
            System.out.println(ex.getMessage());
        }
    }
    
}
