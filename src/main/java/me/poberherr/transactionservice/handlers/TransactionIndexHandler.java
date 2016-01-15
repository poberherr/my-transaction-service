package me.poberherr.transactionservice.handlers;

import me.poberherr.transactionservice.AbstractRequestHandler;
import me.poberherr.transactionservice.Answer;
import me.poberherr.transactionservice.model.Model;

import java.util.Collection;
import java.util.Map;

public class TransactionIndexHandler extends AbstractRequestHandler<EmptyPayload> {

    public TransactionIndexHandler(Model model) {
        super(EmptyPayload.class, model);
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
        Map transactions = model.getAllTransactions();
        if (!transactions.isEmpty()) {
            String json = dataToJson(transactions);
            return Answer.ok(json);
        } else {
            return Answer.empty();
        }
    }
}
