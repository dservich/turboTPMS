package turbotpms;

import java.util.UUID;

/**
 *
 * @author Administrator
 */
public class Account 
{
    private final UUID accountNumber;
    private double balance;
    private final String holder;
    private boolean lock;
    
    public Account (String name, double startingBalance)
    {
        holder = name;
        balance = startingBalance;
        accountNumber = UUID.randomUUID();
        lock = false;
    }
    public String getName()
    {
        return holder;
    }
    public UUID getID()
    {
        return accountNumber;
    }
    public double getBalance()
    {
        return balance;
    }
    public void deposit(double amt)
    {
        balance += amt;
    }
    public void withdraw(double amt)
    {
        balance -= amt;
    }
    public boolean getLock()
    {
        return lock;
    }
    public void setLock(boolean b)      
    {
        lock = b;
    }
            
}
