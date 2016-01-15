package transactionservice.handlers;

/**
 * Created by paTimu on 1/15/2016.
 */

import transactionservice.AbstractRequestHandler;
import transactionservice.Answer;
import transactionservice.model.Model;

import java.util.Map;

public class TypeIndexHandler extends AbstractRequestHandler<EmptyPayload> {

    public TypeIndexHandler(Model model) {
        super(EmptyPayload.class, model);
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String,String> urlParams, boolean shouldReturnHtml) {
        String json = dataToJson(model.getAllTransactionTypes());
        return Answer.ok(json);
    }
}
