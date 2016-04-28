/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turbotpms;

/**
 *
 * @author delan
 */
public class Tester 
{
    public Tester()
    {
        this.runTests();
    }
    private void runTests()
    {
        
        //ADD SOME THINGS TO THE INVENTORY
        InventoryItem i = new InventoryItem("Bolt",3000,1.50);
        InventoryItem j = new InventoryItem("Nut",3000,0.50);
        InventoryItem k = new InventoryItem("Screw",3000,2.00);
        
        ManagerController.getInventoryManager().addItemToInventory(i);
        ManagerController.getInventoryManager().addItemToInventory(j);
        ManagerController.getInventoryManager().addItemToInventory(k);
        
        //QUEUE UP SOME TRANSACTIONS
        /*
        for(int x=0;x<1000;x++)
        {
            Transaction t = new Purchase(i,20);
            ManagerController.getTransactionManager().queueTransaction(t);
            
            t = new Purchase(j,50);
            ManagerController.getTransactionManager().queueTransaction(t);
            
            t = new Purchase(k,80);
            ManagerController.getTransactionManager().queueTransaction(t);
        }
        */
        
    }
}
