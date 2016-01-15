package me.poberherr.transactionservice.handlers;

import me.poberherr.transactionservice.AbstractRequestHandler;
import me.poberherr.transactionservice.Answer;
import me.poberherr.transactionservice.model.Model;

import java.util.Map;

public class TransactionIndexHandler extends AbstractRequestHandler<EmptyPayload> {

    public TransactionIndexHandler(Model model) {
        super(EmptyPayload.class, model);
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String,String> urlParams, boolean shouldReturnHtml) {
        String json = dataToJson(model.getAllTransactions());
        return Answer.ok(json);
    }
}
