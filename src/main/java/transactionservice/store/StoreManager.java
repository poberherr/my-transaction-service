package transactionservice.store;

import transactionservice.model.Model;
import transactionservice.model.Transaction;

import java.util.*;

/**
 * Created by paTimu on 1/14/2016.
 */
public class StoreManager implements Model {

    /**
     * Map holding the transactions
     */
    private Map<String, Transaction> transactions;
    private Map<String, List<Long>> transactionOfTypes;
    private static int nextId = 1;

    public StoreManager() {
        this.transactions = new HashMap<String, Transaction>();
        this.transactionOfTypes = new HashMap<String, List<Long>>();
    }

    public Map<String, List<Long>> getAllTransactionTypes() {
        return transactionOfTypes;
    }

    public Object getAllTransactions(){
        return transactions;
    }

    public long createTransaction(){
        Transaction transaction = new Transaction(nextId);
        transactions.put(String.valueOf(nextId), transaction);
        nextId++;
        return transaction.getId();
    }


    public void updateTransaction(String id, double amount, String newType, long parent_id){
        Transaction oldTransaction = transactions.get(id);
        // TODO: No side effects YET
        oldTransaction.setAmount(amount);

        oldTransaction = handleTransactionTypeChange(oldTransaction, newType);

        oldTransaction.setParent_id(parent_id);
        // TODO: Return false on error update
    }

    public Transaction handleTransactionTypeChange(Transaction transaction, String newType){
        // Check if the transaction type changed
        if (transaction.getType() == null) addTransactionToType(transaction.getId(), newType);
        else if (!transaction.getType().equals(newType)) {
            removeTransactionFromType(transaction.getId(), transaction.getType());
            addTransactionToType(transaction.getId(), newType);
        }
        transaction.setType(newType);
        return transaction;
    }

    public void removeTransactionFromType(long transactionId, String type){
        if(type != null && !type.isEmpty()){
            List<Long> idList = transactionOfTypes.get(type);
            idList.remove(transactionId);

            // Maybe we emptied the type and need to remove
            if (idList.isEmpty()){
                transactionOfTypes.remove(type);
            }
        }
    }

    public void addTransactionToType(long transactionId, String newType){
        if(newType != null && !newType.isEmpty()) {
            // Check if type already exists
            boolean typeExists = transactionOfTypes.containsKey(newType);
            if (typeExists) {
                List<Long> idList = transactionOfTypes.get(newType);
                idList.add(transactionId);
            } else {
                List<Long> idList = new ArrayList<>();
                idList.add(transactionId);
                transactionOfTypes.put(newType, idList);
            }
        }
    }

    public Transaction getTransactionById(String id){
        Transaction transaction = transactions.get(id);
        return transaction;
    }

    public List getTransactionsByType(String type){
        return transactionOfTypes.get(type);
    }

}
