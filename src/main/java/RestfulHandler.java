import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;

import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.get;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/**
 * Created by paTimu on 1/13/2016.
 */
public class RestfulHandler {

    private static final int HTTP_BAD_REQUEST = 400;
    private TransactionManager trans = new TransactionManager();


    public void listen () {

        post("/transaction", (request, response) -> {
            long id = trans.createTransaction();
            response.status(201); // 201 Created
            response.type("application/json");
            return id;
        });

        get("/transaction", (request, response) -> {
            response.status(200);
            response.type("application/json");
            return RestHelpers.dataToJson(trans.getAllTransactions());
        });

        get("/transaction/:id", (request, response) -> {
            String id = request.params(":id");
            Transaction transaction = trans.getTransactionById(id);
            if (transaction != null) {
                response.status(200);
                response.type("application/json");
                return RestHelpers.dataToJson(transaction);
            } else {
                response.status(404); // 404 Not found
                return  "Transaction not found, please create first a transaction by making an empty post call" +
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
                        return "";
                    }
                    transaction.setAmount(creation.getAmount());
                    transaction.setType(creation.getType());
                    transaction.setParent_id(creation.getParent_id());
                    response.status(200); // Proper status for update 201?
                    //response.type("application/json");
                    return "Transaction " + id + " has been updated";
                } catch (JsonParseException jpe) {
                    response.status(HTTP_BAD_REQUEST);
                    return "";
                }
            // If we try to update a non existing transaction
            } else {
                response.status(404); // 404 Not found
                return  "Transaction not found, please create first a transaction by making an empty post call" +
                        " to /transaction";
            }
        });

        /* The question is if a transaction ever can be deleted.
           In my billing experience I vote for creating an inverse of the transaction

           Implement destroy call
         */

//        get("/sum/:id", (request, response) -> {
//            String id = request.params(":id");
//            Transaction transaction = transactions.get(id);
//            if (transaction != null) {
//                response.status(200);
//                //response.type("application/json");
//                // woahhhh so hackerish :/
//                //return "{\"sum\" :" + transaction.getAmount() +"}";
//
//                System.out.println(getAllChildren(transaction));
//                return RestHelpers.dataToJson(getAllChildren(transaction));
//            } else {
//                response.status(404); // 404 Not found
//                return  "Transaction not found";
//            }
//        });


    }
}
