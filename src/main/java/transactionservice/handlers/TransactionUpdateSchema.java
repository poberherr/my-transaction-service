package transactionservice.handlers;

/**
 * Created by paTimu on 1/14/2016.
 */
import lombok.Data;
import transactionservice.Validable;

import java.util.LinkedList;
import java.util.List;

@Data
class TransactionUpdateSchema implements Validable {
    private Double amount;
    private String type;
    private long parent_id;

    @Override
    // TODO: check that it cant be it's own parent
    public boolean isValid() {
        return  amount != null
                && type != null
                && !type.isEmpty();
                //&& parent_id != transactionId; // sadly can't be my own father -luke
    }

}
