package me.poberherr.transactionservice.handlers;

import me.poberherr.transactionservice.Validable;

public class EmptyPayload implements Validable {
    @Override
    public boolean isValid() {
        return true;
    }
}
