package me.poberherr.transactionservice.handlers;

import lombok.Data;
import me.poberherr.transactionservice.Validable;

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
