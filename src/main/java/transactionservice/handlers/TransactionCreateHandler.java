package transactionservice.handlers;

import transactionservice.AbstractRequestHandler;
import transactionservice.Answer;
import transactionservice.model.Model;

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

