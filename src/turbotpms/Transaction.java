package turbotpms;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Administrator
 */
public abstract class Transaction 
{
    private final int TRANSACTION_ERROR = -1;
    private final int TRANSACTION_ORDER = 0;
    private final int TRANSACTION_PURCHASE = 1;
    private final int TRANSACTION_RETURN = 2;
    
    /*
    STANDARDIZED STATUS CODES
    -1 = ERROR
    0  = in queue
    1  = being processed
    2  = complete
    */
    private int status;
    private final Timestamp creationTimestamp;
    private Timestamp processTimestamp;
    private int type;
    private final UUID ID;
    //private final int quantity;
    
    public Transaction()
    {
        this.creationTimestamp = new Timestamp(new Date().getTime());
        this.ID = UUID.randomUUID();
        this.status = -1;
    }
    public void setProcessTimestamp()
    {
        this.processTimestamp = new Timestamp(new Date().getTime());
    }
    public UUID getID()
    {
        return this.ID;
    }
    public int getType()
    {
        return this.type;
    }
    public int getStatis()
    {
        return this.status;
    }
    public void setStatus(int i)
    {
        this.status = i;
    }
}
