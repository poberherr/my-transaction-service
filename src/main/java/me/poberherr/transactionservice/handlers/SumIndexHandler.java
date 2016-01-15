package me.poberherr.transactionservice.handlers;

import me.poberherr.transactionservice.AbstractRequestHandler;
import me.poberherr.transactionservice.Answer;
import me.poberherr.transactionservice.model.Model;

import java.util.Map;

public class SumIndexHandler extends AbstractRequestHandler<EmptyPayload> {

    public SumIndexHandler(Model model) {
        super(EmptyPayload.class, model);
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
        double amount = model.calculateSumOfAllTransactions();
        return new Answer(201, "{\"sum\":" + amount + "}");
    }
}
