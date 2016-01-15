package transactionservice;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;
import static spark.Spark.port;

import transactionservice.handlers.TransactionCreateHandler;
import transactionservice.handlers.TransactionIndexHandler;
import transactionservice.handlers.TransactionSingleHandler;
import transactionservice.handlers.TransactionUpdateHandler;
import transactionservice.model.Model;
import transactionservice.store.StoreManager;

/**
 * Created by paTimu on 1/13/2016.
 */
public class RestfulHandler {

    private StoreManager trans = new StoreManager();

    public void listen () {

        Model model = new StoreManager();

        post("/transaction", new TransactionCreateHandler(model));
        get("/transaction", new TransactionIndexHandler(model));
        get("/transaction/:id", new TransactionSingleHandler(model));
        put("/transaction/:id", new TransactionUpdateHandler(model));


/*
        get("/transaction/:id", (request, response) -> {
            String id = request.params(":id");
            Transaction transaction = trans.getTransactionById(id);
            if (transaction != null) {
                response.status(200);
                response.type("application/json");
                return RestHelpers.dataToJson(transaction);
            } else {
                response.status(404); // 404 Not found
                return  "transactionservice.model.Transaction not found, please create first a transaction by making an empty post call" +
                        " to /transaction";
            }
        });

        put("/transaction/:id", (request, response) -> {
            String id = request.params(":id");
            Transaction transaction = trans.getTransactionById(id);

            // If the transaction is existing we try to deserialize
            if (transaction != null) {
                try {
                    // TODO: Move all direct transaction handling to Manager
                    ObjectMapper mapper = new ObjectMapper();
                    TransactionSchema creation = mapper.readValue(request.body(), TransactionSchema.class);
                    if (!creation.isValid(Long.parseLong(id))) {
                        response.status(HTTP_BAD_REQUEST);
                        return "Bad Request";
                    }
//                    boolean sucessfullUpdate =
                    trans.updateTransaction(id, creation.getAmount(), creation.getType(), creation.getParent_id());

                    response.status(200); // Proper status for update 201?
                    //response.type("application/json");
                    return "transactionservice.model.Transaction " + id + " has been updated";
                        //: "Error updating transactionservice.model.Transaction";
                } catch (JsonParseException jpe) {
                    response.status(HTTP_BAD_REQUEST);
                    return "Bad Request";
                }
            // If we try to update a non existing transaction
            } else {
                response.status(404); // 404 Not found
                return  "transactionservice.model.Transaction not found, please create first a transaction by making an empty post call" +
                        " to /transaction";
            }
        });
*/
        /* The question is if a transaction ever can be deleted.
           In my billing experience I vote for creating an inverse of the transaction

           Implement destroy call
         */
/*
        get("/types", (request, response) -> {
            if (trans.getTransactionsByType(name) == null) {
                return "No transaction have been made yet";
            } else {
                return RestHelpers.dataToJson(trans.getAllTransactionTypes());
            }
        });

        get("/types/:name", (request, response) -> {
            String name = request.params(":name");
            if (trans.getTransactionsByType(name) == null) {
                return "No transaction of this type has been made yet";
            } else return trans.getTransactionsByType(name);
        });




//        get("/sum/:id", (request, response) -> {
//            String id = request.params(":id");
//            transactionservice.model.Transaction transaction = transactions.get(id);
//            if (transaction != null) {
//                response.status(200);
//                //response.type("application/json");
//                // woahhhh so hackerish :/
//                //return "{\"sum\" :" + transaction.getAmount() +"}";
//
//                System.out.println(getAllChildren(transaction));
//                return transactionservice.RestHelpers.dataToJson(getAllChildren(transaction));
//            } else {
//                response.status(404); // 404 Not found
//                return  "transactionservice.model.Transaction not found";
//            }
//        });


*/

    }
}
