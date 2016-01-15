package transactionservice.handlers;

import spark.Request;
import spark.Response;
import transactionservice.AbstractRequestHandler;
import transactionservice.Answer;
import transactionservice.model.Model;
import transactionservice.model.Transaction;

import java.util.Map;
import java.util.Optional;

/**
 * Created by paTimu on 1/14/2016.
 */
public class TransactionUpdateHandler extends AbstractRequestHandler<TransactionUpdateSchema> {

    private Model model;

    public TransactionUpdateHandler(Model model) {
        super(TransactionUpdateSchema.class, model);
        this.model = model;
    }

    @Override
    protected Answer processImpl(TransactionUpdateSchema value, Map<String, String> urlParams, boolean shouldReturnHtml) {
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

        long parent_id;
        if (value.getParent_id() != 0){
            if (transaction.getId() == value.getParent_id()){
                return new Answer(403, "Transaction cant be its own parent");
            }
            parent_id = value.getParent_id();
        } else parent_id = transaction.getParent_id();

        model.updateTransaction(id, value.getAmount(), value.getType(), parent_id);
        return new Answer(200);
    }
}



//        Transaction transaction = trans.getTransactionById(id);

//        // If the transaction is existing we try to deserialize
//        if (transaction != null) {
//            try {
//                // TODO: Move all direct transaction handling to Manager
//                ObjectMapper mapper = new ObjectMapper();
//                TransactionSchema creation = mapper.readValue(request.body(), TransactionSchema.class);
//                if (!creation.isValid(Long.parseLong(id))) {
//                    response.status(HTTP_BAD_REQUEST);
//                    return "Bad Request";
//                }
////                    boolean sucessfullUpdate =
//                trans.updateTransaction(id, creation.getAmount(), creation.getType(), creation.getParent_id());
//
//                response.status(200); // Proper status for update 201?
//                //response.type("application/json");
//                return "Transaction " + id + " has been updated";
//                //: "Error updating Transaction";
//            } catch (JsonParseException jpe) {
//                response.status(HTTP_BAD_REQUEST);
//                return "Bad Request";
//            }
//            // If we try to update a non existing transaction
//        } else {
//            response.status(404); // 404 Not found
//            return  "Transaction not found, please create first a transaction by making an empty post call" +
//                    " to /transaction";
//        }
