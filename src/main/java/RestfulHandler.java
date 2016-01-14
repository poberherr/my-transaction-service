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

    /**
     * Map holding the transactions
     */
    private Map<String, Transaction> transactions = new HashMap<String, Transaction>();
    private Map<String, List> transactionTypes = new HashMap<String, List>();
    private static int nextId = 1;



    public static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e){
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }

    public Object getAllTransactions(){
        return transactions.keySet().stream().sorted().map((id) -> transactions.get(id)).collect(Collectors.toList());
    }


    public void listen () {

        post("/transaction", (request, response) -> {
            Transaction transaction = new Transaction(nextId);
            transactions.put(String.valueOf(nextId), transaction);
            nextId++;

            response.status(201); // 201 Created
            response.type("application/json");
            return transaction.getId();
        });

        get("/transaction", (request, response) -> {
            response.status(200);
            response.type("application/json");
            return dataToJson(getAllTransactions());
        });

        get("/transaction/:id", (request, response) -> {
            String id = request.params(":id");
            Transaction transaction = transactions.get(id);
            if (transaction != null) {
                response.status(200);
                response.type("application/json");
                return dataToJson(transaction);
            } else {
                response.status(404); // 404 Not found
                return  "Transaction not found, please create first a transaction by making an empty post call" +
                        " to /transaction";
            }
        });

        put("/transaction/:id", (request, response) -> {
            String id = request.params(":id");
            Transaction transaction = transactions.get(id);

            // If the transaction is existing we try to deserialize
            if (transaction != null) {
                try {
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

        get("/sum/:id", (request, response) -> {
            String id = request.params(":id");
            Transaction transaction = transactions.get(id);
            if (transaction != null) {
                response.status(200);
                //response.type("application/json");
                // woahhhh so hackerish :/
                //return "{\"sum\" :" + transaction.getAmount() +"}";

                System.out.println(getAllChildren(transaction));
                return dataToJson(getAllChildren(transaction));
            } else {
                response.status(404); // 404 Not found
                return  "Transaction not found";
            }
        });


    }
}
