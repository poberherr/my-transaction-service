package me.poberherr.transactionservice.handlers;

import me.poberherr.transactionservice.AbstractRequestHandler;
import me.poberherr.transactionservice.Answer;
import me.poberherr.transactionservice.model.Model;
import me.poberherr.transactionservice.model.Transaction;

import java.util.Map;

public class TransactionSingleHandler extends AbstractRequestHandler<EmptyPayload> {

    public TransactionSingleHandler(Model model) {
        super(EmptyPayload.class, model);
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
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
