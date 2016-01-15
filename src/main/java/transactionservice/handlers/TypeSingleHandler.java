package transactionservice.handlers;

/**
 * Created by paTimu on 1/15/2016.
 */
import java.util.List;
import java.util.Map;

import transactionservice.AbstractRequestHandler;
import transactionservice.Answer;
import transactionservice.model.Model;
import transactionservice.model.Transaction;

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
