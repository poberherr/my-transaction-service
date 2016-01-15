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

        /**
         *      Transactions
         */
        post("/transaction", new TransactionCreateHandler(model));
        get("/transaction", new TransactionIndexHandler(model));
        get("/transaction/:id", new TransactionSingleHandler(model));
        put("/transaction/:id", new TransactionUpdateHandler(model));
        /* The question is if a transaction ever can be deleted.
           In my billing experience I vote for creating an inverse of the transaction
           I leave this open for now
         */

        /**
         *      Types
         */
//        get("/types", new TypeIndexHandler(model));
//        get("/types/:name", new TypeSingleHandler(model));


        /**
         *      Sums
         */
//        get("/sum", new SumIndexHandler(model));
//        get("/sum/:id", new SumSingleHandler(model));
    }
}


