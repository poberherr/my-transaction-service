package me.poberherr.transactionservice.store;

import me.poberherr.transactionservice.model.Model;
import me.poberherr.transactionservice.model.Transaction;

import java.util.*;

public class StoreManager implements Model {

    /**
     * Map holding the transactions
     */
    private Map<String, Transaction> transactions;
    private Map<String, List<Long>> transactionOfTypes;
    private Map<String, List<Long>> transactionParentToChild;

    private static int nextId = 1;

    public StoreManager() {
        this.transactions = new HashMap<String, Transaction>();
        this.transactionOfTypes = new HashMap<String, List<Long>>();
        this.transactionParentToChild = new HashMap<String, List<Long>>();
    }

    public Map<String, List<Long>> getAllTransactionTypes() {
        return transactionOfTypes;
    }

    public Map getAllTransactions() {
        return transactions;
    }

    public long createTransaction() {
        Transaction transaction = new Transaction(nextId);
        transactions.put(String.valueOf(nextId), transaction);
        nextId++;
        return transaction.getId();
    }


    public void updateTransaction(String id, double amount, String newType, long parent_id) {
        Transaction oldTransaction = transactions.get(id);
        // TODO: No side effects YET
        oldTransaction.setAmount(amount);

        oldTransaction = handleTransactionTypeChange(oldTransaction, newType);
        oldTransaction = handleParentChange(oldTransaction, parent_id);
    }

    public Transaction handleParentChange(Transaction transaction, long newParent) {
        if (transaction.getParent_id() == newParent) {
            return transaction;
        }
        if (transaction.getParent_id() == 0) {
            addParentToChild(transaction.getId(), newParent);

        } else {
            removeParentFromChild(transaction.getId(), transaction.getParent_id());
            addParentToChild(transaction.getId(), newParent);
        }

        transaction.setParent_id(newParent);
        transactions.put(String.valueOf(transaction.getId()), transaction);
        return transaction;
    }

    public void removeParentFromChild(long childId, long oldParent) {
        List<Long> idList = transactionParentToChild.get(oldParent);
        idList.remove(childId);

        // Maybe we emptied the key content and need to remove
        if (idList.isEmpty()) {
            transactionParentToChild.remove(oldParent);
        }
    }

    public void addParentToChild(long childId, long newParent) {
        boolean parentExists = transactionParentToChild.containsKey(newParent);
        if (parentExists) {
            List<Long> idList = transactionParentToChild.get(newParent);
            idList.add(childId);
        } else {
            List<Long> idList = new ArrayList<>();
            idList.add(childId);
            transactionParentToChild.put(String.valueOf(newParent), idList);
        }
    }

    public double calculateSumOfAllTransactions(){
        double sum = 0;
        for (String key : transactions.keySet()) {
            Transaction trans = getTransactionById(key);
            sum += trans.getAmount();
        }
        return sum;
    }

    public double calculateSumIncludingChildren(long parentId){
        double sum = 0;
        String parentKey = String.valueOf(parentId);
        if (transactionParentToChild.containsKey(parentKey)){
            // Sum up all the children
            List<Long> childList = transactionParentToChild.get(String.valueOf(parentId));
            for (long item : childList) {
                Transaction trans = getTransactionById(String.valueOf(item));
                sum += trans.getAmount();
            }
        }
        // Add own value
        if (transactions.containsKey(parentKey)) {
            sum += getTransactionById(parentKey).getAmount();
        }
        return sum;
    }

    public Transaction handleTransactionTypeChange(Transaction transaction, String newType) {
        // Check if the transaction type changed
        if (transaction.getType() == null) addTransactionToType(transaction.getId(), newType);
        else if (!transaction.getType().equals(newType)) {
            removeTransactionFromType(transaction.getId(), transaction.getType());
            addTransactionToType(transaction.getId(), newType);
        }
        transaction.setType(newType);
        return transaction;
    }

    public void removeTransactionFromType(long transactionId, String type) {
        if (type != null && !type.isEmpty()) {
            List<Long> idList = transactionOfTypes.get(type);
            idList.remove(transactionId);

            // Maybe we emptied the type and need to remove
            if (idList.isEmpty()) {
                transactionOfTypes.remove(type);
            }
        }
    }

    public void addTransactionToType(long transactionId, String newType) {
        if (newType != null && !newType.isEmpty()) {
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

    public Transaction getTransactionById(String id) {
        Transaction transaction = transactions.get(id);
        return transaction;
    }

    public List getTransactionsByType(String type) {
        return transactionOfTypes.get(type);
    }

}
