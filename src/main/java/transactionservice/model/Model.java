package transactionservice.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Model {
    Map<String, List<Long>> getAllTransactionTypes();
    Object getAllTransactions();
    long createTransaction();
    void updateTransaction(String id, double amount, String newType, long parent_id);
    Transaction handleTransactionTypeChange(Transaction transaction, String newType);
    void removeTransactionFromType(long transactionId, String type);
    void addTransactionToType(long transactionId, String newType);
    Transaction getTransactionById(String id);
    List getTransactionsByType(String type);
}