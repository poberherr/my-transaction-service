import lombok.Data;

/**
 * Created by paTimu on 1/13/2016.
 */
@Data
public class TransactionSchema {
    private Double amount;
    private String type;
    private long parent_id;

    public boolean isValid(long transactionId) {
        // A custom error code & message would be nice.
        return  amount != null
                && type != null
                && !type.isEmpty()
                && parent_id != transactionId; // sadly can't be my own father -luke
    }
}

