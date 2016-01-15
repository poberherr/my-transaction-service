package transactionservice.model;

import lombok.Data;

/**
 * Created by paTimu on 1/13/2016.
 */
@Data
public class Transaction {
    private long id;
    private double amount;
    private String type;
    private long parent_id;

    public Transaction(long id) {
        this.id = id;
    }
//
//    public long getId() {
//        return id;
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//
//    public long getParent_id() {
//        return parent_id;
//    }
//
//    public void setParent_id(long parent_id) {
//        this.parent_id = parent_id;
//    }
//
//    @Override
//    public String toString() {
//        return "transactionservice.model.Transaction{" +
//                "id=" + id +
//                ", amount=" + amount +
//                ", type='" + type + '\'' +
//                ", parent_id=" + parent_id +
//                '}';
//    }
}
