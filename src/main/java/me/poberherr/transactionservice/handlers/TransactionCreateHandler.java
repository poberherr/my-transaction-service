package me.poberherr.transactionservice.handlers;

import me.poberherr.transactionservice.AbstractRequestHandler;
import me.poberherr.transactionservice.Answer;
import me.poberherr.transactionservice.model.Model;

import java.util.Map;

public class TransactionCreateHandler extends AbstractRequestHandler<EmptyPayload> {

    private Model model;

    public TransactionCreateHandler(Model model) {
        super(EmptyPayload.class, model);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String,String> urlParams, boolean shouldReturnHtml) {
        long id = model.createTransaction();
        return new Answer(201, Long.toString(id));
    }
}

