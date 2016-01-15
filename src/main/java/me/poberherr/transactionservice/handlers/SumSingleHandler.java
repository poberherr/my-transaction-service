package me.poberherr.transactionservice.handlers;

import me.poberherr.transactionservice.AbstractRequestHandler;
import me.poberherr.transactionservice.Answer;
import me.poberherr.transactionservice.model.Model;

import java.util.List;
import java.util.Map;

public class SumSingleHandler extends AbstractRequestHandler<EmptyPayload> {

    public SumSingleHandler(Model model) {
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

        double amount = model.calculateSumIncludingChildren(Long.valueOf(id));
        return new Answer(201, "{\"sum\":" + amount + "}");
//        return Answer.ok(dataToJson(transactionTypes));
    }
}
