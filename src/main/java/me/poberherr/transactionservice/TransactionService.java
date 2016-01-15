package me.poberherr.transactionservice;

public class TransactionService {
    public static void main(String[] args) {
        RestfulHandler restHandler = new RestfulHandler();
        restHandler.listen();
    }
}
