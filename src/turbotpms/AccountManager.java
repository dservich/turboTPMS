package turbotpms;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *  THREAD POOL EXECUTOR
 * Java late objects ch 20
 * @author Administrator
 */
public class AccountManager 
{
    private Map<UUID,Account> accounts;
    Account demo;
    
    public AccountManager()
    {
        accounts = new HashMap<>();
        demo = new Account("test",0.0); //test code
    }
    public void addAccount(Account a)
    {
        accounts.put(a.getID(), a);
    }
    public Account getAccount(UUID id)
    {
        return accounts.get(id);
    }
    public void updateAccount(Account a, UUID id)
    {
        accounts.replace(id, a);
    }
    public void removeAccount(UUID id)
    {
        accounts.remove(id);
    }
    public Account getDemo()
    {
        return demo;
    }
}
