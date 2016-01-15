package transactionservice.handlers;

import transactionservice.Validable;

/**
 * Created by paTimu on 1/14/2016.
 */

public class EmptyPayload implements Validable {
    @Override
    public boolean isValid() {
        return true;
    }
}
