package me.poberherr.transactionservice;

import me.poberherr.transactionservice.handlers.*;
import me.poberherr.transactionservice.model.Model;
import me.poberherr.transactionservice.store.StoreManager;

import static spark.Spark.*;

public class RestfulHandler {

    private StoreManager trans = new StoreManager();

    public void listen() {

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
        get("/types", new TypeIndexHandler(model));
        get("/types/:name", new TypeSingleHandler(model));


        /**
         *      Sums
         */
//        get("/sum", new SumIndexHandler(model));
//        get("/sum/:id", new SumSingleHandler(model));
    }
}


