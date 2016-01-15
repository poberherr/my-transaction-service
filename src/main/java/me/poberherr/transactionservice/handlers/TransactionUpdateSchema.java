package me.poberherr.transactionservice.handlers;

import me.poberherr.transactionservice.Validable;

class TransactionUpdateSchema implements Validable {
    private Double amount;
    private String type;
    private long parent_id;

    public TransactionUpdateSchema() {
    }

    @Override
    // TODO: check that it cant be it's own parent
    public boolean isValid() {
        return amount != null
                && type != null
                && !type.isEmpty();
        //&& parent_id != transactionId; // sadly can't be my own father -luke
    }

    public Double getAmount() {
        return this.amount;
    }

    public String getType() {
        return this.type;
    }

    public long getParent_id() {
        return this.parent_id;
    }

    public void setAmount(Double amount) {
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
        if (!(o instanceof TransactionUpdateSchema)) return false;
        final TransactionUpdateSchema other = (TransactionUpdateSchema) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$amount = this.amount;
        final Object other$amount = other.amount;
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        final Object this$type = this.type;
        final Object other$type = other.type;
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        if (this.parent_id != other.parent_id) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $amount = this.amount;
        result = result * PRIME + ($amount == null ? 0 : $amount.hashCode());
        final Object $type = this.type;
        result = result * PRIME + ($type == null ? 0 : $type.hashCode());
        final long $parent_id = this.parent_id;
        result = result * PRIME + (int) ($parent_id >>> 32 ^ $parent_id);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof TransactionUpdateSchema;
    }

    public String toString() {
        return "me.poberherr.transactionservice.handlers.TransactionUpdateSchema(amount=" + this.amount + ", type=" + this.type + ", parent_id=" + this.parent_id + ")";
    }
}
