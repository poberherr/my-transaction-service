import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by paTimu on 1/14/2016.
 */
public class TransactionManager {

    /**
     * Map holding the transactions
     */
    private Map<String, Transaction> transactions;
    private Map<String, List> transactionTypes;
    private static int nextId = 1;

    public TransactionManager() {
        this.transactions = new HashMap<String, Transaction>();;
        this.transactionTypes = new HashMap<String, List>();
    }


    public Object getAllTransactions(){
        return transactions.keySet().stream().sorted().map((id) -> transactions.get(id)).collect(Collectors.toList());
    }

    public long createTransaction(){
        Transaction transaction = new Transaction(nextId);
        transactions.put(String.valueOf(nextId), transaction);
        nextId++;
        return transaction.getId();
    }

    public Transaction getTransactionById(String id){
        Transaction transaction = transactions.get(id);
        return transaction;
    }


}
