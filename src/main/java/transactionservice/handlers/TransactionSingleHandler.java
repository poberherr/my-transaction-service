package transactionservice.handlers;

/**
 * Created by paTimu on 1/15/2016.
 */
import java.util.Map;
import java.util.Optional;

import transactionservice.AbstractRequestHandler;
import transactionservice.Answer;
import transactionservice.model.Model;
import transactionservice.model.Transaction;

public class TransactionSingleHandler extends AbstractRequestHandler<EmptyPayload> {

    public TransactionSingleHandler(Model model) {
        super(EmptyPayload.class, model);
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String,String> urlParams, boolean shouldReturnHtml) {
        if (!urlParams.containsKey(":id")) {
            throw new IllegalArgumentException();
        }
        String id;
        try {
            id = urlParams.get(":id");
        } catch (IllegalArgumentException e) {
            return new Answer(404);
        }

        Transaction transaction = model.getTransactionById(id);
        if (transaction == null) {
            return new Answer(404);
        }
        return Answer.ok(dataToJson(transaction));
    }
}
