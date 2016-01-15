package me.poberherr.transactionservice.handlers;

import java.util.List;
import java.util.Map;

import me.poberherr.transactionservice.AbstractRequestHandler;
import me.poberherr.transactionservice.Answer;
import me.poberherr.transactionservice.model.Model;

public class TypeSingleHandler extends AbstractRequestHandler<EmptyPayload> {

    public TypeSingleHandler(Model model) {
        super(EmptyPayload.class, model);
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String,String> urlParams, boolean shouldReturnHtml) {
        if (!urlParams.containsKey(":name")) {
            throw new IllegalArgumentException();
        }
        String id;
        try {
            id = urlParams.get(":name");
        } catch (IllegalArgumentException e) {
            return new Answer(404);
        }

        List transactionTypes = model.getTransactionsByType(id);
        if (transactionTypes == null) {
            return new Answer(404);
        }
        return Answer.ok(dataToJson(transactionTypes));
    }
}
