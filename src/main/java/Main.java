//package me.tomassetti;

import static spark.Spark.get;
import static spark.Spark.post;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    private static final int HTTP_BAD_REQUEST = 400;

    interface Validable {
        boolean isValid();
    }

    @Data
    static class NewTransactionPayload {
        private Double amount;
        private String type;
        private long parent_id;

        public boolean isValid() {
            // A custom error code & message would be nice.
            return  amount != null && type != null && !type.isEmpty();
        }
    }

    // In a real application you may want to use a DB, for this example we just store the posts in memory
    public static class Model {
        private int nextId = 1;
        private Map transactions = new HashMap<>();

        @Data
        class Transaction {
            private long id;
            private double amount;
            private String type;
            private long parent_id;
        }

        public long createTransaction(double amount, String type, long parent_id){
            long id = nextId++;
            Transaction transaction = new Transaction();
            transaction.setId(id);
            transaction.setAmount(amount);
            transaction.setType(type);
            transaction.setParent_id(parent_id);
            transactions.put(id, transaction);
            return id;
        }

        public Object getAllTransactions(){
            return transactions.keySet().stream().sorted().map((id) -> transactions.get(id)).collect(Collectors.toList());
        }
    }

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



    public static void main(String[] args) {
        Model model = new Model();

        post("/transaction", (request, response) -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                NewTransactionPayload creation = mapper.readValue(request.body(), NewTransactionPayload.class);
                if (!creation.isValid()) {
                    response.status(HTTP_BAD_REQUEST);
                    // insert here proper http error code
                    return "";
                }
                long id = model.createTransaction(creation.getAmount(), creation.getType(), creation.getParent_id());
                response.status(200);
                response.type("application/json");
                return id;
            } catch (JsonParseException jpe) {
                response.status(HTTP_BAD_REQUEST);
                return "";
            }
        });

        get("/transaction", (request, response) -> {
            response.status(200);
            response.type("application/json");
            return dataToJson(model.getAllTransactions());
        });
    }
}