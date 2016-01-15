package me.poberherr.transactionservice.model;

public class Transaction {
    private long id;
    private double amount;
    private String type;
    private long parent_id;

    public Transaction(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getType() {
        return this.type;
    }

    public long getParent_id() {
        return this.parent_id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Transaction)) return false;
        final Transaction other = (Transaction) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.id != other.id) return false;
        if (Double.compare(this.amount, other.amount) != 0) return false;
        final Object this$type = this.type;
        final Object other$type = other.type;
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        if (this.parent_id != other.parent_id) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.id;
        result = result * PRIME + (int) ($id >>> 32 ^ $id);
        final long $amount = Double.doubleToLongBits(this.amount);
        result = result * PRIME + (int) ($amount >>> 32 ^ $amount);
        final Object $type = this.type;
        result = result * PRIME + ($type == null ? 0 : $type.hashCode());
        final long $parent_id = this.parent_id;
        result = result * PRIME + (int) ($parent_id >>> 32 ^ $parent_id);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Transaction;
    }

    public String toString() {
        return "me.poberherr.transactionservice.model.Transaction(id=" + this.id + ", amount=" + this.amount + ", type=" + this.type + ", parent_id=" + this.parent_id + ")";
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
//        return "me.poberherr.transactionservice.model.Transaction{" +
//                "id=" + id +
//                ", amount=" + amount +
//                ", type='" + type + '\'' +
//                ", parent_id=" + parent_id +
//                '}';
//    }
}
