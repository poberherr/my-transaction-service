package me.poberherr.transactionservice.model;

import java.util.List;
import java.util.Map;

public interface Model {
    Map<String, List<Long>> getAllTransactionTypes();

    Map getAllTransactions();

    long createTransaction();

    void updateTransaction(String id, double amount, String newType, long parent_id);

    double calculateSumIncludingChildren(long parentId);

    Transaction handleTransactionTypeChange(Transaction transaction, String newType);

    void removeTransactionFromType(long transactionId, String type);

    void addTransactionToType(long transactionId, String newType);

    Transaction getTransactionById(String id);

    List getTransactionsByType(String type);
}